package pt.com.FoxyAPI.comands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import pt.com.FoxyAPI.Main;
import pt.com.FoxyAPI.utils.NumberFormatter;

public class BuffCMD implements CommandExecutor{
	static NumberFormatter formatter = new NumberFormatter();
	@Override
	public boolean onCommand(CommandSender s, Command c, String arg2, String[] a) {
		if (!(s instanceof Player)) {
			s.sendMessage("A console não pode executar este comando.");
			return true;
		}
		
		Player p = (Player)s;
		if (Main.cache.buffPrice <= Main.econ.getBalance(p)) {
			Main.econ.withdrawPlayer(p, Main.cache.buffPrice);
			p.sendMessage("§e§lBUFF! §fVocê ativou o buff, pagou§b " + formatter.formatNumber(Main.cache.buffPrice) + "§f e recebeu:");
			p.sendMessage("§7 ➟ §cForça II §7- §b2m");
			p.sendMessage("§7 ➟ §cVelocidade II §7- §b2m");
			giveEfeitos(p);
		} else {
			p.sendMessage("§e§lBUFF! §fVocê não tem coins suficientes para ativar.");
		}
		return true;
	}
	
	private static void giveEfeitos(Player p) {
		p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20*120, 1), true);
		p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20*120, 1), true);
	}

}