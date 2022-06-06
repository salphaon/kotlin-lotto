# kotlin-lotto

## 1단계 - 문자열 덧셈 계산기
- [x] 쉼표(,) 또는 콜론(:)을 구분자로 가지는 문자열을 전달하는 경우 구분자를 기준으로 분리한 각 숫자의 합을 반환 (예: “” => 0, "1,2" => 3, "1,2,3" => 6, “1,2:3” => 6)
- [x] 앞의 기본 구분자(쉼표, 콜론) 외에 커스텀 구분자를 지정할 수 있다. 커스텀 구분자는 문자열 앞부분의 “//”와 “\n” 사이에 위치하는 문자를 커스텀 구분자로 사용한다. 예를 들어 “//;\n1;2;3”과 같이 값을 입력할 경우 커스텀 구분자는 세미콜론(;)이며, 결과 값은 6이 반환되어야 한다.
- [x] 문자열 계산기에 숫자 이외의 값 또는 음수를 전달하는 경우 RuntimeException 예외를 throw 한다.
   - [x] 빈 문자열이나 null은 0을 반환한다.
   - [x] 숫자 이외의 값을 전달하는 경우 RuntimeException을 throw 한다.
   - [x] 음수를 전달하는 경우 RuntimeException을 throw 한다.


## 2단계 - 로또(자동)
- [ ] 로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급해야 한다.
   - [ ] 로또 1개를 구매할 수 있다.
   - [ ] 로또 n개를 구매할 수 있다.
- [ ] 로또 1장의 가격은 1000원이다.
- [ ] 당첨을 판별할 수 있다.
   - [ ] 일치하는 갯수를 판별할 수 있다.
   - [ ] 수익률을 계산할 수 있다.
   - [ ] 당첨 통계를 출력한다.
