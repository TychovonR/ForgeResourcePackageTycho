package com.idtech;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import javax.sound.sampled.Clip;
import java.util.ArrayList;

/**
 * Utilities to use with block, item and entities to simplify some functionality
 */
public class Utils {

    /**
     * Strikes a location with lightning
     * @param world The world to strike lightning in
     * @param location The block position to strike with lightning
     */
    public static void strikeLightning(Level world, BlockPos location){

//        if (world instanceof ServerWorld){
//            ServerWorld serverWorld = (ServerWorld) world;
            LightningBolt lightningBolt = new LightningBolt(EntityType.LIGHTNING_BOLT, world);
            lightningBolt.setPos(location.getX(), location.getY(), location.getZ());
            world.addFreshEntity(lightningBolt);


    }

    /**
     * Creates an explosion at location with radius of explosionRadius
     * @param level The world to create explosion in
     * @param location the block position to create explosion
     * @param explosionRadius the radius of the explosion
     */
    public static void createExplosion(Level level, BlockPos location, float explosionRadius){
        level.explode(null, location.getX(), location.getY(), location.getZ(), explosionRadius, Explosion.BlockInteraction.BREAK);
       // level.explode()
    }

    /**
     * Method to spawn an entity into the world. Use after creating an Entity object to add as a parameter for this method.
     * e.g: Cow cow = new Cow();
     *      spwanEntity(level, cow, location);
     * @param level the world in which we are spawning
     * @param entity an entity object that will be spawned
     * @param location the location in which the entity should be spawned.
     * @return the entity that has been spawned
     */
    public static Entity spawnEntity(Level level, Entity entity, BlockPos location){

        entity.setPos(location.getX(), location.getY(), location.getZ());
        level.addFreshEntity(entity);

        return entity;
    }

    /**
     * Get the block that the player is looking at.
     * @param player The player whose look we're checking
     * @param distance range to check in block
     * @return Block position if one is found within range, null if no block in range.
     */

    public static BlockPos getBlockAtCursor(Player player, double distance, boolean ignoreFluids) {
        ClipContext.Fluid fluidMode = ignoreFluids ? ClipContext.Fluid.NONE : ClipContext.Fluid.ANY;

        ClipContext rayTraceContext = new ClipContext(player.getEyePosition(1), player.getEyePosition(1).add(player.getLookAngle().scale(distance)), ClipContext.Block.COLLIDER, fluidMode, player);
        BlockHitResult blockHit = player.level.clip(rayTraceContext);

        if(blockHit.getType() == BlockHitResult.Type.MISS){
            return null;
        }
        else {
            return blockHit.getBlockPos();
        }

    }

    public static ArrayList<BlockPos> getRayToCursor(Player player, double distance, boolean ignoreFluids){
        ArrayList<BlockPos> ray = new ArrayList<BlockPos>();
        ClipContext.Fluid fluidMode = ignoreFluids ? ClipContext.Fluid.NONE : ClipContext.Fluid.ANY;
        ClipContext rayTraceContext = new ClipContext(player.getEyePosition(1), player.getEyePosition(1).add(player.getLookAngle().scale(distance)), ClipContext.Block.COLLIDER, fluidMode, player);
        BlockHitResult blockHit = player.level.clip(rayTraceContext);

        if(blockHit.getType() == BlockHitResult.Type.MISS){
            return null;
        }
        else {
            double sumX = player.getX() + blockHit.getBlockPos().getX();
            double sumZ = player.getZ() + blockHit.getBlockPos().getZ();
            for (int i = 1; i <300 ; i++) {
                BlockPos tempBlock = new BlockPos(sumX/(1+i*.01),player.getY(),sumZ/(1+i*.01));
                ray.add(tempBlock);
            }
            return ray;
        }
    }



    /**
     * Set a block location on fire.
     * @param world world to set the fire in.
     * @param location The block location to set on fire.
     * @param face The side of the block to set on fire. Use Direction.UP, DOWN, NORTH, SOUTH, EAST, WEST
     */

    public static void setFireBlock(Level world, BlockPos location, Direction face){
        BlockState blockState = world.getBlockState(location);

        if (CampfireBlock.canLight(blockState)) {
            world.playSound(null, location, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, world.getRandom().nextFloat() * 0.4F + 0.8F);
            world.setBlock(location, blockState.setValue(BlockStateProperties.LIT, Boolean.valueOf(true)), 11);
        } else {
           // BlockPos blockpos1 = location.offset(face);
            if (BaseFireBlock.canBePlacedAt(world, location, face)) {
                world.playSound(null, location, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS,  1.0F, world.getRandom().nextFloat() * 0.4F + 0.8F);
                BlockState blockstate1 = BaseFireBlock.getState(world, location);
                world.setBlock(location, blockstate1, 11);

            }
        }
    }

    /**
     * Find a random block pos based on a block pos.
     * @param pos position of the block from which to spread
     * @return a random position near that block.
     */
    public static BlockPos findNeightborBlock(BlockPos pos){

        int spreadX = (int)Math.floor(Math.random()*3) - 1;
        int spreadY = spreadX == 0 ? (int)Math.floor(Math.random()*3) - 1 : 0 ;
        int spreadZ = spreadX == 0  && spreadY == 0 ? (int)Math.floor(Math.random()*3) - 1 : 0;
        //changed to offset from add
        BlockPos spreadPos = pos.offset(spreadX, spreadY, spreadZ);

        return spreadPos;
    }
    public static boolean applyBonemeal(ItemStack p_40628_, Level p_40629_, BlockPos p_40630_, net.minecraft.world.entity.player.Player player) {
        BlockState blockstate = p_40629_.getBlockState(p_40630_);
        int hook = net.minecraftforge.event.ForgeEventFactory.onApplyBonemeal(player, p_40629_, p_40630_, blockstate, p_40628_);
        if (hook != 0) return hook > 0;
        if (blockstate.getBlock() instanceof BonemealableBlock) {
            BonemealableBlock bonemealableblock = (BonemealableBlock)blockstate.getBlock();
            if (bonemealableblock.isValidBonemealTarget(p_40629_, p_40630_, blockstate, p_40629_.isClientSide)) {
                if (p_40629_ instanceof ServerLevel) {
                    if (bonemealableblock.isBonemealSuccess(p_40629_, p_40629_.random, p_40630_, blockstate)) {
                        bonemealableblock.performBonemeal((ServerLevel)p_40629_, p_40629_.random, p_40630_, blockstate);
                    }

                }

                return true;
            }
        }

        return false;
    }

}
