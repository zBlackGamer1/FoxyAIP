package pt.com.FoxyAPI.methods;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import pt.com.FoxyAPI.Main;

public class EsconderMetodo {
	public void OFF(Player p, Boolean sendMessage) {
		if (isAtivo(p) && sendMessage) {
			p.sendMessage("§c§lERRO! §cOs jogadores já estão escondidos!");
			return;
		}
		Main.cache.hide.add(p.getName());
		for(Player online : Bukkit.getOnlinePlayers()) p.hidePlayer(online);
		if (sendMessage) {
			p.sendMessage(new String[] {
					"",
					"§cTodos os jogadores foram escondidos.",
					"§cPara voltar a ver os jogadores use §e/on§c.",
					"§c§lOBS: §7Os outros jogadores conseguem vê-lo.",
					""
			});
			return;
		}
	}
	
	public void ON(Player p, Boolean forced) {
		if (!isAtivo(p) && !forced) {
			p.sendMessage("§cVocê não tem os jogadores escondidos.");
			return;
		}
		Main.cache.hide.remove(p.getName());
		for(Player online : Bukkit.getOnlinePlayers()) p.showPlayer(online);
		if (!forced) {
			p.sendMessage(new String[] {
					"",
					"§aVocê está agora vendo todos os jogadores.",
					""
			});
			return;
		}
	}
	
	public Boolean isAtivo(Player p) {
		return Main.cache.hide.contains(p.getName());
	}
}
