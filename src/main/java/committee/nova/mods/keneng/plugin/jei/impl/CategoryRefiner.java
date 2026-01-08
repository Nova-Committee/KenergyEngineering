package committee.nova.mods.keneng.plugin.jei.impl;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.recipe.RecipeType;
import committee.nova.mods.keneng.Const;
import committee.nova.mods.keneng.lib.client.element.ElementBurnLeft;
import committee.nova.mods.keneng.lib.client.element.ElementProgress;
import committee.nova.mods.keneng.lib.recipe.FormsCombinedRecipe;
import committee.nova.mods.keneng.plugin.jei.JeiCmCategory;
import committee.nova.mods.keneng.plugin.jei.TEJeiPlugins;

import static committee.nova.mods.keneng.lib.tile.CmScreen.handler;

public class CategoryRefiner extends JeiCmCategory
{

    ElementBurnLeft energy;
    ElementProgress progress;
    ElementBurnLeft left;

    public CategoryRefiner(RecipeType<FormsCombinedRecipe> name)
    {
        super(name, 0, 51, 170, 54);
    }

    public void init(FormsCombinedRecipe recipe, IRecipeLayoutBuilder bd)
    {
        drawer.setHandler(Const.jeiHandler2);
        drawer.add(progress = new ElementProgress(81, 20, 22, 16, 27, 0, handler));
        drawer.add(energy = new ElementBurnLeft(6, 2, 14, 46, 0, 0, handler));
        drawer.add(left = new ElementBurnLeft(85, 41, 13, 13, 14, 0, handler));
        inputLiquid(34, 2, 0, recipe, bd);
        input(55, 0, 0, recipe, bd);
        input(55, 18, 1, recipe, bd);
        input(55, 36, 2, recipe, bd);
        output(112, 0, 0, recipe, bd);
        output(112, 18, 1, recipe, bd);
        output(112, 36, 2, recipe, bd);
        outputLiquid(134, 2, 0, recipe, bd);
    }

    public void updateInfo(FormsCombinedRecipe recipe)
    {
        dynamic3e(progress, energy, left);
    }

    public RecipeType<FormsCombinedRecipe> getRecipeType()
    {
        return TEJeiPlugins.refiner;
    }

}
