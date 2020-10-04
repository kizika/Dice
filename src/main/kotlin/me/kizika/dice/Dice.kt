package me.kizika.dice

import org.bukkit.command.CommandExecutor
import org.bukkit.plugin.java.JavaPlugin

class Dice : JavaPlugin() {
    override fun onEnable() {
        // Plugin startup logic
        server.pluginManager.registerEvents(EventListener,this)
        registerCommand("dice",DiceCommand)
        registerCommand("cantrade",CanTradeCommand)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }

    private fun registerCommand(label: String,executor: CommandExecutor){
        getCommand(label)?.run{
            setExecutor(executor)
            logger.info("/$label を登録しました")
        }?:logger.severe("/$label を登録できませんでした")
    }
}