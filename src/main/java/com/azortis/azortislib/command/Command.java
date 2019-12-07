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

package com.azortis.azortislib.command;

import com.azortis.azortislib.command.builders.SubCommandBuilder;
import com.azortis.azortislib.command.executors.ICommandExecutor;
import com.azortis.azortislib.command.executors.ITabCompleter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginIdentifiableCommand;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Command {

    //Command information
    private String name;
    private String description;
    private String usage;
    private List<String> aliases;
    private Plugin plugin;

    //Execution classes
    private ICommandExecutor executor;
    private ITabCompleter tabCompleter;
    private org.bukkit.command.Command bukkitCommand;
    private Collection<SubCommand> subCommands;
    private Collection<AliasFunction> aliasFunctions;

    public Command(String name, String description, String usage, List<String> aliases, Plugin plugin
            , ICommandExecutor executor, ITabCompleter tabCompleter, Collection<SubCommandBuilder> subCommands){
        this.name = name.toLowerCase();
        if(description != null)this.description = description;
        if(usage != null)this.usage = usage;
        if(aliases != null) {
            List<String> processedAliases = new ArrayList<String>();
            for (String alias : aliases) {
                if(!alias.contains("-f")){
                    processedAliases.add(alias.toLowerCase());
                    return;
                }
                if(this.aliasFunctions == null)this.aliasFunctions = new ArrayList<>();
                AliasFunction aliasFunction = new AliasFunction(alias);
                this.aliasFunctions.add(aliasFunction);
                processedAliases.add(aliasFunction.getAlias().toLowerCase());
            }
            this.aliases = processedAliases;
        }
        this.executor = executor;
        if(tabCompleter != null)this.tabCompleter = tabCompleter;
        if(subCommands != null) {
            this.subCommands = new ArrayList<>();
            for (SubCommandBuilder subCommand : subCommands)this.subCommands.add(subCommand.setParent(this).build());
        }
        if(plugin == null){
            Bukkit.getLogger().info("Using BukkitCommand");
            this.bukkitCommand = new BukkitCommand(this.name, this);
        }else{
            Bukkit.getLogger().info("Using BukkitPluginCommand");
            this.bukkitCommand = new BukkitPluginCommand(name, this, plugin);
            this.plugin = plugin;
        }
        if(description != null) bukkitCommand.setDescription(description);
        if(usage != null) bukkitCommand.setUsage(usage);
        if(aliases != null) bukkitCommand.setAliases(aliases);
    }

    private class BukkitCommand extends org.bukkit.command.Command {
        private Command parent;

        BukkitCommand(String name, Command parent){
            super(name);
            this.parent = parent;
        }

        public boolean execute(CommandSender commandSender, String label, String[] args) {
            if(args.length >= 1 && parent.subCommands != null){
                for (SubCommand subCommand : parent.subCommands){
                    if(args[0].equals(subCommand.getName()) || subCommand.getAliases().contains(args[0])){
                        List<String> argsList = Arrays.asList(args);
                        argsList.remove(args[0]);
                        String[] subArgs = argsList.toArray(new String[0]);
                        return subCommand.execute(commandSender, label, subArgs);
                    }
                }
            }
            return parent.executor.onCommand(commandSender, parent, label, args);
        }


        @Override
        public List<String> tabComplete(CommandSender commandSender, String alias, String[] args, Location location) throws IllegalArgumentException {
            if(parent.tabCompleter != null)return parent.tabCompleter.tabComplete(commandSender, alias, args, location);
            return new ArrayList<>();
        }

    }

    private class BukkitPluginCommand extends org.bukkit.command.Command implements PluginIdentifiableCommand{
        private Command parent;
        private Plugin plugin;

        public BukkitPluginCommand(String name, Command parent, Plugin plugin){
            super(name);
            this.parent = parent;
            this.plugin = plugin;
        }

        public boolean execute(CommandSender commandSender, String label, String[] args) {
            if(args.length >= 1 && parent.subCommands != null){
                for (SubCommand subCommand : parent.subCommands){
                    if(args[0].equals(subCommand.getName()) || subCommand.getAliases().contains(args[0])){
                        List<String> argsList = Arrays.asList(args);
                        argsList.remove(args[0]);
                        String[] subArgs = argsList.toArray(new String[0]);
                        return subCommand.execute(commandSender, label, subArgs);
                    }
                }
            }
            return parent.executor.onCommand(commandSender, parent, label, args);
        }


        @Override
        public List<String> tabComplete(CommandSender commandSender, String alias, String[] args, Location location) throws IllegalArgumentException {
            if(parent.tabCompleter != null)return parent.tabCompleter.tabComplete(commandSender, alias, args, location);
            return new ArrayList<>();
        }

        public Plugin getPlugin() {
            return this.plugin;
        }

    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUsage() {
        return usage;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public ICommandExecutor getExecutor() {
        return executor;
    }

    public ITabCompleter getTabCompleter() {
        return tabCompleter;
    }

    public org.bukkit.command.Command getBukkitCommand() {
        return bukkitCommand;
    }

    public Collection<SubCommand> getSubCommands() {
        return subCommands;
    }

    public Collection<AliasFunction> getAliasFunctions() {
        return aliasFunctions;
    }
}
