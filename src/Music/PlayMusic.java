package Music;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;

public class PlayMusic {


    public PlayMusic(){};
    public static void playMusic(String filePath){
        try
        {
            File file = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Erreur musique");
        }

    }
}
