    package com.zvicraft.plusilclient.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ModItems {
    public static final String MOD_ID = "plussmp";
    public static final Logger LOGGER = LoggerFactory.getLogger("PlusSMP/Items");
    public static final List<Item> ITEMS = new ArrayList<>();
    
    // Create a custom item group
    public static final RegistryKey<ItemGroup> ITEM_GROUP = RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of(MOD_ID, "main"));
    
    // Helper method to create item settings
    public static Item.Settings defaultSettings() {
        return new Item.Settings().maxCount(1);
    }

    // Power items - these should match server-side registrations
    public static final Item SPEED_POWER = registerItem("speed_power", new Item(defaultSettings()));
    public static final Item DOGS_POWER = registerItem("dogs_power", new Item(defaultSettings()));
    public static final Item BLINDNESS_POWER = registerItem("blindness_power", new Item(defaultSettings()));
    public static final Item CAGE_POWER = registerItem("cage_power", new Item(defaultSettings()));
    public static final Item EMERALD_POWER = registerItem("emerald_power", new Item(defaultSettings()));
    public static final Item IMMUNITY_POWER = registerItem("immunity_power", new Item(defaultSettings()));
    public static final Item CREEPER_POWER = registerItem("creeper_power", new Item(defaultSettings()));
    public static final Item POISON_POWER = registerItem("poison_power", new Item(defaultSettings()));
    public static final Item TOTEM_POWER = registerItem("totem_power", new Item(defaultSettings()));
    public static final Item VEX_POWER = registerItem("vex_power", new Item(defaultSettings()));
    public static final Item DOLPHIN_POWER = registerItem("dolphin_power", new Item(defaultSettings()));
    public static final Item GRAPPLING_POWER = registerItem("grappling_power", new Item(defaultSettings()));
    public static final Item PEARL_POWER = registerItem("pearl_power", new Item(defaultSettings()));
    public static final Item LIFESTEAL_POWER = registerItem("lifesteal_power", new Item(defaultSettings()));
    public static final Item HEART_POWER = registerItem("heart_power", new Item(defaultSettings()));
    public static final Item SHIELD_PIERCING_POWER = registerItem("shield_piercing_power", new Item(defaultSettings()));
    public static final Item SHRINK_POWER = registerItem("shrink_power", new Item(defaultSettings()));
    public static final Item COBWEB_POWER = registerItem("cobweb_power", new Item(defaultSettings()));
    public static final Item MACE_POWER = registerItem("mace_power", new Item(defaultSettings()));
    public static final Item INVISIBILITY_POWER = registerItem("invisibility_power", new Item(defaultSettings()));
    public static final Item STRENGTH_POWER = registerItem("strength_power", new Item(defaultSettings()));
    public static final Item SLOWNESS_POWER = registerItem("slowness_power", new Item(defaultSettings()));
    public static final Item WEAKNESS_POWER = registerItem("weakness_power", new Item(defaultSettings()));
    public static final Item WITHER_POWER = registerItem("wither_power", new Item(defaultSettings()));
    public static final Item KOCHAVLET_POWER = registerItem("kochavlet_power", new Item(defaultSettings()));
    public static final Item GENERAL_POWER = registerItem("general_power", new Item(defaultSettings()));
    public static final Item KOCHAVLET = registerItem("kochavlet", new Item(defaultSettings()));
    public static final Item GENERAL = registerItem("general", new Item(defaultSettings()));

    private static Item registerItem(String name, Item item) {
        Identifier id =  Identifier.of(MOD_ID, name);
        Item registeredItem = Registry.register(Registries.ITEM, id, item);
        ITEMS.add(registeredItem);
        LOGGER.debug("Registered item: {}", id);
        return registeredItem;
    }
    public static void registerModItems() {
        Registry.register(Registries.ITEM_GROUP, ITEM_GROUP,
                FabricItemGroup.builder()
                        .displayName(Text.translatable("itemGroup." + MOD_ID + ".main"))
                        .icon(() -> new ItemStack(SPEED_POWER))
                        .build()
        );

        ItemGroupEvents.modifyEntriesEvent(ITEM_GROUP).register(entries -> {
            for (Item item : ITEMS) {
                if (item != null) {
                    entries.add(item);
                } else {
                    LOGGER.warn("Trying to add null item to item group!");
                }
            }
        });

        LOGGER.info("Registered {} items", ITEMS.size());
    }


}
