package me.kizika.dice

import org.bukkit.ChatColor
import org.bukkit.NamespacedKey
import org.bukkit.NamespacedKey.BUKKIT

object Util {
    fun getColored(text: String): String{
        return ChatColor.translateAlternateColorCodes('&',text)
    }

    fun getNameSpaceKey(ky: String): NamespacedKey{
        @Suppress("DEPRECATION")
        return NamespacedKey(BUKKIT,ky)
    }
}