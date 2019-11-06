package me.flame.galantic.kits.giveKits.donatorKits;

import me.flame.galantic.commands.gui.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class GiveKitVip {

    public static void giveKitArcher(UUID uuid) {
        Player p = Bukkit.getPlayer(uuid);
        p.getInventory().clear();

        ItemStack ironSword = new ItemStack(new ItemBuilder(Material.IRON_SWORD, 1).setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());

        ItemStack bow = new ItemStack(new ItemBuilder(Material.BOW, 1)
                .addEnchantment(1, Enchantment.ARROW_DAMAGE, Enchantment.ARROW_FIRE)
                .setUnbreakable()
                .setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());

        ItemStack arrow = new ItemStack(Material.ARROW, 64);
        ItemStack chainHelmet = new ItemStack(new ItemBuilder(Material.CHAINMAIL_HELMET, 1).setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
        ItemStack chainChestplate = new ItemStack(new ItemBuilder(Material.CHAINMAIL_CHESTPLATE, 1).setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
        ItemStack chainLeggings = new ItemStack(new ItemBuilder(Material.CHAINMAIL_LEGGINGS, 1).setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
        ItemStack chainBoots = new ItemStack(new ItemBuilder(Material.CHAINMAIL_BOOTS, 1).setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());

        p.getInventory().setItem(0, ironSword);
        p.getInventory().setItem(1, bow);
        p.getInventory().setItem(9, arrow);
        p.getInventory().setHelmet(chainHelmet);
        p.getInventory().setChestplate(chainChestplate);
        p.getInventory().setLeggings(chainLeggings);
        p.getInventory().setBoots(chainBoots);

        ItemStack mushroomSoup = new ItemStack(new ItemBuilder(Material.MUSHROOM_SOUP, 1).setDisplayName("&fSoup").build());
        for (int i = 0; i < p.getInventory().getSize(); i++) {
            p.getInventory().addItem(mushroomSoup);
        }
    }

}
