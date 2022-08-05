package com.idtech.item;

import com.idtech.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.*;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Giant;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.DragonFireball;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraft.world.item.BoneMealItem;

public class TestItem extends Item {

    //static instance for registration
    private static Properties properties = new Properties().tab(CreativeModeTab.TAB_MISC);
    public static Item TEST_ITEM = new TestItem(properties).setRegistryName("testitem");

    //constructor
    public TestItem(Properties properties) {
        super(properties);

    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player playerIn, InteractionHand handIn) {
        //get the held item for return
        ItemStack itemstack = playerIn.getItemInHand(handIn);


        //find the location at the cursor
        BlockPos location = Utils.getBlockAtCursor(playerIn, 200.0d, true);
        BlockPos playerLocation = playerIn.getOnPos();

        //decide the size of the explosion
        float explosionRadius = 1.0f;
        Giant giant = new Giant(EntityType.GIANT, level);
        DragonFireball fireball= new DragonFireball(EntityType.DRAGON_FIREBALL,level);

        fireball.xPower = 15d;
        /*
        BlockState state = level.getBlockState(location);
        BonemealEvent bonemealEvent = new BonemealEvent(playerIn,level,location,state,itemstack );
        BoneMealItem boneMealItem = new BoneMealItem(properties);
        Utils.applyBonemeal(itemstack,level,location,playerIn);

         */
        level.addParticle(ParticleTypes.GLOW, playerIn.getRandomX(0.6D), playerIn.getRandomY(), playerIn.getRandomZ(0.6D), 0.0D, 0.0D, 0.0D);
        MobEffectInstance blindness = new MobEffectInstance(MobEffects.BLINDNESS);
        playerIn.addEffect((new MobEffectInstance(MobEffects.BLINDNESS, 220, 220)), playerIn);
        /*
        //as long as the location exists
        if(location != null){
            //Utils.spawnEntity(level, giant,location);

            for (BlockPos block:Utils.getRayToCursor(playerIn,200.0d,true)) {
                Utils.strikeLightning(level,block);
            }
        }

         */
        //return pass
        return InteractionResultHolder.pass(itemstack);
    }
}