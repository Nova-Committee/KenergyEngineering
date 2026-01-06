package committee.nova.mods.keneng.lib.client.element;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import committee.nova.mods.keneng.util.ComponentHelper;
import committee.nova.mods.keneng.util.DisplayHelper;
import committee.nova.mods.keneng.util.RenderHelper;

import java.util.List;

public class ElementBurnLeft extends ElementBase
{

    double p;
    boolean dv;

    public ElementBurnLeft(int x, int y, int width, int height, int xOff, int yOff, ResourceLocation resourceLocation)
    {

        super(x, y, width, height, xOff, yOff, resourceLocation);

    }

    public ElementBurnLeft(int x, int y, int width, int height, int xOff, int yOff, ResourceLocation resourceLocation, boolean displayValue)
    {

        super(x, y, width, height, xOff, yOff, resourceLocation);
        dv = displayValue;

    }

    @Override
    public void draw(GuiGraphics matrixStack)
    {

        int h = (int) (height * (1 - p));
        RenderHelper.render(matrixStack, x, y, width, height, textureW, textureH, xOff, yOff, resourceLocation);

        //RenderHelper.bindTexture(resourceLocation);
        RenderHelper.render(matrixStack, x, y + h, width, height - h, textureW, textureH, xOff, yOff + height + (int) (height * (1 - p)), resourceLocation);

    }

    @Override
    public void addToolTip(List<Component> tooltips)
    {

        if(!dv) {
            tooltips.add(ComponentHelper.make((int) (p * 100) + "%"));
        }
        else {
            tooltips.add(DisplayHelper.join(val, m_val));
        }

    }

    int val;
    int m_val;

    public void setValue(int v, int mv)
    {
        val = v;
        m_val = mv;
    }

    public void setPer(double per)
    {
        p = per;
    }

    public double getPer()
    {
        return p;
    }

}
