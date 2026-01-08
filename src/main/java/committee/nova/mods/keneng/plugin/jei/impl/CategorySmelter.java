package committee.nova.mods.keneng.plugin.jei.impl;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import committee.nova.mods.keneng.Const;
import committee.nova.mods.keneng.lib.client.element.ElementBurnLeft;
import committee.nova.mods.keneng.lib.client.element.ElementProgress;
import committee.nova.mods.keneng.plugin.jei.JeiVanillaCategory;
import committee.nova.mods.keneng.plugin.jei.TEJeiPlugins;

import static committee.nova.mods.keneng.lib.tile.CmScreen.handler;

public class CategorySmelter extends JeiVanillaCategory<SmeltingRecipe>
{

    ElementBurnLeft energy;
    ElementProgress progress;
    ElementBurnLeft left;

    public CategorySmelter(RecipeType<SmeltingRecipe> name)
    {
        super(name, 0, 0, 150, 50);
    }

    public void init(SmeltingRecipe recipe, IRecipeLayoutBuilder bd)
    {
        drawer.setHandler(Const.jeiHandler1);
        drawer.add(progress = new ElementProgress(73, 19, 22, 16, 27, 0, handler));
        drawer.add(energy = new ElementBurnLeft(6, 2, 14, 46, 0, 0, handler));
        drawer.add(left = new ElementBurnLeft(42, 32, 13, 13, 14, 0, handler));
        input(40, 4, 0, recipe, bd);
        output(112, 18, 0, recipe, bd);
    }

    public void updateInfo(SmeltingRecipe recipe)
    {
        dynamic3e(progress, energy, left);
    }

    public RecipeType<SmeltingRecipe> getRecipeType()
    {
        return TEJeiPlugins.smelter;
    }

}
