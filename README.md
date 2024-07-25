# BeeChat
Chat and tab formatting plugin for Paper and its forks.
### Features
- [MiniMessage](https://docs.advntr.dev/minimessage/format.html) in config and chat
- Using placeholders from [MiniPlaceholders](https://modrinth.com/plugin/miniplaceholders) and [PlaceholderAPI](https://hangar.papermc.io/HelpChat/PlaceholderAPI) in config
- Message formatting with permissions
- Chat channels with customizable formats and permissions
- Spy mode for monitoring chat channels
- Tab customization

## Message formatting
Players with permission can use formatting with MiniMessage in chat.

### Permissions
- **beechat.format.all** - all formatting tags
- **beechat.format.color** - `color`, `gradient` and `rainbow` tags
- **beechat.format.reset** - `reset` tag
- **beechat.format.decorations** - `bold`, `italic`, `underlined`, `strikethrough` and `obfuscated` decorations
- **beechat.format.insertion** - `insert` tag
- **beechat.format.click** - `click` tag
- **beechat.format.hover** - `hover` tag

### Example
![MiniMessage support in chat messages. Message: "<gradient:red:green:blue><b>MiniMessage</b> in chat messages"](https://github.com/beaver010/bee-chat/blob/main/screenshots/chat.png)
_The **MiniPlaceholders LuckPerms Expansion** plugin is used here to display the prefix from **LuckPerms**_

## Chat channels
BeeChat supports multiple chat channels, each with its own format, distance and permission. Channels are identified by prefixes in the message.

### Example
![Chat channels](https://github.com/beaver010/bee-chat/blob/main/screenshots/channels.png)

## Spy mode
Spy mode allows players with permission to monitor all chat channels. 
This is useful for administrators who need to oversee conversations for moderation purposes.
Can be enabled or disabled using the `/beechat spy` command. Permission: **beechat.spy**

## Tab customization
Tab header, footer and player name format can be customized in config.

### Example
![Example tab list](https://github.com/beaver010/bee-chat/blob/main/screenshots/tab.jpeg)

## Configuration
The default config can be found here: [https://github.com/beaver010/bee-chat/blob/main/src/main/resources/config.yml](https://github.com/beaver010/bee-chat/blob/main/src/main/resources/config.yml).

The configuration file (`config.yml`) only supports [MiniMessage](https://docs.advntr.dev/minimessage/format.html) format. Legacy (ampersand) formatting is not supported.

You can use placeholders from [MiniPlaceholders](https://modrinth.com/plugin/miniplaceholders) and [PlaceholderAPI](https://hangar.papermc.io/HelpChat/PlaceholderAPI) in the configuration.

### Reloading
The configuration can be reloaded using the command `/beechat reload`. 

Permission for the command: **beechat.reload**

### Example configuration

```yaml
chat:
  message-format: '<luckperms_prefix><reset> <name> <yellow>→ <gray><message>'
  channels:
    - identifier: '!'
      format: '<message_format>'

    - identifier: '#'
      permission: chat.channel.staff
      format: '<red>(Staff)</red> <message_format>'

    - identifier: ''
      distance: 40
      format: '<yellow>(Local)</yellow> <message_format>'
  spy:
    format: '<aqua>Spy</aqua> <channel_message>'
    disable-on-leave: true

tab-list:
  enable: true
  update-period: 200
  header: '<br><gradient:yellow:gold>Example Server<br>'
  footer: '<br>There are <green><server_online></green> players online<br>'
  player-name: '<luckperms_prefix> <reset><name> <luckperms_suffix>'

messages:
  unknown-subcommand: '<red>Unknown subcommand'
  reload: '<green>The <gradient:yellow:gold>BeeChat</gradient> configuration has been reloaded'
  not-player: '<red>This command can only be executed by a player'
  spy-mode-enabled: '<aqua>Spy</aqua> mode <green>enabled</green>. Enter the command again to <red>disable</red>'
  spy-mode-disabled: '<aqua>Spy</aqua> mode <red>disabled</red>'
```
