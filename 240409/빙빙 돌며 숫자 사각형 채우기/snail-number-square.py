n, m = map(int, input().split())

dx, dy = [0, 1, 0, -1], [1, 0, -1, 0]

x, y, d = 0, 0, 0
board = [[0]*m for _ in range(n)]
for i in range(1, n*m+1):
    board[x][y] = i
    nx, ny = x + dx[d], y + dy[d]
    if not(0 <= nx < n) or not(0 <= ny < m) or board[nx][ny] != 0:
        d = (d + 1) % 4
    x, y = x + dx[d], y + dy[d]

for i in board:
    print(*i)