package com.idtech.enchantment;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.phys.Vec3;

public class omaewamouenchant extends Enchantment {
    public static Enchantment INSTANCE = new omaewamouenchant(Rarity.UNCOMMON, EnchantmentCategory.WEAPON, EquipmentSlot.values()).setRegistryName("omaewamou");

    @Override
    public int getMaxLevel(){
        return 3;
    }

    public omaewamouenchant(Rarity rarity, EnchantmentCategory pCategory, EquipmentSlot[] slots){
        super(rarity,pCategory,slots);
    }

    @Override
    public void doPostAttack(LivingEntity attacker, Entity target, int level){
        if(!attacker.level.isClientSide()){
            ServerLevel world = ((ServerLevel) attacker.level);
            BlockPos position = target.blockPosition();
            Vec3 viewVec = target.getViewVector(0);
            viewVec = viewVec.normalize();
            viewVec = viewVec.scale(-2f);
            Vec3 vectorP = target.getPosition(0);
            viewVec = viewVec.add(vectorP);
            attacker.setXRot(target.getXRot());
            attacker.setYRot(target.getYRot());
            attacker.moveTo(viewVec);
        }
        super.doPostAttack(attacker,target,level);
    }

}
