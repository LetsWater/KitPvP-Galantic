package me.flame.galantic.commands.gui.utils;

import me.flame.galantic.utils.ChatUtils;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemBuilder {
    ItemStack itemStack;

    public ItemBuilder(Material m, int amount) {
        itemStack = new ItemStack(m, amount);
    }

    public ItemBuilder(Material m, int amount, byte number) {
        itemStack = new ItemStack(m, amount, number);
    }

    public ItemBuilder setDisplayName(String itemName) {
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(ChatUtils.format(itemName));

        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        itemStack.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setLore(boolean rank, String... itemLore) {
        ItemMeta meta = itemStack.getItemMeta();
        List<String> lore = new ArrayList();

        for (String lores : itemLore) {
            lore.add(ChatUtils.format(lores));

        }

        if(rank == true){
            lore.add("");
            lore.add(ChatUtils.format("&cstore.galanticncetwork.eu"));
        }

        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setItemFlag(ItemFlag itemFlag) {
        ItemMeta meta = itemStack.getItemMeta();

        meta.addItemFlags(itemFlag);

        itemStack.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setSkullOwner(String skullOwner) {
        SkullMeta meta = (SkullMeta) itemStack.getItemMeta();
        meta.setOwner(skullOwner);

        itemStack.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setEnchanted() {

        itemStack.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
        return this;
    }

    public ItemBuilder addEnchantment(Integer level, Enchantment... enchantment){

        for(Enchantment enchantment1 : enchantment){
            itemStack.addUnsafeEnchantment(enchantment1, level);
        }

        return this;
    }

    public ItemBuilder setUnbreakable(){

        ItemMeta meta = itemStack.getItemMeta();
        meta.spigot().setUnbreakable(true);

        itemStack.setItemMeta(meta);
        return this;
    }

    public ItemStack build() {
        return itemStack;
    }
}