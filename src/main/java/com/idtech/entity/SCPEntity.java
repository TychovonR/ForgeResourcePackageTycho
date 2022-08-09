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
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
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

import javax.annotation.Nullable;

public class SCPEntity extends Zombie {

    public static EntityType<SCPEntity> TYPE = (EntityType<SCPEntity>)
            EntityType.Builder.of(SCPEntity::new, MobCategory.MONSTER)
                    .build("scp")
                    .setRegistryName(BaseMod.MODID, "scp");

    public static Item EGG = EntityUtils.buildEntitySpawnEgg(TYPE,0xf1c678 ,0xff0000);

    public SCPEntity(EntityType<? extends Zombie> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.FOLLOW_RANGE, 35.0D)
                .add(Attributes.MOVEMENT_SPEED, (double)0.23F)
                .add(Attributes.ATTACK_DAMAGE, 3.0D)
                .add(Attributes.ARMOR, 2.0D);
    }

    @Override
    public boolean isInvisibleTo(Player p_20178_) {
        return super.isInvisibleTo(p_20178_);
    }

    @Nullable
    @Override
    public AttributeInstance getAttribute(Attribute attribute) {
        if (attribute != null){
            return super.getAttribute(attribute);
        }else {
            return super.getAttribute(Attributes.ATTACK_DAMAGE);
        }

    }

    @Override
    public void registerGoals() {
        this.targetSelector.addGoal(0, new FloatGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, false));
        this.targetSelector.addGoal(3, new MeleeAttackGoal(this, 0.8f, false));
        this.targetSelector.addGoal(4, (new HurtByTargetGoal(this)).setAlertOthers(SCPEntity.class));
        this.targetSelector.addGoal(8, new RandomLookAroundGoal(this));
    }

}