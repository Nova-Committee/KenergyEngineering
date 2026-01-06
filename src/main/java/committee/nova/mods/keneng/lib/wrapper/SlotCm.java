package committee.nova.mods.keneng.lib.wrapper;

import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class SlotCm extends Slot
{

    boolean isRes;
    ISlotAcceptor acceptor;

    public SlotCm(ISlotAcceptor i, int id, int x, int y)
    {

        //bug fixed+1
        super(i.getInv(), id, x + 1, y + 1);
        acceptor = i;
    }

    public SlotCm withIsResultSlot()
    {
        isRes = true;
        return this;
    }

    //check player input, vanilla method(I cannot change it)
    @Override
    public boolean mayPlace(ItemStack stack)
    {
        return isItemValidInHandler(stack) && !isRes;
    }

    //check handler input
    public boolean isItemValidInHandler(ItemStack stack)
    {
        int ind = getSlotIndex();
        return acceptor.valid(ind, stack);
    }

}
