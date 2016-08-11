package methods;

import java.io.Serializable;
import java.util.TreeMap;

import commands.ReplyModCommand;
import listeners.ClientChatReceievedEventHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommand;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod( modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, clientSideOnly = true ) //Sets mod info to constants from Reference class
public class Main implements Serializable{
	public static ConfigHandler c = new ConfigHandler(); //Create a ConfigHandler object
	public static TreeMap<String, Config> config; //Create a TreeMap to store the Config objects with the custom messages
	//public static Minecraft mc = Minecraft.; //Create a Minecraft object
	public static boolean modOn = false; //Tells if a command is currently active
	public static String currentCommand, currentMessage;
	public static Minecraft mc = Minecraft.getMinecraft();
	public static boolean checkUpdate = false;
	public static boolean hypixel = false;
	
	@EventHandler
	public void preInit( FMLPreInitializationEvent event ){
		if( c.f.exists() ){ //If the config file already exists, run this code
			config = c.readConfig(); //Set config by reading Config objects from file.
		}
		else{ //If the config file does not already exist, run this code.
			config = new TreeMap<String, Config>(); //Set config as a new TreeMap
		}
	}
	
	@EventHandler
	public void init( FMLInitializationEvent event ){
		registerEventListeners();
	}
	
	@EventHandler
	public void postInit( FMLPostInitializationEvent event ){
		
	}
	
	public void registerEventListeners(){ //method to register event listeners
		System.out.println("Registering event listeners");
		
		//Register the event listners with the classes that handle the event listening for each particular listener
		MinecraftForge.EVENT_BUS.register( new ClientChatReceievedEventHandler() );
		ClientCommandHandler.instance.registerCommand( new ReplyModCommand() );
		//MinecraftForge.EVENT_BUS.register( new CommandEventHandler( config ) );
	}
}
