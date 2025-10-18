package com.zvicraft.plusilclient.client.screens;

import com.zvicraft.plusilclient.networking.IconChangePayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.Window;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;

public class Overlay {
    // Cache texture identifier to avoid creating it every frame
    private static final Identifier EMPTY_SLOT_TEXTURE = Identifier.of("plussmp", "textures/screens/empty-slot.png");
    private static Identifier currentIcon =  Identifier.tryParse("plussmp", "textures/screens/empty-slot.png");

    // Define constants for better maintainability
    private static final int SLOT_SIZE = 24;
    private static final int SLOT_SPACING = 6;
    private static final int VERTICAL_OFFSET = 70;

    public static void register() {

        PayloadTypeRegistry.playS2C().register(IconChangePayload.ID, IconChangePayload.CODEC);

        // Register the packet handler
        ClientPlayNetworking.registerGlobalReceiver(IconChangePayload.ID, (payload, context) -> {
            System.out.println("Received icon change packet: " + payload.iconTexture());
            // Execute on the client thread to ensure thread safety
            context.client().execute(() -> {
                currentIcon = payload.iconTexture();
            });
        });



        HudRenderCallback.EVENT.register((DrawContext drawContext, RenderTickCounter tickCounter) -> {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client.player == null || client.world == null) return;

            Window window = client.getWindow();
            int screenWidth = window.getScaledWidth();
            int screenHeight = window.getScaledHeight();

            // Calculate positioning
            int totalWidth = (SLOT_SIZE * 2) + SLOT_SPACING;
            int baseX = (screenWidth - totalWidth) / 2;
            int baseY = screenHeight - VERTICAL_OFFSET;

            // Draw first slot
            drawContext.drawTexture(
                    currentIcon,
                    baseX, baseY,
                    0.0f, 0.0f,
                    SLOT_SIZE, SLOT_SIZE,
                    SLOT_SIZE, SLOT_SIZE
            );

            // Draw second slot
            drawContext.drawTexture(
                    currentIcon,
                    baseX + SLOT_SIZE + SLOT_SPACING, baseY,
                    0.0f, 0.0f,
                    SLOT_SIZE, SLOT_SIZE,
                    SLOT_SIZE, SLOT_SIZE
            );
        });
    }
}