package Game;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundPlayer {
    private static Clip musicClip;
    private final static String resourcePath = "./Resources/";
    private final static String musicPath = resourcePath + "Music/";
    private final static String soundEffectPath = resourcePath + "SoundEffects/";
    // private static AudioInputStream soundEffectStream;
    // private SimpleAudioPlayer audioPlayer = new SimpleAudioPlayer();

    public static enum SoundEffects {
        ITEMGET,
        WORLDCLEAR;

        @Override
        public String toString() {
            String name = super.toString();
            switch (name) {
                case "ITEMGET":
                    return "Receive Item.wav";
                case "WORLDCLEAR":
                    return "Castle Captured.wav";
                default:
                    return null;
            }
        }
    }

    public static enum MusicTracks {
        BATTLETHEME,
        WORLDONEBGM;

        @Override
        public String toString() {
            String name = super.toString();
            switch (name) {
                case "BATTLETHEME":
                    return "Attack! ~ Battle Screen (Player).wav";
                case "WORLDONEBGM":
                    return "Chapter 2 (Crisis in Agustria).wav";
                default:
                    return null;
            }
        }
    }

    public static void playSoundEffect(SoundEffects soundEffect) {
        try {
            AudioInputStream stream = AudioSystem
                    .getAudioInputStream(new File(soundEffectPath, soundEffect.toString()));
            Clip clip = AudioSystem.getClip();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.open(stream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void playMusic(MusicTracks musicTrack) {
        try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(new File(musicPath, musicTrack.toString()));
            if (musicClip != null) {
                musicClip.stop();
            }
            musicClip = AudioSystem.getClip();
            musicClip.open(stream);
            musicClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void stopMusic() {
        musicClip.stop();
    }
}
