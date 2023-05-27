package pt.com.FoxyAPI.methods;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import pt.com.FoxyAPI.Main;
import pt.com.FoxyAPI.utils.zBUtils;

public class Lixeiro {
	public Integer itensCount;
	public Integer time;
	private void Timer() {
		new BukkitRunnable() {
			@Override
			public void run() {
				switch (time) {
				case 300:
				case 120:
					for(Player todos : Bukkit.getOnlinePlayers()) {
						zBUtils.sendActionBar(todos, "§e§lLIXEIRO! §fO Lixeiro irá passar em §e" + time/60 + " §fminutos!");
						zBUtils.sendSound(todos, Sound.CLICK);
					}
					break;
				case 60:
					for(Player todos : Bukkit.getOnlinePlayers()) {
						zBUtils.sendActionBar(todos, "§e§lLIXEIRO! §fO Lixeiro irá passar em §e1 §fminuto!");
						zBUtils.sendSound(todos, Sound.CLICK);
					}
					break;
				case 30:
				case 10:
				case 5:
				case 4:
				case 3:
				case 2:
					for(Player todos : Bukkit.getOnlinePlayers()) {
						zBUtils.sendActionBar(todos, "§e§lLIXEIRO! §fO Lixeiro irá passar em §e" + time + " §fsegundos!");
						zBUtils.sendSound(todos, Sound.CLICK);
					}
					break;

				case 1:
					for(Player todos : Bukkit.getOnlinePlayers()) {
						zBUtils.sendActionBar(todos, "§e§lLIXEIRO! §fO Lixeiro irá passar em §e1 §fsegundo!");
						zBUtils.sendSound(todos, Sound.CLICK);
					}
					break;
				case 0:
					Limpar();
					break;
				default:
					break;
				}
				time--;
			}
		}.runTaskTimer(Main.getInstance(), 0L, 20L);
	}
	
	public void Limpar() {
		int itens = 0;
		for(World w : Bukkit.getWorlds()) {
			for(Entity e : w.getEntities()) {
				if (Main.cache.mobsLixeiro.contains(e.getType())) {
					e.remove();
					itens++;
				}
			}
		}
		itensCount = itens;
		for(Player onlinePlayers : Bukkit.getOnlinePlayers()) {
			zBUtils.sendActionBar(onlinePlayers, "§e§lLIXEIRO! §fO Lixeiro passou e limpou §e" + itens + "§f itens!");
			zBUtils.sendSound(onlinePlayers, Sound.FIRE_IGNITE);
		}
	}
	
	public String getRestante() {
		if (time > 60) {
			int segundos = time;
			int minutos = time/60;
			segundos = segundos-minutos*60;
			return "§fO Lixeiro irá passar em§6 " + minutos + "m " + segundos + "s§f!";
		} else {
			return "§fO Lixeiro irá passar em§6 " + time + "§f segundos!";
		}
	}
	
	public Lixeiro() {
		itensCount = 0;
		time = 300;
		Timer();
	}
}
