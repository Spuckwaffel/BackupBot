package com.spuckwaffel.backupbot;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.security.auth.login.LoginException;

import com.spuckwaffel.backupbot.events.WebhookBackup;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

public class Main extends ListenerAdapter{

public static String prefix = "!";
	
	public static void main(String[] args)throws LoginException{
		
		System.out.println("Yawn. The bot woke up! Here are some stats:");
		
		Date currentDate = new Date();
		
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("current date:" +dateformat.format(currentDate));
		
		JDABuilder.createLight("token", GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_PRESENCES,
        		GatewayIntent.DIRECT_MESSAGE_REACTIONS,
        		GatewayIntent.GUILD_MEMBERS,
        		//GatewayIntent.GUILD_MESSAGE_REACTIONS,
        		GatewayIntent.DIRECT_MESSAGES)
        .setStatus(OnlineStatus.IDLE)
        .setMemberCachePolicy(MemberCachePolicy.ALL)
        
        .addEventListeners(new WebhookBackup())

        .build();
	}
}
