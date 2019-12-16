package macorapci;

import macorapci.file.PrintNewWavFile;
import macorapci.similarty.Similarty;

public class Main {

    public static void main(String[] args) {
	// write your code here
        //int notes[]=Similarty.main("/home/macorapci/IdeaProjects/SongToNotes/src/macorapci/notes/68437__pinkyfinger__piano-a.wav");
        //int notes[]=Similarty.main("/home/macorapci/Downloads/file_example_WAV_1MG.wav");
        int notes[]=Similarty.main("/home/macorapci/Downloads/file_example_WAV_1MG.wav");
        PrintNewWavFile.combineWav(notes);
        String path=System.getProperty("user.dir")+"/src/macorapci/products";
        System.out.println(path);
    }
}
