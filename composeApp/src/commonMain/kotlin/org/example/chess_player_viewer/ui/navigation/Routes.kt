package org.example.chess_player_viewer.ui.navigation

import kotlinx.serialization.Serializable
import kotlin.reflect.KClass

@Serializable
object HomeRoute

@Serializable
object LeaderboardRoute

@Serializable
data class ProfileRoute(val username: String)

@Serializable
object StreamerRoute

fun String?.matchesRoute(routeClass: KClass<*>): Boolean {
    val prefix = routeClass.qualifiedName
    return this != null && prefix != null && this.startsWith(prefix)
}