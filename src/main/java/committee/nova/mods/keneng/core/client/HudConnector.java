package committee.nova.mods.keneng.core.client;

import committee.nova.mods.keneng.util.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import committee.nova.mods.keneng.core.item.Connector;
import committee.nova.mods.keneng.util.*;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class HudConnector extends Screen
{

    static int w;
    static int h;

    public HudConnector()
    {
        super(ComponentHelper.make(""));
    }

    public void render(Player player, GuiGraphics s)
    {

        s.pose().pushPose();

        w = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        h = Minecraft.getInstance().getWindow().getGuiScaledHeight();
        init(Minecraft.getInstance(), w, h);//&*&

        Component in = ComponentHelper.translated("ten3.channel_connector.mode.in");
        Component out = ComponentHelper.translated("ten3.channel_connector.mode.out");
        Component rem = ComponentHelper.translated("ten3.channel_connector.mode.rem");
        Component trueVal = ComponentHelper.make("");
        Component pos = ComponentHelper.make("");
        switch(Connector.Modes.parse(player.getMainHandItem())) {
            case IN -> trueVal = in;
            case OUT -> trueVal = out;
            case REMOVE -> trueVal = rem;
            //default -> trueVal = rem;
        }
        if(ItemNBTHelper.getTag(player.getMainHandItem(), "hasLast") == 1) {
            Component i1 = ComponentHelper.make(DisplayHelper.toString(
                    BlockPos.of((long) ItemNBTHelper.getTagD(player.getMainHandItem(), "last"))
            ));
            pos = ComponentHelper.translated("ten3.channel.pointer_last").append(i1);
        }

        int hp = player.isCreative() ? (int) (h / 3 * 2.6) : (int) (h / 3 * 2.42);
        //RenderHelper.render(s, w / 2 - 29, hp - 3, 58, 13, 256, 256, 0, 198, TConst.guiHandler);
        RenderHelper.renderCString(s, w / 2, hp, SafeOperationHelper.safeInt(ComponentHelper.GOLD.getColor()), trueVal);
        // RenderHelper.renderCString(s, w / 2, h / 2 + 8, SafeOperationHelper.safeInt(ComponentHelper.GOLD.getColor()), pos);

        s.pose().popPose();

    }

    @SubscribeEvent
    public static void onRender(RenderGuiOverlayEvent.Post e)
    {

        Player player = Minecraft.getInstance().player;
        if(player == null) {
            return;
        }

        ItemStack i = player.getMainHandItem();
        if(!(i.getItem() instanceof Connector)) {
            return;
        }

        new HudConnector().render(player, e.getGuiGraphics());
    }

}
