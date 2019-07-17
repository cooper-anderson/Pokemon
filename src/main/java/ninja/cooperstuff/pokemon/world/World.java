package ninja.cooperstuff.pokemon.world;

import ninja.cooperstuff.debug.Debug;
import ninja.cooperstuff.engine.Game;
import ninja.cooperstuff.engine.util.Noise;
import ninja.cooperstuff.engine.util.Vector;
import ninja.cooperstuff.pokemon.world.biome.Biome;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.awt.*;
import java.util.HashMap;

public class World {
	Game game;
	double multiplier = 1.0;
	double multiplierElevation = 6.12;
	double multiplierMoisture = 5.05;
	public double scaleMap = .1;
	double scaleElevation = 1;
	double scaleMoisture = 5.56;
	double offsetElevation = -0.58;
	double offsetMoisture = 0.23;

	private Vector generateSize = new Vector(12, 12);
	private Vector detailSize = new Vector(11, 11);
	private Vector lastGenerateLocation;

	public boolean showDetails;

	private HashMap<Vector, TileData> data;

	public World(Game game) {
		this.game = game;
	}

	private double getElevation(double x, double y) {
		double elevation = (Noise.noise(x / (this.scaleMap * this.scaleElevation * 100), y / (this.scaleMap * this.scaleElevation * 100)));
		return World.sigmoid(multiplier * elevation * multiplierElevation + offsetElevation) * 4;
	}

	private double getMoisture(double x, double y) {
		double moisture = (Noise.noise(x / (this.scaleMap * this.scaleMoisture * 50), y / (this.scaleMap * this.scaleMoisture * 50)));
		return World.sigmoid(multiplier * moisture * multiplierMoisture + offsetMoisture) * 4;
	}

	@NonNull
	private Biome getBiome(int x, int y) {
		return this.getBiome(this.getElevation(x, y), this.getMoisture(x, y));
	}

	@NonNull
	private Biome getBiome(double elevation, double moisture) {
		return Biome.cave;
	}

	public TileData getTileData(int x, int y) {
		if (!this.data.containsKey(new Vector(x, y))) return null;
		return this.data.get(new Vector(x, y));
	}

	public void generate(int x, int y) {
		Vector old = this.lastGenerateLocation;
		if (this.lastGenerateLocation == null || Math.abs(x - this.lastGenerateLocation.x) > 2 * this.generateSize.x || Math.abs(y - this.lastGenerateLocation.y) > 2 * this.generateSize.y) {
			this.data = new HashMap<>();
			for (int j = (int) -this.generateSize.y; j <= (int) this.generateSize.y; j++) {
				for (int i = (int) -this.generateSize.x; i <= (int) this.generateSize.x; i++) {
					Vector pos = new Vector(x + i, y + j);
					Biome biome = this.getBiome(pos.x, pos.y);
					this.data.put(pos, new TileData(biome.getTile(this, (int) pos.x, (int) pos.y), null, biome, 0));
				}
			}
			for (int j = (int) -this.detailSize.y; j <= (int) this.detailSize.y; j++) {
				for (int i = (int) -this.detailSize.x; i <= (int) this.detailSize.x; i++) {
					Vector pos = new Vector(x + i, y + j);
					TileData tileData = this.data.get(pos);
					tileData.setDetail(tileData.getBiome().getDetail(this, (int) pos.x, (int) pos.y));
				}
			}
			this.lastGenerateLocation = new Vector(x, y);
			return;
		} else if (x == this.lastGenerateLocation.x && y == this.lastGenerateLocation.y) return;
		if (x != this.lastGenerateLocation.x && y != this.lastGenerateLocation.y) {
			int signX = (int) Math.signum(x - this.lastGenerateLocation.x);
			int signY = (int) Math.signum(y - this.lastGenerateLocation.y);
			for (int j = 0; j < Math.abs(y - this.lastGenerateLocation.y); j++) {
				for (int i = 0; i < Math.abs(x - this.lastGenerateLocation.x); i++) {
					this.data.remove(new Vector(this.lastGenerateLocation.x + signX * (i - this.generateSize.x), this.lastGenerateLocation.y + signY * (j - this.generateSize.y)));
				}
			}
		}
		if (x != this.lastGenerateLocation.x) {
			int sign = (int) Math.signum(x - this.lastGenerateLocation.x);
			for (int j = (int) -this.generateSize.y; j <= this.generateSize.y; j++) {
				for (int i = 0; i < Math.abs(x - this.lastGenerateLocation.x); i++) {
					Vector pos = new Vector(x + sign * (i + this.generateSize.x), y + j);
					this.data.remove(new Vector(this.lastGenerateLocation.x + sign * (i - this.generateSize.x), y + j));
					Biome biome = this.getBiome(pos.x, pos.y);
					this.data.put(pos, new TileData(biome.getTile(this, (int) pos.x, (int) pos.y), null, biome, 0));
				}
			}
			for (int j = (int) -this.detailSize.y; j <= this.detailSize.y; j++) {
				for (int i = 0; i < Math.abs(x - this.lastGenerateLocation.x); i++) {
					Vector pos = new Vector(x + sign * (i + this.detailSize.x), y + j);
					TileData tileData = this.data.get(pos);
					tileData.setDetail(tileData.getBiome().getDetail(this, (int) pos.x, (int) pos.y));
				}
			}
			this.lastGenerateLocation.x = x;
		}
		if (y != this.lastGenerateLocation.y) {
			int sign = (int) Math.signum(y - this.lastGenerateLocation.y);
			for (int j = 0; j < Math.abs(y - this.lastGenerateLocation.y); j++) {
				for (int i = (int) -this.generateSize.x; i <= this.generateSize.x; i++) {
					Vector pos = new Vector(x + i, y + sign * (j + this.generateSize.y));
					this.data.remove(new Vector(x + i, this.lastGenerateLocation.y + sign * (j - this.generateSize.y)));
					Biome biome = this.getBiome(pos.x, pos.y);
					this.data.put(pos, new TileData(biome.getTile(this, (int) pos.x, (int) pos.y), null, biome, 0));
				}
			}
			for (int j = 0; j < Math.abs(y - this.lastGenerateLocation.y); j++) {
				for (int i = (int) -this.detailSize.x; i <= this.detailSize.x; i++) {
					Vector pos = new Vector(x + i, y + sign * (j + this.detailSize.y));
					TileData tileData = this.data.get(pos);
					tileData.setDetail(tileData.getBiome().getDetail(this, (int) pos.x, (int) pos.y));
				}
			}
			this.lastGenerateLocation.y = y;
		}
		if (!this.lastGenerateLocation.equals(old)) Debug.log(this.lastGenerateLocation);
	}

	/*public void render(Graphics2D screen) {
		int width = this.game.getWidth() - 16;
		int height = this.game.getHeight() - 39;
		this.game.camera.toScreenCoords(screen);
		screen.translate(width / 2, height / 2);
		int x = (int) this.game.camera.position.x;
		int y = (int) this.game.camera.position.y;
		int screenOffsetX = ((x > 0) ? x % 32 : ((x % 32) + 32) % 32);
		int screenOffsetY = ((y > 0) ? y % 32 : ((y % 32) + 32) % 32);
		int tileOffsetX = Math.floorDiv(x, 32);
		int tileOffsetY = Math.floorDiv(y, 32);
		for (int i = 0; i - 3 < width / 32; i++) {
			for (int j = 0; j - 2 < height / 32; j++) {
				double n = this.getElevation(i + tileOffsetX - width / 64, j + tileOffsetY - height / 64) / 4;
				screen.drawImage((n < 0.33 ? Tiles.ground1 : (n < 0.66 ? Tiles.ground2 : Tiles.ground3)).getSprite(), (i - 1 - width / 64) * 32 - screenOffsetX, (j - 1 - height / 64) * 32 - screenOffsetY, 32, 32, null);
			}
		}
		screen.translate(-width / 2, -height / 2);
		this.game.camera.toGameCoords(screen);
	}*/

	public void render(Graphics2D screen) {
		int width = this.game.getWidth() - 16;
		int height = this.game.getHeight() - 39;
		this.game.camera.toScreenCoords(screen);
		screen.translate(width / 2, height / 2);
		int x = (int) this.game.camera.position.x;
		int y = (int) this.game.camera.position.y;
		int screenOffsetX = ((x > 0) ? x % 32 : ((x % 32) + 32) % 32);
		int screenOffsetY = ((y > 0) ? y % 32 : ((y % 32) + 32) % 32);
		int tileOffsetX = Math.floorDiv(x, 32);
		int tileOffsetY = Math.floorDiv(y, 32);
		for (Vector pos : this.data.keySet()) {
			screen.drawImage(this.data.get(pos).getGround().getSprite(), (int) pos.x * 32, (int) pos.y * 32, 32, 32, null);
			if (this.showDetails && this.data.get(pos).getDetail() != null) screen.drawImage(this.data.get(pos).getDetail().getSprite(), (int) pos.x * 32, (int) pos.y * 32, 32, 32, null);
		}
		/*for (int i = - width / 2; i < width / 2; i += 32) {
			screen.drawLine(i, -height / 2, i, height / 2);
			for (int j = - height / 2; j < height / 2; j += 32) {
				screen.drawLine(-width / 2, j, width / 2, j);
			}
		}*/
		screen.translate(-width / 2, -height / 2);
		this.game.camera.toGameCoords(screen);
	}

	private static double sigmoid(double z) {
		return 1.0 / (1.0 + Math.exp(-z));
	}
}
