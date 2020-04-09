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

package com.azortis.azortislib.command.builders;

import com.azortis.azortislib.command.Command;
import com.azortis.azortislib.command.executors.ICommandExecutor;
import com.azortis.azortislib.command.executors.ITabCompleter;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CommandBuilder {

    //Command information
    private String name;
    private String description;
    private String usage;
    private List<String> aliases;
    private String permission;
    private Plugin plugin;

    //Execution classes
    private ICommandExecutor executor;
    private ITabCompleter tabCompleter;
    private Collection<SubCommandBuilder> subCommands;

    public CommandBuilder setName(@NotNull String name) {
        this.name = name;
        return this;
    }

    public CommandBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public CommandBuilder setUsage(String usage) {
        this.usage = usage;
        return this;
    }

    public CommandBuilder addAlias(String alias){
        if(alias == null)return this;
        if(this.aliases == null)this.aliases = new ArrayList<>();
        aliases.add(alias);
        return this;
    }

    public CommandBuilder addAliases(String... aliases){
        if(aliases == null)return this;
        if(this.aliases == null)this.aliases = new ArrayList<>();
        this.aliases.addAll(Arrays.asList(aliases));
        return this;
    }

    public CommandBuilder addAliases(List<String> aliases){
        if(aliases == null)return this;
        if(this.aliases == null)this.aliases = new ArrayList<>();
        this.aliases.addAll(aliases);
        return this;
    }

    public CommandBuilder setPermission(String permission) {
        this.permission = permission;
        return this;
    }

    public CommandBuilder setPlugin(Plugin plugin){
        this.plugin = plugin;
        return this;
    }

    public CommandBuilder setExecutor(@NotNull ICommandExecutor executor){
        this.executor = executor;
        return this;
    }

    public CommandBuilder setTabCompleter(@NotNull ITabCompleter tabCompleter){
        this.tabCompleter = tabCompleter;
        return this;
    }

    public CommandBuilder addSubCommand(SubCommandBuilder subCommand){
        if(this.subCommands == null)this.subCommands = new ArrayList<>();
        this.subCommands.add(subCommand);
        return this;
    }

    public CommandBuilder addSubCommands(SubCommandBuilder... subCommands){
        if(this.subCommands == null)this.subCommands = new ArrayList<>();
        this.subCommands.addAll(Arrays.asList(subCommands));
        return this;
    }

    public CommandBuilder addSubCommands(Collection<SubCommandBuilder> subCommands){
        if(this.subCommands == null)this.subCommands = new ArrayList<>();
        this.subCommands.addAll(subCommands);
        return this;
    }

    public Command build(){
        return new Command(this.name, this.description, this.usage, this.aliases, this.permission, this.plugin, this.executor
                , this.tabCompleter, this.subCommands);
    }

}
