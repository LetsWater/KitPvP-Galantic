package me.flame.galantic.kits.giveKits.kits.knight;

import me.flame.galantic.commands.gui.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class knightKit_2 {

    public void giveKnightKit_2(UUID uuid) {
        Player p = Bukkit.getServer().getPlayer(uuid);

        ItemStack sword = new ItemStack(new ItemBuilder(Material.IRON_SWORD, 1).setDisplayName("&bKnight").setLore("&8- &fLevel: &e2")
                .setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());

        ItemStack helmet = new ItemStack(new ItemBuilder(Material.IRON_HELMET, 1).setDisplayName("&bKnight").setLore("&8- &fLevel: &e2")
                .addEnchantment(1, Enchantment.PROTECTION_ENVIRONMENTAL)
                .setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
        ItemStack chestplate = new ItemStack(new ItemBuilder(Material.IRON_CHESTPLATE, 1).setDisplayName("&bKnight").setLore("&8- &fLevel: &e2")
                .setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
        ItemStack leggings = new ItemStack(new ItemBuilder(Material.IRON_LEGGINGS, 1).setDisplayName("&bKnight").setLore("&8- &fLevel: &e2")
                .addEnchantment(1, Enchantment.PROTECTION_ENVIRONMENTAL)
                .setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
        ItemStack boots = new ItemStack(new ItemBuilder(Material.IRON_BOOTS, 1).setDisplayName("&bKnight").setLore("&8- &fLevel: &e2")
                .setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());

        p.getInventory().setItem(0, sword);
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
