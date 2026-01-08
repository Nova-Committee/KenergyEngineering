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

public class CategoryInduction extends JeiCmCategory
{

    ElementBurnLeft energy;
    ElementProgress progress;
    ElementBurnLeft left;

    public CategoryInduction(RecipeType<FormsCombinedRecipe> name)
    {
        super(name, 0, 0, 150, 50);
    }

    public void init(FormsCombinedRecipe recipe, IRecipeLayoutBuilder bd)
    {
        drawer.setHandler(Const.jeiHandler2);
        drawer.add(progress = new ElementProgress(90, 19, 22, 16, 27, 0, handler));
        drawer.add(energy = new ElementBurnLeft(6, 2, 14, 46, 0, 0, handler));
        drawer.add(left = new ElementBurnLeft(51, 32, 13, 13, 14, 0, handler));
        input(30, 4, 0, recipe, bd);
        input(48, 4, 1, recipe, bd);
        input(66, 4, 2, recipe, bd);
        output(124, 18, 0, recipe, bd);
    }

    public void updateInfo(FormsCombinedRecipe recipe)
    {
        dynamic3e(progress, energy, left);
    }

    public RecipeType<FormsCombinedRecipe> getRecipeType()
    {
        return TEJeiPlugins.inductionFurnace;
    }

}
