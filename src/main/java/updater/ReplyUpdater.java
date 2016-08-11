package updater;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import methods.Main;
import methods.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

public class ReplyUpdater implements Runnable{
	
	private static HttpURLConnection createConnection( String s ) throws Exception {
		URL url = new URL(s);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setConnectTimeout(5000);
		connection.setReadTimeout(5000);
		connection.setUseCaches(true);
		connection.addRequestProperty("User-Agent", "Mozilla/4,76");
		connection.setDoOutput(true);
		return connection;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if( !Main.checkUpdate ){
			String url = "https://raw.githubusercontent.com/MindMC/Reply-Mod/master/version/version.txt";
			String content = "";
			
			try{
				Thread.sleep(5000);
				HttpURLConnection con = createConnection( url );
				
				BufferedReader br = new BufferedReader( new InputStreamReader( con.getInputStream() ) );
				
				String input;
				while( ( input = br.readLine() ) != null ){
					content += input;
				}
				br.close();
			}
			catch( Exception e ){
				e.printStackTrace();
			}
			
			if( ( content.equals(Reference.VERSION) ) == false ){
				ClickEvent versionCheckChatClickEvent = new ClickEvent(ClickEvent.Action.OPEN_URL, "http://www.skywars.info/autotip");
                ChatStyle clickableChatStyle = new ChatStyle().setChatClickEvent(versionCheckChatClickEvent);
                ChatComponentText versionWarningChatComponent = new ChatComponentText((Object)EnumChatFormatting.RED + "Auto reply mod is out of date! Click here to update.");
                versionWarningChatComponent.setChatStyle(clickableChatStyle);
                for (int i = 0; i < 10; ++i) {
                    Minecraft.getMinecraft().thePlayer.addChatComponentMessage((IChatComponent)versionWarningChatComponent);
                }
            }
            Main.checkUpdate = true;
		}
	}
}
