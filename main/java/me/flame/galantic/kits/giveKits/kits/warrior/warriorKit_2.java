package me.flame.galantic.kits.giveKits.kits.warrior;

import me.flame.galantic.commands.gui.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class warriorKit_2 {

    public void giveWarriorKit_2(UUID uuid){
        Player p = Bukkit.getServer().getPlayer(uuid);

        ItemStack diamondSword = new ItemStack(new ItemBuilder(Material.DIAMOND_SWORD, 1)
                .setDisplayName("&bWarrior").setLore("&8- &fLevel: &e2").setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());

        ItemStack chainHelmet = new ItemStack(new ItemBuilder(Material.CHAINMAIL_HELMET, 1)
                .setDisplayName("&bWarrior").setLore("&8- &fLevel: &e2")
                .setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
        ItemStack chainChestplate = new ItemStack(new ItemBuilder(Material.CHAINMAIL_CHESTPLATE, 1)
                .setDisplayName("&bWarrior").setLore("&8- &fLevel: &e2")
                .setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
        ItemStack chainLeggings = new ItemStack(new ItemBuilder(Material.CHAINMAIL_LEGGINGS, 1)
                .setDisplayName("&bWarrior").setLore("&8- &fLevel: &e2")
                .addEnchantment(1, Enchantment.PROTECTION_ENVIRONMENTAL).setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
        ItemStack chainBoots = new ItemStack(new ItemBuilder(Material.CHAINMAIL_BOOTS, 1)
                .setDisplayName("&bWarrior").setLore("&8- &fLevel: &e2")
                .addEnchantment(1, Enchantment.PROTECTION_ENVIRONMENTAL).setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());

        p.getInventory().setItem(0, diamondSword);
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
