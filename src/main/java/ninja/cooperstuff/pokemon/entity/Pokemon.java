package ninja.cooperstuff.pokemon.entity;

import ninja.cooperstuff.debug.Debug;
import ninja.cooperstuff.engine.util.IntVector;
import ninja.cooperstuff.engine.util.Noise;
import ninja.cooperstuff.engine.util.Vector;
import ninja.cooperstuff.pokemon.entity.particle.Smoke;
import ninja.cooperstuff.pokemon.entity.particle.StatModifier;
import ninja.cooperstuff.pokemon.init.Sounds;
import ninja.cooperstuff.pokemon.monster.Monster;
import ninja.cooperstuff.pokemon.sound.Sound;
import ninja.cooperstuff.pokemon.sound.SoundClip;
import ninja.cooperstuff.pokemon.util.Constants;
import ninja.cooperstuff.pokemon.util.Direction;
import ninja.cooperstuff.pokemon.util.Stats;
import ninja.cooperstuff.pokemon.util.Status;
import ninja.cooperstuff.pokemon.world.TileData;
import ninja.cooperstuff.pokemon.world.World;

import java.awt.*;
import java.util.Random;

public class Pokemon extends Entity {
	public Monster monster;
	protected Stats valuesEffort = new Stats();
	protected Stats valuesIndividual = new Stats();
	protected Stats stats;
	protected Stats statModifiers = new Stats();
	protected int level = 1;
	protected boolean shiny;
	protected boolean useAI = false;
	private boolean isPlayer = false;
	protected int walkCycle = 0;
	private Vector lastPos = new Vector();
	public Direction facing = Direction.DOWN;
	protected boolean moving = false;
	protected boolean noclip = false;
	public Vector input = new Vector();
	private int seed = this.hashCode() % 10000;

	public Status status = Status.NONE;

	private int healthAnimation = 0;
	private int healthDelay = 0;
	private SoundClip cryClip;

	public Pokemon(World world, Monster monster) {
		super(world);
		this.setMonster(monster);
		this.shiny = new Random().nextInt(8192) == 1;
		if (this.shiny) Debug.info(String.format("Shiny %s", this.monster.name));
	}

	public void setMonster(Monster monster) {
		this.monster = monster;
		this.stats = monster.baseStats.clone();
		this.healthAnimation = this.stats.health;
		this.shadow.scale = this.monster.getShadowSize();
		this.cryClip = this.cry();
	}

	public boolean isShiny() {
		return this.shiny;
	}

	public Pokemon isShiny(boolean shiny) {
		this.shiny = shiny;
		return this;
	}

	public boolean useAI() {
		return this.useAI;
	}

	public Pokemon useAI(boolean useAI) {
		this.useAI = useAI;
		return this;
	}

	public boolean isPlayer() {
		return this.isPlayer;
	}

	public Pokemon isPlayer(boolean isPlayer) {
		this.isPlayer = isPlayer;
		return this;
	}

	public SoundClip cry() {
		return this.monster.startCry();
	}

	public int getLevel() {
		return this.level;
	}

	public Pokemon setLevel(int level) {
		this.level = level;
		return this;
	}

	public Vector getVelocity() {
		return Vector.sub(this.transform.position, this.lastPos);
	}

	public Vector getForwardVector() {
		Vector velocity = this.getVelocity().normalized();
		if (velocity.equals(Vector.zero)) return Vector.fromDirection(this.facing);
		return velocity;
	}

	public int getHealth() {
		return this.stats.health;
	}

	public int damage(int amount) {
		int health = this.stats.health;
		this.stats.health = Math.max(0, this.stats.health - amount);
		return health - this.stats.health;
	}

	public int heal(int amount) {
		if (this.stats.health == 0) return 0;
		int health = this.stats.health;
		this.stats.health = Math.min(this.monster.baseStats.health, this.stats.health + amount);
		return this.stats.health - health;
	}

	public int modifyAttackPhysical(Stats.Modifier modifier, Stats.Sign sign, double chance) {
		if (new Random().nextDouble() < chance) {
			this.statModifiers.attackPhysical = Math.min(7, Math.max(-7, this.statModifiers.attackPhysical + sign.getValue() * modifier.getValue()));
			this.stats.attackPhysical = (int) (this.monster.baseStats.attackPhysical * Stats.getMultiplier(this.statModifiers.attackPhysical));
			this.game.instantiate(new StatModifier(this.world, Constants.statModifier.color.ATTACK, sign.getValue(), this.shadow.scale)).transform.position = this.transform.position;
		}
		return this.statModifiers.attackPhysical;
	}

	public int modifyAttackSpecial(Stats.Modifier modifier, Stats.Sign sign, double chance) {
		if (new Random().nextDouble() < chance) {
			this.statModifiers.attackSpecial = Math.min(7, Math.max(-7, this.statModifiers.attackSpecial + sign.getValue() * modifier.getValue()));
			this.stats.attackSpecial = (int) (this.monster.baseStats.attackSpecial * Stats.getMultiplier(this.statModifiers.attackSpecial));
			this.game.instantiate(new StatModifier(this.world, Constants.statModifier.color.ATTACK, sign.getValue(), this.shadow.scale)).transform.position = this.transform.position;
		}
		return this.statModifiers.attackSpecial;
	}

	public int modifyDefensePhysical(Stats.Modifier modifier, Stats.Sign sign, double chance) {
		if (new Random().nextDouble() < chance) {
			this.statModifiers.defensePhysical = Math.min(7, Math.max(-7, this.statModifiers.defensePhysical + sign.getValue() * modifier.getValue()));
			this.stats.defensePhysical = (int) (this.monster.baseStats.defensePhysical * Stats.getMultiplier(this.statModifiers.defensePhysical));
			this.game.instantiate(new StatModifier(this.world, Constants.statModifier.color.DEFENSE, sign.getValue(), this.shadow.scale)).transform.position = this.transform.position;
		}
		return this.statModifiers.defensePhysical;
	}

	public int modifyDefenseSpecial(Stats.Modifier modifier, Stats.Sign sign, double chance) {
		if (new Random().nextDouble() < chance) {
			this.statModifiers.defenseSpecial = Math.min(7, Math.max(-7, this.statModifiers.defenseSpecial + sign.getValue() * modifier.getValue()));
			this.stats.defenseSpecial = (int) (this.monster.baseStats.defenseSpecial * Stats.getMultiplier(this.statModifiers.defenseSpecial));
			this.game.instantiate(new StatModifier(this.world, Constants.statModifier.color.DEFENSE, sign.getValue(), this.shadow.scale)).transform.position = this.transform.position;
		}
		return this.statModifiers.defenseSpecial;
	}

	public int modifySpeed(Stats.Modifier modifier, Stats.Sign sign, double chance) {
		if (new Random().nextDouble() < chance) {
			this.statModifiers.speed = Math.min(7, Math.max(-7, this.statModifiers.speed + sign.getValue() * modifier.getValue()));
			this.stats.speed = (int) (this.monster.baseStats.speed * Stats.getMultiplier(this.statModifiers.speed));
			this.game.instantiate(new StatModifier(this.world, Constants.statModifier.color.SPEED, sign.getValue(), this.shadow.scale)).transform.position = this.transform.position;
		}
		return this.statModifiers.speed;
	}

	public int modifyStat(Stats.Stat stat, Stats.Modifier modifier, Stats.Sign sign, double chance) {
		if (stat == Stats.Stat.ATTACK_PHYSICAL) return this.modifyAttackPhysical(modifier, sign, chance);
		else if (stat == Stats.Stat.ATTACK_SPECIAL) return this.modifyAttackSpecial(modifier, sign, chance);
		else if (stat == Stats.Stat.DEFENSE_PHYSICAL) return this.modifyDefensePhysical(modifier, sign, chance);
		else if (stat == Stats.Stat.DEFENSE_SPECIAL) return this.modifyDefenseSpecial(modifier, sign, chance);
		else if (stat == Stats.Stat.SPEED) return this.modifySpeed(modifier, sign, chance);
		return 0;
	}

	public void resetStats() {
		int attackPhysical = (int) -Math.signum(this.statModifiers.attackPhysical);
		int attackSpecial = (int) -Math.signum(this.statModifiers.attackSpecial);
		int defensePhysical = (int) -Math.signum(this.statModifiers.defensePhysical);
		int defenseSpecial = (int) -Math.signum(this.statModifiers.defenseSpecial);
		int speed = (int) -Math.signum(this.statModifiers.speed);
		if (attackPhysical != 0) {
			this.statModifiers.attackPhysical = 0;
			this.stats.attackPhysical = this.monster.baseStats.attackPhysical;
			this.game.instantiate(new StatModifier(this.world, Constants.statModifier.color.ATTACK, attackPhysical, this.shadow.scale)).transform.position = this.transform.position;
		}
		if (attackSpecial != 0) {
			this.statModifiers.attackSpecial = 0;
			this.stats.attackSpecial = this.monster.baseStats.attackSpecial;
			this.game.instantiate(new StatModifier(this.world, Constants.statModifier.color.ATTACK, attackSpecial, this.shadow.scale)).transform.position = this.transform.position;
		}
		if (defensePhysical != 0) {
			this.statModifiers.defensePhysical = 0;
			this.stats.defensePhysical = this.monster.baseStats.defensePhysical;
			this.game.instantiate(new StatModifier(this.world, Constants.statModifier.color.DEFENSE, defensePhysical, this.shadow.scale)).transform.position = this.transform.position;
		}
		if (defenseSpecial != 0) {
			this.statModifiers.defenseSpecial = 0;
			this.stats.defenseSpecial = this.monster.baseStats.defenseSpecial;
			this.game.instantiate(new StatModifier(this.world, Constants.statModifier.color.DEFENSE, defenseSpecial, this.shadow.scale)).transform.position = this.transform.position;
		}
		if (speed != 0) {
			this.statModifiers.speed = 0;
			this.stats.speed = this.monster.baseStats.speed;
			this.game.instantiate(new StatModifier(this.world, Constants.statModifier.color.SPEED, speed, this.shadow.scale)).transform.position = this.transform.position;
		}
	}

	public Pokemon setStatus(Status status) {
		this.status = status;
		return this;
	}

	public boolean canUseMove() {
		Random r = new Random();
		switch (this.status) {
			case PARALYZED:
				return r.nextDouble() < 0.25;
			case FROZEN:
				return false;
			case FLINCH:
				this.status = Status.NONE;
				return false;
			case CONFUSED:
				if (r.nextDouble() < 0.5) return true;
				this.damage(10);
				return false;
			default:
				return true;
		}
	}

	@Override
	public void update() {
		if (this.cryClip != null) {
			if (this.stats.health == 0) {
				if (this.cryClip.stopped()) {
					Sound.playSound(Sounds.faint);
					this.createDeathParticles();
					this.destroy();
				}
				return;
			} else {
				if (this.cryClip.stopped()) {
					if (this.shiny) Sound.playSound(Sounds.shiny, 30);
					this.cryClip = null;
				}
			}
		}
		this.lastPos = this.transform.position.clone();
		if ((!this.moving && this.frame % (2 * this.monster.getAnimationSpeed()) == 0) || (this.moving && this.frame % this.monster.getAnimationSpeed() == 0)) this.walkCycle = 1 - walkCycle;
		this.moving = false;

		if (this.useAI) {
			double threshold = 0.2;
			double x = Noise.noise(seed + this.frame / 100.0);
			double y = Noise.noise(seed + 10000 + this.frame / 100.0);
			this.input.x = (x > threshold) ? 1 : (x < - threshold) ? -1 : 0;
			this.input.y = (y > threshold) ? 1 : (y < - threshold) ? -1 : 0;
		}

		Vector pos = this.transform.position;
		IntVector tile = pos.getTile();
		IntVector camTile = this.game.camera.position.getTile();
		IntVector dist = new IntVector(Math.abs(tile.x - camTile.x), Math.abs(tile.y - camTile.y));
		if (!this.isPlayer() && (dist.x > this.world.entityMaxDist.x || dist.y > this.world.entityMaxDist.y)) this.destroy();
		else if (dist.x <= this.world.getDetailSize().x && dist.y <= this.world.getDetailSize().y) {
			Vector newPos = new Vector(this.transform.position.x + this.input.x, this.transform.position.y + this.input.y);
			IntVector newTile = newPos.getTile();
			TileData tileData = this.world.getTileData(newTile.x, newTile.y);
			TileData tileX = this.world.getTileData(newTile.x, tile.y);
			TileData tileY = this.world.getTileData(tile.x, newTile.y);
			if (tileData == null || tileX == null || tileY == null) Debug.warn(String.format("Pokemon<%s> Out of bounds", this.monster.name));
			else {
				boolean x1 = newPos.x < newTile.x * 32 + 2 * tileData.getCollisionCorner1().x,
						x2 = newPos.x > newTile.x * 32 + 2 * tileData.getCollisionCorner2().x,
						y1 = newPos.y < newTile.y * 32 + 2 * tileData.getCollisionCorner1().y,
						y2 = newPos.y > newTile.y * 32 + 2 * tileData.getCollisionCorner2().y,
						xx1 = newPos.x < newTile.x * 32 + 2 * tileX.getCollisionCorner1().x,
						xx2 = newPos.x > newTile.x * 32 + 2 * tileX.getCollisionCorner2().x,
						xy1 = pos.y <= tile.y * 32 + 2 * tileX.getCollisionCorner1().y,
						xy2 = pos.y > tile.y * 32 + 2 * tileX.getCollisionCorner2().y,
						yx1 = pos.x < tile.x * 32 + 2 * tileY.getCollisionCorner1().x,
						yx2 = pos.x > tile.x * 32 + 2 * tileY.getCollisionCorner2().x,
						yy1 = newPos.y < newTile.y * 32 + 2 * tileY.getCollisionCorner1().y,
						yy2 = newPos.y > newTile.y * 32 + 2 * tileY.getCollisionCorner2().y;

				Vector finalVelocity = new Vector();
				if (this.noclip || tileData.getWalkable() || x1 || x2 || y1 || y2) {
					finalVelocity.x = this.input.x;
					finalVelocity.y = this.input.y;
				} else {
					if (tileX.getWalkable() || xx1 || xx2 || xy1 || xy2) finalVelocity.x = this.input.x;
					if (tileY.getWalkable() || yx1 || yx2 || yy1 || yy2) finalVelocity.y = this.input.y;
				}

				if (finalVelocity.y != 0) {
					this.facing = finalVelocity.y < 0 ? Direction.UP : Direction.DOWN;
					this.transform.position.y += finalVelocity.y;
					this.moving = true;
				}
				if (finalVelocity.x != 0) {
					this.facing = finalVelocity.x < 0 ? Direction.LEFT : Direction.RIGHT;
					this.transform.position.x += finalVelocity.x;
					this.moving = true;
				}
			}
		}

		if (this.healthAnimation != this.stats.health) this.healthDelay = Constants.healthBar.ANIMATION_DELAY;
		if (Math.abs(this.stats.health - this.healthAnimation) < Constants.healthBar.ANIMATION_STEP) this.healthAnimation = this.stats.health;
		this.healthAnimation += Constants.healthBar.ANIMATION_STEP * Math.signum(this.stats.health - this.healthAnimation);
		this.healthDelay = Math.max(0, this.healthDelay - 1);

		if (this.stats.health == 0 && this.healthAnimation == 0) this.cryClip = this.cry();
	}

	public void createDeathParticles() {
		for (int i = 0; i < Constants.deathParticleCount; i++) this.game.instantiate(new Smoke(this.world)).transform.position = this.transform.position.clone();
	}

	@Override
	public void destroy() {
		if (!this.isPlayer()) this.world.pokemon.remove(this);
		super.destroy();
	}

	@Override
	public void render(Graphics2D screen) {
		super.render(screen);
		int size = 64;
		Vector offset = this.monster.getSpriteOffset(this.facing);
		int x = (int) (offset.x - size / 2);
		int y = (int) (offset.y - this.walkCycle * this.monster.getBobHeight(this.facing) - size + 4);
		screen.drawImage(this.monster.getSprite(this.facing, this.walkCycle, this.shiny), x, y, size, size, null);
		if (this.healthDelay != 0) this.drawHealthBar(screen);
	}

	private void drawHealthBar(Graphics2D screen) {
		double percent = (double) this.healthAnimation / (double) this.monster.baseStats.health;
		int width = (int) (40 * this.shadow.scale);
		int height = 10;
		int offsetY = (int) (10 * this.shadow.scale);
		int margin = 2;
		screen.setColor(Constants.healthBar.color.BORDER);
		screen.fillRect(-width / 2, offsetY, width, height);
		screen.setColor(Constants.healthBar.getColor(percent));
		screen.fillRect(-width / 2 + margin, offsetY + margin, (int) Math.round((width - 2 * margin) * percent), height - 2 * margin);
		screen.setColor(Constants.healthBar.color.BACKGROUND);
		screen.fillRect(-width / 2 + margin + (int) Math.round((width - 2 * margin) * percent), offsetY + margin, (int) Math.round((width - 2 * margin) * (1 - percent)), height - 2 * margin);
	}
}
