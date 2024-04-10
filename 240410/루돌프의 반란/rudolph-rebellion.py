import heapq
n, m, p, c, d = map(int, input().split())

board = [[0]*(n+1) for _ in range(n+1)]
info = {}

ru_r, ru_c = map(int, input().split())
board[ru_r][ru_c] = -2  # 루돌프 -2
for i in range(p):
    san_num, san_r, san_c = map(int, input().split())
    board[san_r][san_c] = san_num
    info[san_num] = [1, san_r, san_c, 0, 0]  # 정상 1, 기절 0, 탈락 -1 | 좌표 r, c| 기절해제 | 점수

info = dict(sorted(info.items()))

ru_dx, ru_dy = [-1, -1, 0, 1, 1, 1, 0, -1], [0, 1, 1, 1, 0, -1, -1, -1] # 상 대 우 대 하 대 좌 대
san_dx, san_dy = [-1, 0, 0, 1], [0, 1, -1, 0]   # 상 우 좌 하

def dist(r1, c1, r2, c2):   # 거리 측정
    return (r1 - r2)**2 + (c1 - c2)**2

def dir_ru(r1, c1, r2, c2):    # 가까운 방향 찾기
    if c1 == c2:
        if r1 > r2: return 0
        else: return 4
    if r1 == r2:
        if c1 < c2: return 2
        else: return 6
    if c2 - c1 > 0:
        if r2 - r1 < 0: return 1
        else: return 3
    else:
        if r2 - r1 < 0: return 7
        else: return 5

def dir_san(r1, c1, r2, c2):    # 가까운 방향 찾기 (산타용) (산타 / 루돌프)
    if c1 == c2:
        if r1 > r2: return [0]
        else: return [3]
    if r1 == r2:
        if c1 < c2: return [1]
        else: return [2]
    if c2 - c1 > 0:
        if r2 - r1 < 0:
            U = dist(r1+san_dx[0], c1+san_dy[0], r2, c2)
            R = dist(r1+san_dx[1], c1+san_dy[1], r2, c2)
            if U <= R: return [0, 1]
            elif U > R: return [1, 0]
        else:
            R = dist(r1 + san_dx[1], c1 + san_dy[1], r2, c2)
            D = dist(r1 + san_dx[3], c1 + san_dy[3], r2, c2)
            if R <= D: return [1, 3]
            elif R > D: return [3, 1]
    else:
        if r2 - r1 < 0:
            U = dist(r1 + san_dx[0], c1 + san_dy[0], r2, c2)
            L = dist(r1 + san_dx[2], c1 + san_dy[2], r2, c2)
            if U <= L: return [0, 2]
            elif U > L: return [2, 0]
        else:
            D = dist(r1 + san_dx[3], c1 + san_dy[3], r2, c2)
            L = dist(r1 + san_dx[2], c1 + san_dy[2], r2, c2)
            if D <= L: return [3, 2]
            elif D > L: return [2, 3]

def in_range(r, c):
    if 0 < r <= n and 0 < c <= n:
        return True
    return False

def interaction(r, c, dx, dy, direction):
    other = board[r][c]
    for x in range(1, n + 1):
        next_r, next_c = r + (x * dx[direction]), c + (x * dy[direction])
        if in_range(next_r, next_c):
            if board[next_r][next_c] == 0:  # 빈 곳이면 갱신
                board[next_r][next_c] = other
                info[other][1], info[other][2] = next_r, next_c
                break
            else:
                info[other][1], info[other][2] = next_r, next_c
                board[next_r][next_c], other = other, board[next_r][next_c]
        else:  # 범위 밖 탈락
            info[other][0] = -1
            break


for z in range(m):
    pq = []
    for i in range(1, p+1):
        if info[i][0] != -1:
            heapq.heappush(pq, (dist(ru_r, ru_c, info[i][1], info[i][2]), -info[i][1], -info[i][2]))
        if info[i][0] == 0 and info[i][3] > 0:
            info[i][3] -= 1
            if info[i][3] == 0:
                info[i][0] = 1

    # 루돌프 이동 (가장 가까운 산타 방향으로, 여러명 r큰 c큰)
    if not pq:
        break

    board[ru_r][ru_c] = 0
    dir = dir_ru(ru_r, ru_c, -pq[0][1], -pq[0][2])
    ru_r += ru_dx[dir]
    ru_c += ru_dy[dir]
    
    # 루돌프가 산타 충돌
    if board[ru_r][ru_c] > 0:
        num = board[ru_r][ru_c]
        info[num][-1] += c
        info[num][1] += (c * ru_dx[dir])
        info[num][2] += (c * ru_dy[dir])
        if in_range(info[num][1], info[num][2]):    # 범위 확인
            info[num][0] = 0    # 기절
            info[num][3] = 2
            if board[info[num][1]][info[num][2]] > 0:   # 상호작용 발생
                interaction(info[num][1], info[num][2], ru_dx, ru_dy, dir)
            board[info[num][1]][info[num][2]] = num
        else:   # 범위 밖 탈락
            info[num][0] = -1
    board[ru_r][ru_c] = -2

    # 산타 이동
    for i in range(1, p+1):
        if info[i][0] == 1:
            d_li = dir_san(info[i][1], info[i][2], ru_r, ru_c)
            for k in d_li:
                new_san_r, new_san_c = info[i][1] + san_dx[k], info[i][2] + san_dy[k]
                if in_range(new_san_r, new_san_c):  # 범위 확인
                    if board[new_san_r][new_san_c] > 0: # 이미 산타 있음
                        continue
                    board[info[i][1]][info[i][2]] = 0
                    if board[new_san_r][new_san_c] == -2:   # 루돌프 충돌
                        info[i][-1] += d
                        new_san_r += (d * san_dx[abs(k-3)])
                        new_san_c += (d * san_dy[abs(k-3)])
                        if in_range(new_san_r, new_san_c):   # 범위 확인
                            info[i][0] = 0  # 기절
                            info[i][3] = 2
                            if board[new_san_r][new_san_c] > 0:  # 상호작용 발생
                                interaction(new_san_r, new_san_c, san_dx, san_dy, abs(k-3))
                            board[new_san_r][new_san_c] = i
                            info[i][1], info[i][2] = new_san_r, new_san_c
                            break
                        else:   # 범위 밖 탈락
                            info[i][0] = -1
                            break

                    elif board[new_san_r][new_san_c] == 0:    # 정상 이동
                        board[new_san_r][new_san_c] = i
                        info[i][1], info[i][2] = new_san_r, new_san_c
                        break

    # 살아 있는 산타 점수 +1
    for i in range(1, p+1):
        if info[i][0] != -1:
            info[i][-1] += 1


for i in range(1, p+1):
    print(info[i][-1], end=' ')