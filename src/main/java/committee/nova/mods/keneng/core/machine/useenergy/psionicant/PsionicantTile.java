package committee.nova.mods.keneng.core.machine.useenergy.psionicant;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fluids.FluidStack;
import committee.nova.mods.keneng.lib.recipe.FormsCombinedRecipe;
import committee.nova.mods.keneng.lib.recipe.IBaseRecipeCm;
import committee.nova.mods.keneng.lib.tile.extension.CmTileMachineRecipe;
import committee.nova.mods.keneng.lib.tile.extension.SlotInfo;
import committee.nova.mods.keneng.lib.tile.mac.IngredientType;
import committee.nova.mods.keneng.lib.wrapper.SlotCm;
import committee.nova.mods.keneng.util.RecipeHelper;

public class PsionicantTile extends CmTileMachineRecipe<FormsCombinedRecipe>
{

    public PsionicantTile(BlockPos pos, BlockState state)
    {

        super(pos, state, new SlotInfo(0, 1, 2, 2, 0, 0, 0, 0));

        info.setCap(kFE(20));
        setEfficiency(50);

        addSlot(new SlotCm(this, 0, 34, 20));
        addSlot(new SlotCm(this, 1, 52, 20));
        addSlot(new SlotCm(this, 2, 115, 34).withIsResultSlot());
    }

    public int inventorySize()
    {
        return 3;
    }

    public IngredientType slotType(int slot)
    {
        if(slot == 0 || slot == 1) {
            return IngredientType.INPUT;
        }
        if(slot == 2) {
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
        return IngredientType.OUTPUT;
    }

    public boolean valid(int slot, FluidStack stack)
    {
        return true;
    }

    @Override
    public boolean customFitStackIn(ItemStack s, int slot)
    {
        var o1 = RecipeHelper.getRecipeUsing(level, recipeType, inventory.getItem(0));
        var o2 = RecipeHelper.getRecipeUsing(o1, inventory.getItem(1));
        return !RecipeHelper.getRecipeUsing(o2, s).isEmpty()
                && RecipeHelper.checkItemNotExistInTile(this, s);
    }

    @Override
    public int ticks()
    {
        return ((IBaseRecipeCm<?>) recipeNow).time();
    }

}
