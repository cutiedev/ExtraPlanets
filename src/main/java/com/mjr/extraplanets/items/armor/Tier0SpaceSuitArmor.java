package com.mjr.extraplanets.items.armor;

import java.util.List;

import javax.annotation.Nullable;

import org.lwjgl.input.Keyboard;

import com.mjr.extraplanets.Constants;
import com.mjr.extraplanets.ExtraPlanets;
import com.mjr.extraplanets.api.item.IModularArmor;
import com.mjr.extraplanets.api.item.IPressureSuit;
import com.mjr.extraplanets.api.item.IRadiationSuit;
import com.mjr.extraplanets.client.model.ArmorCustomModel;
import com.mjr.extraplanets.client.model.ArmorSpaceSuitModel;
import com.mjr.extraplanets.items.armor.modules.Module;
import com.mjr.extraplanets.items.armor.modules.ModuleHelper;
import com.mjr.mjrlegendslib.util.TranslateUtilities;

import micdoodle8.mods.galacticraft.api.item.IBreathableArmor;
import micdoodle8.mods.galacticraft.core.util.EnumColor;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Tier0SpaceSuitArmor extends ItemArmor implements IPressureSuit, IRadiationSuit, IBreathableArmor {
	public String name;

	public Tier0SpaceSuitArmor(String name, ArmorMaterial material, EntityEquipmentSlot placement) {
		super(material, 1, placement);
		this.setCreativeTab(ExtraPlanets.ItemsTab);
		this.name = name;
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		if (stack.getItem() == ExtraPlanets_Armor.TIER_0_SPACE_SUIT_HELMET || stack.getItem() == ExtraPlanets_Armor.TIER_0_SPACE_SUIT_CHEST || stack.getItem() == ExtraPlanets_Armor.TIER_0_SPACE_SUIT_BOOTS) {
			return Constants.TEXTURE_PREFIX + "textures/model/armor/tier1_space_suit_main.png";
		} else if (stack.getItem() == ExtraPlanets_Armor.TIER_0_SPACE_SUIT_LEGINGS) {
			return Constants.TEXTURE_PREFIX + "textures/model/armor/tier1_space_suit_main.png";
		} else {
			return null;
		}
	}
	
	@Override
	public boolean isEnchantable(ItemStack stack) {
		return false;
	}

	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
		return false;
	}

	@Override
	public int getArmorTier() {
		return 0;
	}

	@Override
	public boolean handleGearType(EnumGearType gearType) {
		return true;
	}

	@Override
	public boolean canBreathe(ItemStack helmetInSlot, EntityPlayer playerWearing, EnumGearType type) {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, @Nullable World world, List<String> list, ITooltipFlag flagIn) {
		list.add(EnumColor.RED + TranslateUtilities.translate("space.suit.temp.information"));
		list.add(EnumColor.DARK_GREEN + TranslateUtilities.translate("space.suit.temp.ticks") + ": " + getTicksLeft(itemStack) / 20 + " seconds left");
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			list.add(EnumColor.AQUA + TranslateUtilities.translate("space.suit.information"));
			list.add(EnumColor.AQUA + TranslateUtilities.translate("space.suit.information.2"));
			list.add(EnumColor.YELLOW + TranslateUtilities.translate("space.suit.information.extra"));
			list.add(EnumColor.YELLOW + TranslateUtilities.translate("space.suit.information.extra.2"));
			list.add(EnumColor.AQUA + TranslateUtilities.translate("space.suit.information.extra.3"));
			list.add(EnumColor.AQUA + TranslateUtilities.translate("space.suit.information.extra.4"));
		} else
			list.add(EnumColor.YELLOW + TranslateUtilities.translateWithFormat("item_desc.spacesuit.shift.name", GameSettings.getKeyDisplayString(FMLClientHandler.instance().getClient().gameSettings.keyBindSneak.getKeyCode())));
		super.addInformation(itemStack, world, list, flagIn);
	}

	public static ModelBiped fillingArmorModel(ModelBiped model, EntityLivingBase entityLiving) {
		if (model == null)
			return model;
		model.bipedHead.showModel = model.bipedHeadwear.showModel = model.bipedBody.showModel = model.bipedRightArm.showModel = model.bipedLeftArm.showModel = model.bipedRightLeg.showModel = model.bipedLeftLeg.showModel = false;
		model.isSneak = entityLiving.isSneaking();
		model.isRiding = entityLiving.isRiding();
		model.isChild = entityLiving.isChild();
		return model;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
		ModelBiped armorModel = new ArmorSpaceSuitModel(armorSlot);
		if (itemStack.getItem() instanceof Tier0SpaceSuitArmor) {
			armorModel = fillingArmorModel(armorModel, entityLiving);
			if (hasColor(itemStack) && armorModel instanceof ArmorCustomModel)
				((ArmorCustomModel) armorModel).color = getColor(itemStack);
		}
		return armorModel;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void renderHelmetOverlay(ItemStack stack, EntityPlayer player, ScaledResolution resolution, float partialTicks) {
		ItemStack helmet = player.inventory.armorInventory.get(3);
		ItemStack chest = player.inventory.armorInventory.get(2);
		ItemStack leggins = player.inventory.armorInventory.get(1);
		ItemStack boots = player.inventory.armorInventory.get(0);

		if (helmet.isEmpty() == false && helmet.getItem() instanceof IModularArmor)
			for (Module hemletModules : ModuleHelper.getModules(helmet)) {
				if (hemletModules.isActive())
					if (ModuleHelper.hasPower(helmet, ModuleHelper.getModuleUseCost(hemletModules)))
						hemletModules.renderHelmetOverlay(stack, player, resolution, partialTicks);
			}
		if (chest.isEmpty() == false && chest.getItem() instanceof IModularArmor)
			for (Module chestModules : ModuleHelper.getModules(chest)) {
				if (chestModules.isActive())
					if (ModuleHelper.hasPower(chest, ModuleHelper.getModuleUseCost(chestModules)))
						chestModules.renderHelmetOverlay(stack, player, resolution, partialTicks);
			}
		if (leggins.isEmpty() == false && leggins.getItem() instanceof IModularArmor)
			for (Module legginsModules : ModuleHelper.getModules(leggins)) {
				if (legginsModules.isActive())
					if (ModuleHelper.hasPower(leggins, ModuleHelper.getModuleUseCost(legginsModules)))
						legginsModules.renderHelmetOverlay(stack, player, resolution, partialTicks);
			}
		if (boots.isEmpty() == false && boots.getItem() instanceof IModularArmor)
			for (Module bootsModules : ModuleHelper.getModules(boots)) {
				if (bootsModules.isActive())
					if (ModuleHelper.hasPower(boots, ModuleHelper.getModuleUseCost(bootsModules)))
						bootsModules.renderHelmetOverlay(stack, player, resolution, partialTicks);
			}
	}

	public static int getTicksLeft(ItemStack itemStack) {
		if (itemStack.getTagCompound() == null) {
			return 6000;
		}
		return itemStack.getTagCompound().getInteger("ticksLeft");
	}

	public static void setTicksLeft(ItemStack itemStack, int ticksLeft) {
		if (itemStack.getTagCompound() == null) {
			itemStack.setTagCompound(new NBTTagCompound());
			itemStack.getTagCompound().setInteger("ticksLeft", 6000);
		}
		itemStack.getTagCompound().setInteger("ticksLeft", ticksLeft);
	}
}
