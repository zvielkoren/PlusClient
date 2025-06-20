package com.zvicraft.plusilclient.item;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    // Power items - these should match server-side registrations
    public static final Item SPEED_POWER = registerItem("speed_power", new Item(new Item.Settings()));
    public static final Item DOGS_POWER = registerItem("dogs_power", new Item(new Item.Settings()));
    public static final Item BLINDNESS_POWER = registerItem("blindness_power", new Item(new Item.Settings()));
    public static final Item CAGE_POWER = registerItem("cage_power", new Item(new Item.Settings()));
    public static final Item EMERALD_POWER = registerItem("emerald_power", new Item(new Item.Settings()));
    public static final Item IMMUNITY_POWER = registerItem("immunity_power", new Item(new Item.Settings()));
    public static final Item CREEPER_POWER = registerItem("creeper_power", new Item(new Item.Settings()));
    public static final Item POISON_POWER = registerItem("poison_power", new Item(new Item.Settings()));
    public static final Item TOTEM_POWER = registerItem("totem_power", new Item(new Item.Settings()));
    public static final Item VEX_POWER = registerItem("vex_power", new Item(new Item.Settings()));
    public static final Item DOLPHIN_POWER = registerItem("dolphin_power", new Item(new Item.Settings()));
    public static final Item GRAPPLING_POWER = registerItem("grappling_power", new Item(new Item.Settings()));
    public static final Item PEARL_POWER = registerItem("pearl_power", new Item(new Item.Settings()));
    public static final Item LIFESTEAL_POWER = registerItem("lifesteal_power", new Item(new Item.Settings()));
    public static final Item HEART_POWER = registerItem("heart_power", new Item(new Item.Settings()));
    public static final Item SHIELD_PIERCING_POWER = registerItem("shield_piercing_power", new Item(new Item.Settings()));
    public static final Item SHRINK_POWER = registerItem("shrink_power", new Item(new Item.Settings()));
    public static final Item COBWEB_POWER = registerItem("cobweb_power", new Item(new Item.Settings()));
    public static final Item MACE_POWER = registerItem("mace_power", new Item(new Item.Settings()));
    public static final Item INVISIBILITY_POWER = registerItem("invisibility_power", new Item(new Item.Settings()));
    public static final Item STRENGTH_POWER = registerItem("strength_power", new Item(new Item.Settings()));
    public static final Item SLOWNESS_POWER = registerItem("slowness_power", new Item(new Item.Settings()));
    public static final Item WEAKNESS_POWER = registerItem("weakness_power", new Item(new Item.Settings()));
    public static final Item WITHER_POWER = registerItem("wither_power", new Item(new Item.Settings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM,  Identifier.of("plussmp", name), item);
    }

    public static void registerModItems() {
        // This method is called to ensure the class is loaded and static initializers run

    }
}
