package com.idtech.item;

import com.idtech.Utils;
import com.idtech.entity.magmaArrow;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.monster.Giant;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.DragonFireball;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class MagmaArrowTest extends Item {

    //static instance for registration
    private static Properties properties = new Properties().tab(CreativeModeTab.TAB_MISC);
    public static Item ARROW_TEST = new MagmaArrowTest(properties).setRegistryName("magmaarrowtest");

    //constructor
    public MagmaArrowTest(Properties properties) {
        super(properties);

    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player playerIn, InteractionHand handIn) {
        //get the held item for return
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        System.out.println("hey");
        //find the location at the cursor
        BlockPos location = Utils.getBlockAtCursor(playerIn, 200.0d, true);
        BlockPos playerLocation = playerIn.getOnPos();
        magmaArrow magma = new magmaArrow(magmaArrow.TYPE, level);
        //decide the size of the explosion
        Vec3 vector = playerIn.getViewVector(0);
        magma.setDeltaMovement(vector);
        Utils.spawnEntity(level,magma,playerLocation);

        //return pass
        return InteractionResultHolder.pass(itemstack);
    }

}