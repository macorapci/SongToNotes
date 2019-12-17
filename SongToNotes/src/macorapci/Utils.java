package macorapci;

import java.nio.ByteBuffer;

public class Utils {
    public static long byte2Long(byte [] array){
        return ByteBuffer.wrap(array).getLong();
    }

    public static int byte2Int(byte [] array){
        return ByteBuffer.wrap(array).getInt();
    }

    public static short byte2Short(byte [] array){
        return ByteBuffer.wrap(array).getShort();
    }

    public static void printByteArray(byte [] array,int size){
        for(int k=0;k<size;k++){
            System.out.println("byteArray["+k+"]= "+array[k]);
        }
    }

    public static void pritnIntArray(int [] array,int size){
        for(int k=0;k<size;k++){
            System.out.println("Int Array["+k+"]= "+array[k]);
        }
    }
}
