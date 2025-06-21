package com.zvicraft.plusilclient;

import com.zvicraft.plusilclient.item.ModItems;
import com.zvicraft.plusilclient.keybinding.KeyBindingHandler;
import com.zvicraft.plusilclient.keybinding.KeyBindings;
import com.zvicraft.plusilclient.networking.ModPackets;
import com.zvicraft.plusilclient.networking.ServerPacketHandler;
import net.fabricmc.api.ModInitializer;
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

import static com.zvicraft.plusilclient.item.ModItems.ITEMS;
import static com.zvicraft.plusilclient.item.ModItems.ITEM_GROUP;

public class Plusilclient implements ModInitializer {
    public static final String MOD_ID = "plussmp";
    public static final Logger LOGGER = LoggerFactory.getLogger("PlusSMP");

    @Override
    public void onInitialize() {
        try {
            LOGGER.info("Initializing PlusSMP...");
            ItemGroupEvents.modifyEntriesEvent(ITEM_GROUP).register(entries -> {
                for (Item item : ITEMS) {
                    if (item == null) {
                        LOGGER.error("Found null item in ITEMS list!");
                    } else {
                        entries.add(item);
                    }
                }
            });

            // Register items and item group
            ModItems.registerModItems();
            
            // Register network handlers
            ModPackets.register();
            ServerPacketHandler.register();
            
            // Register key bindings
            KeyBindingHandler.register();
            
            LOGGER.info("PlusSMP initialized successfully!");
        } catch (Exception e) {
            LOGGER.error("Failed to initialize PlusSMP", e);
            throw new RuntimeException("Failed to initialize PlusSMP", e);
        }
    }
}