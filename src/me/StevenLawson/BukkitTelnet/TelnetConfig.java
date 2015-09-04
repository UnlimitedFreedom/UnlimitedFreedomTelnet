package me.StevenLawson.BukkitTelnet;

import net.pravian.bukkitlib.YamlConfig;

public class TelnetConfig
{
    private final YamlConfig config;
    private final SimpleConfigEntries configEntries;

    private TelnetConfig()
    {
        configEntries = new SimpleConfigEntries();
        config = new YamlConfig(BukkitTelnet.plugin, "config.yml", true);
    }

    public void loadConfig()
    {
        config.load();

        configEntries.setAddress(config.getString("address"));
        configEntries.setPort(config.getInt("port"));
    }

    public SimpleConfigEntries getConfigEntries()
    {
        return configEntries;
    }

    public static final class SimpleConfigEntries
    {
        private int port;
        private String address;

        public int getPort()
        {
            return port;
        }

        public void setPort(int port)
        {
            this.port = port;
        }

        public String getAddress()
        {
            return address;
        }

        public void setAddress(String address)
        {
            this.address = address;
        }
    }

    public static TelnetConfig getInstance()
    {
        return TelnetConfigHolder.INSTANCE;
    }

    private static class TelnetConfigHolder
    {
        private static final TelnetConfig INSTANCE = new TelnetConfig();
    }
}
