package br.com.kenzo.appodontoprev.api

class UserController {
    private val userRepository = UserRepository()

    fun createUser(user: br.com.kenzo.appodontoprev.User, callback: (String) -> Unit) {
        userRepository.createUser(user, callback)
    }

    fun getUser(userId: String, callback: (User?) -> Unit) {
        userRepository.getUser(userId, callback)
    }

    fun updateUser(userId: String, user: User, callback: (Boolean) -> Unit) {
        userRepository.updateUser(userId, user, callback)
    }

    fun deleteUser(userId: String, callback: (Boolean) -> Unit) {
        userRepository.deleteUser(userId, callback)
    }
}