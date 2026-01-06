package committee.nova.mods.keneng.core.item.upgrades;

import committee.nova.mods.keneng.lib.tile.extension.CmTileMachineRadiused;
import committee.nova.mods.keneng.lib.tile.mac.CmTileMachine;

public class LevelupRg extends UpgradeItem
{

    public LevelupRg()
    {
        super(0);
    }

    @Override
    public boolean effect(CmTileMachine tile)
    {
        boolean a = tile instanceof CmTileMachineRadiused;
        if(a) {
            ((CmTileMachineRadiused) tile).radius += ((CmTileMachineRadiused) tile).initialRadius * 0.5;
        }
        return a;
    }

}
