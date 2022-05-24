package lotto.domain

class LottoStore(private val userMoney: UserMoney, private val lottoMaker: LottoMaker = KoreanLottoNumberMaker()) {
    val lottoCount = userMoney.money / EACH_LOTTO_PRICE
    val boughtLottos = List(lottoCount) { lottoMaker.makeLottoNumbers() }
    val totalYieldRatio
        get() = totalMoney.toDouble() / userMoney.money
    private var totalMoney = 0

    fun getLottoResult(answer: List<Int>): List<LottoResult> {
        val lottoResult = listOf(
            LottoResult(LottoPrizeInfo.WIN3),
            LottoResult(LottoPrizeInfo.WIN4),
            LottoResult(LottoPrizeInfo.WIN5),
            LottoResult(LottoPrizeInfo.WIN6),
        )

        boughtLottos.forEach {
            val matchCount = getMatchCount(it.lottoNumbers, answer)

            val prizeInfo = LottoPrizeInfo.getPrizeInfo(matchCount) ?: return@forEach
            lottoResult.first { info -> info.prize == prizeInfo }.also { prize ->
                prize.increaseMatchCount()

                addPrizeMoney(prize.getPrizeMoney())
            }
        }

        return lottoResult
    }

    private fun addPrizeMoney(money: Int) {
        totalMoney += money
    }

    private fun getMatchCount(list1: List<Int>, list2: List<Int>): Int {
        return list1.intersect(list2.toSet()).size
    }

    companion object {
        private const val EACH_LOTTO_PRICE = 1000
    }
}