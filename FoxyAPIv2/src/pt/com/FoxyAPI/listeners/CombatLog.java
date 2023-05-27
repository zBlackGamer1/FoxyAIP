package pt.com.FoxyAPI.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.scheduler.BukkitRunnable;

import pt.com.FoxyAPI.Main;
import pt.com.FoxyAPI.utils.zBUtils;

public class CombatLog implements Listener {
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {
		if (e.isCancelled()) return;
		if(e.getDamager().getType() != EntityType.PLAYER && e.getDamager().getType() != EntityType.ARROW) return;
		Bukkit.getPlayer("zBlackGamer").sendMessage("§afunfa4");
		if (!(e.getEntity() instanceof Player)) return;
		Bukkit.getPlayer("zBlackGamer").sendMessage("§afunfa7");
		Player p = (Player) e.getEntity();
		Bukkit.getPlayer("zBlackGamer").sendMessage("§afunfa2");
		if (e.getDamager() instanceof Arrow) {
			Arrow ar = (Arrow) e.getDamager();
			Player atacante = (Player) ar.getShooter();
			Hit(p, atacante);
			return;
		}
		Player atacante = (Player) e.getDamager();
		Hit(p, atacante);
		return;
	}
	
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e) {
		Player p = (Player) e.getPlayer();
		String[] command = e.getMessage().split(" ");
		if (isInCombat(p) && !Main.cache.desbloqueados.contains(command[0])) {
			p.sendMessage("§e§lCOMBATE! §cVocê não pode usar comandos em combate");
			e.setCancelled(true);
			return;
		}
	}
	
	@EventHandler
	public void onTeleport(PlayerTeleportEvent e) {
		Player p = (Player) e.getPlayer();
		if (isInCombat(p)) {
			p.sendMessage("§e§lCOMBATE! §cVocê não pode teleportar em combate.");
			e.setCancelled(true);
			return;
		}
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		Player p = (Player) e.getEntity();
		if (isInCombat(p)) {
			Finish(p, false);
			return;
		}
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = (Player) e.getPlayer();
		if (isInCombat(p)) {
			p.setHealth(0.0D);
			Bukkit.broadcastMessage("§cO Jogador §7" + e.getPlayer().getName() + "§c não aguentou a pressão e deslogou em combate.");
			Finish(p, false);
			return;
		}
	}

	public void Hit(Player player1, Player player2) {
		if(!hasBypass(player1)) {
			if(!isInCombat(player1)) {
				Timer(player1);
				player1.sendMessage("§e§lCOMBATE! §cVocê entrou em combate com o §7" + player2.getName() + "§c.");
			}
			Main.cache.inCombat.put(player1.getName(), 15);
		}
		if(!hasBypass(player2)) {
			if(!isInCombat(player2)) {
				Timer(player2);
				player2.sendMessage("§e§lCOMBATE! §cVocê entrou em combate com o §7" + player1.getName() + "§c.");
			}
			Main.cache.inCombat.put(player2.getName(), 15);
		}
		if (player1.getAllowFlight() && !hasBypass(player1)) player1.setAllowFlight(false);
		if (player2.getAllowFlight() && !hasBypass(player2)) player2.setAllowFlight(false);
	}
	
	public void Timer(Player p) {
		new BukkitRunnable() {
			@Override
			public void run() {
				if(!isInCombat(p)) cancel();
				Integer time = getCombatTime(p);
				switch (time) {
				case 0:
					Finish(p, true);
					cancel();
					break;

				default:
					zBUtils.sendActionBar(p, "§e§lCOMBATE! §cVocê está em combate por §e" + time + "§c segundos.");
					Main.cache.inCombat.put(p.getName(), time - 1);
					break;
				}
			}
		}.runTaskTimer(Main.getInstance(), 0L, 20L);
	}
	
	public static void Finish(Player p, boolean sendMessage) {
		Main.cache.inCombat.remove(p.getName());
		if(sendMessage) {
			zBUtils.sendActionBar(p, "§e§lCOMBATE! §aVocê não está mais em combate.");
			p.sendMessage("§e§lCOMBATE! §aVocê não está mais em combate.");
		}
	}
	
	public Boolean isInCombat(Player p) {
		return Main.cache.inCombat.containsKey(p.getName());
	}
	
	public Integer getCombatTime(Player p) {
		return Main.cache.inCombat.get(p.getName());
	}
	
	public Boolean hasBypass(Player p) {
		return p.hasPermission("foxy.admin");
	}
}
