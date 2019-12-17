package macorapci;

import macorapci.file.PrintNewWavFile;
import macorapci.similarty.Similarty;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;

public class Main{

    private static JFrame jFrame;

    public static void main(String[] args) {
        //gui();
        JFileChooser wav_chooser=fileChoose();
        if(wav_chooser.getSelectedFile()==null){
            System.out.println("Selected file is NULL");
            System.exit(0);
        }
        int notes[]=Similarty.main(wav_chooser.getSelectedFile()+"");
        Utils.pritnIntArray(notes,notes.length);
        String wavPath=wav_chooser.getCurrentDirectory().toString();
        String wavFileNameWithoutExt=wav_chooser.getSelectedFile().toString().substring(0,wav_chooser.getSelectedFile().toString().length()-4);
        System.out.println("File Path: "+wavPath);
        System.out.println("File Name: "+wavFileNameWithoutExt);
        PrintNewWavFile.combineWav(notes,wavPath,wavFileNameWithoutExt);
    }

    public static JFileChooser fileChoose() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("only wav files.", "wav");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to open this file: " +
                    chooser.getSelectedFile());

        }
        return chooser;
    }
}
