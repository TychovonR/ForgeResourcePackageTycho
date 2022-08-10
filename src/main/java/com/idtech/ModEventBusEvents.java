package com.idtech;
import com.idtech.*;
import com.idtech.event.loot.TNTFromCreeperAdditionModifier;
import com.idtech.world.dimension.ModDimension;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = BaseMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerModifierSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>>
                                                           event) {
        event.getRegistry().registerAll(
                new TNTFromCreeperAdditionModifier.Serializer().setRegistryName
                        (new ResourceLocation(BaseMod.MODID,"tnt_from_creeper")));
        ModDimension.register();
    }
}