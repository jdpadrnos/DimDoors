package StevenDimDoors.mod_pocketDim;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class LimboProvider extends WorldProvider
{

	@Override
	public String getDimensionName() {
		// TODO Auto-generated method stub
		return "Limbo";
	}

	public int exitXCoord;
	public int exitYCoord;
	public int exitZCoord;
	public int exitDimID;
	public LimboProvider()
	{
		this.hasNoSky=true;
		super.worldChunkMgr = new WorldChunkManagerLimbo();
	
	

		//  super.setAllowedSpawnTypes(false, false);
		
	}
	
	@Override
	  public BiomeGenBase getBiomeGenForCoords(int x, int z)
		{
			return mod_pocketDim.limboBiome;
		}
	
	  public boolean canRespawnHere()
	    {
		  
	        return mod_pocketDim.hardcoreLimbo&&mod_pocketDim.isLimboActive;
	    }
	  public boolean isBlockHighHumidity(int x, int y, int z)
	    {
	        return false;
	    }
	
	
	  @Override
	    public boolean canSnowAt(int x, int y, int z)
	    {
	        return false;
	    }
	@Override
	 protected void generateLightBrightnessTable()
	    {
	        float modifier = 0.0F;

	        for (int steps = 0; steps <= 15; ++steps)
	        {
	            float var3 = 1.0F - (float)steps / 15.0F;
	            this.lightBrightnessTable[steps] = ((0.0F + var3) / (var3 * 3.0F + 1.0F) * (1.0F - modifier) + modifier)*3;
	       //     System.out.println( this.lightBrightnessTable[steps]+"light");
	        }
	    }
	 
	public void setAllowedSpawnTypes(boolean allowHostile, boolean allowPeaceful)
    {
        super.setAllowedSpawnTypes(false, false);
      
    }

	 public float calculateCelestialAngle(long par1, float par3)
	    {
	        int var4 = (int)(par1 % 24000L);
	        float var5 = ((float)var4 + par3) / 24000.0F - 0.25F;

	        if (var5 < 0.0F)
	        {
	            ++var5;
	        }

	        if (var5 > 1.0F)
	        {
	            --var5;
	        }

	        float var6 = var5;
	        var5 = 1.0F - (float)((Math.cos((double)var5 * Math.PI) + 1.0D) / 2.0D);
	        var5 = var6 + (var5 - var6) / 3.0F;
	        return 0;
	    }

	    @SideOnly(Side.CLIENT)
	    public int getMoonPhase(long par1, float par3)
	    {
	        return 4;
	    }

    @SideOnly(Side.CLIENT)
	@Override
	public String getSaveFolder()
    {
        return (dimensionId == 0 ? null : "DimensionalDoors/Limbo" + dimensionId);
    }
    
    public boolean canCoordinateBeSpawn(int par1, int par2)
    {
        int var3 = this.worldObj.getFirstUncoveredBlock(par1, par2);
        return var3 == mod_pocketDim.blockLimboID;
    }
    @Override
	public double getHorizon()
    {
        return worldObj.getHeight()/4-800;
    }
	public Vec3 getSkyColor(Entity cameraEntity, float partialTicks)
	{
    	setCloudRenderer( new CloudRenderBlank());
        return this.worldObj.getWorldVec3Pool().getVecFromPool((double)0, (double)0, (double)0);

	}
    @SideOnly(Side.CLIENT)
	@Override
	public Vec3 getFogColor(float par1, float par2)
    {
        return this.worldObj.getWorldVec3Pool().getVecFromPool((double).2, (double).2, (double).2);

    }
    public int getRespawnDimension(EntityPlayerMP player)
    {
        return 0;
    }
	
	@Override
	public IChunkProvider createChunkGenerator()
	{
		return new LimboGenerator(worldObj, 45);
	}
	  public boolean canBlockFreeze(int x, int y, int z, boolean byWater)
	    {
	        return false;
	    }

	@Override
	 public ChunkCoordinates getRandomizedSpawnPoint()
    {
        ChunkCoordinates var5 = new ChunkCoordinates(this.worldObj.getSpawnPoint());

     
        int spawnFuzz = 10000;
        int spawnFuzzHalf = spawnFuzz / 2;

        {
            var5.posX += this.worldObj.rand.nextInt(spawnFuzz) - spawnFuzzHalf;
            var5.posZ += this.worldObj.rand.nextInt(spawnFuzz) - spawnFuzzHalf;
            var5.posY = 700;
        }

        return var5;
    
		
	}
	


	
	
}