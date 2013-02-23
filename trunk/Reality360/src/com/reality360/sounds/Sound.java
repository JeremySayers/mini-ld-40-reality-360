package com.reality360.sounds;

import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import com.reality360.Reality360;

public class Sound {
	private AudioInputStream stream = null;
	private Clip clip;

	public Sound(String path) {
		try {
			AudioInputStream ais = Reality360.loadSound(path);
			AudioFormat baseFormat = ais.getFormat();
			final AudioFormat decodedFormat = new AudioFormat(
					AudioFormat.Encoding.PCM_SIGNED,
					baseFormat.getSampleRate(), 16, baseFormat.getChannels(),
					baseFormat.getChannels() * 2, baseFormat.getSampleRate(),
					false);
			stream = AudioSystem.getAudioInputStream(decodedFormat, ais);
			new Thread(){
				public void run() {
					try {
						rawplay(decodedFormat, stream);
					} catch (Exception e) {
						e.printStackTrace();
					} 
				}
			}.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void rawplay(AudioFormat targetFormat, AudioInputStream din)
			throws IOException, LineUnavailableException {
		byte[] data = new byte[4096];
		SourceDataLine line = getLine(targetFormat);
		if (line != null) {
			// Start
			line.start();
			int nBytesRead = 0, nBytesWritten = 0;
			while (nBytesRead != -1) {
				nBytesRead = din.read(data, 0, data.length);
				if (nBytesRead != -1)
					nBytesWritten = line.write(data, 0, nBytesRead);
			}
			// Stop
			line.drain();
			line.stop();
			line.close();
			din.close();
		}
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
