package me.kizika.dice

import me.kizika.dice.Util.getNameSpaceKey
import org.bukkit.Material
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryDragEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.persistence.PersistentDataType

object InventoryEventChecker {
    val key = getNameSpaceKey("trade")
    val mine = mutableListOf(InventoryType.ENDER_CHEST,InventoryType.PLAYER,InventoryType.CRAFTING)
    fun ClickEventcheck(p: InventoryClickEvent){
        val inv = p.cursor?.itemMeta?.persistentDataContainer?.get(key, PersistentDataType.INTEGER)
        if(p.cursor?.type != Material.AIR){
            p.whoClicked.sendMessage("debug1")
            if(!mine.contains(p.clickedInventory?.type)){
                p.whoClicked.sendMessage("debug2")
                if(inv==null ||inv==0) {
                    p.isCancelled = true
                    p.whoClicked.sendMessage("debug3")
                }
            }
        }
    }

    fun DragEventCheck(p: InventoryDragEvent){
        val inv = p.oldCursor.itemMeta?.persistentDataContainer?.get(key, PersistentDataType.INTEGER)
        val testdata :String = p.inventory.type.toString()
        p.whoClicked.sendMessage(testdata)
        if(p.oldCursor.type != Material.AIR){
            if(!mine.contains(p.inventory.type)){
                if(inv==null||inv==0){
                    p.isCancelled=true
                }
            }
        }
    }
}