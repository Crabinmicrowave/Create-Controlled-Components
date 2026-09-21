package com.neoloxal.controlled_components.block;

import com.neoloxal.controlled_components.ControlledComponents;
import com.neoloxal.controlled_components.block.custom.RotationSensor;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.world.level.block.SoundType;

import static com.simibubi.create.foundation.data.ModelGen.customItemModel;
import static com.simibubi.create.foundation.data.TagGen.axeOrPickaxe;

public class ModBlocks {
    private static final CreateRegistrate REGISTRATE = ControlledComponents.REGISTRATE;

    public static BlockEntry<RotationSensor> ROTATION_SENSOR = REGISTRATE.block("rotation_sensor", RotationSensor::new)
            .initialProperties(SharedProperties::stone)
            .properties(p -> p.sound(SoundType.STONE))
            .transform(axeOrPickaxe())
            .item()
            .transform(customItemModel())
            .register();

    public static void register() {}
}
