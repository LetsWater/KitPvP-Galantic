package me.flame.galantic.kits.giveKits.normalKits;

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

public class GiveKitNinja {

    public static void giveKitNinja(UUID uuid) {
        Player p = Bukkit.getPlayer(uuid);
        p.getInventory().clear();
        PotionEffect potionEffect = new PotionEffect(PotionEffectType.SPEED, 999999, 0);
        p.addPotionEffect(potionEffect);

        ItemStack stoneSword = new ItemStack(new ItemBuilder(Material.STONE_SWORD, 1)
                .addEnchantment(1, Enchantment.DAMAGE_ALL)
                .setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());

        ItemStack chainHelmet = new ItemStack(new ItemBuilder(Material.CHAINMAIL_HELMET, 1).setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
        ItemStack chainChestplate = new ItemStack(new ItemBuilder(Material.CHAINMAIL_CHESTPLATE, 1).setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
        ItemStack chainLeggings = new ItemStack(new ItemBuilder(Material.CHAINMAIL_LEGGINGS, 1).setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
        ItemStack chainBoots = new ItemStack(new ItemBuilder(Material.CHAINMAIL_BOOTS, 1).setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());

        p.getInventory().setItem(0, stoneSword);
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
