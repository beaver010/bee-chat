# BeeChat
Chat and tab formatting plugin for Paper and its forks.
### Features
- [MiniMessage](https://docs.advntr.dev/minimessage/format.html) in config and chat
- Using placeholders from [MiniPlaceholders](https://modrinth.com/plugin/miniplaceholders) and [PlaceholderAPI](https://hangar.papermc.io/HelpChat/PlaceholderAPI) in config
- Message formatting with permissions
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
![MiniMessage support in chat messages. Message: "<gradient:red:green:blue><b>MiniMessage</b> in chat messages"](https://cdn.modrinth.com/data/cached_images/3e62983eb618d1df5747f697c433811dd3b5922c.png)
_The **MiniPlaceholders LuckPerms Expansion** plugin is used here to display the prefix from **LuckPerms**_

## Tab customization
Tab header, footer and player name format can be customized in config.

### Example
![Example tab list](https://cdn.modrinth.com/data/cached_images/6c4cec84f34910c20872e2ce60347cd9867e95e5.jpeg)

## Configuration
The default config can be found here: [https://github.com/beaver010/bee-chat/blob/main/src/main/resources/config.yml](https://github.com/beaver010/bee-chat/blob/main/src/main/resources/config.yml).

The configuration file (`config.yml`) only supports [MiniMessage](https://docs.advntr.dev/minimessage/format.html) format. Legacy (ampersand) formatting is not supported.

You can use placeholders from [MiniPlaceholders](https://modrinth.com/plugin/miniplaceholders) and [PlaceholderAPI](https://hangar.papermc.io/HelpChat/PlaceholderAPI) in the configuration.

Here's an example of the configuration I used for the screenshots:

```
chat:
  # Placeholders:
  #   <name> - player name
  #   <message> - message
  message-format: '<luckperms_prefix><reset> <name> <yellow>→ <gray><message>'

tab-list:
  # Enable tab list formatting
  enable: true
  # Tab list update period in ticks (20 ticks = 1 second)
  update-period: 200
  # Tab list header
  header: '<br><gradient:yellow:gold>Example Server<br>'
  # Tab list footer
  footer: '<br>There are <green><server_online></green> players online<br>'
  # Player name in tab list.
  # <name> - placeholder for player name
  player-name: '<luckperms_prefix> <reset><name> <luckperms_suffix>'

# Messages do NOT support PlaceholderAPI placeholders
messages:
  # Used when the sender passes an unknown subcommand to the `/beechat` command
  unknown-subcommand: '<red>Unknown subcommand'
  # Message for '/beechat reload' command
  reload: '<green>The <gradient:yellow:gold>BeeChat</gradient> configuration has been reloaded'
```

### Reloading
The configuration can be reloaded using the command `/beechat reload`. 

Permission for the command: **beechat.reload**
