package com.reality360.sounds;

import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import com.reality360.Reality360;

public class Sound {
	private boolean playing = false;
	private boolean stopped = false;
	private boolean loop = false;
	private static ArrayList<Sound> instances = new ArrayList<Sound>();
	public Sound(final String path, boolean loopSound) {
		loop = loopSound;
		instances.add(this);
		try {
			new Thread(){
				public void run() {
					try {
						do {
							final AudioInputStream ais = Reality360.loadSound(path);
							AudioFormat baseFormat = ais.getFormat();
							final AudioFormat decodedFormat = new AudioFormat(
									AudioFormat.Encoding.PCM_SIGNED,
									baseFormat.getSampleRate(), 16, baseFormat.getChannels(),
									baseFormat.getChannels() * 2, baseFormat.getSampleRate(),
									false);
							rawplay(decodedFormat, AudioSystem.getAudioInputStream(decodedFormat, ais));
						} while (loop && !stopped);
						instances.remove(this);
					} catch (Exception e) {
						instances.remove(this);
					} 
				}
			}.start();
		} catch (Exception e) {
			instances.remove(this);
		}
	}
	
	public static void stopAll() {
		for (Sound s:instances) {
			s.pause();
		}
	}

	private void rawplay(AudioFormat targetFormat, AudioInputStream din)
			throws IOException, LineUnavailableException {
		byte[] data = new byte[4096];
		SourceDataLine line = getLine(targetFormat);
		if (line != null) {
			// Start
			line.start();
			int nBytesRead = 0;
			while (nBytesRead != -1) {
				while (!playing) {
					if (stopped) {
						return;
					}
				}
				nBytesRead = din.read(data, 0, data.length);
				if (nBytesRead != -1)
					line.write(data, 0, nBytesRead);
			}
			// Stop
			line.drain();
			line.stop();
			line.close();
			din.close();
		}
	}
	
	public void play() {
		playing = true;
	}
	
	public void pause() {
		playing = false;
	}
	
	public void stop() {
		playing = false;
		stopped = true;
	}

	private SourceDataLine getLine(AudioFormat audioFormat)
			throws LineUnavailableException {
		SourceDataLine res = null;
		DataLine.Info info = new DataLine.Info(SourceDataLine.class,
				audioFormat);
		res = (SourceDataLine) AudioSystem.getLine(info);
		res.open(audioFormat);
		return res;
	}

}
