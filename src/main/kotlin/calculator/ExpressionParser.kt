package calculator

import calculator.domain.Expression
import calculator.domain.Operand

object ExpressionParser {

    private const val DELIMITERS = "[,:]"
    private const val CUSTOM_DELIMITER = "//(.)\n(.*)"

    fun parse(input: String?): Expression {
        if (input.isNullOrEmpty()) return Expression.ofInts(listOf(0))

        findCustomDelimiter(input)?.let {
            val customDelimiter = it.groupValues[1]
            val tokens = it.groupValues[2].split(customDelimiter)
            return tokens.toExpression()
        }

        val tokens = input.split(DELIMITERS.toRegex())
        return tokens.toExpression()
    }

    private fun findCustomDelimiter(input: String): MatchResult? {
        return CUSTOM_DELIMITER.toRegex().find(input)
    }

    private fun List<String>.toExpression(): Expression {
        return Expression(this.map(::Operand))
    }
}
