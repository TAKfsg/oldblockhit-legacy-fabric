package com.takfsg.oldblockhit.mixin;

import com.takfsg.oldblockhit.config.Config;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ArmorFeatureRenderer.class)
public class ArmorFeatureMixin {

    @Inject(method = "combineTextures", at = @At("HEAD"), cancellable = true)
    private void redArmor(CallbackInfoReturnable<Boolean> cir) {
        if (Config.redArmor) {
            cir.setReturnValue(true);
        } else {
            cir.setReturnValue(false);
        }
    }
}
