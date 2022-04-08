/*
 *       Copyright (C) 2018-present Hyperium <https://hyperium.cc/>
 *       Modified by TAKfsg
 *
 *       This program is free software: you can redistribute it and/or modify
 *       it under the terms of the GNU Lesser General Public License as published
 *       by the Free Software Foundation, either version 3 of the License, or
 *       (at your option) any later version.
 *
 *       This program is distributed in the hope that it will be useful,
 *       but WITHOUT ANY WARRANTY; without even the implied warranty of
 *       MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *       GNU Lesser General Public License for more details.
 *
 *       You should have received a copy of the GNU Lesser General Public License
 *       along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.takfsg.oldblockhit.mixin;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.HeldItemRenderer;
import net.minecraft.client.render.entity.model.BiPedModel;
import net.minecraft.client.render.model.json.ModelTransformation.Mode;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(HeldItemRenderer.class)
public class ItemFeatureRendererMixin {

    @Shadow @Final private LivingEntityRenderer<?> field_11165;

    /**
     * @author TAKfsg
     * @reason Old 3rd Person Block Animation
     */
    @Overwrite
    public void render(LivingEntity entity, float handSwing, float handSwingAmount, float tickDelta, float age, float headYaw, float headPitch, float scale) {
        doRenderLayer(entity, field_11165);
    }

    public void doRenderLayer(LivingEntity entitylivingbaseIn, LivingEntityRenderer<?> livingEntityRenderer) {
        ItemStack itemstack = entitylivingbaseIn.getStackInHand();

        if (itemstack != null) {
            GlStateManager.pushMatrix();
            if (this.field_11165.getModel().field_1493) {
                float f = 0.5F;
                GlStateManager.translatef(0.0F, 0.625F, 0.0F);
                GlStateManager.rotatef(-20.0F, -1.0F, 0.0F, 0.0F);
                GlStateManager.scalef(f, f, f);
            }
            Label_0327:
            if (entitylivingbaseIn instanceof PlayerEntity) {
                if (((PlayerEntity) entitylivingbaseIn).method_2611()) {
                    if (entitylivingbaseIn.isSneaking()) {
                        ((BiPedModel) livingEntityRenderer.getModel()).method_9636(0.0325f);
                        GlStateManager.scalef(1.05f, 1.05f, 1.05f);
                        GlStateManager.translatef(-0.58f, 0.32f, -0.07f);
                        GlStateManager.rotatef(-24405.0f, 137290.0f, -2009900.0f, -2654900.0f);
                    } else {
                        ((BiPedModel) livingEntityRenderer.getModel()).method_9636(0.0325f);
                        GlStateManager.scalef(1.05f, 1.05f, 1.05f);
                        GlStateManager.translatef(-0.45f, 0.25f, -0.07f);
                        GlStateManager.rotatef(-24405.0f, 137290.0f, -2009900.0f, -2654900.0f);
                    }
                } else {
                    ((BiPedModel) livingEntityRenderer.getModel()).method_9636(0.0625f);
                }

                if (!((PlayerEntity) entitylivingbaseIn).method_2611()) {
                    GlStateManager.translatef(-0.0855f, 0.4775f, 0.1585f);
                    GlStateManager.rotatef(-19.0f, 20.0f, 0.0f, -6.0f);
                    break Label_0327;
                }

                if (((PlayerEntity) entitylivingbaseIn).method_2611()) {
                    GlStateManager.translatef(-0.0625f, 0.4375f, 0.0625f);
                }
            } else {
                ((BiPedModel) livingEntityRenderer.getModel()).method_9636(0.0625f);
                GlStateManager.translatef(-0.0625f, 0.4375f, 0.0625f);
            }

            if (entitylivingbaseIn instanceof PlayerEntity && ((PlayerEntity) entitylivingbaseIn).fishHook != null) {
                itemstack = new ItemStack(Items.FISHING_ROD, 0);
            }

            Item item = itemstack.getItem();
            MinecraftClient minecraft = MinecraftClient.getInstance();

            if (item instanceof BlockItem && Block.getBlockFromItem(item).getBlockType() == 2) {
                GlStateManager.translatef(0.0f, 0.1875f, -0.3125f);
                GlStateManager.rotatef(20.0f, 1.0f, 0.0f, 0.0f);
                GlStateManager.rotatef(45.0f, 0.0f, 1.0f, 0.0f);
                float f2 = 0.375f;
                GlStateManager.scalef(-f2, -f2, f2);
            }

            if (entitylivingbaseIn.isSneaking()) {
                GlStateManager.translatef(0.0f, 0.203125f, 0.0f);
            }

            minecraft.getHeldItemRenderer().method_9872(entitylivingbaseIn, itemstack, Mode.THIRD_PERSON);
            GlStateManager.popMatrix();
        }
    }
}
