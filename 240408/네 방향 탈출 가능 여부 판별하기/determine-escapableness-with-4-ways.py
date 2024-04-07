from collections import deque

n, m = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(n)]
dx, dy = [1, -1, 0, 0], [0, 0, 1, -1]
ans = 0

q = deque()
q.append((0, 0))
while q:
    x, y = q.popleft()
    board[x][y] = 3
    for i in range(4):
        nx, ny = x + dx[i], y + dy[i]
        if 0 <= nx < n and 0 <= ny < m and board[nx][ny] == 1:
            q.append((nx, ny))

print(1 if board[n-1][m-1] == 3 else 0)