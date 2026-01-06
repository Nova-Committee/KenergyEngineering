package committee.nova.mods.keneng.core.machine.cable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fluids.FluidStack;
import committee.nova.mods.keneng.core.block.CableBased;
import committee.nova.mods.keneng.lib.capability.net.BatteryWayFinding;
import committee.nova.mods.keneng.lib.tile.mac.CmTileMachine;
import committee.nova.mods.keneng.lib.tile.mac.IngredientType;
import committee.nova.mods.keneng.lib.tile.option.Type;

public class CableTile extends CmTileMachine
{

    public int inventorySize()
    {
        return 0;
    }

    public CableTile(BlockPos pos, BlockState state)
    {

        super(pos, state);

    }

    public boolean hasUpgrade()
    {
        return false;
    }

    public boolean hasSideBar()
    {
        return false;
    }

    @Override
    public Type typeOf()
    {
        return Type.NON_MAC;
    }

    public IngredientType slotType(int slot)
    {
        return IngredientType.IGNORE;
    }

    public boolean valid(int slot, ItemStack stack)
    {
        return false;
    }

    public IngredientType tankType(int tank)
    {
        return IngredientType.IGNORE;
    }

    public boolean valid(int slot, FluidStack stack)
    {
        return false;
    }

    public int getCapacity()
    {
        return kFE(1);
    }

    boolean eneWFS;
    int hangtime;

    @Override
    public void update()
    {

        if(getTileAliveTime() % 10 == 0) {
            ((CableBased) getBlockState().getBlock()).update(level, worldPosition);
            BatteryWayFinding.updateNet(this);
        }

        eneWFS = hangtime > 0;
        hangtime--;

        reflection.setActive(eneWFS && signalAllowRun());

    }

    public void hangUp()
    {
        hangtime = 15;
    }

    @Override
    public void initHandlers()
    {
        handlerEnergyNull = new BatteryWayFinding(null, this);
        for(Direction d : Direction.values()) {
            handlerEnergy.put(d, new BatteryWayFinding(d, this));
        }
    }

    @Override
    protected boolean hasFaceCapability(Capability<?> cap, Direction d)
    {
        if(cap != ForgeCapabilities.ENERGY) {
            return false;
        }
        return super.hasFaceCapability(cap, d);
    }

}
