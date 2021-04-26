# Backup Bot

### By Spuckwaffel#5000

### Discord Backup Bot that will create all text channels and categories and sends messages via webhooks in your backup server.

## Table of contents
* [General info](#general-info)
* [Socials](#socials)
* [Technologies](#technologies)
* [Setup](#setup)

## General info

This will send all messages from your server in a new server and will create a webhook if there's no webhook.
DO NOT automate this/abuse this to create all text channels and categories *at once* and send all messages in
thereas this will most likely result in a API abuse. It is designed on purpose that it will only create 
channels, categories and webhooks whenever the user sends a message in the channel. This will not update
permissions as that would defenitely get you API banned for spamming. If you want to get all the channels
permissions consider doing a server copy. This will also save the bot the time to create the channel and will
only create a webhook if theres none and sends messages with it. The code is not the best but hey it works,
feel free to change it and improve it.

## Socials

* [<img align="left" alt="Spuckwaffel | Discord" width="22px" src="https://i.ibb.co/9WJF8CY/Discord-Logo-Color.png" />][discord]Spuckwaffel#5000
* [<img align="left" alt="Spuckwaffel | Discord Server" width="22px" src="https://i.ibb.co/9WJF8CY/Discord-Logo-Color.png" />][DCServer] [My Discord Server][DCServer]
* [<img align="left" alt="Spuckwaffel | Twitter" width="22px" src="https://i.ibb.co/fxzdm2C/Twitter-bird-logo-2012-svg.png" />][Twitter] [My Twitter][twitter]
* [<img align="left" alt="Spuckwaffel | Youtube" width="22px" src="https://i.ibb.co/1bhf9w0/You-Tube-icon.png" />][Twitter] [My Youtube][youtube]

## Technologies
Project is created with:
* [<img align="left" alt="Eclipse Java Oxygen" width="22px" src="https://i.ibb.co/6sjcrPg/ecl.png" />][eclipse][Eclipse Java Oxygen 3.0][eclipse]
* [<img align="left" alt="JDA" width="22px" src="https://i.ibb.co/Wv7MLP0/jar-2.png" />][jar][JDA-4.2.0][jar]
* [<img align="left" alt="JDA" width="22px" src="https://i.ibb.co/Wv7MLP0/jar-2.png" />][jar][MySQL Jar][MySQL]
* At least 5 IQ lol
	
## Setup
To run this project, make sure to have a java compiler + newest JDA, MySQL jar

You can find the links under [Technologies](#technologies)


[discord]: https://discord.com
[DCServer]: https://discord.gg/aP88KNHSt7
[twitter]: https://twitter.com/spuckwaffel
[youtube]: https://youtube.com/spuckwaffel
[eclipse]: https://www.eclipse.org/downloads/packages/release/oxygen/3
[jar]: https://ci.dv8tion.net/job/JDA/228/artifact/build/libs/
[MySQL]: https://dev.mysql.com/downloads/connector/j/
