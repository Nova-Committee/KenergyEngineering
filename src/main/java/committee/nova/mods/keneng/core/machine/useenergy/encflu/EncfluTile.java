package committee.nova.mods.keneng.core.machine.useenergy.encflu;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fluids.FluidStack;
import committee.nova.mods.keneng.init.FluidInit;
import committee.nova.mods.keneng.lib.capability.fluid.Tank;
import committee.nova.mods.keneng.lib.tile.extension.CmTileMachineProcess;
import committee.nova.mods.keneng.lib.tile.mac.IngredientType;
import committee.nova.mods.keneng.lib.wrapper.SlotCm;

import java.util.Map;

public class EncfluTile extends CmTileMachineProcess
{

    public EncfluTile(BlockPos pos, BlockState state)
    {

        super(pos, state);

        info.setCap(kFE(20));
        setEfficiency(100);

        addTank(new Tank(1000));

        addSlot(new SlotCm(this, 0, 43, 15));
        addSlot(new SlotCm(this, 1, 43, 51));
        addSlot(new SlotCm(this, 2, 115, 34).withIsResultSlot());
    }

    public boolean customFitStackIn(ItemStack s, int slot)
    {
        if(slot == 1) {
            return s.isEnchantable() || s.is(Items.BOOK);
        }
        else if(slot == 0) {
            return s.isEnchanted();
        }
        return false;
    }

    public IngredientType slotType(int slot)
    {
        if(slot == 1) {
            return IngredientType.INPUT;
        }
        else if(slot == 0) {
            if(!inventory.getItem(slot).isEnchanted()
                    && !inventory.getItem(slot).isEmpty()) {
                return IngredientType.OUTPUT;
            }
            return IngredientType.INPUT;
        }
        else if(slot == 2) {
            return IngredientType.OUTPUT;
        }
        return IngredientType.IGNORE;
    }

    public boolean valid(int slot, ItemStack stack)
    {
        if(slot == 1) {
            return stack.isEnchantable() || stack.is(Items.BOOK);
        }
        else if(slot == 0) {
            return stack.isEnchanted();
        }
        return false;
    }

    public IngredientType tankType(int tank)
    {
        return IngredientType.OUTPUT;
    }

    public boolean valid(int slot, FluidStack stack)
    {
        return true;
    }

    public int inventorySize()
    {
        return 3;
    }

    public boolean conditionStart()
    {
        ItemStack tool = inventory.getItem(0);
        ItemStack expectedBook = inventory.getItem(1);
        boolean canStart1 =
                (expectedBook.isEnchantable() || expectedBook.is(Items.BOOK))
                        || ftr.selfGive(getXPFluid(), 0, 0, true);
        return tool.isEnchanted() && inventory.getItem(2).isEmpty() && canStart1;
    }

    public void cookEnd()
    {
        ItemStack tool = inventory.getItem(0);
        ItemStack expectedBook = inventory.getItem(1);

        if(expectedBook.is(Items.BOOK)) {
            ItemStack eb;
            inventory.setItem(2, eb = Items.ENCHANTED_BOOK.getDefaultInstance());
            for(Map.Entry<Enchantment, Integer> e : EnchantmentHelper.getEnchantments(tool).entrySet()) {
                EnchantedBookItem.addEnchantment(eb, new EnchantmentInstance(e.getKey(), e.getValue()));
            }
        }
        else if(expectedBook.isEnchantable()) {
            inventory.setItem(2, expectedBook.copy());
            for(Map.Entry<Enchantment, Integer> e : EnchantmentHelper.getEnchantments(tool).entrySet()) {
                inventory.getItem(2).enchant(e.getKey(), e.getValue());
            }
            expectedBook.shrink(1);
        }
        else {
            ftr.selfGive(getXPFluid(), 0, 0, false);
        }
        expectedBook.shrink(1);
        tool.removeTagKey("Enchantments");
        tool.removeTagKey("StoredEnchantments");
    }

    private FluidStack getXPFluid()
    {
        int l = 0;
        Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(inventory.getItem(0));

        for(Map.Entry<Enchantment, Integer> entry : map.entrySet()) {
            Enchantment enchantment = entry.getKey();
            Integer integer = entry.getValue();
            if(!enchantment.isCurse()) {
                l += enchantment.getMinCost(integer);
            }
        }

        return new FluidStack(FluidInit.getSource("liquid_xp"), l);
    }

    @Override
    public int ticks()
    {
        return 800;
    }

}
