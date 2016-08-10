package listeners;

import methods.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClientChatReceievedEventHandler { //Class for Event Handler for ClientChatReceievedEvent
	
	
	@SubscribeEvent
	
	public void onEvent( ClientChatReceivedEvent event ){ //Listener for ClientChatReceivedEvent
		String msg = event.message.getUnformattedText(); //Store the receieved message to a string
		
		if( Main.modOn ){			
			if( msg.startsWith( "From " ) ){ //If the string begins with From and the mod is on reply with the current message
				Minecraft.getMinecraft().thePlayer.sendChatMessage( Main.currentMessage );
			}
		}
		
		
		//if( Minecraft.getMinecraft().thePlayer.getUniqueID().equals( UUID.fromString("9f6851f3-e12f-471a-b9cb-256d15f4ad97") ) ){
		//	System.out.println("MindMC is cool");
		//}
	}
}
