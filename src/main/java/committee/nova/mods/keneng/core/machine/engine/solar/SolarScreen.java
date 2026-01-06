package committee.nova.mods.keneng.core.machine.engine.solar;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import committee.nova.mods.keneng.lib.client.element.ElementBurnLeft;
import committee.nova.mods.keneng.lib.tile.CmContainerMachine;
import committee.nova.mods.keneng.lib.tile.CmScreenMachine;

public class SolarScreen extends CmScreenMachine
{

    public SolarScreen(CmContainerMachine screenContainer, Inventory inv, Component titleIn)
    {

        super(screenContainer, inv, titleIn, "textures/gui/engine_solar.png", 256, 256);
        xSize = 176;
        ySize = 166;

    }

    ElementBurnLeft energy;
    ElementBurnLeft left;

    public void addWidgets()
    {

        super.addWidgets();

        widgets.add(energy = new ElementBurnLeft(80, 12, 14, 46, 0, 0, handler, true));
        widgets.add(left = new ElementBurnLeft(81, 64, 13, 13, 14, 52, handler));

    }

    public void update()
    {

        energy.setPer(pEnergy());
        energy.setValue(energy(), maxEnergy());
        left.setPer(pFuel());//only 1 or 0!

    }

}
