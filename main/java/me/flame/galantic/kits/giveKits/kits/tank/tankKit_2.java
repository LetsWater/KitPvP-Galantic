package me.flame.galantic.kits.giveKits.kits.tank;

import me.flame.galantic.commands.gui.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

public class tankKit_2 {

    public void giveTankKit_2(UUID uuid) {
        Player p = Bukkit.getServer().getPlayer(uuid);

        PotionEffect effect = new PotionEffect(PotionEffectType.SLOW, 999999, 0);
        p.addPotionEffect(effect);

        ItemStack stoneSword = new ItemStack(new ItemBuilder(Material.STONE_SWORD, 1).setDisplayName("&bTank").setLore("&8- &fLevel: &e2")
                .setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());

        ItemStack ironHelmet = new ItemStack(new ItemBuilder(Material.IRON_HELMET, 1).setDisplayName("&bTank").setLore("&8- &fLevel: &e2")
                .setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
        ItemStack diamondChestplate = new ItemStack(new ItemBuilder(Material.DIAMOND_CHESTPLATE, 1).setDisplayName("&bTank").setLore("&8- &fLevel: &e2")
                .addEnchantment(1, Enchantment.PROTECTION_PROJECTILE)
                .setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
        ItemStack ironLeggings = new ItemStack(new ItemBuilder(Material.IRON_LEGGINGS, 1).setDisplayName("&bTank").setLore("&8- &fLevel: &e2")
                .addEnchantment(1, Enchantment.PROTECTION_PROJECTILE)
                .setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
        ItemStack ironBoots = new ItemStack(new ItemBuilder(Material.IRON_BOOTS, 1).setDisplayName("&bTank").setLore("&8- &fLevel: &e2")
                .setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());

        p.getInventory().setItem(0, stoneSword);
        p.getInventory().setHelmet(ironHelmet);
        p.getInventory().setChestplate(diamondChestplate);
        p.getInventory().setLeggings(ironLeggings);
        p.getInventory().setBoots(ironBoots);

        ItemStack soup = new ItemStack(new ItemBuilder(Material.MUSHROOM_SOUP, 1).setDisplayName("&bSoup").build());
        for (int i = 0; i < p.getInventory().getSize(); i++) {
            p.getInventory().addItem(soup);
        }
    }
}
