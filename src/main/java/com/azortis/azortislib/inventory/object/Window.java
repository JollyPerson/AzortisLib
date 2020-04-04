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

package com.azortis.azortislib.inventory.object;

import com.azortis.azortislib.inventory.Menu;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;


public class Window<T extends Menu> extends InventoryView {
    private int slot;

    @Override
    public Inventory getTopInventory() {
        return null;
    }

    @Override
    public Inventory getBottomInventory() {
        return null;
    }

    @Override
    public HumanEntity getPlayer() {
        return null;
    }

    @Override
    public InventoryType getType() {
        return null;
    }

    @Override
    public String getTitle() {
        return null;
    }

    public Window() {

    }

    @Override
    public void setItem(int slot, ItemStack item) {
        super.setItem(slot, item);
    }

    @Override
    public ItemStack getItem(int slot) {
        return super.getItem(slot);
    }
}
