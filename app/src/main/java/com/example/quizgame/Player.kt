package com.example.quizgame

data class Player (var name: String = "", var score: Int = 0) {
    override fun toString() : String {
        return "{\"name\":\"" + this.name + "\",\"score\":\"" + this.score + "\"}"
    }

    fun toObject(stringObject: String): Player {
        var player: Player = Player()
        var attributes = stringObject.split(",")
        var nameAttribute = attributes[0]
        var scoreAttribute = attributes[1]

        player.name = nameAttribute.split(":")[1]
        player.name = player.name.substring(1, player.name.length -1)
        var tempScore = scoreAttribute.split(":")[1]
        player.score = tempScore.substring(1, tempScore.length -2).toInt()
        return player
    }
}