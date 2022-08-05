package com.idtech.enchantment;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.phys.Vec3;

public class glowEnchant extends Enchantment {
    public static Enchantment INSTANCE = new glowEnchant(Rarity.UNCOMMON, EnchantmentCategory.WEAPON, EquipmentSlot.values()).setRegistryName("glowenchant");

    @Override
    public int getMaxLevel(){
        return 3;
    }

    public glowEnchant(Rarity rarity, EnchantmentCategory pCategory, EquipmentSlot[] slots){
        super(rarity,pCategory,slots);
    }

    @Override
    public void doPostAttack(LivingEntity attacker, Entity target, int level){

        if(!attacker.level.isClientSide()){
            target.level.addParticle(ParticleTypes.DRAGON_BREATH, target.getRandomX(0.6D), target.getRandomY(), target.getRandomZ(0.6D), 0.0D, 0.0D, 0.0D);
            target.setGlowingTag(true);
        }
        super.doPostAttack(attacker,target,level);
    }

}
