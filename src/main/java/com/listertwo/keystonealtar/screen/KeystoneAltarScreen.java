package com.listertwo.keystonealtar.screen;

import com.listertwo.keystonealtar.KeystoneAltarMod;
import com.listertwo.keystonealtar.container.KeystoneAltarContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class KeystoneAltarScreen extends ContainerScreen<KeystoneAltarContainer> {
    private final ResourceLocation GUI = new ResourceLocation(KeystoneAltarMod.MODID, "textures/gui/keystone_altar_gui.png");

    public KeystoneAltarScreen(KeystoneAltarContainer p_i51105_1_, PlayerInventory p_i51105_2_, ITextComponent p_i51105_3_) {
        super(p_i51105_1_, p_i51105_2_, p_i51105_3_);
    }

    @Override
    public void render(MatrixStack p_230430_1_, int p_230430_2_, int p_230430_3_, float p_230430_4_) {
        this.renderBackground(p_230430_1_);
        super.render(p_230430_1_, p_230430_2_, p_230430_3_, p_230430_4_);
        this.renderHoveredTooltip(p_230430_1_, p_230430_2_, p_230430_3_);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
        RenderSystem.color4f(1f, 1f, 1f, 1f);
        this.minecraft.getTextureManager().bindTexture(GUI);
        int i = guiLeft;
        int j = guiTop;
        this.blit(matrixStack, i, j, 0, 0, this.xSize, this.ySize);

        if(container.isRedstonePulsed()){
            this.blit(matrixStack, i + 43, j + 37, 178, 3, 4, 12);
        }
    }
}
