package etc.soap.bStaffVelocity;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
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

    @Inject
    public BStaffVelocity(ProxyServer server, Logger logger) {
        this.server = server;
        this.logger = logger;
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        CommandManager commandManager = server.getCommandManager();
        CommandMeta meta = commandManager.metaBuilder("staffchat")
                .aliases("sc")
                .build();

        commandManager.register(meta, new StaffChatCommand(server));

        logger.info("BStaff Velocity plugin has been enabled!");
    }
}