n = int(input())
board = [list(map(int, input().split())) for _ in range(n)]
dx, dy = [1, -1, 0, 0], [0, 0, 1, -1]
ans = 0

for x in range(n):
    for y in range(n):
        cnt = 0
        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            if 0 <= nx < n and 0 <= ny < n and board[nx][ny] == 1:
                cnt += 1

        if cnt >= 3:
            ans += 1

print(ans)