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

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryItem extends ItemStack {
    private Action<InventoryClickEvent> action;

    public InventoryItem(Material type, Action<InventoryClickEvent> action) {
        super(type);
        this.action = action;
    }

    public InventoryItem(Material type, int amount, Action<InventoryClickEvent> action) {
        super(type, amount);
        this.action = action;
    }

    public InventoryItem(ItemStack stack, Action<InventoryClickEvent> action) throws IllegalArgumentException {
        super(stack);
        this.action = action;
    }


    public void useAction(InventoryClickEvent event) {
        action.action(event);
    }


    public void setAction(Action<InventoryClickEvent> action) {
        this.action = action;
    }

    public void setItemStack(ItemStack stack) {
        setType(stack.getType());
        setAmount(stack.getAmount());
        if (stack.hasItemMeta()) {
            setItemMeta(stack.getItemMeta());
        }
    }
}
