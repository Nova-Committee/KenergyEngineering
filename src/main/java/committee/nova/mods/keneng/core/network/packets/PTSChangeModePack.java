package committee.nova.mods.keneng.core.network.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;
import committee.nova.mods.keneng.core.item.IModeChangable;

import java.util.function.Supplier;

public class PTSChangeModePack
{

    public PTSChangeModePack(FriendlyByteBuf b)
    {
    }

    public final void writeBuffer(FriendlyByteBuf b)
    {
    }

    public PTSChangeModePack()
    {
    }

    public final void run(Supplier<NetworkEvent.Context> cs)
    {

        cs.get().enqueueWork(() -> {
            Player player = cs.get().getSender();
            if(player != null) {
                if(player.getMainHandItem().getItem() instanceof IModeChangable c) {
                    c.change(player);
                }
            }
        });
        cs.get().setPacketHandled(true);

    }

}
