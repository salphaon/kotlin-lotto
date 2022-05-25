package lotto

class LottoMachine(private val receipt: Receipt) {
    fun issue(): List<Lotto> {
        return List(receipt.lottoCount) { Lotto { generateNumbers(Lotto.LOTTO_NUMBER_COUNT) } }
    }

    fun verify(lastNumber: String, lottos: List<Lotto>): List<StatResult> {
        val checker = Checker(lastNumber)
        return Stat(lottos, checker).sumRecords
    }

    fun yields(resultList: List<StatResult>): Double {
        val totalProfit = resultList.sumOf { it.sum }
        return Profit(
            profit = totalProfit.toDouble(),
            payment = receipt.payment.toDouble()
        ).yields
    }
}