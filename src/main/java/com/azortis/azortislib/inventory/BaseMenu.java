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

import com.azortis.azortislib.inventory.item.InventoryItem;
import com.azortis.azortislib.inventory.item.action.InventoryAction;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;


public class BaseMenu implements Menu {
    private final Inventory inventory;
    private InventoryItem[] inventoryItems;
    private String title;

    private BaseMenu(int size, String title) {
        inventory = Bukkit.createInventory(this, size, title);
        this.title = title;
        inventoryItems = new InventoryItem[size];
    }

    private BaseMenu(int size) {
        inventory = Bukkit.createInventory(this, size);
        inventoryItems = new InventoryItem[size];
    }

    private BaseMenu(InventoryType type, String title) {
        inventory = Bukkit.createInventory(this, type, title);
        this.title = title;
        inventoryItems = new InventoryItem[inventory.getSize()];
    }

    private BaseMenu(InventoryType type) {
        inventory = Bukkit.createInventory(this, type);
        inventoryItems = new InventoryItem[inventory.getSize()];
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public InventoryAction getOpenAction() {
        return player -> false;
    }

    @Override
    public InventoryAction getCloseAction() {
        return player -> false;
    }

    @Override
    public void addItem(InventoryItem item, int slot) {
        inventoryItems[slot] = item;
    }

    @Override
    public void removeItem(int slot) {
        inventoryItems[slot] = new InventoryItem(Material.AIR, (player, type) -> true);
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public InventoryItem[] getItems() {
        return inventoryItems;
    }

}
