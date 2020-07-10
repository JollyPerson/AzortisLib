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

import com.azortis.azortislib.inventory.object.InventoryItem;
import com.azortis.azortislib.inventory.object.InventoryLayout;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

public interface IPaginatedMenu extends IMenu {
    /**
     * Gets an item from a specific page and slot
     *
     * @param slot the slot on the page
     * @param page the page which to get the item from
     * @return The item
     */
    InventoryItem getItem(int slot, int page);

    /**
     * Returns an inventory from a specified page
     *
     * @param page the page of the inventory
     * @return Inventory on page
     */
    Inventory getInventory(int page);

    /**
     * Sets an item on a specified page and slot to it.
     *
     * @param slot the slot on the page
     * @param page the page on which the slot is
     * @param item the item to set the slot ot
     */
    void setItem(int slot, int page, InventoryItem item);

    /**
     * Applies a layout to the page.
     * Anything below 0 applies to all pages.
     *
     * @param layout The layout of the inventory
     * @param page   The page to apply the layout to.
     */
    void setLayout(InventoryLayout layout, int page);

    /**
     * Gets the item from a slot from the first page.
     *
     * @param slot the slot at which the item resides at
     * @return the item at the slot
     */
    @Override
    default InventoryItem getItem(int slot) {
        return getItem(slot, 1);
    }

    /**
     * Sets the slot to the item on the first page.
     *
     * @param slot slot that the item will reside in
     * @param item the item to set the slot to
     */
    @Override
    default void setItem(int slot, InventoryItem item) {
        setItem(slot, 1, item);
    }

    /**
     * Sets the first pages layout
     *
     * @param layout the layout to set the inventory to
     */
    @Override
    default void setLayout(InventoryLayout layout) {
        setLayout(layout, 1);
    }

    /**
     * Gets the inventory from the first page.
     *
     * @return Inventory at the first page
     */
    @Override
    default Inventory getInventory() {
        return getInventory(1);
    }

    /**
     * Sets the number of pages that the inventory is allowed to have.
     *
     * @param sum the number of pages to set.
     */
    void setPages(int sum);

    /**
     * The amount of pages that currently exist.
     *
     * @return how many pages currently exist
     */
    int getPages();

    /**
     * Will add items to the first available slot.
     * Does not expand the GUI size.
     *
     * @param item the item to add
     */
    @Override
    void addItem(InventoryItem item);

    /**
     * Adds an item to the GUI in the first available slot.
     * This will expand the GUI when no slots are available.
     * If a new page is added it will use the layout specified.
     *
     * @param item the item to add
     * @param defaultLayout the layout to use if a page is added.
     * @return the int of the page number if expanded; returns -1 if no new page was created.
     */
    int addItemExpand(InventoryItem item, InventoryLayout defaultLayout);

    /**
     * Gets the current page the viewer is on.
     *
     * @return the current page the viewer is on
     */
    int getPage(UUID uuid);

    /**
     * Opens the inventory of the player in the proper way.
     *
     * @param player the player to open the inventory for
     */
    void openInventory(Player player, int page);

    /**
     * Called after a player closes the inventory to correctly close it.
     *
     * @param player the player that closed the inventory
     */
    void onInventoryClose(Player player);

}
