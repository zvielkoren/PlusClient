package com.zvicraft.plusilclient.client.screens;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.Window;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;

public class Overlay {
    // Cache texture identifier to avoid creating it every frame
    private static final Identifier EMPTY_SLOT_TEXTURE = Identifier.of("plussmp", "textures/screens/empty-slot.png");

    // Define constants for better maintainability
    private static final int SLOT_SIZE = 24;
    private static final int SLOT_SPACING = 6;
    private static final int VERTICAL_OFFSET = 70;

    public static void register() {
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
                    RenderLayer::getGuiTextured,
                    EMPTY_SLOT_TEXTURE,
                    baseX, baseY,
                    0.0f, 0.0f,
                    SLOT_SIZE, SLOT_SIZE,
                    SLOT_SIZE, SLOT_SIZE
            );

            // Draw second slot
            drawContext.drawTexture(
                    RenderLayer::getGuiTextured,
                    EMPTY_SLOT_TEXTURE,
                    baseX + SLOT_SIZE + SLOT_SPACING, baseY,
                    0.0f, 0.0f,
                    SLOT_SIZE, SLOT_SIZE,
                    SLOT_SIZE, SLOT_SIZE
            );
        });
    }
}