package me.flame.galantic.kits;

import me.flame.galantic.kits.giveKits.donatorKits.*;
import me.flame.galantic.kits.giveKits.normalKits.*;
import me.flame.galantic.kits.interfaces.IKitManager;
import me.flame.galantic.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class KitManager implements IKitManager {

    private static KitManager instance = new KitManager();

    @Override
    public void giveKit(UUID uuid, String kit) {
        Player p = Bukkit.getPlayer(uuid);
        if (p.getInventory().getHelmet() == null) {
            p.sendMessage(ChatUtils.format("&fJe hebt zojuist kit &9" + kit + "&f gekregen! &aSucces!"));
            switch (kit) {
                case "warrior":
                    GiveKitWarrior.giveKitWarrior(uuid);
                    break;
                case "archer":
                    GiveKitArcher.giveKitArcher(uuid);
                    break;
                case "tank":
                    GiveKitTank.giveKitTank(uuid);
                    break;
                case "axe":
                    GiveKitAxe.giveKitWarrior(uuid);
                    break;
                case "ninja":
                    GiveKitNinja.giveKitNinja(uuid);
                    break;
                case "vip":
                    GiveKitVip.giveKitArcher(uuid);
                    break;
                case "elite":
                    GiveKitElite.giveKitElite(uuid);
                    break;
                case "hero":
                    GiveKitHero.giveKitHero(uuid);
                    break;
                case "god":
                    GiveKitGod.giveKitGod(uuid);
                    break;
                case "custom":
                    GiveKitCustom.giveKitCustom(uuid);
                    break;
                default:
                    GiveKitWarrior.giveKitWarrior(uuid);
                    break;

            }
        }
    }

    @Override
    public void viewKit(UUID uuid, String kit) {

    }

    public static KitManager getInstance() {
        return instance;
    }
}
