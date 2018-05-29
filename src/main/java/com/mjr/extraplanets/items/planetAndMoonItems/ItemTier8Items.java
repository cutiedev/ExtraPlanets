package com.mjr.extraplanets.items.planetAndMoonItems;

import java.util.List;

import javax.annotation.Nullable;

import micdoodle8.mods.galacticraft.core.proxy.ClientProxyCore;
import micdoodle8.mods.galacticraft.core.util.EnumColor;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.mjr.extraplanets.ExtraPlanets;
import com.mjr.mjrlegendslib.item.ItemBasicMeta;
import com.mjr.mjrlegendslib.util.TranslateUtilities;

public class ItemTier8Items extends ItemBasicMeta {

	public ItemTier8Items(String name) {
		super(name, ExtraPlanets.ItemsTab, getItemList());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack itemStack) {
		return ClientProxyCore.galacticraftItem;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, @Nullable World world, List<String> list, ITooltipFlag flagIn) {
		if (itemStack != null && itemStack.getItemDamage() == 3) {
			list.add(EnumColor.GREY + TranslateUtilities.translate("tier8.heavy_duty_plate.name"));
		}
	}

	public static String[] getItemList() {
		return new String[] { "tier8engine", "tier8booster", "tier8fin", "tier8heavy_duty_plate", "compressed_zinc", "ingot_zinc", "blue_gem" };
	}
}
