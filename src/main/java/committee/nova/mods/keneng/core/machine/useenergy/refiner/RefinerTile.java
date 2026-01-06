package committee.nova.mods.keneng.core.machine.useenergy.refiner;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fluids.FluidStack;
import committee.nova.mods.keneng.lib.capability.fluid.Tank;
import committee.nova.mods.keneng.lib.recipe.FormsCombinedRecipe;
import committee.nova.mods.keneng.lib.tile.extension.CmTileMachineRecipe;
import committee.nova.mods.keneng.lib.tile.extension.SlotInfo;
import committee.nova.mods.keneng.lib.tile.mac.IngredientType;
import committee.nova.mods.keneng.lib.wrapper.SlotCm;
import committee.nova.mods.keneng.util.RecipeHelper;

public class RefinerTile extends CmTileMachineRecipe<FormsCombinedRecipe>
{

    public RefinerTile(BlockPos pos, BlockState state)
    {

        super(pos, state, new SlotInfo(0, 2, 3, 5, 0, 0, 1, 1));

        info.setCap(kFE(20));
        setEfficiency(15);

        addTank(new Tank(6000));
        addTank(new Tank(6000));

        addSlot(new SlotCm(this, 0, 58, 15));
        addSlot(new SlotCm(this, 1, 58, 33));
        addSlot(new SlotCm(this, 2, 58, 51));
        addSlot(new SlotCm(this, 3, 115, 15).withIsResultSlot());
        addSlot(new SlotCm(this, 4, 115, 33).withIsResultSlot());
        addSlot(new SlotCm(this, 5, 115, 51).withIsResultSlot());
    }

    public boolean customFitStackIn(ItemStack s, int slot)
    {
        var o1 = RecipeHelper.getRecipeUsing(level, recipeType, inventory.getItem(0));
        var o2 = RecipeHelper.getRecipeUsing(o1, inventory.getItem(1));
        var o3 = RecipeHelper.getRecipeUsing(o2, inventory.getItem(2));
        return !RecipeHelper.getRecipeUsing(o3, s).isEmpty()
                && RecipeHelper.checkItemNotExistInTile(this, s);
    }

    public boolean customFitStackIn(FluidStack s, int tank)
    {
        var o1 = RecipeHelper.getRecipeUsing(level, recipeType, tanks.get(0).getFluid());
        var o2 = RecipeHelper.getRecipeUsing(o1, s);
        return !o2.isEmpty();
    }

    public int inventorySize()
    {
        return 6;
    }

    public IngredientType slotType(int slot)
    {
        if(slot == 0 || slot == 1 || slot == 2) {
            return IngredientType.INPUT;
        }
        else if(slot <= 5) {
            return IngredientType.OUTPUT;
        }
        return IngredientType.IGNORE;
    }

    public boolean valid(int slot, ItemStack stack)
    {
        return true;
    }

    public IngredientType tankType(int tank)
    {
        if(tank == 0) {
            return IngredientType.INPUT;
        }
        if(tank == 1) {
            return IngredientType.OUTPUT;
        }
        return IngredientType.IGNORE;
    }

    public boolean valid(int slot, FluidStack stack)
    {
        return true;
    }

}
