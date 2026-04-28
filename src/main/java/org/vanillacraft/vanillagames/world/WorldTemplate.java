package org.vanillacraft.vanillagames.world;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class WorldTemplate {
    public static World createCopy(String templateName, String newWorldName) {
        copyFileStructure(new File("template_worlds/" + templateName), new File(Bukkit.getWorldContainer(), newWorldName));
        Random rand = new Random();
        int randNum = rand.nextInt(100_000, 999_999);
        String worldName = newWorldName;
        return new WorldCreator(newWorldName).createWorld();
    }

    // Massive thanks to https://www.spigotmc.org/threads/world-copy.37932/#post-1387948
    private static void copyFileStructure(File source, File target){
        try {
            ArrayList<String> ignore = new ArrayList<>(Arrays.asList("uid.dat", "session.lock"));
            if(!ignore.contains(source.getName())) {
                if(source.isDirectory()) {
                    if(!target.exists())
                        if (!target.mkdirs())
                            throw new IOException("Couldn't create world directory!");
                    String[] files = source.list();
                    for (String file : files) {
                        File srcFile = new File(source, file);
                        File destFile = new File(target, file);
                        copyFileStructure(srcFile, destFile);
                    }
                } else {
                    InputStream in = new FileInputStream(source);
                    OutputStream out = new FileOutputStream(target);
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = in.read(buffer)) > 0)
                        out.write(buffer, 0, length);
                    in.close();
                    out.close();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
