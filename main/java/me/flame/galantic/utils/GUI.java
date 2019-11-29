package me.flame.galantic.utils;

import lombok.AllArgsConstructor;
import lombok.Setter;
import me.galantic.galanticcore.LanguageCombiner.Language;
import me.galantic.galanticcore.api.BungeecordAPI;
import me.galantic.galanticcore.api.CoreAPI;
import me.galantic.galanticcore.api.inventory.ClickableItem;
import me.galantic.galanticcore.api.inventory.ItemBuilder;
import me.galantic.galanticcore.api.inventory.SmartInventory;
import me.galantic.galanticcore.api.inventory.content.InventoryContents;
import me.galantic.galanticcore.api.inventory.content.InventoryProvider;
import me.galantic.galanticcore.api.inventory.content.SlotPos;
import me.galantic.galanticcore.api.manager.IMessageManager;
import me.galantic.galanticcore.api.objects.IUser;
import me.galanticmc.hub.api.HubAPI;
import me.galanticmc.hub.api.objects.IHubUser;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@AllArgsConstructor
public enum GUI {

    SERVER_SELECTOR( null ), PLAYER_PROFILE( null ), PLAYER_PROFILE_LANGUAGE( null ), PLAYER_PROFILE_SETTINGS( null );

    @Setter
    private SmartInventory smartInventory;

    public void openInventory( Player player ) {
        if ( this.smartInventory == null )
            throw new NullPointerException( "smartinventory null." );
        this.smartInventory.open( player );
        player.playSound( player.getLocation(), Sound.CLICK, 1.0F, 1.0F );
    }

    public static void loadInventories() {
        GUI.SERVER_SELECTOR.setSmartInventory( SmartInventory.builder()
                .manager( CoreAPI.getInventoryManager() ).id( "Server Selector" ).provider(new InventoryProvider() {

                    @Override
                    public void init( Player player, InventoryContents contents ) {
                        IUser user = CoreAPI.getUserManager().getUser( player.getUniqueId() );
                        IMessageManager messageManager = CoreAPI.getMessageManager();
                        contents.set( SlotPos.of( 2, 0 ), ClickableItem.of( ItemBuilder.of( Material.BOOKSHELF )
                                .setName( messageManager.getSingleMessage( user.getLanguage(),
                                        "gui_serverselector_hub_displayname" ) )
                                .setLore( messageManager.getMessage( user.getLanguage(), "gui_serverselector_hub_lore",
                                        Bukkit.getOnlinePlayers().size() ) )
                                .toItemStack(), e -> {
                            try {
                                e.getWhoClicked()
                                        .teleport( CoreAPI.getConfig().getLocation( "spawnLocation" ) );
                            } catch ( Exception exc ) {}
                        } ) );
                        contents.set( SlotPos.of( 3, 0 ), ClickableItem.empty( ItemBuilder.of( Material.WOOL )
                                .setDyeColor( DyeColor.RED )
                                .setName( messageManager.getSingleMessage( user.getLanguage(),
                                        "gui_serverselector_event_displayname" ) )
                                .setLore( messageManager.getMessage( user.getLanguage(),
                                        "gui_serverselector_event_lore", BungeecordAPI.getServerCountNow( "event" ) ) )
                                .toItemStack() ) );
                        contents.set( SlotPos.of( 2, 4 ),
                                ClickableItem.of(
                                        ItemBuilder.of( Material.DIAMOND_CHESTPLATE )
                                                .addEnchant( Enchantment.PROTECTION_ENVIRONMENTAL, 1 )
                                                .addItemFlag( ItemFlag.HIDE_ENCHANTS )
                                                .setName( messageManager.getSingleMessage( user.getLanguage(),
                                                        "gui_serverselector_kitpvp_displayname" ) )
                                                .setLore( messageManager.getMessage( user.getLanguage(),
                                                        "gui_serverselector_kitpvp_lore",
                                                        BungeecordAPI.getServerCountNow( "kitpvp" ) ) )
                                                .toItemStack(),
                                        e -> {
                                            BungeecordAPI.sendPlayer( e.getWhoClicked().getName(), "kitpvp" );
                                        } ) );
                        contents.set( SlotPos.of( 3, 4 ),
                                ClickableItem.of(
                                        ItemBuilder.of( Material.BOW )
                                                .addEnchant( Enchantment.PROTECTION_ENVIRONMENTAL, 1 )
                                                .addItemFlag( ItemFlag.HIDE_ENCHANTS )
                                                .setName( messageManager.getSingleMessage( user.getLanguage(),
                                                        "gui_serverselector_oitc_displayname" ) )
                                                .setLore( messageManager.getMessage( user.getLanguage(),
                                                        "gui_serverselector_oitc_lore",
                                                        BungeecordAPI.getServerCountNow( "oitc" ) ) )
                                                .toItemStack(),
                                        e -> {
                                            BungeecordAPI.sendPlayer( e.getWhoClicked().getName(), "oitc" );
                                        } ) );
                        if ( player.hasPermission( "galantichub.dev" ) ) {
                            contents.set( SlotPos.of( 1, 8 ), ClickableItem.of( ItemBuilder.of( Material.SKULL_ITEM )
                                    .setDurability( ( short ) 3 )
                                    .setSkullOwnerBase64(
                                            "eyJ0aW1lc3RhbXAiOjE1NzMyMjk5OTcyMDksInByb2ZpbGVJZCI6IjRiZTg1MzA4OGMzZTQ0ZDA5MjQ5YmIwYmQxNDI4ODVhIiwicHJvZmlsZU5hbWUiOiJOb3JtYWFsQmFydCIsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS83ZjA2Y2IwMjVmNjNhMGZkMjhkMmY3NDIzZjViYzBhZGEyNDE3ZTI0OGM1YTljMzA0ZjQ5NDM2M2NlMTA5MGUifX19" )
                                    .setName( messageManager.getSingleMessage( user.getLanguage(),
                                            "gui_serverselector_dev01_displayname" ) )
                                    .setLore( messageManager.getMessage( user.getLanguage(),
                                            "gui_serverselector_dev01_lore",
                                            BungeecordAPI.getServerCountNow( "dev" ) ) )
                                    .toItemStack(), e -> {
                                BungeecordAPI.sendPlayer( e.getWhoClicked().getName(), "dev" );
                            } ) );
                            contents.set( SlotPos.of( 2, 8 ), ClickableItem.of( ItemBuilder.of( Material.SKULL_ITEM )
                                    .setDurability( ( short ) 3 )
                                    .setSkullOwnerBase64(
                                            "eyJ0aW1lc3RhbXAiOjE1NzMyMzAwOTYyMDksInByb2ZpbGVJZCI6IjNiZGRhOTExYjE4NTRlODBiZmM2ZThhZDgyZTY3MWRiIiwicHJvZmlsZU5hbWUiOiJpRXhjZXB0aW9uIiwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2NjMjBhMDc0YWM3ODc5MDgxNjlmMzlmZGNmYWUyMjBhOTAxMzgxMjU5NTdkOWNlOWRmMTZiZDM3ODU0MGQ5MTEiLCJtZXRhZGF0YSI6eyJtb2RlbCI6InNsaW0ifX19fQ" )
                                    .setName( messageManager.getSingleMessage( user.getLanguage(),
                                            "gui_serverselector_dev02_displayname" ) )
                                    .setLore( messageManager.getMessage( user.getLanguage(),
                                            "gui_serverselector_dev02_lore",
                                            BungeecordAPI.getServerCountNow( "dev2" ) ) )
                                    .toItemStack(), e -> {
                                BungeecordAPI.sendPlayer( e.getWhoClicked().getName(), "dev2" );
                            } ) );
                            contents.set( SlotPos.of( 3, 8 ), ClickableItem.of( ItemBuilder.of( Material.SKULL_ITEM )
                                    .setDurability( ( short ) 3 )
                                    .setSkullOwnerBase64(
                                            "eyJ0aW1lc3RhbXAiOjE1NzMyMzAxMjU4MzYsInByb2ZpbGVJZCI6ImJhYTBlZmExZTZkZDQxMzg5MjhjMjFiZTY3NGE4NTRkIiwicHJvZmlsZU5hbWUiOiJzam9tcCIsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS85MzU5MmEyZTMxMjA5MGYxMTEwMmZmNzIzYTMyNmFjMDc3Yjk3MWJlMGUzMDNjOWQ2YjgxYzc0MzhlYjUxMTE1In19fQ" )
                                    .setName( messageManager.getSingleMessage( user.getLanguage(),
                                            "gui_serverselector_dev03_displayname" ) )
                                    .setLore( messageManager.getMessage( user.getLanguage(),
                                            "gui_serverselector_dev03_lore",
                                            BungeecordAPI.getServerCountNow( "dev3" ) ) )
                                    .toItemStack(), e -> {
                                BungeecordAPI.sendPlayer( e.getWhoClicked().getName(), "dev3" );
                            } ) );
                        }
                        if ( player.hasPermission( "galantichub.bouwserver" ) )
                            contents.set( SlotPos.of( 5, 8 ),
                                    ClickableItem.of( ItemBuilder.of( Material.WORKBENCH )
                                            .setName( messageManager.getSingleMessage( user.getLanguage(),
                                                    "gui_serverselector_build_displayname" ) )
                                            .setLore( messageManager.getMessage( user.getLanguage(),
                                                    "gui_serverselector_build_lore",
                                                    BungeecordAPI.getServerCountNow( "build" ) ) )
                                            .toItemStack(), e -> {
                                        BungeecordAPI.sendPlayer( e.getWhoClicked().getName(), "build" );
                                    } ) );
                    }

                    @Override
                    public void update( Player player, InventoryContents contents ) {
                        int ticks = contents.property( "ticks", 0 );
                        contents.setProperty( "ticks", ticks + 1 );

                        if ( ticks % 20 != 0 )
                            return;

                        IUser user = CoreAPI.getUserManager().getUser( player.getUniqueId() );
                        contents.get( SlotPos.of( 2, 0 ) ).ifPresent( consumer -> {
                            updateLore( consumer.getItem(), "gui_serverselector_hub_lore", user.getLanguage(),
                                    Bukkit.getOnlinePlayers().size());
                        } );
                        contents.get( SlotPos.of( 3, 0 ) ).ifPresent( consumer -> {
                            updateLore( consumer.getItem(), "gui_serverselector_event_lore", user.getLanguage(),
                                    BungeecordAPI.getServerCountNow( "event" ));
                        } );
                        contents.get( SlotPos.of( 2, 4 ) ).ifPresent( consumer -> {
                            updateLore( consumer.getItem(), "gui_serverselector_kitpvp_lore", user.getLanguage(),
                                    BungeecordAPI.getServerCountNow( "kitpvp" ));
                        } );
                        contents.get( SlotPos.of( 3, 4 ) ).ifPresent( consumer -> {
                            updateLore( consumer.getItem(), "gui_serverselector_oitc_lore", user.getLanguage(),
                                    BungeecordAPI.getServerCountNow( "oitc" ));
                        } );
                        contents.get( SlotPos.of( 1, 8 ) ).ifPresent( consumer -> {
                            updateLore( consumer.getItem(), "gui_serverselector_dev01_lore", user.getLanguage(),
                                    BungeecordAPI.getServerCountNow( "dev" ));
                        } );
                        contents.get( SlotPos.of( 2, 8 ) ).ifPresent( consumer -> {
                            updateLore( consumer.getItem(), "gui_serverselector_dev02_lore", user.getLanguage(),
                                    BungeecordAPI.getServerCountNow( "dev2" ));
                        } );
                        contents.get( SlotPos.of( 3, 8 ) ).ifPresent( consumer -> {
                            updateLore( consumer.getItem(), "gui_serverselector_dev03_lore", user.getLanguage(),
                                    BungeecordAPI.getServerCountNow( "dev3" ));
                        } );
                        contents.get( SlotPos.of( 5, 8 ) ).ifPresent( consumer -> {
                            updateLore( consumer.getItem(), "gui_serverselector_build_lore", user.getLanguage(),
                                    BungeecordAPI.getServerCountNow( "build" ));
                        } );
                        contents.update();
                        player.updateInventory();
                    }

                    private ItemStack updateLore(ItemStack itemStack, String key, Language language,
                                                 Object... objects ) {
                        ItemMeta itemMeta = itemStack.getItemMeta();
                        itemMeta.setLore( CoreAPI.getMessageManager().getMessage( language, key, objects ) );
                        itemStack.setItemMeta( itemMeta );
                        return itemStack;
                    }
                } ).size( 6, 9 ).title( "gui_serverselector_title" ).build() );
        GUI.PLAYER_PROFILE.setSmartInventory( SmartInventory.builder().manager( CoreAPI.getInventoryManager() )
                .id( "Player profile" ).provider( new InventoryProvider() {

                    @Override
                    public void init( Player player, InventoryContents contents ) {
                        IUser user = CoreAPI.getUserManager().getUser( player.getUniqueId() );
                        IMessageManager messageManager = CoreAPI.getMessageManager();
                        contents.set( SlotPos.of( 3, 4 ), ClickableItem.of( ItemBuilder.of( Material.SKULL_ITEM )
                                .setDurability( ( short ) 3 )
                                .setSkullOwnerBase64(
                                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDM4Y2YzZjhlNTRhZmMzYjNmOTFkMjBhNDlmMzI0ZGNhMTQ4NjAwN2ZlNTQ1Mzk5MDU1NTI0YzE3OTQxZjRkYyJ9fX0=" )
                                .setName( messageManager.getSingleMessage( user.getLanguage(),
                                        "gui_player_profile_language_displayname" ) )
                                .setLore( messageManager.getMessage( user.getLanguage(),
                                        "gui_player_profile_language_lore" ) )
                                .toItemStack(), e -> {
                            GUI.PLAYER_PROFILE_LANGUAGE.openInventory( ( Player ) e.getWhoClicked() );
                        } ) );
                        contents.set( SlotPos.of( 2, 4 ),
                                ClickableItem.of( ItemBuilder.of( Material.BOOK_AND_QUILL )
                                        .setName( messageManager.getSingleMessage( user.getLanguage(),
                                                "gui_player_profile_settings_displayname" ) )
                                        .setLore( messageManager.getMessage( user.getLanguage(),
                                                "gui_player_profile_settings_lore" ) )
                                        .toItemStack(), e -> {
                                    GUI.PLAYER_PROFILE_SETTINGS
                                            .openInventory( ( Player ) e.getWhoClicked() );
                                } ) );
                        contents.set( SlotPos.of( 5, 2 ), ClickableItem.of( ItemBuilder.of( Material.SKULL_ITEM )
                                .setDurability( ( short ) 3 )
                                .setSkullOwnerBase64(
                                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTY3OWQ2MzBmODUxYzU4OTdkYTgzYTY0MjUxNzQzM2Y2NWRjZmIzMmIxYmFiYjFmZWMzMmRhNzEyNmE5ZjYifX19=" )
                                .setName( messageManager.getSingleMessage( user.getLanguage(),
                                        "gui_player_profile_youtube_displayname" ) )
                                .setLore( messageManager.getMessage( user.getLanguage(),
                                        "gui_player_profile_youtube_lore" ) )
                                .toItemStack(), e -> {
                            CoreAPI.getMessageManager().sendMessage( e.getWhoClicked(), "youtube" );
                            e.getWhoClicked().closeInventory();
                        } ) );
                        contents.set( SlotPos.of( 5, 3 ), ClickableItem.of( ItemBuilder.of( Material.SKULL_ITEM )
                                .setDurability( ( short ) 3 )
                                .setSkullOwnerBase64(
                                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGQ0MjMzN2JlMGJkY2EyMTI4MDk3ZjFjNWJiMTEwOWU1YzYzM2MxNzkyNmFmNWZiNmZjMjAwMDAwMTFhZWI1MyJ9fX0==" )
                                .setName( messageManager.getSingleMessage( user.getLanguage(),
                                        "gui_player_profile_discord_displayname" ) )
                                .setLore( messageManager.getMessage( user.getLanguage(),
                                        "gui_player_profile_discord_lore" ) )
                                .toItemStack(), e -> {
                            CoreAPI.getMessageManager().sendMessage( e.getWhoClicked(), "discord" );
                            e.getWhoClicked().closeInventory();
                        } ) );
                        contents.set( SlotPos.of( 5, 4 ), ClickableItem.of( ItemBuilder.of( Material.SKULL_ITEM )
                                .setDurability( ( short ) 3 )
                                .setSkullOwnerBase64(
                                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzI4NDczOGQ0MDE4ZDk0MjhkNGRjODM4ZTIwNjY4MWUzZWUyMzY1NDUzNWI4ZmY0YzI5MzI2MmRmMzI3NGExMSJ9fX0===" )
                                .setName( messageManager.getSingleMessage( user.getLanguage(),
                                        "gui_player_profile_website_displayname" ) )
                                .setLore( messageManager.getMessage( user.getLanguage(),
                                        "gui_player_profile_website_lore" ) )
                                .toItemStack(), e -> {
                            CoreAPI.getMessageManager().sendMessage( e.getWhoClicked(), "website" );
                            e.getWhoClicked().closeInventory();
                        } ) );
                        contents.set( SlotPos.of( 5, 5 ), ClickableItem.of( ItemBuilder.of( Material.SKULL_ITEM )
                                .setDurability( ( short ) 3 )
                                .setSkullOwnerBase64(
                                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGNiNzYxNjZkMWUxZTQ0OTQ1N2I1YzQ0MzZiM2Y0OGI3ZDc2OGFjNjBmMTllMmM2YjI1ZWE0MmM0YmFkN2MifX19=" )
                                .setName( messageManager.getSingleMessage( user.getLanguage(),
                                        "gui_player_profile_twitter_displayname" ) )
                                .setLore( messageManager.getMessage( user.getLanguage(),
                                        "gui_player_profile_twitter_lore" ) )
                                .toItemStack(), e -> {
                            CoreAPI.getMessageManager().sendMessage( e.getWhoClicked(), "twitter" );
                            e.getWhoClicked().closeInventory();
                        } ) );
                        contents.set( SlotPos.of( 5, 6 ), ClickableItem.of( ItemBuilder.of( Material.SKULL_ITEM )
                                .setDurability( ( short ) 3 )
                                .setSkullOwnerBase64(
                                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTM2ZTk0ZjZjMzRhMzU0NjVmY2U0YTkwZjJlMjU5NzYzODllYjk3MDlhMTIyNzM1NzRmZjcwZmQ0ZGFhNjg1MiJ9fX0==" )
                                .setName( messageManager.getSingleMessage( user.getLanguage(),
                                        "gui_player_profile_store_displayname" ) )
                                .setLore( messageManager.getMessage( user.getLanguage(),
                                        "gui_player_profile_store_lore" ) )
                                .toItemStack(), e -> {
                            CoreAPI.getMessageManager().sendMessage( e.getWhoClicked(), "store" );
                            e.getWhoClicked().closeInventory();
                        } ) );
                    }

                    @Override
                    public void update( Player player, InventoryContents contents ) {

                    }
                } ).size( 6, 9 ).title( "Player profile" ).build() );
        GUI.PLAYER_PROFILE_LANGUAGE
                .setSmartInventory( SmartInventory.builder().manager( CoreAPI.getInventoryManager() )
                        .id( "Player profile language" ).provider( new InventoryProvider() {

                            @Override
                            public void init( Player player, InventoryContents contents ) {
                                IUser user = CoreAPI.getUserManager().getUser( player.getUniqueId() );
                                IMessageManager messageManager = CoreAPI.getMessageManager();
                                contents.set( SlotPos.of( 5, 0 ), ClickableItem.of( ItemBuilder.of( Material.ARROW )
                                        .setName( messageManager.getSingleMessage( user.getLanguage(),
                                                "gui_arrow_back_displayname" ) )
                                        .setLore(
                                                messageManager.getMessage( user.getLanguage(), "gui_arrow_back_lore" ) )
                                        .toItemStack(), e -> {
                                    GUI.PLAYER_PROFILE.openInventory( ( Player ) e.getWhoClicked() );
                                } ) );
                                contents.set( SlotPos.of( 2, 4 ), ClickableItem.of( ItemBuilder
                                        .of( Material.SKULL_ITEM ).setDurability( ( short ) 3 )
                                        .setSkullOwnerBase64(
                                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTE3MDFmMjE4MzVhODk4YjIwNzU5ZmIzMGE1ODNhMzhiOTk0YWJmNjBkMzkxMmFiNGNlOWYyMzExZTc0ZjcyIn19fQ==" )
                                        .setName( messageManager.getSingleMessage( user.getLanguage(),
                                                "gui_player_profile_language_english_displayname" ) )
                                        .setLore( messageManager.getMessage( user.getLanguage(),
                                                "gui_player_profile_language_english_lore" ) )
                                        .toItemStack(), e -> {
                                    user.setLanguage( Language.ENGLISH, true );
                                    init( player, contents );
                                    contents.update();
                                    player.updateInventory();
                                } ) );
                                contents.set( SlotPos.of( 3, 4 ), ClickableItem.of( ItemBuilder
                                        .of( Material.SKULL_ITEM ).setDurability( ( short ) 3 )
                                        .setSkullOwnerBase64(
                                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzIzY2YyMTBlZGVhMzk2ZjJmNWRmYmNlZDY5ODQ4NDM0ZjkzNDA0ZWVmZWFiZjU0YjIzYzA3M2IwOTBhZGYifX19" )
                                        .setName( messageManager.getSingleMessage( user.getLanguage(),
                                                "gui_player_profile_language_netherlands_displayname" ) )
                                        .setLore( messageManager.getMessage( user.getLanguage(),
                                                "gui_player_profile_language_netherlands_lore" ) )
                                        .toItemStack(), e -> {
                                    user.setLanguage( Language.DUTCH, true );
                                    init( player, contents );
                                    contents.update();
                                    player.updateInventory();

                                } ) );
                            }

                            @Override
                            public void update( Player player, InventoryContents contents ) {}

                        } ).size( 6, 9 ).title( "gui_player_profile_title" ).build() );
        GUI.PLAYER_PROFILE_SETTINGS
                .setSmartInventory( SmartInventory.builder().manager( CoreAPI.getInventoryManager() )
                        .id( "Player profile Settings" ).provider( new InventoryProvider() {

                            @Override
                            public void init( Player player, InventoryContents contents ) {
                                IUser user = CoreAPI.getUserManager().getUser( player.getUniqueId() );
                                IHubUser hubUser = HubAPI.getUserManager().getUser(player.getName());
                                IMessageManager messageManager = CoreAPI.getMessageManager();
                                contents.set( SlotPos.of( 5, 0 ), ClickableItem.of( ItemBuilder.of( Material.ARROW )
                                        .setName( messageManager.getSingleMessage( user.getLanguage(),
                                                "gui_arrow_back_displayname" ) )
                                        .setLore(
                                                messageManager.getMessage( user.getLanguage(), "gui_arrow_back_lore" ) )
                                        .toItemStack(), e -> {
                                    GUI.PLAYER_PROFILE.openInventory( ( Player ) e.getWhoClicked() );
                                } ) );
                                // CLOCK DAYTIME
                                contents.set( SlotPos.of( 2, 2 ), ClickableItem.of( ItemBuilder.of( Material.WATCH )
                                        .setName( messageManager.getSingleMessage( user.getLanguage(),
                                                "gui_settings_clock_" + hubUser.hasDayLight() + "_displayname" ) )
                                        .setLore( messageManager.getMessage( user.getLanguage(),
                                                "gui_settings_clock_" + hubUser.hasDayLight() + "_lore" ) )
                                        .toItemStack(), e -> {
                                    hubUser.setDayLight( !hubUser.hasDayLight(), true );
                                    init( player, contents );
                                    contents.update();
                                } ) );
                                contents.set( SlotPos.of( 3, 2 ), ClickableItem.of( ItemBuilder.of( Material.INK_SACK )
                                        .setDurability( hubUser.hasDayLight() ? ( short ) 10 : 8 )
                                        .setName( messageManager.getSingleMessage( user.getLanguage(),
                                                "gui_settings_clock_dye_" + hubUser.hasDayLight() + "_displayname" ) )
                                        .setLore( messageManager.getMessage( user.getLanguage(),
                                                "gui_settings_clock_dye_" + hubUser.hasDayLight() + "_lore" ) )
                                        .toItemStack(), e -> {
                                    hubUser.setDayLight( !hubUser.hasDayLight(), true );
                                    init( player, contents );
                                    contents.update();
                                } ) );
                                // CHAT ENABLED / DISABLED
                                contents.set( SlotPos.of( 2, 3 ), ClickableItem.of( ItemBuilder
                                        .of( Material.BOOK_AND_QUILL )
                                        .setName( messageManager.getSingleMessage( user.getLanguage(),
                                                "gui_settings_chat_" + hubUser.hasChatEnabled() + "_displayname" ) )
                                        .setLore( messageManager.getMessage( user.getLanguage(),
                                                "gui_settings_chat_" + hubUser.hasChatEnabled() + "_lore" ) )
                                        .toItemStack(), e -> {
                                    hubUser.setChatEnabled( !hubUser.hasChatEnabled(), true );
                                    init( player, contents );
                                    contents.update();
                                } ) );
                                contents.set( SlotPos.of( 3, 3 ), ClickableItem.of( ItemBuilder.of( Material.INK_SACK )
                                        .setDurability( hubUser.hasChatEnabled() ? ( short ) 10 : 8 )
                                        .setName( messageManager.getSingleMessage( user.getLanguage(),
                                                "gui_settings_chat_dye_" + hubUser.hasChatEnabled() + "_displayname" ) )
                                        .setLore( messageManager.getMessage( user.getLanguage(),
                                                "gui_settings_chat_dye_" + hubUser.hasChatEnabled() + "_lore" ) )
                                        .toItemStack(), e -> {
                                    hubUser.setChatEnabled( !hubUser.hasChatEnabled(), true );
                                    init( player, contents );
                                    contents.update();
                                } ) );
                                // PM ENABLED / DISABLED
                                contents.set( SlotPos.of( 2, 4 ), ClickableItem.of( ItemBuilder.of( Material.PAPER )
                                        .setName( messageManager.getSingleMessage( user.getLanguage(),
                                                "gui_settings_pm_" + hubUser.hasPrivateMessageEnabled()
                                                        + "_displayname" ) )
                                        .setLore( messageManager.getMessage( user.getLanguage(),
                                                "gui_settings_pm_" + hubUser.hasPrivateMessageEnabled() + "_lore" ) )
                                        .toItemStack(), e -> {
                                    hubUser.setPrivateMessageEnabled( !hubUser.hasPrivateMessageEnabled(),
                                            true );
                                    init( player, contents );
                                    contents.update();
                                } ) );
                                contents.set( SlotPos.of( 3, 4 ),
                                        ClickableItem.of( ItemBuilder.of( Material.INK_SACK )
                                                .setDurability( hubUser.hasPrivateMessageEnabled() ? ( short ) 10 : 8 )
                                                .setName( messageManager.getSingleMessage( user.getLanguage(),
                                                        "gui_settings_pm_dye_" + hubUser.hasPrivateMessageEnabled()
                                                                + "_displayname" ) )
                                                .setLore( messageManager.getMessage( user.getLanguage(),
                                                        "gui_settings_pm_dye_" + hubUser.hasPrivateMessageEnabled()
                                                                + "_lore" ) )
                                                .toItemStack(), e -> {
                                            hubUser.setPrivateMessageEnabled(
                                                    !hubUser.hasPrivateMessageEnabled(), true );
                                            init( player, contents );
                                            contents.update();
                                        } ) );
                                // FLY ENABLED / DISABLED
                                contents.set( SlotPos.of( 2, 5 ),
                                        ClickableItem.of( ItemBuilder.of( Material.ENDER_PEARL )
                                                .setName( messageManager.getSingleMessage( user.getLanguage(),
                                                        player.hasPermission( "galanticcore.fly" )
                                                                ? ( "gui_settings_fly_" + hubUser.hasFlyDefaultEnabled()
                                                                + "_displayname" )
                                                                : "gui_settings_fly_no_perm_displayname" ) )
                                                .setLore( messageManager.getMessage( user.getLanguage(),
                                                        player.hasPermission( "galanticcore.fly" )
                                                                ? ( "gui_settings_fly_" + hubUser.hasFlyDefaultEnabled()
                                                                + "_lore" )
                                                                : "gui_settings_fly_no_perm_lore" ) )
                                                .toItemStack(), e -> {
                                            if ( !player.hasPermission( "galanticcore.fly" ) )
                                                return;
                                            hubUser.setFlyDefaultEnabled( !hubUser.hasFlyDefaultEnabled(),
                                                    true );
                                            init( player, contents );
                                            contents.update();
                                        } ) );
                                contents.set( SlotPos.of( 3, 5 ), ClickableItem.of(
                                        ItemBuilder.of( Material.INK_SACK )
                                                .setDurability( hubUser.hasFlyDefaultEnabled()
                                                        ? ( short ) 10
                                                        : 8 )
                                                .setName( messageManager.getSingleMessage( user.getLanguage(),
                                                        player.hasPermission( "galanticcore.fly" )
                                                                ? ( "gui_settings_fly_dye_"
                                                                + hubUser.hasFlyDefaultEnabled()
                                                                + "_displayname" )
                                                                : "gui_settings_fly_dye_no_perm_displayname" ) )
                                                .setLore( messageManager.getMessage( user.getLanguage(),
                                                        player.hasPermission( "galanticcore.fly" )
                                                                ? ( "gui_settings_fly_dye_"
                                                                + hubUser.hasFlyDefaultEnabled() + "_lore" )
                                                                : "gui_settings_fly_dye_no_perm_lore" ) )
                                                .toItemStack(),
                                        e -> {
                                            if ( !player.hasPermission( "galanticcore.fly" ) )
                                                return;
                                            hubUser.setFlyDefaultEnabled( !hubUser.hasFlyDefaultEnabled(), true );
                                            init( player, contents );
                                            contents.update();
                                        } ) );
                                // Friends menu
                                contents.set( SlotPos.of( 2, 6 ),
                                        ClickableItem.of(
                                                ItemBuilder.of( Material.SKULL_ITEM ).setDurability( ( short ) 3 )
                                                        .setName( messageManager.getSingleMessage( user.getLanguage(),
                                                                "gui_settings_friends_displayname" ) )
                                                        .setLore( messageManager.getMessage( user.getLanguage(),
                                                                "gui_settings_friends_lore" ) )
                                                        .toItemStack(),
                                                e -> {} ) );
                                contents.set( SlotPos.of( 3, 6 ),
                                        ClickableItem.of(
                                                ItemBuilder.of( Material.INK_SACK ).setDurability( ( short ) 12 )
                                                        .setName( messageManager.getSingleMessage( user.getLanguage(),
                                                                "gui_settings_friends_dye_displayname" ) )
                                                        .setLore( messageManager.getMessage( user.getLanguage(),
                                                                "gui_settings_friends_dye_lore" ) )
                                                        .toItemStack(),
                                                e -> {} ) );
                            }

                            @Override
                            public void update( Player player, InventoryContents contents ) {}
                        } ).size( 6, 9 ).title( "gui_settings_title" ).build() );
    }
}
