package com.mjr.extraplanets.moons.Europa.worldgen.biomes;

import java.util.List;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

import com.google.common.collect.Lists;
import com.mjr.extraplanets.blocks.fluid.ExtraPlanets_Fluids;
import com.mjr.extraplanets.moons.Europa.worldgen.EuropaBiomes;

public class BiomeGenEuropaSaltSea extends EuropaBiomes {
	public BiomeGenEuropaSaltSea(BiomeProperties properties) {
		super(properties);
		this.spawnableCreatureList.clear();
		this.topBlock = ExtraPlanets_Fluids.SALT.getDefaultState();
		this.fillerBlock = ExtraPlanets_Fluids.SALT.getDefaultState();
	}

	@Override
	public List<Biome.SpawnListEntry> getSpawnableList(EnumCreatureType creatureType) {
		return Lists.<Biome.SpawnListEntry> newArrayList();
	}

	@Override
	public void registerTypes() {
		BiomeDictionary.addTypes(this, BiomeDictionary.Type.COLD, BiomeDictionary.Type.OCEAN);
	}
}