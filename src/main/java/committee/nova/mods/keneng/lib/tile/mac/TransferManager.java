package committee.nova.mods.keneng.lib.tile.mac;

import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import committee.nova.mods.keneng.lib.tile.option.FaceOption;
import committee.nova.mods.keneng.lib.tile.option.Type;
import committee.nova.mods.keneng.lib.wrapper.IntArrayCm;
import committee.nova.mods.keneng.util.DirectionHelper;

public class TransferManager
{

    CmTileMachine tile;

    public int maxReceiveEnergy;
    public int maxExtractEnergy;
    public int maxReceiveItem;
    public int maxExtractItem;
    public int maxReceiveFluid;
    public int maxExtractFluid;
    public int maxStorageEnergy;

    public int initialEnergyStorage;//initial vars won't be change!
    public int initialEnergyReceive;//initial vars won't be change!
    public int initialEnergyExtract;//initial vars won't be change!
    public int initialItemReceive;
    public int initialItemExtract;
    public int initialFluidReceive;
    public int initialFluidExtract;

    public IntArrayCm energyAllow = new IntArrayCm(6);
    public IntArrayCm itemAllow = new IntArrayCm(6);
    public IntArrayCm fluidAllow = new IntArrayCm(6);

    public TransferManager(CmTileMachine t)
    {
        tile = t;
    }

    public void setCap(int store, int itm, int fld)
    {

        initialEnergyReceive = maxReceiveEnergy = tile.typeOf() == Type.NON_MAC ? store : store / 200;
        initialEnergyExtract = maxExtractEnergy = tile.typeOf() == Type.NON_MAC ? store : store / 200;
        initialEnergyStorage = maxStorageEnergy = store;
        initialItemExtract = maxExtractItem = itm;
        initialItemReceive = maxReceiveItem = itm;
        initialFluidExtract = maxExtractFluid = fld;
        initialFluidReceive = maxReceiveFluid = fld;

    }

    public void setCap(int store)
    {
        setCap(store, 8, 100);
    }

    public void resetAll()
    {
        maxExtractItem = initialItemExtract;
        maxReceiveItem = initialItemReceive;
        maxExtractFluid = initialFluidExtract;
        maxReceiveFluid = initialFluidReceive;
        maxExtractEnergy = initialEnergyExtract;
        maxReceiveEnergy = initialEnergyReceive;
        maxStorageEnergy = initialEnergyStorage;//reset all, but data storage them to client!
    }

    public int direCheckFluid(Direction d)
    {
        if(!tile.hasFaceCapability(ForgeCapabilities.FLUID_HANDLER, d)) {
            return FaceOption.NONE;
        }
        if(d == null) {
            return FaceOption.BOTH;
        }
        return fluidAllow.get(DirectionHelper.direToInt(d));
    }

    public void setOpenFluid(Direction d, int mode)
    {
        if(tile.hasFaceCapability(ForgeCapabilities.FLUID_HANDLER, d)) {
            fluidAllow.set(DirectionHelper.direToInt(d), mode);
        }
    }

    public int direCheckItem(Direction d)
    {
        if(!tile.hasFaceCapability(ForgeCapabilities.ITEM_HANDLER, d)) {
            return FaceOption.NONE;
        }
        if(d == null) {
            return FaceOption.BOTH;
        }
        return itemAllow.get(DirectionHelper.direToInt(d));
    }

    public void setOpenItem(Direction d, int mode)
    {
        if(tile.hasFaceCapability(ForgeCapabilities.ITEM_HANDLER, d)) {
            itemAllow.set(DirectionHelper.direToInt(d), mode);
        }
    }

    public int direCheckEnergy(Direction d)
    {
        if(!tile.hasFaceCapability(ForgeCapabilities.ENERGY, d)) {
            return FaceOption.NONE;
        }
        if(d == null) {
            return FaceOption.BOTH;
        }
        return energyAllow.get(DirectionHelper.direToInt(d));
    }

    public void setOpenEnergy(Direction d, int mode)
    {
        if(tile.hasFaceCapability(ForgeCapabilities.ENERGY, d)) {
            energyAllow.set(DirectionHelper.direToInt(d), mode);
        }
    }

}
