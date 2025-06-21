package com.zvicraft.plusilclient.networking;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record IconChangePayload(Identifier iconTexture) implements CustomPayload {
    public static final CustomPayload.Id<IconChangePayload> ID = new CustomPayload.Id<>(Identifier.of("plussmp", "icon_change"));
    public static final PacketCodec<PacketByteBuf, IconChangePayload> CODEC = PacketCodec.tuple(
        Identifier.PACKET_CODEC,
        IconChangePayload::iconTexture,
        IconChangePayload::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }

    public static IconChangePayload read(PacketByteBuf buf) {
        return new IconChangePayload(buf.readIdentifier());
    }

    public void write(PacketByteBuf buf) {
        buf.writeIdentifier(iconTexture);
    }
}
