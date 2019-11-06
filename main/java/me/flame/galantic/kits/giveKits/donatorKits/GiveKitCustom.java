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

public class GiveKitCustom {

    public static void giveKitCustom(UUID uuid) {
        Player p = Bukkit.getPlayer(uuid);
        p.getInventory().clear();

        PotionEffect potionEffectSpeed = new PotionEffect(PotionEffectType.SPEED, 999999, 2);
        PotionEffect potionEffectJump = new PotionEffect(PotionEffectType.JUMP, 999999, 1);
        p.addPotionEffect(potionEffectJump);
        p.addPotionEffect(potionEffectSpeed);

        ItemStack stoneSword = new ItemStack(new ItemBuilder(Material.IRON_SWORD, 1)
                .addEnchantment(1, Enchantment.DAMAGE_ALL)
                .setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());

        ItemStack bow = new ItemStack(new ItemBuilder(Material.BOW, 1)
                .addEnchantment(1, Enchantment.ARROW_DAMAGE, Enchantment.ARROW_FIRE)
                .setUnbreakable()
                .setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());

        ItemStack slimeball = new ItemStack(new ItemBuilder(Material.SLIME_BALL, 1).addEnchantment(1, Enchantment.KNOCKBACK).setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());

        ItemStack arrow = new ItemStack(Material.ARROW, 64);
        ItemStack leatherHelmet = new ItemStack(new ItemBuilder(Material.LEATHER_HELMET, 1).addEnchantment(2, Enchantment.PROTECTION_ENVIRONMENTAL)
                .setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());

        ItemStack leatherChestplate = new ItemStack(new ItemBuilder(Material.LEATHER_CHESTPLATE, 1).addEnchantment(2, Enchantment.PROTECTION_ENVIRONMENTAL)
                .setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());

        ItemStack leatherLeggings = new ItemStack(new ItemBuilder(Material.LEATHER_LEGGINGS, 1).addEnchantment(2, Enchantment.PROTECTION_ENVIRONMENTAL)
                .setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());

        ItemStack leaterBoots = new ItemStack(new ItemBuilder(Material.LEATHER_BOOTS, 1).addEnchantment(2, Enchantment.PROTECTION_ENVIRONMENTAL)
                .setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());

        p.getInventory().setItem(0, stoneSword);
        p.getInventory().setItem(1, bow);
        p.getInventory().setItem(2, slimeball);
        p.getInventory().setItem(9, arrow);
        p.getInventory().setHelmet(leatherHelmet);
        p.getInventory().setChestplate(leatherChestplate);
        p.getInventory().setLeggings(leatherLeggings);
        p.getInventory().setBoots(leaterBoots);

        ItemStack mushroomSoup = new ItemStack(new ItemBuilder(Material.MUSHROOM_SOUP, 1).setDisplayName("&fSoup").build());
        for (int i = 0; i < p.getInventory().getSize(); i++) {
            p.getInventory().addItem(mushroomSoup);
        }
    }
}
