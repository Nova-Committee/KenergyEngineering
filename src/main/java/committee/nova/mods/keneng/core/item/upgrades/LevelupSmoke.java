package committee.nova.mods.keneng.core.item.upgrades;

import committee.nova.mods.keneng.core.machine.useenergy.smelter.FurnaceTile;
import committee.nova.mods.keneng.lib.tile.mac.CmTileMachine;

public class LevelupSmoke extends UpgradeItem
{

    public LevelupSmoke()
    {
        super(0);
    }

    @Override
    public boolean effect(CmTileMachine tile)
    {
        return tile instanceof FurnaceTile;
    }

}
