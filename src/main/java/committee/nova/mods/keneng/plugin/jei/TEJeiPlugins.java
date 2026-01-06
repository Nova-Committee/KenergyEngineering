package committee.nova.mods.keneng.plugin.jei;

import committee.nova.mods.keneng.plugin.jei.impl.*;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.forge.ForgeTypes;
import mezz.jei.api.gui.handlers.IGlobalGuiHandler;
import mezz.jei.api.ingredients.IIngredientType;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.Rect2i;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraftforge.fluids.FluidStack;
import committee.nova.mods.keneng.TConst;
import committee.nova.mods.keneng.core.machine.useenergy.compressor.CompressorScreen;
import committee.nova.mods.keneng.core.machine.useenergy.indfur.IndfurScreen;
import committee.nova.mods.keneng.core.machine.useenergy.psionicant.PsionicantScreen;
import committee.nova.mods.keneng.core.machine.useenergy.pulverizer.PulverizerScreen;
import committee.nova.mods.keneng.core.machine.useenergy.refiner.RefinerScreen;
import committee.nova.mods.keneng.core.machine.useenergy.smelter.FurnaceScreen;
import committee.nova.mods.keneng.lib.recipe.FormsCombinedRecipe;
import committee.nova.mods.keneng.init.ItemInit;
import committee.nova.mods.keneng.init.RecipeInit;
import committee.nova.mods.keneng.lib.tile.CmScreenMachine;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@JeiPlugin
public class TEJeiPlugins implements IModPlugin {

    public static RecipeType<FormsCombinedRecipe> pulverizer = getType(FormsCombinedRecipe.class, "pulverizer");
    public static RecipeType<FormsCombinedRecipe> compressor = getType(FormsCombinedRecipe.class, "compressor");
    public static RecipeType<SmeltingRecipe> smelter = getType(SmeltingRecipe.class, "smelter");
    public static RecipeType<FormsCombinedRecipe> psionicant = getType(FormsCombinedRecipe.class, "psionicant");
    public static RecipeType<FormsCombinedRecipe> inductionFurnace = getType(FormsCombinedRecipe.class, "induction_furnace");
    public static RecipeType<FormsCombinedRecipe> refiner = getType(FormsCombinedRecipe.class, "refiner");

    private static<T> RecipeType<T> getType(Class<T> recipe, String name) {
        return RecipeType.create(TConst.modid, name, recipe);
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration)
    {
        addRecipeSM(registration, smelter);
        addRecipe(registration, psionicant);
        addRecipe(registration, pulverizer);
        addRecipe(registration, compressor);
        addRecipe(registration, inductionFurnace);
        addRecipe(registration, refiner);
    }

    @SuppressWarnings("all")
    private void addRecipe(IRecipeRegistration registration, RecipeType<? extends Recipe<Container>> type) {
        if(Minecraft.getInstance().level == null) return;
        RecipeManager manager = Minecraft.getInstance().level.getRecipeManager();
        net.minecraft.world.item.crafting.RecipeType<? extends Recipe<Container>> typeVl =
                (net.minecraft.world.item.crafting.RecipeType<? extends Recipe<Container>>) RecipeInit.getRcpType(type.getUid().getPath());
        List<Recipe<Container>> lst = (List<Recipe<Container>>) manager.getAllRecipesFor(typeVl);
        registration.addRecipes((RecipeType<Recipe<Container>>) type, lst);
    }

    private void addRecipeSM(IRecipeRegistration registration, RecipeType<SmeltingRecipe> type) {
        if(Minecraft.getInstance().level == null) return;
        RecipeManager manager = Minecraft.getInstance().level.getRecipeManager();
        net.minecraft.world.item.crafting.RecipeType<SmeltingRecipe> typeVl = net.minecraft.world.item.crafting.RecipeType.SMELTING;
        List<SmeltingRecipe> lst = manager.getAllRecipesFor(typeVl);
        registration.addRecipes(type, lst);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration)
    {
        registration.addRecipeCategories(new CategorySmelter(smelter));
        registration.addRecipeCategories(new CategoryPsionicant(psionicant));
        registration.addRecipeCategories(new CategoryCompressor(compressor));
        registration.addRecipeCategories(new CategoryInduction(inductionFurnace));
        registration.addRecipeCategories(new CategoryPulverizer(pulverizer));
        registration.addRecipeCategories(new CategoryRefiner(refiner));
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration)
    {
        addArea(76, 35, 22, 16, pulverizer, PulverizerScreen.class, registration);
        addArea(76, 35, 22, 16, compressor, CompressorScreen.class, registration);
        addArea(76, 35, 22, 16, smelter, FurnaceScreen.class, registration);
        addArea(76, 35, 22, 16, psionicant, PsionicantScreen.class, registration);
        addArea(92, 35, 22, 16, inductionFurnace, IndfurScreen.class, registration);
        addArea(81, 35, 22, 16, refiner, RefinerScreen.class, registration);

        registration.addGlobalGuiHandler(new IGlobalGuiHandler()
        {
            @Override
            public Collection<Rect2i> getGuiExtraAreas()
            {
                if(!(Minecraft.getInstance().screen instanceof CmScreenMachine scr)) return Collections.emptyList();
                return scr.getExtras();
            }
        });
    }

    public static IIngredientType<FluidStack> FLUID_TYPE = ForgeTypes.FLUID_STACK;

    private<T> void addArea(int x, int y, int w, int h, RecipeType<T> type, Class<? extends AbstractContainerScreen<?>> clazz, IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(clazz, x, y, w, h, type);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration)
    {
        addCatalyst(registration, pulverizer);
        addCatalyst(registration, compressor);
        addCatalyst(registration, smelter);
        addCatalyst(registration, psionicant);
        addCatalyst(registration, inductionFurnace);
        addCatalyst(registration, refiner);
    }

    private void addCatalyst(IRecipeCatalystRegistration registration, RecipeType<?> type) {
        registration.addRecipeCatalyst(
                ItemInit.getItem("machine_" + type.getUid().getPath()).getDefaultInstance(),
                type);
    }

    @Override
    public ResourceLocation getPluginUid()
    {
        return new ResourceLocation("jei", TConst.modid);
    }

}
