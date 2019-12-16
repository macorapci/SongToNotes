package macorapci.file;

import java.io.File;
import java.io.SequenceInputStream;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import static macorapci.notes.PianoNotes.int2Note;

public class PrintNewWavFile {
    public static void combineWav(int[] keys) {
        String path="src/macorapci/products/macorapci";
        String wavFileProduct = path;

        for(int k=0;k<keys.length;k++){
            if(k==0){
                try {
                    AudioInputStream clip1 = AudioSystem.getAudioInputStream(new File(int2Note(keys[k])));

                    AudioInputStream appendedFiles =
                            new AudioInputStream(
                                    clip1,
                                    clip1.getFormat(),
                                    clip1.getFrameLength());

                    AudioSystem.write(appendedFiles,
                            AudioFileFormat.Type.WAVE,
                            new File(wavFileProduct+"0.wav"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                continue;
            }else{
                try {
                    File old_file=new File(wavFileProduct+(k-1)+".wav");
                    AudioInputStream clip1 = AudioSystem.getAudioInputStream(old_file);
                    AudioInputStream clip2 = AudioSystem.getAudioInputStream(new File(int2Note(keys[k])));

                    AudioInputStream appendedFiles =
                            new AudioInputStream(
                                    new SequenceInputStream(clip1, clip2),
                                    clip1.getFormat(),
                                    clip1.getFrameLength() + clip2.getFrameLength());

                    String finalFile;
                    if(keys.length-1==k){
                        finalFile=wavFileProduct+"Prodoct"+".wav";
                    }else{
                        finalFile=wavFileProduct+k+".wav";
                    }

                    AudioSystem.write(appendedFiles,
                            AudioFileFormat.Type.WAVE,
                            new File(finalFile));

                    old_file.delete();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
