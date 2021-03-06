package me.kizika.dice

import me.kizika.dice.InventoryEventChecker.clickEventCheck
import me.kizika.dice.InventoryEventChecker.dragEventCheck
import me.kizika.dice.InventoryEventChecker.itemFrameClick
import me.kizika.dice.Util.getColored
import me.kizika.dice.Util.getNameSpaceKey
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryDragEvent
import org.bukkit.event.player.PlayerDropItemEvent
import org.bukkit.event.player.PlayerInteractEntityEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.persistence.PersistentDataType

object EventListener : Listener {
    @EventHandler
    fun onPlayerJoin (e: PlayerJoinEvent){
        val p = e.player
        e.joinMessage = "${p.displayName}が鯖に来ました"
    }

    @EventHandler
    fun onPlayerRelease(e: PlayerDropItemEvent){
        val key = getNameSpaceKey("trade")
        val ctrade = e.itemDrop.itemStack.itemMeta?.persistentDataContainer?.get(key, PersistentDataType.INTEGER)
        if(ctrade==null||ctrade==0){
            e.isCancelled=true
            e.player.sendMessage(getColored("&c取引不能アイテムです"))
        }
    }

    @EventHandler
    fun onInventoryControl(e: InventoryClickEvent){
        clickEventCheck(e)
    }

    @EventHandler
    fun onInventoryDrag(e: InventoryDragEvent){
        dragEventCheck(e)
    }

    @EventHandler
    fun onPlayerInteractEntity(e: PlayerInteractEntityEvent){
        itemFrameClick(e)
    }
}