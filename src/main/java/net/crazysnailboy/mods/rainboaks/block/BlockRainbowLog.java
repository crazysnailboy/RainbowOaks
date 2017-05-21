package net.crazysnailboy.mods.rainboaks.block;

import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;

public class BlockRainbowLog extends BlockLog
{

	public BlockRainbowLog()
	{
		super();
		this.setDefaultState(this.blockState.getBaseState().withProperty(LOG_AXIS, BlockLog.EnumAxis.Y));
	}

	@SuppressWarnings("deprecation")
	@Override
	public MapColor getMapColor(IBlockState state)
	{
		return BlockPlanks.EnumType.OAK.getMapColor();
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		IBlockState iblockstate = this.getDefaultState();
		switch (meta & 12)
		{
			case 0:
				iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.Y);
				break;
			case 4:
				iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.X);
				break;
			case 8:
				iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.Z);
				break;
			default:
				iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.NONE);
		}

		return iblockstate;
	}

	@Override
	@SuppressWarnings("incomplete-switch")
	public int getMetaFromState(IBlockState state)
	{
		int i = 0;

		switch ((BlockLog.EnumAxis)state.getValue(LOG_AXIS))
		{
			case X:
				i |= 4;
				break;
			case Z:
				i |= 8;
				break;
			case NONE:
				i |= 12;
		}

		return i;
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] { LOG_AXIS });
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return 0;
	}

}
