package me.flame.galantic.kits.giveKits.kits.hood;

import me.flame.galantic.commands.gui.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class hoodKit_3 {

    public void giveHoodKit_3(UUID uuid){
        Player p = Bukkit.getServer().getPlayer(uuid);

        ItemStack ironSword = new ItemStack(new ItemBuilder(Material.IRON_SWORD, 1).setDisplayName("&bHood").setLore("&8- &fLevel: &e3")
                .setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());

        ItemStack bow = new ItemStack(new ItemBuilder(Material.BOW, 1).setDisplayName("&bHood").setLore("&8- &fLevel: &e3")
                .addEnchantment(1, Enchantment.ARROW_DAMAGE).setUnbreakable().setItemFlag(ItemFlag.HIDE_ENCHANTS.HIDE_UNBREAKABLE).build());

        ItemStack leatherHelmet = new ItemStack(new ItemBuilder(Material.LEATHER_HELMET, 1).setColor(Color.LIME)
                .addEnchantment(2, Enchantment.PROTECTION_ENVIRONMENTAL)
                .setDisplayName("&bHood").setLore("&8- &fLevel: &e3").setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
        ItemStack leatherChestplate = new ItemStack(new ItemBuilder(Material.LEATHER_CHESTPLATE, 1).setColor(Color.LIME)
                .addEnchantment(2, Enchantment.PROTECTION_ENVIRONMENTAL)
                .setDisplayName("&bHood").setLore("&8- &fLevel: &e3").setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
        ItemStack leatherLeggings = new ItemStack(new ItemBuilder(Material.LEATHER_LEGGINGS, 1).setColor(Color.LIME)
                .addEnchantment(2, Enchantment.PROTECTION_ENVIRONMENTAL)
                .setDisplayName("&bHood").setLore("&8- &fLevel: &e3").setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
        ItemStack leatherBoots = new ItemStack(new ItemBuilder(Material.LEATHER_BOOTS, 1).setColor(Color.LIME)
                .addEnchantment(2, Enchantment.PROTECTION_ENVIRONMENTAL)
                .setDisplayName("&bHood").setLore("&8- &fLevel: &e3").setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());

        ItemStack arrow = new ItemStack(new ItemBuilder(Material.ARROW, 64).setDisplayName("&bArrows").build());

        p.getInventory().setItem(0, ironSword);
        p.getInventory().setItem(1, bow);
        p.getInventory().setItem(9, arrow);
        p.getInventory().setItem(10, arrow);
        p.getInventory().setHelmet(leatherHelmet);
        p.getInventory().setChestplate(leatherChestplate);
        p.getInventory().setLeggings(leatherLeggings);
        p.getInventory().setBoots(leatherBoots);

        ItemStack soup = new ItemStack(new ItemBuilder(Material.MUSHROOM_SOUP, 1).setDisplayName("&bSoup").build());
        for(int i = 0; i < p.getInventory().getSize(); i++){
            p.getInventory().addItem(soup);
        }
    }
}
