package commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import methods.Config;
import methods.ConfigHandler;
import methods.Main;
import methods.PrintMessageReplyMod;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.EnumChatFormatting;

public class ReplyModCommand extends CommandBase{
	
	public int getRequiredPermissionLevel(){
		return 0;
	}
	
	public boolean canSenderUseCommand( ICommandSender sender ){
		return true;
	}
	
	public String getCommandUsage( ICommandSender sender ){
		return "Usage: /replymod <add [Command Name] [Message], delete [Command Name], list>";
	}
	
	public List getAliases(){
		List<String> aliases = new ArrayList<String>();
		aliases.add( "ReplyMod" );
		aliases.add( "replymod" );
		return aliases;
	}

	@Override
	public String getCommandName() {
		return "replymod";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		// TODO Auto-generated method stub
		try{
			if( args.length == 0){
				//Print out the available commands
				PrintMessageReplyMod.showRedMessage( "Usage: /replymod <add [Command Name] [Message], delete [Command Name], list>" );
			}
			else if ( args.length >= 1 ){
				if( args[0].equalsIgnoreCase( "help" ) ){ //Run the help command
					//Print out the available commands
					PrintMessageReplyMod.showRedMessage("Usage: /replymod <add [Command Name] [Message], delete [Command Name], list>" );
				}
				else if( args[0].equalsIgnoreCase( "list" ) ){ //Run the list command
					//List out the available custom messages
					PrintMessageReplyMod.line();
					//PrintMessageReplyMod.showFormattedMessage( "Avaliable Custom Messages", EnumChatFormatting.DARK_GREEN );
					
					for( Map.Entry<String, Config> c : Main.config.entrySet() ){ //Loop through command list TreeMap
						//Prints out current command of Config object in the TreeMap
						PrintMessageReplyMod.showYellowMsg( "/replymod ", c.getValue().getCommand() );
					}
					
					PrintMessageReplyMod.line();
				}
				else if( args[0].equalsIgnoreCase( "add" ) ){ //If the user wants to add a new custom command, run this code
					//Attempt to add a slot in the TreeArray with the new command name, and message.
					
					try{
						String foo = ""; 
						for( int i = 2; i < args.length; i++ ){ //Add any of the args containing the message to foo
							foo += args[i] + " ";
						}
						Main.config.put( args[1].toLowerCase(), new Config( foo, args[1].toLowerCase() ) );
						PrintMessageReplyMod.showMsg( "Command " + args[1].toLowerCase() + " added" );
					}
					catch( Exception e ){ //In case the user does not fill out args[1] and args[2]
						PrintMessageReplyMod.showRedMessage( "Command not recognized/mistyped" );
					}
					
					Main.c.writeConfig( Main.config ); //Write the new TreeMap to the config file
				}
				else if( args[0].equalsIgnoreCase( "delete" ) ){ //If the user wants to delete a custom command, run this code.
					//Attempt to delete the custom message from the TreeMap
					try{
						Main.config.remove( args[1].toLowerCase() ); //Remove the Config object from the TreeMap
						PrintMessageReplyMod.showMsg( "Command " + args[1] + " deleted" );
					}
					catch( Exception e ){ //In case the user does not fill out args[1]
						PrintMessageReplyMod.showRedMessage( "Command not recognized/mistyped" );
					}
					
					Main.c.writeConfig( Main.config );
				}
				else if( args[0] != null ){ //If there is a parameter, but it's none of the above, see if its a custom message
					//Attempt to turn on the custom message
					try{
						if( Main.modOn == true && args[0].toLowerCase().equals( Main.currentCommand )){ //If a custom message is already enabled, and it's the same message as the one entered in args, run this code.
							Main.modOn = false; //Turn off auto reply command
							PrintMessageReplyMod.showMsg( Main.currentCommand + "[OFF]" ); //Say the message is off
						}
						else{ //Otherwise, run this code.
							Main.currentMessage = "/r " + Main.config.get( args[0].toLowerCase() ).getContent(); //Set the currentMessage to /r + the message associated with the command.
							Main.currentCommand = args[0].toLowerCase(); //Set the current command to the user input
							Main.modOn = true; //Turn on auto reply command
							PrintMessageReplyMod.showMsg( Main.currentCommand + "[ON]" ); //Say the message is on
						}
					}
					catch( Exception e ){ //If it's not a custom message, then it's none of the available commands at this point
						PrintMessageReplyMod.showRedMessage( "Command not recognized/mistyped" );
					}
				}
			}
		}
		catch( Exception e ){
			e.printStackTrace();
		}
	}
}
