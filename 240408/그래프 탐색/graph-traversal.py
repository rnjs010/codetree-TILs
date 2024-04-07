n, m = map(int, input().split())
graph = [[] for _ in range(n+1)]
visited = [0] * (n+1)
for _ in range(m):
    a, b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)

def dfs(v):
    visited[v] = 1
    for i in graph[v]:
        if not visited[i]:
            visited[i] = 1
            dfs(i)
    return

dfs(1)
print(visited.count(1)-1)