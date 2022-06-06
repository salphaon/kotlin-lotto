package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class LottoGameTest {

    private lateinit var lottoGame: LottoGame

    @BeforeEach
    fun setUp() {
        val lottoNumbers = (1..45).map { LottoNumber.of(it) }
        lottoGame = LottoGame(lottoNumbers)
    }

    @Test
    fun `LottoGame has Lotto Number`() {
        assertThat(lottoGame.lottoNumbers).hasSize(45)
    }

    @Test
    fun `Buy 1 Lotto`() {
        val lotto = lottoGame.buy()

        assertThat(lotto.numbers).hasSize(6)
    }

    @ParameterizedTest
    @ValueSource(ints = [2, 3, 100])
    fun `Buy N Lotto Games`(n: Int) {
        val games = lottoGame.buy(n)

        assertThat(games).hasSize(n)
    }
}
