package utils;

import sun.applet.Main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.HashMap;
import java.util.Map;

public class QSoundLoader {
    private static int ID;
    private static Map<String, String> files;
    private static Map<String, AudioInputStream> streams;
    private static QSoundLoader loader;
    private static Map<Integer, SoundDriver> soundsPlaying;
    public static QSoundLoader getInstance() {
        if(loader == null) {
            loader = new QSoundLoader();
        }
        return loader;
    }

    private QSoundLoader() {
        files = new HashMap<>();
        streams = new HashMap<>();
        soundsPlaying = new HashMap<>();
        ID = 0;

        // load sounds
        files.put("click", "/sounds/click.wav");
        files.put("shot", "/sounds/shot.wav");
        files.put("die", "/sounds/die.wav");
        files.put("beat","/sounds/heartbeat.wav");
        files.put("air", "/sounds/air.wav");
        files.put("victory", "/sounds/victory.wav");
        refresh("click");
        refresh("die");
        refresh("beat");
        refresh("shot");
        refresh("air");
        refresh("victory");
    }

    public synchronized void playClip(String clipName) {
        new Thread(new Runnable() {
            @Override
            public synchronized void run() {
                Clip clip;
                try {
                    clip = AudioSystem.getClip();
                    AudioInputStream stream = streams.get(clipName);
                    clip.open(stream);
                    clip.start();
                    refresh(clipName);
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }}).start();
    }

    public synchronized int playSound(String clipName) {
        SoundDriver driver = null;
        try {
            driver = new SoundDriver(AudioSystem.getClip(), clipName, streams.get(clipName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Thread t = new Thread(driver);
        t.start();
        soundsPlaying.put(ID, driver);
        ID++;
        return ID-1;
    }

    public synchronized void stopSound(int ID) {
        SoundDriver t = soundsPlaying.get(ID);
        if(t != null) {
            t.exit();
            soundsPlaying.remove(ID);
        }
    }

    private void refresh(String clipName) {
        try {
            /* LOAD SOUNDS HERE */
            streams.put(clipName, AudioSystem.getAudioInputStream(
                    Main.class.getResourceAsStream(files.get(clipName))));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public class SoundDriver implements Runnable {
        private Clip clip;
        private AudioInputStream stream;
        private String clipName;
        private volatile boolean exited;
        public SoundDriver(Clip clip, String clipName, AudioInputStream stream) {
            this.clip = clip;
            this.stream = stream;
            this.clipName = clipName;
            this.exited = false;
        }

        public void run() {
            try {
                clip = AudioSystem.getClip();
                clip.open(stream);
                clip.start();
                while(!exited);
                clip.stop();
                refresh(clipName);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        public void exit() {
            exited = true;
        }
    }
}
