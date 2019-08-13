package ninja.cooperstuff.pokemon.sound;

import ninja.cooperstuff.pokemon.util.Constants;
import org.newdawn.easyogg.OggClip;

import java.io.IOException;
import java.util.HashSet;

public class Sound {
	private static HashSet<SoundClip> clips = new HashSet<>();
	private static HashSet<SoundClip> clipRemove = new HashSet<>();

	private String path;
	private double volume;

	public Sound(String path, double volume) {
		this.path = String.format("%s.ogg", path);
		this.volume = volume;
	}

	public Sound(String path) {
		this(path, 1.0);
	}

	public SoundClip play() {
		return Sound.playSound(this);
	}

	public static SoundClip playSound(Sound sound, int delay) {
		System.out.println(sound.path);
		OggClip oggClip;
		try {
			oggClip = new OggClip(Sound.class.getResourceAsStream(sound.path));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		SoundClip clip = new SoundClip(oggClip, delay);
		Sound.clips.add(clip);
		oggClip.setGain((float) Math.min(1, Math.max(0, Constants.audio.masterVolume * sound.volume)));
		if (delay == 0) clip.play();
		return clip;
	}

	public static SoundClip playSound(Sound sound) {
		return Sound.playSound(sound, 0);
	}

	public static void update() {
		for (SoundClip clip : Sound.clips) {
			clip.update();
			if (clip.started() && clip.stopped()) {
				clip.close();
				Sound.clipRemove.add(clip);
			}
		}
		Sound.clips.removeAll(Sound.clipRemove);
		Sound.clipRemove.clear();
	}
}
