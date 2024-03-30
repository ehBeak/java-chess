# java-chess

체스 미션 저장소

# 기능 목록

## 기물 이동 규칙
| 왼쪽_위  | 위     | 오른쪽_위  |
|-------|-------|--------|
| 왼쪽    | *     | 오른쪽    |
| 왼쪽_아래 | 아래    | 오른쪽_아래 |

### 킹
- [x] 이동 방향은 `위`, `아래`, `왼쪽`, `오른쪽`, `왼쪽_위`, `오른쪽_위`, `왼쪽_아래`, `오른쪽_아래`이다.
- [x] 1칸씩 이동한다.
- [x] 이동할 칸에 같은 색 기물이 있다면 이동할 수 없다.
- [x] King은 잡히는 경우 경기가 끝난다.(점수가 없다)

### 퀸
- [x] 이동 방향은 `위`, `아래`, `왼쪽`, `오른쪽`, `왼쪽_위`, `오른쪽_위`, `왼쪽_아래`, `오른쪽_아래`이다.
- [x] n칸씩 이동한다.
- [x] 이동 칸에 같은 색 기물이 있다면 이동할 수 없다.
- [x] 이동할 방향에 기물이 있다면 해당 기물 전 칸까지만 이동할 수 있다.
- [x] 퀸의 점수는 9점이다.

### 비숍
- [x] 이동 방향은 `왼쪽_위`, `오른쪽_위`, `왼쪽_아래`, `오른쪽_아래`이다.
- [x] n칸씩 이동한다.
- [x] 이동할 방향에 같은 색 기물이 있다면 해당 기물 전 칸까지만 이동할 수 있다.
- [x] 이동할 방향에 같은 다른 색 기물이 있다면 해당 기물 칸까지만 이동할 수 있다.
- [x] 비숌의 점수는 3점이다.

### 나이트
- [x] 이동 방향은 `위 -> 오른쪽_위`, `위 -> 왼쪽_위`, `아래 -> 오른쪽_아래`, `아래 -> 왼쪽_아래`,
      `왼쪽 -> 왼쪽_위`, `왼쪽 -> 왼쪽_아래`, `오른쪽 -> 오른쪽_아래`, `오른쪽 -> 오른쪽_위`이다.
- [x] 1칸씩 이동한다.
- [x] 이동할 칸에 같은 색 기물이 있다면 이동할 수 없다.
- [x] 이동할 방향에 기물이 있어도 이동할 수 있다.
- [x] 나이트의 점수는 2.5점이다.

### 룩
- [x] 이동 방향은 `위`, `아래`, `왼쪽`, `오른쪽`이다.
- [x] n칸씩 이동한다.
- [x] 이동할 칸에 같은 색 기물이 있다면 이동할 수 없다.
- [x] 이동할 방향에 기물이 있다면 해당 기물 전 칸까지만 이동할 수 있다.
- [x] 룩의 점수는 5점이다.

### 폰
- [x] 흑의 이동 방향
  - [x] rank7에 위치하면 이동은 `아래 -> 아래`, `아래`이다.
  - [x] rank7에 위치하지 않는다면 이동은 `아래`이다.
  - [x] `왼쪽_아래`, `오른쪽_아래`에 백 기물이 존재하면 이동은 `왼쪽_아래`, `오른쪽_아래`다.

- [x] 백의 이동 방향
  - [x] rank2에 위치하면 이동은 `위 -> 위`, `위`이다.
  - [x] rank2에 위치하지 않는다면 이동은 `위`다.
  - [x] `왼쪽_위`, `오른쪽_위`에 흑 기물이 존재하면 이동은 `왼쪽_위`, `오른쪽_위`다.

- [x] 폰의 점수는 1점이다.
- [x] 같은 세로줄에 같은 색의 폰이 있는 경우 1점이 아닌 0.5점을 준다.

### 입력과 출력
- [x] 아래와 같이 시작 메시지를 출력한다.
```
> 체스 게임을 시작합니다.
> 게임 시작 : start
> 게임 종료 : end
> 게임 이동 : move source위치 target위치 - 예. move b2 b3
```
- [x] 게임 시작 여부를 입력 받는다.
  - [x] 입력이 start, end가 아닌 경우 예외가 발생한다.
  - [x] start를 입력 받으면 게임이 시작된다.
  - [x] end를 입력하면 게임이 종료된다.
  - [x] King이 잡혔을 때, 게임을 종료한다.(상대편 King이 잡히는 경우 게임에서 진다)
- [x] 이동 명령어를 입력 받는다.
  - [x] 형식이 `move A B`가 아니면 예외가 발생한다.
  - [x] rank가 `1-8`사이의 범위가 아니면 예외가 발생한다.
  - [x] file이 `a-h`사이의 범위가 아니면 예외가 발생한다.
- [x] "status" 명령을 입력받는다.
  - [x] 각 진영마다 현재 남아있는 말에 대한 점수를 출력한다.
    - [x] 한 번에 한 쪽의 점수만을 계산해야 한다.
  - [x] 어느 진영이 이겼는지 결과를 볼 수 있다.
- [ ] 흑백을 번갈아 이동시킨다.
  - [ ] 백의 선공으로 게임을 시작한다.
  - [ ] 바로 앞에서 움직인 기물의 색과 지금 움직이려는 기물의 색이 동일하다면 예외가 발생한다.
  - [ ] 기물이 이동할 수 없는 경우 예외가 발생한다.

- [ ] 예외가 발생하면 다시 입력 받는다.

- [x] 보드판을 출력한다.
  - [x] 흑은 대문자로, 백은 소문자로 출력한다.
  - [x] 빈 칸은 `.`으로 출력한다.
  - [x] 킹, 퀸, 룩, 비숍, 나이트, 폰을 각각 `K`, `Q`, `R`, `B`, `N`, `P`로 출력한다.

### 체스판
- [x] 초기 기물 위치를 각 기물 별로 초기화 한다.
  - [x] 흑은 rank7과 rank8에 위치한다.
  - [x] 백은 rank2와 rank1에 위치한다.
  - [x] 초기 기물의 위치는 아래와 같다.
```    
    RNBQKBNR
    PPPPPPPP
    ........
    ........
    ........
    ........
    pppppppp
    rnbqkbnr
```

- 체크 또는 체크메이트는 고려하지 않고 이동한다.

### 데이터베이스
- [ ] 애플리케이션을 재시작 하더라도 이전에 하던 체스 게임을 다시 시작할 수 있어야 한다.
- [ ] 기물을 움직일 때마다, 기물의 상태를 업데이트 시킨다.
- [ ] status 또는 end를 입력하면, 기물의 상태를 초기 상태로 업데이트 시킨다.

- DB를 적용할 때 도메인 객체의 변경을 최소화한다.

## 프로그램 실행 결과

```
> 체스 게임을 시작합니다.
> 게임 시작 : start
> 게임 종료 : end
> 게임 이동 : move source위치 target위치 - 예. move b2 b3
start
RNBQKBNR
PPPPPPPP
........
........
........
........
pppppppp
rnbqkbnr

move b2 b3
RNBQKBNR
PPPPPPPP
........
........
........
.p......
p.pppppp
rnbqkbnr
```

## 점수계산
```
.KR.....  8
P.PB....  7
.P..Q...  6
........  5
.....nq.  4
.....p.p  3
.....pp.  2
....rk..  1

abcdefgh
```
- 위와 같은 체스 판의 점수 결과는 검은색(대문자)은 20점. 흰색(소문자)은 19.5점이 된다.
- 검은색은 5(rook) + 3(bishop) + 9(queen) + 3(pawn) + 0(king) = 20점
- 흰색은 5(rook) + 2.5(knight) + 9(queen) + 3(pawn, 4개의 pawn이 있지만 세로로 있어 각 0.5이 된다.) + 0(king) = 19.5점
