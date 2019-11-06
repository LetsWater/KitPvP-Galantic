package me.flame.galantic.kits.giveKits.normalKits;

import me.flame.galantic.commands.gui.utils.ItemBuilder;
import me.flame.galantic.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

public class GiveKitTank {

    public static void giveKitTank(UUID uuid) {
        Player p = Bukkit.getPlayer(uuid);
        p.getInventory().clear();

        PotionEffect potionEffect = new PotionEffect(PotionEffectType.SLOW, 9999999, 0);
        p.addPotionEffect(potionEffect);

        ItemStack stoneSword = new ItemStack(new ItemBuilder(Material.STONE_SWORD, 1).setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
        ItemStack ironHelmet = new ItemStack(new ItemBuilder(Material.IRON_HELMET, 1).setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
        ItemStack diamondChestplate = new ItemStack(new ItemBuilder(Material.DIAMOND_CHESTPLATE, 1).setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
        ItemStack diamondLeggings = new ItemStack(new ItemBuilder(Material.DIAMOND_LEGGINGS, 1).setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
        ItemStack ironBoots = new ItemStack(new ItemBuilder(Material.IRON_BOOTS, 1).setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());

        p.getInventory().setItem(0, stoneSword);
        p.getInventory().setHelmet(ironHelmet);
        p.getInventory().setChestplate(diamondChestplate);
        p.getInventory().setLeggings(diamondLeggings);
        p.getInventory().setBoots(ironBoots);

        ItemStack mushroomSoup = new ItemStack(new ItemBuilder(Material.MUSHROOM_SOUP, 1).setDisplayName("&fSoup").build());
        mushroomSoup.getItemMeta().setDisplayName(ChatUtils.format("&fSoup"));
        for (int i = 0; i < p.getInventory().getSize(); i++) {
            p.getInventory().addItem(mushroomSoup);
        }
    }
}
