class Query:
    def __init__(self, cmd, t, x, name, n):
        self.cmd = cmd
        self.t = t
        self.x = x
        self.name = name
        self.n = n


queries = []    # 명령들 관리
names = set()   # 등장한 사람 목록 관리
p_queries = {}  # 각 사람마다 주어진 초밥 명령만 관리
entry_time = {} # 각 사람마다 입장 시간 관리
position = {}   # 각 손님의 위치 관리
exit_time = {}  # 각 사람마다 퇴장 시간 관리

# 입력:
L, Q = map(int, input().split())
for _ in range(Q):
    command = input().split()
    cmd, t, x, n = -1, -1, -1, -1
    name = ""
    cmd = int(command[0])
    if cmd == 100:
        t, x, name = command[1:]
        t, x = map(int, [t, x])
    elif cmd == 200:
        t, x, name, n = command[1:]
        t, x, n = map(int, [t, x, n])
    else:
        t = int(command[1])

    queries.append(Query(cmd, t, x, name, n))

    # 사람별 주어진 초밥 목록 관리
    if cmd == 100:
        if name not in p_queries:
            p_queries[name] = []
        p_queries[name].append(Query(cmd, t, x, name, n))
    # 손님이 입장한 시간과 위치 관리
    elif cmd == 200:
        names.add(name)
        entry_time[name] = t
        position[name] = x


# 자신의 이름이 적힌 조합을 언제 먹게 되는지 계산해 해당 정보 Query 추가 (111)
for name in names:
    # 해당 사람의 퇴장 시간 관리
    # 이는 마지막으로 먹는 초밥 시간 중 가장 늦은 시간
    exit_time[name] = 0

    for q in p_queries[name]:
        # 만약 초밥이 사람이 등장하기 전에 미리 주어진 상황이라면
        time_to_removed = 0
        if q.t < entry_time[name]:
            # entry_time때의 스시 위치 구하기
            t_sushi_x = (q.x + (entry_time[name] - q.t)) % L
            # 몇 초가 더 지나야 만나는지 계산
            additionl_time = (position[name] - t_sushi_x + L) % L
            time_to_removed = entry_time[name] + additionl_time

        # 초밥이 사람이 등장한 이후에 주어졌다면
        else:
            # 몇 초가 더 지나야 만나는지 계산
            additionl_time = (position[name] - q.x + L) % L
            time_to_removed = q.t + additionl_time

        # 초밥이 사라지는 시간 중 가장 늦은 시간을 업데이트
        exit_time[name] = max(exit_time[name], time_to_removed)

        # 초밥이 사라지는 111번 쿼리를 추가
        queries.append(Query(111, time_to_removed, -1, name, -1))


# 초밥을 마지막으로 먹은 시간 t 계산해 그 사람이 t때 떠났다는 Query 추가 (222)
for name in names:
    queries.append(Query(222, exit_time[name], -1, name, -1))


# 전체 Query 시간 순 정렬, t가 일치 한다면 300이 가장 늦게 나오도록 cmd 순 오름차순 정렬
queries.sort(key=lambda q: (q.t, q.cmd))
people_num, sushi_num = 0, 0
for i in range(len(queries)):
    if queries[i].cmd == 100:
        sushi_num += 1
    elif queries[i].cmd == 111:
        sushi_num -= 1
    elif queries[i].cmd == 200:
        people_num += 1
    elif queries[i].cmd == 222:
        people_num -= 1
    else:
        print(people_num, sushi_num)