package org.swing.custom.components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


/*
 * A JPanel modal dialog to set the workspace name and workspace root directory
 * 
 * Is a UI
 */
public class WorkspacePanel extends JPanel {

		public enum FieldTitle{
			NAME("Workspace Name"),ROOTDIR("Workspace root");
			private String title;
			
			private FieldTitle(String title){
				this.title=title;
			}
			
			public String getTitle(){
				return this.title;
			}
		};//end enum
		
		JFrame frame= new JFrame("Create a new Workspace");
		JFileChooser chooser = new JFileChooser();
		private static final Insets WEST_INSETS = new Insets(5, 0, 5, 5);
		private static final Insets EAST_INSETS = new Insets(5, 5, 5, 0);
	   	private Map<FieldTitle, JTextField> fieldMap = new HashMap<FieldTitle, JTextField>();

	   	public WorkspacePanel(){
	   		setLayout(new GridBagLayout());

	        GridBagConstraints gbc;
	        for (int i = 0; i < FieldTitle.values().length; i++) {
	           FieldTitle fieldTitle = FieldTitle.values()[i];
	           gbc = createGbc(0, i);
	           add(new JLabel(fieldTitle.getTitle() + ":", JLabel.LEFT), gbc);
	           gbc = createGbc(1, i);
	           final JTextField textField = new JTextField(30);
	           add(textField, gbc);
	           
	          if(i==1){//FileBrowse
	        	  gbc=createGbc(2,i);
	        	  
	        	   JButton btnFile=new JButton("Browse...");
	        	   
	        	   add(btnFile,gbc);
	        	   chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
             	    
                  
	        	   btnFile.addActionListener(new java.awt.event.ActionListener() {
	                   public void actionPerformed(java.awt.event.ActionEvent evt) {
	                	   frame.setTitle("Create a new Workspace");
	                  	    int returnVal = chooser.showOpenDialog(frame);
	                  	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	                  	       System.out.println("You chose to open this file: " +
	                  	            chooser.getSelectedFile()+"");
	                  	       textField.setText(chooser.getSelectedFile().toString());
	                  	    }//end if
	                  	    
	                   }//end actionPerformed()
	               });
           	 
           	    }//end if i==1
           	  
	           fieldMap.put(fieldTitle, textField);
	        }//end for
	          
	   	}//end constructor()
	   	
	   	public WorkspacePanel(String argName,String argDir){
	   		setLayout(new GridBagLayout());

	        GridBagConstraints gbc;
	        for (int i = 0; i < FieldTitle.values().length; i++) {
	           FieldTitle fieldTitle = FieldTitle.values()[i];
	           gbc = createGbc(0, i);
	           add(new JLabel(fieldTitle.getTitle() + ":", JLabel.LEFT), gbc);
	           gbc = createGbc(1, i);
	           final JTextField textField = new JTextField(30);
	           
	           add(textField, gbc);
	           textField.setText(argName);
	          if(i==1){//FileBrowse
	        	  textField.setText(argDir);
	        	  gbc=createGbc(2,i);
	        	  
	        	   JButton btnFile=new JButton("Browse...");
	        	   
	        	   add(btnFile,gbc);
	        	   chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
             	    
                  
	        	   btnFile.addActionListener(new java.awt.event.ActionListener() {
	                   public void actionPerformed(java.awt.event.ActionEvent evt) {
	                	      
	                	   	frame.setTitle("Edit Workspace");
	                  	    int returnVal = chooser.showOpenDialog(frame);
	                  	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	                  	       System.out.println("You chose to open this file: " +
	                  	            chooser.getSelectedFile()+"");
	                  	       textField.setText(chooser.getSelectedFile().toString());
	                  	    }//end if
	                  	    
	                   }//end actionPerformed()
	               });
           	 
           	    }//end if i==1
           	  
	           fieldMap.put(fieldTitle, textField);
	        }//end for
	          
	   	}//end constructor()
	   	
	   	private GridBagConstraints createGbc(int x, int y) {
	        GridBagConstraints gbc = new GridBagConstraints();
	        gbc.gridx = x;
	        gbc.gridy = y;
	        gbc.gridwidth = 1;
	        gbc.gridheight = 1;

	        gbc.anchor = (x == 0) ? GridBagConstraints.WEST : GridBagConstraints.EAST;
	        gbc.fill = (x == 0) ? GridBagConstraints.BOTH
	              : GridBagConstraints.HORIZONTAL;

	        gbc.insets = (x == 0) ? WEST_INSETS : EAST_INSETS;
	        gbc.weightx = (x == 0) ? 0.1 : 1.0;
	        gbc.weighty = 1.0;
	        return gbc;
	     }

	     public String getFieldText(FieldTitle fieldTitle) {
	        return fieldMap.get(fieldTitle).getText();
	     }

	   	
}//end class WorkspacePanel
