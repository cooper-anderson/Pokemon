package ninja.cooperstuff.pokemon.client;

import ninja.cooperstuff.debug.Debug;
import ninja.cooperstuff.engine.Game;
import ninja.cooperstuff.engine.events.KeyListener;
import ninja.cooperstuff.engine.util.IntVector;
import ninja.cooperstuff.engine.util.Keys;
import ninja.cooperstuff.pokemon.entity.Player;
import ninja.cooperstuff.pokemon.init.Monsters;
import ninja.cooperstuff.pokemon.init.Moves;
import ninja.cooperstuff.pokemon.move.Move;
import ninja.cooperstuff.pokemon.world.World;

import java.awt.*;
import java.util.ArrayList;

public class PokemonGame extends Game {
	public World world = new World(this);
	public Player player;

	private int generateCounter = 0;
	private int spawnCounter = 0;
	private boolean showDetails = true;

	public ArrayList<Move> moves = new ArrayList<>();
	public int moveIndex = 0;

	public PokemonGame() {
		super();
		this.setSize(240 * 4 + 16, 160 * 4 + 39);
		this.setResizable(false);
		this.setTitle("Pokemon");
	}

	@Override
	public void update() {
		if (KeyListener.isKeyDown(Keys.BRACKET_RIGHT)) this.moveIndex = (this.moveIndex + this.moves.size() + 1) % this.moves.size();
		if (KeyListener.isKeyDown(Keys.BRACKET_LEFT)) this.moveIndex = (this.moveIndex + this.moves.size() - 1) % this.moves.size();
		this.world.tempMove = this.moves.get(this.moveIndex);
		this.world.showDetails = this.showDetails;
		IntVector pos = this.player.transform.position.getTile();
		if (this.generateCounter == 0) {
			this.world.generate(pos.x, pos.y);
			this.generateCounter = 2;
		}
		if (this.spawnCounter == 0) {
			this.spawnCounter = (this.world.trySpawnPokemon(pos.x, pos.y) == null ? 2 : 100) + 10 * this.world.pokemon.size();
		}
		this.generateCounter--;
		this.spawnCounter--;
		super.update();
	}

	public void run() throws InterruptedException {
		Debug.level = 1;
		ninja.cooperstuff.pokemon.init.Init.preInit();
		ninja.cooperstuff.pokemon.init.Init.init();
		ninja.cooperstuff.pokemon.init.Init.postInit();
		this.stopLoading();

		this.moves.add(Moves.absorb);
		this.moves.add(Moves.acid);
		this.moves.add(Moves.acidArmor);
		this.moves.add(Moves.agility);
		this.moves.add(Moves.amnesia);
		this.moves.add(Moves.auroraBeam);
		this.moves.add(Moves.barrage);
		this.moves.add(Moves.barrier);
		this.moves.add(Moves.bite);
		this.moves.add(Moves.blizzard);
		this.moves.add(Moves.bodySlam);
		this.moves.add(Moves.boneClub);
		this.moves.add(Moves.bonemerang);
		this.moves.add(Moves.bubble);
		this.moves.add(Moves.bubbleBeam);
		this.moves.add(Moves.cometPunch);
		this.moves.add(Moves.confuseRay);
		this.moves.add(Moves.confusion);
		this.moves.add(Moves.constrict);
		this.moves.add(Moves.crabhammer);
		this.moves.add(Moves.cut);
		this.moves.add(Moves.defenceCurl);
		this.moves.add(Moves.dig);
		this.moves.add(Moves.dizzyPunch);
		this.moves.add(Moves.doubleKick);
		this.moves.add(Moves.doubleSlap);
		this.moves.add(Moves.doubleEdge);
		this.moves.add(Moves.dragonRage);
		this.moves.add(Moves.dreamEater);
		this.moves.add(Moves.drillPeck);
		this.moves.add(Moves.earthquake);
		this.moves.add(Moves.eggBomb);
		this.moves.add(Moves.ember);
		this.moves.add(Moves.explosion);
		this.moves.add(Moves.fireBlast);
		this.moves.add(Moves.firePunch);
		this.moves.add(Moves.fireSpin);
		this.moves.add(Moves.fissure);
		this.moves.add(Moves.flamethrower);
		this.moves.add(Moves.fly);
		this.moves.add(Moves.furyAttack);
		this.moves.add(Moves.furySwipes);
		this.moves.add(Moves.glare);
		this.moves.add(Moves.growl);
		this.moves.add(Moves.growth);
		this.moves.add(Moves.guillotine);
		this.moves.add(Moves.gust);
		this.moves.add(Moves.harden);
		this.moves.add(Moves.haze);
		this.moves.add(Moves.headbutt);
		this.moves.add(Moves.highJumpKick);
		this.moves.add(Moves.hornAttack);
		this.moves.add(Moves.hornDrill);
		this.moves.add(Moves.hydroPump);
		this.moves.add(Moves.hyperBeam);
		this.moves.add(Moves.hyperFang);
		this.moves.add(Moves.hypnosis);
		this.moves.add(Moves.iceBeam);
		this.moves.add(Moves.icePunch);
		this.moves.add(Moves.jumpKick);
		this.moves.add(Moves.karateChop);
		this.moves.add(Moves.leechLife);
		this.moves.add(Moves.leechSeed);
		this.moves.add(Moves.leer);
		this.moves.add(Moves.lick);
		this.moves.add(Moves.lovelyKiss);
		this.moves.add(Moves.meditate);
		this.moves.add(Moves.megaDrain);
		this.moves.add(Moves.megaKick);
		this.moves.add(Moves.megaPunch);
		this.moves.add(Moves.metronome);
		//this.moves.add(Moves.vineWhip);

		this.player = this.instantiate(new Player(this.world, Monsters.charmander));

		while (this.running) {
			this.update();
			this.repaint();
			Thread.sleep((long) (1000.0 / 60.0));
		}
	}

	@Override
	public void render(Graphics2D screen) {
		super.render(screen);
		world.render(screen);
		screen.setColor(Color.white);
		this.camera.toScreenCoords(screen);
		screen.scale(5, 5);
		screen.drawString(this.moves.get(this.moveIndex).name, 1, 10);
		screen.scale(0.2, 0.2);
		this.camera.toGameCoords(screen);
		screen.fillRect(-1, -1, 2, 2);
	}
}
