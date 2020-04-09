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

package com.azortis.azortislib.file;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.plugin.Plugin;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Map;

@SuppressWarnings("all")
public class FileManager {

    private Map<String, File> jsonFiles;

    private Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    private Map<String, Map<String, Object>> jsonFileMaps;

    public FileManager(Plugin bukkitPlugin, Map<String, File> jsonFiles){
        if(!bukkitPlugin.getDataFolder().exists())bukkitPlugin.getDataFolder().mkdir();
        for (String fileName : jsonFiles.keySet()){
            File file = jsonFiles.get(fileName);
            try{
                if(!file.exists())copy(bukkitPlugin.getResource(fileName + ".json"), file);
                jsonFileMaps.put(fileName, gson.fromJson(new FileReader(file), Map.class));
            }catch (FileNotFoundException ex){
                ex.printStackTrace();
            }
        }
    }

    public FileManager(net.md_5.bungee.api.plugin.Plugin bungeePlugin, Map<String, File> jsonFiles){
        if(!bungeePlugin.getDataFolder().exists())bungeePlugin.getDataFolder().mkdir();
        for (String fileName : jsonFiles.keySet()){
            File file = jsonFiles.get(fileName);
            try{
                if(!file.exists())copy(bungeePlugin.getResourceAsStream(fileName + ".json"), file);
                jsonFileMaps.put(fileName, gson.fromJson(new FileReader(file), Map.class));
            }catch (FileNotFoundException ex){
                ex.printStackTrace();
            }
        }
    }

    public File getJsonFile(String fileName){
        return jsonFiles.get(fileName);
    }

    public void saveJsonFile(String fileName){
        try {
            final String json = gson.toJson(jsonFileMaps.get(fileName));
            getJsonFile(fileName).delete();
            Files.write(getJsonFile(fileName).toPath(), json.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reloadJsonFile(String fileName) {
        try {
            jsonFileMaps.remove(fileName);
            jsonFileMaps.put(fileName, gson.fromJson(new FileReader(getJsonFile(fileName)), Map.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Object> getJsonFileMap(String fileName){
        return jsonFileMaps.get(fileName);
    }

    private static void copy(InputStream in, File file) {
        try {
            OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while((len=in.read(buf))>0){
                out.write(buf,0,len);
            }
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
