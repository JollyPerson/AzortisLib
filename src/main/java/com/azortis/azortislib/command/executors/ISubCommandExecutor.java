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

package com.azortis.azortislib.command.executors;

import com.azortis.azortislib.command.SubCommand;
import org.bukkit.command.CommandSender;

public interface ISubCommandExecutor {

    /**
     * Called when a subCommand is executed.
     *
     * @param commandSender The commandSender
     * @param subCommand The subCommand being executed.
     * @param label The label used for the subCommand.
     * @param args The arguments for the subCommand.
     * @return Successful
     */
    boolean onSubCommand(CommandSender commandSender, SubCommand subCommand, String label, String[] args);

}
