package me.flame.galantic.kits.giveKits;

import me.flame.galantic.kits.giveKits.kits.*;
import me.flame.galantic.kits.interfaces.IKitManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import java.util.UUID;

public class giveKitManager implements IKitManager {

    final private ArcherManager archerManager = new ArcherManager();
    final private AssassinManager assassinManager = new AssassinManager();
    final private AxeManager axeManager = new AxeManager();
    final private HealerManager healerManager = new HealerManager();
    final private HoodManager hoodManager = new HoodManager();
    final private KnightManager knightManager = new KnightManager();
    final private NinjaManager ninjaManager = new NinjaManager();
    final private RogueManager rogueManager = new RogueManager();
    final private TankManager tankManager = new TankManager();
    final private WarriorManager warriorManager = new WarriorManager();

    @Override
    public void giveKit(UUID uuid, String kit) {
        Player p = Bukkit.getServer().getPlayer(uuid);

        for(PotionEffect potionEffect : p.getActivePotionEffects()){
            p.removePotionEffect(potionEffect.getType());
        }
        p.getInventory().clear();
        switch(kit){
            case "archer":
                archerManager.giveKit(uuid, kit);
                break;
            case "assassin":
                assassinManager.giveKit(uuid, kit);
                break;
            case "axe":
                axeManager.giveKit(uuid, kit);
                break;
            case "healer":
                healerManager.giveKit(uuid, kit);
                break;
            case "hood":
                hoodManager.giveKit(uuid, kit);
                break;
            case "knight":
                knightManager.giveKit(uuid, kit);
                break;
            case "ninja":
                ninjaManager.giveKit(uuid, kit);
                break;
            case "rogue":
                rogueManager.giveKit(uuid, kit);
                break;
            case "tank":
                tankManager.giveKit(uuid, kit);
                break;
            case "warrior": default:
                warriorManager.giveKit(uuid, kit);
                break;
        }
    }
}
