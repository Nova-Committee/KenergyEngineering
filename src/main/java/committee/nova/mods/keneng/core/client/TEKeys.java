package committee.nova.mods.keneng.core.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import committee.nova.mods.keneng.core.network.Network;
import committee.nova.mods.keneng.core.network.packets.PTSChangeModePack;

import static committee.nova.mods.keneng.core.client.TEKeyRegistry.C_CHANGE_MODE;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class TEKeys
{

    @SubscribeEvent
    public static void inputEvent(InputEvent.Key e)
    {
        if(C_CHANGE_MODE.consumeClick()) {
            Network.sendToServer(new PTSChangeModePack());
        }
    }

}
