package com.wildcard.createbuddycards;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(CreateBuddycards.MOD_ID)
public class CreateBuddycards
{
    public static final String MOD_ID = "createbuddycards";

    public CreateBuddycards(IEventBus eventBus, ModContainer modContainer)
    {
        RegistryHandler.registerAll(eventBus);
    }
}
