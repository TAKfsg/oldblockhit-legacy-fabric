 package com.takfsg.oldblockhit.mixin;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.block.Material;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.player.ClientPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.hit.HitResult;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HeldItemRenderer.class)
public class HeldItemRendererMixin {

    @Shadow @Final private MinecraftClient field_1876;
    @Shadow private ItemStack field_1877;

    private static float h;

    @Inject(method = "method_1354", at = @At("HEAD"))
    private void getSwingProgress(float f, CallbackInfo ci) {
        h = this.field_1876.player.getHandSwingProgress(f);
    }

    @Inject(method = "method_9873", at = @At(value = "HEAD"))
    private void useSwing(CallbackInfo ci) {
        if (field_1876.options.keyAttack.isPressed() && field_1876.options.keyUse.isPressed() && field_1876.result.type == HitResult.Type.BLOCK) {
            ClientPlayerEntity player = field_1876.player;
            int swingAnimationEnd = player.hasStatusEffect(StatusEffect.HASTE) ? (6 - (1 +
                    player.getEffectInstance(StatusEffect.HASTE).getAmplifier())) : (player.hasStatusEffect(StatusEffect.MINING_FATIGUE) ? (6 + (1 +
                    player.getEffectInstance(StatusEffect.MINING_FATIGUE).getAmplifier()) * 2) : 6);
            if (!player.handSwinging || player.handSwingTicks >= swingAnimationEnd / 2 || player.handSwingTicks < 0) {
                player.handSwingTicks = -1;
                player.handSwinging = true;
            }
        }
    }

    @Inject(method = "method_1354", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;"))
    private void transformRod(float f, CallbackInfo ci) {
        if (field_1877.getItem() == Items.FISHING_ROD) {
            GlStateManager.translatef(0.08f, -0.027f, -0.33f);
            GlStateManager.scalef(0.93f, 1.0f, 1.0f);
        }
    }

    @Inject(method = "method_1354", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/item/HeldItemRenderer;method_9877()V", ordinal = 0, shift = At.Shift.AFTER))
    private void transformBlockingSword(float f, CallbackInfo ci) {
        GlStateManager.scalef(0.83f, 0.88f, 0.85f);
        GlStateManager.translatef(-0.3f, 0.1f, 0.0f);
    }

    @Inject(method = "method_1354", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/item/HeldItemRenderer;method_9863(FLnet/minecraft/client/network/AbstractClientPlayerEntity;)V", ordinal = 0, shift = At.Shift.AFTER))
    private void transformBow(float f, CallbackInfo ci) {
        GlStateManager.translatef(0.0F, 0.1F, -0.15F);
    }

    @ModifyConstant(method = "method_1354", constant = @Constant(floatValue = 0.0F))
    private float enableBlockHits(float constant) {
        return h;
    }
}
