package com.takfsg.oldblockhit.mixin;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.item.HeldItemRenderer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HeldItemRenderer.class)
public abstract class HeldItemRendererMixin {

	@Shadow @Final private MinecraftClient field_1876;

	private static float h;

	@Inject(method = "method_1354", at = @At("HEAD"))
	private void getClient(float f, CallbackInfo ci) {
		h = this.field_1876.player.getHandSwingProgress(f);
	}

	@Inject(method = "method_1354", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/item/HeldItemRenderer;method_9877()V", ordinal = 0, shift = At.Shift.AFTER))
	private void transformBlockingSword(float f, CallbackInfo ci) {
		GlStateManager.scalef(0.83f, 0.88f, 0.85f);
		GlStateManager.translatef(-0.3f, 0.1f, 0.0f);
	}

	@ModifyConstant(method = "method_1354", constant = @Constant(floatValue = 0.0F))
	private float enableBlockHits(float constant) {
		return h;
	}
}
