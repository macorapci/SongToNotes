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
}
