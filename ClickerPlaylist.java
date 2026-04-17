import javax.sound.sampled.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class ClickerPlaylist {
    private ArrayList<String> songs;
    private Clip currentClip;
    private Random rand = new Random();

    public ClickerPlaylist() {
        songs = new ArrayList<String>();
        // Add your .wav files here
        songs.add("song1.wav");
        songs.add("song2.wav");
        songs.add("song3.wav");
    }

    public void playRandomSong() {
        // Stop any music currently playing
        if (currentClip != null) {
            currentClip.stop();
            currentClip.close();
        }

        try {
            // Pick a random index from the list
            int index = rand.nextInt(songs.size());
            File f = new File(songs.get(index));
            
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(f);
            currentClip = AudioSystem.getClip();
            currentClip.open(audioIn);
            
            // This "Listener" waits for the song to end
            currentClip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    // When the song stops, pick another random one!
                    playRandomSong();
                }
            });

            currentClip.start();
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}