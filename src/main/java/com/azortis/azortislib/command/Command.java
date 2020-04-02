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
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginIdentifiableCommand;
import org.bukkit.plugin.Plugin;

import java.util.*;

public class Command {

    //Command information
    private final String name;
    private String description;
    private String usage;
    private List<String> aliases;
    private String permission;
    private Plugin plugin;

    //Execution classes
    private final ICommandExecutor executor;
    private ITabCompleter tabCompleter;
    private final org.bukkit.command.Command bukkitCommand;
    private Collection<SubCommand> subCommands;
    private Map<String, Alias> aliasesMap;

    public Command(String name, String description, String usage, List<String> aliases, String permission, Plugin plugin
            , ICommandExecutor executor, ITabCompleter tabCompleter, Collection<SubCommandBuilder> subCommands){
        this.name = name.toLowerCase();
        if(description != null)this.description = description;
        if(usage != null)this.usage = usage;
        if(aliases != null) {
            List<String> processedAliases = new ArrayList<>();
            for (String alias : aliases) {
                if(!alias.contains("-f")){
                    processedAliases.add(alias.toLowerCase());
                    break;
                    //I am going to keep this note here, for my future self to understand my current stupidity!
                    //This was a return one, and that's why I wasted a whole day finding it out!
                }
                if(this.aliasesMap == null)this.aliasesMap = new HashMap<>();
                Alias aliasFunction = new Alias(alias);
                this.aliasesMap.put(aliasFunction.getAlias(), aliasFunction);
                processedAliases.add(aliasFunction.getAlias().toLowerCase());
            }
            this.aliases = processedAliases;
        }
        if(permission != null)this.permission = permission;
        this.executor = executor;
        if(tabCompleter != null)this.tabCompleter = tabCompleter;
        if(subCommands != null) {
            this.subCommands = new ArrayList<>();
            for (SubCommandBuilder subCommand : subCommands)this.subCommands.add(subCommand.setParent(this).build());
        }
        if(plugin == null){
            this.bukkitCommand = new BukkitCommand(this.name, this);
        }else{
            this.bukkitCommand = new BukkitPluginCommand(name, this, plugin);
            this.plugin = plugin;
        }
        if(description != null) bukkitCommand.setDescription(description);
        if(usage != null) bukkitCommand.setUsage(usage);
        if(aliases != null) bukkitCommand.setAliases(aliases);
        if(permission != null) bukkitCommand.setPermission(permission);
    }

    private class BukkitCommand extends org.bukkit.command.Command {
        private Command parent;

        BukkitCommand(String name, Command parent){
            super(name);
            this.parent = parent;
        }

        public boolean execute(CommandSender commandSender, String label, String[] args) {
            return executeCommand(commandSender, label, args, parent);
        }

        @Override
        public List<String> tabComplete(CommandSender commandSender, String alias, String[] args, Location location) throws IllegalArgumentException {
            if(parent.tabCompleter != null)return parent.tabCompleter.onTabComplete(commandSender, parent, alias, args, location);
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
            return executeCommand(commandSender, label, args, parent);
        }


        @Override
        public List<String> tabComplete(CommandSender commandSender, String alias, String[] args, Location location) throws IllegalArgumentException {
            if(parent.tabCompleter != null)return parent.tabCompleter.onTabComplete(commandSender, parent, alias, args, location);
            return new ArrayList<>();
        }

        public Plugin getPlugin() {
            return this.plugin;
        }

    }

    private boolean executeCommand(CommandSender commandSender, String label, String[] args, Command parent) {
        if(args.length >= 1 && parent.subCommands != null){
            for (SubCommand subCommand : parent.subCommands){
                if(args[0].equalsIgnoreCase(subCommand.getName()) || (subCommand.hasAliases() && subCommand.getAliases().contains(args[0].toLowerCase()))){
                    List<String> argsList = new LinkedList<>(Arrays.asList(args));
                    argsList.remove(0);
                    String[] subArgs = argsList.toArray(new String[0]);
                    return subCommand.execute(commandSender, label, subArgs);
                }
            }
        }
        return parent.executor.onCommand(commandSender, parent, label, args);
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

    public String getPermission() {
        return permission;
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

    public Map<String, Alias> getAliasesMap() {
        return aliasesMap;
    }

    public Alias getAlias(String alias){
        return aliasesMap.get(alias);
    }
}
