package lotto.domain

class WinningLotto(
    val lotto: Lotto,
    val bonusNumber: LottoNumber
) {
    init {
        require(bonusNumber !in lotto.numbers) { "Bonus number[$bonusNumber] must not be in winning numbers." }
    }

    fun matchedBonus(lotto: Lotto) = bonusNumber in lotto.numbers

    fun rank(other: Lotto): Rank {
        return Rank.of(countOfMatch = lotto.countOfMatch(other), matchedBonus = matchedBonus(other))
    }
}