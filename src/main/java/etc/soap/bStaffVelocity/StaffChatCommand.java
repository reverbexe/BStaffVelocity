package etc.soap.bStaffVelocity;

import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class StaffChatCommand implements SimpleCommand {

    private final ProxyServer server;
    private final BStaffVelocity plugin;
    private final Set<UUID> staffChatToggled;

    public StaffChatCommand(ProxyServer server, BStaffVelocity plugin) {
        this.server = server;
        this.plugin = plugin;
        this.staffChatToggled = new HashSet<>();
    }

    @Override
    public void execute(Invocation invocation) {
        String[] args = invocation.arguments();

        // Handle toggle functionality when no arguments are provided
        if (args.length == 0) {
            if (!(invocation.source() instanceof Player)) {
                invocation.source().sendMessage(Component.text("This command can only be executed by players.").color(NamedTextColor.RED));
                return;
            }

            Player player = (Player) invocation.source();
            
            if (!hasPermission(invocation)) {
                player.sendMessage(Component.text("You don't have permission to use staff chat.").color(NamedTextColor.RED));
                return;
            }

            // Send usage information
            sendUsage(invocation.source());
            return;
        }

        // Handle toggle command
        if (args.length == 1 && args[0].equalsIgnoreCase("toggle")) {
            if (!(invocation.source() instanceof Player)) {
                invocation.source().sendMessage(Component.text("This command can only be executed by players.").color(NamedTextColor.RED));
                return;
            }

            Player player = (Player) invocation.source();
            
            if (!hasPermission(invocation)) {
                player.sendMessage(Component.text("You don't have permission to use staff chat.").color(NamedTextColor.RED));
                return;
            }

            toggleStaffChat(player);
            return;
        }

        // Handle help command
        if (args.length == 1 && args[0].equalsIgnoreCase("help")) {
            sendUsage(invocation.source());
            return;
        }

        // Permission check for all sources
        if (!hasPermission(invocation)) {
            invocation.source().sendMessage(Component.text("You don't have permission to use staff chat.").color(NamedTextColor.RED));
            return;
        }

        // Send staff chat message
        sendStaffMessage(invocation.source(), String.join(" ", args));
    }

    @Override
    public boolean hasPermission(Invocation invocation) {
        return invocation.source().hasPermission("staffchat.use");
    }

    private void toggleStaffChat(Player player) {
        UUID playerId = player.getUniqueId();
        
        if (staffChatToggled.contains(playerId)) {
            staffChatToggled.remove(playerId);
            player.sendMessage(Component.text("Staff chat mode disabled. Messages will go to public chat.").color(NamedTextColor.YELLOW));
        } else {
            staffChatToggled.add(playerId);
            player.sendMessage(Component.text("Staff chat mode enabled. Messages will go to staff chat.").color(NamedTextColor.GREEN));
        }
    }

    public boolean isStaffChatToggled(Player player) {
        return staffChatToggled.contains(player.getUniqueId());
    }

    private void sendStaffMessage(com.velocitypowered.api.command.CommandSource source, String message) {
        String senderName = (source instanceof Player) 
                ? ((Player) source).getUsername() 
                : "Console";

        Component staffMessage = Component.text()
                .append(Component.text("[Staff] ", NamedTextColor.RED, TextDecoration.BOLD))
                .append(Component.text(senderName + ": ", NamedTextColor.YELLOW))
                .append(Component.text(message, NamedTextColor.WHITE))
                .build();

        // Send to all online players with permission, including console
        for (Player onlinePlayer : server.getAllPlayers()) {
            if (onlinePlayer.hasPermission("staffchat.use")) {
                onlinePlayer.sendMessage(staffMessage);
            }
        }

        // Also send to console for visibility
        server.getConsoleCommandSource().sendMessage(staffMessage);

        // Log to plugin logger
        plugin.getLogger().info("[Staff] {}: {}", senderName, message);
    }

    private void sendUsage(com.velocitypowered.api.command.CommandSource source) {
        source.sendMessage(Component.text()
                .append(Component.text("Staff Chat Commands:", NamedTextColor.YELLOW, TextDecoration.BOLD))
                .append(Component.newline())
                .append(Component.text("/staffchat <message>", NamedTextColor.GREEN))
                .append(Component.text(" - Send a message to staff chat", NamedTextColor.GRAY))
                .append(Component.newline())
                .append(Component.text("/staffchat toggle", NamedTextColor.GREEN))
                .append(Component.text(" - Toggle staff chat mode", NamedTextColor.GRAY))
                .append(Component.newline())
                .append(Component.text("/staffchat help", NamedTextColor.GREEN))
                .append(Component.text(" - Show this help message", NamedTextColor.GRAY))
                .build());
    }

    public void cleanup() {
        staffChatToggled.clear();
        plugin.getLogger().info("Staff chat command cleaned up.");
    }
}