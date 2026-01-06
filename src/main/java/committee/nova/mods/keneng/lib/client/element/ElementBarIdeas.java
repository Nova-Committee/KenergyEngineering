package committee.nova.mods.keneng.lib.client.element;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import committee.nova.mods.keneng.util.ComponentHelper;

import java.util.ArrayList;
import java.util.List;

public class ElementBarIdeas extends ElementImage
{

    List<Component> list = new ArrayList<>();

    public ElementBarIdeas(int xr, int y, int w, int h, int xOff, int yOff, ResourceLocation resourceLocation, String key)
    {

        super(xr, y, w, h, xOff, yOff, resourceLocation);

        list.add(ComponentHelper.translated(ComponentHelper.GOLD, "keneng.info.bar_ideas"));

        for(int i = 0; true; i++) {
            String k = "keneng.info." + ComponentHelper.exceptMachineOrGiveCell(key) + "." + i;
            Component ttc = ComponentHelper.translated(k);
            if(ttc.getString().equals(k)) {
                break;
            }

            list.add(ttc);
        }

    }

    @Override
    public void onMouseClicked(int mouseX, int mouseY)
    {
        //no act
    }

    @Override
    public void addToolTip(List<Component> tooltips)
    {

        tooltips.addAll(list);

    }

}
