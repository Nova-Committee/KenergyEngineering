package committee.nova.mods.keneng.core.machine.useenergy.quarry;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import committee.nova.mods.keneng.lib.client.element.ElementBurnLeft;
import committee.nova.mods.keneng.lib.client.element.ElementProgress;
import committee.nova.mods.keneng.lib.tile.CmContainerMachine;
import committee.nova.mods.keneng.lib.tile.CmScreenMachine;

public class QuarryScreen extends CmScreenMachine
{

    public QuarryScreen(CmContainerMachine screenContainer, Inventory inv, Component titleIn)
    {

        super(screenContainer, inv, titleIn, "textures/gui/quarry.png", 256, 256);
        xSize = 176;
        ySize = 166;

    }

    ElementBurnLeft energy;
    ElementProgress progress;

    public void addWidgets()
    {

        super.addWidgets();

        widgets.add(energy = getDefaultEne());
        widgets.add(progress = new ElementProgress(48, 73, 80, 5, 97, 0, handler, true));

    }

    public void update()
    {

        energy.setPer(pEnergy());
        energy.setValue(energy(), maxEnergy());
        progress.setPer(pProgress());
    }


}
