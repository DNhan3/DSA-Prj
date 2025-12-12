package utils;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Sound {
    private Random random = new Random();
    private Clip clip;

    public void loadSound(String filePath) {
        try {
            File soundFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void playWithRandomPitch() {
        if (clip != null) {
            try {
                FloatControl rateControl = (FloatControl) clip.getControl(FloatControl.Type.SAMPLE_RATE);
                float originalRate = rateControl.getValue();
                // Random pitch between 0.8 (lower) and 1.2 (higher)
                float pitchFactor = 0.8f + random.nextFloat() * 0.4f;
                rateControl.setValue(originalRate * pitchFactor);
            } catch (IllegalArgumentException e) {
                // Sample rate control not supported, play normally
            }
            clip.setFramePosition(0);
            clip.start();
        }
    }

    public void playLoop() {
        if (clip != null) {
            clip.setFramePosition(0);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void playOnce() {
        if (clip != null) {
            clip.setFramePosition(0);
            clip.start();
        }
    }


    public void stop() {
        if (clip != null) {
            clip.stop();
        }
    }

    public void close() {
        if (clip != null) {
            clip.close();
        }
    }
}
