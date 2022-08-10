package com.idtech.entity.custom;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.minecraft.world.entity.projectile.Arrow;


public class TestRenderFactory implements EntityRendererProvider<TestEntity> {

    public static TestRenderFactory INSTANCE = new TestRenderFactory();

    @Override
    public EntityRenderer<TestEntity> create(Context manager) {
        return new TestRenderer(manager);
    }
}