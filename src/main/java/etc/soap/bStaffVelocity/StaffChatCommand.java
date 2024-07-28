package etc.soap.bStaffVelocity;

import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class StaffChatCommand implements SimpleCommand {

    private final ProxyServer server;

    public StaffChatCommand(ProxyServer server) {
        this.server = server;
    }

    @Override
    public void execute(Invocation invocation) {
        if (!(invocation.source() instanceof Player)) {
            invocation.source().sendMessage(Component.text("This command can only be executed by players.").color(NamedTextColor.RED));
            return;
        }

        Player player = (Player) invocation.source();

        if (!player.hasPermission("staffchat.use")) {
            player.sendMessage(Component.text("You don't have permission to use staff chat.").color(NamedTextColor.RED));
            return;
        }

        String[] args = invocation.arguments();

        if (args.length == 0) {
            player.sendMessage(Component.text("Usage: /staffchat <message>").color(NamedTextColor.YELLOW));
            return;
        }

        String message = String.join(" ", args);
        Component staffMessage = Component.text("[Staff] ")
                .color(NamedTextColor.RED)
                .append(Component.text(player.getUsername() + ": ").color(NamedTextColor.YELLOW))
                .append(Component.text(message).color(NamedTextColor.WHITE));

        for (Player onlinePlayer : server.getAllPlayers()) {
            if (onlinePlayer.hasPermission("staffchat.use")) {
                onlinePlayer.sendMessage(staffMessage);
            }
        }
    }
}