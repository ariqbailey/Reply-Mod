package listeners;

import methods.Main;
import net.minecraft.network.INetHandler;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import updater.ReplyUpdater;

public class HypixelChecker {
	boolean check = false;
	
	@SubscribeEvent
	public void onLoginEvent( FMLNetworkEvent.ClientConnectedToServerEvent e ) {
		if( FMLClientHandler.instance().getClient().getCurrentServerData().serverIP.contains(".hypixel.net" ) ){ 
			Main.hypixel = true;
			if( !this.check ){
				new Thread( new ReplyUpdater() ).start();
				this.check = true;
			}
		}
		else{
			Main.hypixel = false;
		}
	}
	
	@SubscribeEvent
	public void onLogoutEvent( FMLNetworkEvent.ClientDisconnectionFromServerEvent e ){
		Main.hypixel = false;
	}
}
