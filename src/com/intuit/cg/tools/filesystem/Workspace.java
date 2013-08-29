package com.intuit.cg.tools.filesystem;

/*
 * Will save and load workspaces from a txt file with JSON format
 */
public class Workspace {
	private String name;
	private String dir;
	
	//give a name and a parent directory name
	public Workspace(String name, String dir){
		this.name=name;
		this.dir=dir;
	}//end Constructor(String,String)
	
	public String toString(){
		return this.name;
	}//end toString()
	
	public String getName(){
		return toString();
	}//end getName()
	
	public String getDir(){
		return this.dir;
	}//end 
}//end class Workspace
