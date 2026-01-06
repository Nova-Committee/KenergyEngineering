package committee.nova.mods.keneng.core.block.mac;

import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.MapColor;

public class Machine4 extends MachineN
{

    public static DirectionProperty direction = DirectionProperty.create("facing", (direction) -> direction != Direction.UP && direction != Direction.DOWN);

    public Machine4(String name)
    {
        this(MapColor.METAL, SoundType.STONE, name, true);
    }

    public Machine4(MapColor m, SoundType s, String name, boolean solid)
    {
        super(m, s, name, solid);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        super.createBlockStateDefinition(builder);
        builder.add(direction);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context)
    {
        return defaultBlockState()
                .setValue(direction, context.getHorizontalDirection().getOpposite())
                .setValue(active, false);
    }

}
