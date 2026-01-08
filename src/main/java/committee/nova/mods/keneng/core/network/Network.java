package committee.nova.mods.keneng.core.network;

import committee.nova.mods.keneng.core.network.packets.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import committee.nova.mods.keneng.Const;
import committee.nova.mods.keneng.core.network.check.PTCCheckPack;
import committee.nova.mods.keneng.core.network.check.PTSCheckPack;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class Network
{

    public static SimpleChannel instance;
    static int id;

    public static void register()
    {

        instance = NetworkRegistry.newSimpleChannel
                (
                        new ResourceLocation(Const.modid, "ten3_network_handler"),
                        () -> "1.0",
                        (v) -> true,
                        (v) -> true
                );
        instance.registerMessage(id++, PTSRedStatePack.class, PTSRedStatePack::writeBuffer,
                                 PTSRedStatePack::new, PTSRedStatePack::run
        );
        instance.registerMessage(id++, PTSCheckPack.class, PTSCheckPack::writeBuffer,
                                 PTSCheckPack::new, PTSCheckPack::run
        );
        instance.registerMessage(id++, PTCCheckPack.class, PTCCheckPack::writeBuffer,
                                 PTCCheckPack::new, PTCCheckPack::run
        );
        instance.registerMessage(id++, PTCInfoClientPack.class, PTCInfoClientPack::writeBuffer,
                                 PTCInfoClientPack::new, PTCInfoClientPack::run
        );
        instance.registerMessage(id++, PTSModeTransfPack.class, PTSModeTransfPack::writeBuffer,
                                 PTSModeTransfPack::new, PTSModeTransfPack::run
        );
        instance.registerMessage(id++, PTCInfoChannelPack.class, PTCInfoChannelPack::writeBuffer,
                                 PTCInfoChannelPack::new, PTCInfoChannelPack::run
        );
        instance.registerMessage(id++, PTSChangeModePack.class, PTSChangeModePack::writeBuffer,
                                 PTSChangeModePack::new, PTSChangeModePack::run
        );
    }

    public static void sendToServer(Object o)
    {
        instance.sendToServer(o);
    }

    public static void sendToClient(Object o)
    {
        instance.send(PacketDistributor.ALL.noArg(), o);
    }

    @SubscribeEvent
    public static void doReg(FMLCommonSetupEvent e)
    {

        register();

    }

}
