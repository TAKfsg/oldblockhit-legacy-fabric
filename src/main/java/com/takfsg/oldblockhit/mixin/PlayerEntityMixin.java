package com.takfsg.oldblockhit.mixin;

import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

    private float currentHeight = 1.62F;
    private long lastChangeTime = System.currentTimeMillis();

    /**
     * @author TAKfsg
     * @reason Old Eye Height
     */
    @Overwrite
    public float getEyeHeight() {
        PlayerEntity parent = (PlayerEntity)(Object)this;
        int timeDelay = 1000 / 60;
        if (parent.isSneaking()) {
            float sneakingHeight = 1.54F;
            if (currentHeight > sneakingHeight) {
                long time = System.currentTimeMillis();
                long timeSinceLastChange = time - lastChangeTime;
                if (timeSinceLastChange > timeDelay) {
                    currentHeight -= 0.012F;
                    lastChangeTime = time;
                }
            }
        } else {
            float standingHeight = 1.62F;
            if (currentHeight < standingHeight && currentHeight > 0.2F) {
                long time = System.currentTimeMillis();
                long timeSinceLastChange = time - lastChangeTime;
                if (timeSinceLastChange > timeDelay) {
                    currentHeight += 0.012F;
                    lastChangeTime = time;
                }
            } else {
                currentHeight = 1.62F;
            }
        }

        if (parent.isSleeping()) currentHeight = 0.2F;

        return currentHeight;
    }
}
