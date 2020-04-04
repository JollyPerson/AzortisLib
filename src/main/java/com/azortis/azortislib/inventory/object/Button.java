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

import com.azortis.azortislib.inventory.item.action.ButtonAction;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;


public class Button extends ItemStack {
    private ButtonAction action;

    @Override
    public Button clone() {
        Button stack = (Button) super.clone();
        stack.action = action;
        return stack;
    }

    public Button(ItemStack stack, ButtonAction action) throws IllegalArgumentException {
        super(stack);
        this.action = action;
    }


    public Button(Material type, ButtonAction action) {
        super(type);
        this.action = action;
    }

    public Button(Material type, int amount, ButtonAction action) {
        super(type, amount);
        this.action = action;
    }

    public ButtonAction getAction() {
        return action;
    }
}
