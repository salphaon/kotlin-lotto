package calculator.domain

data class Expression(val operands: List<Operand>) {

    val sum: Int
        get() {
            var result = 0

            operands.forEach {
                result += it.value
            }

            return result
        }

    companion object {
        fun ofInts(operands: List<Int>) = Expression(operands.map { Operand(it) })
    }
}
