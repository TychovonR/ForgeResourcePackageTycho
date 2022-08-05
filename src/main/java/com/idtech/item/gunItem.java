package com.idtech.item;

import com.idtech.Utils;
import com.idtech.entity.magmaArrow;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.DragonFireball;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class gunItem extends Item {

    //static instance for registration
    private static Properties properties = new Properties().tab(CreativeModeTab.TAB_MISC);
    public static Item GUN_ITEM = new gunItem(properties).setRegistryName("gunitem");

    //constructor
    public gunItem(Properties properties) {
        super(properties);

    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player playerIn, InteractionHand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        magmaArrow arrow = new magmaArrow(magmaArrow.TYPE,level);
        Vec3 dirVec = playerIn.getViewVector(0);
        dirVec = dirVec.scale(6);
        Vec3 upVec = playerIn.getUpVector(0);
        arrow.setDeltaMovement(dirVec);
        arrow.setSecondsOnFire(100);
        Vec3 spawnVec = playerIn.getEyePosition();
        arrow.setNoGravity(true);
        //dirVec = dirVec.scale(100);
        if (handIn.name().equals("MAIN_HAND")){
            Vec3 offsetVec = playerIn.getViewVector(0).cross(upVec);
            offsetVec = offsetVec.normalize();
            offsetVec = offsetVec.scale(.3);
            spawnVec = spawnVec.add(offsetVec);
        } else{
            Vec3 offsetVec = playerIn.getViewVector(0).cross(upVec);
            offsetVec = offsetVec.normalize();
            offsetVec = offsetVec.scale(-.3);
            spawnVec = spawnVec.add(offsetVec);
        }
        BlockPos spawnPos = new BlockPos(spawnVec);
        Utils.spawnEntity(level,arrow,spawnPos);


        return InteractionResultHolder.pass(itemstack);
    }
}