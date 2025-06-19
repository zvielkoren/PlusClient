package com.zvicraft.plusilclient.networking;

import com.zvicraft.plusilclient.keybinding.KeyBindingHandler;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public class ModPackets {
    public static final CustomPayload.Id<KeyBindingHandler.PowerActivationPayload> POWER_ACTIVATION_ID =
            new CustomPayload.Id<>(Identifier.of("plussmp", "power_activation"));

    public static void register() {
        // Register the payload type for both client-to-server and server-to-client
        PayloadTypeRegistry.playS2C().register(
                POWER_ACTIVATION_ID,
                KeyBindingHandler.PowerActivationPayload.CODEC
        );
        PayloadTypeRegistry.playC2S().register(
                POWER_ACTIVATION_ID,
                KeyBindingHandler.PowerActivationPayload.CODEC
        );
    }
}