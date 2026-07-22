package net.ethelred.pillow.mixin;

import net.ethelred.pillow.PillowItem;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Ports the Bedrock addon's pillow-fight behaviour: hitting a living entity with a
 * Pillow deals no damage but sends the target flying, heals it, and bursts feathers.
 */
@Mixin(LivingEntity.class)
public abstract class PlayerAttackMixin {

    private static final double KNOCKBACK_STRENGTH = 3.0;
    private static final double VERTICAL_KNOCKBACK = 0.5;
    private static final float HEAL_AMOUNT = 1.0F;

    @Inject(method = "hurt", at = @At("HEAD"), cancellable = true)
    private void pillow$onHurt(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        Entity attacker = source.getEntity();
        if (!(attacker instanceof Player player)) {
            return;
        }
        if (!(player.getMainHandItem().getItem() instanceof PillowItem)) {
            return;
        }

        LivingEntity target = (LivingEntity) (Object) this;
        if (!(target.level() instanceof ServerLevel serverLevel)) {
            return;
        }

        double dx = player.getX() - target.getX();
        double dz = player.getZ() - target.getZ();
        target.knockback(KNOCKBACK_STRENGTH, dx, dz);
        target.setDeltaMovement(target.getDeltaMovement().x, VERTICAL_KNOCKBACK, target.getDeltaMovement().z);
        target.hasImpulse = true;

        target.heal(HEAL_AMOUNT);

        serverLevel.sendParticles(
            new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(Items.FEATHER)),
            target.getX(), target.getEyeY(), target.getZ(),
            12, 0.3, 0.3, 0.3, 0.05
        );

        cir.setReturnValue(false);
    }
}
