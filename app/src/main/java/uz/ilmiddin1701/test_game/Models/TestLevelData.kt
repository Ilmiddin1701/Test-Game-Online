package uz.ilmiddin1701.test_game.Models

import java.io.Serializable

class TestLevelData: Serializable {
    var levelId: Int? = null
    var yourLevel: Int? = null
    var stars: Int? = null
    var tests: Int? = null
    var testCompleted: Int? = null
    var testCompletedTime: String? = null
    var isChecked: Boolean = false

    constructor()

    constructor(
        levelId: Int?,
        yourLevel: Int?,
        stars: Int?,
        tests: Int?,
        testCompleted: Int?,
        testCompletedTime: String?,
        isChecked: Boolean
    ) {
        this.levelId = levelId
        this.yourLevel = yourLevel
        this.stars = stars
        this.tests = tests
        this.testCompleted = testCompleted
        this.testCompletedTime = testCompletedTime
        this.isChecked = isChecked
    }
}