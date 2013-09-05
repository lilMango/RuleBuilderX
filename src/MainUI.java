/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.intuit.cg.lang.simplexslt.*;
import com.intuit.cg.tools.filesystem.*;
import com.intuit.cg.tools.rules.utils.TextEditor;
import com.intuit.cg.tools.rules.utils.XsltBuilder;
import com.sun.javafx.sg.PGShape.Mode;
//import com.intuit.fsapp.Validator;
import java.awt.Component;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerModel;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.tree.TreeModel;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.ScrollPane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.fife.ui.autocomplete.*;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;
import org.swing.custom.components.ClosableTabbedPane;
import org.swing.custom.components.WorkspacePanel;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;



/**
 *
 * @author mpaysan
 */
public class MainUI extends JFrame {
    KeyboardFocusManager keyManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
     
    private RSyntaxTextArea textArea;
    private RTextScrollPane rScrollPane;
    private FileSystemModel fileSystemModel;
    
    private ArrayList<TextEditor> arrTextEditors;
    private boolean isAnd=true;
    private boolean isSavingFlag=false;
    private XsltBuilder xsltBuilder;
    private HashMap<Component,TextEditor> mapTabTE;
    
    private SimpleXsltCompiler simpleXsltCompiler;
    private ConfigFiler configFiler= new ConfigFiler();
    
    String[] agencyArr={"AK","AL","AR","AS","AZ","CA","CO","CT","DC","DE","FL","GA","GU","HI","IA","ID",
			"IL","IN","KS","KY","LA","MA","MD","ME","MH","MI","MN","MO","MS","MT","NC","ND","NE","NH","NJ","NM","NV","NY",
			"OH","OK","OR","PA","PR","PW","RI","SC","SD","TN","TX","UT","VA","VI","VT","WA","WI","WV","WY"};
    /**
     * Creates new form NewJFrame
     */
    public MainUI() {
        keyManager.addKeyEventDispatcher(new MyDispatcher()); // so ctrl-s is application wide
      
        configFiler.readConfig();

        initComponents();
        initFileTreeViewer();
        //initCodeTextArea();//jTabbedPane1's post-creation code
        xsltBuilder = new XsltBuilder();
        arrTextEditors = new ArrayList<TextEditor>();
        mapTabTE = new HashMap<Component,TextEditor>();
        
        String[] args={"-gui","-tree"};
        try {
			simpleXsltCompiler= new SimpleXsltCompiler(args);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
    }//end constructor()

    
    /* Application Wide Key Listener
     * Is usually ran last compared to all listeners
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

                    if(tempTE!=null){
                        if(!tempTE.isSaved()){
                            tempTE.saveFile();
                            mapTabTE.put(tempC, tempTE);
                            jTabbedPane1.setTitleAt(tempI,tempTE.getName());
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
            	updateEditedFileTitle();
            	String tmp="   <xsl:if test=\"a < b\">\n"+
            				"\t<Error>\n"+
            				"\t\t<xsl:attribute name=\"errorCode\">faf</xsl:attribute>\n"+
            				"\t\t<xsl:attribute name=\"type\">rejectToCustomer</xsl:attribute>\n"+
            				"\t\t<xsl:attribute name=\"RejectingAgency\">AK</xsl:attribute>\n"+
            				"\t</Error>\n"+
            				"  </xsl:if>"+
            				
            				"   <xsl:if test=\"a < b\">\n"+
            				"\t<Error>\n"+
            				"\t\t<xsl:attribute name=\"errorCode\">faf</xsl:attribute>\n"+
            				"\t\t<xsl:attribute name=\"type\">rejectToCustomer</xsl:attribute>\n"+
            				"\t\t<xsl:attribute name=\"RejectingAgency\">AK</xsl:attribute>\n"+
            				"\t</Error>\n"+
            				"  </xsl:if>"+
            				
							"   <xsl:if test=\"a <= b < 3434\">\n"+
							"\t<Error>\n"+
							"\t\t<xsl:attribute name=\"errorCode\">faf</xsl:attribute>\n"+
							"\t\t<xsl:attribute name=\"type\">rejectToCustomer</xsl:attribute>\n"+
							"\t\t<xsl:attribute name=\"RejectingAgency\">AK</xsl:attribute>\n"+
							"\t</Error>\n"+
							"  </xsl:if>"
            			;
            	
            	XsltEncoder.encode(tmp);
               
            	
            }
            return false;
        }//end dispatchKeyEvent()

    }//end class MyDispatcher
    
    /*
     * sets up the state agency spinner
     */	

    /*
     *  Used to initialize the Syntax highlighted Code text area
     */
    private void initCodeTextArea(){
      textArea = new RSyntaxTextArea(60, 60);
      //textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_XML);
      textArea.setCodeFoldingEnabled(true);
      textArea.setAntiAliasingEnabled(true);
      rScrollPane = new RTextScrollPane(textArea);
      

      textArea.setText("Welcome to the XSLT Rule Builder!\n\n"+
    		  			"A Bizarre Syntax Production.");
      jTabbedPane1.addTab("Welcome", rScrollPane);  

      jTabbedPane1.addChangeListener(new ChangeListener(){
    	  public void stateChanged(ChangeEvent e){
    		  System.out.println("Changed tabs to tab:"+jTabbedPane1.getSelectedIndex());
    	  }
      });//end addChangeListener()
      
    }//end initCodeTextArea()
    /*
     *  Method called to initialize the JTree with the model of the XSLT files
     *  Will eventually add to model the XSLT/XML structure of the file
     */
    private void initFileTreeViewer(){
    	
    	Vector<Workspace> workspaces=configFiler.getWorkspaces();
    	if(workspaces.size()>2){
    		fileSystemModel =  new FileSystemModel(new File(workspaces.get(0).getDir()));
    	}else{
    		//fileSystemModel = new FileSystemModel(new File("C:\\"));
    	}
        jTreeFileSystem.setModel(fileSystemModel);
        jTreeFileSystem.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent event) {
                File file = (File) jTreeFileSystem.getLastSelectedPathComponent();
                
                if(file!=null&&!file.isDirectory()){//TODO Fix so it doesn't open on any normal file
                    TextEditor tE=new TextEditor(file);
                    arrTextEditors.add(tE);
                    jTabbedPane1.add(file.getName(),tE.getRTextScrollPane());
                    mapTabTE.put(tE.getRTextScrollPane(), tE);
                    FileReaderWriter fileRW= new FileReaderWriter(file);
                    
                    //textArea.setText(fileRW.toString());
                    tE.setText(fileRW.toString());
                    jTabbedPane1.setSelectedIndex(jTabbedPane1.getTabCount()-1);
                    
                }//end if !isDir
            }//end valueChanged()
        });//end treeSelectionListener()
    }//end initFileTreeViewer()

    private void initWorkspace(){
    	
        comboBoxWorkspace.setModel(new DefaultComboBoxModel(configFiler.getWorkspaces()));
	    comboBoxWorkspace.addActionListener(new java.awt.event.ActionListener() {
	         public void actionPerformed(java.awt.event.ActionEvent evt) {
	         	JComboBox cb=(JComboBox)evt.getSource();
	         	String wkName=cb.getSelectedItem()+"";
	         	String rootDir=((Workspace) cb.getSelectedItem()).getDir()+"";
	             System.out.println("Selected a workspace: "+wkName+"=>"+rootDir);
	             
	             if("New Workspace...".equals(wkName)){
	            	 
	            	 	WorkspacePanel workspacePanel=new WorkspacePanel();
	            	 	 
	            	 	int result = JOptionPane.showConfirmDialog(null, workspacePanel,
	            	 		    "Create a Workspace", JOptionPane.OK_CANCEL_OPTION,
	            	 		    JOptionPane.PLAIN_MESSAGE);
	            	 
	            	 	if (result == JOptionPane.OK_OPTION) {
	                        /*
	            	 		for (WorkspacePanel.FieldTitle fieldTitle : WorkspacePanel.FieldTitle
	                              .values()) {
	                           System.out.println("\t"+fieldTitle+":  "+workspacePanel.getFieldText(fieldTitle));
	                           
	                        }
	                        */
	                        WorkspacePanel.FieldTitle name = WorkspacePanel.FieldTitle.NAME;
	                        WorkspacePanel.FieldTitle dir = WorkspacePanel.FieldTitle.ROOTDIR;
	                        configFiler.addWorkspace(workspacePanel.getFieldText(name),workspacePanel.getFieldText(dir));
	                        configFiler.writeConfig();
	                        comboBoxWorkspace.setModel(new DefaultComboBoxModel(configFiler.getWorkspaces()));
	                        comboBoxWorkspace.setSelectedIndex(comboBoxWorkspace.getItemCount()-3);
	                     
	                        File file=new File(workspacePanel.getFieldText(dir));
	    	                fileSystemModel = new FileSystemModel(file);
	   	                    jTreeFileSystem.setModel(fileSystemModel);
	   	                    //TODO fix adding workspaces
	                     }
	            	    
	            	    
	             }else if("Select Workspace...".equals(wkName)){
	            	 	//TODO Create a menu for editing and deleting workspace
	             }else{
	            	 File file=new File(rootDir);
	             	
	                 fileSystemModel = new FileSystemModel(file);
	                 
	                 jTreeFileSystem.setModel(fileSystemModel);
	            
	             }
	         }
	     });
	    
    }//end initWorkspace()
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
   
    private void initComponents() {

    	splitPaneLeftRight = new JSplitPane();
    	
    	splitPaneLeft = new JSplitPane();
    	 jPanel1= new JPanel();
         jTreeFileSystem = new JTree();
		 jTreeXsltRules = new javax.swing.JTree();
	     comboBoxWorkspace = new JComboBox();
	    
		splitPaneRight = new JSplitPane();
		paneRight = new JPanel();
	    splitPaneEditor = new JSplitPane();
	    paneQueryBar = new JPanel();
        txtRuleName = new javax.swing.JTextField();
        lblRuleName = new javax.swing.JLabel();
        lblAgency = new javax.swing.JLabel();
        spinnerAgency = new javax.swing.JSpinner();
        lblForm = new javax.swing.JLabel();
        txtFormName = new javax.swing.JTextField();
        lblConditions = new javax.swing.JLabel();
        txtQueryBar = new RSyntaxTextArea();
        btnTest = new javax.swing.JButton();
        lblPressEnter = new javax.swing.JLabel();
        paneEditor = new JPanel();
        btnRule= new JButton();
        
        radBtnAnd = new JRadioButton("And");
        radBtnOr = new JRadioButton("Or");
        buttonGroup = new ButtonGroup();
       
        jScrollPane2 = new javax.swing.JScrollPane();
        paneTermHelper = new javax.swing.JPanel();
        txtTermHelper = new javax.swing.JTextField();
        btnTermHelper = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
 
        jScrollPane1 = new javax.swing.JScrollPane();
        //jTextPane1 = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
       
        
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        txtQueryBar.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_NONE);
        txtQueryBar.setCodeFoldingEnabled(false);
        txtQueryBar.setAntiAliasingEnabled(true);
        
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
    

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);


        spinnerAgency.setModel(new SpinnerListModel(agencyArr));
        
        initWorkspace();
	     
	     javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
	        jPanel1.setLayout(jPanel1Layout);
	        jPanel1Layout.setHorizontalGroup(
	            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addGap(0, 0, Short.MAX_VALUE))
	            .addComponent(comboBoxWorkspace, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	        );
	        jPanel1Layout.setVerticalGroup(
	            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
	                .addComponent(comboBoxWorkspace, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE))
	    );
	    
        splitPaneLeft.setTopComponent(jPanel1);

        jScrollPane3.setViewportView(jTreeXsltRules);

        splitPaneLeft.setRightComponent(jScrollPane3);

        splitPaneLeftRight.setLeftComponent(splitPaneLeft);

        splitPaneRight.setDividerLocation(800);
        splitPaneRight.setDividerSize(10);
        splitPaneRight.setOneTouchExpandable(true);

        paneTermHelper.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Term Helper")));

        
        splitPaneLeftRight.setDividerSize(10);
        splitPaneLeftRight.setOneTouchExpandable(true);

        splitPaneLeft.setDividerLocation(4000);//400
        splitPaneLeft.setDividerSize(10);
        splitPaneLeft.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        splitPaneLeft.setOneTouchExpandable(true);


        jScrollPane2.setViewportView(jTreeFileSystem);

     

        paneTermHelper.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Term Helper")));

        txtTermHelper.setText("ie. is significant");
        txtTermHelper.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
            }
        });

        btnTermHelper.setText("search");

        jLabel9.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel9.setText("is significant: just type in a field name");

        javax.swing.GroupLayout paneTermHelperLayout = new javax.swing.GroupLayout(paneTermHelper);
        paneTermHelper.setLayout(paneTermHelperLayout);
        paneTermHelperLayout.setHorizontalGroup(
            paneTermHelperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneTermHelperLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneTermHelperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paneTermHelperLayout.createSequentialGroup()
                        .addComponent(txtTermHelper, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addComponent(btnTermHelper, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(paneTermHelperLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        paneTermHelperLayout.setVerticalGroup(
            paneTermHelperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneTermHelperLayout.createSequentialGroup()
                .addGroup(paneTermHelperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnTermHelper)
                    .addComponent(txtTermHelper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addGap(0, 113, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout paneRightLayout = new javax.swing.GroupLayout(paneRight);
        paneRight.setLayout(paneRightLayout);
        paneRightLayout.setHorizontalGroup(
                paneRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(paneRightLayout.createSequentialGroup()
                    .addComponent(paneTermHelper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 150, Short.MAX_VALUE))
                .addGroup(paneRightLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(btnTest)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            paneRightLayout.setVerticalGroup(
                paneRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(paneRightLayout.createSequentialGroup()
                    .addComponent(paneTermHelper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(42, 42, 42)
                    .addComponent(btnTest)
                    .addContainerGap(519, Short.MAX_VALUE))
            );

        splitPaneRight.setRightComponent(paneRight);
        splitPaneRight.setResizeWeight(1.0);
        splitPaneEditor.setDividerSize(7);
        splitPaneEditor.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        splitPaneEditor.setOneTouchExpandable(true);
        
/////////////////////////////////////////////////////// Middle ///////////////////////
       
        javax.swing.GroupLayout paneQueryBarLayout = new javax.swing.GroupLayout(paneQueryBar);
        paneQueryBar.setLayout(paneQueryBarLayout);
        paneQueryBarLayout.setHorizontalGroup(
            paneQueryBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneQueryBarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneQueryBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paneQueryBarLayout.createSequentialGroup()
                        .addComponent(lblRuleName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRuleName, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblAgency)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spinnerAgency, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblForm)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFormName, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 447, Short.MAX_VALUE))
                    .addGroup(paneQueryBarLayout.createSequentialGroup()
                        .addComponent(lblConditions)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radBtnAnd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radBtnOr)
                        .addGap(18, 18, 18)
                        .addGroup(paneQueryBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(paneQueryBarLayout.createSequentialGroup()
                                .addComponent(lblPressEnter)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnRule))
                            .addComponent(txtQueryBar))))
                .addContainerGap())
        );
        paneQueryBarLayout.setVerticalGroup(
            paneQueryBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneQueryBarLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(paneQueryBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRuleName)
                    .addComponent(txtRuleName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAgency)
                    .addComponent(spinnerAgency, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblForm)
                    .addComponent(txtFormName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(paneQueryBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblConditions)
                    .addComponent(radBtnAnd)
                    .addComponent(radBtnOr)
                    .addComponent(txtQueryBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(paneQueryBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPressEnter)
                    .addComponent(btnRule))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        splitPaneEditor.setTopComponent(paneQueryBar);
        
        javax.swing.GroupLayout paneEditorLayout = new javax.swing.GroupLayout(paneEditor);
        paneEditor.setLayout(paneEditorLayout);
        paneEditorLayout.setHorizontalGroup(
            paneEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 797, Short.MAX_VALUE)
        );
        paneEditorLayout.setVerticalGroup(
            paneEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE)
        );

        splitPaneEditor.setRightComponent(paneEditor);

        splitPaneRight.setLeftComponent(splitPaneEditor);

        splitPaneLeftRight.setRightComponent(splitPaneRight);


        //jScrollPane1.setViewportView(jTextPane1);

        //jTabbedPane1.addTab("Welcome", jScrollPane1);

        jScrollPane3.setViewportView(jTreeXsltRules);

        
        txtRuleName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	if(!"".equals(txtRuleName.getText()))
            		updateRuleName(true);
            }
        });
        txtRuleName.addKeyListener(new KeyAdapter(){
           public void keyTyped(KeyEvent evt){
        	  //updateRuleName(false);
            }
           
           public void keyReleased(KeyEvent evt){
        	   if(!"".equals(txtRuleName.getText()))        		   updateRuleName(false);
           }
         });//end addKeyListener

        initCodeTextArea();
        
        lblRuleName.setText("Rule Name:");
        btnRule.setText("Write");
        lblAgency.setText("Agency:");

        spinnerAgency.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                //updateRuleName(false);
            }
        });

        radBtnAnd.setSelected(true);
        buttonGroup.add(radBtnAnd);
        buttonGroup.add(radBtnOr);
        
        radBtnOr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                isAnd=false;
            }
        });
        
        lblForm.setText("Form:");

        txtFormName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        lblConditions.setText("Conditions:");

        btnRule.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent evt){
        		txtQueryBarActionPerformed(evt);
        	}
        });
        
        txtQueryBar.setRows(3);
        txtQueryBar.setColumns(50);
        txtQueryBar.setWrapStyleWord(true);
        txtQueryBar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        //txtQueryBar;
        txtQueryBar.getDocument().addDocumentListener(new DocumentListener(){

			public void changedUpdate(DocumentEvent arg0) {
				
				
			}
			
			//autoComplete
			public void insertUpdate(DocumentEvent arg0) {

			}

			public void removeUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				
			}
        });
        
        /*
        txtQueryBar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        */
        btnTest.setText("Test");
        btnTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //jButton1ActionPerformed(evt);
            	//Validator.validate();
            }
        });

     
        radBtnAnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                isAnd=true;
            }
        });

        lblPressEnter.setText(" ");

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(splitPaneLeftRight)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(splitPaneLeftRight)
        );
     
        pack();
    }


    /*
     * Changes rule name from txt Rule Name
     */
    private void updateRuleName(boolean reset){

        String inputString = txtRuleName.getText();
    	
        xsltBuilder.setRulename(inputString);
        System.out.println("entered on Rulename: " + inputString);
        
    	Component tempC=jTabbedPane1.getSelectedComponent();
        TextEditor tempTE=mapTabTE.get(tempC);
        
        
        if(tempTE!=null){
        	tempTE.updateRuleProps(inputString,spinnerAgency.getValue()+"");
        	tempTE.setIsSaved(false);
        }//end tempTE!=null
      
        updateEditedFileTitle();
        if(reset)
        	txtRuleName.setText("");
    }//end updateRuleName()
    
    
    /*
     *  For when a user enters a query. query entering. enter a query
     */
    private void txtQueryBarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
//activated by pressing enter by default
         System.out.println("hi there you pressed enter in the query field");
        String inputString = txtQueryBar.getText();
        
        if (!"".equals(inputString)){ 
        	String xsltString="";
        	try {
				//simpleXsltCompiler.processString(inputString);//"{reddfe-fve540}-{hi}+{/4342-yoammoma/CA-Return540}");
				xsltString=simpleXsltCompiler.translateToXslt(inputString);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("There was an error=======");
				xsltString="";
			}
        	//System.out.println(xsltString);
        	//System.out.println("null=="+null);
        	//System.out.println(xsltString.length());
            //System.out.println("IsNUll? "+(xsltString instanceof Object));//xsltString.equals("null"));
            if( !"null".equals(xsltString) && xsltString!=null && !"".equals(xsltString)){//use this to test for valid querys
            	Component tempC=jTabbedPane1.getSelectedComponent();
                TextEditor tempTE=mapTabTE.get(tempC);
                
                //xsltBuilder.addQuery(isAnd,xsltString);
                //jTextPane1.setText(xsltBuilder.getXSLT());
                //textArea.setText(xsltBuilder.getXSLT());
                
                if(tempTE!=null){
                	tempTE.appendRule(isAnd,xsltString,txtRuleName.getText(),spinnerAgency.getValue()+"",txtFormName.getText());
                	tempTE.setIsSaved(false);
                	txtRuleName.setText("");
                	txtFormName.setText("");
                }//end tempTE!=null
            	//jTextPane1.setText(xsltString);
                
                updateEditedFileTitle();
                
            }
            txtQueryBar.setText("");
        }else if(!"".equals(txtRuleName.getText())){
        	Component tempC=jTabbedPane1.getSelectedComponent();
            TextEditor tempTE=mapTabTE.get(tempC);
            

            if(tempTE!=null){
            	tempTE.appendRule(isAnd,"",txtRuleName.getText(),spinnerAgency.getValue()+"",txtFormName.getText());
            	tempTE.setIsSaved(false);
            	txtRuleName.setText("");
            	txtFormName.setText("");
            }//end tempTE!=null
        	//jTextPane1.setText(xsltString);
        }
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {
        
        xsltBuilder.setAgency(btnTest.getText());
        System.out.println("entered on formname");
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jSpinner1StateChanged(javax.swing.event.ChangeEvent evt) {
        xsltBuilder.setAgency(spinnerAgency.getValue()+"");
        System.out.println("change state on Spinner1");
    }//GEN-LAST:event_jSpinner1StateChanged




    /*
     * Used to mark with '*' when a document is not saved/edited in the title of file
     */
    private void updateEditedFileTitle(){
    	System.out.println("zomh");
    	
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
    }//end updateChangedText()
    
    
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
                //System.out.println(info.getName());
                if ("Windows".equals(info.getName()) ) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            	MainUI mainFrame=new MainUI();
            	mainFrame.setVisible(true);
     
            }//end run()
        });
    }//end main()

    private JRadioButton radBtnAnd;
    private JRadioButton radBtnOr;
    private ButtonGroup buttonGroup;
    private JButton btnTest;
    private JButton btnTermHelper;
    private JLabel lblRuleName;
    private JLabel lblAgency;
    private JLabel lblForm;
    private JLabel lblConditions;
    private JLabel lblPressEnter;
    private JLabel jLabel9;
    private JTextField txtRuleName;
    private JTextField txtFormName;
    private JButton btnRule;
    private RSyntaxTextArea txtQueryBar;//TODO convert to text Area. Action Listeners. 
    							   //Automatically put test text inside text Area. 
    							   //If can't parse just re-emit the same text converted. 
    								//But try and find a way to reverse compile
    private JSpinner spinnerAgency;
    private JMenu jMenu1;
    private JMenu jMenu2;
    private JMenuBar jMenuBar1;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JScrollPane jScrollPane3;

    private ClosableTabbedPane jTabbedPane1;

    private JTextField txtTermHelper;
    private JTextPane jTextPane1;
    private JTree jTreeXsltRules;
    private JTree jTreeFileSystem;
    private JComboBox comboBoxWorkspace;
    
    private javax.swing.JPanel jPanel1;//For the JTRee?
    private JPanel paneEditor;
    private JPanel paneQueryBar;
    private JPanel paneRight;
    private JPanel paneTermHelper;
    private JSplitPane splitPaneEditor;
    private JSplitPane splitPaneLeft;
    private JSplitPane splitPaneLeftRight;
    private JSplitPane splitPaneRight;
}
