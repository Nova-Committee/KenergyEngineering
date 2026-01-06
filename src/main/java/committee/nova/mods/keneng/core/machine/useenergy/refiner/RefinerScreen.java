package committee.nova.mods.keneng.core.machine.useenergy.refiner;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import committee.nova.mods.keneng.lib.client.element.ElementBurnLeft;
import committee.nova.mods.keneng.lib.client.element.ElementFluid;
import committee.nova.mods.keneng.lib.client.element.ElementProgress;
import committee.nova.mods.keneng.lib.tile.CmContainerMachine;
import committee.nova.mods.keneng.lib.tile.CmScreenMachine;

public class RefinerScreen extends CmScreenMachine
{

    public RefinerScreen(CmContainerMachine screenContainer, Inventory inv, Component titleIn)
    {

        super(screenContainer, inv, titleIn, "textures/gui/three_to_three_fluid.png", 256, 256);
        xSize = 176;
        ySize = 166;

    }

    ElementBurnLeft energy;
    ElementBurnLeft left;
    ElementProgress progress;
    ElementFluid fluidi;
    ElementFluid fluido;

    public void addWidgets()
    {

        super.addWidgets();

        widgets.add(energy = getDefaultEne());
        widgets.add(left = new ElementBurnLeft(88, 56, 13, 13, 14, 0, handler));
        widgets.add(progress = new ElementProgress(84, 35, 22, 16, 27, 159, handler, false));
        widgets.add(fluidi = new ElementFluid(37, 17, 18, 50, 0, 92, handler, 0, true));
        widgets.add(fluido = new ElementFluid(137, 17, 18, 50, 0, 92, handler, 1, true));
    }

    public void update()
    {

        fluidi.update(container);
        fluido.update(container);
        energy.setPer(pEnergy());
        energy.setValue(energy(), maxEnergy());
        left.setPer(pEnergy());
        progress.setPer(pProgress());

    }

}
