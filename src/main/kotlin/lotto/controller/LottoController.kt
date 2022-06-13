package lotto.controller

import lotto.domain.Lotto
import lotto.domain.LottoGame
import lotto.domain.LottoNumber
import lotto.service.InputParser
import lotto.service.LottoService
import lotto.view.LottoGameView
import lotto.view.LottoView

class LottoController(private val lottoService: LottoService) {

    fun start() {
        val purchaseAmount = getPurchaseAmount()
        val n = purchaseAmount / LOTTO_PRICE

        LottoGameView.printBuyAmount(n)

        val lottos = lottoService.buy(n)

        lottos.map { LottoView(it).print() }

        val winningLotto = getLastWinningLotto()
        val bonusNumber = getBonusNumber()
        val lottoGame = LottoGame(lottos, winningLotto, bonusNumber)

        LottoGameView.printWinningStats(lottoGame)
    }

    private fun getPurchaseAmount(): Int {
        LottoGameView.printPurchaseAmountInput()

        return InputParser.parsePurchaseAmount(readLine()!!)
    }

    private fun getLastWinningLotto(): Lotto {
        LottoGameView.printLastWinningNumbers()

        return Lotto(InputParser.parseWinningNumbers(readLine()!!))
    }

    private fun getBonusNumber(): LottoNumber {
        return InputParser.parseBonusNumber(readLine()!!)
    }

    companion object {
        private const val LOTTO_PRICE = 1_000
    }
}
