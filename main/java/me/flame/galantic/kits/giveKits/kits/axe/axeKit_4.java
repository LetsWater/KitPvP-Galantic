package me.flame.galantic.kits.giveKits.kits.axe;

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

public class axeKit_4 {

    public void giveAxeKit_4(UUID uuid){
        Player p = Bukkit.getServer().getPlayer(uuid);

        PotionEffect effect = new PotionEffect(PotionEffectType.JUMP, 999999, 1);
        p.addPotionEffect(effect);

        ItemStack ironAxe = new ItemStack(new ItemBuilder(Material.IRON_AXE, 1).setDisplayName("&bAxe").setLore("&8- &fLevel: &e4")
                .addEnchantment(1, Enchantment.DAMAGE_ALL)
                .setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());

        ItemStack ironHelmet = new ItemStack(new ItemBuilder(Material.IRON_HELMET, 1).setDisplayName("&bAxe").setLore("&8- &fLevel: &e4")
                .addEnchantment(1, Enchantment.PROTECTION_ENVIRONMENTAL)
                .setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
        ItemStack ironChestplate = new ItemStack(new ItemBuilder(Material.IRON_CHESTPLATE, 1).setDisplayName("&bAxe").setLore("&8- &fLevel: &e4")
                .addEnchantment(2, Enchantment.PROTECTION_ENVIRONMENTAL)
                .setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
        ItemStack ironLeggings = new ItemStack(new ItemBuilder(Material.IRON_LEGGINGS, 1).setDisplayName("&bAxe").setLore("&8- &fLevel: &e4")
                .addEnchantment(2, Enchantment.PROTECTION_ENVIRONMENTAL)
                .setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
        ItemStack ironBoots = new ItemStack(new ItemBuilder(Material.IRON_BOOTS, 1).setDisplayName("&bAxe").setLore("&8- &fLevel: &e4")
                .addEnchantment(1, Enchantment.PROTECTION_ENVIRONMENTAL)
                .setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());

        p.getInventory().setItem(0, ironAxe);
        p.getInventory().setHelmet(ironHelmet);
        p.getInventory().setChestplate(ironChestplate);
        p.getInventory().setLeggings(ironLeggings);
        p.getInventory().setBoots(ironBoots);

        ItemStack soup = new ItemStack(new ItemBuilder(Material.MUSHROOM_SOUP, 1).setDisplayName("&bSoup").build());
        for (int i = 0; i < p.getInventory().getSize(); i++) {
            p.getInventory().addItem(soup);
        }

    }
}
