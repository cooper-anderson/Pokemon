package ninja.cooperstuff.pokemon.sound;

import org.newdawn.easyogg.OggClip;

public class SoundClip {
	private OggClip clip;
	public int delay;
	private boolean started;
	private boolean stopped;

	public SoundClip(OggClip clip, int delay) {
		this.clip = clip;
		this.delay = delay;
	}

	public boolean started() {
		return this.started;
	}

	public boolean stopped() {
		return this.stopped;
	}

	public void play() {
		if (!this.started) {
			this.started = true;
			this.clip.play();
		}
	}

	public void close() {
		this.stopped = true;
		this.clip.close();
	}

	protected void update() {
		if (!this.started && this.delay <= 0) this.play();
		if (this.started && this.clip.stopped()) this.close();
		this.delay--;
	}
}
