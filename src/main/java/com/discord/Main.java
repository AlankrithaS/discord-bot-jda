package com.discord;

import commands.CommandManager;
import events.EventListener;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

class Bot {
    private final Dotenv config;

    public Dotenv getConfig() {
        return config;
    }

    public Bot() {
        config = Dotenv.configure().load();
        String token = config.get("TOKEN");
        // JDA jda =
        // JDABuilder.createDefault(token).enableIntents(GatewayIntent.GUILD_MEMBERS).setMemberCachePolicy(MemberCachePolicy.ONLINE);
        // .build();
        JDA jda = JDABuilder.createDefault(token)
                .enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_PRESENCES, GatewayIntent.GUILD_MESSAGES)
                .setMemberCachePolicy(MemberCachePolicy.ALL).setChunkingFilter(ChunkingFilter.ALL)
                .enableCache(CacheFlag.ONLINE_STATUS)
                .build();
        jda.addEventListener(new EventListener(), new CommandManager());
        // System.out.println("log" + jda);
    }
}

public class Main {

    public static void main(String[] args) {
        Bot bot = new Bot();
    }
}