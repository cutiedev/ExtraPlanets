package com.mjr.extraplanets.handlers;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;
import com.mjr.extraplanets.Config;
import com.mjr.extraplanets.Constants;
import com.mjr.extraplanets.ExtraPlanets;
import com.mjr.extraplanets.api.item.IModularArmor;
import com.mjr.extraplanets.api.item.IPressureSuit;
import com.mjr.extraplanets.api.item.IRadiationSuit;
import com.mjr.extraplanets.api.prefabs.entity.EntityElectricRocketBase;
import com.mjr.extraplanets.api.prefabs.world.WorldProviderRealisticSpace;
import com.mjr.extraplanets.blocks.fluid.ExtraPlanets_Fluids;
import com.mjr.extraplanets.blocks.fluid.FluidBlockEP;
import com.mjr.extraplanets.client.handlers.capabilities.CapabilityProviderStatsClient;
import com.mjr.extraplanets.client.handlers.capabilities.CapabilityStatsClientHandler;
import com.mjr.extraplanets.compatibility.MachineMusePowersuitsCompatibility;
import com.mjr.extraplanets.handlers.capabilities.CapabilityProviderStats;
import com.mjr.extraplanets.handlers.capabilities.CapabilityStatsHandler;
import com.mjr.extraplanets.handlers.capabilities.IStatsCapability;
import com.mjr.extraplanets.items.ExtraPlanets_Items;
import com.mjr.extraplanets.items.armor.Tier0SpaceSuitArmor;
import com.mjr.extraplanets.items.armor.modules.Module;
import com.mjr.extraplanets.items.armor.modules.ModuleHelper;
import com.mjr.extraplanets.network.ExtraPlanetsPacketHandler;
import com.mjr.extraplanets.network.PacketSimpleEP;
import com.mjr.extraplanets.network.PacketSimpleEP.EnumSimplePacket;
import com.mjr.extraplanets.util.DamageSourceEP;
import com.mjr.mjrlegendslib.util.MessageUtilities;
import com.mjr.mjrlegendslib.util.PlayerUtilties;
import com.mjr.mjrlegendslib.util.TranslateUtilities;

import micdoodle8.mods.galacticraft.api.prefab.entity.EntitySpaceshipBase;
import micdoodle8.mods.galacticraft.api.world.IGalacticraftWorldProvider;
import micdoodle8.mods.galacticraft.core.dimension.WorldProviderMoon;
import micdoodle8.mods.galacticraft.core.dimension.WorldProviderSpaceStation;
import micdoodle8.mods.galacticraft.core.entities.EntityLanderBase;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerHandler.ThermalArmorEvent;
import micdoodle8.mods.galacticraft.core.util.CompatibilityManager;
import micdoodle8.mods.galacticraft.core.util.OxygenUtil;
import micdoodle8.mods.galacticraft.planets.asteroids.dimension.WorldProviderAsteroids;
import micdoodle8.mods.galacticraft.planets.asteroids.items.AsteroidsItems;
import micdoodle8.mods.galacticraft.planets.mars.dimension.WorldProviderMars;
import micdoodle8.mods.galacticraft.planets.venus.dimension.WorldProviderVenus;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemHandlerHelper;

public class MainHandlerServer {

	private static List<ExtraPlanetsPacketHandler> packetHandlers = Lists.newCopyOnWriteArrayList();

	public static void addPacketHandler(ExtraPlanetsPacketHandler handler) {
		MainHandlerServer.packetHandlers.add(handler);
	}

	@SubscribeEvent
	public void worldUnloadEvent(WorldEvent.Unload event) {
		for (ExtraPlanetsPacketHandler packetHandler : packetHandlers) {
			packetHandler.unload(event.getWorld());
		}
	}

	@SubscribeEvent
	public void onWorldTick(WorldTickEvent event) {
		if (event.phase == Phase.END) {
			final WorldServer world = (WorldServer) event.world;

			for (ExtraPlanetsPacketHandler handler : packetHandlers) {
				handler.tick(world);
			}
		}
	}

	@SubscribeEvent
	public void onPlayerCloned(PlayerEvent.Clone event) {
		IStatsCapability oldStats = event.getOriginal().getCapability(CapabilityStatsHandler.EP_STATS_CAPABILITY, null);
		IStatsCapability newStats = event.getEntityPlayer().getCapability(CapabilityStatsHandler.EP_STATS_CAPABILITY, null);
		newStats.copyFrom(oldStats, !event.isWasDeath() || event.getOriginal().world.getGameRules().getBoolean("keepInventory"));
	}

	@SubscribeEvent
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		if (event.player instanceof EntityPlayerMP) {
			EntityPlayerMP player = (EntityPlayerMP) event.player;
			World world = event.player.world;

			IBlockState blockTest = world.getBlockState(player.getPosition());
			if (blockTest.getBlock() instanceof FluidBlockEP) {
				BlockPos block = world.getTopSolidOrLiquidBlock(player.getPosition().add(1, 1, 0));
				world.setBlockState(block, world.getBiome(block).topBlock);
				ExtraPlanets.packetPipeline.sendTo(new PacketSimpleEP(EnumSimplePacket.C_MOVE_PLAYER, world.provider.getDimensionType().getId(), new Object[] { block }), player);
			}
		}
	}

	@SubscribeEvent
	public void onEntityDealth(LivingDeathEvent event) {
		if (event.getEntity() instanceof EntityPlayerMP) {
			final EntityLivingBase entityLiving = event.getEntityLiving();
			IStatsCapability stats = null;

			if (entityLiving != null) {
				stats = entityLiving.getCapability(CapabilityStatsHandler.EP_STATS_CAPABILITY, null);
			}
			if (stats.getRadiationLevel() >= 85)
				stats.setRadiationLevel(80);
			else if (stats.getRadiationLevel() >= 65 && stats.getRadiationLevel() < 85)
				stats.setRadiationLevel(60);
			else if (stats.getRadiationLevel() >= 50 && stats.getRadiationLevel() < 65)
				stats.setRadiationLevel(50);
		}
	}

	@SubscribeEvent
	public void onPlayer(PlayerTickEvent event) {
		if (Config.JUITPER_LIGHTING_SERVER && event.player.world.provider.getDimensionType().getId() == Config.JUPITER_ID) {
			Random rand = new Random();
			int addX = rand.nextInt(64);
			int addZ = rand.nextInt(64);
			if (rand.nextInt(2) == 1)
				addX = -addX;
			if (rand.nextInt(2) == 1)
				addZ = -addZ;
			if (addX <= 10)
				addX = 10;
			if (addZ <= 10)
				addZ = 10;
			int lightingSpawnChance = rand.nextInt(100);
			if (lightingSpawnChance == 10) {
				event.player.world.addWeatherEffect(new EntityLightningBolt(event.player.world, event.player.posX + addX, event.player.world.getTopSolidOrLiquidBlock(new BlockPos(event.player.posX + addX, 0, (int) event.player.posZ + addZ)).getY(),
						event.player.posZ + addZ, false));
			}
		}
	}

	@SubscribeEvent
	public void onThermalArmorEvent(ThermalArmorEvent event) {
		if (event.armorStack == null) {
			event.setArmorAddResult(ThermalArmorEvent.ArmorAddResult.REMOVE);
			return;
		}
		if (event.armorStack.getItem() == AsteroidsItems.thermalPadding && event.armorStack.getItemDamage() == event.armorIndex) {
			event.setArmorAddResult(ThermalArmorEvent.ArmorAddResult.ADD);
			return;
		}
		if (event.armorStack.getItem() == ExtraPlanets_Items.TIER_3_THERMAL_PADDING && event.armorStack.getItemDamage() == event.armorIndex) {
			event.setArmorAddResult(ThermalArmorEvent.ArmorAddResult.ADD);
			return;
		}
		if (event.armorStack.getItem() == ExtraPlanets_Items.TIER_4_THERMAL_PADDING && event.armorStack.getItemDamage() == event.armorIndex) {
			event.setArmorAddResult(ThermalArmorEvent.ArmorAddResult.ADD);
			return;
		}
		if (event.armorStack.getItem() == ExtraPlanets_Items.TIER_5_THERMAL_PADDING && event.armorStack.getItemDamage() == event.armorIndex) {
			event.setArmorAddResult(ThermalArmorEvent.ArmorAddResult.ADD);
			return;
		}
		event.setArmorAddResult(ThermalArmorEvent.ArmorAddResult.NOTHING);
	}

	@SubscribeEvent
	public void onCommandEvent(CommandEvent event) {
		if (event.getCommand().getName().equalsIgnoreCase("gckit")) {
			if (event.getParameters().length == 0) {
				if (event.getSender() instanceof EntityPlayerMP)
					ItemHandlerHelper.giveItemToPlayer((EntityPlayerMP) event.getSender(), new ItemStack(ExtraPlanets_Items.ENVIRO_EMERGENCY_KIT), 0);
			} else {
				ItemHandlerHelper.giveItemToPlayer(event.getSender().getServer().getPlayerList().getPlayerByUsername(event.getParameters()[0]), new ItemStack(ExtraPlanets_Items.ENVIRO_EMERGENCY_KIT), 0);
			}

		}
	}

	@SubscribeEvent
	public void onAttachCapability(AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof EntityPlayerMP) {
			event.addCapability(CapabilityStatsHandler.EP_PLAYER_PROP, new CapabilityProviderStats((EntityPlayerMP) event.getObject()));
		} else if (event.getObject() instanceof EntityPlayer && ((EntityPlayer) event.getObject()).world.isRemote) {
			this.onAttachCapabilityClient(event);
		}
	}

	@SideOnly(Side.CLIENT)
	private void onAttachCapabilityClient(AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof EntityPlayerSP)
			event.addCapability(CapabilityStatsClientHandler.EP_PLAYER_CLIENT_PROP, new CapabilityProviderStatsClient((EntityPlayerSP) event.getObject()));
	}

	public boolean isInGlowstone(EntityPlayerMP player) {
		return player.world.isMaterialInBB(player.getEntityBoundingBox().expand(-0.10000000149011612D, -0.4000000059604645D, -0.10000000149011612D), ExtraPlanets_Fluids.GLOWSTONE_MATERIAL);
	}
	
	public boolean isInMagma(EntityPlayerMP player) {
		return player.world.isMaterialInBB(player.getEntityBoundingBox().expand(-0.10000000149011612D, -0.4000000059604645D, -0.10000000149011612D), ExtraPlanets_Fluids.MAGMA_MATERIAL);
	}

	@SubscribeEvent
	public void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
		final EntityLivingBase entityLiving = event.getEntityLiving();
		if (entityLiving instanceof EntityPlayerMP) {
			tickTempSpaceSuit(event, entityLiving);
			tickModules(event, entityLiving);
			if (isInGlowstone((EntityPlayerMP) entityLiving))
				entityLiving.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 500, 0));
			else if (isInMagma((EntityPlayerMP) entityLiving))
				if (!entityLiving.isImmuneToFire())
					entityLiving.setFire(18);
			onPlayerUpdate((EntityPlayerMP) entityLiving);
			if (OxygenUtil.isAABBInBreathableAirBlock(entityLiving.world, entityLiving.getEntityBoundingBox(), true) == false
					&& !(entityLiving.world.getBlockState(new BlockPos(entityLiving.posX, entityLiving.posY, entityLiving.posZ)).getBlock() instanceof BlockFluidBase))
				runChecks(event, entityLiving);

		}
	}

	private void tickTempSpaceSuit(LivingUpdateEvent event, EntityLivingBase entityLiving) {
		EntityPlayerMP player = (EntityPlayerMP) entityLiving;

		if (player.capabilities.isCreativeMode)
			return;
		if (player.isSpectator())
			return;

		ItemStack helmet = player.inventory.armorInventory.get(3);
		ItemStack chest = player.inventory.armorInventory.get(2);
		ItemStack leggins = player.inventory.armorInventory.get(1);
		ItemStack boots = player.inventory.armorInventory.get(0);

		if (helmet.getItem() instanceof Tier0SpaceSuitArmor) {
			Tier0SpaceSuitArmor.setTicksLeft(helmet, Tier0SpaceSuitArmor.getTicksLeft(helmet) - 1);
			if (Tier0SpaceSuitArmor.getTicksLeft(helmet) <= 0) {
				player.inventory.armorInventory.set(3, ItemStack.EMPTY);
			}
		}
		if (chest.getItem() instanceof Tier0SpaceSuitArmor) {
			Tier0SpaceSuitArmor.setTicksLeft(chest, Tier0SpaceSuitArmor.getTicksLeft(chest) - 1);
			if (Tier0SpaceSuitArmor.getTicksLeft(chest) <= 0) {
				player.inventory.armorInventory.set(2, ItemStack.EMPTY);
			}
		}
		if (leggins.getItem() instanceof Tier0SpaceSuitArmor) {
			Tier0SpaceSuitArmor.setTicksLeft(leggins, Tier0SpaceSuitArmor.getTicksLeft(leggins) - 1);
			if (Tier0SpaceSuitArmor.getTicksLeft(leggins) <= 0) {
				player.inventory.armorInventory.set(1, ItemStack.EMPTY);
			}
		}
		if (boots.getItem() instanceof Tier0SpaceSuitArmor) {
			Tier0SpaceSuitArmor.setTicksLeft(boots, Tier0SpaceSuitArmor.getTicksLeft(boots) - 1);
			if (Tier0SpaceSuitArmor.getTicksLeft(boots) <= 0) {
				player.inventory.armorInventory.set(0, ItemStack.EMPTY);
			}
		}

		if ((player.ticksExisted - 1) % 90 == 0) {
			for (ItemStack item : player.inventory.mainInventory) {
				if (item.getItem() instanceof Tier0SpaceSuitArmor) {
					item.setCount(0);
				}
			}
		}
	}

	private void tickModules(LivingUpdateEvent event, EntityLivingBase entityLiving) {
		EntityPlayerMP player = (EntityPlayerMP) entityLiving;

		if (player.isSpectator())
			return;

		ItemStack helmet = player.inventory.armorInventory.get(3);
		ItemStack chest = player.inventory.armorInventory.get(2);
		ItemStack leggins = player.inventory.armorInventory.get(1);
		ItemStack boots = player.inventory.armorInventory.get(0);

		if (helmet.getItem() instanceof IModularArmor)
			for (Module hemletModules : ModuleHelper.getModules(helmet)) {
				if (hemletModules.isActive()) {
					int passivePower = ModuleHelper.getModulePassiveCost(hemletModules);
					if ((player.ticksExisted - 1) % 20 == 0 && ModuleHelper.hasPower(helmet, passivePower))
						ModuleHelper.takeArmourPower(helmet, passivePower);
					if (ModuleHelper.hasPower(helmet, ModuleHelper.getModuleUseCost(hemletModules)))
						hemletModules.tickServer(player);
				}
			}
		if (chest.getItem() instanceof IModularArmor)
			for (Module chestModules : ModuleHelper.getModules(chest)) {
				if (chestModules.isActive()) {
					int passivePower = ModuleHelper.getModulePassiveCost(chestModules);
					if ((player.ticksExisted - 1) % 20 == 0 && ModuleHelper.hasPower(chest, passivePower))
						ModuleHelper.takeArmourPower(chest, passivePower);
					if (ModuleHelper.hasPower(helmet, ModuleHelper.getModuleUseCost(chestModules)))
						chestModules.tickServer(player);
				}
			}
		if (leggins.getItem() instanceof IModularArmor)
			for (Module legginsModules : ModuleHelper.getModules(leggins)) {
				if (legginsModules.isActive()) {
					int passivePower = ModuleHelper.getModulePassiveCost(legginsModules);
					if ((player.ticksExisted - 1) % 20 == 0 && ModuleHelper.hasPower(leggins, passivePower))
						ModuleHelper.takeArmourPower(leggins, passivePower);
					if (ModuleHelper.hasPower(helmet, ModuleHelper.getModuleUseCost(legginsModules)))
						legginsModules.tickServer(player);
				}
			}
		if (boots.getItem() instanceof IModularArmor)
			for (Module bootsModules : ModuleHelper.getModules(boots)) {
				if (bootsModules.isActive()) {
					int passivePower = ModuleHelper.getModulePassiveCost(bootsModules);
					if ((player.ticksExisted - 1) % 20 == 0 && ModuleHelper.hasPower(boots, passivePower))
						ModuleHelper.takeArmourPower(boots, passivePower);
					if (ModuleHelper.hasPower(helmet, ModuleHelper.getModuleUseCost(bootsModules)))
						bootsModules.tickServer(player);
				}
			}
	}

	private void runChecks(LivingEvent.LivingUpdateEvent event, EntityLivingBase entityLiving) {
		EntityPlayerMP player = (EntityPlayerMP) entityLiving;
		if (player.capabilities.isCreativeMode)
			return;
		if (player.isSpectator())
			return;
		if ((entityLiving.getRidingEntity() instanceof EntityLanderBase))
			return;
		if ((entityLiving.getRidingEntity() instanceof EntityElectricRocketBase))
			return;
		if ((entityLiving.getRidingEntity() instanceof EntitySpaceshipBase))
			return;
		if (entityLiving.world.provider instanceof IGalacticraftWorldProvider) {
			List<String> list = Lists.newArrayList(Config.SPACE_SUIT_SUPPORTED_ARMOUR);
			if (((EntityPlayerMP) entityLiving).world.provider instanceof WorldProviderRealisticSpace) {
				if (Config.PRESSURE)
					checkPressure(event, player, ((WorldProviderRealisticSpace) player.world.provider).getPressureLevel(), list);
				if (Config.RADIATION)
					checkRadiation(event, player, ((WorldProviderRealisticSpace) player.world.provider).getSolarRadiationLevel(), list);
			} else if (player.world.provider instanceof WorldProviderMoon) {
				if (Config.GC_PRESSURE && Config.PRESSURE)
					checkPressure(event, player, 80, list);
				if (Config.GC_RADIATION && Config.RADIATION)
					checkRadiation(event, player, Config.MOON_RADIATION_AMOUNT, list);
			} else if (player.world.provider instanceof WorldProviderMars) {
				if (Config.GC_PRESSURE && Config.PRESSURE)
					checkPressure(event, player, 90, list);
				if (Config.GC_RADIATION && Config.RADIATION)
					checkRadiation(event, player, Config.MARS_RADIATION_AMOUNT, list);
			} else if (player.world.provider instanceof WorldProviderVenus) {
				if (Config.GC_PRESSURE && Config.PRESSURE)
					checkPressure(event, player, 100, list);
				if (Config.GC_RADIATION && Config.RADIATION)
					checkRadiation(event, player, Config.VENUS_RADIATION_AMOUNT, list);
			} else if (player.world.provider instanceof WorldProviderAsteroids) {
				if (Config.GC_PRESSURE && Config.PRESSURE)
					checkPressure(event, player, 100, list);
				if (Config.GC_RADIATION && Config.RADIATION)
					checkRadiation(event, player, Config.ASTEROIDS_RADIATION_AMOUNT, list);
			} else if (player.world.provider instanceof WorldProviderSpaceStation) {
				if (Config.GC_PRESSURE && Config.PRESSURE)
					checkPressure(event, player, 100, list);
				if (Config.GC_RADIATION && Config.RADIATION)
					checkRadiation(event, player, Config.SPACE_STATION_RADIATION_AMOUNT, list);
			} else if(Config.OTHER_ADDON_PLANET_MOON_RAD_VALUES_LIST.containsKey(((IGalacticraftWorldProvider)player.world.provider).getCelestialBody().getUnlocalizedName())) {
				checkRadiation(event, player, Config.OTHER_ADDON_PLANET_MOON_RAD_VALUES_LIST.get(((IGalacticraftWorldProvider)player.world.provider).getCelestialBody().getUnlocalizedName()), list);
			}

		}
	}

	public boolean isValidSpaceSuit(EntityPlayer player, ItemStack helmet, ItemStack chest, ItemStack leggins, ItemStack boots, List<String> list, boolean pressure) {
		// MatterOverdrive Mod Compact
		if (CompatibilityManager.isAndroid(player))
			return true;
		// Check for slot filled
		if (helmet == ItemStack.EMPTY)
			return false;
		if (chest == ItemStack.EMPTY)
			return false;
		if (leggins == ItemStack.EMPTY)
			return false;
		if (boots == ItemStack.EMPTY)
			return false;

		boolean validHelmet = false;
		boolean validChest = false;
		boolean validLeggings = false;
		boolean validBoots = false;

		// ExtraPlanets Space Suit/Interface compact
		if (pressure) {
			if (helmet.getItem() instanceof IPressureSuit)
				validHelmet = true;
			if (chest.getItem() instanceof IPressureSuit)
				validChest = true;
			if (leggins.getItem() instanceof IPressureSuit)
				validLeggings = true;
			if (boots.getItem() instanceof IPressureSuit)
				validBoots = true;
		} else {
			if (helmet.getItem() instanceof IRadiationSuit)
				validHelmet = true;
			if (chest.getItem() instanceof IRadiationSuit)
				validChest = true;
			if (leggins.getItem() instanceof IRadiationSuit)
				validLeggings = true;
			if (boots.getItem() instanceof IRadiationSuit)
				validBoots = true;
		}
		if (validHelmet && validChest && validLeggings && validBoots)
			return true;
		else {
			if (Loader.isModLoaded("powersuits")) {
				if (pressure) {
					if (!validHelmet)
						validHelmet = MachineMusePowersuitsCompatibility.isPressureModuleInstalled(helmet);
					if (!validChest)
						validChest = MachineMusePowersuitsCompatibility.isPressureModuleInstalled(chest);
					if (!validLeggings)
						validLeggings = MachineMusePowersuitsCompatibility.isPressureModuleInstalled(leggins);
					if (!validBoots)
						validBoots = MachineMusePowersuitsCompatibility.isPressureModuleInstalled(boots);
				} else {
					if (!validHelmet)
						validHelmet = MachineMusePowersuitsCompatibility.isRadiationModuleInstalled(helmet);
					if (!validChest)
						validChest = MachineMusePowersuitsCompatibility.isRadiationModuleInstalled(chest);
					if (!validLeggings)
						validLeggings = MachineMusePowersuitsCompatibility.isRadiationModuleInstalled(leggins);
					if (!validBoots)
						validBoots = MachineMusePowersuitsCompatibility.isRadiationModuleInstalled(boots);
				}
				if (validHelmet && validChest && validLeggings && validBoots)
					return true;
			}

			// Config List of armour items to be considered as a space suit compact
			for (String temp : list) {
				temp = temp.substring(0, temp.lastIndexOf(':'));
				if (temp.equalsIgnoreCase(helmet.getItem().getRegistryName().toString()))
					validHelmet = true;
			}
			for (String temp : list) {
				temp = temp.substring(0, temp.lastIndexOf(':'));
				if (temp.equalsIgnoreCase(chest.getItem().getRegistryName().toString()))
					validChest = true;
			}
			for (String temp : list) {
				temp = temp.substring(0, temp.lastIndexOf(':'));
				if (temp.equalsIgnoreCase(leggins.getItem().getRegistryName().toString()))
					validLeggings = true;
			}
			for (String temp : list) {
				temp = temp.substring(0, temp.lastIndexOf(':'));
				if (temp.equalsIgnoreCase(boots.getItem().getRegistryName().toString()))
					validBoots = true;
			}

			if (validHelmet && validChest && validLeggings && validBoots)
				return true;
			else
				return false;
		}
	}

	public int getTier(ItemStack testItem, List<String> list) {
		if (testItem.getItem() instanceof IRadiationSuit)
			return ((IRadiationSuit) testItem.getItem()).getArmorTier();
		for (String line : list) {
			if(line.length() == 0 || !line.contains(":"))
				continue;
			if (line.substring(0, line.lastIndexOf(':')).equalsIgnoreCase(testItem.getItem().getRegistryName().toString()))
				return Integer.parseInt(line.substring(line.lastIndexOf(':') + 1));
		}
		if (Loader.isModLoaded("powersuits")) {
			return MachineMusePowersuitsCompatibility.getHighestRadiationTierModuleInstalled(testItem);
		}
		return -1;
	}

	private void checkPressure(LivingEvent.LivingUpdateEvent event, EntityPlayerMP playerMP, int amount, List<String> list) {
		if ((playerMP.ticksExisted - 1) % 50 == 0) {
			if (amount == 0)
				return;
			if ((playerMP.ticksExisted - 1) % 300 == 0 && Config.DEBUG_MODE)
				MessageUtilities.debugMessageToLog(Constants.modID, "Environment Pressure Amount: " + amount);
			ItemStack helmet = playerMP.inventory.armorInventory.get(3);
			ItemStack chest = playerMP.inventory.armorInventory.get(2);
			ItemStack leggins = playerMP.inventory.armorInventory.get(1);
			ItemStack boots = playerMP.inventory.armorInventory.get(0);

			if (!isValidSpaceSuit(playerMP, helmet, chest, leggins, boots, list, true)) {
				float tempLevel = amount;
				tempLevel = (tempLevel / 100) * 8;
				if ((playerMP.ticksExisted - 1) % 100 == 0 && Config.DEBUG_MODE)
					MessageUtilities.debugMessageToLog(Constants.modID, "Damage Amount for Pressure: " + tempLevel);
				playerMP.attackEntityFrom(DamageSourceEP.pressure, tempLevel);
			}
		}
	}

	private void checkRadiation(LivingEvent.LivingUpdateEvent event, EntityPlayerMP playerMP, int amount, List<String> list) {
		// Tier 1 Space Suit
		// 25 Level = 36 mins
		// 50 Level = 14 mins
		// Tier 2 Space Suit
		// 25 Level = 38 mins
		// 50 Level = 15 mins

		if (amount == 0)
			return;
		if ((playerMP.ticksExisted - 1) % 300 == 0 && Config.DEBUG_MODE)
			MessageUtilities.debugMessageToLog(Constants.modID, "Environment Radiation Amount: " + amount);
		boolean doDamage = false;
		boolean doArmorCheck = false;
		double damageToTake = 0;
		double damageModifer = 0;
		int tierValue = 0;

		ItemStack helmet = playerMP.inventory.armorInventory.get(3);
		ItemStack chest = playerMP.inventory.armorInventory.get(2);
		ItemStack leggins = playerMP.inventory.armorInventory.get(1);
		ItemStack boots = playerMP.inventory.armorInventory.get(0);
		if (!isValidSpaceSuit(playerMP, helmet, chest, leggins, boots, list, false)) {
			damageModifer = 0.1;
			doDamage = true;
		} else {
			doArmorCheck = true;
			doDamage = false;
		}
		if (doArmorCheck) {
			double helmetTier = getTier(helmet, list);
			double chestTier = getTier(chest, list);
			double legginsTier = getTier(leggins, list);
			double bootsTier = getTier(boots, list);

			if (helmetTier == 0)
				helmetTier = 0.25;

			if (chestTier == 0)
				chestTier = 0.25;

			if (legginsTier == 0)
				legginsTier = 0.25;

			if (bootsTier == 0)
				bootsTier = 0.25;

			if ((playerMP.ticksExisted - 1) % 100 == 0 && Config.DEBUG_MODE) {
				MessageUtilities.debugMessageToLog(Constants.modID, "Helmet Tier: " + helmetTier);
				MessageUtilities.debugMessageToLog(Constants.modID, "Chest Tier: " + chestTier);
				MessageUtilities.debugMessageToLog(Constants.modID, "Leggins Tier: " + legginsTier);
				MessageUtilities.debugMessageToLog(Constants.modID, "Boots Tier: " + bootsTier);
			}

			tierValue = (int) ((helmetTier + chestTier + legginsTier + bootsTier) / 2);
			damageToTake = 0.005 * tierValue;
			doDamage = true;
		}
		if (doDamage) {
			IStatsCapability stats = null;
			if (playerMP != null) {
				stats = playerMP.getCapability(CapabilityStatsHandler.EP_STATS_CAPABILITY, null);
			}
			if (stats.getRadiationLevel() >= 100) {
				if ((playerMP.ticksExisted - 1) % 50 == 0)
					playerMP.attackEntityFrom(DamageSourceEP.radiation, 3F);
			} else if (stats.getRadiationLevel() >= 0) {
				double tempLevel = 0.0;
				if (amount < 10) {
					damageModifer = 0.005625 - (damageToTake / 2) / 10;
					tempLevel = (damageModifer * amount) / 100;
				} else {
					damageModifer = 0.001875 - (damageToTake / 2) / 10;
					if (damageModifer < 0)
						damageModifer = 0.000225;
					tempLevel = damageModifer * (amount / 10) / 6;
				}
				if ((playerMP.ticksExisted - 1) % 100 == 0 && Config.DEBUG_MODE)
					MessageUtilities.debugMessageToLog(Constants.modID, "Gained amount of Radiation: " + tempLevel);
				stats.setRadiationLevel(stats.getRadiationLevel() + tempLevel);
			} else
				stats.setRadiationLevel(0);
			if ((playerMP.ticksExisted - 1) % 100 == 0 && Config.DEBUG_MODE)
				MessageUtilities.debugMessageToLog(Constants.modID, "Player Current Radiation Amount: " + stats.getRadiationLevel());
		}
	}

	public void onPlayerUpdate(EntityPlayerMP player) {
		int tick = player.ticksExisted - 1;
		final boolean isInGCDimension = player.world.provider instanceof IGalacticraftWorldProvider;
		IStatsCapability stats = player.getCapability(CapabilityStatsHandler.EP_STATS_CAPABILITY, null);

		if ((isInGCDimension || player.world.provider instanceof WorldProviderSpaceStation) && Config.RADIATION) {
			if (tick % 30 == 0) {
				this.sendSolarRadiationPacket(player, stats);
				if (Config.RADIATION_OVERTIME_REDUCE_AMOUNT != 0) {
					double temp = stats.getRadiationLevel();
					double level = (temp * (Config.RADIATION_OVERTIME_REDUCE_AMOUNT * 1.5F)) / 100;
					if (level <= 0)
						stats.setRadiationLevel(0);
					else {
						if (tick % 100 == 0 && Config.DEBUG_MODE)
							MessageUtilities.debugMessageToLog(Constants.modID, "Auto Reduced Radiation Amount: " + level);
						stats.setRadiationLevel(stats.getRadiationLevel() - level);
					}
				}
			}
		} else if (tick % 30 == 0) {
			if (Config.RADIATION_SLEEPING_REDUCE_AMOUNT != 0) {
				double temp = stats.getRadiationLevel();
				double level = (temp * Config.RADIATION_OVERTIME_REDUCE_AMOUNT) / 100;
				if (level <= 0)
					stats.setRadiationLevel(0);
				else {
					stats.setRadiationLevel(stats.getRadiationLevel() - level);
				}
			}
		}
	}

	protected void sendSolarRadiationPacket(EntityPlayerMP player, IStatsCapability stats) {
		ExtraPlanets.packetPipeline.sendTo(new PacketSimpleEP(EnumSimplePacket.C_UPDATE_SOLAR_RADIATION_LEVEL, player.world.provider.getDimensionType().getId(), new Object[] { stats.getRadiationLevel() }), player);
	}

	@SubscribeEvent
	public void onSleepInBedEvent(PlayerWakeUpEvent event) {
		EntityPlayer player = event.getEntityPlayer();
		if (player.world.isRemote == false && (!event.wakeImmediately() && !event.updateWorld())) {
			EntityPlayerMP playerMP = (EntityPlayerMP) player;
			IStatsCapability stats = null;
			if (playerMP != null) {
				stats = playerMP.getCapability(CapabilityStatsHandler.EP_STATS_CAPABILITY, null);
			}
			if (Config.RADIATION_SLEEPING_REDUCE_AMOUNT != 0) {
				double temp = stats.getRadiationLevel();
				double level = (temp * Config.RADIATION_SLEEPING_REDUCE_AMOUNT) / 100;
				if (level <= 0)
					stats.setRadiationLevel(0);
				else {
					stats.setRadiationLevel(stats.getRadiationLevel() - level);
					PlayerUtilties.sendMessage(player, "" + TextFormatting.AQUA + TextFormatting.BOLD + playerMP.getName() + TextFormatting.GOLD + ", " + TranslateUtilities.translate("gui.radiation.reduced.message") + " "
							+ Config.RADIATION_SLEEPING_REDUCE_AMOUNT + "% " + TranslateUtilities.translate("gui.radiation.reduced.message.2"));
					PlayerUtilties.sendMessage(player,
							"" + TextFormatting.AQUA + TextFormatting.BOLD + playerMP.getName() + TextFormatting.DARK_AQUA + ", " + TranslateUtilities.translate("gui.radiation.current.message") + ": " + (int) stats.getRadiationLevel() + "/100");
				}
			}
		}
	}

	@SubscribeEvent
	public void onWorldChange(PlayerChangedDimensionEvent event) {
		if (event.player.world.isRemote == false) {
			if (event.player.world.provider instanceof WorldProviderRealisticSpace || event.player.world.provider instanceof WorldProviderMoon || event.player.world.provider instanceof WorldProviderMars
					|| event.player.world.provider instanceof WorldProviderAsteroids || event.player.world.provider instanceof WorldProviderVenus || event.player.world.provider instanceof WorldProviderSpaceStation) {
				EntityPlayer player = event.player;
				int amount = 0;
				if (event.player.world.provider instanceof WorldProviderRealisticSpace)
					amount = ((WorldProviderRealisticSpace) event.player.world.provider).getSolarRadiationLevel();
				if (event.player.world.provider instanceof WorldProviderMoon)
					amount = Config.MOON_RADIATION_AMOUNT;
				if (event.player.world.provider instanceof WorldProviderMars)
					amount = Config.MARS_RADIATION_AMOUNT;
				if (event.player.world.provider instanceof WorldProviderAsteroids)
					amount = Config.ASTEROIDS_RADIATION_AMOUNT;
				if (event.player.world.provider instanceof WorldProviderVenus)
					amount = Config.VENUS_RADIATION_AMOUNT;
				if (player.world.provider instanceof WorldProviderSpaceStation)
					amount = Config.SPACE_STATION_RADIATION_AMOUNT;
				PlayerUtilties.sendMessage(player, "" + TextFormatting.GOLD + TextFormatting.BOLD + player.getName() + TextFormatting.DARK_RED + ", " + TranslateUtilities.translate("gui.radiation.subject.message") + " " + amount + "/100% "
						+ TranslateUtilities.translate("gui.radiation.type.message") + "");
				PlayerUtilties.sendMessage(player, TextFormatting.DARK_GREEN + TranslateUtilities.translate("gui.radiation.reverse.message") + "!");
				PlayerUtilties.sendMessage(player, TextFormatting.GOLD + TranslateUtilities.translate("gui.radiation.cancel.message") + "!");
				PlayerUtilties.sendMessage(player, TextFormatting.DARK_AQUA + "Radiation Calculator:");
				player.sendMessage(ForgeHooks.newChatWithLinks("https://www.mjrlegends.com/extraplanetsradiation.php"));
			}
		}
	}
}
