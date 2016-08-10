package methods;

import java.io.IOException;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

public class PrintMessageReplyMod {
	public static void showMsg(String msg) throws IOException {
       Main.mc.thePlayer.addChatComponentMessage((IChatComponent)new ChatComponentText((Object)EnumChatFormatting.GOLD + "R" + (Object)EnumChatFormatting.YELLOW + "M" + (Object)EnumChatFormatting.DARK_GRAY + " > " + (Object)EnumChatFormatting.GRAY + msg));
    }
	
	public static void showRedMessage( String msg ) throws IOException{
		Main.mc.thePlayer.addChatComponentMessage((IChatComponent)new ChatComponentText( (Object) EnumChatFormatting.RED + msg));
	}

    public static void rawMsg(String msg) throws IOException {
    	Main.mc.thePlayer.addChatComponentMessage((IChatComponent) new ChatComponentText(msg) );
    }

    public static void line() throws IOException {
    	Main.mc.thePlayer.addChatComponentMessage((IChatComponent) new ChatComponentText((Object)EnumChatFormatting.GOLD + "" + (Object)EnumChatFormatting.BOLD + "----------------------------------"));
    }

	public static void showYellowMsg(String string, String command) {
		// TODO Auto-generated method stub
		Main.mc.thePlayer.addChatComponentMessage((IChatComponent)new ChatComponentText((Object)EnumChatFormatting.GOLD + "R" + (Object)EnumChatFormatting.YELLOW + "M" + (Object)EnumChatFormatting.DARK_GRAY + " > " + (Object)EnumChatFormatting.GRAY + string + (Object)EnumChatFormatting.GOLD + command ));
	}
}
