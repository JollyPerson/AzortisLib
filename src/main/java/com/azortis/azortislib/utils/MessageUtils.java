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

package com.azortis.azortislib.utils;

import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.chat.ComponentSerializer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MessageUtils {

    public static void sendPlayerMessage(Player receiver, Player placeholderPlayer, String message){
        message = PlaceholderAPI.setPlaceholders(placeholderPlayer, message);
        if(message.startsWith("[JSON]")){
            String jsonString = message.replaceFirst("[JSON]", "").trim();
            BaseComponent[] baseComponents = ComponentSerializer.parse(jsonString);
            receiver.spigot().sendMessage(baseComponents);
        }
        receiver.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

}
