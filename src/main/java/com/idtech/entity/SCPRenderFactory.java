package com.idtech.entity;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.monster.Zombie;


public class SCPRenderFactory implements EntityRendererProvider<Zombie> {

    public static SCPRenderFactory INSTANCE = new SCPRenderFactory();

    @Override
    public EntityRenderer<Zombie> create(Context manager) {
        return new SCPRenderer(manager);
    }
}