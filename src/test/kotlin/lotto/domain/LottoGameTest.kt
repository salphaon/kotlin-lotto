package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class LottoGameTest {

    @Test
    fun `LottoGame should be get matched number`() {
        val lottos = listOf(Lotto((1..6).map { LottoNumber.of(it) }))
        val winningNumbers = Lotto((1..6).map { LottoNumber.of(it) })

        val lottoGame = LottoGame(lottos, winningNumbers)
        assertThat(lottoGame.match(6)).isEqualTo(1)
    }
}