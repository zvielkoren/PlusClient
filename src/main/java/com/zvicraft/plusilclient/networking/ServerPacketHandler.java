package com.zvicraft.plusilclient.networking;

import com.zvicraft.plusilclient.keybinding.KeyBindingHandler;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class ServerPacketHandler {
    public static void register() {
        ServerPlayNetworking.registerGlobalReceiver(
                ModPackets.POWER_ACTIVATION_ID,
                (payload, context) -> {
                    ServerPlayerEntity player = context.player();
                    MinecraftServer server = player.getServer();
                    if (server != null) {
                        server.execute(() -> {
                            int slot = payload.slot();
                            player.sendMessage(Text.literal("Activated power in slot: " + slot));
                        });
                    }
                }
        );
    }
}