package methods;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

public class ConfigHandler implements ConfigReader, ConfigWriter, Serializable{
	File f = new File( "config/ReplyMod.dat" ); //New File object for storing custom messages
	ObjectInputStream in = null; //Create a new ObjectInputStream object
	ObjectOutputStream out = null; //Create an ObjectOutputStream object
	
	public TreeMap<String, Config> readConfig(){ //Overriding readConfig from ConfigReader
		TreeMap<String, Config> config = new TreeMap<String, Config>(); //Create a TreeMap to hold the Config objects
		
		try{
			//Setup in with object wrapping
			in = new ObjectInputStream( new BufferedInputStream( new FileInputStream( f ) ) );
			
			while( true ){ //Code will run until EOFException, and then config will be returned;
				Config foo = ( Config ) in.readObject(); //Store Config object in foo
				config.put( foo.getCommand(), foo ); //Put the Config object in the TreeMap w/ the command as its key
			}
		}
		catch( EOFException e ){
			return config; //Once end of file is reached, return contact
		}
		catch( Exception e ){
			System.out.println( "Error occured in reading from config" + e.toString() );
			e.printStackTrace();
		}
		return config;
	}

	public void writeConfig( TreeMap<String, Config> configMap ) { //Overriding writeConfig from ConfigWriter
		f.delete();
		
		try{
			//Setup out with object wrapping
			out = new ObjectOutputStream( new BufferedOutputStream( new FileOutputStream( f ) ) );
			
			for( Map.Entry<String, Config> c : configMap.entrySet() ){ //Loop through the values in the TreeMap of type Config
				out.writeObject(c.getValue()); //Write each Config object to the file.
			}
			
			out.flush(); //Flush outs buffer and close it
			out.close();
		}
		catch( Exception e){
			System.out.println( "Error occured in writing to config: " + e.toString() );
			e.printStackTrace();
		}
	}
	
}
