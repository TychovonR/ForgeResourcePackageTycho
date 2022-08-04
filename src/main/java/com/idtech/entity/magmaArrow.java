package com.idtech.entity;

import com.idtech.BaseMod;
import com.idtech.item.TestItem;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.LavaCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.LavaFluid;
import net.minecraft.world.phys.BlockHitResult;

public class magmaArrow extends Arrow {
    public static EntityType<magmaArrow> TYPE = (EntityType<magmaArrow>)
            EntityType.Builder.of(magmaArrow::new, MobCategory.MISC)
                    .build("magmaarrow")
                    .setRegistryName(BaseMod.MODID, "magmaarrow");

    public magmaArrow(EntityType<? extends Arrow> type, Level level) {
        super(type, level);
        this.setPierceLevel((byte)120);
        this.setSecondsOnFire(100);
        this.setGlowingTag(true);
    }
    @Override
    public void onHitBlock(BlockHitResult block) {
        BlockPos pos = block.getBlockPos();
        BlockState state = getBlockStateOn();
        Fluid fluid = new LavaFluid.Flowing();
        BlockState lava = fluid.defaultFluidState().createLegacyBlock();
        System.out.println(state.toString());
        level.setBlock(pos, state, 0);
        level.setBlock(pos, lava, 0);

    }


}
