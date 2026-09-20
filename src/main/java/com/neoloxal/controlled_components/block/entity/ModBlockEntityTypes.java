package com.neoloxal.controlled_components.block;

import com.neoloxal.controlled_components.ControlledComponents;
import com.simibubi.create.content.kinetics.simpleRelays.SimpleKineticBlockEntity;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.BlockEntityEntry;

public class ModBlockEntityTypes {
    private static final CreateRegistrate REGISTRATE = ControlledComponents.REGISTRATE;

    public static final BlockEntityEntry<SimpleKineticBlockEntity> ROTATION_SENSOR = REGISTRATE
            .blockEntity("rotation_sensor", SimpleKineticBlockEntity::new)
            .visual(() -> ClutchV)
}
