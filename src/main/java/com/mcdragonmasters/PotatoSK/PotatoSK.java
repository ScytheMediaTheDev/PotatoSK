package com.mcdragonmasters.PotatoSK;

import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

//import com.mcdragonmasters.PotatoSK.utils.PackageLoader;
import com.olyno.skriptmigrate.SkriptMigrate;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;

public class PotatoSK extends JavaPlugin {

    public static PotatoSK instance;
    public static FileConfiguration config;
	SkriptAddon addon;

	public void onEnable() {

        instance = this;
        addon = Skript.registerAddon(this);
        try {
            addon.loadClasses("com.mcdragonmasters.PotatoSK.skript");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Register Metrics
//        Metrics metrics = new Metrics(this);
//        metrics.addCustomChart(new Metrics.DrilldownPie("java_version", () -> {
//            Map<String, Map<String, Integer>> map = new HashMap<>();
//            String javaVersion = System.getProperty("java.version");
//            Map<String, Integer> entry = new HashMap<>();
//            entry.put(javaVersion, 1);
//            if (javaVersion.startsWith("1.7")) {
//                map.put("Java 1.7", entry);
//            } else if (javaVersion.startsWith("1.8")) {
//                map.put("Java 1.8", entry);
//            } else if (javaVersion.startsWith("1.9")) {
//                map.put("Java 1.9", entry);
//            } else {
//                map.put("Other", entry);
//            }
//            return map;
//        }));

        // Register events
        //PluginManager pm = getServer().getPluginManager();
        //pm.registerEvents(new JoinLeave(), this);
//        new PackageLoader<Listener>("com.mcdragonmasters.PotatoSK.skript.events.bukkit", "register bukkit events").getList()
//                .thenAccept(events -> {
//                    for (Listener evt : events) {
//                        getServer().getPluginManager().registerEvents(evt, this);
//                    }
//                });

        // Setup migrations
        if (classExist()) {
			SkriptMigrate.load(this);
		}

        if (!getDataFolder().exists()) {
			saveDefaultConfig();
		}

		config = getConfig();

    }

    private boolean classExist() {
		try {
			Class.forName("com.olyno.skriptmigrate.SkriptMigrate");
			return true;
		} catch (ClassNotFoundException e) {
			return false;
		}
	}

}
