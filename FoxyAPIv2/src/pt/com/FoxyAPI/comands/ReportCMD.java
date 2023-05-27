package pt.com.FoxyAPI.comands;

import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pt.com.FoxyAPI.Main;
import pt.com.FoxyAPI.methods.Report;

public class ReportCMD implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender s, Command c, String arg2, String[] a) {
		
		if(!(s instanceof Player)) {
			s.sendMessage("§cComando apenas para jogadores!");
			return true;
		}
		Player p = (Player)s;
		if(a.length < 2) {
			p.sendMessage("§c§lERRO!§c Use /report (Jogador) (Motivo)!");
			return true;
		}
		String vit = a[0];
		if(Bukkit.getPlayer(a[0]) != null) vit = Bukkit.getPlayer(a[0]).getName();
		if(Bukkit.getOfflinePlayer(a[0]) != null) vit = Bukkit.getOfflinePlayer(a[0]).getName();
		new Report(Main.cache.getNextReportID(), vit, p.getName(), arg2, true, new Date());
		p.sendMessage("§aO jogador " + vit + " §afoi reportado com sucesso!");
		return true;
	}
}
