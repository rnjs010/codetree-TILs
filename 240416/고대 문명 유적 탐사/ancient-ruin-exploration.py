from collections import deque

K, M = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(5)]
pack = deque([*map(int, input().split())])

dx, dy = [-1, 0, 1, 0], [0, 1, 0, -1]
visited = [[0] * 5 for _ in range(5)]
change_idx = []


def bfs(b_map, i, j):
    q = deque([(i, j)])
    visited[i][j] = 1
    cnt = 1
    while q:
        x, y = q.popleft()
        for d in range(4):
            nx, ny = x + dx[d], y + dy[d]
            if 0 <= nx < 5 and 0 <= ny < 5 and visited[nx][ny] == 0:
                if b_map[x][y] == b_map[nx][ny]:
                    cnt += 1
                    change_idx.append((nx, ny))
                    visited[nx][ny] = 1
                    q.append((nx, ny))

    if cnt == 2: change_idx.pop()
    return cnt if cnt >= 3 else 0


def get_pack(b_map):    # 얻을 수 있는 가치 총합
    # visited 초기화
    for i in range(5):
        for j in range(5):
            visited[i][j] = 0
    change_idx.clear()
    ans = 0
    for i in range(5):
        for j in range(5):
            if visited[i][j] == 0:
                get = bfs(b_map, i, j)
                if get != 0:
                    ans += get
                    change_idx.append((i, j))
    return ans


def rotate(sx, sy, case):
    temp_map = [[0] * 5 for _ in range(5)]
    for x in range(5):
        for y in range(5):
            temp_map[x][y] = board[x][y]

    for x in range(sx, sx + 3):
        for y in range(sy, sy + 3):
            ox, oy = x - sx, y - sy
            if case == 0: rx, ry = oy, 3 - ox - 1
            elif case == 1: rx, ry = 3 - ox - 1, 3 - oy - 1
            else: rx, ry = 3 - oy - 1, ox
            temp_map[rx + sx][ry + sy] = board[x][y]
    return get_pack(temp_map), temp_map


def max_get_map():
    max_val = 0
    new_map = [[0] * 5 for _ in range(5)]
    # temp_map = [[0] * 5 for _ in range(5)]
    for case in range(3):
        for j in range(0, 3):
            for i in range(0, 3):
                val, temp = rotate(i, j, case)
                if val > max_val:
                    max_val = val
                    for x in range(5):
                        for y in range(5):
                            new_map[x][y] = temp[x][y]

    if not max_val:
        return False
    else:
        for x in range(5):
            for y in range(5):
                board[x][y] = new_map[x][y]
        return True


def change_pack():
    change_idx.sort(key= lambda x: (x[1], -x[0]))
    for x, y in change_idx:
        board[x][y] = pack.popleft()


for _ in range(K):
    result = 0
    if max_get_map():
        while True:
            k = get_pack(board)
            if k == 0: break
            result += k
            change_pack()
        if result > 0:
            print(result, end=' ')
    else:
        break