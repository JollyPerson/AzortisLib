/*
 * An open source utilities library used for Azortis plugins.
 *     Copyright (C) 2019  Azortis
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.azortis.azortislib.inventory;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.plugin.Plugin;

public class InventoryListener implements Listener {
    private static InventoryListener listener;

    private InventoryListener(Plugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public static InventoryListener initialize(Plugin plugin) {
        if (listener == null) listener = new InventoryListener(plugin);
        return listener;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getClickedInventory() != null && event.getClickedInventory().getHolder() != null
                && event.getClickedInventory().getHolder() instanceof IMenu && event.getCurrentItem() != null
                && event.getCurrentItem().getType() != Material.AIR) {
            IMenu menu = (IMenu) event.getClickedInventory().getHolder();
            menu.getItem(event.getSlot()).useAction(event);

        }

    }

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {
        if (event.getInventory().getHolder() != null && event.getInventory().getHolder() instanceof IMenu) {
            IMenu menu = (IMenu) event.getInventory().getHolder();
            menu.getOpenCondition().forEach(condition -> condition.test(event));
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (event.getInventory().getHolder() != null && event.getInventory().getHolder() instanceof IMenu) {
            IMenu menu = (IMenu) event.getInventory().getHolder();
            menu.getCloseCondition().forEach(condition -> condition.test(event));
            if(menu instanceof IPaginatedMenu) {
                ((IPaginatedMenu) menu).onInventoryClose((Player) event.getPlayer());
            }
        }
    }

}
