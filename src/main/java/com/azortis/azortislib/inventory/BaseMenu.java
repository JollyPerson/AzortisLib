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
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;


public class BaseMenu implements Menu {
    private final Inventory inventory;

    private BaseMenu(int size, String title) {
        inventory = Bukkit.createInventory(this, size, title);
    }

    private BaseMenu(int size) {
        inventory = Bukkit.createInventory(this, size);
    }

    private BaseMenu(InventoryType type, String title) {
        inventory = Bukkit.createInventory(this, type, title);
    }

    private BaseMenu(InventoryType type) {
        inventory = Bukkit.createInventory(this, type);
    }

    @Override
    public boolean onClick(Player player, int slot, ClickType type) {
        return true;
    }

    @Override
    public void onOpen(Player player) {
    }

    @Override
    public void onClose(Player player) {
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }
}
