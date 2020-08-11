package lotto

import lotto.domain.LottoNumber
import lotto.domain.LottoTicket
import lotto.domain.WinningLottoTicket
import lotto.domain.generator.ManualLottoGenerator
import lotto.domain.selling.LottoExchanger
import lotto.domain.selling.Payment
import lotto.domain.selling.PaymentResult
import lotto.domain.selling.Seller
import lotto.view.InputView
import lotto.view.ResultView

object Application {
    private val seller = Seller()

    @JvmStatic
    fun main(args: Array<String>) {
        val paymentResult = inputUserPayment()
        var lottoTicket: LottoTicket?
        var winningLottoTicket: WinningLottoTicket?

        do {
            lottoTicket = inputWinningNumbers()
        } while (lottoTicket == null)

        do {
            winningLottoTicket = processWinningLotto(lottoTicket)
        } while (winningLottoTicket == null)

        val exchangeResult = LottoExchanger.exchange(paymentResult, winningLottoTicket)
        ResultView.printExchangeResult(exchangeResult)
    }

    private fun inputUserPayment(): PaymentResult {
        val payment = Payment(InputView.readMoney { seller.isAcceptable(it) }.toInt())
        val result = seller.processPayment(payment)
        ResultView.printPaymentResult(result)
        return result
    }

    private fun inputWinningNumbers() = try {
        ManualLottoGenerator(InputView.readWinningNumbers()).execute()
    } catch (e: IllegalArgumentException) {
        ResultView.printInvalidLottoNumbers()
        null
    }

    private fun processWinningLotto(lottoTicket: LottoTicket): WinningLottoTicket? = try {
        val bonus = LottoNumber(InputView.readBonusNumber())
        WinningLottoTicket(lottoTicket, bonus)
    } catch (e: IllegalArgumentException) {
        ResultView.printInvalidBonusNumber()
        null
    }
}
