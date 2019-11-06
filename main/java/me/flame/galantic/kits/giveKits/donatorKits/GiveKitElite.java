package me.flame.galantic.kits.giveKits.donatorKits;

import me.flame.galantic.commands.gui.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class GiveKitElite {

    public static void giveKitElite(UUID uuid) {
        Player p = Bukkit.getPlayer(uuid);
        p.getInventory().clear();

        ItemStack dSword = new ItemStack(new ItemBuilder(Material.DIAMOND_SWORD, 1).setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
        ItemStack fishingRod = new ItemStack(new ItemBuilder(Material.FISHING_ROD, 1).setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());

        ItemStack ironHelmet = new ItemStack(new ItemBuilder(Material.IRON_HELMET, 1).addEnchantment(1, Enchantment.PROTECTION_ENVIRONMENTAL)
                .setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
        ItemStack ironChestplate = new ItemStack(new ItemBuilder(Material.IRON_CHESTPLATE, 1).addEnchantment(1, Enchantment.PROTECTION_ENVIRONMENTAL)
                .setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
        ItemStack ironLeggings = new ItemStack(new ItemBuilder(Material.IRON_LEGGINGS, 1).addEnchantment(1, Enchantment.PROTECTION_ENVIRONMENTAL)
                .setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
        ItemStack ironBoots = new ItemStack(new ItemBuilder(Material.IRON_BOOTS, 1).addEnchantment(1, Enchantment.PROTECTION_ENVIRONMENTAL)
                .setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());

        p.getInventory().setItem(0, dSword);
        p.getInventory().setItem(1, fishingRod);
        p.getInventory().setHelmet(ironHelmet);
        p.getInventory().setChestplate(ironChestplate);
        p.getInventory().setLeggings(ironLeggings);
        p.getInventory().setBoots(ironBoots);

        ItemStack mushroomSoup = new ItemStack(new ItemBuilder(Material.MUSHROOM_SOUP, 1).setDisplayName("&fSoup").build());
        for (int i = 0; i < p.getInventory().getSize(); i++) {
            p.getInventory().addItem(mushroomSoup);
        }
    }

}
