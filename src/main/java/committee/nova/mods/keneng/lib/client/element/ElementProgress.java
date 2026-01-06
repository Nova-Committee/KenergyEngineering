package committee.nova.mods.keneng.lib.client.element;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import committee.nova.mods.keneng.lib.tile.CmContainerMachine;
import committee.nova.mods.keneng.lib.wrapper.IntArrayCm;
import committee.nova.mods.keneng.util.ComponentHelper;
import committee.nova.mods.keneng.util.RenderHelper;

import java.util.List;

public class ElementProgress extends ElementBase
{

    double p;
    boolean dv;

    public ElementProgress(int x, int y, int width, int height, int xOff, int yOff, ResourceLocation resourceLocation)
    {

        super(x, y, width, height, xOff, yOff, resourceLocation);

    }

    public ElementProgress(int x, int y, int width, int height, int xOff, int yOff, ResourceLocation resourceLocation, boolean display)
    {

        super(x, y, width, height, xOff, yOff, resourceLocation);
        dv = display;
    }

    @Override
    public void draw(GuiGraphics matrixStack)
    {

        RenderHelper.render(matrixStack, x, y, width, height, textureW, textureH, xOff, yOff, resourceLocation);

        RenderHelper.bindTexture(resourceLocation);
        RenderHelper.render(matrixStack, x, y, (int) (p * width), height, textureW, textureH, xOff, yOff + height, resourceLocation);

    }

    @Override
    public void addToolTip(List<Component> tooltips)
    {
        if(dv) {
            tooltips.add(ComponentHelper.make((int) (p * 100) + "%"));
        }
    }

    int ie;
    int je;

    public void setEs(int i1, int i2, CmContainerMachine c)
    {

        IntArrayCm ia = c.data;
        ie = ia.get(i1);
        je = ia.get(i2);

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
