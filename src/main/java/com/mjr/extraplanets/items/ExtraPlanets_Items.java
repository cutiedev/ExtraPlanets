package com.mjr.extraplanets.items;

import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import micdoodle8.mods.galacticraft.api.item.EnumExtendedInventorySlot;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

import com.mjr.extraplanets.Config;
import com.mjr.extraplanets.blocks.fluid.ExtraPlanets_Fluids;
import com.mjr.extraplanets.items.keys.ItemKeyT10;
import com.mjr.extraplanets.items.keys.ItemKeyT4;
import com.mjr.extraplanets.items.keys.ItemKeyT5;
import com.mjr.extraplanets.items.keys.ItemKeyT6;
import com.mjr.extraplanets.items.keys.ItemKeyT7;
import com.mjr.extraplanets.items.keys.ItemKeyT8;
import com.mjr.extraplanets.items.keys.ItemKeyT9;
import com.mjr.extraplanets.items.noseCones.Tier10NoseCone;
import com.mjr.extraplanets.items.noseCones.Tier4NoseCone;
import com.mjr.extraplanets.items.noseCones.Tier5NoseCone;
import com.mjr.extraplanets.items.noseCones.Tier6NoseCone;
import com.mjr.extraplanets.items.noseCones.Tier7NoseCone;
import com.mjr.extraplanets.items.noseCones.Tier8NoseCone;
import com.mjr.extraplanets.items.noseCones.Tier9NoseCone;
import com.mjr.extraplanets.items.planetAndMoonItems.ItemTier10Items;
import com.mjr.extraplanets.items.planetAndMoonItems.ItemTier11Items;
import com.mjr.extraplanets.items.planetAndMoonItems.ItemTier4Items;
import com.mjr.extraplanets.items.planetAndMoonItems.ItemTier5Items;
import com.mjr.extraplanets.items.planetAndMoonItems.ItemTier6Items;
import com.mjr.extraplanets.items.planetAndMoonItems.ItemTier7Items;
import com.mjr.extraplanets.items.planetAndMoonItems.ItemTier8Items;
import com.mjr.extraplanets.items.planetAndMoonItems.ItemTier9Items;
import com.mjr.extraplanets.items.rockets.Tier10Rocket;
import com.mjr.extraplanets.items.rockets.Tier4Rocket;
import com.mjr.extraplanets.items.rockets.Tier5Rocket;
import com.mjr.extraplanets.items.rockets.Tier6Rocket;
import com.mjr.extraplanets.items.rockets.Tier7Rocket;
import com.mjr.extraplanets.items.rockets.Tier8Rocket;
import com.mjr.extraplanets.items.rockets.Tier9Rocket;
import com.mjr.extraplanets.items.schematics.SchematicTier10;
import com.mjr.extraplanets.items.schematics.SchematicTier4;
import com.mjr.extraplanets.items.schematics.SchematicTier5;
import com.mjr.extraplanets.items.schematics.SchematicTier6;
import com.mjr.extraplanets.items.schematics.SchematicTier7;
import com.mjr.extraplanets.items.schematics.SchematicTier8;
import com.mjr.extraplanets.items.schematics.SchematicTier9;
import com.mjr.extraplanets.items.thermalPadding.ItemThermalCloth;
import com.mjr.extraplanets.items.thermalPadding.ItemTier3ThermalPadding;
import com.mjr.extraplanets.items.thermalPadding.ItemTier4ThermalPadding;
import com.mjr.extraplanets.items.thermalPadding.ItemTier5ThermalPadding;

public class ExtraPlanets_Items {
	public static Item tier4Rocket;
	public static Item tier5Rocket;
	public static Item tier6Rocket;
	public static Item tier7Rocket;
	public static Item tier8Rocket;
	public static Item tier9Rocket;
	public static Item tier10Rocket;

	public static Item schematicTier4;
	public static Item schematicTier5;
	public static Item schematicTier6;
	public static Item schematicTier7;
	public static Item schematicTier8;
	public static Item schematicTier9;
	public static Item schematicTier10;

	public static Item noseConeTier4;
	public static Item noseConeTier5;
	public static Item noseConeTier6;
	public static Item noseConeTier7;
	public static Item noseConeTier8;
	public static Item noseConeTier9;
	public static Item noseConeTier10;

	public static Item tier4Items;
	public static Item tier5Items;
	public static Item tier6Items;
	public static Item tier7Items;
	public static Item tier8Items;
	public static Item tier9Items;
	public static Item tier10Items;
	public static Item tier11Items;

	public static Item T4key;
	public static Item T5key;
	public static Item T6key;
	public static Item T7key;
	public static Item T8key;
	public static Item T9key;
	public static Item T10key;

	public static Item nickelBattery;
	public static Item zincBattery;
	public static Item mercuryBattery;
	public static Item advancedBattery;
	public static Item ultimateBattery;

	public static Item oxygenTankVeryHeavy;
	public static Item oxygenTankExtremelyHeavy;

	public static Item glowstone_bucket;
	public static Item magma_bucket;
	public static Item nitrogen_bucket;
	public static Item frozen_water_bucket;
	public static Item salt_bucket;
	public static Item clean_water_bucket;
	public static Item infected_water_bucket;
	public static Item radioactive_bucket;
	public static Item methane_bucket;
	public static Item nitrogen_ice_bucket;

	public static Item cannedFood;
	public static Item diamondApple;
	public static Item ironApple;
	public static Item bodyParts;

	public static Item ingotUranium;
	public static Item compressedMercury;
	public static Item ingotMercury;
	public static Item ingotLead;

	public static Item cloth;
	public static Item gravityController;

	public static Item tier1PressureLayer;
	public static Item tier2PressureLayer;
	public static Item tier3PressureLayer;
	public static Item tier4PressureLayer;

	public static Item tier1RadiationLayer;
	public static Item tier2RadiationLayer;
	public static Item tier3RadiationLayer;
	public static Item tier4RadiationLayer;

	public static Item tier1ArmorLayer;
	public static Item tier2ArmorLayer;
	public static Item tier3ArmorLayer;
	public static Item tier4ArmorLayer;

	public static Item thermalCloth;

	public static Item tier3ThermalPadding;
	public static Item tier4ThermalPadding;
	public static Item tier5ThermalPadding;

	public static Item tier1UnPreparedSpaceSuitHelmet;
	public static Item tier1UnPreparedSpaceSuitChest;
	public static Item tier1UnPreparedSpaceSuitLegings;
	public static Item tier1UnPreparedSpaceSuitBoots;

	public static Item tier2UnPreparedSpaceSuitHelmet;
	public static Item tier2UnPreparedSpaceSuitChest;
	public static Item tier2UnPreparedSpaceSuitLegings;
	public static Item tier2UnPreparedSpaceSuitBoots;

	public static Item tier3UnPreparedSpaceSuitHelmet;
	public static Item tier3UnPreparedSpaceSuitChest;
	public static Item tier3UnPreparedSpaceSuitLegings;
	public static Item tier3UnPreparedSpaceSuitBoots;

	public static Item tier4UnPreparedSpaceSuitHelmet;
	public static Item tier4UnPreparedSpaceSuitChest;
	public static Item tier4UnPreparedSpaceSuitLegings;
	public static Item tier4UnPreparedSpaceSuitBoots;

	public static Item wafers;
	public static Item iodideSalt;
	public static Item potassiumIodide;
	public static Item potassium;
	public static Item potash;
	public static Item anti_radiation;

	public static void init() {
		initializeItems();
		registerItems();
		registerFluidContainer();
		OreDictionaryRegister();
		registerGearItems();
	}

	private static void initializeItems() {
		if (Config.mercury) {
			if (Config.batteries)
				mercuryBattery = new ItemBasicBattery("mercuryBattery", 85000f, 2);
			ingotMercury = new ItemBasicItem("ingotMercury");
			compressedMercury = new ItemMercuryCompressed("compressedMercury");
			tier4Rocket = new Tier4Rocket("itemTier4Rocket");
			schematicTier4 = new SchematicTier4("schematicTier4");
			noseConeTier4 = new Tier4NoseCone("noseConeTier4");
			tier4Items = new ItemTier4Items("tier4Items");
			T4key = new ItemKeyT4("T4key");
		}
		if (Config.ceres) {
			ingotUranium = new ItemBasicItem("ingotUranium");
			salt_bucket = new ItemBasicItemBucket("bucket_salt", ExtraPlanets_Fluids.salt);
		}
		if (Config.jupiter) {
			if (Config.batteries)
				nickelBattery = new ItemBasicBattery("nickelBattery", 45000f, 2);
			magma_bucket = new ItemBasicItemBucket("bucket_magma", ExtraPlanets_Fluids.magma);
			tier5Rocket = new Tier5Rocket("itemTier5Rocket");
			schematicTier5 = new SchematicTier5("schematicTier5");
			noseConeTier5 = new Tier5NoseCone("noseConeTier5");
			tier5Items = new ItemTier5Items("tier5Items");
			T5key = new ItemKeyT5("T5key");
		}
		if (Config.saturn) {
			glowstone_bucket = new ItemBasicItemBucket("bucket_glowstone", ExtraPlanets_Fluids.glowstone);
			tier6Rocket = new Tier6Rocket("itemTier6Rocket");
			schematicTier6 = new SchematicTier6("schematicTier6");
			noseConeTier6 = new Tier6NoseCone("noseConeTier6");
			tier6Items = new ItemTier6Items("tier6Items");
			T6key = new ItemKeyT6("T6key");
		}
		if (Config.uranus) {
			frozen_water_bucket = new ItemBasicItemBucket("bucket_frozen_water", ExtraPlanets_Fluids.frozen_water);
			tier7Rocket = new Tier7Rocket("itemTier7Rocket");
			schematicTier7 = new SchematicTier7("schematicTier7");
			noseConeTier7 = new Tier7NoseCone("noseConeTier7");
			tier7Items = new ItemTier7Items("tier7Items");
			T7key = new ItemKeyT7("T7key");
		}
		if (Config.neptune) {
			if (Config.batteries)
				zincBattery = new ItemBasicBattery("zincBattery", 125000f, 2);
			nitrogen_bucket = new ItemBasicItemBucket("bucket_nitrogen", ExtraPlanets_Fluids.nitrogen);
			tier8Rocket = new Tier8Rocket("itemTier8Rocket");
			schematicTier8 = new SchematicTier8("schematicTier8");
			noseConeTier8 = new Tier8NoseCone("noseConeTier8");
			tier8Items = new ItemTier8Items("tier8Items");
			T8key = new ItemKeyT8("T8key");
		}
		if (Config.pluto) {
			tier9Rocket = new Tier9Rocket("itemTier9Rocket");
			schematicTier9 = new SchematicTier9("schematicTier9");
			noseConeTier9 = new Tier9NoseCone("noseConeTier9");
			tier9Items = new ItemTier9Items("tier9Items");
			T9key = new ItemKeyT9("T9key");
		}
		if (Config.eris) {
			tier10Rocket = new Tier10Rocket("itemTier10Rocket");
			schematicTier10 = new SchematicTier10("schematicTier10");
			noseConeTier10 = new Tier10NoseCone("noseConeTier10");
			tier10Items = new ItemTier10Items("tier10Items");
			T10key = new ItemKeyT10("T10key");
		}
		if (Config.kepler22b && Config.keplerSolarSystems) {
			tier11Items = new ItemTier11Items("tier11Items");
		}
		if (Config.customApples) {
			diamondApple = new ItemAppleDiamond(8, 2.2F, false);
			ironApple = new ItemAppleIron(4, 2.2F, false);
		}
		if (Config.thermalPaddings) {
			thermalCloth = new ItemThermalCloth("thermalCloth");
			tier3ThermalPadding = new ItemTier3ThermalPadding("tier3ThermalPadding");
			tier4ThermalPadding = new ItemTier4ThermalPadding("tier4ThermalPadding");
			tier5ThermalPadding = new ItemTier5ThermalPadding("tier5ThermalPadding");
		}
		if (Config.batteries) {
			advancedBattery = new ItemBasicBattery("advancedBattery", 50000f, 2);
			ultimateBattery = new ItemBasicBattery("ultimateBattery", 200000f, 2);
		}
		if (Config.oxygenTanks) {
			oxygenTankVeryHeavy = new ItemOxygenTank(4, "oxygen_tank_very_heavy_full");
			oxygenTankExtremelyHeavy = new ItemOxygenTank(5, "oxygen_tank_extremely_heavy_full");
		}
		if (Config.pressure || Config.radiation) {
			tier1PressureLayer = new ItemBasicItem("tier1PressureLayer");
			tier2PressureLayer = new ItemBasicItem("tier2PressureLayer");
			tier3PressureLayer = new ItemBasicItem("tier3PressureLayer");
			tier4PressureLayer = new ItemBasicItem("tier4PressureLayer");

			tier1RadiationLayer = new ItemBasicItem("tier1RadiationLayer");
			tier2RadiationLayer = new ItemBasicItem("tier2RadiationLayer");
			tier3RadiationLayer = new ItemBasicItem("tier3RadiationLayer");
			tier4RadiationLayer = new ItemBasicItem("tier4RadiationLayer");

			tier1ArmorLayer = new ItemBasicItem("tier1ArmorLayer");
			tier2ArmorLayer = new ItemBasicItem("tier2ArmorLayer");
			tier3ArmorLayer = new ItemBasicItem("tier3ArmorLayer");
			tier4ArmorLayer = new ItemBasicItem("tier4ArmorLayer");

			tier1UnPreparedSpaceSuitHelmet = new ItemBasicItem("tier1UnPreparedSpaceSuitHelmet");
			tier1UnPreparedSpaceSuitChest = new ItemBasicItem("tier1UnPreparedSpaceSuitChest");
			tier1UnPreparedSpaceSuitLegings = new ItemBasicItem("tier1UnPreparedSpaceSuitLegings");
			tier1UnPreparedSpaceSuitBoots = new ItemBasicItem("tier1UnPreparedSpaceSuitBoots");

			tier2UnPreparedSpaceSuitHelmet = new ItemBasicItem("tier2UnPreparedSpaceSuitHelmet");
			tier2UnPreparedSpaceSuitChest = new ItemBasicItem("tier2UnPreparedSpaceSuitChest");
			tier2UnPreparedSpaceSuitLegings = new ItemBasicItem("tier2UnPreparedSpaceSuitLegings");
			tier2UnPreparedSpaceSuitBoots = new ItemBasicItem("tier2UnPreparedSpaceSuitBoots");

			tier3UnPreparedSpaceSuitHelmet = new ItemBasicItem("tier3UnPreparedSpaceSuitHelmet");
			tier3UnPreparedSpaceSuitChest = new ItemBasicItem("tier3UnPreparedSpaceSuitChest");
			tier3UnPreparedSpaceSuitLegings = new ItemBasicItem("tier3UnPreparedSpaceSuitLegings");
			tier3UnPreparedSpaceSuitBoots = new ItemBasicItem("tier3UnPreparedSpaceSuitBoots");

			tier4UnPreparedSpaceSuitHelmet = new ItemBasicItem("tier4UnPreparedSpaceSuitHelmet");
			tier4UnPreparedSpaceSuitChest = new ItemBasicItem("tier4UnPreparedSpaceSuitChest");
			tier4UnPreparedSpaceSuitLegings = new ItemBasicItem("tier4UnPreparedSpaceSuitLegings");
			tier4UnPreparedSpaceSuitBoots = new ItemBasicItem("tier4UnPreparedSpaceSuitBoots");
		}
		if (Config.radiation) {
			iodideSalt = new ItemBasicItem("iodideSalt");
			potassiumIodide = new ItemBasicItem("potassiumIodide");
			potassium = new ItemBasicItem("potassium");
			potash = new ItemBasicItem("potashShards");
			anti_radiation = new ItemBasicItem("anti_radiation");
		}
		clean_water_bucket = new ItemBasicItemBucket("bucket_clean_water", ExtraPlanets_Fluids.cleanWater);
		infected_water_bucket = new ItemBasicItemBucket("bucket_infected_water", ExtraPlanets_Fluids.infectedWater);
		radioactive_bucket = new ItemBasicItemBucket("bucket_radioactive_water", ExtraPlanets_Fluids.radioactiveWater);
		methane_bucket = new ItemBasicItemBucket("bucket_methane", ExtraPlanets_Fluids.methane);
		nitrogen_ice_bucket = new ItemBasicItemBucket("bucket_nitrogen_ice", ExtraPlanets_Fluids.nitrogen_ice);

		// cannedFood = new ItemCannedFood("cannedfood");
		wafers = new ItemWafers("wafer");
		ingotLead = new ItemBasicItem("ingotLead");
		cloth = new ItemBasicItem("cloth");
		gravityController = new ItemBasicItem("gravityController");

	}

	private static void registerItems() {
		if (Config.mercury) {
			if (Config.batteries)
				GameRegistry.registerItem(mercuryBattery, mercuryBattery.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(compressedMercury, compressedMercury.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(ingotMercury, ingotMercury.getUnlocalizedName().substring(5));

			GameRegistry.registerItem(tier4Rocket, tier4Rocket.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(schematicTier4, schematicTier4.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(noseConeTier4, noseConeTier4.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(tier4Items, tier4Items.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(T4key, T4key.getUnlocalizedName().substring(5));
		}
		if (Config.ceres) {
			GameRegistry.registerItem(ingotUranium, ingotUranium.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(salt_bucket, "bucket_salt");
		}
		if (Config.jupiter) {
			GameRegistry.registerItem(tier5Rocket, tier5Rocket.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(schematicTier5, schematicTier5.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(noseConeTier5, noseConeTier5.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(tier5Items, tier5Items.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(T5key, T5key.getUnlocalizedName().substring(5));

			if (Config.batteries)
				GameRegistry.registerItem(nickelBattery, nickelBattery.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(magma_bucket, "bucket_magma");
		}
		if (Config.saturn) {
			GameRegistry.registerItem(tier6Rocket, tier6Rocket.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(schematicTier6, schematicTier6.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(noseConeTier6, noseConeTier6.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(tier6Items, tier6Items.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(T6key, T6key.getUnlocalizedName().substring(5));

			GameRegistry.registerItem(glowstone_bucket, "bucket_glowstone");
		}
		if (Config.uranus) {
			GameRegistry.registerItem(tier7Rocket, tier7Rocket.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(schematicTier7, schematicTier7.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(noseConeTier7, noseConeTier7.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(tier7Items, tier7Items.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(T7key, T7key.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(frozen_water_bucket, "bucket_frozen_water");
		}
		if (Config.neptune) {
			GameRegistry.registerItem(tier8Rocket, tier8Rocket.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(schematicTier8, schematicTier8.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(noseConeTier8, noseConeTier8.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(tier8Items, tier8Items.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(T8key, T8key.getUnlocalizedName().substring(5));
			if (Config.batteries)
				GameRegistry.registerItem(zincBattery, zincBattery.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(nitrogen_bucket, "bucket_nitrogen");
		}
		if (Config.pluto) {
			GameRegistry.registerItem(tier9Rocket, tier9Rocket.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(schematicTier9, schematicTier9.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(noseConeTier9, noseConeTier9.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(tier9Items, tier9Items.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(T9key, T9key.getUnlocalizedName().substring(5));
		}
		if (Config.eris) {
			GameRegistry.registerItem(tier10Rocket, tier10Rocket.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(schematicTier10, schematicTier10.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(noseConeTier10, noseConeTier10.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(tier10Items, tier10Items.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(T10key, T10key.getUnlocalizedName().substring(5));
		}
		if (Config.kepler22b && Config.keplerSolarSystems) {
			GameRegistry.registerItem(tier11Items, tier11Items.getUnlocalizedName().substring(5));
		}
		if (Config.customApples) {
			GameRegistry.registerItem(diamondApple, diamondApple.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(ironApple, ironApple.getUnlocalizedName().substring(5));
		}
		if (Config.thermalPaddings) {
			GameRegistry.registerItem(thermalCloth, thermalCloth.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(tier3ThermalPadding, tier3ThermalPadding.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(tier4ThermalPadding, tier4ThermalPadding.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(tier5ThermalPadding, tier5ThermalPadding.getUnlocalizedName().substring(5));
		}
		if (Config.batteries) {
			GameRegistry.registerItem(advancedBattery, advancedBattery.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(ultimateBattery, ultimateBattery.getUnlocalizedName().substring(5));
		}
		if (Config.oxygenTanks) {
			GameRegistry.registerItem(oxygenTankVeryHeavy, oxygenTankVeryHeavy.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(oxygenTankExtremelyHeavy, oxygenTankExtremelyHeavy.getUnlocalizedName().substring(5));
		}
		if (Config.pressure || Config.radiation) {
			GameRegistry.registerItem(tier1PressureLayer, tier1PressureLayer.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(tier2PressureLayer, tier2PressureLayer.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(tier3PressureLayer, tier3PressureLayer.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(tier4PressureLayer, tier4PressureLayer.getUnlocalizedName().substring(5));

			GameRegistry.registerItem(tier1RadiationLayer, tier1RadiationLayer.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(tier2RadiationLayer, tier2RadiationLayer.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(tier3RadiationLayer, tier3RadiationLayer.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(tier4RadiationLayer, tier4RadiationLayer.getUnlocalizedName().substring(5));

			GameRegistry.registerItem(tier1ArmorLayer, tier1ArmorLayer.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(tier2ArmorLayer, tier2ArmorLayer.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(tier3ArmorLayer, tier3ArmorLayer.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(tier4ArmorLayer, tier4ArmorLayer.getUnlocalizedName().substring(5));

			GameRegistry.registerItem(tier1UnPreparedSpaceSuitHelmet, tier1UnPreparedSpaceSuitHelmet.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(tier1UnPreparedSpaceSuitChest, tier1UnPreparedSpaceSuitChest.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(tier1UnPreparedSpaceSuitLegings, tier1UnPreparedSpaceSuitLegings.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(tier1UnPreparedSpaceSuitBoots, tier1UnPreparedSpaceSuitBoots.getUnlocalizedName().substring(5));

			GameRegistry.registerItem(tier2UnPreparedSpaceSuitHelmet, tier2UnPreparedSpaceSuitHelmet.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(tier2UnPreparedSpaceSuitChest, tier2UnPreparedSpaceSuitChest.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(tier2UnPreparedSpaceSuitLegings, tier2UnPreparedSpaceSuitLegings.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(tier2UnPreparedSpaceSuitBoots, tier2UnPreparedSpaceSuitBoots.getUnlocalizedName().substring(5));

			GameRegistry.registerItem(tier3UnPreparedSpaceSuitHelmet, tier3UnPreparedSpaceSuitHelmet.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(tier3UnPreparedSpaceSuitChest, tier3UnPreparedSpaceSuitChest.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(tier3UnPreparedSpaceSuitLegings, tier3UnPreparedSpaceSuitLegings.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(tier3UnPreparedSpaceSuitBoots, tier3UnPreparedSpaceSuitBoots.getUnlocalizedName().substring(5));

			GameRegistry.registerItem(tier4UnPreparedSpaceSuitHelmet, tier4UnPreparedSpaceSuitHelmet.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(tier4UnPreparedSpaceSuitChest, tier4UnPreparedSpaceSuitChest.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(tier4UnPreparedSpaceSuitLegings, tier4UnPreparedSpaceSuitLegings.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(tier4UnPreparedSpaceSuitBoots, tier4UnPreparedSpaceSuitBoots.getUnlocalizedName().substring(5));
		}
		if (Config.radiation) {
			GameRegistry.registerItem(iodideSalt, iodideSalt.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(potassiumIodide, potassiumIodide.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(potassium, potassium.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(potash, potash.getUnlocalizedName().substring(5));
			GameRegistry.registerItem(anti_radiation, anti_radiation.getUnlocalizedName().substring(5));
		}
		// GameRegistry.registerItem(cannedFood, "cannedFood");
		GameRegistry.registerItem(wafers, wafers.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(ingotLead, ingotLead.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(cloth, cloth.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(gravityController, gravityController.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(clean_water_bucket, "bucket_clean_water");
		GameRegistry.registerItem(infected_water_bucket, "bucket_infected_water");
		;
		GameRegistry.registerItem(radioactive_bucket, "bucket_radioactive_water");
		GameRegistry.registerItem(methane_bucket, "bucket_methane");
		GameRegistry.registerItem(nitrogen_ice_bucket, "bucket_nitrogen_ice");
	}

	private static void registerFluidContainer() {
		if (Config.saturn) {
			FluidContainerRegistry.registerFluidContainer(ExtraPlanets_Fluids.glowstone_fluid, new ItemStack(glowstone_bucket, 1, 0), new ItemStack(Items.BUCKET));
		}
		if (Config.jupiter) {
			FluidContainerRegistry.registerFluidContainer(ExtraPlanets_Fluids.magma_fluid, new ItemStack(magma_bucket, 1, 0), new ItemStack(Items.BUCKET));
		}
		if (Config.neptune) {
			FluidContainerRegistry.registerFluidContainer(ExtraPlanets_Fluids.nitrogen_fluid, new ItemStack(nitrogen_bucket, 1, 0), new ItemStack(Items.BUCKET));
		}
		if (Config.uranus) {
			FluidContainerRegistry.registerFluidContainer(ExtraPlanets_Fluids.frozen_water_fluid, new ItemStack(frozen_water_bucket, 1, 0), new ItemStack(Items.BUCKET));
		}
		if (Config.ceres) {
			FluidContainerRegistry.registerFluidContainer(ExtraPlanets_Fluids.salt_fluid, new ItemStack(salt_bucket, 1, 0), new ItemStack(Items.BUCKET));
		}
		FluidContainerRegistry.registerFluidContainer(ExtraPlanets_Fluids.cleanWater_fluid, new ItemStack(clean_water_bucket, 1, 0), new ItemStack(Items.BUCKET));
		FluidContainerRegistry.registerFluidContainer(ExtraPlanets_Fluids.infectedWater_fluid, new ItemStack(infected_water_bucket, 1, 0), new ItemStack(Items.BUCKET));
		FluidContainerRegistry.registerFluidContainer(ExtraPlanets_Fluids.radioactiveWater_fluid, new ItemStack(radioactive_bucket, 1, 0), new ItemStack(Items.BUCKET));
		FluidContainerRegistry.registerFluidContainer(ExtraPlanets_Fluids.methane_fluid, new ItemStack(methane_bucket, 1, 0), new ItemStack(Items.BUCKET));
		FluidContainerRegistry.registerFluidContainer(ExtraPlanets_Fluids.nitrogen_ice_fluid, new ItemStack(nitrogen_ice_bucket, 1, 0), new ItemStack(Items.BUCKET));
	}

	public static void OreDictionaryRegister() {
		if (Config.mercury) {
			OreDictionary.registerOre("ingotMercury", new ItemStack(ingotMercury));
			OreDictionary.registerOre("ingotCarbon", new ItemStack(tier4Items, 1, 5));
		}
		if (Config.ceres)
			OreDictionary.registerOre("ingotUranium", new ItemStack(ingotUranium));
		if (Config.jupiter) {
			OreDictionary.registerOre("ingotPalladium", new ItemStack(tier5Items, 1, 5));
			OreDictionary.registerOre("ingotNickel", new ItemStack(tier5Items, 1, 7));
			OreDictionary.registerOre("gemRedGem", new ItemStack(tier5Items, 1, 8));
		}
		if (Config.saturn)
			OreDictionary.registerOre("ingotMagnesium", new ItemStack(tier6Items, 1, 5));
		if (Config.uranus) {
			OreDictionary.registerOre("ingotCrystal", new ItemStack(tier7Items, 1, 5));
			OreDictionary.registerOre("gemWhiteGem", new ItemStack(tier7Items, 1, 7));
		}
		if (Config.neptune) {
			OreDictionary.registerOre("ingotZinc", new ItemStack(tier8Items, 1, 5));
			OreDictionary.registerOre("gemBlueGem", new ItemStack(tier8Items, 1, 6));
		}
		if (Config.pluto)
			OreDictionary.registerOre("ingotTungsten", new ItemStack(tier9Items, 1, 5));
		if (Config.eris)
			OreDictionary.registerOre("ingotDarkIron", new ItemStack(tier10Items, 1, 5));
		if (Config.kepler22b && Config.keplerSolarSystems) {
			OreDictionary.registerOre("gemBlueDiamond", new ItemStack(tier11Items, 1, 0));
			OreDictionary.registerOre("gemRedDiamond", new ItemStack(tier11Items, 1, 1));
			OreDictionary.registerOre("gemPurpleDiamond", new ItemStack(tier11Items, 1, 2));
			OreDictionary.registerOre("gemYellowDiamond", new ItemStack(tier11Items, 1, 3));
			OreDictionary.registerOre("gemGreenDiamond", new ItemStack(tier11Items, 1, 4));
		}
		OreDictionary.registerOre("ingotLead", new ItemStack(ingotLead));
	}

	public static void registerGearItems() {
		if (Config.oxygenTanks) {
			GalacticraftRegistry.registerGear(5000, EnumExtendedInventorySlot.LEFT_TANK, oxygenTankVeryHeavy);
			GalacticraftRegistry.registerGear(5000, EnumExtendedInventorySlot.RIGHT_TANK, oxygenTankVeryHeavy);

			GalacticraftRegistry.registerGear(5001, EnumExtendedInventorySlot.LEFT_TANK, oxygenTankExtremelyHeavy);
			GalacticraftRegistry.registerGear(5001, EnumExtendedInventorySlot.RIGHT_TANK, oxygenTankExtremelyHeavy);
		}
		if (Config.thermalPaddings) {
			GalacticraftRegistry.registerGear(6000, EnumExtendedInventorySlot.THERMAL_HELMET, new ItemStack(tier3ThermalPadding, 1, 0));
			GalacticraftRegistry.registerGear(6001, EnumExtendedInventorySlot.THERMAL_CHESTPLATE, new ItemStack(tier3ThermalPadding, 1, 1));
			GalacticraftRegistry.registerGear(6002, EnumExtendedInventorySlot.THERMAL_LEGGINGS, new ItemStack(tier3ThermalPadding, 1, 2));
			GalacticraftRegistry.registerGear(6003, EnumExtendedInventorySlot.THERMAL_BOOTS, new ItemStack(tier3ThermalPadding, 1, 3));

			GalacticraftRegistry.registerGear(6004, EnumExtendedInventorySlot.THERMAL_HELMET, new ItemStack(tier4ThermalPadding, 1, 0));
			GalacticraftRegistry.registerGear(6005, EnumExtendedInventorySlot.THERMAL_CHESTPLATE, new ItemStack(tier4ThermalPadding, 1, 1));
			GalacticraftRegistry.registerGear(6006, EnumExtendedInventorySlot.THERMAL_LEGGINGS, new ItemStack(tier4ThermalPadding, 1, 2));
			GalacticraftRegistry.registerGear(6007, EnumExtendedInventorySlot.THERMAL_BOOTS, new ItemStack(tier4ThermalPadding, 1, 3));

			GalacticraftRegistry.registerGear(6008, EnumExtendedInventorySlot.THERMAL_HELMET, new ItemStack(tier5ThermalPadding, 1, 0));
			GalacticraftRegistry.registerGear(6009, EnumExtendedInventorySlot.THERMAL_CHESTPLATE, new ItemStack(tier5ThermalPadding, 1, 1));
			GalacticraftRegistry.registerGear(6010, EnumExtendedInventorySlot.THERMAL_LEGGINGS, new ItemStack(tier5ThermalPadding, 1, 2));
			GalacticraftRegistry.registerGear(6011, EnumExtendedInventorySlot.THERMAL_BOOTS, new ItemStack(tier5ThermalPadding, 1, 3));
		}
	}
}
