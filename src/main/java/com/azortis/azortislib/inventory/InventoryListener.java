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
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class InventoryListener implements Listener {
    private static InventoryListener instance;

    private InventoryListener(JavaPlugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(ignoreCancelled = true)
    private void onClick(InventoryClickEvent event) {
        if(event.getView() instanceof Window)
            event.setCancelled(((Window) event.getView()).getItem(event.getSlot()).getAction().action((Player) event.getWhoClicked(), event.getClick()));
    }

    @EventHandler
    private void onOpen(InventoryOpenEvent event) {
        if(event.getView() instanceof Window)
            ((Window) event.getView()).onOpen();
    }

    @EventHandler
    private void onClose(InventoryCloseEvent event) {
        if(event.getView() instanceof Window)
            ((Window) event.getView()).onClose();
    }

    public static void initialize(JavaPlugin plugin) {
        if (instance == null) instance = new InventoryListener(plugin);
    }
}
