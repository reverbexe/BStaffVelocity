package etc.soap.bStaffVelocity;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.command.CommandManager;
import com.velocitypowered.api.command.CommandMeta;
import org.slf4j.Logger;

@Plugin(
        id = "bstaffvelocity",
        name = "BStaff Velocity",
        version = "1.0-SNAPSHOT",
        description = "A simple staff chat plugin for Velocity",
        authors = {"Soapdev"}
)
public class BStaffVelocity {

    private final ProxyServer server;
    private final Logger logger;
    private StaffChatCommand staffChatCommand;

    @Inject
    public BStaffVelocity(ProxyServer server, Logger logger) {
        this.server = server;
        this.logger = logger;
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        CommandManager commandManager = server.getCommandManager();
        
        // Create the command instance and pass the plugin instance
        staffChatCommand = new StaffChatCommand(server, this);
        
        CommandMeta meta = commandManager.metaBuilder("staffchat")
                .aliases("sc")
                .plugin(this)
                .build();

        commandManager.register(meta, staffChatCommand);

        logger.info("BStaff Velocity plugin has been enabled!");
    }

    @Subscribe
    public void onProxyShutdown(ProxyShutdownEvent event) {
        // Clean up when the proxy shuts down
        if (staffChatCommand != null) {
            staffChatCommand.cleanup();
        }
        
        logger.info("BStaff Velocity plugin has been disabled!");
    }

    public ProxyServer getServer() {
        return server;
    }

    public Logger getLogger() {
        return logger;
    }
}