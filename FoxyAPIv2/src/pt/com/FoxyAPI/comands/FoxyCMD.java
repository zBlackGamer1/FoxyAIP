package pt.com.FoxyAPI.comands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pt.com.FoxyAPI.utils.UltimateFancy;

public class FoxyCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command c, String arg2, String[] a) {
		if (!(s instanceof Player)) {
			s.sendMessage(new String[] {
					"§6§l   FOXY API - FUNCIONALIDADES    ",
					"§f > §eFoxyReports",
					"§f > §eFoxyEsconder",
					"§f > §eFoxyCombatLog",
					"§f > §eFoxyLixeiro",
					"§f > §eFoxyStatsView",
					"§f > §eFoxyBuff",
					"§f > §eFoxyAFK",
					"§f > §eFoxyCheques",
					"",
					"§6§l   FOXY API - COMANDOS    ",
					"§f > §e/foxy",
					"§f > §e/report",
					"§f > §e/reports",
					"§f > §e/on",
					"§f > §e/off",
					"§f > §e/lixeiro",
					"§f > §e/buff",
					"§f > §e/cheques",
					""
			});
			return true;
		}
		Player p = (Player)s;
		switch (a.length) {
		case 1:
			if (a[0].equalsIgnoreCase("comandos")) {
				p.sendMessage(new String[] {
						"§6§l   FOXY API - COMANDOS    ",
						"§f │ §e/foxy",
						"§f │ §e/on",
						"§f │ §e/off",
						"§f │ §e/lixeiro",
						"§f │ §e/buff",
						"§f │ §e/report",
						"§f │ §e/reports",
						""
				});
				break;
			}
			if (a[0].equalsIgnoreCase("funcionalidades")) {
				p.sendMessage(new String[] {
						"§6§l   FOXY API - FUNCIONALIDADES    ",
						"§f │ §eFoxyEsconder",
						"§f │ §eFoxyCombatLog",
						"§f │ §eFoxyLixeiro",
						"§f │ §eFoxyStatsView",
						"§f │ §eFoxyBuff",
						"§f │ §eFoxyAFK",
						"§f │ §eFoxyReports",
						""
				});
				break;
			}
			break;

		default:
			UltimateFancy msg = new UltimateFancy();
			msg.text("§e§lFOXYAPI! §r§fClique para ver ").next();
			msg.text("§e§lCOMANDOS").hoverShowText("§aClique para ver!").clickRunCmd("/foxyapi comandos").next();
			msg.text(" §r§fou ").next();
			msg.text("§e§lFUNCIONALIDADES").hoverShowText("§aClique para ver!").clickRunCmd("/foxyapi funcionalidades").next();
			msg.send(p);
			break;
		}
		return true;
	}
}
