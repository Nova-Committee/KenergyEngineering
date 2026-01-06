package committee.nova.mods.keneng.lib.capability.net;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import committee.nova.mods.keneng.core.machine.pipe.PipeTile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Net<T>
{

    public List<T> elements = new ArrayList<>();

    public Set<BlockEntity> netPoses = new HashSet<>();

    public int getCap(Finder.GetCap getCap)
    {
        int ret = Integer.MAX_VALUE;

        for(BlockEntity e : netPoses) {
            ret = Math.min(getCap.get(e), ret);
        }
        return ret;
    }

    public boolean isItemValid(ItemStack stack)
    {

        boolean ret = true;

        for(BlockEntity e : netPoses) {
            if(e instanceof PipeTile) {
                if(!((PipeTile) e).isItemCanBeTransferred(stack)) {
                    ret = false;
                }
            }
        }
        return ret;

    }

}
