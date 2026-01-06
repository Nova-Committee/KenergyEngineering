package committee.nova.mods.keneng.core.client;

import committee.nova.mods.keneng.util.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import committee.nova.mods.keneng.core.item.Spanner;
import committee.nova.mods.keneng.core.machine.pole.PoleTile;
import committee.nova.mods.keneng.lib.tile.extension.CmTileMachineRadiused;
import committee.nova.mods.keneng.lib.tile.mac.CmTileMachine;
import committee.nova.mods.keneng.lib.tile.option.FaceOption;
import committee.nova.mods.keneng.lib.tile.option.RedstoneMode;
import committee.nova.mods.keneng.util.*;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class HudSpanner extends Screen
{

    static int w;
    static int h;

    public HudSpanner()
    {
        super(ComponentHelper.make(""));
    }

    public void render(boolean catchIt, Player player, GuiGraphics s, BlockPos pos, BlockEntity t, Direction d)
    {

        s.pose().pushPose();

        w = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        h = Minecraft.getInstance().getWindow().getGuiScaledHeight();
        init(Minecraft.getInstance(), w, h);//&*&

        Component tc = ComponentHelper.translated("keneng.info.spanner.mode", "keneng.info.mode." + ItemNBTHelper.getTag(player.getMainHandItem(), "mode"));

        int hp = player.isCreative() ? (int) (h / 3 * 2.6) : (int) (h / 3 * 2.42);
        //RenderHelper.render(s, w / 2 - 29, hp - 3, 58, 13, 256, 256, 0, 198, TConst.guiHandler);
        RenderHelper.renderCString(s, w / 2, hp, SafeOperationHelper.safeInt(ComponentHelper.GOLD.getColor()), tc);

        if(!catchIt) {
            return;
        }

        MutableComponent c1 = ComponentHelper.translated("keneng.info.spanner.dire.energy");
        MutableComponent c2 = ComponentHelper.translated("keneng.info.spanner.dire.item");
        MutableComponent c25 = ComponentHelper.translated("keneng.info.spanner.dire.fluid");
        MutableComponent c3 = ComponentHelper.translated("keneng.info.spanner.dire.redstone");
        MutableComponent c4 = ComponentHelper.translated("keneng.info.spanner.work_radius")
                .append(ComponentHelper.make(
                        String.valueOf(SafeOperationHelper.safeInt(ClientHolder.radius.get(pos)))
                ));
        MutableComponent c5 = ComponentHelper.translated("keneng.info.spanner.bind_pos")
                .append(ComponentHelper.make(
                        String.valueOf(ClientHolder.binds.get(pos))
                ));
        MutableComponent c0 = ((CmTileMachine) t).getDisplayWith()
                .append(ComponentHelper.make(" ("))
                .append(ComponentHelper.translated("dire." + d.getSerializedName()))
                .append(ComponentHelper.make(")"));

        int red = ClientHolder.redstone.get(pos);

        int di = DirectionHelper.direToInt(d);

        c1.append(ComponentHelper.translated("keneng.info." + FaceOption.toStr(ClientHolder.energy.getOrFill(pos, 6).get(di))));
        c2.append(ComponentHelper.translated("keneng.info." + FaceOption.toStr(ClientHolder.item.getOrFill(pos, 6).get(di))));
        c25.append(ComponentHelper.translated("keneng.info." + FaceOption.toStr(ClientHolder.fluid.getOrFill(pos, 6).get(di))));

        if(red == RedstoneMode.LOW) {
            c3.append(ComponentHelper.translated("keneng.info.low"));
        }
        else if(red == RedstoneMode.HIGH) {
            c3.append(ComponentHelper.translated("keneng.info.high"));
        }
        else {
            c3.append(ComponentHelper.translated("keneng.info.off"));
        }

        int x = w / 2;
        int y = h / 2 + h / 10;

        List<MutableComponent> components = new ArrayList<>();
        components.add(c0);
        components.add(c1);
        components.add(c2);
        components.add(c3);
        if(t instanceof CmTileMachineRadiused) {
            components.add(c4);
        }
        if(t instanceof PoleTile && ClientHolder.binds.get(pos) != null) {
            components.add(c5);
        }

        //renderComponentTooltip(s, components, x, y, Minecraft.getInstance().font);

        s.pose().popPose();

    }

    @SubscribeEvent
    public static void onRender(RenderGuiOverlayEvent e)
    {

        Player player = Minecraft.getInstance().player;
        if(player == null) {
            return;
        }

        ItemStack i = player.getMainHandItem();
        if(!(i.getItem() instanceof Spanner)) {
            return;
        }

        Level world = player.level();
        HitResult result = Minecraft.getInstance().hitResult;
        if(result instanceof BlockHitResult r) {
            Direction d = r.getDirection();
            BlockPos hitPos = r.getBlockPos();
            BlockEntity t = world.getBlockEntity(hitPos);
            new HudSpanner().render(t instanceof CmTileMachine, player, e.getGuiGraphics(), hitPos, t, d);
            if(Minecraft.getInstance().isPaused()) {
                return;
            }
        }

    }

}
