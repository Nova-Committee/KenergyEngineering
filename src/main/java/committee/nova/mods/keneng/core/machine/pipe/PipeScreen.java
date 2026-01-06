package committee.nova.mods.keneng.core.machine.pipe;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import committee.nova.mods.keneng.lib.tile.CmContainerMachine;
import committee.nova.mods.keneng.lib.tile.CmScreen;

public class PipeScreen extends CmScreen<CmContainerMachine>
{

    public PipeScreen(CmContainerMachine screenContainer, Inventory inv, Component titleIn)
    {

        super(screenContainer, inv, titleIn, "textures/gui/pipe.png", 256, 256);
        xSize = 176;
        ySize = 166;

    }

}
