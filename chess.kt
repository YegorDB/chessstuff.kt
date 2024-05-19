val COLUMN_SIGNS = mutableListOf('a'..'h').flatten()
val PIECE_COLOR_NAMES = mutableListOf("white", "black")
val SQUARE_COLOR_NAMES = mutableListOf("light", "dark")


class Board() {
    // main data is matrix 8x8
    // each item contains:
    // - square
    // - piece on square (or null)
    // - piece to peace interactions (attack, cover) direct and reverse [think about binds]
    // - piece to square interactions (move, xray) direct and reverse
}


data class Direction(val dx: Int, val dy: Int, val maxDistance: Int = 1)


abstract class Piece(
    open val color: Int,
    open val isFirstMove: Boolean = true
) {
    abstract fun moveDirections(): List<Direction>
    open fun attackDirections(): List<Direction>? = null
    val isWhiteColor = color == 0
    val colorName = PIECE_COLOR_NAMES[color]
}


class King(
    override val color: Int,
    override val isFirstMove: Boolean = true
) : Piece(color, isFirstMove) {
    override fun moveDirections(): List<Direction> {
        return mutableListOf(
            Direction(-1, -1),
            Direction(0, -1),
            Direction(1, -1),
            Direction(-1, 0),
            Direction(1, 0),
            Direction(-1, 1),
            Direction(0, 1),
            Direction(1, 1)
        )
    }
}


class Pawn(
    override val color: Int,
    override val isFirstMove: Boolean = true
) : Piece(color, isFirstMove) {
    override fun moveDirections(): List<Direction> {
        val dy = if (isWhiteColor) -1 else 1
        val maxDistance = if (isFirstMove) 2 else 1
        return mutableListOf(
            Direction(0, dy, maxDistance)
        )
    }

    override fun attackDirections(): List<Direction> {
        val dy = if (isWhiteColor) -1 else 1
        val maxDistance = if (isFirstMove) 2 else 1
        return mutableListOf(
            Direction(-1, dy, maxDistance),
            Direction(1, dy, maxDistance)
        )
    }
}


class Square(
    val x: Int,
    val y: Int
) {
    constructor(name: String) : this(
        name[0].toInt() - 97,
        8 - name[1].toString().toInt()
    ) { }

    val name = "${COLUMN_SIGNS[x]}${8 - y}"
    val isLightColor = x % 2 == y % 2
    val color = if (isLightColor) 0 else 1
    val colorName = SQUARE_COLOR_NAMES[color]
}

// TODO: to take en passant use special piece - link to pawn

fun main() {
    // val s1 = Square(2, 2)
    // println(s1.name)
    // println(s1.isLightColor)
    // println(s1.color)
    // println(s1.colorName)

    // val s2 = Square("b6")
    // println(s2.name)
    // println(s2.isLightColor)
    // println(s2.color)
    // println(s2.colorName)

    val k1 = King(0)
    println(k1.moveDirections())
    println(k1.attackDirections())
    println(k1.colorName)
    println(k1.isFirstMove)

    val p1 = Pawn(0)
    println(p1.moveDirections())
    println(p1.attackDirections())
    println(p1.colorName)
    println(p1.isFirstMove)

    val p2 = Pawn(1, false)
    println(p2.moveDirections())
    println(p2.attackDirections())
    println(p2.colorName)
    println(p2.isFirstMove)
}
