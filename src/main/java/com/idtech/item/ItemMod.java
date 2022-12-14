package com.idtech.item;

import com.idtech.block.BackroomsPortalBlock;
import com.idtech.world.dimension.ModDimension;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber
public class ItemMod {

    //BASIC ITEMS
    public static final Item STRUCTURE_GEL = ItemUtils.buildBasicItem("structuregel", CreativeModeTab.TAB_MISC);
    public static final Item TEST_ITEM = ItemUtils.buildBasicItem("testitem", CreativeModeTab.TAB_MISC);
    //FOODS


    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {

        //BASIC ITEMS
        event.getRegistry().register(STRUCTURE_GEL);
        event.getRegistry().register(TestItem.TEST_ITEM);
        event.getRegistry().register(MagmaArrowTest.ARROW_TEST);
        event.getRegistry().register(gunItem.GUN_ITEM);
        event.getRegistry().register(deathItem.DEATH_ITEM);
        event.getRegistry().register(BackroomsPortalBlock.ITEM);

        // ITEMS

        // TOOLS

        // FOOD

        // ARMOR

        //PROJECTILES

    }
}
