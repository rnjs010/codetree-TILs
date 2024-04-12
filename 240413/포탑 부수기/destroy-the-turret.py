from collections import deque
dx1, dy1 = [0, 1, 0, -1], [1, 0, -1, 0]
dx2, dy2 = [0, 0, 1, 0, -1, 1, 1, -1, -1], [0, 1, 0, -1, 0, 1, -1, 1, -1]

N, M, K = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(N)]
live = []
turn = 0
record = [[0]*M for _ in range(N)]
is_active = [[0]*M for _ in range(N)]

visited = [[0]*M for _ in range(N)]
back_x = [[0]*M for _ in range(N)]
back_y = [[0]*M for _ in range(N)]

class poInfo:
    def __init__(self, x, y, p, rec):
        self.x = x
        self.y = y
        self.p = p
        self.rec = rec

def init():
    global turn
    turn += 1
    for i in range(N):
        for j in range(M):
            is_active[i][j] = 0
            visited[i][j] = 0


def select_attack():    # 공격자 선택
    live.sort(key= lambda x: (x.p, -x.rec, -(x.x+x.y), -x.y))   # 정렬
    # power, record, is_active 갱신
    po_attack = live[0]
    x, y = po_attack.x, po_attack.y
    board[x][y] += (N + M)
    record[x][y] = turn
    po_attack.p, po_attack.rec = board[x][y], record[x][y]
    is_active[x][y] = 1
    live[0] = po_attack

def light_attack(): # 레이저 공격
    sx, sy = live[0].x, live[0].y
    power = live[0].p
    ex, ey = live[-1].x, live[-1].y

    # BFS 이용한 최단거리 구하기
    can_attack = False
    q = deque()
    visited[sx][sy] = 1
    q.append((sx, sy))
    while q:
        x, y = q.popleft()
        if x == ex and y == ey:
            can_attack = True
            break

        for dx, dy in zip(dx1, dy1):
            nx = (x + dx + N) % N
            ny = (y + dy + M) % M
            if (not visited[nx][ny]) and board[nx][ny] != 0:
                visited[nx][ny] = 1
                back_x[nx][ny], back_y[nx][ny] = x, y
                q.append((nx, ny))

    # 공격 가능한 경우
    if can_attack:
        # 공격 대상 감소
        board[ex][ey] -= power
        if board[ex][ey] < 0 : board[ex][ey] = 0
        is_active[ex][ey] = 1
        # 역추적 경로 감소
        cx, cy = back_x[ex][ey], back_y[ex][ey]
        while not(cx == sx and cy == sy):
            board[cx][cy] -= (power // 2)
            if board[cx][cy] < 0 : board[cx][cy] = 0
            is_active[cx][cy] = 1
            next_cx, next_cy = back_x[cx][cy], back_y[cx][cy]
            cx, cy = next_cx, next_cy

    return can_attack


def bomb_attack():  # 폭탄 공격
    sx, sy = live[0].x, live[0].y
    power = live[0].p
    ex, ey = live[-1].x, live[-1].y

    for dx, dy in zip(dx2, dy2):
        nx = (ex + dx + N) % N
        ny = (ey + dy + M) % M
        if nx == sx and ny == sy:
            continue
        # 공격 대상 & 주변 포탑 감소
        if nx == ex and ny == ey:
            board[nx][ny] -= power
            if board[nx][ny] < 0: board[ex][ny] = 0
            is_active[nx][ny] = 1
        else:
            board[nx][ny] -= (power // 2)
            if board[nx][ny] < 0: board[nx][ny] = 0
            is_active[nx][ny] = 1


def add_power():    # 무관 포탑 + 1
    for i in range(N):
        for j in range(M):
            if (not is_active[i][j]) and board[i][j]:
                board[i][j] += 1


for _ in range(K):
    # 살아있는 포탑 리스트
    live = []
    for i in range(N):
        for j in range(M):
            if board[i][j]:
                new_p = poInfo(i, j, board[i][j], record[i][j])
                live.append(new_p)

    # 1개 이하면 종료
    if len(live) < 2: break

    init()
    select_attack() # 공격 포탑 증가
    can = light_attack()    # 레이저 공격
    if not can: bomb_attack()   # 폭탄 공격
    add_power() # 무관한 포탑 1 증가

ans = 0
for i in range(N):
    for j in range(M):
        ans = max(ans, board[i][j])

print(ans)