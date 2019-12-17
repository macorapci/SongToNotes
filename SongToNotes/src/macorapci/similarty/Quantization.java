package macorapci.similarty;

import macorapci.Utils;

public class Quantization {

    public static int [] sampleSignal(byte [] data,int sampleSizeInBits,int sampleSize){
        //sampled array left rigth left rigth left ri
        int sampleSizeInBytes=sampleSizeInBits/8;
        int [] temp=new int[sampleSize];
        int step=(int)Math.floor(data.length/temp.length);

        for(int k=0;k<temp.length;k++){
            byte [] bytes=new byte[sampleSizeInBytes];

            for(int j=0;j<bytes.length;j++){
                //System.out.println("k-> "+k+" k*step+j-> "+(k*step+j)+" templen-> "+temp.length+" datalen-> "+data.length);
                bytes[sampleSizeInBytes-j-1]=data[k*step+j];
            }
            if(sampleSizeInBytes<3){
                temp[k]= Utils.byte2Short(bytes);
            }else if(sampleSizeInBytes<9){
                temp[k]= Utils.byte2Int(bytes);
            }else{
                System.out.println("Too much bytes for define one sample.");
                System.exit(0);
            }

        }

        return temp;
    }

    public static byte [] getOneSec(byte [] data,int part,double sampleRate,double noteRateInSec){
        if(data==null){
            System.out.println("wavFile is null");
            System.exit(0);
        }

        int frame=(int) (sampleRate/noteRateInSec);
        //data-> left r left r left right

        byte [] array=new byte[frame];
        for(int k=0;k<frame;k++){
            array[k]=data[(int)(sampleRate*noteRateInSec*part)+k];
        }
        return array;
    }

}
