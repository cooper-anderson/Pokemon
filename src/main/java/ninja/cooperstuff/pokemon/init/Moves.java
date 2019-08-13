package ninja.cooperstuff.pokemon.init;

import ninja.cooperstuff.debug.Debug;
import ninja.cooperstuff.pokemon.move.*;
import ninja.cooperstuff.pokemon.util.Stats;
import ninja.cooperstuff.pokemon.util.Status;

import static ninja.cooperstuff.pokemon.init.Types.*;
import static ninja.cooperstuff.pokemon.move.Move.AttackType.*;

public class Moves implements Init.Initializer {
	public static final Move absorb = new Absorb("Absorb", GRASS, SPECIAL, 20, 100, 25);
	public static final Move acid = new MoveDefault("Acid", POISON, SPECIAL, 40, 100, 30).addModifier(Stats.Stat.DEFENSE_SPECIAL, Stats.Modifier.NORMAL, Stats.Sign.FALL, 0.1);
	public static final Move acidArmor = new MoveStatus("Acid Armor", POISON, STATUS, 0, 0, 20).addSelfModifier(Stats.Stat.DEFENSE_PHYSICAL, Stats.Modifier.SHARP, Stats.Sign.RAISE, 1.0);
	public static final Move agility = new MoveStatus("Agility", PSYCHIC, STATUS, 0, 0, 30).addSelfModifier(Stats.Stat.SPEED, Stats.Modifier.SHARP, Stats.Sign.RAISE, 1.0);
	public static final Move amnesia = new MoveStatus("Amnesia", PSYCHIC, STATUS, 0, 0, 20).addSelfModifier(Stats.Stat.DEFENSE_SPECIAL, Stats.Modifier.SHARP, Stats.Sign.RAISE, 1.0);
	public static final Move auroraBeam = new MoveBeam("Aurora Beam", ICE, SPECIAL, 65, 100, 20).addModifier(Stats.Stat.ATTACK_PHYSICAL, Stats.Modifier.NORMAL, Stats.Sign.FALL, 0.1);
	public static final Move barrage = new MoveDefault("Barrage", NORMAL, PHYSICAL, 15, 85, 20).setProjectileCount(2, 5);
	public static final Move barrier = new MoveStatus("Barrier", PSYCHIC, STATUS, 0, 0, 20).addSelfModifier(Stats.Stat.DEFENSE_PHYSICAL, Stats.Modifier.SHARP, Stats.Sign.RAISE, 1.0);
	public static final Move bide = new MoveDefault("Bide", NORMAL, PHYSICAL, 0, 0, 10); // TODO Move<Bide>
	public static final Move bind = new MoveDefault("Bind", NORMAL, PHYSICAL, 15, 85, 20); // TODO Move<Bind>
	public static final Move bite = new MoveDefault("Bite", DARK, PHYSICAL, 60, 100, 25).addEffect(Status.FLINCH, 0.3);
	public static final Move blizzard = new MoveDefault("Blizzard", ICE, SPECIAL, 110, 70, 5).addEffect(Status.FROZEN, 0.1);
	public static final Move bodySlam = new MoveDefault("Body Slam", NORMAL, PHYSICAL, 85, 100, 15).addEffect(Status.PARALYZED, 0.3);
	public static final Move boneClub = new MoveDefault("Bone Club", GROUND, PHYSICAL, 65, 85, 20).addEffect(Status.FLINCH, 0.1);
	public static final Move bonemerang = new Bonemerang("Bonemerang", GROUND, PHYSICAL, 50, 90, 10);
	public static final Move bubble = new MoveDefault("Bubble", WATER, SPECIAL, 40, 100, 30).addModifier(Stats.Stat.SPEED, Stats.Modifier.NORMAL, Stats.Sign.FALL, 0.1);
	public static final Move bubbleBeam = new MoveDefault("Bubble Beam", WATER, SPECIAL, 65, 100, 20).setProjectileCount(3).addModifier(Stats.Stat.SPEED, Stats.Modifier.NORMAL, Stats.Sign.FALL, 0.1);;
	public static final Move clamp = new MoveDefault("Clamp", WATER, PHYSICAL, 35, 85, 10); // TODO Move<Clamp>
	public static final Move cometPunch = new MoveDefault("Comet Punch", NORMAL, PHYSICAL, 18, 85, 15).setProjectileCount(2, 5);
	public static final Move confuseRay = new MoveDefault("Confuse Ray", GHOST, STATUS, 0, 100, 10).addEffect(Status.CONFUSED, 1.0);
	public static final Move confusion = new MoveDefault("Confusion", PSYCHIC, SPECIAL, 50, 100, 25).addEffect(Status.CONFUSED, 0.1);
	public static final Move constrict = new MoveDefault("Constrict", NORMAL, PHYSICAL, 10, 100, 35).addModifier(Stats.Stat.SPEED, Stats.Modifier.NORMAL, Stats.Sign.FALL, 0.1);
	public static final Move conversion = new MoveDefault("Conversion", NORMAL, STATUS, 0, 0, 30); // TODO Move<Conversion>
	public static final Move counter = new MoveDefault("Counter", FIGHTING, PHYSICAL, 0, 100, 20); // TODO Move<Counter>
	public static final Move crabhammer = new MoveDefault("Crabhammer", WATER, PHYSICAL, 100, 90, 10); // TODO Move<Crabhammer> Crit Ratio
	public static final Move cut = new Cut("Cut", NORMAL, PHYSICAL, 50, 95, 30);
	public static final Move defenceCurl = new MoveStatus("Defence Curl", NORMAL, STATUS, 0, 0, 40).addSelfModifier(Stats.Stat.DEFENSE_PHYSICAL, Stats.Modifier.NORMAL, Stats.Sign.RAISE, 1.0);
	public static final Move dig = new MoveDefault("Dig", GROUND, PHYSICAL, 80, 100, 10); // Todo Move<Dig> Functionality
	public static final Move disable = new MoveDefault("Disable", NORMAL, STATUS, 0, 100, 20); // Todo Move<Disable>
	public static final Move dizzyPunch = new MoveDefault("Dizzy Punch", NORMAL, PHYSICAL, 70, 100, 10).addEffect(Status.CONFUSED, 0.2);
	public static final Move doubleKick = new DoubleKick("Double Kick", FIGHTING, PHYSICAL, 30, 100, 30);
	public static final Move doubleSlap = new MoveDefault("Double Slap", NORMAL, PHYSICAL, 15, 85, 10).setProjectileCount(2, 5);
	public static final Move doubleTeam = new MoveDefault("Double Team", NORMAL, STATUS, 0, 0, 15); // Todo Move<DoubleTeam> Evasiveness
	public static final Move doubleEdge = new MoveDefault("Double Edge", NORMAL, PHYSICAL, 120, 100, 15).addRecoil(1.0 / 3.0, 1.0);
	public static final Move dragonRage = new DragonRage("Dragon Rage", DRAGON, SPECIAL, 0, 100, 10);
	public static final Move dreamEater = new DreamEater("Dream Eater", PSYCHIC, SPECIAL, 100, 100, 15);
	public static final Move drillPeck = new MoveDefault("Drill Peck", FLYING, PHYSICAL, 80, 100, 20);
	public static final Move earthquake = new MoveDefault("Earthquake", GROUND, PHYSICAL, 100, 100, 10); // Todo Move<Earthquake> Underground Functionality
	public static final Move eggBomb = new MoveDefault("Egg Bomb", NORMAL, PHYSICAL, 100, 75, 10);
	public static final Move ember = new MoveDefault("Ember", FIRE, SPECIAL, 40, 100, 25).setProjectileCount(3).addEffect(Status.BURNED, 0.1);
	public static final Move explosion = new MoveDefault("Explosion", NORMAL, PHYSICAL, 250, 100, 5).addRecoil(100.0, 1.0); // Todo Move<Explosion> Explosion Functionality
	public static final Move fireBlast = new FireBlast("Fire Blast", FIRE, SPECIAL, 110, 85, 5).addEffect(Status.BURNED, 0.1);
	public static final Move firePunch = new MoveDefault("Fire Punch", FIRE, PHYSICAL, 75, 100, 15).addEffect(Status.BURNED, 0.1);
	public static final Move fireSpin = new FireSpin("Fire Spin", FIRE, SPECIAL, 35, 85, 15); // Todo Move<FireSpin> Functionality
	public static final Move fissure = new MoveOneHitKO("Fissure", GROUND, PHYSICAL, 0, 0, 5);
	public static final Move flamethrower = new Flamethrower("Flamethrower", FIRE, SPECIAL, 90, 100, 15).addEffect(Status.BURNED, 0.1);
	public static final Move flash = new MoveDefault("Flash", NORMAL, STATUS, 0, 100, 20); // Todo Move<Flash> Accuracy
	public static final Move fly = new MoveDefault("Fly", FLYING, PHYSICAL, 90, 95, 15); // Todo Move<Fly> Flying functionality
	public static final Move focusEnergy = new MoveDefault("Focus Energy", NORMAL, STATUS, 0, 0, 30); // Todo Move<FocusEnergy> Crit ratio
	public static final Move furyAttack = new MoveDefault("Fury Attack", NORMAL, PHYSICAL, 15, 85, 20).setProjectileCount(2, 5);
	public static final Move furySwipes = new MoveDefault("Fury Swipes", NORMAL, PHYSICAL, 18, 80, 15).setProjectileCount(2, 5);
	public static final Move glare = new MoveDefault("Glare", NORMAL, STATUS, 0, 100, 30).addEffect(Status.PARALYZED, 1.0);
	public static final Move growl = new MoveAreaOfEffect("Growl", NORMAL, STATUS, 0, 100, 40).addModifier(Stats.Stat.ATTACK_PHYSICAL, Stats.Modifier.NORMAL, Stats.Sign.FALL, 1.0);
	public static final Move growth = new MoveStatus("Growth", NORMAL, STATUS, 0, 0, 40).addSelfModifier(Stats.Stat.ATTACK_PHYSICAL, Stats.Modifier.NORMAL, Stats.Sign.RAISE, 1.0).addSelfModifier(Stats.Stat.ATTACK_SPECIAL, Stats.Modifier.NORMAL, Stats.Sign.RAISE, 1.0);
	public static final Move guillotine = new MoveOneHitKO("Guillotine", NORMAL, PHYSICAL, 0, 0, 5);
	public static final Move gust = new MoveDefault("Gust", FLYING, SPECIAL, 40, 100, 35); // Todo Move<Gust> Functionality
	public static final Move harden = new MoveStatus("Harden", NORMAL, STATUS, 0, 0, 30).addSelfModifier(Stats.Stat.DEFENSE_PHYSICAL, Stats.Modifier.NORMAL, Stats.Sign.RAISE, 1.0);
	public static final Move haze = new Haze("Haze", ICE, STATUS, 0, 0, 30);
	public static final Move headbutt = new MoveDefault("Headbutt", NORMAL, PHYSICAL, 70, 100, 15).addEffect(Status.FLINCH, 0.3);
	public static final Move highJumpKick = new HighJumpKick("High Jump Kick", FIGHTING, PHYSICAL, 130, 90, 10);
	public static final Move hornAttack = new MoveDefault("Horn Attack", NORMAL, PHYSICAL, 65, 100, 25);
	public static final Move hornDrill = new MoveOneHitKO("Horn Drill", NORMAL, PHYSICAL, 0, 0, 5);
	public static final Move hydroPump = new HydroPump("Hydro Pump", WATER, SPECIAL, 110, 80, 5);
	public static final Move hyperBeam = new HyperBeam("Hyper Beam", NORMAL, SPECIAL, 150, 90, 5).addSelfEffect(Status.FLINCH, 1.0);
	public static final Move hyperFang = new MoveDefault("Hyper Fang", NORMAL, PHYSICAL, 80, 90, 15).addEffect(Status.FLINCH, 0.1);
	public static final Move hypnosis = new MoveDefault("Hypnosis", PSYCHIC, STATUS, 0, 60, 20).addEffect(Status.SLEEPING, 1.0);
	public static final Move iceBeam = new MoveBeam("Ice Beam", ICE, SPECIAL, 90, 100, 10);
	public static final Move icePunch = new MovePunch("Ice Punch", ICE, PHYSICAL, 75, 100, 15);
	public static final Move jumpKick = new HighJumpKick("Jump Kick", FIGHTING, PHYSICAL, 100, 95, 10);
	public static final Move karateChop = new MovePunch("Karate Chop", FIGHTING, PHYSICAL, 50, 100, 25);
	public static final Move kinesis = new MoveDefault("Kinesis", PSYCHIC, STATUS, 0, 80, 15); // Todo Move<Kinesis> Accuracy Functionality
	public static final Move leechLife = new Absorb("Leech Life", BUG, PHYSICAL, 80, 100, 10);
	public static final Move leechSeed = new LeechSeed("Leech Seed", GRASS, STATUS, 0, 90, 10);
	public static final Move leer = new MoveAreaOfEffect("Leer", NORMAL, STATUS, 0, 100, 30).addModifier(Stats.Stat.DEFENSE_PHYSICAL, Stats.Modifier.NORMAL, Stats.Sign.FALL, 1.0);
	public static final Move lick = new MoveDefault("Lick", GHOST, PHYSICAL, 30, 100, 30).addEffect(Status.PARALYZED, 0.3);
	public static final Move lightScreen = new MoveDefault("Light Screen", PSYCHIC, STATUS, 0, 0, 30); // Todo Move<LightScreen> Functionality
	public static final Move lovelyKiss = new MoveDefault("Lovely Kiss", NORMAL, STATUS, 0, 75, 10).addEffect(Status.SLEEPING, 1.0);
	public static final Move lowKick = new MoveDefault("Low Kick", FIGHTING, PHYSICAL, 0, 100, 20); // Todo Move<LowKick> Maybe use shadow size
	public static final Move meditate = new MoveStatus("Meditate", PSYCHIC, STATUS, 0, 0, 40).addModifier(Stats.Stat.ATTACK_PHYSICAL, Stats.Modifier.NORMAL, Stats.Sign.RAISE, 1.0);
	public static final Move megaDrain = new Absorb("Mega Drain", GRASS, SPECIAL, 40, 100, 15);
	public static final Move megaKick = new DoubleKick("Mega Kick", NORMAL, PHYSICAL, 120, 75, 5);
	public static final Move megaPunch = new MovePunch("Mega Punch", NORMAL, PHYSICAL, 80, 85, 20);
	public static final Move metronome = new Metronome("Metronome", NORMAL, STATUS, 0, 0, 10);
	public static final Move mimic = new MoveDefault("Mimic", NORMAL, STATUS, 0, 0, 10); // Todo Move<Mimic> Functionality
	public static final Move minimize = new MoveStatus("Minimize", NORMAL, STATUS, 0, 0, 10); // Todo Move<Evasiveness>
	public static final Move mirrorMove = new MoveDefault("Mirror Move", FLYING, STATUS, 0, 0, 20); // Todo Move<MirrorMove> Functionality
	public static final Move mist = new MoveDefault("Mist", ICE, STATUS, 0, 0, 30); // Todo Move<Mist> Functionality
	public static final Move nightShade = new NightShade("Night Shade", GHOST, SPECIAL, 0, 100, 15);
	public static final Move payDay = new PayDay("Pay Day", NORMAL, PHYSICAL, 40, 100, 20);
	public static final Move peck = new MoveDefault("Peck", FLYING, PHYSICAL, 35, 100, 35);
	public static final Move petalDance = new MoveDefault("Petal Dance", GRASS, SPECIAL, 120, 100, 10).setProjectileCount(2, 3).addSelfEffect(Status.CONFUSED, 1.0);
	public static final Move pinMissile = new PinMissile("Pin Missile", BUG, PHYSICAL, 25, 95, 20);
	public static final Move poisonGas = new MoveDefault("Poison Gas", POISON, STATUS, 0, 90, 40).addEffect(Status.POISONED, 1.0);
	public static final Move poisonPowder = new MoveDefault("Poison Powder", POISON, STATUS, 0, 75, 35).addEffect(Status.POISONED, 1.0);
	public static final Move poisonSting = new PoisonSting("Poison Sting", POISON, PHYSICAL, 15, 100, 35).addEffect(Status.POISONED, 0.3);
	public static final Move pound = new MoveDefault("Pound", NORMAL, PHYSICAL, 40, 100, 35);
	public static final Move psybeam = new MoveBeam("Psybeam", PSYCHIC, SPECIAL, 65, 100, 20);
	public static final Move psychic = new MoveDefault("Psychic", PSYCHIC, SPECIAL, 90, 100, 10).addModifier(Stats.Stat.DEFENSE_SPECIAL, Stats.Modifier.NORMAL, Stats.Sign.FALL, 0.1);
	public static final Move psywave = new Psywave("Psywave", PSYCHIC, SPECIAL, 0, 80, 15);
	public static final Move quickAttack = new QuickAttack("Quick Attack", NORMAL, PHYSICAL, 40, 100, 30);
	public static final Move rage = new MoveDefault("Rage", NORMAL, PHYSICAL, 20, 100, 20); // Todo Move<Rage> Functionality
	public static final Move razorLeaf = new RazorLeaf("Razor Leaf", GRASS, PHYSICAL, 55, 95, 10); // Todo Move<RazorLeaf> Crit Ratio
	public static final Move razorWind = new MoveAreaOfEffect("Razor Wind", NORMAL, SPECIAL, 80, 100, 10);
	public static final Move recover = new Recover("Recover", NORMAL, STATUS, 0, 0, 10);
	public static final Move reflect = new MoveDefault("Reflect", PSYCHIC, STATUS, 0, 0, 20); // Todo Move<Reflect> Functionality
	public static final Move rest = new MoveStatus("Rest", PSYCHIC, STATUS, 0, 0, 10).addSelfEffect(Status.SLEEPING, 1.0);
	public static final Move roar = new MoveDefault("Roar", NORMAL, STATUS, 0, 0, 20); // Todo Move<Roar> functionality
	public static final Move rockSlide = new MoveDefault("Rock Slide", ROCK, PHYSICAL, 75, 90, 10).addEffect(Status.FLINCH, 0.3);
	public static final Move rockThrow = new MoveDefault("Rock Throw", ROCK, PHYSICAL, 50, 90, 15);
	public static final Move rollingKick = new MoveDefault("Rolling Kick", FIGHTING, PHYSICAL, 60, 85, 15).addEffect(Status.FLINCH, 0.3);
	public static final Move sandAttack = new MoveAreaOfEffect("Sand Attack", GROUND, STATUS, 0, 100, 15); // Todo Move<SandAttack> Functionality
	public static final Move scratch = new MoveDefault("Scratch", NORMAL, PHYSICAL, 40, 100, 35);
	public static final Move screech = new MoveDefault("Screech", NORMAL, STATUS, 0, 85, 40).addModifier(Stats.Stat.DEFENSE_PHYSICAL, Stats.Modifier.SHARP, Stats.Sign.FALL, 1.0);
	public static final Move seismicToss = new SeismicToss("Seismic Toss", FIGHTING, PHYSICAL, 0, 100, 20);
	public static final Move selfDestruct = new SelfDestruct("Self Destruct", NORMAL, PHYSICAL, 200, 100, 5);
	public static final Move sharpen = new MoveStatus("Sharpen", NORMAL, STATUS, 0, 0, 30).addSelfModifier(Stats.Stat.ATTACK_PHYSICAL, Stats.Modifier.NORMAL, Stats.Sign.RAISE, 1.0);
	public static final Move sing = new MoveAreaOfEffect("Sing", NORMAL, STATUS, 0, 55, 15).addEffect(Status.SLEEPING, 1.0);
	public static final Move skullBash = new MoveDefault("Skull Bash", NORMAL, PHYSICAL, 130, 100, 10).addSelfModifier(Stats.Stat.DEFENSE_PHYSICAL, Stats.Modifier.NORMAL, Stats.Sign.RAISE, 1.0);
	public static final Move skyAttack = new MoveDefault("Sky Attack", FLYING, PHYSICAL, 140, 90, 5).addEffect(Status.FLINCH, 0.3);
	public static final Move slam = new MoveDefault("Slam", NORMAL, PHYSICAL, 80, 75, 20);
	public static final Move slash = new MoveDefault("Slash", NORMAL, PHYSICAL, 75, 100, 20); // Todo Move<Slash> Crit Ratio
	public static final Move sleepPowder = new MoveAreaOfEffect("Sleep Powder", GRASS, STATUS, 0, 75, 15).addEffect(Status.SLEEPING, 1.0);
	public static final Move sludge = new MoveDefault("Sludge", POISON, SPECIAL, 65, 100, 20).addEffect(Status.POISONED, 0.3);
	public static final Move smog = new MoveAreaOfEffect("Smog", POISON, SPECIAL, 30, 70, 20).addEffect(Status.POISONED, 0.3);
	public static final Move smokescreen = new MoveAreaOfEffect("Smokescreen", NORMAL, STATUS, 0, 100, 20); // Todo Move<Smokescreen> Accuracy
	public static final Move softBoiled = new Recover("Soft Boiled", NORMAL, STATUS, 0, 0, 10);
	public static final Move solarBeam = new MoveBeam("Solar Beam", GRASS, SPECIAL, 120, 100, 10);
	public static final Move sonicBoom = new SonicBoom("Sonic Boom", NORMAL, SPECIAL, 0, 90, 20);
	public static final Move spikeCannon = new PinMissile("Spike Cannon", NORMAL, PHYSICAL, 20, 100, 15);
	public static final Move splash = new MoveDefault("Splash", NORMAL, STATUS, 0, 0, 40);
	public static final Move spore = new MoveDefault("Spore", GRASS, STATUS, 0, 100, 15).addEffect(Status.SLEEPING, 1.0);
	public static final Move stomp = new MovePunch("Stomp", NORMAL, PHYSICAL, 65, 100, 20).addEffect(Status.FLINCH, 0.3);
	public static final Move strength = new MoveDefault("Strength", NORMAL, PHYSICAL, 80, 100, 15);
	public static final Move stringShot = new MoveAreaOfEffect("String Shot", BUG, STATUS, 0, 95, 40); // Todo Move<StringShot> Speed
	public static final Move struggle = new Struggle("Struggle", NORMAL, PHYSICAL, 50, 100, 0);
	public static final Move stunSpore = new MoveAreaOfEffect("Stun Spore", GRASS, STATUS, 0, 75, 30).addEffect(Status.PARALYZED, 1.0);
	public static final Move submission = new MoveDefault("Submission", FIGHTING, PHYSICAL, 80, 80, 20).addRecoil(0.25, 1.0);
	public static final Move substitute = new MoveDefault("Substitute", NORMAL, STATUS, 0, 0, 10); // Todo Move<Substitute> Functionality
	public static final Move superFang = new SuperFang("Super Fang", NORMAL, PHYSICAL, 0, 90, 10);
	public static final Move supersonic = new MoveAreaOfEffect("Supersonic", NORMAL, STATUS, 0, 55, 20).addEffect(Status.CONFUSED, 1.0);
	public static final Move surf = new Surf("Surf", WATER, SPECIAL, 90, 100, 15);
	public static final Move swift = new Swift("Swift", NORMAL, SPECIAL, 60, -1, 20); // Todo Move<Swift> Functionality
	public static final Move swordsDance = new MoveStatus("Swords Dance", NORMAL, STATUS, 0, 0, 20).addSelfModifier(Stats.Stat.ATTACK_PHYSICAL, Stats.Modifier.SHARP, Stats.Sign.RAISE, 1.0);
	public static final Move tackle = new MovePunch("Tackle", NORMAL, PHYSICAL, 40, 100, 35);
	public static final Move tailWhip = new TailWhip("Tail Whip", NORMAL, STATUS, 0, 100, 30).addModifier(Stats.Stat.DEFENSE_PHYSICAL, Stats.Modifier.NORMAL, Stats.Sign.FALL, 1.0);
	public static final Move takeDown = new MoveDefault("Take Down", NORMAL, PHYSICAL, 90, 85, 20).addRecoil(0.25, 1.0);
	public static final Move teleport = new Teleport("Teleport", PSYCHIC, STATUS, 0, 0, 20);
	public static final Move thrash = new MoveDefault("Thrash", NORMAL, PHYSICAL, 120, 100, 10).setProjectileCount(2, 3).addSelfEffect(Status.CONFUSED, 1.0);
	public static final Move thunder = new MoveAreaOfEffect("Thunder", ELECTRIC, SPECIAL, 110, 70, 10).addEffect(Status.PARALYZED, 0.3);
	public static final Move thunderPunch = new MovePunch("Thunder Punch", ELECTRIC, PHYSICAL, 75, 100, 15).addEffect(Status.PARALYZED, 0.1);
	public static final Move thunderShock = new MoveDefault("Thunder Shock", ELECTRIC, SPECIAL, 40, 100, 30).addEffect(Status.PARALYZED, 0.1);
	public static final Move thunderWave = new ThunderWave("Thunder Wave", ELECTRIC, STATUS, 0, 90, 20).addEffect(Status.PARALYZED, 1.0);
	public static final Move thunderbolt = new MoveBeam("Thunderbolt", ELECTRIC, SPECIAL, 90, 100, 15).addEffect(Status.PARALYZED, 0.1);
	public static final Move toxic = new MoveAreaOfEffect("Toxic", POISON, STATUS, 0, 90, 10).addEffect(Status.POISONED, 1.0);
	public static final Move transform = new Transform("Transform", NORMAL, STATUS, 0, 0, 10);
	public static final Move triAttack = new MoveDefault("Tri Attack", NORMAL, SPECIAL, 80, 100, 10);
	public static final Move twineedle = new Twineedle("Twineedle", BUG, PHYSICAL, 25, 100, 20);
	public static final Move viceGrip = new MoveDefault("Vice Grip", NORMAL, PHYSICAL, 55, 100, 30);
	public static final Move vineWhip = new VineWhip("Vine Whip", GRASS, PHYSICAL, 45, 100, 25);
	public static final Move waterGun = new MoveDefault("Water Gun", WATER, SPECIAL, 40, 100, 25);
	public static final Move waterfall = new ThunderWave("Waterfall", WATER, PHYSICAL, 80, 100, 15);
	public static final Move whirlwind = new MoveDefault("Whirlwind", NORMAL, STATUS, 0, 0, 20); // Todo Move<Whirlwind> functionality
	public static final Move wingAttack = new MoveDefault("Wing Attack", FLYING, PHYSICAL, 60, 100, 35);
	public static final Move withdraw = new MoveDefault("Withdraw", WATER, STATUS, 0, 0, 40).addSelfModifier(Stats.Stat.DEFENSE_PHYSICAL, Stats.Modifier.NORMAL, Stats.Sign.RAISE, 1.0);
	public static final Move wrap = new Wrap("Wrap", NORMAL, PHYSICAL, 15, 90, 20);

	@Override
	public void preInit() {}

	@Override
	public void init() {
		for (Move m : Move.moves) Debug.debug(m);
	}

	@Override
	public void postInit() {}
}
