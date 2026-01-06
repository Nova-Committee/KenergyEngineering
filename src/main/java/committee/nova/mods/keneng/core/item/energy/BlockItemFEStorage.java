package committee.nova.mods.keneng.core.item.energy;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import committee.nova.mods.keneng.core.block.Cell;
import committee.nova.mods.keneng.init.TabInit;
import committee.nova.mods.keneng.init.TileInit;
import committee.nova.mods.keneng.init.template.DefItemBlock;
import committee.nova.mods.keneng.lib.tile.mac.CmTileEntity;
import committee.nova.mods.keneng.lib.tile.mac.CmTileMachine;
import committee.nova.mods.keneng.util.ItemNBTHelper;

import javax.annotation.Nullable;
import java.util.List;

import static committee.nova.mods.keneng.init.template.DefItem.build;

public class BlockItemFEStorage extends DefItemBlock
{

    //in game item do

    private CmTileMachine getBind()
    {
        return (CmTileMachine) CmTileEntity.ofType(TileInit.getType(BuiltInRegistries.ITEM.getKey(this).getPath()));
    }

    public BlockItemFEStorage(Block b)
    {
        super(b, build(1));
    }

    public void fillGroup()
    {
        TabInit.MACHINES.add(this::getDefaultInstance);

        if(getBlock() instanceof Cell) {
            TabInit.MACHINES.add(() -> {
                CmTileMachine t = getBind();
                ItemStack full = EnergyItemHelper.getState(this, t.info.maxStorageEnergy, t.info.maxReceiveEnergy, t.info.maxExtractEnergy);
                ItemNBTHelper.setTag(full, "energy", t.info.maxStorageEnergy);
                return full;
            });
        }
    }

    @Override
    public Component getName(ItemStack stack)
    {
        CmTileMachine t = getBind();
        return t.getDisplayWith();
    }

    //in whenPlaceToWorld item do(Tab or crafting...)

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt)
    {
        return new ItemEnergyCapProvider(stack);
    }

    @Override
    public ItemStack getDefaultInstance()
    {
        CmTileMachine t = getBind();
        return EnergyItemHelper.getState(this, t.info.maxStorageEnergy, t.info.maxReceiveEnergy, t.info.maxExtractEnergy);
    }

    @Override
    public boolean isBarVisible(ItemStack stack)
    {
        return ItemNBTHelper.getTag(stack, "energy") != 0;
    }

    @Override
    public int getBarWidth(ItemStack stack)
    {
        if(ItemNBTHelper.getTag(stack, "maxEnergy") == 0) {
            return 0;
        }
        return (int) (13 * (ItemNBTHelper.getTag(stack, "energy") / (double) ItemNBTHelper.getTag(stack, "maxEnergy")));
    }

    @Override
    public int getBarColor(ItemStack p_150901_)
    {
        return Mth.color(1f, 0.1f, 0.1f);
    }



    /*
    @Override
    public void fillItemCategory(CreativeModeTab tab, NonNullList<ItemStack> stacks)
    {
        if(allowedIn(tab)) {
            CmTileMachine t = getBind();
            EnergyItemHelper.fillEmpty(this, stacks, t.info.maxStorageEnergy, t.info.maxReceiveEnergy, t.info.maxExtractEnergy);

            if(getBlock() instanceof Cell) {
                EnergyItemHelper.fillFull(this, stacks, t.info.maxStorageEnergy, t.info.maxReceiveEnergy, t.info.maxExtractEnergy);
            }
        }
    }

     */

    @Override
    public void appendHoverText(ItemStack stack, @org.jetbrains.annotations.Nullable Level p_40573_, List<Component> tooltip, TooltipFlag p_40575_)
    {
        EnergyItemHelper.addTooltip(tooltip, stack);
    }

    @Override
    public void onCraftedBy(ItemStack stack, Level p_41448_, Player p_41449_)
    {
        CmTileMachine t = getBind();
        EnergyItemHelper.setState(stack, t.info.maxStorageEnergy, t.info.maxReceiveEnergy, t.info.maxExtractEnergy);
    }

}
