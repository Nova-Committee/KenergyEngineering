package committee.nova.mods.keneng.core.item.upgrades;

import committee.nova.mods.keneng.lib.tile.mac.CmTileMachine;

public class LevelupKnow extends UpgradeItem
{

    public LevelupKnow()
    {
        super(0);
    }

    @Override
    public boolean effect(CmTileMachine tile)
    {
        tile.upgradeSlots.upgSize = 6;
        return true;
    }
}
