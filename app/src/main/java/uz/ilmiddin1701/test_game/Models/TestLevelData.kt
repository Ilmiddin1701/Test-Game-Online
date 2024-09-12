package uz.ilmiddin1701.test_game.Models

import java.io.Serializable

class TestLevelData: Serializable {
    var levelId: Int? = null
    var stars: Int? = null
    var tests: Int? = null
    var testCompleted: Int? = null
    var testCompletedTime: Long? = null
    var isChecked: Boolean = false

    constructor()

    constructor(
        levelId: Int?,
        stars: Int?,
        tests: Int?,
        testCompleted: Int?,
        testCompletedTime: Long?,
        isChecked: Boolean
    ) {
        this.levelId = levelId
        this.stars = stars
        this.tests = tests
        this.testCompleted = testCompleted
        this.testCompletedTime = testCompletedTime
        this.isChecked = isChecked
    }
}