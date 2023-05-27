package pt.com.FoxyAPI.methods;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import pt.com.FoxyAPI.Main;
import pt.com.FoxyAPI.utils.UltimateFancy;

public class Report {
	private Integer reportID;
	private String vitima;
	private String autor;
	private String motivo;
	private Date reportDate;
	
	public Integer getID() {
		return this.reportID;
	}
	
	public String getVitima() {
		return this.vitima;
	}
	
	public String getAutor() {
		return this.autor;
	}
	
	public String getMotivo() {
		return this.motivo;
	}
	
	public Date getReportDate() {
		return this.reportDate;
	}
	
	public Report(int reportID, String vitima, String autor, String motivo, Boolean newReport, Date reportDate) {
		this.reportID = reportID;
		this.vitima = vitima;
		this.autor = autor;
		this.motivo = motivo;
		this.reportDate = reportDate;
		Main.cache.allReports.put(reportID, this);
		List<Report> l = (Main.cache.playerReports.containsKey(vitima.toLowerCase())) ? Main.cache.playerReports.get(vitima.toLowerCase()) : new ArrayList<>();
		l.add(this);
		Main.cache.playerReports.put(vitima.toLowerCase(), l);
		if (newReport) {
			UltimateFancy msg = new UltimateFancy();
			msg.text("§eUm report foi realizado clique ").next();
			msg.text("§7§lAQUI").hoverShowText("§aClique para ver jogador reportado!").clickRunCmd("/reports ver " + reportID).next();
			msg.text("§r§e ver detalhes.");
			for(Player p : Bukkit.getOnlinePlayers()) {
				if(!p.hasPermission("foxyreports.staff")) continue;
				p.sendMessage("");
				msg.send(p);
				p.sendMessage("");
			}
		}
	}
	
	public static Report getByID(Integer id) {
		if(!Main.cache.allReports.containsKey(id)) return null;
		return Main.cache.allReports.get(id);
	}
	
}
