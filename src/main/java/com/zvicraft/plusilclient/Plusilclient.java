
package com.zvicraft.plusilclient;

import com.zvicraft.plusilclient.discord.DiscordIntegration;
import com.zvicraft.plusilclient.discord.DiscordRPCHandler;
import com.zvicraft.plusilclient.item.ModItemGroups;
import com.zvicraft.plusilclient.item.ModItems;
import com.zvicraft.plusilclient.keybinding.KeyBindingHandler;
import com.zvicraft.plusilclient.networking.ModPackets;
import com.zvicraft.plusilclient.networking.ServerPacketHandler;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Plusilclient implements ModInitializer {
    public static final String MOD_ID = "plussmp";
    public static final Logger LOGGER = LoggerFactory.getLogger("PlusSMP");
    private static DiscordRPCHandler discordHandler;

    /**
     * Get the Discord RPC handler instance
     * @return DiscordRPCHandler instance or null if not initialized
     */
    public static DiscordRPCHandler getDiscordHandler() {
        return discordHandler;
    }

    /**
     * Update Discord presence with custom activity
     * @param activity The activity to display
     */
    public static void updateDiscordActivity(String activity) {
        if (discordHandler != null) {
            discordHandler.updateCustomActivity(activity);
        }
    }

    /**
     * Get the current player name from Discord handler
     * @return Current player name or "Unknown Player"
     */
    public static String getCurrentPlayerName() {
        if (discordHandler != null) {
            return discordHandler.getCurrentPlayerName();
        }
        return "Unknown Player";
    }

    /**
     * Check if player is currently in game
     * @return true if player is in game, false otherwise
     */
    public static boolean isPlayerInGame() {
        if (discordHandler != null) {
            return discordHandler.isPlayerInGame();
        }
        return false;
    }

    /**
     * Get the current server name or IP
     * @param client The Minecraft client instance
     * @return Server name or "Unknown Server"
     */
    private static String getServerName(net.minecraft.client.MinecraftClient client) {
        try {
            if (client.getCurrentServerEntry() != null) {
                String name = client.getCurrentServerEntry().name;
                return name != null && !name.isEmpty() ? name : client.getCurrentServerEntry().address;
            } else if (client.isInSingleplayer()) {
                return "Singleplayer";
            } else {
                return "Multiplayer Server";
            }
        } catch (Exception e) {
            LOGGER.warn("Failed to get server name", e);
            return "Unknown Server";
        }
    }

    @Override
    public void onInitialize() {
        try {
            LOGGER.info("Initializing PlusSMP...");

            // Register network handlers first (these are always safe)
            ModPackets.register();
            ServerPacketHandler.register();

            // Register key bindings only on client
            if (FabricLoader.getInstance().getEnvironmentType().equals(net.fabricmc.api.EnvType.CLIENT)) {
                // Register items first
                ModItems.registerModItems();

                // Register item groups after all items are registered
                ModItemGroups.registerItemGroups();

                // Register creative tab integrations after everything else is ready
                ModItems.registerCreativeTabs();

                KeyBindingHandler.register();

                // Initialize Discord RPC Handler
                discordHandler = DiscordRPCHandler.getInstance();
                discordHandler.initialize();

                // Register additional server join/disconnect events for Discord RPC
                ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
                    if (client.player != null) {
                        String serverName = getServerName(client);
                        LOGGER.info("Player joined server: {}", serverName);
                        updateDiscordActivity("Playing on " + serverName);
                    }
                });

                ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> {
                    LOGGER.info("Player disconnected from server");
                    updateDiscordActivity("In Menu");
                });
            }

            LOGGER.info("PlusSMP initialized successfully!");
        } catch (Exception e) {
            LOGGER.error("Failed to initialize PlusSMP", e);
        }
    }
}