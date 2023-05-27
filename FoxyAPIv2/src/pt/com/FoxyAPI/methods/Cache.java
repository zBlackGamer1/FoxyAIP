package pt.com.FoxyAPI.methods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.entity.EntityType;

import pt.com.FoxyAPI.Main;

public class Cache {
	public List<String> hide;
	public Map<String, Integer> inCombat;
	public Double buffPrice;
	public List<String> desbloqueados;
	public List<EntityType> mobsLixeiro;
	
	public Map<Integer, Report> allReports;
	public Map<String, List<Report>> playerReports;
	
	public Cache() {
		hide = new ArrayList<>();
		inCombat = new HashMap<>();
		buffPrice = Main.getInstance().getConfig().getDouble("Buff-Price");
		desbloqueados = Arrays.asList("/tell", "/r", "/l", "/g", "/.", "/c", "/a");
		mobsLixeiro = Arrays.asList(EntityType.DROPPED_ITEM, EntityType.BAT, EntityType.BLAZE, EntityType.CAVE_SPIDER, EntityType.CHICKEN, EntityType.COW,
				EntityType.CREEPER, EntityType.ENDERMAN, EntityType.ENDERMITE, EntityType.GHAST, EntityType.GUARDIAN, EntityType.HORSE, EntityType.IRON_GOLEM,
				EntityType.MAGMA_CUBE, EntityType.MUSHROOM_COW, EntityType.OCELOT, EntityType.PIG, EntityType.PIG_ZOMBIE, EntityType.RABBIT, EntityType.SHEEP,
				EntityType.SILVERFISH, EntityType.SKELETON, EntityType.SLIME, EntityType.SPIDER, EntityType.SQUID, EntityType.VILLAGER, EntityType.WITCH,
				EntityType.WITHER, EntityType.WOLF, EntityType.ZOMBIE);
		
		allReports = new HashMap<>();
		playerReports = new HashMap<>();
	}
	
	public Integer getNextReportID() {
		return allReports.size() + 1;
	}
}
