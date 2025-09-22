package com.zvicraft.plusilclient.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.SuspiciousStewIngredient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemGroups;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.ArrayList;
import java.util.List;

@Environment(EnvType.CLIENT)
@Mixin(ItemGroups.class)
public class SuspiciousStewIngredientMixin {

    /**
     * Safe replacement for SuspiciousStewIngredient.getAll() that handles null values
     * Only runs on client side and only in single player to prevent server issues
     */
    @Redirect(method = "addSuspiciousStews", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/SuspiciousStewIngredient;getAll()Ljava/util/List;"))
    private static List<SuspiciousStewIngredient> safeGetAll() {
        try {
            // Check if we're connected to a server - if so, use original method
            MinecraftClient client = MinecraftClient.getInstance();
            if (client != null && client.getNetworkHandler() != null && !client.isInSingleplayer()) {
                // On multiplayer server - use original method to avoid packet issues
                return SuspiciousStewIngredient.getAll();
            }

            // Single player or no connection - safe to use our filtering
            List<SuspiciousStewIngredient> originalList = SuspiciousStewIngredient.getAll();
            if (originalList == null) {
                return new ArrayList<>();
            }

            List<SuspiciousStewIngredient> safeList = new ArrayList<>();
            for (SuspiciousStewIngredient ingredient : originalList) {
                if (ingredient != null) {
                    safeList.add(ingredient);
                }
            }
            return safeList;

        } catch (Exception e) {
            // If anything goes wrong, return empty list
            System.err.println("SuspiciousStew mixin failed: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
