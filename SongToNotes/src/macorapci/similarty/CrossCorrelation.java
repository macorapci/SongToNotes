package macorapci.similarty;

public class CrossCorrelation {

    final static private double maxDelayRATE=0.4;

    public static double crossCorrelation(int [] musicSignal,int [] noteSignal){
        if(musicSignal.length!=noteSignal.length){
            System.out.println("2 SIGNAL HAVE DIFFERENT LENGHT!");
            System.out.println("PROCESS WILL CLOSING!");
            System.exit(0);
        }

        int i,j,N=musicSignal.length;
        double mx,my,sx,sy,sxy,denom,r;
        int maxDelay=(int)(musicSignal.length * maxDelayRATE);

        /* Calculate the mean of the two series x[], y[] */
        mx = 0;
        my = 0;
        for (i=0;i<N;i++) {
            mx += musicSignal[i];
            my += noteSignal[i];
        }
        mx /= N;
        my /= N;

        /* Calculate the denominator */
        sx = 0;
        sy = 0;
        for (i=0;i<N;i++) {
            sx += (musicSignal[i] - mx) * (musicSignal[i] - mx);
            sy += (noteSignal[i] - my) * (noteSignal[i] - my);
        }
        denom=Math.sqrt(sx*sy);

        r= Double.MIN_VALUE;
        /* Calculate the correlation series */
        for (int delay=-maxDelay;delay<maxDelay;delay++) {
            sxy = 0;
            for (i = 0; i < N; i++) {
                j = i + delay;
                if (j < 0 || j >= N)
                    continue;
                else
                    sxy += (musicSignal[i] - mx) * (noteSignal[j] - my);
            }

            if(r < (sxy / denom)){
                r = sxy / denom;
            }
            /* r is the correlation coefficient at "delay" */
        }

        return r;

    }

    public static byte[] sampledData(byte [] data,int sampledNumber){
        byte [] temp=new byte[sampledNumber];
        int step=data.length/sampledNumber;
        for(int k=0;k<sampledNumber;k++){
            temp[k]=data[k*step];
        }
        return temp;
    }
}


