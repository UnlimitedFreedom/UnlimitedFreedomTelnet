package me.StevenLawson.BukkitTelnet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.bukkit.Server;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class BukkitTelnet extends JavaPlugin
{
    public static BukkitTelnet plugin;
    public static Server server;

    @Override
    public void onLoad()
    {
        plugin = this;
        server = plugin.getServer();
        TelnetLogger.setPluginLogger(plugin.getLogger());
        TelnetLogger.setServerLogger(server.getLogger());
    }

    @Override
    public void onEnable()
    {
        TelnetConfig.getInstance().loadConfig();
        ((Logger) LogManager.getRootLogger()).addAppender(TelnetLogAppender.getInstance());
        TelnetServer.getInstance().startServer();

        PluginManager pm = plugin.getServer().getPluginManager();
        pm.registerEvents(new PlayerEventListener(), plugin);

        TelnetLogger.info(plugin.getName() + " v" + plugin.getDescription().getVersion() + " enabled");
        TelnetLogger.info("BukkitTelnet originally forked by Madgeek1450 and Prozza");
    }

    @Override
    public void onDisable()
    {
        TelnetServer.getInstance().stopServer();
        TelnetLogger.info(plugin.getName() + " disabled.");
    }
}
