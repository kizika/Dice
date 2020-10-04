package me.kizika.dice

import me.kizika.dice.Util.getNameSpaceKey
import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.entity.ItemFrame
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryDragEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.event.player.PlayerInteractEntityEvent
import org.bukkit.inventory.Inventory
import org.bukkit.persistence.PersistentDataType

object InventoryEventChecker {
    val key = getNameSpaceKey("trade")
    val mine = mutableListOf(InventoryType.BARREL,InventoryType.CHEST,InventoryType.BLAST_FURNACE,InventoryType.BEACON,
                                InventoryType.BREWING,InventoryType.SMOKER,InventoryType.SHULKER_BOX,InventoryType.HOPPER,
                                InventoryType.DROPPER,InventoryType.FURNACE,InventoryType.DISPENSER)
    fun clickEventCheck(p: InventoryClickEvent) {
        val inv = p.cursor?.itemMeta?.persistentDataContainer?.get(key, PersistentDataType.INTEGER)
        val cli = p.currentItem?.itemMeta?.persistentDataContainer?.get(key, PersistentDataType.INTEGER)
        if (p.isShiftClick) {
            if(mine.contains(p.inventory.type)){
                if(cli==null||cli==0) {
                    p.isCancelled = true
                    return
                }
            }
        }
        if(p.cursor?.type != Material.AIR){
            if(mine.contains(p.clickedInventory?.type)){
                if(inv==null ||inv==0) {
                    p.isCancelled = true
                }
            }
        }
    }

    fun dragEventCheck(p: InventoryDragEvent){
        val inv = p.oldCursor.itemMeta?.persistentDataContainer?.get(key, PersistentDataType.INTEGER)
        if(p.oldCursor.type != Material.AIR){
            if(mine.contains(p.inventory.type)){
                if(inv==null||inv==0){
                    p.isCancelled=true
                }
            }
        }
    }

    fun itemFrameClick(p: PlayerInteractEntityEvent){
        val tar = p.rightClicked.type
        val inv = p.player.inventory.itemInMainHand.itemMeta?.persistentDataContainer?.get(key, PersistentDataType.INTEGER)
        if(tar==EntityType.ITEM_FRAME){
            if(inv==null||inv==0){
                p.isCancelled = true
            }
        }
    }
}