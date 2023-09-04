package org.lone64.mws.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.lone64.mws.util.adventure.AdventureUtil;
import org.lone64.mws.world.WorldManager;
import org.lone64.mws.world.WorldType;

public class MainCmd implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String arg, @NotNull String[] args) {
        if (sender instanceof ConsoleCommandSender) return true;

        Player player = (Player) sender;
        if (!player.hasPermission("mws.admin")) return true;

        if (args.length == 0) {
            AdventureUtil.playerMessage(player, "{@p} &7명령어가 올바르지 않습니다!");
            return true;
        }

        String env = null;
        switch (args[0]) {
            default:
                AdventureUtil.playerMessage(player, "{@p} &7명령어가 올바르지 않습니다!");
                break;
            case "create":
                if (args.length < 2) {
                    AdventureUtil.playerMessage(player, "{@p} &7월드 이름을 입력하여 주세요!");
                    return true;
                }

                if (args.length > 2) {
                    switch (args[2].toLowerCase()) {
                        default:
                            AdventureUtil.playerMessage(player, "{@p} &7월드 종류가 올바르지 않습니다!");
                            return true;
                        case "normal":
                            env = "NORMAL";
                            break;
                        case "nether":
                            env = "NETHER";
                            break;
                        case "the_end":
                            env = "THE_END";
                            break;
                    }
                }

                WorldManager wm = WorldManager.of(args[1]);
                if (wm.isWorld()) {
                    AdventureUtil.playerMessage(player, "{@p} &7이미 존재하는 월드입니다!");
                    return true;
                }

                AdventureUtil.playerMessage(player, "{@p} &6" + wm.getName() + "&f(을)를 생성하고 있습니다! 잠시만 기다려주세요...");
                if (env == null)
                    wm.create();
                else wm.create(WorldType.valueOf(env.toLowerCase()));
                AdventureUtil.playerMessage(player, "{@p} &a" + wm.getName() + "&f(을)를 생성했습니다!");
                break;
            case "remove":
            case "delete":
                if (args.length < 2) {
                    AdventureUtil.playerMessage(player, "{@p} &7월드 이름을 입력하여 주세요!");
                    return true;
                }

                wm = WorldManager.of(args[1]);
                if (!wm.isWorld()) {
                    AdventureUtil.playerMessage(player, "{@p} &7존재하지 않는 월드입니다!");
                    return true;
                } else if (wm.size() != 0) {
                    AdventureUtil.playerMessage(player, "{@p} &7초기화하려는 월드에 플레이어가 존재합니다!");
                    return true;
                }

                AdventureUtil.playerMessage(player, "{@p} &6" + wm.getName() + "&f(을)를 삭제하고 있습니다! 잠시만 기다려주세요...");
                wm.delete();
                AdventureUtil.playerMessage(player, "{@p} &c" + wm.getName() + "&f(을)를 삭제했습니다!");
                break;
            case "load":
                if (args.length < 2) {
                    AdventureUtil.playerMessage(player, "{@p} &7월드 이름을 입력하여 주세요!");
                    return true;
                }

                wm = WorldManager.of(args[1]);
                if (!wm.isWorld()) {
                    AdventureUtil.playerMessage(player, "{@p} &7존재하지 않는 월드입니다!");
                    return true;
                }

                wm.load();
                AdventureUtil.playerMessage(player, "{@p} &a" + wm.getName() + "&f(을)를 불러왔습니다!");
                break;
            case "unload":
                if (args.length < 2) {
                    AdventureUtil.playerMessage(player, "{@p} &7월드 이름을 입력하여 주세요!");
                    return true;
                }

                wm = WorldManager.of(args[1]);
                if (!wm.isWorld()) {
                    AdventureUtil.playerMessage(player, "{@p} &7존재하지 않는 월드입니다!");
                    return true;
                } else if (wm.size() != 0) {
                    AdventureUtil.playerMessage(player, "{@p} &7초기화하려는 월드에 플레이어가 존재합니다!");
                    return true;
                }

                wm.unload();
                AdventureUtil.playerMessage(player, "{@p} &c" + wm.getName() + "&f(을)를 제외했습니다!");
                break;
            case "backup":
                if (args.length < 2) {
                    AdventureUtil.playerMessage(player, "{@p} &7월드 이름을 입력하여 주세요!");
                    return true;
                }

                wm = WorldManager.of(args[1]);
                if (!wm.isWorld()) {
                    AdventureUtil.playerMessage(player, "{@p} &7존재하지 않는 월드입니다!");
                    return true;
                } else if (wm.size() != 0) {
                    AdventureUtil.playerMessage(player, "{@p} &7초기화하려는 월드에 플레이어가 존재합니다!");
                    return true;
                }

                AdventureUtil.playerMessage(player, "{@p} &6" + wm.getName() + "&f(을)를 백업하고 있습니다! 잠시만 기다려주세요...");
                wm.backup();
                AdventureUtil.playerMessage(player, "{@p} &a" + wm.getName() + "&f(을)를 백업했습니다!");
                break;
            case "reset":
                if (args.length < 2) {
                    AdventureUtil.playerMessage(player, "{@p} &7월드 이름을 입력하여 주세요!");
                    return true;
                }

                wm = WorldManager.of(args[1]);
                if (!wm.isWorld()) {
                    AdventureUtil.playerMessage(player, "{@p} &7존재하지 않는 월드입니다!");
                    return true;
                } else if (wm.size() != 0) {
                    AdventureUtil.playerMessage(player, "{@p} &7초기화하려는 월드에 플레이어가 존재합니다!");
                    return true;
                } else if (!wm.isBackup()) {
                    AdventureUtil.playerMessage(player, "{@p} &7월드를 먼저 백업하고 초기화를 진행해주세요!");
                    return true;
                }

                AdventureUtil.playerMessage(player, "{@p} &6" + wm.getName() + "&f(을)를 초기화하고 있습니다! 잠시만 기다려주세요...");
                wm.reset();
                AdventureUtil.playerMessage(player, "{@p} &a" + wm.getName() + "&f(을)를 초기화했습니다!");
                break;
            case "warp":
                if (args.length < 2) {
                    AdventureUtil.playerMessage(player, "{@p} &7월드 이름을 입력하여 주세요!");
                    return true;
                }

                wm = WorldManager.of(args[1]);
                if (!wm.isWorld()) {
                    AdventureUtil.playerMessage(player, "{@p} &7존재하지 않는 월드입니다!");
                    return true;
                }

                player.teleport(wm.center());
                AdventureUtil.playerMessage(player, "{@p} &a" + wm.getName() + "&f으로 이동했습니다!");
                break;
        }
        return false;
    }

}
