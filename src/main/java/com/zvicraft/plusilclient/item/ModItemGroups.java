package com.zvicraft.plusilclient.item;

import com.zvicraft.plusilclient.Plusilclient;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static ItemGroup PLUS_SMP_GROUP;

    public static void registerItemGroups() {
        try {
            if (PLUS_SMP_GROUP == null) {
                Plusilclient.LOGGER.info("Creating custom ItemGroup...");

                PLUS_SMP_GROUP = Registry.register(Registries.ITEM_GROUP,
                     Identifier.of(Plusilclient.MOD_ID, "plus_smp_group"),
                    FabricItemGroup.builder()
                            .displayName(Text.translatable("itemgroup.plussmp.plus_smp_group"))
                            .icon(() -> {
                                // Safe icon creation with fallback
                                if (ModItems.KOCHAVLET != null) {
                                    return new ItemStack(ModItems.KOCHAVLET);
                                } else {
                                    return new ItemStack(Items.DIAMOND);
                                }
                            })
                            .entries((displayContext, entries) -> {
                                try {
                                    // Add base items first (with null checks)
                                    if (ModItems.KOCHAVLET != null) entries.add(ModItems.KOCHAVLET);
                                    if (ModItems.GENERAL != null) entries.add(ModItems.GENERAL);

                                    // Add all power items safely
                                    safeAddItem(entries, ModItems.SPEED_POWER);
                                    safeAddItem(entries, ModItems.DOGS_POWER);
                                    safeAddItem(entries, ModItems.BLINDNESS_POWER);
                                    safeAddItem(entries, ModItems.CAGE_POWER);
                                    safeAddItem(entries, ModItems.EMERALD_POWER);
                                    safeAddItem(entries, ModItems.IMMUNITY_POWER);
                                    safeAddItem(entries, ModItems.CREEPER_POWER);
                                    safeAddItem(entries, ModItems.POISON_POWER);
                                    safeAddItem(entries, ModItems.TOTEM_POWER);
                                    safeAddItem(entries, ModItems.VEX_POWER);
                                    safeAddItem(entries, ModItems.DOLPHIN_POWER);
                                    safeAddItem(entries, ModItems.GRAPPLING_POWER);
                                    safeAddItem(entries, ModItems.PEARL_POWER);
                                    safeAddItem(entries, ModItems.LIFESTEAL_POWER);
                                    safeAddItem(entries, ModItems.HEART_POWER);
                                    safeAddItem(entries, ModItems.SHIELD_PIERCING_POWER);
                                    safeAddItem(entries, ModItems.SHRINK_POWER);
                                    safeAddItem(entries, ModItems.COBWEB_POWER);
                                    safeAddItem(entries, ModItems.MACE_POWER);
                                    safeAddItem(entries, ModItems.INVISIBILITY_POWER);
                                    safeAddItem(entries, ModItems.STRENGTH_POWER);
                                    safeAddItem(entries, ModItems.SLOWNESS_POWER);
                                    safeAddItem(entries, ModItems.WEAKNESS_POWER);
                                    safeAddItem(entries, ModItems.WITHER_POWER);
                                    safeAddItem(entries, ModItems.KOCHAVLET_POWER);
//                                    safeAddItem(entries, ModItems.GENERAL_POWER);

                                    Plusilclient.LOGGER.debug("Added {} items to custom creative tab", 
                                        ModItems.ITEMS.size());
                                } catch (Exception e) {
                                    Plusilclient.LOGGER.error("Error adding items to custom creative tab", e);
                                }
                            })
                            .build());

                if (PLUS_SMP_GROUP != null) {
                    Plusilclient.LOGGER.info("Successfully created custom ItemGroup: Plus SMP");
                } else {
                    Plusilclient.LOGGER.error("Failed to create custom ItemGroup");
                }
            }

            Plusilclient.LOGGER.info("Registering Mod Item Groups for " + Plusilclient.MOD_ID);
        } catch (Exception e) {
            Plusilclient.LOGGER.error("Critical error creating ItemGroup", e);
        }
    }

    private static void safeAddItem(net.minecraft.item.ItemGroup.Entries entries, net.minecraft.item.Item item) {
        if (item != null) {
            try {
                entries.add(item);
            } catch (Exception e) {
                Plusilclient.LOGGER.warn("Failed to add item to creative tab: {}", item, e);
            }
        }
    }
}
