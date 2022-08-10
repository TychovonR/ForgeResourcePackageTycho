package com.idtech.world.dimension;

import com.idtech.BaseMod;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.Serializers;


public class ModDimension {
    public static final ResourceKey<Level> BACKROOMS_KEY = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(BaseMod.MODID,"backroomsdimm"));

    public static final ResourceKey<DimensionType> BACKROOMS_TYPE = ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, BACKROOMS_KEY.getRegistryName());

    public static void register(){
        System.out.println("registering");
    }
}
