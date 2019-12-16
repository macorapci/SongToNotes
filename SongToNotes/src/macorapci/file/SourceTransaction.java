package macorapci.file;

import macorapci.Utils;

import java.io.File;
import java.nio.file.Files;

public class SourceTransaction {
    public int fileIntSize;
    public int formatSize;
    public short formatTag;
    public short channelNumber;
    public long sampleRate;
    public int bitPerSample;
    public short monoMode;
    public int dataSize;
    public int byteSizeOfOneSec;
    public byte[] wavFile=null;

    public void readWavFile(String fileUrl){
        File file=new File(fileUrl);
        try {
            //System.out.println("FILE IS READING...");
            //System.out.println("BE PATIENT!");
            wavFile = Files.readAllBytes(file.toPath());
        }catch (Exception e){
            e.printStackTrace();
            System.exit(-1);
        }

        setWavFileAttribuite();
    }

    private void setWavFileAttribuite(){
        if(wavFile[0]!=(char)'R' || wavFile[1]!=(char)'I' || wavFile[2]!=(char)'F' || wavFile[3]!=(char)'F'){
            System.out.println("THIS AINT WAV-FILE");
            System.out.println("APP WILL CLOSING!");
            System.exit(-2);
        }

        fileIntSize = Utils.byte2Int(new byte[]{wavFile[7],wavFile[6],wavFile[5],wavFile[4]});

        if(wavFile[8]!=(char)'W' || wavFile[9]!=(char)'A' || wavFile[10]!=(char)'V' || wavFile[11]!=(char)'E'){
            System.out.println("THIS AINT WAV-FILE");
            System.out.println("APP WILL CLOSING!");
            System.exit(-3);
        }

        if(wavFile[12]!=(char)'f' || wavFile[13]!=(char)'m' || wavFile[14]!=(char)'t' || wavFile[15]!=(char)' '){
            System.out.println("THIS AINT WAV-FILE");
            System.out.println("APP WILL CLOSING!");
            System.exit(-4);
        }

        formatSize=Utils.byte2Int(new byte[]{wavFile[19],wavFile[18],wavFile[17],wavFile[16]});
        formatTag=Utils.byte2Short(new byte[]{wavFile[21],wavFile[20]});
        channelNumber=Utils.byte2Short(new byte[]{wavFile[23],wavFile[22]});
        sampleRate=Utils.byte2Int(new byte[]{wavFile[27],wavFile[26],wavFile[25],wavFile[24]});
        bitPerSample=Utils.byte2Short(new byte[]{wavFile[35],wavFile[34]});

        int check=Utils.byte2Int(new byte[]{wavFile[31],wavFile[30],wavFile[29],wavFile[28]});
        if(check*8!=(sampleRate*channelNumber*bitPerSample)){
            System.out.println("FILE IS BROKEN");
            System.exit(-5);
        }

        monoMode=Utils.byte2Short(new byte[]{wavFile[33],wavFile[32]});

        if(wavFile[36]!=(char)'d' || wavFile[37]!=(char)'a' || wavFile[38]!=(char)'t' || wavFile[39]!=(char)'a'){
            System.out.println("COULD NOT FIND DATA PART");
        }

        dataSize=Utils.byte2Int(new byte[]{wavFile[43],wavFile[42],wavFile[41],wavFile[40]});

        byteSizeOfOneSec=(int)(channelNumber*sampleRate*(bitPerSample/8.0));

    }

    public void printInfo(){
        System.out.println();
        System.out.println("File size of File :"+fileIntSize);
        System.out.println("Format size :"+formatSize);
        System.out.println("Format Tag :"+formatTag);
        System.out.println("Channel Number :"+channelNumber);
        System.out.println("Sample Rate :"+sampleRate);
        System.out.println("Bit Per Sample :"+bitPerSample);
        System.out.println("Mono Mode :"+bitPerSample);
        System.out.println("Data Size :"+dataSize);
        System.out.println("Duration :"+(double)wavFile.length/byteSizeOfOneSec);
        System.out.println();
    }
}
