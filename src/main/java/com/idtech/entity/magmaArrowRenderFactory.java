package com.idtech.entity;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.minecraft.world.entity.projectile.Arrow;


public class magmaArrowRenderFactory implements EntityRendererProvider<Arrow> {

    public static magmaArrowRenderFactory INSTANCE = new magmaArrowRenderFactory();

    @Override
    public EntityRenderer<Arrow> create(Context manager) {
        return new magmaArrowRenderer(manager);
    }
}