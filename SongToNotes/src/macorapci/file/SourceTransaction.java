package macorapci.file;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class SourceTransaction {
    public long frameSize;
    public float frameRate;
    public int channelNumber;
    public float sampleRate;
    public int sampleSizeInBits;
    public byte[] wavFile=null;

    public AudioInputStream ais;

    public void readWavFile(String fileUrl){
        File file=new File(fileUrl);
        try {
            ais=AudioSystem.getAudioInputStream(file);
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();

            AudioFormat audioFormat = ais.getFormat();
            channelNumber=audioFormat.getChannels();
            frameRate=audioFormat.getFrameRate();
            sampleRate=audioFormat.getSampleRate();
            sampleSizeInBits=audioFormat.getSampleSizeInBits();
            frameSize=ais.getFrameLength();

            byte[] data = new byte[4096];
            int count=0;

            while ((count=ais.read(data,0,data.length))!=-1){
                buffer.write(data,0,count);
            }

            wavFile=buffer.toByteArray();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("SOME ERROR IN READING FILE...");
            System.exit(0);
        }
    }

    public void printInfo(){
        AudioFormat audioFormat = ais.getFormat();
        System.out.println("Channels = " + audioFormat.getChannels());
        System.out.println("Frame Rate = " + audioFormat.getFrameRate());
        System.out.println("Frame Size = " + audioFormat.getFrameSize());
        System.out.println("Sample Rate = " + audioFormat.getSampleRate());
        System.out.println("Sample Size In Bits = " + audioFormat.getSampleSizeInBits());
        System.out.println("Encoding = " + audioFormat.getEncoding());
        System.out.println("Format ="+ais.getFormat());
        System.out.println("Frame Len ="+ais.getFrameLength());
    }
}
