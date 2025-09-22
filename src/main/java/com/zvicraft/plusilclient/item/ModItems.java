package com.zvicraft.plusilclient.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
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

    // Helper method to create item settings
    public static Item.Settings defaultSettings() {
        return new Item.Settings().maxCount(1);
    }

    // Item Declarations
    public static Item SPEED_POWER;
    public static Item DOGS_POWER;
    public static Item BLINDNESS_POWER;
    public static Item CAGE_POWER;
    public static Item EMERALD_POWER;
    public static Item IMMUNITY_POWER;
    public static Item CREEPER_POWER;
    public static Item POISON_POWER;
    public static Item TOTEM_POWER;
    public static Item VEX_POWER;
    public static Item DOLPHIN_POWER;
    public static Item GRAPPLING_POWER;
    public static Item PEARL_POWER;
    public static Item LIFESTEAL_POWER;
    public static Item HEART_POWER;
    public static Item SHIELD_PIERCING_POWER;
    public static Item SHRINK_POWER;
    public static Item COBWEB_POWER;
    public static Item MACE_POWER;
    public static Item INVISIBILITY_POWER;
    public static Item STRENGTH_POWER;
    public static Item SLOWNESS_POWER;
    public static Item WEAKNESS_POWER;
    public static Item WITHER_POWER;
    public static Item KOCHAVLET_POWER;
//    public static Item GENERAL_POWER;
    public static Item KOCHAVLET;
    public static Item GENERAL;

    private static Item registerItem(String name, Item item) {
        try {
            Identifier id = Identifier.of(MOD_ID, name);
            Item registeredItem = Registry.register(Registries.ITEM, id, item);
            if (registeredItem != null) {
                ITEMS.add(registeredItem);
                LOGGER.debug("Successfully registered item: {}", id);
            } else {
                LOGGER.error("Failed to register item: {} - returned null", id);
            }
            return registeredItem;
        } catch (Exception e) {
            LOGGER.error("Exception while registering item: {}", name, e);
            return null;
        }
    }

    public static void registerModItems() {
        LOGGER.info("Starting item registration...");

        try {
            // Register all items
            SPEED_POWER = registerItem("speed_power", new Item(defaultSettings()));
            DOGS_POWER = registerItem("dogs_power", new Item(defaultSettings()));
            BLINDNESS_POWER = registerItem("blindness_power", new Item(defaultSettings()));
            CAGE_POWER = registerItem("cage_power", new Item(defaultSettings()));
            EMERALD_POWER = registerItem("emerald_power", new Item(defaultSettings()));
            IMMUNITY_POWER = registerItem("immunity_power", new Item(defaultSettings()));
            CREEPER_POWER = registerItem("creeper_power", new Item(defaultSettings()));
            POISON_POWER = registerItem("poison_power", new Item(defaultSettings()));
            TOTEM_POWER = registerItem("totem_power", new Item(defaultSettings()));
            VEX_POWER = registerItem("vex_power", new Item(defaultSettings()));
            DOLPHIN_POWER = registerItem("dolphin_power", new Item(defaultSettings()));
            GRAPPLING_POWER = registerItem("grappling_power", new Item(defaultSettings()));
            PEARL_POWER = registerItem("pearl_power", new Item(defaultSettings()));
            LIFESTEAL_POWER = registerItem("lifesteal_power", new Item(defaultSettings()));
            HEART_POWER = registerItem("heart_power", new Item(defaultSettings()));
            SHIELD_PIERCING_POWER = registerItem("shield_piercing_power", new Item(defaultSettings()));
            SHRINK_POWER = registerItem("shrink_power", new Item(defaultSettings()));
            COBWEB_POWER = registerItem("cobweb_power", new Item(defaultSettings()));
            MACE_POWER = registerItem("mace_power", new Item(defaultSettings()));
            INVISIBILITY_POWER = registerItem("invisibility_power", new Item(defaultSettings()));
            STRENGTH_POWER = registerItem("strength_power", new Item(defaultSettings()));
            SLOWNESS_POWER = registerItem("slowness_power", new Item(defaultSettings()));
            WEAKNESS_POWER = registerItem("weakness_power", new Item(defaultSettings()));
            WITHER_POWER = registerItem("wither_power", new Item(defaultSettings()));
            KOCHAVLET_POWER = registerItem("kochavlet_power", new Item(defaultSettings()));
//            GENERAL_POWER = registerItem("general_power", new Item(defaultSettings()));
            KOCHAVLET = registerItem("kochavlet", new Item(defaultSettings()));
            GENERAL = registerItem("general_power", new Item(defaultSettings()));

            // Remove any null items from the list
            int originalSize = ITEMS.size();
            ITEMS.removeIf(item -> {
                boolean isNull = item == null;
                if (isNull) {
                    LOGGER.warn("Removed null item from list");
                }
                return isNull;
            });

            if (ITEMS.size() < originalSize) {
                LOGGER.warn("Removed {} null items from registry", originalSize - ITEMS.size());
            }

            LOGGER.info("Successfully registered {} items", ITEMS.size());
            LOGGER.info("Items will be available in the 'Plus SMP' creative tab");
            LOGGER.info("Items can also be obtained using /give commands:");
            LOGGER.info("Example: /give @p plussmp:speed_power");

            // Log all registered items for debugging
            LOGGER.debug("Registered items: {}", ITEMS.stream()
                .filter(item -> item != null)
                .map(item -> Registries.ITEM.getId(item).toString())
                .toList());

        } catch (Exception e) {
            LOGGER.error("Critical error during item registration", e);
            // Don't throw - let the mod continue
        }
    }

    // Utility method to get item by name (useful for commands/recipes)
    public static Item getItemByName(String name) {
        try {
            Identifier id = Identifier.of(MOD_ID, name);
            return Registries.ITEM.get(id);
        } catch (Exception e) {
            LOGGER.error("Failed to get item by name: {}", name, e);
            return null;
        }
    }

    // Method to handle creative tab registration (vanilla tab integration disabled)
    public static void registerCreativeTabs() {
        LOGGER.info("Vanilla creative tab integration is disabled to prevent crashes");
        LOGGER.info("Items are only available in the custom 'Plus SMP' creative tab");
        LOGGER.info("Items can also be obtained using /give commands (e.g., /give @p plussmp:speed_power)");
    }

}