# 숫자 야구 게임
## 진행 방법
* 숫자 야구 게임 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 과제를 제출한다.

## 과제 제출 과정
* [과제 제출 방법](https://github.com/next-step/nextstep-docs/tree/master/precourse)


## 요구 사항
- 컴퓨터의 값을 받는다(1-9의 중복되지 않는 세자리 숫자)
- [while]
    1. 사용자의 값을 입력 받는다(1-9의 중복되지 않는 세자리 숫자)
       - 값 검증(3자리인가?, 올바른 범위의 숫자인가?, 중복된 수가 없는가?) -> [Error]
    2. 결과 출력
       - Strike, ball 수 세기
    3. 정답이라면 종료 or 다시 하기 선택
       - 정답이 아니라면 다시 1로.


## 기능 목록
- [x] 1부터 9까지의 서로 다른 임의의 수 3개 생성. (NumberGenerator.createRandomNumber)
  - [x] 숫자 이외의 문자는 올 수 없다. (Balls.validateOnlyNumber)
  - [x] 3개의 숫자는 중복될 수 없다.(Balls.validateDuplicatedNumber)
  - [x] 1 to 9 까지의 숫자.
- [x] 컴퓨터 수 3자리와 플레이어 수 3자리를 비교할 수 있다. (Judgement.compare)
  - [x] 몇 개의 숫자가 같은지 알 수 있다. (correctCount)
  - [x] 특정 숫자가 특정 자리에 있는지 알 수 있다.
- [x] 같은 수가 같은 자리에 있으면 '스트라이크'.
- [x] 같은 수가 전혀 없으면 '낫싱' 이다. 
