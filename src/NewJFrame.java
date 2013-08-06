/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.intuit.cg.tools.FileSystemViewer.*;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.tree.TreeModel;

import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.ScrollPane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;



/**
 *
 * @author mpaysan
 */
public class NewJFrame extends JFrame {
    private static int hi=0;
    KeyboardFocusManager keyManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
     
    private RSyntaxTextArea textArea;
    private RTextScrollPane rScrollPane;
    private FileSystemModel fileSystemModel;
    
    private ArrayList<TextEditor> arrTextEditors;
    private boolean isAnd=true;
    private boolean isSavingFlag=false;
    private XsltBuilder xsltBuilder;
    private HashMap<Component,TextEditor> mapTabTE;
    /**
     * Creates new form NewJFrame
     */
    public NewJFrame() {
        keyManager.addKeyEventDispatcher(new MyDispatcher()); // so ctrl-s is application wide
   
        initComponents();
        initFileTreeViewer();
        //initCodeTextArea();//jTabbedPane1's post-creation code
        xsltBuilder = new XsltBuilder();
        arrTextEditors = new ArrayList<TextEditor>();
        mapTabTE = new HashMap<Component,TextEditor>();
    }

    /* Application Wide Key Listener
     * 
     */
    private class MyDispatcher implements KeyEventDispatcher{
        public boolean dispatchKeyEvent(KeyEvent e){
            if(e.getID() == KeyEvent.KEY_PRESSED){
              if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_S){
                  if(!isSavingFlag){
                      isSavingFlag=true;

                    Component tempC = jTabbedPane1.getSelectedComponent();
                    int tempI = jTabbedPane1.getSelectedIndex();
                    TextEditor tempTE=mapTabTE.get(tempC);
                    
                    System.out.println("Times saved: "+hi++);
                    System.out.println(jTabbedPane1.getTitleAt(tempI));
                    
                    if(tempTE!=null){
                        System.out.println("am i saved?: "+tempTE.isSaved());
                        if(!tempTE.isSaved()){
                            tempTE.saveFile();
                            mapTabTE.put(tempC, tempTE);
                            jTabbedPane1.setTitleAt(tempI,tempTE.getName());
                            System.out.println("is Saved");
                        }
                    }else{
                        System.out.println("invalid file");
                    }
                  }
              }   
            } else if (e.getID() == KeyEvent.KEY_RELEASED){
                if(e.getKeyCode() == KeyEvent.VK_S){
                    //System.out.println("Released key");
                    isSavingFlag=false;
                }
            } else if (e.getID() == KeyEvent.KEY_TYPED){
                System.out.println("MyDispatcherType");//TODO No Adding duplicate tags, and saving marks the name with *
                Component tempC = jTabbedPane1.getSelectedComponent();
                int tempI = jTabbedPane1.getSelectedIndex();
                TextEditor tempTE = mapTabTE.get(tempC);
                
                if(tempTE!=null){
                    if(!tempTE.isSaved()){
                        jTabbedPane1.setTitleAt(tempI, "*"+tempTE.getName());

                    }//end !tempTE.isSaved()
                    else{
                        
                    }
                }//end tempTE!=null
            }
            return false;
        }//end dispatchKeyEvent()

    }//end class MyDispatcher
    
    /*
     *  Used to initialize the Syntax highlighted Code text area
     */
    private void initCodeTextArea(){
      textArea = new RSyntaxTextArea(60, 60);
      //textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_XML);
      textArea.setCodeFoldingEnabled(true);
      textArea.setAntiAliasingEnabled(true);
      rScrollPane = new RTextScrollPane(textArea);
      textArea.setText("Welcome to the XSLT Rule Builder!\n"+
    		  			"A Paysan Production.");
      
      jTabbedPane1.addTab("Welcome", rScrollPane);  
     jTabbedPane1.addKeyListener(new KeyAdapter(){
         public void keyPressed(KeyEvent evt){
             if(evt.isControlDown() && evt.getKeyCode() == KeyEvent.VK_S){
                 System.out.println("Saved called while TAB is focused (not text)!");
            }
         
         }//end keyPressed(KeyEvent)
         
         public void keyReleased(KeyEvent evt){}
         public void keyTyped(KeyEvent evt){
             System.out.println("typed event");
             TextEditor temp=mapTabTE.get(jTabbedPane1.getSelectedComponent());
             if(temp!=null){
                 temp.setIsSaved(false);
                 mapTabTE.put(jTabbedPane1.getSelectedComponent(), temp);
             }
         }
     });//end addKeyLisner
 
    }//end initCodeTextArea()
    /*
     *  Method called to initialize the JTree with the model of the XSLT files
     *  Will eventually add to model the XSLT/XML structure of the file
     */
    private void initFileTreeViewer(){

        fileSystemModel = new FileSystemModel(new File("C:\\"));
        
        jTreeFileSystem.setModel(fileSystemModel);
        jTreeFileSystem.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent event) {
                File file = (File) jTreeFileSystem.getLastSelectedPathComponent();
                jTextPane1.setText(getFileDetails(file));
                if(!file.isDirectory()){//TODO Fix so it doesn't open on any normal file
                    TextEditor tE=new TextEditor(file);
                    arrTextEditors.add(tE);
                    jTabbedPane1.add(file.getName(),tE.getRTextScrollPane());
                    mapTabTE.put(tE.getRTextScrollPane(), tE);
                    FileReaderWriter fileRW= new FileReaderWriter(file);
                    
                    //textArea.setText(fileRW.toString());
                    tE.setText(fileRW.toString());
                    jTabbedPane1.setSelectedIndex(jTabbedPane1.getTabCount()-1);
                    //TODO SHOW THE TAB TO FRONT!!
                }//end if !isDir
            }//end valueChanged()
        });//end treeSelectionListener()
    }//end initFileTreeViewer()

    private String getFileDetails(File file) {
        if (file == null)
            return "";
        StringBuffer buffer = new StringBuffer();
        buffer.append("Name: " + file.getName() + "\n");
        buffer.append("Path: " + file.getPath() + "\n");
        buffer.append("Size: " + file.length() + "\n");
        return buffer.toString();
    }//end getFileDetails
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTreeFileSystem = new javax.swing.JTree();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        btnAndOr = new javax.swing.JToggleButton();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jTextField4 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jTabbedPane1 = new ClosableTabbedPane(){
        		public boolean tabAboutToClose(int tabIndex) {
        			String tab = jTabbedPane1.getTabTitleAt(tabIndex);

        			Component tempC = jTabbedPane1.getSelectedComponent();
        			TextEditor tempTE = mapTabTE.get(tempC);
        			if(tempTE!=null && !tempTE.isSaved()){
            			int choice = JOptionPane.showConfirmDialog(null, 
            					"'" + tab + "' has been modified."+"\nSave changes ?", 
            							"Confirmation Dialog", 
            							JOptionPane.INFORMATION_MESSAGE);
            			if(choice ==0){//save changes
            				tempTE.saveFile();
            			
            			}else if(choice==2){//do not close
            				return false;
            			}
            			return true;
        			}
        			
        			return true;
            // if returned false tab
            // closing will be canceled
        		}
        }	;
        initCodeTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTree2 = new javax.swing.JTree();
        spinnerWorkspace = new javax.swing.JSpinner();
        btnAndOr1 = new javax.swing.JToggleButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Rule Name:");

        jLabel2.setText("Agency:");

        jSpinner1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner1StateChanged(evt);
            }
        });

        jScrollPane2.setViewportView(jTreeFileSystem);

        jLabel3.setText("Form:");

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel4.setText("Conditions:");

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jButton1.setText("Test");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnAndOr.setText("AND");
        btnAndOr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAndOrActionPerformed(evt);
            }
        });

        jLabel5.setText("(Press enter)");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Term Helper")));

        jTextField4.setText("ie. is significant");
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jButton2.setText("mg");

        jLabel9.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel9.setText("is significant: just enter field name");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addGap(0, 113, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jTextPane1);

        jTabbedPane1.addTab("tab1", jScrollPane1);

        jScrollPane3.setViewportView(jTree2);

        btnAndOr1.setText("OR");
        btnAndOr1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAndOr1ActionPerformed(evt);
            }
        });

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                    .addComponent(jScrollPane3)
                    .addComponent(spinnerWorkspace))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnAndOr)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAndOr1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jSpinner1, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jButton1)))
                .addGap(73, 73, 73))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spinnerWorkspace, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnAndOr)
                                    .addComponent(btnAndOr1))
                                .addGap(0, 0, 0)
                                .addComponent(jLabel5)
                                .addGap(10, 10, 10)
                                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jButton1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jScrollPane2)
                                .addGap(10, 10, 10)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
        xsltBuilder.setRulename(jTextField1.getText());
        System.out.println("entered on Rulename");
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//event_jButton1ActionPerformed
        // TODO add your handling code here:

        
    }//event_jButton1ActionPerformed
    
    /*
     *  For when a user enters a query
     */
    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
//activated by pressing enter by default
        // TODO add your handling code here:
        System.out.println("hi there you pressed enter in the query field");
        String inputString = jTextField3.getText();
        
        if (!"".equals(inputString)){ //also test if this is a valid query using parser
            
            if(true){//use this to test for valid querys
            	Component tempC=jTabbedPane1.getSelectedComponent();
                TextEditor tempTE=mapTabTE.get(tempC);
                
                xsltBuilder.addQuery(isAnd,inputString);
                jTextPane1.setText(xsltBuilder.getXSLT());
                textArea.setText(xsltBuilder.getXSLT());
                
                if(tempTE!=null){
                	//XSLTbuilder tempXsltBuilder
                	RSyntaxTextArea tempTA=tempTE.getTextArea();
                	int i=tempTA.getCaretPosition();
                	tempTA.insert(xsltBuilder.getXSLT(), i);
                	System.out.println("enter at cursor pos: "+i);
                }
            	//jTextPane1.setText(inputString);
                
                
            }
            jTextField3.setText("");
        }
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
        xsltBuilder.setAgency(jButton1.getText());
        System.out.println("entered on formname");
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jSpinner1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner1StateChanged
        // TODO add your handling code here:
        xsltBuilder.setAgency(jSpinner1.getValue()+"");
        System.out.println("change state on Spinner1");
    }//GEN-LAST:event_jSpinner1StateChanged

    private void btnAndOrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAndOrActionPerformed
        // TODO add your handling code here:
        isAnd=!isAnd;
        if(isAnd){
            btnAndOr.setText("AND");
        }else{
            btnAndOr.setText("OR");
        }
        System.out.println("PRessed AND/OR");
    }//GEN-LAST:event_btnAndOrActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void btnAndOr1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAndOr1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAndOr1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                System.out.println(info.getName());
                if ("Windows".equals(info.getName()) ) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }//end main()
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnAndOr;
    private javax.swing.JToggleButton btnAndOr1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSpinner jSpinner1;
    private ClosableTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTree jTree2;
    private javax.swing.JTree jTreeFileSystem;
    private javax.swing.JSpinner spinnerWorkspace;
    // End of variables declaration//GEN-END:variables
}
