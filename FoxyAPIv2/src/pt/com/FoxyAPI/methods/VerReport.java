package pt.com.FoxyAPI.methods;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class VerReport {
	public static void open(Player p, Report r) {
		if(r == null) {
			ReportsList.open(p, 1);
			return;
		}
		Inventory inv = Bukkit.createInventory(null, 5*9, "§7Report #" + r.getID());
		
		p.openInventory(inv);
	}
}
