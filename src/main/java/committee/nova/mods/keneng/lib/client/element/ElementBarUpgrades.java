package committee.nova.mods.keneng.lib.client.element;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import committee.nova.mods.keneng.util.ComponentHelper;

import java.util.List;

public class ElementBarUpgrades extends ElementBar
{

    public ElementBarUpgrades(int xr, int y, int h, int xOff, int yOff, ResourceLocation resourceLocation)
    {

        super(xr, y, h, xOff, yOff, resourceLocation);

    }

    @Override
    public void onMouseClicked(int mouseX, int mouseY)
    {
        //no act
    }

    @Override
    public boolean checkInstr(int mouseX, int mouseY)
    {
        return mouseX >= x - bw && mouseY >= y && mouseX <= x - bw + barSize && mouseY <= y + barSize;
    }

    @Override
    public void addToolTip(List<Component> tooltips)
    {

        tooltips.add(ComponentHelper.translated(ComponentHelper.GOLD, "keneng.info.bar_upgrade"));

    }

}
