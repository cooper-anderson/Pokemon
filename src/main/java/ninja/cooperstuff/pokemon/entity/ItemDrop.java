package ninja.cooperstuff.pokemon.entity;

import ninja.cooperstuff.pokemon.item.ItemStack;
import ninja.cooperstuff.pokemon.world.World;

public class ItemDrop extends Entity {
	private ItemStack itemStack;

	public ItemDrop(World world) {
		super(world);
	}
}
