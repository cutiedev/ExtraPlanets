package com.mjr.extraplanets.items;

import micdoodle8.mods.galacticraft.api.item.IItemElectric;
import micdoodle8.mods.galacticraft.core.energy.item.ItemElectricBase;
import micdoodle8.mods.galacticraft.core.proxy.ClientProxyCore;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.mjr.extraplanets.ExtraPlanets;

public class ItemBasicBattery extends ItemElectricBase implements IItemElectric {

	private float max;
	private int tier;

	public ItemBasicBattery(String name, Float max, int tier) {
		this.setUnlocalizedName(name);
		this.setCreativeTab(ExtraPlanets.ItemsTab);
		this.max = max;
		this.tier = tier;
	}

	@SideOnly(Side.CLIENT)
	public EnumRarity func_77613_e(ItemStack par1ItemStack) {
		return ClientProxyCore.galacticraftItem;
	}

	@Override
	public float getMaxElectricityStored(ItemStack itemStack) {
		return max;
	}

	@Override
	public int getTierGC(ItemStack itemStack) {
		return tier;
	}
    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> list)
    {
        if (tab == this.getCreativeTab())
        {
            list.add(new ItemStack(this, 1, 0));
        }
    }
}
