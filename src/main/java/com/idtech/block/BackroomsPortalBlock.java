package com.idtech.block;
import com.idtech.BaseMod;
import com.idtech.world.dimension.ModDimension;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.Cancelable;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.Serializers;

import javax.annotation.Nullable;
import java.util.Properties;
import java.util.Random;
import java.util.function.Function;

public class BackroomsPortalBlock extends Block {
    private static Properties properties = Properties.of(Material.PORTAL).destroyTime(1000f).strength(100f);

    public static Block INSTANCE = new BackroomsPortalBlock(properties).setRegistryName(BaseMod.MODID, "brportal");
    public static Item ITEM = BlockUtils.createBlockItem(INSTANCE, CreativeModeTab.TAB_MISC);

    public BackroomsPortalBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        Level entityWorld = entity.level;
        if (entityWorld != null) {
            MinecraftServer minecraftserver = entityWorld.getServer();
            ResourceKey<Level> destination = entity.level.dimension() == Level.NETHER
                    ? Level.OVERWORLD : Level.NETHER;
            if (minecraftserver != null) {
                ServerLevel destinationWorld = minecraftserver.getLevel(destination);
                System.out.println(destinationWorld.toString());
                if (destinationWorld != null && minecraftserver.isNetherEnabled() && !entity.isPassenger()) {
                    System.out.println("Teleporting");
                    entity.level.getProfiler().push("br_portal");
                    entity.setPortalCooldown();
                    entity.changeDimension(destinationWorld );
                    entity.level.getProfiler().pop();
                }

            }
            super.stepOn(level, pos, state, entity);
        }

    }
}