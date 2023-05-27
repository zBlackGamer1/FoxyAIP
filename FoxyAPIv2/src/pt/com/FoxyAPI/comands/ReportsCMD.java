package pt.com.FoxyAPI.comands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pt.com.FoxyAPI.methods.Report;
import pt.com.FoxyAPI.methods.ReportsList;
import pt.com.FoxyAPI.methods.VerReport;

public class ReportsCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command c, String arg2, String[] a) {
		if(!(s instanceof Player)) {
			s.sendMessage("§cComando apenas para jogadores!");
			return true;
		}
		Player p = (Player)s;
		if (a.length != 2 || !a[0].equalsIgnoreCase("ver") || !isInteger(a[1])) {
			ReportsList.open(p, 1);
			return true;
		}
		VerReport.open(p, Report.getByID(Integer.parseInt(a[1])));
		return true;
	}
	
	private boolean isInteger(String s) {
		return Integer.parseInt(s) > 0;
	}
}
