package ink.magma.safeEnderDragon;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class SafeEnderDragon extends JavaPlugin implements Listener {

    private final List<EntityType> EnderDragonCannotAttack = List.of(
            EntityType.ENDER_DRAGON,
            EntityType.ENDER_CRYSTAL,
            EntityType.ENDERMITE,
            EntityType.ENDERMAN,
            EntityType.WITHER,
            EntityType.PLAYER
    );

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onEnderDragonBreakBlock(EntityExplodeEvent event) {
//        getLogger().info("onEnderDragonBreakBlock: " + event.getEntityType());
        if (event.getEntityType().equals(EntityType.ENDER_DRAGON)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onEnderDragonDamageEntity(EntityDamageByEntityEvent event) {
//        getLogger().info("onEnderDragonDamageEntity event.getDamager().getType(): " + event.getDamager().getType());
        if (event.getDamager().getType().equals(EntityType.ENDER_DRAGON)) {
            getLogger().info("onEnderDragonDamageEntity event.getEntityType(): " + event.getEntityType());
            if (!EnderDragonCannotAttack.contains(event.getEntityType())) event.setCancelled(true);
        }
    }
}
