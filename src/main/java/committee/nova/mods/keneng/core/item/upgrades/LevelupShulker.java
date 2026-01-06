package committee.nova.mods.keneng.core.item.upgrades;

import committee.nova.mods.keneng.lib.tile.mac.CmTileMachine;

public class LevelupShulker extends UpgradeItem
{

    public LevelupShulker()
    {
        super(0.75);
    }

    @Override
    public boolean effect(CmTileMachine tile)
    {
        tile.upgradeSlots.upgSize += 3;
        return super.effect(tile);
    }

}
