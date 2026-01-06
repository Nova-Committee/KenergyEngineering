package committee.nova.mods.keneng.core.item.upgrades;

import committee.nova.mods.keneng.core.machine.useenergy.quarry.QuarryTile;
import committee.nova.mods.keneng.lib.tile.mac.CmTileMachine;

public class LevelupMineral extends UpgradeItem
{

    public LevelupMineral()
    {
        super(0);
    }

    public boolean effect(CmTileMachine tile)
    {
        return tile instanceof QuarryTile;
    }
}
