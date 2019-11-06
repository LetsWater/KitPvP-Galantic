package me.flame.galantic.kits.giveKits.donatorKits;

import me.flame.galantic.commands.gui.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

public class GiveKitGod {

    public static void giveKitGod(UUID uuid) {
        Player p = Bukkit.getPlayer(uuid);
        p.getInventory().clear();

        PotionEffect potionEffect = new PotionEffect(PotionEffectType.SPEED, 999999, 1);
        p.addPotionEffect(potionEffect);

        ItemStack dSword = new ItemStack(new ItemBuilder(Material.DIAMOND_SWORD, 1).setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
        ItemStack fishingRod = new ItemStack(new ItemBuilder(Material.FISHING_ROD, 1).setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());

        ItemStack ironHelmet = new ItemStack(new ItemBuilder(Material.IRON_HELMET, 1).addEnchantment(1, Enchantment.PROTECTION_ENVIRONMENTAL)
                .setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
        ItemStack chainChestplate = new ItemStack(new ItemBuilder(Material.CHAINMAIL_CHESTPLATE, 1).addEnchantment(2, Enchantment.PROTECTION_ENVIRONMENTAL)
                .setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
        ItemStack chainLeggings = new ItemStack(new ItemBuilder(Material.CHAINMAIL_LEGGINGS, 1).addEnchantment(2, Enchantment.PROTECTION_ENVIRONMENTAL)
                .setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
        ItemStack ironBoots = new ItemStack(new ItemBuilder(Material.IRON_BOOTS, 1).addEnchantment(1, Enchantment.PROTECTION_ENVIRONMENTAL)
                .setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());

        p.getInventory().setItem(0, dSword);
        p.getInventory().setItem(1, fishingRod);
        p.getInventory().setHelmet(ironHelmet);
        p.getInventory().setChestplate(chainChestplate);
        p.getInventory().setLeggings(chainLeggings);
        p.getInventory().setBoots(ironBoots);

        ItemStack mushroomSoup = new ItemStack(new ItemBuilder(Material.MUSHROOM_SOUP, 1).setDisplayName("&fSoup").build());
        for (int i = 0; i < p.getInventory().getSize(); i++) {
            p.getInventory().addItem(mushroomSoup);
        }
    }
}
