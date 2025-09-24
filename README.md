# BStaffVelocity - Staff Chat Plugin for Velocity

A lightweight and efficient staff chat plugin for Velocity proxy servers, allowing staff members to communicate privately across servers.

## 🚧 Status: Work in Progress (WIP)

This plugin is currently under active development. Core functionality is implemented and working, but additional features are planned.

## ✨ Features

### Current Features
- **Staff Chat Commands**: `/staffchat` or `/sc` with aliases
- **Permission-Based Access**: `staffchat.use` permission required
- **Cross-Server Communication**: Messages reach staff across all connected servers
- **Console Support**: Both sending and receiving staff messages from console
- **Toggle System**: Toggle staff chat mode on/off for individual players
- **Rich Text Formatting**: Color-coded messages with proper formatting
- **Proper Cleanup**: Clean shutdown and resource management

### Planned Features
- [ ] Configurable message formats
- [ ] Customizable permissions
- [ ] Message logging to files
- [ ] Ignore system for staff members
- [ ] Discord integration
- [ ] Staff chat spy mode for higher-ranking staff

## 📥 Installation

1. Download the latest jar file from the Releases page
2. Place the jar file in your Velocity server's `plugins` folder
3. Restart or reload the Velocity proxy
4. Configure permissions (see below)

## 🔧 Configuration

### Permissions
Add these permissions to your permission system (LuckPerms recommended):

```yaml
# Basic staff chat access
staffchat.use:
  description: Allows using staff chat commands
  default: op

# Optional: Additional permissions for future features
staffchat.admin:
  description: Administrative staff chat permissions
  default: op
Command Usage
Send a Staff Message
bash
/staffchat <message>
/sc <message>
Example: /staffchat Server maintenance in 5 minutes

Toggle Staff Chat Mode
bash
/staffchat toggle
/sc toggle
When toggled on, all your messages will go to staff chat until toggled off.

Show Help
bash
/staffchat help
/sc help
🎯 Usage Examples
Basic Staff Communication
bash
/staffchat Please check the spawn area on Lobby1
Console Usage
bash
# From console - messages will show "Console" as sender
/staffchat Proxy restarting in 10 minutes
Toggle Mode
bash
/sc toggle  # Enable staff chat mode
Hello!      # This goes to staff chat
/sc toggle  # Disable staff chat mode
Hello!      # This goes to normal chat
🔨 Development
Building from Source
Clone the repository:

bash
git clone https://github.com/yourusername/BStaffVelocity.git
cd BStaffVelocity
Build with Maven:

bash
mvn clean package
Project Structure
text
BStaffVelocity/
├── src/
│   └── main/
│       └── java/
│           └── etc/
│               └── soap/
│                   └── bStaffVelocity/
│                       ├── BStaffVelocity.java      # Main plugin class
│                       └── StaffChatCommand.java    # Command handler
├── README.md
└── pom.xml
Dependencies
Velocity API 3.0.0+

Adventure Text (included with Velocity)

Guice (included with Velocity)

🤝 Contributing
Since this is a WIP project, contributions are welcome! Here are areas that need work:

Configuration System - YAML configuration implementation

Message Formatting - Customizable message formats

Testing - Unit and integration tests

Documentation - Improved docs and examples

How to Contribute
Fork the repository

Create a feature branch

Make your changes

Test thoroughly

Submit a pull request

📋 TODO List
Implement config.yml for customizable settings

Add message logging system

Create custom events for API integration

Add metrics and statistics

Implement staff chat spy mode

Add Discord webhook integration

Create placeholder support for other plugins

🐛 Issue Reporting
Found a bug? Please create an issue with:

Velocity version

Plugin version

Steps to reproduce

Error logs (if any)

Expected vs actual behavior

📄 License
This project is licensed under the MIT License - see the LICENSE file for details.

🙏 Acknowledgments
VelocityPowered for the excellent Velocity proxy

Kyori Adventure for the great text library

The Minecraft server community for inspiration

