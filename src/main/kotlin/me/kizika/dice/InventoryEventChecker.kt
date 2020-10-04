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
    fun ClickEventcheck(p: InventoryClickEvent) {
        val inv = p.cursor?.itemMeta?.persistentDataContainer?.get(key, PersistentDataType.INTEGER)
        val cli = p.currentItem?.itemMeta?.persistentDataContainer?.get(key, PersistentDataType.INTEGER)
        if (p.isShiftClick) {
            if(!mine.contains(p.inventory.type)){
                if(cli==null||cli==0) {
                    p.isCancelled = true
                    return
                }
            }
        }
        if(p.cursor?.type != Material.AIR){
            if(!mine.contains(p.clickedInventory?.type)){
                if(inv==null ||inv==0) {
                    p.isCancelled = true
                }
            }
        }
    }

    fun DragEventCheck(p: InventoryDragEvent){
        val inv = p.oldCursor.itemMeta?.persistentDataContainer?.get(key, PersistentDataType.INTEGER)
        if(p.oldCursor.type != Material.AIR){
            if(!mine.contains(p.inventory.type)){
                if(inv==null||inv==0){
                    p.isCancelled=true
                }
            }
        }
    }
}