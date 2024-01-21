package events;

import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import net.dv8tion.jda.api.entities.*;

public class EventListener extends ListenerAdapter {

    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event) {
        User user = event.getUser();
        String emoji = event.getReaction().toString();
        System.out.println(emoji);
        String channel = event.getChannel().getAsMention();
        String jump = event.getJumpUrl();

        String msg = user.getAsMention() + "reacted to " + emoji + "in the" + channel;
        event.getGuild().getDefaultChannel().asStandardGuildMessageChannel().sendMessage(msg).queue();

    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String msg = event.getMessage().getContentRaw();
        System.out.println(msg);
        if (msg.contains("ping")) {
            event.getChannel().sendMessage("pong").queue();
        }
    }

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        String avatar = event.getUser().getEffectiveAvatarUrl();
        System.out.println(avatar);
    }

}
