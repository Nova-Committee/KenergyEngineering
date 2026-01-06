package committee.nova.mods.keneng.core.item.energy;

import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import committee.nova.mods.keneng.lib.capability.energy.BatteryItem;
import committee.nova.mods.keneng.util.ItemNBTHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

public class ItemEnergyCapProvider implements ICapabilityProvider
{

    ItemStack stack;

    public ItemEnergyCapProvider(ItemStack s)
    {
        stack = s;
    }

    @Nonnull
    @Override
    @SuppressWarnings("all")
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side)
    {
        if(Objects.equals(cap, ForgeCapabilities.ENERGY)) {
            return (LazyOptional<T>) LazyOptional.of(() -> new BatteryItem(
                    ItemNBTHelper.getTag(stack, "maxEnergy"),
                    ItemNBTHelper.getTag(stack, "receive"),
                    ItemNBTHelper.getTag(stack, "extract"),
                    stack
            ));
        }
        return LazyOptional.empty();
    }

}
