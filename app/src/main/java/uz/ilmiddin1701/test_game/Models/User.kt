package uz.ilmiddin1701.test_game.Models

import java.io.Serializable

class User : Serializable {
    var id: String? = null
    var firstName: String? = null
    var lastName: String? = null
    var userName: String? = null
    var password: String? = null
    var userImage: String? = null
    var allTestsCompleted20: Int? = null
    var allTestsCompletedTime20: Long? = null
    var allTestsCompleted30: Int? = null
    var allTestsCompletedTime30: Long? = null
    var allTestsCompleted40: Int? = null
    var allTestsCompletedTime40: Long? = null
    var allTestsCompleted50: Int? = null
    var allTestsCompletedTime50: Long? = null

    constructor(
        id: String?,
        firstName: String?,
        lastName: String?,
        userName: String?,
        password: String?,
        userImage: String?,
        allTestsCompleted20: Int?,
        allTestsCompletedTime20: Long?,
        allTestsCompleted30: Int?,
        allTestsCompletedTime30: Long?,
        allTestsCompleted40: Int?,
        allTestsCompletedTime40: Long,
        allTestsCompleted50: Int?,
        allTestsCompletedTime50: Long?
    ) {
        this.id = id
        this.firstName = firstName
        this.lastName = lastName
        this.userName = userName
        this.password = password
        this.userImage = userImage
        this.allTestsCompleted20 = allTestsCompleted20
        this.allTestsCompletedTime20 = allTestsCompletedTime20
        this.allTestsCompleted30 = allTestsCompleted30
        this.allTestsCompletedTime30 = allTestsCompletedTime30
        this.allTestsCompleted40 = allTestsCompleted40
        this.allTestsCompletedTime40 = allTestsCompletedTime40
        this.allTestsCompleted50 = allTestsCompleted50
        this.allTestsCompletedTime50 = allTestsCompletedTime50
    }

    constructor()
}
