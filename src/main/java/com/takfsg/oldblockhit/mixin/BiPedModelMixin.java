package com.takfsg.oldblockhit.mixin;

import com.takfsg.oldblockhit.config.Config;
import net.minecraft.client.render.entity.model.BiPedModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(BiPedModel.class)
public class BiPedModelMixin {

    @ModifyConstant(method = "setAngles", constant = @Constant(floatValue = -0.5235988F))
    private float cancelRotation(float value) {
        return Config.old3rdPersonBlock ? 0.0F : value;
    }
}
