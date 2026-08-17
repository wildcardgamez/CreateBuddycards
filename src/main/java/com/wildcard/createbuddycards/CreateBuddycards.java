package com.wildcard.createbuddycards;

import net.minecraftforge.fml.common.Mod;

@Mod(CreateBuddycards.MOD_ID)
public class CreateBuddycards
{
    public static final String MOD_ID = "createbuddycards";

    public CreateBuddycards()
    {
        RegistryHandler.init();
    }
}
