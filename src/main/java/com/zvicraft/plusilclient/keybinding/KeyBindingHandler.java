package com.zvicraft.plusilclient.keybinding;

import com.zvicraft.plusilclient.networking.ModPackets;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public class KeyBindingHandler {
    // Remove the old POWER_ACTIVATION_PACKET field

    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null) {
                if (KeyBindings.POWER_1_KEY.wasPressed()) {
                    sendPowerActivation(1);
                }
                if (KeyBindings.POWER_2_KEY.wasPressed()) {
                    sendPowerActivation(2);
                }
            }
        });
    }

    private static void sendPowerActivation(int slot) {
        // Create and send the payload
        PowerActivationPayload payload = new PowerActivationPayload(slot);
        ClientPlayNetworking.send(payload);
    }

    public record PowerActivationPayload(int slot) implements CustomPayload {
        public static final PacketCodec<PacketByteBuf, PowerActivationPayload> CODEC =
                PacketCodec.of(PowerActivationPayload::write, PowerActivationPayload::new);

        public PowerActivationPayload(PacketByteBuf buf) {
            this(buf.readInt());
        }

        public void write(PacketByteBuf buf) {
            buf.writeInt(slot);
        }

        @Override
        public Id<? extends CustomPayload> getId() {
            return ModPackets.POWER_ACTIVATION_ID;
        }
    }
}
