package com.neoloxal.controlled_components.item;

import com.neoloxal.controlled_components.ControlledComponents;
import com.simibubi.create.AllItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTab {
    private static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ControlledComponents.MODID);

    public static final Supplier<CreativeModeTab> CONTROLLED_COMPONENTS = CREATIVE_MODE_TABS.register("controlled_components", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(AllItems.WRENCH.get()))
            .title(Component.translatable("itemGroup.controlled_components.controlled_components"))
            .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
