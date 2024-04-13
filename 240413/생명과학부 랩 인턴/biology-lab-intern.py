BLANK = (-1, -1, -1)

# 변수 선언 및 입력
n, m, k = tuple(map(int, input().split()))
mold = [[BLANK for _ in range(m)] for _ in range(n)]
next_mold = [[BLANK for _ in range(m)] for _ in range(n)]
ans = 0


def collect(col):   # 곰팡이 채취
    global ans
    for row in range(n):
        if mold[row][col] != BLANK:
            mold_size, _, _ = mold[row][col]
            ans += mold_size
            mold[row][col] = BLANK
            break


def get_next_pos(x, y, dist, dir):
    dxs, dys = [-1, 1, 0, 0], [0, 0, 1, -1] # 상 하 우 좌

    # 한 칸씩 dist번 이동
    for _ in range(dist):
        nx, ny = x + dxs[dir], y + dys[dir]
        if 0 <= nx < n and 0 <= ny < m:    # 범위 확인
            x, y = nx, ny
        else:   # 방향을 반대로 바꾸고 한 칸 이동
            dir = dir + 1 if dir % 2 == 0 else dir - 1
            x, y = x + dxs[dir], y + dys[dir]

    return (x, y, dir)


def move(x, y): # (x, y)위치 곰팡이 이동
    size, dist, dir = mold[x][y]
    nx, ny, next_dir = get_next_pos(x, y, dist, dir)
    new_mold = (size, dist, next_dir)

    # 현재 곰팡이가 더 큰 경우에만 정보 적음
    if new_mold > next_mold[nx][ny]:
        next_mold[nx][ny] = new_mold


def move_all(): # 전체 곰팡이 이동
    # next_mold 초기화
    for i in range(n):
        for j in range(m):
            next_mold[i][j] = BLANK

    # 곰팡이 한번씩 이동
    for i in range(n):
        for j in range(m):
            if mold[i][j] != BLANK:
                move(i, j)

    # next_mold 값 mold에 옮기기
    for i in range(n):
        for j in range(m):
            mold[i][j] = next_mold[i][j]


def simulate(col):
    collect(col)    # 해당 열에 있는 곰팡이 채취
    move_all()  # 곰팡이 움직임


for _ in range(k):
    x, y, s, d, b = tuple(map(int, input().split()))

    # 위/아래 방향, 2n-2번 움직이면 제자리
    # 왼쪽/오른쪽 방향, 2m-2번 움직이면 제자리
    # s를 2n-2 or 2m-2로 나눴을 때의 나머지 만큼 움직이면 최적화 가능
    if d <= 2:
        s %= (2 * n - 2)
    else:
        s %= (2 * m - 2)

    # 곰팡이 크기 정보 먼저 넣어, 최대 곰팡이 쉽게 판단
    mold[x - 1][y - 1] = (b, s, d - 1)


# 한 칸씩 이동하면서 곰팡이 채취
for col in range(m):
    simulate(col)

print(ans)