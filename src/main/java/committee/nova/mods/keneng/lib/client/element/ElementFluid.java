package committee.nova.mods.keneng.lib.client.element;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import committee.nova.mods.keneng.lib.tile.CmContainerMachine;
import committee.nova.mods.keneng.util.ComponentHelper;
import committee.nova.mods.keneng.util.DisplayHelper;
import committee.nova.mods.keneng.util.RenderHelper;

import java.util.List;

public class ElementFluid extends ElementBase
{

    double p;
    boolean dv;
    int id;
    FluidStack stack;

    public ElementFluid(int x, int y, int width, int height, int xOff, int yOff, ResourceLocation resourceLocation, int id)
    {

        super(x, y, width, height, xOff, yOff, resourceLocation);
        this.id = id;
    }

    public ElementFluid(int x, int y, int width, int height, int xOff, int yOff, ResourceLocation resourceLocation, int id, boolean displayValue)
    {

        super(x, y, width, height, xOff, yOff, resourceLocation);
        dv = displayValue;
        this.id = id;
    }

    public void update(CmContainerMachine ct)
    {
        Fluid fid = BuiltInRegistries.FLUID.byId(ct.fluidData.get(id));
        int amt = ct.fluidAmount.get(id);
        stack = new FluidStack(fid, amt);
        setValue(amt, ct.tile.tanks.get(id).getCapacity());
        setPer(val / (double) m_val);
    }

    public void update(FluidStack s, int max)
    {
        stack = s.copy();
        setValue(s.getAmount(), max);
        setPer(val / (double) m_val);
    }

    @Override
    public void draw(GuiGraphics matrixStack)
    {

        int h = (int) ((height - 2) * (1 - p));
        RenderHelper.render(matrixStack, x, y, width, height, textureW, textureH, xOff, yOff, resourceLocation);

        if(stack != null && !stack.isEmpty()) {
            RenderHelper.drawFlTil(matrixStack, stack, x + 1, y + 1 + h, width - 2, height - h - 2);
        }
        //RenderHelper.render(matrixStack, x, y, width, height, textureW, textureH, xOff, yOff + height, resourceLocation);
    }

    @Override
    public void addToolTip(List<Component> tooltips)
    {

        if(stack != null && !stack.isEmpty()) {
            tooltips.add(stack.getDisplayName());
        }

        if(!dv) {
            tooltips.add(ComponentHelper.make((int) (p * 100) + "%"));
        }
        else {
            tooltips.add(DisplayHelper.joinmB(val, m_val));
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

}
