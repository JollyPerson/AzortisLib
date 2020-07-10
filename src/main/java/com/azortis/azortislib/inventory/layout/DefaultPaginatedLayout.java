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

package com.azortis.azortislib.inventory.layout;

import com.azortis.azortislib.inventory.IPaginatedMenu;
import com.azortis.azortislib.inventory.object.InventoryItem;
import com.azortis.azortislib.inventory.object.InventoryLayout;
import com.azortis.azortislib.inventory.object.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;

/**
 * Default layout for a paginated GUI.
 * The minimum size to use this layout is 27 slots or 3 rows in a chest type inventory.
 * This layout contains forward/back arrows.
 */
public class DefaultPaginatedLayout extends InventoryLayout {

    public DefaultPaginatedLayout(IPaginatedMenu menu) {
        int size = menu.getInventorySize();
        setSize(size);
        if (size < 27) {
            System.out.println("ERROR LAYOUT INVENTORY SIZE TOO SMALL " + DefaultPaginatedLayout.class);
            return;
        }
        InventoryItem placeholder = new ItemBuilder<>(Material.BLACK_STAINED_GLASS_PANE)
                .name("&l")
                .setAction(event -> event.setCancelled(true))
                .build();
        // Set the top row as placeholders
        for (int i = 0; i < 9; i++)
            item[i] = placeholder;
        // Set the bottom row as placeholders
        for (int i = size - 10; i < size; i++)
            item[i] = placeholder;
        InventoryItem forward = new ItemBuilder<>(Material.BLUE_STAINED_GLASS_PANE)
                .setAction(event -> {
                    event.setCancelled(true);
                    IPaginatedMenu imenu = (IPaginatedMenu) event.getClickedInventory().getHolder();
                    if (imenu.getPage(event.getWhoClicked().getUniqueId()) < imenu.getPages()) {
                        imenu.openInventory((Player) event.getWhoClicked(), imenu.getPage(event.getWhoClicked().getUniqueId()) + 1);
                    }
                }).name("&bNext Page").build();
        InventoryItem backward = new ItemBuilder<>(Material.ORANGE_STAINED_GLASS_PANE)
                .setAction(event -> {
                    event.setCancelled(true);
                    IPaginatedMenu imenu = (IPaginatedMenu) event.getClickedInventory().getHolder();
                    if (imenu.getPage(event.getWhoClicked().getUniqueId()) > 1) {
                        imenu.openInventory((Player) event.getWhoClicked(), imenu.getPage(event.getWhoClicked().getUniqueId()) - 1);
                    }
                }).name("&6Last Page").build();
        // Set the forward and backwards button respectively 2 slots from each side.
        item[size-2] = forward;
        item[size-8] = backward;
        // Set the sides as placeholders
        for(int i = 9; i < size-10; i+=9) {
            item[i+8] = placeholder;
            item[i] = placeholder;
        }
    }

    @Override
    public InventoryLayout setSize(int size) {
        return super.setSize(Math.max(size, 27));
    }

    @Override
    public InventoryItem[] getLayout() {
        return super.getLayout();
    }
}
