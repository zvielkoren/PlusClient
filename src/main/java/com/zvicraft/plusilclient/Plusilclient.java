package com.zvicraft.plusilclient;

import com.zvicraft.plusilclient.networking.ModPackets;
import com.zvicraft.plusilclient.networking.ServerPacketHandler;
import net.fabricmc.api.ModInitializer;

public class Plusilclient implements ModInitializer {

    @Override
    public void onInitialize() {
        ModPackets.register();

        ServerPacketHandler.register();

    }
}
