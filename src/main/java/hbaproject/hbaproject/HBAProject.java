package hbaproject.hbaproject;

import org.bukkit.plugin.java.JavaPlugin;

public final class HBAProject extends JavaPlugin {
    private static HBAProject instace;

    public static HBAProject getInstace() {
        return instace;
    }

    @Override
    public void onEnable() {
        instace = this;


    }

    @Override
    public void onDisable() {

    }
}
