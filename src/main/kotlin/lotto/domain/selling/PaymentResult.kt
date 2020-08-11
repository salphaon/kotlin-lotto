package lotto.domain.selling

import lotto.domain.LottoTicket

data class PaymentResult(
    val money: Int,
    val lottoTickets: List<LottoTicket>,
    val change: Int
)
