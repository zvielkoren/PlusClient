package com.zvicraft.plusilclient;

import com.zvicraft.plusilclient.item.ModItems;
import com.zvicraft.plusilclient.networking.ModPackets;
import com.zvicraft.plusilclient.networking.ServerPacketHandler;
import net.fabricmc.api.ModInitializer;

public class Plusilclient implements ModInitializer {
    public static final String MOD_ID = "plussmp";

    @Override
    public void onInitialize() {
        // Register networking
        ModPackets.register();
        ServerPacketHandler.register();
        
        // Register power items
        ModItems.registerModItems();
    }
}
