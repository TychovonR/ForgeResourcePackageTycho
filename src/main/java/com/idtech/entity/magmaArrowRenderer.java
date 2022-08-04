package com.idtech.entity;

import com.idtech.BaseMod;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.projectile.Arrow;

public class magmaArrowRenderer extends ArrowRenderer {

    public magmaArrowRenderer(EntityRendererProvider.Context provider) {
        super(provider);
    }

    @Override
    public ResourceLocation getTextureLocation(Entity arrow) {
        return new ResourceLocation(BaseMod.MODID, "textures/item/lightningarrow.png");
    }


}