package me.flame.galantic.kits.giveKits.kits.healer;

import me.flame.galantic.commands.gui.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class healerKit_3 {

    public void giveHealerKit_3(UUID uuid) {
        Player p = Bukkit.getServer().getPlayer(uuid);

        ItemStack diamondAxe = new ItemStack(new ItemBuilder(Material.DIAMOND_AXE, 1).setDisplayName("&bHealer").setLore("&8- &fLevel: &e3")
                .setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());

        ItemStack helmet = new ItemStack(new ItemBuilder(Material.CHAINMAIL_HELMET, 1).setDisplayName("&bHealer").setLore("&8- &fLevel: &e3")
                .addEnchantment(1, Enchantment.PROTECTION_ENVIRONMENTAL)
                .setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
        ItemStack chestplate = new ItemStack(new ItemBuilder(Material.GOLD_CHESTPLATE, 1).setDisplayName("&bHealer").setLore("&8- &fLevel: &e3")
                .addEnchantment(1, Enchantment.PROTECTION_ENVIRONMENTAL)
                .setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
        ItemStack leggings = new ItemStack(new ItemBuilder(Material.CHAINMAIL_LEGGINGS, 1).setDisplayName("&bHealer").setLore("&8- &fLevel: &e3")
                .addEnchantment(1, Enchantment.PROTECTION_ENVIRONMENTAL)
                .setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
        ItemStack boots = new ItemStack(new ItemBuilder(Material.GOLD_BOOTS, 1).setDisplayName("&bHealer").setLore("&8- &fLevel: &e3")
                .addEnchantment(1, Enchantment.PROTECTION_ENVIRONMENTAL)
                .setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());

        ItemStack regen = new ItemStack(new ItemBuilder(Material.POTION, 1, (byte) 16385).setDisplayName("&bRegen Potion").setSplash().build());
        ItemStack weakness = new ItemStack(new ItemBuilder(Material.POTION, 1, (byte) 16424).setDisplayName("&bWeakness Potion").setSplash().build());
        ItemStack slowness = new ItemStack(new ItemBuilder(Material.POTION, 1, (byte) 16426).setDisplayName("&bSlowness Potion").setSplash().build());

        p.getInventory().setItem(0, diamondAxe);
        p.getInventory().setItem(7, weakness);
        p.getInventory().setItem(8, regen);
        p.getInventory().setItem(6, slowness);
        p.getInventory().setItem(34, weakness);
        p.getInventory().setItem(35, regen);
        p.getInventory().setHelmet(helmet);
        p.getInventory().setChestplate(chestplate);
        p.getInventory().setLeggings(leggings);
        p.getInventory().setBoots(boots);

        ItemStack soup = new ItemStack(new ItemBuilder(Material.MUSHROOM_SOUP, 1).setDisplayName("&bSoup").build());
        for (int i = 0; i < p.getInventory().getSize(); i++) {
            p.getInventory().addItem(soup);
        }
    }
}
