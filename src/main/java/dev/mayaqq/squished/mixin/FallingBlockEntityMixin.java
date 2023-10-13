package dev.mayaqq.squished.mixin;

import dev.mayaqq.squished.resources.BlockSquishAmount;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import virtuoel.pehkui.api.ScaleTypes;

@Mixin(FallingBlockEntity.class)
public class FallingBlockEntityMixin {
    @Inject(method = "handleFallDamage", at = @At("HEAD"))
    private void squished$addSquishEffect(float fallDistance, float damageMultiplier, DamageSource damageSource, CallbackInfoReturnable<Boolean> cir) {
        FallingBlockEntity self = (FallingBlockEntity) (Object) this;
        String blockTranslationKey = self.getBlockState().getBlock().getTranslationKey();
        Identifier blockId = new Identifier(blockTranslationKey.split("\\.")[1], blockTranslationKey.split("\\.")[2]);

        float blockSquishAmount = BlockSquishAmount.BLOCK_SQUISH_AMOUNT.blockSquishAmount.getOrDefault(blockId.toString(), 0.0F);
        float totalSquishAmount = blockSquishAmount * fallDistance * 0.01f;

        self.getWorld().getOtherEntities(self, self.getBoundingBox(), EntityPredicates.VALID_LIVING_ENTITY).forEach(entity -> {
            ScaleTypes.HEIGHT.getScaleData(entity).setScale(ScaleTypes.HEIGHT.getScaleData(entity).getScale() - totalSquishAmount);
        });
    }
}