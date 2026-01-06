package committee.nova.mods.keneng.init.template;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.capability.wrappers.FluidBucketWrapper;
import committee.nova.mods.keneng.core.item.ICanFillGroup;
import committee.nova.mods.keneng.init.FluidInit;
import committee.nova.mods.keneng.init.TabInit;
import committee.nova.mods.keneng.util.ComponentHelper;
import committee.nova.mods.keneng.util.SafeOperationHelper;

import javax.annotation.Nullable;

import static committee.nova.mods.keneng.init.template.DefItem.build;

public class Bucket extends BucketItem implements ICanFillGroup
{

    public Bucket(String id)
    {
        super(() -> FluidInit.getSource(id), build(1).craftRemainder(Items.BUCKET));
    }

    public void fillGroup()
    {
        TabInit.TOOLS.add(this::getDefaultInstance);
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt)
    {
        return new FluidBucketWrapper(stack);
    }

    @Override
    public String getDescriptionId()
    {
        return ComponentHelper.getKey(SafeOperationHelper.regNameOf(this));
    }

}
