package com.takfsg.oldblockhit.mixin;

import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(InventoryScreen.class)
public abstract class InventoryScreenMixin {

    @ModifyConstant(method = "applyStatusEffectOffset", constant = @Constant(intValue = 200))
    private int noEffectOffset(int original) {
        return 324;
    }
}
