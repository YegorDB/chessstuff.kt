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

data class Direction(
    val dx: Int,
    val dy: Int,
    val maxDistance: Int = 1
)

abstract class Piece(
    open val color: Int,
    open var isFirstMove: Boolean
) {
    abstract fun moveDirections(): List<Direction>
    open fun attackDirections(): List<Direction>? = null
    fun isWhiteColor() = color == 0
    fun colorName() = PIECE_COLOR_NAMES[color]
}

class King(
    override val color: Int,
    override var isFirstMove: Boolean = true
) : Piece(color, isFirstMove) {
    override fun moveDirections(): List<Direction> {
        return listOf(
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
    override var isFirstMove: Boolean = true
) : Piece(color, isFirstMove) {
    override fun moveDirections(): List<Direction> {
        val dy = if (isWhiteColor()) -1 else 1
        val maxDistance = if (isFirstMove) 2 else 1
        return listOf(
            Direction(0, dy, maxDistance)
        )
    }

    override fun attackDirections(): List<Direction> {
        val dy = if (isWhiteColor()) -1 else 1
        return listOf(
            Direction(-1, dy),
            Direction(1, dy)
        )
    }
}

class Rook(
    override val color: Int,
    override var isFirstMove: Boolean = true
) : Piece(color, isFirstMove) {
    override fun moveDirections(): List<Direction> {
        return listOf(
            Direction(0, -1, 7),
            Direction(-1, 0, 7),
            Direction(1, 0, 7),
            Direction(0, 1, 7)
        )
    }
}

class Knight(
    override val color: Int,
    override var isFirstMove: Boolean = true
) : Piece(color, isFirstMove) {
    override fun moveDirections(): List<Direction> {
        return listOf(
            Direction(-1, -2),
            Direction(1, -2),
            Direction(-2, -1),
            Direction(2, -1),
            Direction(-2, 1),
            Direction(2, 1),
            Direction(-1, 2),
            Direction(1, 2)
        )
    }
}

class Bishop(
    override val color: Int,
    override var isFirstMove: Boolean = true
) : Piece(color, isFirstMove) {
    override fun moveDirections(): List<Direction> {
        return listOf(
            Direction(-1, -1, 7),
            Direction(1, -1, 7),
            Direction(-1, 1, 7),
            Direction(1, 1, 7)
        )
    }
}

class Queen(
    override val color: Int,
    override var isFirstMove: Boolean = true
) : Piece(color, isFirstMove) {
    override fun moveDirections(): List<Direction> {
        return listOf(
            Direction(-1, -1, 7),
            Direction(0, -1, 7),
            Direction(1, -1, 7),
            Direction(-1, 0, 7),
            Direction(1, 0, 7),
            Direction(-1, 1, 7),
            Direction(0, 1, 7),
            Direction(1, 1, 7)
        )
    }
}

// Special piece to take en passant - link to pawn
data class PawnLink(
    val pawn: Pawn
)

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

fun main() {
    // val s1 = Square(2, 2)
    // println(s1.name)
    // println(s1.isLightColor)
    // println(s1.color)
    // println(s1.colorName())

    // val s2 = Square("b6")
    // println(s2.name)
    // println(s2.isLightColor)
    // println(s2.color)
    // println(s2.colorName())

    // val k1 = King(0)
    // println(k1.moveDirections())
    // println(k1.attackDirections())
    // println(k1.colorName())
    // println(k1.isFirstMove)

    // val p1 = Pawn(0)
    // println(p1.moveDirections())
    // println(p1.attackDirections())
    // println(p1.colorName())
    // println(p1.isFirstMove)

    // val p2 = Pawn(1, false)
    // println(p2.moveDirections())
    // println(p2.attackDirections())
    // println(p2.colorName())
    // println(p2.isFirstMove)

    // val pawnLink = PawnLink(p2)
    // println(pawnLink.pawn)

    // val r1 = Rook(1)
    // println(r1.moveDirections())
    // println(r1.attackDirections())
    // println(r1.colorName())
    // println(r1.isFirstMove)

    // val kn1 = Knight(1)
    // println(kn1.moveDirections())
    // println(kn1.attackDirections())
    // println(kn1.colorName())
    // println(kn1.isFirstMove)

    // val b1 = Bishop(0)
    // println(b1.moveDirections())
    // println(b1.attackDirections())
    // println(b1.color)
    // println(b1.isWhiteColor())
    // println(b1.colorName())
    // println(b1.isFirstMove)

    val q1 = Queen(1)
    println(q1.moveDirections())
    println(q1.attackDirections())
    println(q1.color)
    println(q1.isWhiteColor())
    println(q1.colorName())
    println(q1.isFirstMove)
}
