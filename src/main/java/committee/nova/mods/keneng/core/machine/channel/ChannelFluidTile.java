package committee.nova.mods.keneng.core.machine.channel;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import committee.nova.mods.keneng.lib.capability.fluid.Tank;
import committee.nova.mods.keneng.lib.capability.fluid.TankArray;
import committee.nova.mods.keneng.lib.tile.mac.IngredientType;

public class ChannelFluidTile extends ChannelTile
{

    public ChannelFluidTile(BlockPos pos, BlockState state)
    {
        super(pos, state);
        info.setCap(0, 0, 1000);

        addTank(new Tank(2000));
        addTank(new Tank(2000));
    }

    public IngredientType tankType(int tank)
    {
        return IngredientType.BOTH;
    }

    public boolean valid(int slot, FluidStack stack)
    {
        return true;
    }

    public void update()
    {
        doBaseData();
        reflection.setActive(false);
        if(!signalAllowRun()) {
            return;
        }

        ftr.transferFluid();

        if(outputs.size() > 0) {
            reflection.setActive(true);
            BlockPos pos = outputs.get(nowOutputIndex);
            ftr.transferTo(pos, null, info.maxExtractFluid);
            if(!isPosSame(pos)) {
                spiltOut(pos);
            }
        }
        if(inputs.size() > 0) {
            reflection.setActive(true);
            BlockPos pos = inputs.get(nowInputIndex);
            ftr.transferFrom(inputs.get(nowInputIndex), null, info.maxReceiveFluid);
            if(!isPosSame(pos)) {
                spiltIn(pos);
            }
        }

        cycle();
    }

    public boolean isPosSame(BlockPos pos)
    {
        return (level.getBlockEntity(pos) instanceof ChannelFluidTile);
    }

    protected boolean hasFaceCapability(Capability<?> cap, Direction d)
    {
        return cap == ForgeCapabilities.FLUID_HANDLER &&
                (d == reflection.direction() || d == null);
    }

    IFluidHandler handler;

    public void initHandlers()
    {
        handler = new TankArray(null, this);
    }

    public LazyOptional<IFluidHandler> getFluidHandler(Direction d)
    {
        return LazyOptional.of(() -> handler);
    }

}
