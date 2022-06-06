package calculator.domain

@JvmInline
value class Operand(val value: Int) {

    constructor(value: String) : this(value.toInt())

    init {
        require(value >= 0) { "음수[$value]를 사용할 수 없습니다." }
    }
}
