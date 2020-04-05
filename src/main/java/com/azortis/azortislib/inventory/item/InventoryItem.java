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

package com.azortis.azortislib.inventory.item;

import com.azortis.azortislib.inventory.item.action.ItemAction;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class InventoryItem extends ItemStack {
    private ItemAction action;

    @Override
    public InventoryItem clone() {
        InventoryItem stack = (InventoryItem) super.clone();
        stack.action = action;
        return stack;

    }
    public InventoryItem(ItemStack stack, ItemAction action) throws IllegalArgumentException {
        super(stack);
        this.action = action;
    }


    public InventoryItem(Material type, ItemAction action) {
        super(type);
        this.action = action;
    }

    public InventoryItem(Material type, int amount, ItemAction action) {
        super(type, amount);
        this.action = action;
    }

    public ItemAction getAction() {
        return action;
    }
}
