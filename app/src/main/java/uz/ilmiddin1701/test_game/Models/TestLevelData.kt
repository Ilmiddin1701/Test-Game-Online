package uz.ilmiddin1701.test_game.Models

class TestLevelData {
    var levelId: Int? = null
    var yourLevel: Int? = null
    var stars: Int? = null
    var tests: Int? = null
    var testCompleted: Int? = null
    var testCompletedTime: String? = null

    constructor()

    constructor(
        levelId: Int?,
        yourLevel: Int?,
        stars: Int?,
        tests: Int?,
        testCompleted: Int?,
        testCompletedTime: String?
    ) {
        this.levelId = levelId
        this.yourLevel = yourLevel
        this.stars = stars
        this.tests = tests
        this.testCompleted = testCompleted
        this.testCompletedTime = testCompletedTime
    }
}