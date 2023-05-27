package pt.com.FoxyAPI.comands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pt.com.FoxyAPI.Main;

public class OnCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command c, String arg2, String[] a) {
		if (!(s instanceof Player)) {
			s.sendMessage("§cErro! Comando apenas para jogadores.");
			return true;
		}
		Player p = (Player)s;
		Main.esconder.ON(p, false);
		return true;
	}
}
