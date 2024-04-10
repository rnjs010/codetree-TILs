from collections import deque
L, Q = map(int, input().split())

table = deque([{} for _ in range(L)])
people = {}
T = 0

def eating():
    for key in people.keys():
        if key in table[people[key][0]]:
            if people[key][1] > table[people[key][0]][key]:
                people[key][1] -= table[people[key][0]][key]
            else:
                people[key][1] = 0
            table[people[key][0]].pop(key)


for _ in range(Q):
    q = list(input().split())
    time = int(q[1])
    table.rotate(time - T)
    T = time
    if q[0] == '100':
        pos, name = int(q[2]), q[3]
        if name not in table[pos]:
            table[pos][name] = 1
        else:
            table[pos][name] += 1

        if people: eating()

    elif q[0] == '200':
        pos, name, eat = int(q[2]), q[3], int(q[4])
        people[name] = [pos, eat]
        eating()

    else:
        if people: eating()

        p_cnt = 0
        for p in people.keys():
            if people[p][1] != 0: p_cnt += 1

        e_cnt = 0
        for i in range(L):
            if table[i]:
                for j in table[i].items():
                    e_cnt += j[1]

        print(p_cnt, e_cnt)