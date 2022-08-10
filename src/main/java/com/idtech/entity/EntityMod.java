package com.idtech.entity;

import com.idtech.entity.custom.TestEntity;
import com.idtech.entity.custom.TestRenderFactory;
import com.idtech.item.MagmaArrowTest;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EntityMod {

    @SubscribeEvent
    public static void registerEntities(final RegistryEvent.Register<EntityType<?>> event){
        event.getRegistry().register(magmaArrow.TYPE);
        event.getRegistry().register(SCPEntity.TYPE);
        event.getRegistry().register(TestEntity.TYPE);



    }
    @SubscribeEvent
    public static void registerEntityEggs(final RegistryEvent.Register<Item> event) {
        event.getRegistry().register(SCPEntity.EGG);

    }
    @SubscribeEvent
    public static void entityRenderers(final EntityRenderersEvent.RegisterRenderers event){
       event.registerEntityRenderer(magmaArrow.TYPE, magmaArrowRenderFactory.INSTANCE);
       event.registerEntityRenderer(SCPEntity.TYPE, SCPRenderFactory.INSTANCE);
       event.registerEntityRenderer(TestEntity.TYPE, TestRenderFactory.INSTANCE);
    }

    // this is different than in 1.16 but everything else is the same
    // I do think this makes more sense than the other way but alas change is usually hard.
    @SubscribeEvent
    public static void onAttributeCreate(EntityAttributeCreationEvent event) {
        event.put(SCPEntity.TYPE, SCPEntity.createAttributes().build());
        event.put(TestEntity.TYPE, TestEntity.createAttributes().build());
    }

}
