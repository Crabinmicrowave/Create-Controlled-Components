package com.neoloxal.controlled_components;

import com.mojang.logging.LogUtils;
import com.neoloxal.controlled_components.block.ModBlocks;
import com.neoloxal.controlled_components.block.entity.ModBlockEntityTypes;
import com.neoloxal.controlled_components.item.ModCreativeModeTab;
import com.simibubi.create.AllItems;
import com.simibubi.create.foundation.data.CreateRegistrate;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(ControlledComponents.MODID)
public class ControlledComponents {
    public static final String MODID = "controlled_components";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MODID);

    public ControlledComponents(IEventBus modEventBus, ModContainer modContainer) {
        ModCreativeModeTab.register(modEventBus);
        REGISTRATE.defaultCreativeTab("controlled_components", builder -> builder.icon(() -> new ItemStack(AllItems.WRENCH.get())));
        REGISTRATE.registerEventListeners(modEventBus);

        ModBlocks.register();
        ModBlockEntityTypes.register();
    }
}
