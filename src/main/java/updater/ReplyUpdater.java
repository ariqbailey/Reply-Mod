package updater;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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
		/*
		try{
			String line;
			String uuid = Minecraft.getMinecraft().thePlayer.getGameProfile().getId().toString();
			URL url2 = new URL( "http://themindofminecraft.com/repositoriesofmemes/index.php?name=modtest&mod=ReplyMod" );
	        HttpURLConnection urlConnection = (HttpURLConnection) url2.openConnection();
	        urlConnection.setConnectTimeout(5000);
	        urlConnection.setReadTimeout(5000);
	        BufferedReader br = new BufferedReader( new InputStreamReader( urlConnection.getInputStream() ) );
	        StringBuilder stringBuilder = new StringBuilder();
			while( ( line = br.readLine() ) != null ){
				stringBuilder.append(line);
			}
		}
		catch( Exception e ){
			e.printStackTrace();
		}
		*/
		
		/*try {
			String uuid = Minecraft.getMinecraft().thePlayer.getGameProfile().getId().toString();
			String url = "http://themindofminecraft.com/repositoriesofmemes/index.php?name=" + "modtest" + "&mod=ReplyMod" ;
		    URL web = new URL(url);
		    InputStream fis = web.openStream();
		    List<String> lines = new ArrayList<String>();
		 
		    String line;
		    String response = "";
		 
		    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis,"UTF-8"));
		    while ((line = bufferedReader.readLine()) != null) {
		        lines.add(line);
		    }
		    bufferedReader.close();
		 
		    for (String value : lines) {response += value;}
		 
		} catch (UnsupportedEncodingException e) {
		    String returnString = "Unsupported encoding!";
		    e.printStackTrace();
		} catch (FileNotFoundException e) {
			String returnString = "File not found!";
		    e.printStackTrace();
		} catch (IOException e) {
		    String returnString = "IO exception!";
		    e.printStackTrace();
		}
		
		*/
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
				ClickEvent versionCheckChatClickEvent = new ClickEvent(ClickEvent.Action.OPEN_URL, "http://www.themindofminecraft.com/replymod/");
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
