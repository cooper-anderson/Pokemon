package ninja.cooperstuff.pokemon.client;

import ninja.cooperstuff.debug.Debug;
import ninja.cooperstuff.engine.Game;
import ninja.cooperstuff.engine.events.KeyListener;
import ninja.cooperstuff.engine.util.Keys;
import ninja.cooperstuff.engine.util.Vector;
import ninja.cooperstuff.pokemon.entity.Player;
import ninja.cooperstuff.pokemon.entity.Pokemon;
import ninja.cooperstuff.pokemon.monster.Monster;
import ninja.cooperstuff.pokemon.util.Direction;
import ninja.cooperstuff.pokemon.world.World;

import java.awt.*;
import java.awt.datatransfer.StringSelection;

public class Launch {
	private static Game game = new Game();

	private static int id = 1;
	private static Player player;
	private static Pokemon pUp;
	private static Pokemon pDown;
	private static Pokemon pLeft;
	private static Pokemon pRight;
	private static Pokemon pPrev;
	private static Pokemon pNext;
	private static Pokemon pSpin;
	private static Pokemon pVert;
	private static Pokemon pHorz;
	private static int wait = 0;
	private static World world = new World(game);

	public static void run() throws InterruptedException {
		Debug.info("Starting Pokemon");
		game.setSize(816, 616);
		game.setTitle("Pokemon");

		Debug.level = 1;

		ninja.cooperstuff.pokemon.init.Init.preInit();
		ninja.cooperstuff.pokemon.init.Init.init();
		ninja.cooperstuff.pokemon.init.Init.postInit();

		int scale = 100;
		Vector center = new Vector((game.getWidth() - 16) / 2, (game.getHeight() - 16) / 2 - 50);

		player = game.instantiate(new Player(world, Monster.ids.get(id)));
		player.transform.position = Vector.add(center, new Vector(0, scale));
		pUp = game.instantiate(new Pokemon(world, Monster.ids.get(id)));
		pUp.transform.position = Vector.add(center, new Vector(0, -scale));
		pUp.facing = Direction.UP;
		pDown = game.instantiate(new Pokemon(world, Monster.ids.get(id)));
		pDown.transform.position = Vector.add(center, new Vector(0, 0));
		pLeft = game.instantiate(new Pokemon(world, Monster.ids.get(id)));
		pLeft.transform.position = Vector.add(center, new Vector(-scale, 0));
		pLeft.facing = Direction.LEFT;
		pRight = game.instantiate(new Pokemon(world, Monster.ids.get(id)));
		pRight.transform.position = Vector.add(center, new Vector(scale, 0));
		pRight.facing = Direction.RIGHT;
		pPrev = game.instantiate(new Pokemon(world, Monster.ids.get(getId(id - 1))));
		pPrev.transform.position = Vector.add(center, new Vector(-scale * 2, -scale));
		pNext = game.instantiate(new Pokemon(world, Monster.ids.get(getId(id + 1))));
		pNext.transform.position = Vector.add(center, new Vector(scale * 2, -scale));
		pSpin = game.instantiate(new Pokemon(world, Monster.ids.get(id)));
		pSpin.transform.position = Vector.add(center, new Vector(0, -scale * 2));
		pVert = game.instantiate(new Pokemon(world, Monster.ids.get(id)));
		pVert.transform.position = Vector.add(center, new Vector(-scale, -scale * 2));
		pHorz = game.instantiate(new Pokemon(world, Monster.ids.get(id)));
		pHorz.transform.position = Vector.add(center, new Vector(scale, -scale * 2));
		pHorz.facing = Direction.LEFT;

		int tick = 0;

		while (game.running) {
			game.update();
			game.repaint();
			Thread.sleep((long) (1000.0 / 60.0));
			if (KeyListener.isKeyDown(Keys.SPACE)) {
				int i = 0;
				player.destroy();
				player = game.instantiate(new Player(world, Monster.ids.get(id)));
				while (!player.isShiny()) {
					player.destroy();
					player = game.instantiate(new Player(world, Monster.ids.get(id)));
					i++;
				}
				System.out.println(i);
			}
			if ((KeyListener.isKeyHeld(Keys.BRACKET_RIGHT) || KeyListener.isKeyHeld(Keys.C)) && wait == 0) {
				id++; if (id == Monster.ids.size()) id = 1;
				respawn();
			} else if (KeyListener.isKeyHeld(Keys.BRACKET_LEFT) && wait == 0) {
				id--; if (id == 0) id = Monster.ids.size() - 1;
				respawn();
			} else if (KeyListener.isKeyDown(Keys.M)) {
				player.monster.setShadowSize(pPrev.monster.getShadowSize());
				player.monster.setBobHeight(Direction.UP, pPrev.monster.getBobHeight(Direction.UP));
				player.monster.setBobHeight(Direction.DOWN, pPrev.monster.getBobHeight(Direction.DOWN));
				player.monster.setBobHeight(Direction.LEFT, pPrev.monster.getBobHeight(Direction.LEFT));
				player.monster.setBobHeight(Direction.RIGHT, pPrev.monster.getBobHeight(Direction.RIGHT));
				player.monster.setSpriteOffset(Direction.UP, pPrev.monster.getSpriteOffset(Direction.UP));
				player.monster.setSpriteOffset(Direction.DOWN, pPrev.monster.getSpriteOffset(Direction.DOWN));
				player.monster.setSpriteOffset(Direction.LEFT, pPrev.monster.getSpriteOffset(Direction.LEFT));
				player.monster.setSpriteOffset(Direction.RIGHT, pPrev.monster.getSpriteOffset(Direction.RIGHT));
				player.monster.setAnimationSpeed(pPrev.monster.getAnimationSpeed());
				player.monster.setCollisionCorner1(pPrev.monster.getCollisionCorner1());
				player.monster.setCollisionCorner2(pPrev.monster.getCollisionCorner2());
			} else if (KeyListener.isKeyDown(Keys.V)) {
				Monster m = player.monster;
				String text = m.name.toLowerCase();
				if (m.getShadowSize() != 0) text += ".setShadowSize(" + (Math.round(m.getShadowSize() * 10) / 10.0) + ")";
				if (!Vector.equals(m.getSpriteOffset(Direction.UP), Vector.zero)) text += String.format(".setSpriteOffset(Direction.UP, new %s)", m.getSpriteOffset(Direction.UP));
				if (!Vector.equals(m.getSpriteOffset(Direction.DOWN), Vector.zero)) text += String.format(".setSpriteOffset(Direction.DOWN, new %s)", m.getSpriteOffset(Direction.DOWN));
				if (!Vector.equals(m.getSpriteOffset(Direction.LEFT), Vector.zero)) text += String.format(".setSpriteOffset(Direction.LEFT, new %s)", m.getSpriteOffset(Direction.LEFT));
				if (!Vector.equals(m.getSpriteOffset(Direction.RIGHT), Vector.zero)) text += String.format(".setSpriteOffset(Direction.RIGHT, new %s)", m.getSpriteOffset(Direction.RIGHT));
				if (m.getBobHeight(Direction.UP) != 1) text += String.format(".setBobHeight(Direction.UP, %d)", m.getBobHeight(Direction.UP));
				if (m.getBobHeight(Direction.DOWN) != 1) text += String.format(".setBobHeight(Direction.DOWN, %d)", m.getBobHeight(Direction.DOWN));
				if (m.getBobHeight(Direction.LEFT) != 1) text += String.format(".setBobHeight(Direction.LEFT, %d)", m.getBobHeight(Direction.LEFT));
				if (m.getBobHeight(Direction.RIGHT) != 1) text += String.format(".setBobHeight(Direction.RIGHT, %d)", m.getBobHeight(Direction.RIGHT));
				if (m.getAnimationSpeed() != 15) text += ".setAnimationSpeed(" + m.getAnimationSpeed() + ")";
				if (!Vector.equals(m.getCollisionCorner1(), new Vector(-16, -32))) text += String.format(".setCollisionCorner1(new %s)", m.getCollisionCorner1());
				if (!Vector.equals(m.getCollisionCorner2(), new Vector(16, 0))) text += String.format(".setCollisionCorner2(new %s)", m.getCollisionCorner2());
				//Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(text + ";"), null);
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(m.name + "\n"), null);
			}
			if (tick == 0) {
				tick = 15;
				pSpin.facing = pSpin.facing.getRight();
				pVert.facing = pVert.facing.getBack();
				pHorz.facing = pHorz.facing.getBack();
			}
			if (wait > 0) wait--;
			if (tick > 0) tick--;
		}

		Debug.info("Closing Pokemon");
	}

	static void respawn() {
		player.setMonster(Monster.ids.get(id));
		pUp.setMonster(Monster.ids.get(id));
		pDown.setMonster(Monster.ids.get(id));
		pLeft.setMonster(Monster.ids.get(id));
		pRight.setMonster(Monster.ids.get(id));
		pPrev.setMonster(Monster.ids.get(getId(id - 1)));
		pNext.setMonster(Monster.ids.get(getId(id + 1)));
		pSpin.setMonster(Monster.ids.get(id));
		pVert.setMonster(Monster.ids.get(id));
		pHorz.setMonster(Monster.ids.get(id));
		wait = 10;
	}

	private static int getId(int id) {
		if (id == 0) return 151;
		else if (id == 152) return 1;
		return id;
	}
}
