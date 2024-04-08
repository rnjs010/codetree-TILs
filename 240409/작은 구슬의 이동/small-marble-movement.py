n, t = map(int, input().split())
r, c, d = input().split()
r, c = int(r), int(c)

dir = {
    'U': 0, 'R': 1, 'L': 2, 'D': 3
}
dx, dy = [-1, 0, 0, 1], [0, 1, -1, 0]

d_num = dir[d]
for _ in range(t):
    if d == 'U' or d == 'D':
        nr = r + dx[d_num]
        if not (0 < nr <= n):
            d_num = abs(d_num - 3)
        else:
            r = nr
    if d == 'R' or d == 'L':
        nc = c + dy[d_num]
        if not (0 < nc <= n):
            d_num = abs(d_num - 3)
        else:
            c = nc

print(r, c)