package com.intuit.cg.tools.filesystem;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/*
 * This class loads data from a config file (*.json)
 */
public class ConfigFiler {
	private static String CONFIG_FILE=System.getProperty("user.dir").toString();//"C:\\0_TestDir\\test.json";
	private Vector<Workspace> workspaces=new Vector<Workspace>();
	private Workspace newWorkspace=new Workspace("New Workspace...",null);
	private Workspace selectWorkspace =  new Workspace("Select Workspace...",null);
	
	public ConfigFiler(){
		
	}//end constructor()
	
	public void addWorkspace(String name, String root){
		workspaces.addElement(new Workspace(name,root));
	}//end addWorkspace(String,String)
	
	public void editWorkspace(String name,String root){
		
	}//end editWorkspace(String,String)
	
	public void dropWorkspace(String name){
		workspaces.remove(name);
	}//end dropWorkspace(String)
	
	public void writeConfig(){
		JSONObject obj = new JSONObject();
		obj.put("name","miguel");
		obj.put("age",new Integer(22)+"");
		
		JSONObject jsonWorkspaces=new JSONObject();
		
		for(Workspace w:workspaces){
			jsonWorkspaces.put(w.getName(), w.getDir());
		}
	
	
		obj.put("workspaces", jsonWorkspaces);
	
		try{
			FileWriter file = new FileWriter(CONFIG_FILE);
			file.write(obj.toJSONString());
			file.flush();
			file.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		System.out.println(obj);
	}//end writeConfig()
	
	public void readConfig(){
		JSONParser parser = new JSONParser();
		ContainerFactory containerFactory = new ContainerFactory(){
		    public List creatArrayContainer() {
		      return new LinkedList();
		    }

		    public Map createObjectContainer() {
		      return new LinkedHashMap();
		    }
		                        
		  };
		try {
			Map json =(Map) parser.parse(new FileReader(CONFIG_FILE),containerFactory);
			Iterator iter = json.entrySet().iterator();
			System.out.println("==iterate result==");
			while(iter.hasNext()){
			      Map.Entry entry = (Map.Entry)iter.next();
			      System.out.println(entry.getKey() + "=>" + entry.getValue());
			      if("workspaces".equals(entry.getKey())){
			    	  System.out.println("Workspaces: ");
			    	  Map mW=(Map)entry.getValue();
			    	  Iterator iterMW=mW.entrySet().iterator();
			    	  while(iterMW.hasNext()){
			    		  Map.Entry wk=(Map.Entry)iterMW.next();
			    		  System.out.println(wk.getKey()+"=>"+wk.getValue());
			    		  workspaces.addElement(new Workspace(wk.getKey()+"",wk.getValue()+""));
			    	  }//end while iterMW
			      }
			}//end while iter

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}//end try-catch
	 
	}//end readConfig()
	
	public Vector<Workspace> getWorkspaces(){
		Vector<Workspace> workspaceTemp=(Vector<Workspace>)this.workspaces.clone();
		workspaceTemp.add(selectWorkspace);
		workspaceTemp.add(newWorkspace);
		
		return workspaceTemp;
		
	}//end getWorkspaces()
	
	
}//end class ConfigFile
