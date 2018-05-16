package rpg.system;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;


import com.keenant.tabbed.Tabbed;
import com.keenant.tabbed.item.PlayerTabItem;
import com.keenant.tabbed.item.TextTabItem;
import com.keenant.tabbed.tablist.CustomTabList;
import com.keenant.tabbed.tablist.TableTabList;

import rpg.Main;


public class Tab {
	public static Tab getInstance() {
		return new Tab();
	}

	public void tab(Player p) {
		Tabbed tabbed = Main.tabbed;
		CustomTabList tab = tabbed.newTableTabList(p, 4);
		for(int y = 0; y < 20 ; y++) {
			((TableTabList) tab).set(0, y, new TextTabItem("§e ", 0));
			((TableTabList) tab).set(1, y, new TextTabItem("§e ", 0));
			((TableTabList) tab).set(2, y, new TextTabItem("§e ", 0));
			((TableTabList) tab).set(3, y, new TextTabItem("§e ", 0));
		}
	}
	public void update(Player p) {
		Tabbed tabbed = Main.tabbed;
		CustomTabList tab = (CustomTabList) tabbed.getTabList(p);
		int index = 1;
		((TableTabList) tab).set(0, 0, new TextTabItem(" §e§lFriends  ", 0));
		((TableTabList) tab).set(1, 0, new TextTabItem(" §f§lGlobal  " , 0));
		((TableTabList) tab).set(2, 0, new TextTabItem(" §a§lParty  " , 0));
		((TableTabList) tab).set(3, 0, new TextTabItem(" §e§lGuild  ", 0));
		for (Player player : Bukkit.getOnlinePlayers()) {
			if (index == 20) {
				int Total = (Bukkit.getOnlinePlayers().size() - 20);
				((TableTabList) tab).set(1, 0,
					new TextTabItem(" §f§lGlobal " + ChatColor.WHITE + "[" + Total + "] "));
				break;
			}
			((TableTabList) tab).set(1, index, new PlayerTabItem(player));
			for(int y = 1+Bukkit.getOnlinePlayers().size(); y < 20 ; y++) {
				((TableTabList) tab).set(1, y, new TextTabItem("§e§l ", 0));
			}
			index++;
		}
		tab.update();
	}

	public void simpleupdate() {
		Bukkit.getOnlinePlayers().forEach(v -> {
				update(v);
		});
	}
}
