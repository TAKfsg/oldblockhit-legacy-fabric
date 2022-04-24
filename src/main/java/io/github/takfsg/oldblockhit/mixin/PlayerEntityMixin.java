package io.github.takfsg.oldblockhit.mixin;

import io.github.takfsg.oldblockhit.config.Config;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

    private float currentHeight = 1.62F;
    private long lastChangeTime = System.currentTimeMillis();

    // TODO: Make Upsneak Faster than Downsneak
    /**
     * @author TAKfsg
     * @reason Semi-old Sneak Animation
     */
    @Overwrite
    public float getEyeHeight() {
        PlayerEntity parent = (PlayerEntity)(Object)this;
        if (Config.oldSneak) {
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
        } else {
            float f = 1.62F;
            if (parent.isSleeping()) f = 0.2F;
            if (parent.isSneaking()) f -= 0.08F;
            return f;
        }
    }
}
