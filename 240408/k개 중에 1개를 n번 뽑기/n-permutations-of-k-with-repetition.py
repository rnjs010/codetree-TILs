k, n = map(int, input().split())

def product(n, arr):
    if len(arr) == n:
        print(*arr)
        return
    for i in range(1, k+1):
        product(n, arr + [i])

product(n, [])