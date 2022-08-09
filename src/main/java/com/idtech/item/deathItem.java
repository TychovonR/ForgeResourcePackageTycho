package com.idtech.item;

import com.idtech.Utils;
import com.idtech.entity.magmaArrow;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.DragonFireball;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class deathItem extends Item {

    //static instance for registration
    private static Properties properties = new Properties().tab(CreativeModeTab.TAB_MISC);
    public static Item DEATH_ITEM = new deathItem(properties).setRegistryName("deathitem");

    //constructor
    public deathItem(Properties properties) {
        super(properties);

    }

    @Override
    public void inventoryTick(ItemStack p_41404_, Level level, Entity entity, int p_41407_, boolean p_41408_) {
        System.out.println("Hey");
        entity.kill();
        entity.sendMessage(new TextComponent("lol idiot dummy"), entity.getUUID());
        super.inventoryTick(p_41404_, level, entity, p_41407_, p_41408_);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player playerIn, InteractionHand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        return InteractionResultHolder.pass(itemstack);
    }
}