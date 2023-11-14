package Game;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundPlayer {
    private static Clip musicClip;
    private final static String resourcePath = "./Resources/";
    private final static String musicPath = resourcePath + "Music/";
    private final static String soundEffectPath = resourcePath + "SoundEffects/";
    private static Boolean inIntro = false;
    // private static AudioInputStream soundEffectStream;
    // private SimpleAudioPlayer audioPlayer = new SimpleAudioPlayer();

    public static enum SoundEffects {
        ITEMGET,
        WORLDCLEAR,
        FRAGMENTGET;

        @Override
        public String toString() {
            String name = super.toString();
            switch (name) {
                case "ITEMGET":
                    return "Receive Item";
                case "WORLDCLEAR":
                    return "Castle Captured";
                case "FRAGMENTGET":
                    return "Receive Money";
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
            switch (getInternalName()) {
                case "BATTLETHEME":
                    return "Battle 1";
                case "WORLDONEBGM":
                    return "Onett Theme";
                default:
                    return null;
            }
        }

        public Boolean doesLoop() {
            switch (getInternalName()) {
                case "BATTLETHEME":
                    return true;
                default:
                    return false;
            }
        }

        private String getInternalName() {
            return super.toString();
        }
    }

    public static void playSoundEffect(SoundEffects soundEffect) {
        try {
            AudioInputStream stream = AudioSystem
                    .getAudioInputStream(new File(soundEffectPath, soundEffect.toString() + ".wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(stream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void playMusic(MusicTracks musicTrack) {
        try {
            if (musicClip == null) {
                musicClip = AudioSystem.getClip();
            }
            stopMusic();
            String trackName = musicTrack.toString() + ".wav";
            if (musicTrack.doesLoop()) {
                trackName = musicTrack.toString() + "-intro.wav";
            }
            AudioInputStream stream = AudioSystem.getAudioInputStream(new File(musicPath, trackName));
            musicClip.open(stream);
            if (musicTrack.doesLoop()) {
                inIntro = true;
                AudioInputStream loopStream = AudioSystem.getAudioInputStream(new File(musicPath, musicTrack.toString() + "-loop.wav"));
                Clip loopingClip = AudioSystem.getClip();
                loopingClip.open(loopStream);
                LineListener listener = new LineListener() {
                    private Clip loopClip = loopingClip;
                    @Override
                    public void update(LineEvent event) {
                        if (event.getType() == LineEvent.Type.STOP && inIntro) {
                            inIntro = false;
                            musicClip.close();
                            musicClip = loopClip;
                            musicClip.loop(Clip.LOOP_CONTINUOUSLY);
                            musicClip.start();
                        }
                    }
                };
                musicClip.addLineListener(listener);
            }
            else {
                musicClip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            musicClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void stopMusic() {
        inIntro = false;
        musicClip.close();
    }
}
