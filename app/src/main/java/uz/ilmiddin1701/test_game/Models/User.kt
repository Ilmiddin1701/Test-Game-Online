package uz.ilmiddin1701.test_game.Models

import java.io.Serializable

class User : Serializable {
    var id: String? = null
    var firstName: String? = null
    var lastName: String? = null
    var userName: String? = null
    var password: String? = null
    var userImage: Int? = null

    constructor(
        id: String?,
        firstName: String?,
        lastName: String?,
        userName: String?,
        password: String?,
        userImage: Int?
    ) {
        this.id = id
        this.firstName = firstName
        this.lastName = lastName
        this.userName = userName
        this.password = password
        this.userImage = userImage
    }

    constructor()

    override fun toString(): String {
        return "User(id=$id, firstName=$firstName, lastName=$lastName, userName=$userName, password=$password, userImage=$userImage)"
    }
}
