package calculator.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class ExpressionTest {

    @ParameterizedTest
    @MethodSource("expressionArguments")
    fun `Expression은 합계를 가진다`(expression: Expression, expected: Int) {
        val result = expression.sum

        assertThat(result).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun expressionArguments(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(Expression.ofInts(listOf(0)), 0),
                Arguments.of(Expression.ofInts(listOf(1)), 1),
                Arguments.of(Expression.ofInts(listOf(1, 2)), 3),
                Arguments.of(Expression.ofInts(listOf(1, 2, 3)), 6),
                Arguments.of(Expression.ofInts(listOf(100, 200)), 300),
                Arguments.of(Expression.ofInts(listOf(4, 3, 2, 1)), 10),
            )
        }
    }
}
