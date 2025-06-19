package com.zvicraft.plusilclient.client;

import com.zvicraft.plusilclient.client.screens.Overlay;
import com.zvicraft.plusilclient.keybinding.KeyBindingHandler;
import com.zvicraft.plusilclient.keybinding.KeyBindings;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class PlusilclientClient implements ClientModInitializer {
//    EntityType<BossEntity> BOSS_ENTITY_TYPE = Registry.register(
//            Registries.ENTITY_TYPE,
//            Identifier.of("plussmp", "boss"),
//            FabricEntityTypeBuilder.create(SpawnGroup.MISC, BossEntity::new).build()
//    );


    @Override
    public void onInitializeClient() {
        KeyBindings.register();
        KeyBindingHandler.register();
        Overlay.register();
//        MyModEntities.registerClientEntities();

    }
}
