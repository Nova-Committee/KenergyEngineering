package committee.nova.mods.keneng.core.network.packets;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.entity.BlockEntity;
import committee.nova.mods.keneng.lib.tile.mac.CmTileMachine;

public class PTSRedStatePack extends ServerIntSetterPacket
{

    public PTSRedStatePack(FriendlyByteBuf b)
    {
        super(b);
    }

    public PTSRedStatePack(int mode, BlockPos pos)
    {
        super(mode, pos);
    }

    @Override
    protected void run(BlockEntity tile)
    {
        ((CmTileMachine) tile).data.set(CmTileMachine.RED_MODE, value);
    }

}
