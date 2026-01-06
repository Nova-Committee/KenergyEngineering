package committee.nova.mods.keneng.core.item.upgrades;

import committee.nova.mods.keneng.init.TabInit;
import committee.nova.mods.keneng.init.template.DefItem;
import committee.nova.mods.keneng.lib.tile.mac.CmTileMachine;

public abstract class UpgradeItem extends DefItem
{

    double percent;

    public UpgradeItem(double per)
    {

        super(build(1));
        percent = per;

    }

    public void fillGroup()
    {
        TabInit.TOOLS.add(this::getDefaultInstance);
    }

    public boolean effect(CmTileMachine tile)
    {
        tile.efficientIn += tile.initialEfficientIn * percent;
        tile.info.maxStorageEnergy += tile.info.initialEnergyStorage * percent;
        tile.info.maxReceiveEnergy += tile.info.initialEnergyReceive * percent;
        tile.info.maxExtractEnergy += tile.info.initialEnergyExtract * percent;
        tile.info.maxReceiveItem += tile.info.initialItemReceive * percent;
        tile.info.maxExtractItem += tile.info.initialItemExtract * percent;
        return true;
    }

}
