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
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;


public class Window extends InventoryView {
    private Player player;
    private InventoryAction openInventory, closeInventory;
    private Inventory playerInventory, inventory;
    private InventoryItem[] items;
    private String title;

    @Override
    public Inventory getTopInventory() {
        return inventory;
    }

    @Override
    public Inventory getBottomInventory() {
        return playerInventory;
    }

    @Override
    public HumanEntity getPlayer() {
        return player;
    }

    @Override
    public InventoryType getType() {
        return inventory.getType();
    }

    @Override
    public String getTitle() {
        return title;
    }

    public Window(Player player, Menu menu) {
        this.openInventory = menu.getOpenAction();
        this.closeInventory = menu.getCloseAction();
        this.player = player;
        this.playerInventory = player.getInventory();
        this.inventory = menu.getInventory();
        this.items = menu.getItems();
        this.title = menu.getTitle();
    }

    public boolean onClose() {
        if (closeInventory != null)
            return closeInventory.action(player);
        return false;
    }

    public boolean onOpen() {
        if (openInventory != null)
            return openInventory.action(player);
        return false;
    }


    @Override
    public void setItem(int slot, ItemStack item) {
    }

    @Override
    public InventoryItem getItem(int slot) {
        if (items[slot] == null) return new InventoryItem(Material.AIR, (player, type) -> true);
        return items[slot];
    }
}
