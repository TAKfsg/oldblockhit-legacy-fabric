package com.takfsg.oldblockhit.mixin;

import net.minecraft.client.render.entity.model.BiPedModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(BiPedModel.class)
public class BiPedModelMixin {

    @ModifyConstant(method = "setAngles", constant = @Constant(floatValue = -0.5235988F))
    private float old3rdPersonBlockArm(float value) {
        return 0.0F;
    }
}
