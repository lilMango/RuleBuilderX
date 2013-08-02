
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.Icon;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mpaysan
 */
public class TextEditor {
    File file;
    private RSyntaxTextArea textArea;
    private RTextScrollPane rScrollPane;
    private static Icon savedIcon;
    private static Icon unsavedIcon;
    public boolean isSaved;
    
    public TextEditor(File file){
    	this.isSaved=true;
        this.file=file;
        textArea = new RSyntaxTextArea(60, 60);
        setSyntax();
        textArea.setCodeFoldingEnabled(true);
        textArea.setAntiAliasingEnabled(true);
        rScrollPane = new RTextScrollPane(textArea);              
        rScrollPane.setFoldIndicatorEnabled(true);  
        
        textArea.addKeyListener(new KeyAdapter(){
         public void keyPressed(KeyEvent evt){
             if(evt.isControlDown() && evt.getKeyCode() == KeyEvent.VK_S){
                 System.out.println("Saved called inside textArea!");
             }else{
                 isSaved=false;System.out.println("typed in textArea");
             }
         
         }//end keyPressed(KeyEvent)
         
         public void keyReleased(KeyEvent evt){}

     });//end textArea.addKeyListener()
        rScrollPane.addKeyListener(new KeyAdapter(){
         public void keyPressed(KeyEvent evt){
             if(evt.isControlDown() && evt.getKeyCode() == KeyEvent.VK_S){
                 System.out.println("Saved called inside ScrollPane!");
            }
         
         }//end keyPressed(KeyEvent)
         
         public void keyReleased(KeyEvent evt){}
         public void keyType(KeyEvent evt){System.out.println("type in ScrollArea);");}
     });//end rScrollPane.addKeyListener()
        
    }//end constructor(File)
    
    /*
     * TODO
     */
    public void setText(String str){
        textArea.setText(str);
    }//end setText(String)
    
    /*
     *  Open and read file contents
     */
    public void readFile(File file){
        
    }//end readFile(File)
    
    public void setIsSaved(boolean arg){
        isSaved=arg;
    }//end setIsSaved(bool)
    public boolean isSaved(){
        return this.isSaved;
    }
    /*
     * Write file to path and boldens the string name of the tab
     * @TODO
     */
    public void saveFile(){
        if(!isSaved){
            try {
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(textArea.getText());
			bw.close();
 
			System.out.println("Done Saving...");
 
		} catch (IOException e) {
			e.printStackTrace();
		}
            isSaved=true;
        }
    }//end saveFile()
    
    public RTextScrollPane getRTextScrollPane(){
        return this.rScrollPane;
    }//end getRTextScrollPane()
    
    public String getName(){
        return file.getName();
    }//end getName()
       
    
    private String getFileExtension(){
        String extension = "";
        int i = file.getName().lastIndexOf('.');
        if(i>0){
            extension= file.getName().substring(i+1);
        }
        return extension;
    }//end getFileExtension()
    
    private void setSyntax(){
        String ext=getFileExtension();
        
        if("c".equals(ext)){
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_C);
        }else if("cpp".equals(ext)||"hpp".equals(ext)){
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CPLUSPLUS);
        }else if("css".equals(ext)){
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CSS);
        }else if("html".equals(ext)){
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_HTML);   
        }else if("java".equals(ext)){
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        }else if(".js".equals(ext)){
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT);
        }else if("py".equals(ext)){
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_PYTHON);
        }else if("php".equals(ext)){
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_PHP);
        }else if("rb".equals(ext)){
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_RUBY);
        }else if("xml".equals(ext)||"xslt".equals(ext)){
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_XML);
        }
    
    }//end setSyntax()
    
}//end class Editor
