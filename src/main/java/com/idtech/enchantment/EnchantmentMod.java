package com.idtech.enchantment;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EnchantmentMod {

    public static void registerEnchantments(RegistryEvent.Register<Enchantment> event) {
        event.getRegistry().register(TeleportEnchantment.INSTANCE);
        event.getRegistry().register(omaewamouenchant.INSTANCE);
        event.getRegistry().register(glowEnchant.INSTANCE);
    }

}