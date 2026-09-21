package com.neoloxal.controlled_components.block.entity.custom;

import com.simibubi.create.content.kinetics.base.ShaftVisual;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;

public class RotationSensorVisual extends ShaftVisual<RotationSensorBlockEntity> {
    public RotationSensorVisual(VisualizationContext context, RotationSensorBlockEntity blockEntity, float partialTick) {
        super(context, blockEntity, partialTick);
    }
}
