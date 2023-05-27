package pt.com.FoxyAPI.comands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pt.com.FoxyAPI.Main;

public class LixeiroCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command c, String arg2, String[] a) {
		if (!(s instanceof Player)) {
			if (a.length != 1) {
				s.sendMessage("§cPara forçar o lixeiro a passar use /lixeiro force.");
				return true;
			}
			if(a[0].equalsIgnoreCase("force")) {
				Main.lixeiro.Limpar();
			} else s.sendMessage("§cPara forçar o lixeiro a passar use /lixeiro force.");
			return true;
		}
		Player p = (Player)s;
		if(!p.hasPermission("foxy.admin")) {
			p.sendMessage(new String[] {
					"",
					"§6 ➡ " + Main.lixeiro.getRestante(),
					"§6 ➡ §fNa ultima vez o lixeiro limpou §6" + Main.lixeiro.itensCount + " §fitens.",
					""
			});
			return true;
		}
		
		if(a.length != 1) {
			p.sendMessage("§c§lERRO: §fUse §c/lixeiro help §fpara ver todos os comandos.");
			return true;
		}
		switch (a[0].toLowerCase()) {
		case "ajuda":
		case "help":
		case "?":
			p.sendMessage(new String[] {
					"",
					"   §6§lFOXY LIXEIRO",
					"",
					" §6➡ §f/lixeiro force",
					" §6➡ §f/lixeiro tempo",
					" §6➡ §f/lixeiro ajuda",
					""
			});
			break;
		case "force":
			Main.lixeiro.Limpar();
			break;
			
		case "tempo":
		case "info":
			p.sendMessage(new String[] {
					"",
					"§6 ➡ " + Main.lixeiro.getRestante(),
					"§6 ➡ §fNa ultima vez o lixeiro limpou §6" + Main.lixeiro.itensCount + " §fitens.",
					""
			});
			break;

		default:
			p.sendMessage("§c§lERRO: §fUse §c/lixeiro help §fpara ver todos os comandos.");
			break;
		}
		return true;
	}
}
