package committee.nova.mods.keneng.core.machine.useenergy.compressor;

import committee.nova.mods.keneng.init.TabInit;
import committee.nova.mods.keneng.init.template.DefItem;

public class Mould extends DefItem
{

    public Mould()
    {
        super(build(1));
    }

    public void fillGroup()
    {
        TabInit.TOOLS.add(this::getDefaultInstance);
    }
}
