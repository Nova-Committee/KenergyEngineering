package committee.nova.mods.keneng.util;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidStack;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import committee.nova.mods.keneng.lib.client.element.ElementBase;

import java.util.ArrayList;

public class RenderHelper
{

    public static TextureAtlasSprite getSprite(FluidStack fluidStack)
    {
        IClientFluidTypeExtensions renderProperties = IClientFluidTypeExtensions.of(fluidStack.getFluid());
        ResourceLocation fluidStill = renderProperties.getStillTexture(fluidStack);

        Minecraft minecraft = Minecraft.getInstance();
        return minecraft.getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(fluidStill);
    }

    //* stole from CoFH Core *
    public static void drawFlTil(GuiGraphics matrixStack, FluidStack fluidStack, int x, int y, int width, int height)
    {
        if(fluidStack.isEmpty()) {
            return;
        }
        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        RenderSystem.setShaderTexture(0, InventoryMenu.BLOCK_ATLAS);
        int color = IClientFluidTypeExtensions.of(fluidStack.getFluid()).getTintColor();
        float red = (color >> 16 & 0xFF) / 255.0F;
        float green = (color >> 8 & 0xFF) / 255.0F;
        float blue = (color & 0xFF) / 255.0F;
        float alpha = ((color >> 24) & 0xFF) / 255F;
        RenderSystem.setShaderColor(red, green, blue, alpha);

        int drawHeight;
        int drawWidth;

        for(int i = 0; i < width; i += 16) {
            for(int j = 0; j < height; j += 16) {
                drawWidth = Math.min(width - i, 16);
                drawHeight = Math.min(height - j, 16);
                drawSprite(matrixStack, getSprite(fluidStack), x + i, y + j, drawWidth, drawHeight);
            }
        }

        RenderSystem.disableBlend();
        RenderSystem.setShaderColor(1, 1, 1, 1);
    }

    public static void drawSprite(GuiGraphics matrixStack, TextureAtlasSprite icon, int x, int y, int width, int height)
    {
        float minU = icon.getU0();
        float maxU = icon.getU1();
        float minV = icon.getV0();
        float maxV = icon.getV1();

        float u = minU + (maxU - minU) * width / 16F;
        float v = minV + (maxV - minV) * height / 16F;

        Matrix4f matrix = matrixStack.pose().last().pose();

        BufferBuilder buffer = Tesselator.getInstance().getBuilder();
        buffer.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        buffer.vertex(matrix, x, y + height, 0).uv(minU, v).endVertex();
        buffer.vertex(matrix, x + width, y + height, 0).uv(u, v).endVertex();
        buffer.vertex(matrix, x + width, y, 0).uv(u, minV).endVertex();
        buffer.vertex(matrix, x, y, 0).uv(minU, minV).endVertex();
        Tesselator.getInstance().end();
    }

    public static void drawAll(ArrayList<ElementBase> widgets, GuiGraphics matrixStack)
    {

        for(int i = 0; i < widgets.size(); i++) {
            if(widgets.get(i).isVisible()) {
                widgets.get(i).draw(matrixStack);
            }
        }

    }

    public static void updateAll(ArrayList<ElementBase> widgets)
    {

        for(int i = 0; i < widgets.size(); i++) {
            if(widgets.get(i).isVisible()) {
                widgets.get(i).update();
            }
        }

    }

    public static void hangingAll(ArrayList<ElementBase> widgets, boolean hang, int mouseX, int mouseY)
    {

        for(int i = 0; i < widgets.size(); i++) {
            if(widgets.get(i).isVisible()) {
                widgets.get(i).hangingEvent(hang, mouseX, mouseY);
            }
        }

    }

    public static void clickAll(ArrayList<ElementBase> widgets, int mouseX, int mouseY)
    {

        //ItemStack ret = stack;

        for(int i = 0; i < widgets.size(); i++) {
            if(widgets.get(i).checkInstr(mouseX, mouseY) && widgets.get(i).isVisible()) {
                /*
                if(widgets.getRecipe(i) instanceof ElementSlot) {
                    ItemStack in = ((ElementSlot) widgets.getRecipe(i)).item;
                    if(!stack.isEmpty() && in.isEmpty()) {
                        in = stack.copy();
                        ret = ItemStack.EMPTY;
                    }
                    else if(stack.isEmpty() && !in.isEmpty()) {
                        ret = in.copy();
                        in.setCount(0);
                    }
                    else if(!stack.isEmpty() && !in.isEmpty()) {
                        if(stack.getRecipe() != in.getRecipe()) {
                            ItemStack cac = stack.copy();
                            ret = in.copy();
                            in = cac;
                        }
                    }
                }
                 */
                widgets.get(i).onMouseClicked(mouseX, mouseY);
            }
        }

        //return ret;

    }

    public static void bindTexture(ResourceLocation resourceLocation)
    {
        RenderSystem.setShaderTexture(0, resourceLocation);
    }

    public static void renderBackGround(GuiGraphics matrixStack, int i, int j, int textureW, int textureH, ResourceLocation resourceLocation)
    {
        render(matrixStack, i, j, 176, 166, textureW, textureH, 0, 0, resourceLocation);
    }

    public static void renderBackGround(GuiGraphics matrixStack, int w, int h, int i, int j, int textureW, int textureH, ResourceLocation resourceLocation)
    {
        render(matrixStack, i, j, w, h, textureW, textureH, 0, 0, resourceLocation);
    }

    public static void render(GuiGraphics matrixStack, int x, int y, int width, int height, int textureW, int textureH, int xOff, int yOff, ResourceLocation resourceLocation)
    {
        matrixStack.blit(resourceLocation, x, y, xOff, yOff, width, height, textureW, textureH);
    }

    public static void renderString(GuiGraphics matrixStack, int x, int y, int color, Component str)
    {

        Font font = Minecraft.getInstance().font;
        matrixStack.drawString(font, str, x, y, color);
    }

    public static void renderCString(GuiGraphics matrixStack, int x, int y, int color, Component str)
    {

        Font font = Minecraft.getInstance().font;
        matrixStack.drawCenteredString(font, str, x, y, color);
    }

}
