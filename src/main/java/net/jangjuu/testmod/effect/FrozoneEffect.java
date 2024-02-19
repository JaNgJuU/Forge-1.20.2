package net.jangjuu.testmod.effect;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

public class FrozoneEffect extends MobEffect {

    public FrozoneEffect(MobEffectCategory mobEffectCategory, int i) {
        super(MobEffectCategory.BENEFICIAL, 0x00FFFF);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        Level world = entity.getCommandSenderWorld(); // Accessing the entity's world (level)

        BlockPos playerPos = entity.blockPosition(); // Getting the position of the entity (player)

        // Check if the block below the player is not already ice
        if (world.getBlockState(playerPos.below()).getBlock() != Blocks.ICE) {
            // Turn the ground into ice where the player steps
            world.setBlockAndUpdate(playerPos.below(), Blocks.ICE.defaultBlockState());
        }

        // Check if the player is sneaking (holding shift)
        if (entity.isCrouching()) {
            // If the player is sneaking, remove the ice block below them
            if (world.getBlockState(playerPos.below()).getBlock() == Blocks.ICE) {
                world.setBlockAndUpdate(playerPos.below(), Blocks.AIR.defaultBlockState());
            }
        } else {
            // If the player is not sneaking, check if they are jumping
            if (!entity.onGround()) {
                // If the player is jumping, move the ice block up by one block
                BlockPos abovePlayerPos = playerPos.above();
                // Check if the block above the player is air, then move the ice block
                if (world.getBlockState(abovePlayerPos).getBlock() == Blocks.AIR) {
                    world.setBlockAndUpdate(abovePlayerPos, Blocks.ICE.defaultBlockState());
                    world.setBlockAndUpdate(playerPos.below(), Blocks.AIR.defaultBlockState());
                }
            }
        }
    }
}

