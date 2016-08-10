package methods;

import java.io.Serializable;

public class Config implements Serializable{
	String content, command; //Strings to hold Configs info
	
	public Config( String content, String command ) { //Constructor that takes in content and command arguments 
		this.content = content;
		this.command = command;
	}
	
	//Setter and getter methods
	public void setContent( String c ){ content = c; }
	public void setCommand( String c ){ command = c; }
	
	public String getContent(){ return content; }
	public String getCommand(){ return command; }
}
