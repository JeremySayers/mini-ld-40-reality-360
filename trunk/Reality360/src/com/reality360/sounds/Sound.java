package com.reality360.sounds;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import com.reality360.Reality360;

public class Sound {
	private AudioInputStream stream = null;
	private Clip clip;
	public Sound(String path) {
		try {
			AudioInputStream ais = Reality360.loadSound(path);
			AudioFormat baseFormat = ais.getFormat();
			AudioFormat decodedFormat = new AudioFormat(
					AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(),
					16, baseFormat.getChannels(), baseFormat.getChannels() * 2,
					baseFormat.getSampleRate(), false);
			stream = AudioSystem.getAudioInputStream(decodedFormat, ais);
			clip = AudioSystem.getClip();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void play() {
		try {
			clip.open(stream);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
