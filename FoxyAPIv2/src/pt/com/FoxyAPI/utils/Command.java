package pt.com.FoxyAPI.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;

import pt.com.FoxyAPI.Main;

public class Command {
	
    private CommandExecutor executor;
    private String command;
    private List<String> aliases;
    private String description;
	private String permissionMessage;
    private String permission;
    private PluginCommand pluginCommand;
    
    public Command(String cmd, String perm, String msgSemPerm, String desc, List<String> alias, CommandExecutor executor) {
    	this.executor = executor;
    	this.command = cmd;
    	this.description = desc;
    	if(alias != null) this.aliases = alias;
    	this.permissionMessage = msgSemPerm.replace("&", "�");
    	this.permission = perm;
    	this.pluginCommand = createPluginCommand();
    	registerPluginCommand();
    }
    
    private PluginCommand createPluginCommand() {
        try {
            Constructor<PluginCommand> c = PluginCommand.class.getDeclaredConstructor(String.class, Plugin.class);
            c.setAccessible(true);
            
            PluginCommand cmd = c.newInstance(command, Main.getInstance());
            if(aliases != null) cmd.setAliases(aliases);
            cmd.setPermission(permission);
            cmd.setPermissionMessage(permissionMessage);
            cmd.setDescription(description);
            cmd.setExecutor(executor);

            return cmd;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    private void registerPluginCommand() {
        if (pluginCommand == null) return;
        try {
            Field f = Bukkit.getPluginManager().getClass().getDeclaredField("commandMap");
            f.setAccessible(true);
            CommandMap commandMap = (CommandMap) f.get(Bukkit.getPluginManager());
            commandMap.register("system", pluginCommand);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

	@Override
	public String toString() {
		return command;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((command == null) ? 0 : command.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Command other = (Command) obj;
		if (command == null) {
			if (other.command != null)
				return false;
		} else if (!command.equals(other.command))
			return false;
		return true;
	}
	
}
