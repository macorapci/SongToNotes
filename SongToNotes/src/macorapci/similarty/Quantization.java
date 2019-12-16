package macorapci.similarty;

import macorapci.Utils;

public class Quantization {

    public static int [] SampleSignal(byte [] data,int dataSize,int bitPerSample,int sampleSize){
        int bytePerSample=bitPerSample/8;
        int [] temp=new int[sampleSize];
        int step=(int)Math.floor(data.length/sampleSize);
        for(int k=0;k<sampleSize;k++){
            byte [] bytes=new byte[bytePerSample];
            for(int j=0;j<bytePerSample;j++){
                //System.out.println("data len:"+data.length+" k*step:"+(k*step+j)+" k:"+k);
                bytes[bytePerSample-j-1]=data[k*step+j];
            }
            if(bytePerSample<3){
                temp[k]= Utils.byte2Short(bytes);
            }else if(bytePerSample<9){
                temp[k]= Utils.byte2Int(bytes);
            }else{
                //Too Big PCM-DATA
                System.out.println("So much bit per sample");
                System.exit(-7);
            }
        }
        return temp;
    }

    public static byte [] getOneSec(byte [] data,int sec,int byteSizeOfOneSec){
        if(data==null){
            System.out.println("wavFile is null");
            System.exit(0);
        }
        byteSizeOfOneSec/=2;
        byte [] array=new byte[byteSizeOfOneSec];
        for(int k=44+(byteSizeOfOneSec*sec);k<44+(byteSizeOfOneSec*(sec+1));k++){
            //System.out.println("data len:"+data.length+" k:"+(k)+" array len:"+array.length+" sec:"+sec);
            array[k-(44+(byteSizeOfOneSec*sec))]=data[k];
        }
        return array;
    }

}
