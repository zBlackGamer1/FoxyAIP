package pt.com.FoxyAPI;

import org.black_ixx.playerpoints.PlayerPoints;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;

import br.com.ystoreplugins.product.yclans.ClanAPIHolder;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.milkbowl.vault.economy.Economy;
import pt.com.FoxyAPI.comands.BuffCMD;
import pt.com.FoxyAPI.comands.FoxyCMD;
import pt.com.FoxyAPI.comands.LixeiroCMD;
import pt.com.FoxyAPI.comands.OffCMD;
import pt.com.FoxyAPI.comands.OnCMD;
import pt.com.FoxyAPI.comands.ReportCMD;
import pt.com.FoxyAPI.comands.ReportsCMD;
import pt.com.FoxyAPI.listeners.CombatLog;
import pt.com.FoxyAPI.listeners.StatsViewEventos;
import pt.com.FoxyAPI.methods.Cache;
import pt.com.FoxyAPI.methods.EsconderMetodo;
import pt.com.FoxyAPI.methods.Lixeiro;
import pt.com.FoxyAPI.methods.ReportsList;
import pt.com.FoxyAPI.utils.Command;
import pt.com.FoxyAPI.utils.NumberFormatter;
import pt.com.FoxyAPI.utils.zBUtils;

public class Main extends JavaPlugin {
	private static Main instance;
	public static Economy econ = null;
	public static ClanAPIHolder yClans = null;
	public static LuckPerms luckPerms = null;
	public static NumberFormatter formatter;
	public static Cache cache;
	public static EsconderMetodo esconder;
	public static Lixeiro lixeiro;
	private PlayerPoints playerPoints;
	@Override
	public void onEnable() {
		instance = this;
//		setupEconomy();
//		setupYClans();
//		setupLuckPerms();
		saveDefaultConfig();
		formatter = new NumberFormatter();
//		Plugin plugin = this.getServer().getPluginManager().getPlugin("PlayerPoints");
//		playerPoints = PlayerPoints.class.cast(plugin);
		cache = new Cache();
		esconder = new EsconderMetodo();
		lixeiro = new Lixeiro();
		loadListeners();
		loadCmds();
		zBUtils.ConsoleMsg("&7[&bFoxyAPI&7] &aO plugin foi iniciado.");
	}
	
	@Override
	public void onDisable() {
		for(Hologram h : HologramsAPI.getHolograms(this)) h.delete();
		zBUtils.ConsoleMsg("&7[&bFoxyAPI&7] &cO plugin foi encerrado.");
	}
	
	private void loadListeners() {
		Bukkit.getPluginManager().registerEvents(new CombatLog(), this);
		Bukkit.getPluginManager().registerEvents(new StatsViewEventos(), this);
		Bukkit.getPluginManager().registerEvents(new ReportsList(), this);
	}
	
	private void loadCmds() {
		new Command("on", null, "&cSem permissão!", "Comando para voltar a ver os jogadores", null, new OnCMD());
		new Command("off", null, "&cSem permissão!", "Comando para esconder os jogadores", null, new OffCMD());
 		getCommand("foxy").setExecutor(new FoxyCMD());
 		getCommand("foxy").setPermission("foxy.admin");
 		getCommand("foxy").setPermissionMessage("§cVocê não tem permissão para isso!");
 		getCommand("buff").setExecutor(new BuffCMD());
 		getCommand("buff").setPermission("foxy.buff");
 		getCommand("buff").setPermissionMessage("§cVocê não tem permissão para isso!");
 		getCommand("lixeiro").setExecutor(new LixeiroCMD());
 		getCommand("report").setExecutor(new ReportCMD());
 		getCommand("reports").setExecutor(new ReportsCMD());
 		getCommand("reports").setPermission("foxyreports.reports");
 		getCommand("reports").setPermissionMessage("§cVocê não tem permissão para isso!");
	}
	
	private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
	
	private boolean setupYClans() {
        if (getServer().getPluginManager().getPlugin("yClans") == null) return false;
        RegisteredServiceProvider<ClanAPIHolder> rsp = getServer().getServicesManager().getRegistration(ClanAPIHolder.class);
        if (rsp != null) yClans = rsp.getProvider();
        return yClans != null;
    }
	
	private boolean setupLuckPerms() {
        if (getServer().getPluginManager().getPlugin("LuckPerms") == null) return false;
        luckPerms = LuckPermsProvider.get();
        return luckPerms != null;
    }
	
	public static Main getInstance() {
		return instance;
	}
	
	public PlayerPoints getPlayerPoints() {
	    return playerPoints;
	}
}
