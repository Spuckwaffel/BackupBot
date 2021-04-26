package com.spuckwaffel.backupbot.events;

import java.io.IOException;

import java.util.List;

import com.spuckwaffel.backupbot.DiscordWebhook;

import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

/*
	This will send all messages from your server in a new server and will create a webhook if theres no webhook
	DO NOT automate this/abuse this to create all text channels and categories *at once* and send all messages 
	in thereas this will most likely result in a API abuse. It is designed on purpose that it will only create 
	channels, categories and webhooks whenever the user sends a message in the channel. This will not update
	permissions as that would defenitely get you API banned for spamming. If you want to get all the channels
	permissions consider doing a server copy. This will also save the bot the time to create the channel and
	will only create a webhook if theres none and sends messages with it. The code is not the best but hey it
	works, feel free to change it and improve it. Make sure to enter the guild id everywhere (removed it on
	purpose for security). Be careful using this as its kinds against ToS, but oh well if you want to update
	server and have all the messages backed up, use it.
*/

public class WebhookBackup extends ListenerAdapter{
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		
		if(!event.getMessage().isWebhookMessage()) {
			List<TextChannel> Channels = event.getJDA().getGuildById("").getTextChannelsByName(event.getChannel().getName(), true);
			
			//check if the channel has a parent, .getName() will throw a exception if there is no parent
			if(event.getChannel().getParent() == null) {
				//check if there is a channel with the same name
				if(Channels.size() > 0) {
					//get the webhooks of the channel
					Channels.get(0).retrieveWebhooks().queue(WebHooks -> {
						boolean webhookfound = false;
						//check if there is our bot webhook
						for(int i =0; i < WebHooks.size(); i++) {
							if(WebHooks.get(i).getName().equals("Backup")) {
								webhookfound = true;
							    DiscordWebhook webhook = new DiscordWebhook(WebHooks.get(i).getUrl()); //gets the webhook url
							    webhook.setContent(event.getMessage().getContentRaw()); //gets the message content
							    webhook.setAvatarUrl(event.getAuthor().getAvatarUrl()); //get the users Avatar
							    webhook.setUsername(event.getAuthor().getName()); //get the users name
							    webhook.setTts(false);
								try {
									webhook.execute();
								} catch (IOException e) {}
							}
							//if the webhook couldn't be found
							if(!webhookfound) {
								Channels.get(0).createWebhook("Backup").queue(WebhookAction -> {
									DiscordWebhook webhook = new DiscordWebhook(WebhookAction.getUrl());
								    webhook.setContent(event.getMessage().getContentRaw());
								    webhook.setAvatarUrl(event.getAuthor().getAvatarUrl());
								    webhook.setUsername(event.getAuthor().getName());
								    webhook.setTts(false);
									try {
										webhook.execute();
									} catch (IOException e) {}
								});
							}
						}
					});
				}
				//if there's no channel with the same name
				else {
					event.getJDA().getGuildById("").createTextChannel(event.getChannel().getName()).queue(Channel -> {
						Channel.createWebhook("Backup").queue(WebhookAction -> {
							DiscordWebhook webhook = new DiscordWebhook(WebhookAction.getUrl());
							webhook.setContent(event.getMessage().getContentRaw());
							webhook.setAvatarUrl(event.getAuthor().getAvatarUrl());
							webhook.setUsername(event.getAuthor().getName());
							webhook.setTts(false);
							try {
								webhook.execute();
							} catch (IOException e) {}
						});
					});
				}
			}
			//channel has a parent
			else {
				List<Category> Categories = event.getJDA().getGuildById("").getCategoriesByName(event.getChannel().getParent().getName(), true);
				//if a category with the same name could be found
				if(Categories.size() > 0) {
					//
					//same stuff just copy pasted
					//
					if(Channels.size() > 0) {
						Channels.get(0).retrieveWebhooks().queue(WebHooks -> {
							boolean webhookfound = false;
							for(int i =0; i < WebHooks.size(); i++) {
								if(WebHooks.get(i).getName().equals("Backup")) {
									webhookfound = true;
								    DiscordWebhook webhook = new DiscordWebhook(WebHooks.get(i).getUrl());
								    webhook.setContent(event.getMessage().getContentRaw());
								    webhook.setAvatarUrl(event.getAuthor().getAvatarUrl());
								    webhook.setUsername(event.getAuthor().getName());
								    webhook.setTts(false);
									try {
										webhook.execute();
									} catch (IOException e) {}
								}
								if(!webhookfound) {
									Channels.get(0).createWebhook("Backup").queue(WebhookAction -> {
										DiscordWebhook webhook = new DiscordWebhook(WebhookAction.getUrl());
									    webhook.setContent(event.getMessage().getContentRaw());
									    webhook.setAvatarUrl(event.getAuthor().getAvatarUrl());
									    webhook.setUsername(event.getAuthor().getName());
									    webhook.setTts(false);
										try {
											webhook.execute();
										} catch (IOException e) {}
									});
								}
							}
						});
					}
					else {
						event.getJDA().getGuildById("").createTextChannel(event.getChannel().getName(), Categories.get(0)).queue(Channel -> {
							Channel.createWebhook("Backup").queue(WebhookAction -> {
								DiscordWebhook webhook = new DiscordWebhook(WebhookAction.getUrl());
								webhook.setContent(event.getMessage().getContentRaw());
								webhook.setAvatarUrl(event.getAuthor().getAvatarUrl());
								webhook.setUsername(event.getAuthor().getName());
								webhook.setTts(false);
								try {
									webhook.execute();
								} catch (IOException e) {}
							});
						});
					}
				}
				//if there is no category create one and the channel and the webhook and post it
				else {
					event.getJDA().getGuildById("").createCategory(event.getChannel().getParent().getName()).queue( category -> {
						event.getJDA().getGuildById("").createTextChannel(event.getChannel().getName(), category).queue(channel -> {
							channel.createWebhook("Backup").queue(WebhookAction -> {
								DiscordWebhook webhook = new DiscordWebhook(WebhookAction.getUrl());
								webhook.setContent(event.getMessage().getContentRaw());
								webhook.setAvatarUrl(event.getAuthor().getAvatarUrl());
								webhook.setUsername(event.getAuthor().getName());
								webhook.setTts(false);
								try {
									webhook.execute();
								} catch (IOException e) {}
							});
						});
					});
				}
			}
		}
	}
}
