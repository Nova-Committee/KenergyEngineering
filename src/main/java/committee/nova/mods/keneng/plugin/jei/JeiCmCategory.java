package committee.nova.mods.keneng.plugin.jei;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotRichTooltipCallback;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.fluids.FluidStack;
import committee.nova.mods.keneng.lib.recipe.FormsCombinedIngredient;
import committee.nova.mods.keneng.lib.recipe.FormsCombinedRecipe;
import committee.nova.mods.keneng.util.ComponentHelper;

import java.util.List;


public abstract class JeiCmCategory extends JeiCategory<FormsCombinedRecipe>
{

    public JeiCmCategory(RecipeType<FormsCombinedRecipe> name, int u, int v, int w, int h)
    {
        super(name, u, v, w, h);
    }

    public Class<FormsCombinedRecipe> getRecipeClass()
    {
        return FormsCombinedRecipe.class;
    }

    public void input(int x, int y, int i, FormsCombinedRecipe recipe, IRecipeLayoutBuilder bd)
    {
        NonNullList<Ingredient> fs = recipe.getIngredients();
        if(fs.size() <= i) return;
        bd.addSlot(RecipeIngredientRole.INPUT, x + 1, y + 1)
                .addIngredients(fs.get(i));
    }

    public void inputLiquid(int x, int y, int i, FormsCombinedRecipe recipe, IRecipeLayoutBuilder bd)
    {
        List<FormsCombinedIngredient> fs = recipe.allInputFluids();
        if(fs.size() <= i) return;
        FormsCombinedIngredient fi = fs.get(i);
        bd.addSlot(RecipeIngredientRole.INPUT, x + 1, y + 1)
                .addIngredients(TEJeiPlugins.FLUID_TYPE, fi.fluidStacks())
                .setFluidRenderer(getCapFluid(fi.fluidStacks()), true, 16, 48);
    }

    private int getCapFluid(List<FluidStack> stacks)
    {
        int max = 0;
        for(FluidStack f : stacks)
        {
            max = Math.max(f.getAmount(), max);
        }
        return max;
    }

    public void output(int x, int y, int i, FormsCombinedRecipe recipe, IRecipeLayoutBuilder bd)
    {
        List<FormsCombinedIngredient> fs = recipe.allOutputItems();
        if(fs.size() <= i) return;
        FormsCombinedIngredient ing = fs.get(i);
        bd.addSlot(RecipeIngredientRole.OUTPUT, x + 1, y + 1)
                .addItemStack(ing.symbolItem())
                .addRichTooltipCallback(callbackChance(ing));
    }

    public void outputLiquid(int x, int y, int i, FormsCombinedRecipe recipe, IRecipeLayoutBuilder bd)
    {
        List<FormsCombinedIngredient> fs = recipe.allOutputFluids();
        if(fs.size() <= i) return;
        FormsCombinedIngredient ing = fs.get(i);
        bd.addSlot(RecipeIngredientRole.OUTPUT, x + 1, y + 1)
                .addIngredient(TEJeiPlugins.FLUID_TYPE, ing.symbolFluid())
                .addRichTooltipCallback(callbackChance(ing))
                .setFluidRenderer(getCapFluid(ing.fluidStacks()), true, 16, 48);;
    }

    private IRecipeSlotRichTooltipCallback callbackChance(FormsCombinedIngredient ing)
    {
        return (r, lst) -> {
            double chance = ing.chance();
            //if(chance >= 1) return;
            lst.add(ComponentHelper.make(((int) (chance * 100)) + "%")
                            .withStyle(ChatFormatting.GOLD));
        };
    }

}
