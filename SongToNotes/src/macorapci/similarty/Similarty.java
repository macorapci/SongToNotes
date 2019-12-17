package macorapci.similarty;

import macorapci.file.SourceTransaction;
import macorapci.notes.PianoNotes;

public class Similarty {

    private static final double noteRateInSec=2;
    public static int[] main(String filepath){
        SourceTransaction sourceTransaction=new SourceTransaction();
        sourceTransaction.readWavFile(filepath);
        sourceTransaction.printInfo();

        double duration=(double)sourceTransaction.wavFile.length/(sourceTransaction.sampleRate *
                (sourceTransaction.sampleSizeInBits/8) * sourceTransaction.channelNumber);
        System.out.println("Wav music duration ="+duration);

        int notesLen=(int) (duration*noteRateInSec);
        int [] notes=new int[notesLen];
        for(int k=0;k<notesLen;k++){
            int key=0;
            double r=Double.MIN_VALUE;
            int [] sampledWav=Quantization.sampleSignal(Quantization.getOneSec(sourceTransaction.wavFile,
                        k,
                        sourceTransaction.sampleRate,
                        noteRateInSec),
                    sourceTransaction.sampleSizeInBits,
                    10000);

            int [] sampledNote=null;

            for(int j=0;j<12;j++){
                sampledNote=Quantization.sampleSignal((PianoNotes.notes_piano(j)),
                        PianoNotes.sampleSizeInBits,
                        10000);

                double similarty=CrossCorrelation.crossCorrelation(sampledWav,sampledNote);
                if(r<similarty){
                    r=similarty;
                    key=j;
                }
            }
            notes[k]=key;
            System.out.println("%"+(int)(k*(100.0/notesLen)));
        }

        return notes;
        /*

        int duration=sourceTransaction.dataSize/sourceTransaction.byteSizeOfOneSec;
        int [] notes=new int[duration*2];

        for(int k=0;k<duration*2;k++){
            int key=-1;
            double r=Double.MIN_VALUE;
            int [] sampledWav= Quantization.SampleSignal((Quantization.getOneSec(sourceTransaction.wavFile,k,sourceTransaction.byteSizeOfOneSec)),
                    sourceTransaction.dataSize,
                    sourceTransaction.sampleSizeInBits,
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

         */
    }
}
