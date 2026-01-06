package committee.nova.mods.keneng.core.machine.engine;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import committee.nova.mods.keneng.lib.client.element.ElementBurnLeft;
import committee.nova.mods.keneng.lib.tile.CmContainerMachine;
import committee.nova.mods.keneng.lib.tile.CmScreenMachine;

public class EngineScreen extends CmScreenMachine
{

    public EngineScreen(CmContainerMachine screenContainer, Inventory inv, Component titleIn)
    {

        super(screenContainer, inv, titleIn, "textures/gui/engine.png", 256, 256);
        xSize = 176;
        ySize = 166;

    }

    ElementBurnLeft energy;
    ElementBurnLeft left;

    public void addWidgets()
    {

        super.addWidgets();

        widgets.add(energy = new ElementBurnLeft(117, 22, 14, 46, 0, 0, handler, true));
        widgets.add(left = new ElementBurnLeft(81, 39, 13, 13, 14, 26, handler));

    }

    public void update()
    {

        energy.setPer(pEnergy());
        energy.setValue(energy(), maxEnergy());
        left.setPer(pFuel());

    }

}
