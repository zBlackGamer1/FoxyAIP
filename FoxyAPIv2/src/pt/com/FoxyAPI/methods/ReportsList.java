package pt.com.FoxyAPI.methods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import pt.com.FoxyAPI.Main;
import pt.com.FoxyAPI.utils.ItemBuilder;
import pt.com.FoxyAPI.utils.NBTAPI;

public class ReportsList implements Listener {
	@EventHandler
	void onClick(InventoryClickEvent e) {
		if(!e.getInventory().getName().equalsIgnoreCase("§7Reportes - Lista")) return;
		if(e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) return;
		e.setCancelled(true);
		Player p = (Player) e.getWhoClicked();
		switch (e.getSlot()) {
		case 45:
			open(p, getPage(e.getInventory()) - 1);
			break;
		case 53:
			open(p, getPage(e.getInventory()) + 1);
			break;
		default:
			if (NBTAPI.getNBT(e.getCurrentItem()).hasKey("rep_id")) {
				VerReport.open(p, Report.getByID(NBTAPI.getNBT(e.getCurrentItem()).getInt("rep_id")));
			}
			break;
		}
	}
	
	public static void open(Player p, int page) {
		if(page == 0) {
			p.closeInventory();
			return;
		}
		Inventory inv = Bukkit.createInventory(null, 6*9, "§7Reportes - Lista");
		List<String> lore = new ArrayList<>();
		String name = (page == 1) ? "§cSair" : "§cPágina Anterior";
		NBTAPI voltar = NBTAPI.getNBT(new ItemBuilder(Material.ARROW).setName(name).toItemStack());
		voltar.setInt("reps_page", page);
		inv.setItem(45, voltar.getItem());
		if (Main.cache.allReports.size() == 0) {
			lore = Arrays.asList("§7Ainda não foi realizado ", "§7nenhum report até ao momento.");
			inv.setItem(22, new ItemBuilder(Material.WEB).setName("§cNada").setLore(lore).toItemStack());
			p.openInventory(inv);
			return;
		}
		int inicio = (page - 1) * 21;
		int fim = page * 21;
		int i = 0;
		int slot = 9;
		
		if(Main.cache.allReports.size() > fim) inv.setItem(53, new ItemBuilder(Material.ARROW).setName("§aPróxima Página").toItemStack());
		
		for(Report rep : Main.cache.allReports.values()) {
			if (i < inicio) {
				i++;
				continue;
			}
			if(i >= fim) break;
			slot = nextSlot(slot);
			ItemBuilder head = new ItemBuilder(new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal())).setSkullOwner(rep.getVitima());
			int total = Main.cache.playerReports.get(rep.getVitima().toLowerCase()).size();
			String s = (total == 1) ? "report" : "reports";
			lore = Arrays.asList("", "§7Suspeito: §e" + rep.getVitima(), "§7Autor: §e" + rep.getAutor(), "",
					"§7 Este suspeito tem §6" + total + "§7 " + s + "!", "", "§6│§a Clique Esquerdo: §7Detalhes do report.  ",
					"§6│§a Clique Direito: §7Histórico do suspeito.  ", "§6│§a Clique Q: §7Teleportar até ao suspeito.  ");
			head.setName("§eReport #" + rep.getID()).setLore(lore);
			NBTAPI nbt = NBTAPI.getNBT(head.toItemStack());
			nbt.setInt("rep_id", rep.getID());
			inv.setItem(slot, nbt.getItem());
			i++;
		}
		if(inv.getItem(10) == null || inv.getItem(10).getType() == Material.AIR) {
			lore = Arrays.asList("§7Ainda não foi realizado ", "§7nenhum report até ao momento.");
			inv.setItem(22, new ItemBuilder(Material.WEB).setName("§cNada").setLore(lore).toItemStack());
		}
		p.openInventory(inv);
	}
	
	private static Integer getPage(Inventory inv) {
		return NBTAPI.getNBT(inv.getItem(45)).getInt("reps_page");
	}
	
	private static Integer nextSlot(int i) {
		switch (i) {
		case 16:
			return 19;
		case 25:
			return 28;
		case 34:
			return null;

		default:
			return i + 1;
		}
	}
}
