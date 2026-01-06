package committee.nova.mods.keneng.plugin.jei;

import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.gui.drawable.IDrawable;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import committee.nova.mods.keneng.util.RenderHelper;
import committee.nova.mods.keneng.lib.client.element.ElementBase;

import java.util.ArrayList;
import java.util.List;

public class ProcessDraw implements IDrawable {

    int u, v, wi, hi;
    String n;
    List<ElementBase> elements = new ArrayList<>();
    ResourceLocation handler;

    public void setHandler(ResourceLocation rl)
    {
        handler = rl;
    }

    public ProcessDraw(int u, int v, int w, int h, String name)
    {
        wi = w;
        hi = h;
        n = name;
        this.u = u;
        this.v = v;
    }

    public void add(ElementBase e)
    {
        elements.add(e);
    }

    @Override
    public int getWidth()
    {
        return wi;
    }

    @Override
    public int getHeight()
    {
        return hi;
    }

    @Override
    public void draw(@NotNull GuiGraphics guiGraphics, int xOffset, int yOffset) {
        RenderHelper.render(guiGraphics, 0, 0, wi, hi, 256, 256, u, v, handler);
        List<Component> tooltip = new ArrayList<>();
        for(ElementBase e : elements)
        {
            e.update();
            e.draw(guiGraphics);
            e.addToolTip(tooltip);
        }
    }
}
