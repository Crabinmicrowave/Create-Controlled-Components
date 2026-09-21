package com.neoloxal.controlled_components.block.entity.custom;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.logging.LogUtils;
import com.neoloxal.controlled_components.block.custom.RotationSensor;
import com.simibubi.create.content.kinetics.motor.CreativeMotorBlock;
import com.simibubi.create.content.kinetics.motor.CreativeMotorBlockEntity;
import com.simibubi.create.content.kinetics.motor.KineticScrollValueBehaviour;
import com.simibubi.create.content.kinetics.simpleRelays.SimpleKineticBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.ValueBoxTransform;
import com.simibubi.create.foundation.blockEntity.behaviour.scrollValue.ScrollValueBehaviour;
import com.simibubi.create.foundation.utility.CreateLang;
import dev.engine_room.flywheel.lib.transform.TransformStack;
import net.createmod.catnip.math.AngleHelper;
import net.createmod.catnip.math.VecHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.slf4j.Logger;

import java.util.List;
import java.util.Map;

public class RotationSensorBlockEntity extends SimpleKineticBlockEntity {
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final int DEFAULT_MIN_SPEED = 16;
    public static final int MAX_SPEED = 256;

    private ScrollValueBehaviour minSpeed;

    public RotationSensorBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
        super.addBehaviours(behaviours);
        int max = MAX_SPEED;
        minSpeed = new ScrollValueBehaviour(Component.translatable("controlled_components.kinetics.rotation_sensor.min_rotation_speed"),
                this, new MinSpeedBox());
        minSpeed.between(0, max);
        minSpeed.value = DEFAULT_MIN_SPEED;
        minSpeed.withCallback(i -> onSpeedChanged(this.speed));
        behaviours.add(minSpeed);
    }

    @Override
    public void onSpeedChanged(float previousSpeed) {
        super.onSpeedChanged(previousSpeed);

        assert level != null;
        if (!level.isClientSide()) {
            BlockState currentBlockState = level.getBlockState(this.worldPosition);

            boolean speedMet = Math.abs(this.speed) >= minSpeed.value;

            if (speedMet != currentBlockState.getValue(RotationSensor.SPEED_MET)) {
                level.setBlock(
                        this.worldPosition,
                        currentBlockState.setValue(RotationSensor.SPEED_MET, speedMet),
                        3
                );
            }
        }
    }

    static class MinSpeedBox extends ValueBoxTransform.Sided {
        @Override
        protected Vec3 getSouthLocation() {
            return VecHelper.voxelSpace(8, 8, 16);
        }

        @Override
        public void rotate(LevelAccessor level, BlockPos pos, BlockState state, PoseStack ms) {
            super.rotate(level, pos, state, ms);
            Direction.Axis facing = state.getValue(RotationSensor.AXIS);
            if (facing == Direction.Axis.Y)
                return;
            if (!Direction.Axis.Y.test(getSide())) {
                return;
            }
            int rotation = facing == Direction.Axis.Z ? 0 : -90;
            Player player = level.getNearestPlayer(pos.getX(), pos.getY(), pos.getZ(), 5, false);
            if (player != null) {
                Map<Direction, Integer> DIRECTION_MAP = Map.of(
                        Direction.NORTH, 0,
                        Direction.EAST, 90,
                        Direction.SOUTH, -180,
                        Direction.WEST, -90
                );
                rotation = DIRECTION_MAP.get(player.getDirection());
                if (getSide() == Direction.DOWN && Direction.Axis.X.test(player.getDirection())) {
                    rotation += 180;
                }
            }
            TransformStack.of(ms).rotateZDegrees(rotation);
        }

        @Override
        protected boolean isSideActive(BlockState state, Direction direction) {
            return !state.getValue(RotationSensor.AXIS).test(direction);
        }
    }
}
