package com.idtech;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class eventHandler {
    @SubscribeEvent
    public static void blockBreak(BlockEvent.BreakEvent event) {
        System.out.println("block!");
    }
}
