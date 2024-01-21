package com.discord;

import events.EventListener;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

class Bot {
    private final Dotenv config;

    public Dotenv getConfig() {
        return config;
    }

    public Bot() {
        config = Dotenv.configure().load();
        String token = config.get("TOKEN");
        JDA jda = JDABuilder.createDefault(token).enableIntents(GatewayIntent.GUILD_MEMBERS)
                .build();

        jda.addEventListener(new EventListener());
        // System.out.println("log" + jda);
    }
}

public class Main {

    public static void main(String[] args) {
        Bot bot = new Bot();
    }
}