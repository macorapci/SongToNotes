package macorapci.similarty;

import macorapci.file.SourceTransaction;
import macorapci.notes.PianoNotes;

public class Similarty {
    public static int[] main(String filepath){
        SourceTransaction sourceTransaction=new SourceTransaction();
        sourceTransaction.readWavFile(filepath);
        sourceTransaction.printInfo();

        int duration=sourceTransaction.dataSize/sourceTransaction.byteSizeOfOneSec;
        int [] notes=new int[duration*2];

        for(int k=0;k<duration*2;k++){
            int key=-1;
            double r=Double.MIN_VALUE;
            int [] sampledWav= Quantization.SampleSignal((Quantization.getOneSec(sourceTransaction.wavFile,k,sourceTransaction.byteSizeOfOneSec)),
                    sourceTransaction.dataSize,
                    sourceTransaction.bitPerSample,
                    4000);

            int [] sampledNote=null;


            for(int j=0;j<12;j++){
                sampledNote= Quantization.SampleSignal(((PianoNotes.notes_piano(j))),
                        PianoNotes.dataSize,
                        PianoNotes.bitPerSample,
                        4000);

                double similarty=CrossCorrelation.crossCorrelation(sampledWav,sampledNote);
                if(r<similarty){
                    r=similarty;
                    key=j;
                }
            }

            notes[k]=key;
        }

        return notes;
    }
}
