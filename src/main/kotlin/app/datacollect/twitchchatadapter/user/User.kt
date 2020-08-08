package app.datacollect.twitchchatadapter.user

data class User(val username: String, val token: String, var isLoggedIn: Boolean = false)
