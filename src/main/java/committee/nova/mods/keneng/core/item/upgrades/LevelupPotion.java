package committee.nova.mods.keneng.core.item.upgrades;

import committee.nova.mods.keneng.core.machine.useenergy.beacon.BeaconTile;
import committee.nova.mods.keneng.lib.tile.mac.CmTileMachine;

public class LevelupPotion extends UpgradeItem
{

    public LevelupPotion()
    {
        super(0);
    }

    @Override
    public boolean effect(CmTileMachine tile)
    {
        return tile instanceof BeaconTile;
    }
}
