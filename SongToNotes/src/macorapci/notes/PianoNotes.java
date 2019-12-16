package macorapci.notes;

import macorapci.file.SourceTransaction;

public class PianoNotes {
    public static int dataSize;
    public static int bitPerSample;
    public static int byteSizeOfOneSec;

    public static byte [] notes_piano(int j){
        SourceTransaction sourceTransaction=new SourceTransaction();
        sourceTransaction.readWavFile(int2Note(j));
        dataSize=sourceTransaction.dataSize;
        bitPerSample=sourceTransaction.bitPerSample;
        byteSizeOfOneSec=sourceTransaction.byteSizeOfOneSec;
        return sourceTransaction.wavFile;
    }

    public static String int2Note(int j){
        if(j==0){
            return ("src/macorapci/notes/68437__pinkyfinger__piano-a.wav");
        }else if (j==1){
            return("src/macorapci/notes/68438__pinkyfinger__piano-b.wav");
        }else if (j==2){
            return("src/macorapci/notes/68439__pinkyfinger__piano-bb.wav");
        }else if (j==3){
            return("src/macorapci/notes/68440__pinkyfinger__piano-c.wav");
        }else if (j==4){
            return("src/macorapci/notes/68441__pinkyfinger__piano-c.wav");
        }else if (j==5){
            return("src/macorapci/notes/68442__pinkyfinger__piano-d.wav");
        }else if (j==6){
            return("src/macorapci/notes/68443__pinkyfinger__piano-e.wav");
        }else if (j==7){
            return("src/macorapci/notes/68444__pinkyfinger__piano-eb.wav");
        }else if (j==8){
            return("src/macorapci/notes/68445__pinkyfinger__piano-f.wav");
        }else if (j==9){
            return("src/macorapci/notes/68446__pinkyfinger__piano-f.wav");
        }else if (j==10){
            return("src/macorapci/notes/68447__pinkyfinger__piano-g.wav");
        }else if (j==11){
            return("src/macorapci/notes/68448__pinkyfinger__piano-g.wav");
        }else{
            System.out.println("Unfortunately, our support only 12 key");
            System.exit(0);
        }
        return "a";
    }
}

