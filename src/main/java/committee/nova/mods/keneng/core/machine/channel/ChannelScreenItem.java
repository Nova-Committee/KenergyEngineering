package committee.nova.mods.keneng.core.machine.channel;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import committee.nova.mods.keneng.lib.tile.CmContainerMachine;

public class ChannelScreenItem extends ChannelScreen
{

    public ChannelScreenItem(CmContainerMachine screenContainer, Inventory inv, Component titleIn)
    {
        super(screenContainer, inv, titleIn, "textures/gui/channel_item.png");
    }

}
