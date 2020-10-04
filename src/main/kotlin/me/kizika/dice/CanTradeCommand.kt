package me.kizika.dice

import org.bukkit.NamespacedKey
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.persistence.PersistentDataType
import me.kizika.dice.Util.getColored
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.persistence.PersistentDataContainer


object CanTradeCommand :CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if(sender is Player){
            val switch = args.getOrNull(0)?.toIntOrNull()
            val trade = if (switch==0||switch==null)0 else 1
            val MainHandItems = sender.inventory.itemInMainHand
            val MainHandStack = MainHandItems.itemMeta
            MainHandStack?.let{
                MainHandItems.itemMeta = setCanTrade(MainHandStack,trade)
            }?: run{
                sender.sendMessage(getColored("MainHandにアイテムを持ってください"))
                return true
            }
            sender.inventory.setItemInMainHand(MainHandItems)
            when(trade){
                0->sender.sendMessage(getColored("交換不能アイテムに変換しました"))
                1->sender.sendMessage(getColored("交換可能アイテムに変換しました"))
            }
        }
        return true
        }

    private fun setCanTrade(MHMeta: ItemMeta, stat: Int): ItemMeta {
        @Suppress("DEPRECATION") val key = NamespacedKey(NamespacedKey.BUKKIT,"trade")
        MHMeta.persistentDataContainer.set(key, PersistentDataType.INTEGER,stat)
        return MHMeta
    }
}