package ninja.cooperstuff.pokemon.world;

import ninja.cooperstuff.engine.util.IntVector;
import ninja.cooperstuff.engine.util.Noise;
import ninja.cooperstuff.engine.util.Vector;
import ninja.cooperstuff.pokemon.client.PokemonGame;
import ninja.cooperstuff.pokemon.entity.Pokemon;
import ninja.cooperstuff.pokemon.init.Tiles;
import ninja.cooperstuff.pokemon.monster.Monster;
import ninja.cooperstuff.pokemon.tile.Tile;
import ninja.cooperstuff.pokemon.world.biome.Biome;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class World {
	PokemonGame game;
	double multiplier = 1.0;
	double multiplierElevation = 6.12;
	double multiplierMoisture = 5.05;
	public double scaleMap = 1;
	double scaleElevation = 2;
	double scaleMoisture = 5.56;
	double offsetElevation = -0.58;
	double offsetMoisture = 0.23;

	private IntVector generateSize = new IntVector(17, 13);
	private IntVector detailSize = new IntVector(16, 12);
	private IntVector lastGenerateLocation;

	public HashSet<Pokemon> pokemon = new HashSet<>();
	public IntVector entityMaxDist = this.detailSize.clone().mul(3);
	public int entityCap = 32;
	public double entitySpawnRate = 0.1;

	public boolean showDetails;

	private HashMap<IntVector, TileData> data;

	public World(PokemonGame game) {
		this.game = game;
	}

	public IntVector getGenerateSize() {
		return this.generateSize;
	}

	public IntVector getDetailSize() {
		return this.detailSize;
	}

	private double getElevation(double x, double y) {
		double elevation = (Noise.noise(x / (this.scaleMap * this.scaleElevation * 100), y / (this.scaleMap * this.scaleElevation * 100)));
		return World.sigmoid(multiplier * elevation * multiplierElevation + offsetElevation) * 4;
	}

	private double getMoisture(double x, double y) {
		double moisture = (Noise.noise(x / (this.scaleMap * this.scaleMoisture * 50), y / (this.scaleMap * this.scaleMoisture * 50)));
		return World.sigmoid(multiplier * moisture * multiplierMoisture + offsetMoisture) * 6;
	}

	@NonNull
	private Biome getBiome(int x, int y) {
		return this.getBiome(this.getElevation(x, y), this.getMoisture(x, y));
	}

	@NonNull
	private Biome getBiome(double elevation, double moisture) {
//		return Biome.woods;
		if (moisture < 1) {
			if (elevation < 1) return Biome.bog;
			if (elevation < 2) return Biome.cave;
			if (elevation < 3) return Biome.forest;
			return Biome.marsh;
		} else if (moisture < 2) {
			if (elevation < 1) return Biome.snow;
			if (elevation < 2) return Biome.woods;
			if (elevation < 3) return Biome.bog;
			return Biome.cave;
		} else if (moisture < 3) {
			if (elevation < 1) return Biome.forest;
			if (elevation < 2) return Biome.marsh;
			if (elevation < 3) return Biome.snow;
			return Biome.woods;
		} else if (moisture < 4) {
			if (elevation < 1) return Biome.bog;
			if (elevation < 2) return Biome.cave;
			if (elevation < 3) return Biome.forest;
			return Biome.marsh;
		} else if (moisture < 5) {
			if (elevation < 1) return Biome.snow;
			if (elevation < 2) return Biome.woods;
			if (elevation < 3) return Biome.bog;
			return Biome.cave;
		}
		if (elevation < 1) return Biome.forest;
		if (elevation < 2) return Biome.marsh;
		if (elevation < 3) return Biome.snow;
		return Biome.woods;
	}

	public TileData getTileData(int x, int y) {
		if (!this.data.containsKey(new IntVector(x, y))) return null;
		return this.data.get(new IntVector(x, y));
	}

	public double getHeight(int x, int y) {
		double elevation = this.getElevation(x, y);
		double moisture = this.getMoisture(x, y);
		double distE = Math.abs((elevation % 1) - 0.5);
		double distM = Math.abs((moisture % 1) - 0.5);
		//return Math.min(15 * (2 * Math.pow(2 * (0.5 - Math.max(distE, distM)), 2)), 1);
		distE = 20 * Math.pow(2 * (0.5 - distE), 2);
		distM = 20 * Math.pow(2 * (0.5 - distM), 2);
		return Math.min(Math.min(distE, distM), 1);
	}

	public double heightNoise(int x, int y, double multiplier, double offset) {
		double noise = Noise.noise(x / 10.0, y / 10.0);
		return (World.sigmoid(multiplier * noise + offset) * 2 - 1) * this.getHeight(x, y);
	}

	public void generate(int x, int y) {
		Tile road = Tiles.ground1;
		if (this.lastGenerateLocation == null || Math.abs(x - this.lastGenerateLocation.x) > 2 * this.generateSize.x || Math.abs(y - this.lastGenerateLocation.y) > 2 * this.generateSize.y) {
			this.data = new HashMap<>();
			for (int j = -this.generateSize.y; j <= this.generateSize.y; j++) {
				for (int i = -this.generateSize.x; i <= this.generateSize.x; i++) {
					IntVector pos = new IntVector(x + i, y + j);
					Biome biome = this.getBiome(pos.x, pos.y);
					if (this.getHeight(pos.x, pos.y) < 0.15) this.data.put(pos, new TileData(this, biome, 0, road, null));
					else this.data.put(pos, new TileData(this, biome, biome.getHeight(this, pos.x, pos.y), pos.x, pos.y));
				}
			}
			for (int j = -this.detailSize.y; j <= this.detailSize.y; j++) {
				for (int i = -this.detailSize.x; i <= this.detailSize.x; i++) {
					IntVector pos = new IntVector(x + i, y + j);
					TileData tileData = this.data.get(pos);
					//if (tileData.getGround() == Tiles.ground1) continue;
					tileData.setDetail(tileData.getBiome().getDetail(this, tileData.getHeight(), pos.x, pos.y));
				}
			}
			this.lastGenerateLocation = new IntVector(x, y);
			return;
		} else if (x == this.lastGenerateLocation.x && y == this.lastGenerateLocation.y) return;
		if (x != this.lastGenerateLocation.x && y != this.lastGenerateLocation.y) {
			int signX = (int) Math.signum(x - this.lastGenerateLocation.x);
			int signY = (int) Math.signum(y - this.lastGenerateLocation.y);
			for (int j = 0; j < Math.abs(y - this.lastGenerateLocation.y); j++) {
				for (int i = 0; i < Math.abs(x - this.lastGenerateLocation.x); i++) {
					this.data.remove(new IntVector(this.lastGenerateLocation.x + signX * (i - this.generateSize.x), this.lastGenerateLocation.y + signY * (j - this.generateSize.y)));
				}
			}
		}
		if (x != this.lastGenerateLocation.x) {
			int sign = (int) Math.signum(x - this.lastGenerateLocation.x);
			for (int j = -this.generateSize.y; j <= this.generateSize.y; j++) {
				for (int i = 0; i < Math.abs(x - this.lastGenerateLocation.x); i++) {
					IntVector pos = new IntVector(x + sign * (i + this.generateSize.x), y + j);
					this.data.remove(new IntVector(this.lastGenerateLocation.x + sign * (i - this.generateSize.x), y + j));
					Biome biome = this.getBiome(pos.x, pos.y);
					if (this.getHeight(pos.x, pos.y) < 0.15) this.data.put(pos, new TileData(this, biome, 0, road, null));
					else this.data.put(pos, new TileData(this, biome, biome.getHeight(this, pos.x, pos.y), pos.x, pos.y));
				}
			}
			for (int j = -this.detailSize.y; j <= this.detailSize.y; j++) {
				for (int i = 0; i < Math.abs(x - this.lastGenerateLocation.x); i++) {
					IntVector pos = new IntVector(x + sign * (i + this.detailSize.x), y + j);
					TileData tileData = this.data.get(pos);
					tileData.setDetail(tileData.getBiome().getDetail(this, tileData.getHeight(), pos.x, pos.y));
				}
			}
			this.lastGenerateLocation.x = x;
		}
		if (y != this.lastGenerateLocation.y) {
			int sign = (int) Math.signum(y - this.lastGenerateLocation.y);
			for (int j = 0; j < Math.abs(y - this.lastGenerateLocation.y); j++) {
				for (int i = -this.generateSize.x; i <= this.generateSize.x; i++) {
					IntVector pos = new IntVector(x + i, y + sign * (j + this.generateSize.y));
					this.data.remove(new IntVector(x + i, this.lastGenerateLocation.y + sign * (j - this.generateSize.y)));
					Biome biome = this.getBiome(pos.x, pos.y);
					if (this.getHeight(pos.x, pos.y) < 0.15) this.data.put(pos, new TileData(this, biome, 0, road, null));
					else this.data.put(pos, new TileData(this, biome, biome.getHeight(this, pos.x, pos.y), pos.x, pos.y));
				}
			}
			for (int j = 0; j < Math.abs(y - this.lastGenerateLocation.y); j++) {
				for (int i = -this.detailSize.x; i <= this.detailSize.x; i++) {
					IntVector pos = new IntVector(x + i, y + sign * (j + this.detailSize.y));
					TileData tileData = this.data.get(pos);
					tileData.setDetail(tileData.getBiome().getDetail(this, tileData.getHeight(), pos.x, pos.y));
				}
			}
			this.lastGenerateLocation.y = y;
		}
	}

	public Pokemon spawnPokemon(Monster monster) {
		return this.spawnPokemon(monster, this.game.camera.position);
	}

	public Pokemon spawnPokemon(Monster monster, Vector position) {
		if (monster == null) return null;
		if (this.pokemon.size() >= this.entityCap) return null;
		Pokemon pokemon = this.game.instantiate(new Pokemon(this, monster)).useAI(true);
		pokemon.transform.position = position.clone();
		this.pokemon.add(pokemon);
		return pokemon;
	}

	public Pokemon trySpawnPokemon(int x, int y) {
		Random r = new Random();
		if (r.nextDouble() < this.entitySpawnRate) {
			IntVector tile = null;
			IntVector velocity = new IntVector(Math.signum(this.game.player.getVelocity().x), Math.signum(this.game.player.getVelocity().y));
			for (int i = 0; i < 5; i++) {
				IntVector flip = new IntVector(0, 0);
				if (velocity.x == 0) flip.x = r.nextDouble() < 0.5 ? 1 : -1;
				else flip.x = velocity.x;
				if (velocity.y == 0) flip.y = r.nextDouble() < 0.5 ? 1 : -1;
				else flip.y = velocity.y;
				//velocity.equals(IntVector.zero) ? new IntVector(r.nextDouble() < 0.5 ? 1 : -1, r.nextDouble() < 0.5 ? 1 : -1) : velocity;
				double offset = r.nextDouble();
				if (r.nextDouble() < 0.5) tile = new IntVector(x + flip.x * this.detailSize.x, y + flip.y * this.detailSize.y * offset);
				else tile = new IntVector(x + flip.x * this.detailSize.x * offset, y + flip.y * this.detailSize.y);
				if (this.getTileData(tile.x, tile.y).getWalkable()) break;
				tile = null;
			}
			if (tile == null) return null;
			return this.spawnPokemon(this.getBiome(tile.x, tile.y).getPokemon(), new Vector(tile.x * 32, tile.y * 32));
		}
		return null;
	}

	public void render(Graphics2D screen) {
		int width = this.game.getWidth() - 16;
		int height = this.game.getHeight() - 39;
		this.game.camera.toScreenCoords(screen);
		screen.translate(width / 2, height / 2);
		int x = (int) Math.floor(this.game.camera.position.x);
		int y = (int) Math.floor(this.game.camera.position.y);
		int screenOffsetX = ((x > 0) ? x % 32 : ((x % 32) + 32) % 32);
		int screenOffsetY = ((y > 0) ? y % 32 : ((y % 32) + 32) % 32);
		for (int i = x / 32 - width / 16; i < x / 32 + width / 16; i++) {
			for (int j = y / 32 - height / 16; j < y / 32 + height / 16; j++) {
				IntVector pos = new IntVector(i, j);
				if (this.data.containsKey(pos)) {
					TileData tileData = this.data.get(pos);
					if (tileData == null) continue;
					int drawX = (int) Math.floor(pos.x - Math.floorDiv(x, 32)) * 32 - screenOffsetX;
					int drawY = (int) Math.floor(pos.y - Math.floorDiv(y, 32)) * 32 - screenOffsetY;
					screen.drawImage(tileData.getGround().getSprite(), drawX, drawY, 32, 32, null);
					if (tileData.getDetail() != null) screen.drawImage(tileData.getDetail().getSprite(), drawX, drawY, 32, 32, null);
					if (!this.showDetails) {
						int scale = 10000;
						//screen.setColor(new Color(0, 0, 0, (int) (255 * (1 - this.getHeight(Math.floorDiv(x, scale) + i, Math.floorDiv(y, scale) + j)))));
						screen.setColor(new Color(255, 0, 0, tileData.getWalkable() ? 0 : 255));
						IntVector col1 = tileData.getCollisionCorner1();
						IntVector col2 = tileData.getCollisionCorner2();
						screen.fillRect(drawX + col1.x * 2, drawY + col1.y * 2, (col2.x - col1.x) * 2, (col2.y - col1.y) * 2);
					}
				}
			}

		}
		/*for (Vector pos : this.data.keySet()) {
			int drawX = (int) pos.x * 32 - screenOffsetX;
			int drawY = (int) pos.y * 32 - screenOffsetY;
			screen.drawImage(this.data.get(pos).getGround().getSprite(), drawX, drawY, 32, 32, null);
			if (this.showDetails && this.data.get(pos).getDetail() != null) screen.drawImage(this.data.get(pos).getDetail().getSprite(), drawX, drawY, 32, 32, null);
		}*/
		/*for (int i = - width / 2; i - 32 < width / 2; i += 32) {
			screen.drawLine(i - screenOffsetX, -height / 2 + screenOffsetY, i - screenOffsetX, height / 2 - screenOffsetY + 32);
			for (int j = - height / 2; j - 32 < height / 2; j += 32) {
				screen.drawLine(-width / 2 - screenOffsetX, j - screenOffsetY, width / 2 - screenOffsetX + 32, j - screenOffsetY);
			}
		}*/
		screen.translate(-width / 2, -height / 2);
		this.game.camera.toGameCoords(screen);
	}

	private static double sigmoid(double z) {
		return 1.0 / (1.0 + Math.exp(-z));
	}
}
