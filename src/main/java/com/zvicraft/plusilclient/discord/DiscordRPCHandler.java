
package com.zvicraft.plusilclient.discord;

import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.minecraft.client.MinecraftClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DiscordRPCHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger("DiscordRPCHandler");
    private static final String CLIENT_ID = "1371214190659895306";
    private static DiscordRPCHandler instance;
    private static long startTime;
    private String currentPlayerName = "Unknown Player";
    private String currentServerName = "Menu";
    private boolean isInGame = false;
    private Thread callbackThread;

    private DiscordRPCHandler() {}

    public static DiscordRPCHandler getInstance() {
        if (instance == null) {
            instance = new DiscordRPCHandler();
        }
        return instance;
    }

    public void initialize() {
        LOGGER.info("Initializing Discord RPC Handler...");

        try {
            startTime = System.currentTimeMillis() / 1000;

            DiscordEventHandlers handlers = new DiscordEventHandlers.Builder()
                    .setReadyEventHandler(user -> {
                        LOGGER.info("Discord RPC connected: {}#{}", user.username, user.discriminator);
                    })
                    .build();

            DiscordRPC.discordInitialize(CLIENT_ID, handlers, true);

            // Start callback thread
            startCallbackThread();

            // Register lifecycle events
            registerEvents();

            // Set initial state
            updatePresence("In Menu", "Browsing menus");

            LOGGER.info("Discord RPC Handler initialized successfully!");
        } catch (Exception e) {
            LOGGER.error("Failed to initialize Discord RPC Handler", e);
        }
    }

    private void startCallbackThread() {
        callbackThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    DiscordRPC.discordRunCallbacks();
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                } catch (Exception e) {
                    LOGGER.error("Discord RPC callback error", e);
                }
            }
        }, "Discord-RPC-Callback-Handler");
        callbackThread.start();
    }

    private void registerEvents() {
        // Client started - no player available yet
        ClientLifecycleEvents.CLIENT_STARTED.register(client -> {
            LOGGER.info("Minecraft client started");
            updatePresence("In Menu", "Browsing menus");
        });

        // Player joins server/world - player is available
        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
            if (client.player != null) {
                currentPlayerName = client.player.getName().getString();
                currentServerName = getServerName(client);
                isInGame = true;
                LOGGER.info("Player '{}' joined server: {}", currentPlayerName, currentServerName);
                updatePresence("Playing on " + currentServerName, "As " + currentPlayerName);
            }
        });

        // Player disconnects from server/world
        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> {
            LOGGER.info("Player '{}' disconnected from {}", currentPlayerName, currentServerName);
            isInGame = false;
            currentServerName = "Menu";
            updatePresence("In Menu", "Browsing menus");
        });

        // Client stopping
        ClientLifecycleEvents.CLIENT_STOPPING.register(client -> {
            LOGGER.info("Minecraft client stopping");
            shutdown();
        });
    }

    private String getServerName(MinecraftClient client) {
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

    public void updatePresence(String details, String state) {
        try {
            DiscordRichPresence.Builder presence = new DiscordRichPresence.Builder(state)
                    .setDetails(details)
                    .setStartTimestamps(startTime)
                    .setBigImage("minecraft", "Playing Minecraft")
                    .setSmallImage("plussmp", "Plus Server");

            DiscordRPC.discordUpdatePresence(presence.build());
            LOGGER.debug("Updated Discord presence: {} - {}", details, state);
        } catch (Exception e) {
            LOGGER.error("Failed to update Discord presence", e);
        }
    }

    public void updateCustomActivity(String customActivity) {
        if (isInGame) {
            updatePresence(customActivity, "As " + currentPlayerName);
        } else {
            updatePresence(customActivity, "In Menu");
        }
    }

    public String getCurrentPlayerName() {
        return currentPlayerName;
    }

    public String getCurrentServerName() {
        return currentServerName;
    }

    public boolean isPlayerInGame() {
        return isInGame;
    }

    public void shutdown() {
        LOGGER.info("Shutting down Discord RPC Handler...");
        try {
            if (callbackThread != null && callbackThread.isAlive()) {
                callbackThread.interrupt();
            }
            DiscordRPC.discordShutdown();
            LOGGER.info("Discord RPC Handler shut down successfully");
        } catch (Exception e) {
            LOGGER.error("Error during Discord RPC shutdown", e);
        }
    }
}
