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
import com.azortis.azortislib.command.SubCommand;
import com.azortis.azortislib.command.executors.ISubCommandExecutor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubCommandBuilder {

    private String name;
    private List<String> aliases;
    private Command parent;
    private ISubCommandExecutor executor;

    public SubCommandBuilder setName(String name){
        this.name = name;
        return this;
    }

    public SubCommandBuilder addAlias(String alias){
        if(this.aliases == null)this.aliases = new ArrayList<>();
        aliases.add(alias);
        return this;
    }

    public SubCommandBuilder addAliases(String... aliases){
        if(this.aliases == null)this.aliases = new ArrayList<>();
        this.aliases.addAll(Arrays.asList(aliases));
        return this;
    }

    public SubCommandBuilder addAliases(List<String> aliases){
        if(this.aliases == null)this.aliases = new ArrayList<>();
        this.aliases.addAll(aliases);
        return this;
    }

    public SubCommandBuilder setParent(Command parent){
        this.parent = parent;
        return this;
    }

    public SubCommandBuilder setExecutor(ISubCommandExecutor executor){
        this.executor = executor;
        return this;
    }

    public SubCommand build(){
        return new SubCommand(name, aliases, parent, executor);
    }

}
