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

public class TeleportEnchantment extends Enchantment {
    public static Enchantment INSTANCE = new TeleportEnchantment(Rarity.UNCOMMON, EnchantmentCategory.WEAPON, EquipmentSlot.values()).setRegistryName("teleport");

    @Override
    public int getMaxLevel(){
        return 3;
    }

    public TeleportEnchantment(Rarity rarity, EnchantmentCategory pCategory, EquipmentSlot[] slots){
        super(rarity,pCategory,slots);
    }

    @Override
    public void doPostAttack(LivingEntity attacker, Entity target, int level){
        if(!attacker.level.isClientSide()){
            ServerLevel world = ((ServerLevel) attacker.level);
            BlockPos position = target.blockPosition();
            Vec3 vector = attacker.getViewVector(0);
            vector = vector.normalize();
            Vec3 cancelY = new Vec3(0,-vector.y(),0);
            vector= vector.add(cancelY);
            vector = vector.scale(0.75f);
            Vec3 vectorP = attacker.getPosition(0);
            vector = vector.add(vectorP);
            attacker.moveTo(vector);
            System.out.println("Hey");
        }
        super.doPostAttack(attacker,target,level);
    }

}
