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

import java.util.Set;

/**
 * This item can do actions based off of conditions.
 * Also does the default action as well.
 */
public class ConditionalItem extends InventoryItem {
    private Set<Condition> actions;


    public ConditionalItem(Material type, Action<InventoryClickEvent> action) {
        super(type, action);
    }

    public ConditionalItem(Material type, int amount, Action<InventoryClickEvent> action) {
        super(type, amount, action);
    }

    public ConditionalItem(ItemStack stack, Action<InventoryClickEvent> action) throws IllegalArgumentException {
        super(stack, action);
    }

    public Set<Condition> getConditions() {
        return actions;
    }

    public ConditionalItem setConditions(Set<Condition> actions) {
        this.actions = actions;
        return this;
    }

    public ConditionalItem addConditions(Condition condition) {
        actions.add(condition);
        return this;
    }

    public void testConditions(InventoryClickEvent event) {
        actions.forEach(condition -> condition.test(event));
    }

    @Override
    public void useAction(InventoryClickEvent event) {
        testConditions(event);
        super.useAction(event);
    }
}
