n, m, h, k = tuple(map(int, input().split()))

# 도망자 정보 관리 (도망자 방향만 저장)
hiders = [[[] for _ in range(n)] for _ in range(n)]
next_hiders = [[[] for _ in range(n)] for _ in range(n)]
tree = [[False] * n for _ in range(n)]

for _ in range(m):
    x, y, d = tuple(map(int, input().split()))
    hiders[x - 1][y - 1].append(d)

for _ in range(h):
    x, y = tuple(map(int, input().split()))
    tree[x - 1][y - 1] = True

# 정방향 기준, 현재 위치에서 술래가 움직여야 할 방향 관리
seeker_next_dir = [[0] * n for _ in range(n)]
# 역방향 기준, 현재 위치에서 술래가 움직여야 할 방향 관리
seeker_rev_dir = [[0] * n for _ in range(n)]

seeker_pos = (n // 2, n // 2)   # 술래 현재 위치
forward_facing = True   # 술래 움직이는 방향 정방향 True / 역방향 False
ans = 0


def initialize_seeker_path():   # 정중앙부터 끝까지 달팽이 경로 계산
    dxs, dys = [-1, 0, 1, 0], [0, 1, 0, -1] # 상우하좌
    # 시작 위치와 방향, 해당 방향으로 이동할 횟수 설정
    curr_x, curr_y = n // 2, n // 2
    move_dir, move_num = 0, 1

    while curr_x or curr_y:
        # move_num 만큼 이동
        for _ in range(move_num):
            seeker_next_dir[curr_x][curr_y] = move_dir  # 정방향 저장
            curr_x, curr_y = curr_x + dxs[move_dir], curr_y + dys[move_dir]
            seeker_rev_dir[curr_x][curr_y] = move_dir + 2 if move_dir < 2 else move_dir - 2 # 역방향 저장

            # 이동 중 (0, 0)으로 오게 되면, 이동 종료
            if not curr_x and not curr_y:
                break

        move_dir = (move_dir + 1) % 4   # 방향 전환
        # 현재 방향이 위/아래인 경우, 움직여야 할 횟수 1 증가
        if move_dir == 0 or move_dir == 2:
            move_num += 1

# ---------------------------------------------------------------------

def in_range(x, y):
    return 0 <= x < n and 0 <= y < n


def hider_move(x, y, move_dir):
    dxs, dys = [0, 0, 1, -1], [-1, 1, 0, 0] # 좌우하상

    nx, ny = x + dxs[move_dir], y + dys[move_dir]
    # Step 1. 만약 격자 벗어나면 방향 전환
    if not in_range(nx, ny):
        move_dir = 1 - move_dir if move_dir < 2 else 5 - move_dir
        nx, ny = x + dxs[move_dir], y + dys[move_dir]

    # Step 2. 다음 위치에 술래가 없다면 이동, 있다면 이동 안함
    if (nx, ny) != seeker_pos:
        next_hiders[nx][ny].append(move_dir)
    else:
        next_hiders[x][y].append(move_dir)


def dist_from_seeker(x, y):
    seeker_x, seeker_y = seeker_pos # 현재 술래 위치
    return abs(seeker_x - x) + abs(seeker_y - y)


def hider_move_all():
    # Step 1. next hider 초기화
    for i in range(n):
        for j in range(n):
            next_hiders[i][j] = []

    # Step 2. hider 이동
    for i in range(n):
        for j in range(n):
            if dist_from_seeker(i, j) <= 3: # 술래와 거리가 3 이내인 도망자만 이동
                for move_dir in hiders[i][j]:
                    hider_move(i, j, move_dir)
            else:   # 그렇지 않다면 현재 위치, 방향 그대로
                for move_dir in hiders[i][j]:
                    next_hiders[i][j].append(move_dir)

    # Step 3. next_hider 값 hiders에 옮기기
    for i in range(n):
        for j in range(n):
            hiders[i][j] = next_hiders[i][j]

# ---------------------------------------------------------------------

def get_seeker_dir():   # 현재 술래가 바라보는 방향 가져오기
    x, y = seeker_pos
    move_dir = 0
    if forward_facing:
        move_dir = seeker_next_dir[x][y]
    else:
        move_dir = seeker_rev_dir[x][y]
    return move_dir


def check_facing():
    global forward_facing
    # Case 1. 정방향으로 끝에 다다른 경우
    if seeker_pos == (0, 0) and forward_facing:
        forward_facing = False
    # Case 2. 역방향으로 끝에 다다른 경우
    if seeker_pos == (n // 2, n // 2) and not forward_facing:
        forward_facing = True


def seeker_move():  # 술래 이동
    global seeker_pos
    x, y = seeker_pos
    dxs, dys = [-1, 0, 1, 0], [0, 1, 0, -1] # 상우하좌
    move_dir = get_seeker_dir()
    seeker_pos = (x + dxs[move_dir], y + dys[move_dir])

    check_facing()    # 끝에 도달했다면 방향 전환

# ---------------------------------------------------------------------

def get_score(t):
    global ans
    dxs, dys = [-1, 0, 1, 0], [0, 1, 0, -1] # 상우하좌
    x, y = seeker_pos
    move_dir = get_seeker_dir()

    for dist in range(3):   # 3칸 확인
        nx, ny = x + dist * dxs[move_dir], y + dist * dys[move_dir]
        if in_range(nx, ny) and not tree[nx][ny]:
            ans += t * len(hiders[nx][ny])
            hiders[nx][ny] = [] # 도망자 사라짐


def simulate(t):
    hider_move_all()    # 도망자 이동
    seeker_move()   # 술래 이동
    get_score(t)    # 점수 갱신


initialize_seeker_path()    # 시작 전에 편의를 위해 술래 경로 미리 계산
for t in range(1, k + 1):   # k번에 걸쳐 술래잡기 진행
    simulate(t)

print(ans)