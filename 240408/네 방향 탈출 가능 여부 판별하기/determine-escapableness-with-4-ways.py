from collections import deque

n, m = map(int, input().split())
visited = [[0] * m for _ in range(n)]
board = [list(map(int, input().split())) for _ in range(n)]
dx, dy = [1, -1, 0, 0], [0, 0, 1, -1]
ans = 0

q = deque()
q.append((0, 0))
while q:
    x, y = q.popleft()
    visited[x][y] = 1
    for i in range(4):
        nx, ny = x + dx[i], y + dy[i]
        if nx == n - 1 and ny == m - 1:
            ans = 1
        if 0 <= nx < n and 0 <= ny < m:
            if not visited[nx][ny] and board[nx][ny] == 1:
                q.append((nx, ny))

print(ans)