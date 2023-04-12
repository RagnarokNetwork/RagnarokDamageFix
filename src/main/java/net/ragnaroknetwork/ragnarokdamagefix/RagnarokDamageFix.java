package net.ragnaroknetwork.ragnarokdamagefix;

import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class RagnarokDamageFix extends JavaPlugin implements Listener {

    public void onEnable() {
        this.saveDefaultConfig();
        this.getCommand("ragnarokdamagefix").setExecutor(new Command());
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    public void onDisable() {
        this.getLogger().info("[RagnarokBowBoosting] Disabling plugin.");
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();

        if (!(damager instanceof Player)) return;

        Player player = (Player) damager;

        if (!player.getItemInHand().containsEnchantment(Enchantment.DAMAGE_ALL)) return;

        int sharpness = player.getItemInHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL);
        double damage = event.getDamage();
        double value = getConfig().getDouble("value");

        double newDamage = damage + sharpness * value;

        event.setDamage(newDamage);
    }
}

