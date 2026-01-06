package committee.nova.mods.keneng.core.item.upgrades;

import committee.nova.mods.keneng.lib.tile.mac.CmTileMachine;

public class LevelupPower extends UpgradeItem
{

    public LevelupPower()
    {
        super(0.35);
    }

    @Override
    public boolean effect(CmTileMachine tile)
    {
        tile.upgradeSlots.upgSize += 2;
        return super.effect(tile);
    }

}
