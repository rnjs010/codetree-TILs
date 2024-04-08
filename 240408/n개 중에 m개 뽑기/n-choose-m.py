k, n = map(int, input().split())

def product(n, arr, c_idx):
    if len(arr) == n:
        print(*arr)
        return
    for i in range(c_idx, k+1):
        product(n, arr + [i], i+1)

product(n, [], 1)