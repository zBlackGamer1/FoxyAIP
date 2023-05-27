package pt.com.FoxyAPI.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import br.com.ystoreplugins.product.yclans.internal.ClanPlayerHolder;
import pt.com.FoxyAPI.Main;
import pt.com.FoxyAPI.utils.NumberFormatter;
import pt.com.FoxyAPI.utils.zBUtils;
import rush.sistemas.comandos.VanishListener;

public class StatsViewEventos implements Listener {
	static NumberFormatter formatter = new NumberFormatter();

	@SuppressWarnings({ "unused" })
	public <T extends Player> T getTarget(final Player entity, final Iterable<T> entities) {
        if (entity == null) {
            return null;
        }
        T target = null;
        final double threshold = 1.0;
        for (final T other : entities) {
            final Vector n = other.getLocation().toVector().subtract(entity.getLocation().toVector());
            if (entity.getLocation().getDirection().normalize().crossProduct(n).lengthSquared() < 1.0 && n.normalize().dot(entity.getLocation().getDirection().normalize()) >= 0.0) {
                if (target == null || target.getLocation().distanceSquared(entity.getLocation()) > other.getLocation().distanceSquared(entity.getLocation())) {
                    target = other;
                }
                Ver(entity, target);
            }
        }
        return target;
    }
	
	public Player getTargetPlayer(final Player player) {
        return this.getTarget(player, (Iterable<Player>)player.getWorld().getPlayers());
    }
	
	@EventHandler
    public void onMove(final PlayerMoveEvent e) {
        final Player p = e.getPlayer();
        this.getTargetPlayer(p);
    }
	
	@SuppressWarnings("deprecation")
	public void Ver(Player p, Player t) {
		if(isVanish(t)) return;
		double money = Main.econ.getBalance(t);
		double cash = Main.getInstance().getPlayerPoints().getAPI().look(t.getName());
		ClanPlayerHolder clanPlayer = Main.yClans.getPlayer(t);
		String playerName = Main.luckPerms.getGroupManager().getGroup(Main.luckPerms.getUserManager().getUser(t.getUniqueId()).getPrimaryGroup()).getCachedData().getMetaData().getSuffix().replace("&", "§") + " " + t.getName();
		if (!clanPlayer.hasClan()) {
			// [CLAN] Nick (com tag) - coins: 1 Cash: 1
			zBUtils.sendActionBar(p, "" + playerName + "§7 - §fCoins: §6" + formatter.formatNumber(money) + "§7 - §fCash: §6" + formatter.formatNumber(cash));
			return;
		}
		String clanTAG = clanPlayer.getClanTag();
		zBUtils.sendActionBar(p, "§7[" + clanTAG + "] " + playerName + "§7 - §fCoins: §6" + formatter.formatNumber(money) + "§7 - §fCash: §6" + formatter.formatNumber(cash));
	}
	
	private boolean isVanish(Player player) {
		return VanishListener.VANISHEDS.contains(player);
	}
}