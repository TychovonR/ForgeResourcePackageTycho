package com.idtech.entity.custom;

import com.idtech.BaseMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class TestModel extends AnimatedGeoModel<TestEntity> {

    @Override
    public ResourceLocation getModelLocation(TestEntity object) {
        return new ResourceLocation(BaseMod.MODID,"geo/model.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(TestEntity object) {
        return new ResourceLocation(BaseMod.MODID,"textures/entity/model/model.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(TestEntity animatable) {
        return new ResourceLocation(BaseMod.MODID,"animations/model.animation.json");
    }
}
