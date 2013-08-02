/*
 * FileReaderWriter will be assigned to each file/tab
 * 
 */
package com.intuit.cg.tools.FileSystemViewer;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mpaysan
 */
public class FileReaderWriter {
    private File file;
    
    StringBuffer stringBuffer;
    
    private boolean isSaved=false;
    
    private final static String TEST_FILE="C:\\Users\\mpaysan\\Desktop\\TESTME.txt";
        
    public FileReaderWriter(File file){
        this.file=file;
        this.stringBuffer = new StringBuffer();
        try {
            readLargerTextFile();
        } catch (IOException ex) {
            Logger.getLogger(FileReaderWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//end constructor(file)
    
    private void readLargerTextFile() throws IOException{
        BufferedReader br = null;
        FileReader fr = null;
        String line = null;
        try{
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            
            while((line = br.readLine())!=null){
                stringBuffer.append(line+"\n");
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally{
            try {
                if (fr != null){  
                    fr.close();  
                    fr = null;  
                }  
                if (br != null){  
                    br.close();  
                    br = null;  
                }  
            } catch (IOException ex) {  
                ex.printStackTrace();  
            }//end try-catch             
        }//end finally
    }//end readLargerTextFile()
    
    //Gives the string representation of the files contents
    @Override
    public String toString(){
        return stringBuffer.toString();
    }//end toString()
    
    public String getContents(){
        return toString();
    }//end getContents()
    
    public boolean saveContents(){
        
        return true;
    }//end saveContents
}//end class FileReaderWriter
