package com.mjr.extraplanets.client.render.tile;

import micdoodle8.mods.galacticraft.core.client.model.block.ModelTreasureChest;
import micdoodle8.mods.galacticraft.core.client.model.block.ModelTreasureChestLarge;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.mjr.extraplanets.Constants;
import com.mjr.extraplanets.blocks.treasureChest.T8TreasureChest;
import com.mjr.extraplanets.tileEntities.treasureChest.TileEntityT8TreasureChest;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntityT8TreasureChestRenderer extends TileEntitySpecialRenderer
{
	private static final ResourceLocation treasureChestTexture = new ResourceLocation(Constants.ASSET_PREFIX, "textures/model/treasure_t8.png");
	private static final ResourceLocation treasureLargeChestTexture = new ResourceLocation(Constants.ASSET_PREFIX, "textures/model/treasureT8large.png");

	private final ModelTreasureChest chestModel = new ModelTreasureChest();
	private final ModelTreasureChestLarge largeChestModel = new ModelTreasureChestLarge();

	public void renderGCTileEntityTreasureChestAt(TileEntityT8TreasureChest par1GCTileEntityTreasureChest, double par2, double par4, double par6, float par8)
	{
		if (par1GCTileEntityTreasureChest.getWorldObj() != null)
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		else
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		int var9;

		if (!par1GCTileEntityTreasureChest.hasWorldObj())
		{
			var9 = 0;
		}
		else
		{
			final Block var10 = par1GCTileEntityTreasureChest.getBlockType();
			var9 = par1GCTileEntityTreasureChest.getBlockMetadata();

			if (var10 != null && var9 == 0)
			{
				((T8TreasureChest) var10).unifyAdjacentChests(par1GCTileEntityTreasureChest.getWorldObj(), par1GCTileEntityTreasureChest.xCoord, par1GCTileEntityTreasureChest.yCoord, par1GCTileEntityTreasureChest.zCoord);
				var9 = par1GCTileEntityTreasureChest.getBlockMetadata();
			}

			par1GCTileEntityTreasureChest.checkForAdjacentChests();
		}

		if (par1GCTileEntityTreasureChest.adjacentChestZNeg == null && par1GCTileEntityTreasureChest.adjacentChestXNeg == null)
		{
			ModelTreasureChest var14 = null;
			ModelTreasureChestLarge var14b = null;

			if (par1GCTileEntityTreasureChest.adjacentChestXPos == null && par1GCTileEntityTreasureChest.adjacentChestZPos == null)
			{
				var14 = this.chestModel;
				this.bindTexture(TileEntityT8TreasureChestRenderer.treasureChestTexture);
			}
			else
			{
				var14b = this.largeChestModel;
				this.bindTexture(TileEntityT8TreasureChestRenderer.treasureLargeChestTexture);
			}

			GL11.glPushMatrix();
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glTranslatef((float) par2, (float) par4 + 1.0F, (float) par6 + 1.0F);
			GL11.glScalef(1.0F, -1.0F, -1.0F);
			GL11.glTranslatef(0.5F, 0.5F, 0.5F);
			short var11 = 0;

			if (var9 == 2)
			{
				var11 = 180;
			}

			if (var9 == 3)
			{
				var11 = 0;
			}

			if (var9 == 4)
			{
				var11 = 90;
			}

			if (var9 == 5)
			{
				var11 = -90;
			}

			if (var9 == 2 && par1GCTileEntityTreasureChest.adjacentChestXPos != null)
			{
				GL11.glTranslatef(1.0F, 0.0F, 0.0F);
			}

			if (var9 == 5 && par1GCTileEntityTreasureChest.adjacentChestZPos != null)
			{
				GL11.glTranslatef(0.0F, 0.0F, -1.0F);
			}

			GL11.glRotatef(var11, 0.0F, 1.0F, 0.0F);
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
			float var12 = par1GCTileEntityTreasureChest.prevLidAngle + (par1GCTileEntityTreasureChest.lidAngle - par1GCTileEntityTreasureChest.prevLidAngle) * par8;

			float var13;

			if (par1GCTileEntityTreasureChest.adjacentChestZNeg != null)
			{
				var13 = par1GCTileEntityTreasureChest.adjacentChestZNeg.prevLidAngle + (par1GCTileEntityTreasureChest.adjacentChestZNeg.lidAngle - par1GCTileEntityTreasureChest.adjacentChestZNeg.prevLidAngle) * par8;

				if (var13 > var12)
				{
					var12 = var13;
				}
			}

			if (par1GCTileEntityTreasureChest.adjacentChestXNeg != null)
			{
				var13 = par1GCTileEntityTreasureChest.adjacentChestXNeg.prevLidAngle + (par1GCTileEntityTreasureChest.adjacentChestXNeg.lidAngle - par1GCTileEntityTreasureChest.adjacentChestXNeg.prevLidAngle) * par8;

				if (var13 > var12)
				{
					var12 = var13;
				}
			}

			var12 = 1.0F - var12;
			var12 = 1.0F - var12 * var12 * var12;

			if (var14 != null)
			{
				var14.chestLid.rotateAngleX = -(var12 * Constants.floatPI / 4.0F);
				var14.renderAll(!par1GCTileEntityTreasureChest.locked);
			}

			if (var14b != null)
			{
				var14b.chestLid.rotateAngleX = -(var12 * Constants.floatPI / 4.0F);
				var14b.renderAll(!par1GCTileEntityTreasureChest.locked);
			}

			GL11.glPopMatrix();
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		}
	}

	@Override
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
	{
		this.renderGCTileEntityTreasureChestAt((TileEntityT8TreasureChest) par1TileEntity, par2, par4, par6, par8);
	}
}