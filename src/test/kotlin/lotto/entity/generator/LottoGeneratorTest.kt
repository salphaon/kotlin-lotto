package lotto.entity.generator

import lotto.domain.entity.generator.LottoGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoGeneratorTest {

    @Test
    fun `생성된 로또 번호가 중복없이 6개 생성되는지 확인 합니다`() {
        assertThat(LottoGenerator.generatorUserLotto().distinct().size).isEqualTo(LOTTO_SIZE)
    }

    @Test
    fun `생성된 로또 번호가 로또 범위(1~45) 에 있는지 확인 합니다`() {
        LottoGenerator.generatorUserLotto().forEach { assertThat(it).isBetween(LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["1, 3, 10, 11, 43, 32", "2, 3, 4, 10, 15, 41"])
    fun `지난 주 로또 번호 입력시 입력한 번호가 생성되는지 확인한다`(lottoNumber: String) {
        val creatLottoNumber = LottoGenerator.generatorWinningLotto(lottoNumber)
        val inputLottoNumber = lottoNumber.split(",").map { it.trim().toInt() }.sorted().toList()
        checkSameWinningLottoNumber(creatLottoNumber, inputLottoNumber)
    }

    @ParameterizedTest
    @ValueSource(strings = ["-1, 3, 10, 20 , 43, 45", "1, 32, 38, 41, 44, 50"])
    fun `지난 주 로또 번호 입력시 (1~45) 에 포함 되어 있지 않을 경우 IllegalArgumentException 예외가 발생한다`(lottoNumber: String) {
        assertThrows<IllegalArgumentException> { LottoGenerator.generatorWinningLotto(lottoNumber) }
    }

    private fun checkSameWinningLottoNumber(createLottoNumber: List<Int>, inputLottoNumber: List<Int>) {
        for (index in 0 until LOTTO_SIZE) {
            assertThat(createLottoNumber[index]).isEqualTo(inputLottoNumber[index])
        }
    }

    companion object {
        private const val LOTTO_SIZE = 6
        private const val LOTTO_MIN_NUMBER = 1
        private const val LOTTO_MAX_NUMBER = 45
    }
}