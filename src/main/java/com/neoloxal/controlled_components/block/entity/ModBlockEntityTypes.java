package com.neoloxal.controlled_components.block.entity;

import com.neoloxal.controlled_components.ControlledComponents;
import com.neoloxal.controlled_components.block.ModBlocks;
import com.neoloxal.controlled_components.block.entity.custom.RotationSensorBlockEntity;
import com.neoloxal.controlled_components.block.entity.custom.RotationSensorVisual;
import com.simibubi.create.content.kinetics.base.ShaftRenderer;
import com.simibubi.create.content.kinetics.base.ShaftVisual;
import com.simibubi.create.content.kinetics.base.SingleAxisRotatingVisual;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.BlockEntityEntry;

public class ModBlockEntityTypes {
    private static final CreateRegistrate REGISTRATE = ControlledComponents.REGISTRATE;

    public static final BlockEntityEntry<RotationSensorBlockEntity> ROTATION_SENSOR = REGISTRATE
            .blockEntity("rotation_sensor", RotationSensorBlockEntity::new)
            .visual(() -> RotationSensorVisual::new, false)
            .validBlocks(ModBlocks.ROTATION_SENSOR)
            .renderer(() -> ShaftRenderer::new)
            .register();

    public static void register() {}
}
