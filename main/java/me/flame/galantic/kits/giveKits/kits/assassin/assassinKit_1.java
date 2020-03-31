package me.flame.galantic.kits.giveKits.kits.assassin;

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

public class assassinKit_1 {

    public void giveAssassinKit_1(UUID uuid) {
        Player p = Bukkit.getServer().getPlayer(uuid);

        PotionEffect potionEffect = new PotionEffect(PotionEffectType.SPEED, 999999, 0);
        p.addPotionEffect(potionEffect);

        ItemStack sword = new ItemStack(new ItemBuilder(Material.IRON_SWORD, 1).setDisplayName("&bAssassin").setLore("&8- &fLevel: &e1")
                .setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());

        ItemStack helmet = new ItemStack(new ItemBuilder(Material.LEATHER_HELMET, 1).setDisplayName("&bAssassin").setLore("&8- &fLevel: &e1")
                .addEnchantment(2, Enchantment.PROTECTION_ENVIRONMENTAL)
                .setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
        ItemStack chestplate = new ItemStack(new ItemBuilder(Material.LEATHER_CHESTPLATE, 1).setDisplayName("&bAssassin").setLore("&8- &fLevel: &e1")
                .addEnchantment(1, Enchantment.PROTECTION_ENVIRONMENTAL)
                .setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
        ItemStack leggings = new ItemStack(new ItemBuilder(Material.LEATHER_LEGGINGS, 1).setDisplayName("&bAssassin").setLore("&8- &fLevel: &e1")
                .addEnchantment(1, Enchantment.PROTECTION_ENVIRONMENTAL)
                .setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
        ItemStack boots = new ItemStack(new ItemBuilder(Material.LEATHER_BOOTS, 1).setDisplayName("&bAssassin").setLore("&8- &fLevel: &e1")
                .addEnchantment(2, Enchantment.PROTECTION_ENVIRONMENTAL)
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
