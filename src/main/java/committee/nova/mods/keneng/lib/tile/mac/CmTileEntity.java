package committee.nova.mods.keneng.lib.tile.mac;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import committee.nova.mods.keneng.Const;
import committee.nova.mods.keneng.init.BlockInit;
import committee.nova.mods.keneng.init.ContInit;
import committee.nova.mods.keneng.init.TileInit;
import committee.nova.mods.keneng.lib.wrapper.IntArrayCm;
import committee.nova.mods.keneng.util.ComponentHelper;
import committee.nova.mods.keneng.util.SafeOperationHelper;

public abstract class CmTileEntity extends BlockEntity implements MenuProvider
{

    public static CmTileEntity ofType(BlockEntityType<?> type, BlockPos... pos)
    {
        return (CmTileEntity) type.create(
                pos.length > 0 ? pos[0] : BlockPos.ZERO,
                BlockInit.getBlock(SafeOperationHelper.regNameOf(type)).defaultBlockState()
        );
    }

    public IntArrayCm data = ContInit.createDefaultIntArr();
    public Component component;
    public String id;

    boolean init;

    public CmTileEntity(String key, BlockPos pos, BlockState state)
    {
        super(TileInit.getType(key), pos, state);
        component = ComponentHelper.translated(Const.modid + "." + key);
        id = key;
    }

    public void readTileData(CompoundTag nbt)
    {
    }

    public void writeTileData(CompoundTag nbt)
    {
    }

    public void load(CompoundTag nbt)
    {
        readTileData(nbt);
        super.load(nbt);
    }

    public void saveAdditional(CompoundTag compound)
    {
        writeTileData(compound);
        super.saveAdditional(compound);
    }

    boolean loaded;

    @Override
    public void onLoad()
    {
        super.onLoad();
        loaded = true;
    }

    protected int globalTimer = 0;

    public int getTileAliveTime()
    {
        return globalTimer;
    }

    public void serverTick()
    {

        if(level == null) {
            return;
        }

        if(!level.isClientSide()) {
            globalTimer++;
            if(!init) {
                init = true;
                whenPlaceToWorld();
            }
            update();
            endTick();
        }

        updateRemote();

    }

    public void endTick()
    {
    }

    public void whenPlaceToWorld()
    {
    }

    public void updateRemote()
    {
    }

    public void update()
    {
    }

    public Component getDisplayName()
    {
        return component;
    }

}
