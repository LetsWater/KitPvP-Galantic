package me.flame.galantic.kits.giveKits.kits.archer;

import me.flame.galantic.commands.gui.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class archerKit_2 {

    public void giveArcherKit_2(UUID uuid){
        Player p = Bukkit.getServer().getPlayer(uuid);

        ItemStack stoneSword = new ItemStack(new ItemBuilder(Material.STONE_SWORD, 1).setDisplayName("&bArcher").setLore("&8- &fLevel: &e2")
                .setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());

        ItemStack bow = new ItemStack(new ItemBuilder(Material.BOW, 1).setDisplayName("&bArcher").setLore("&8- &fLevel: &e2")
                .addEnchantment(1, Enchantment.ARROW_DAMAGE).setUnbreakable().setItemFlag(ItemFlag.HIDE_ENCHANTS.HIDE_UNBREAKABLE).build());

        ItemStack chainHelmet = new ItemStack(new ItemBuilder(Material.CHAINMAIL_HELMET, 1)
                .setDisplayName("&bArcher").setLore("&8- &fLevel: &e2")
                .setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
        ItemStack chainChestplate = new ItemStack(new ItemBuilder(Material.CHAINMAIL_CHESTPLATE, 1)
                .setDisplayName("&bArcher").setLore("&8- &fLevel: &e2")
                .addEnchantment(1, Enchantment.PROTECTION_ENVIRONMENTAL).setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
        ItemStack chainLeggings = new ItemStack(new ItemBuilder(Material.CHAINMAIL_LEGGINGS, 1)
                .setDisplayName("&bArcher").setLore("&8- &fLevel: &e2")
                .addEnchantment(1, Enchantment.PROTECTION_ENVIRONMENTAL).setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
        ItemStack chainBoots = new ItemStack(new ItemBuilder(Material.CHAINMAIL_BOOTS, 1)
                .setDisplayName("&bArcher").setLore("&8- &fLevel: &e2")
                .setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());

        ItemStack arrow = new ItemStack(new ItemBuilder(Material.ARROW, 64).setDisplayName("&bArrows").build());

        p.getInventory().setItem(0, stoneSword);
        p.getInventory().setItem(1, bow);
        p.getInventory().setItem(9, arrow);
        p.getInventory().setItem(10, arrow);
        p.getInventory().setHelmet(chainHelmet);
        p.getInventory().setChestplate(chainChestplate);
        p.getInventory().setLeggings(chainLeggings);
        p.getInventory().setBoots(chainBoots);

        ItemStack soup = new ItemStack(new ItemBuilder(Material.MUSHROOM_SOUP, 1).setDisplayName("&bSoup").build());
        for(int i = 0; i < p.getInventory().getSize(); i++){
            p.getInventory().addItem(soup);
        }
    }
}
