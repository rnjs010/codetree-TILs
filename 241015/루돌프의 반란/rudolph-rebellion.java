import java.util.*;
import java.io.*;

public class Main {
    static final int MAX_N = 51, MAX_P = 31;
    static int N, M, P, C, D;
    static Map<Integer, Pair> pos = new HashMap<>();    // 산타 위치 정보
    static Pair rudolf = new Pair(0, 0);    // 루돌프 정보
    static int[][] board = new int[MAX_N][MAX_N];
    static boolean[] is_live = new boolean[MAX_P];
    static int[] points = new int[MAX_P];
    static int[] stun = new int[MAX_P];
    static final int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};

    static class Pair {
        int first, second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    static class Tuple implements Comparable<Tuple> {
        int first, second, third;

        Tuple(int first, int second, int third) {
            this.first = first;
            this.second = second;
            this.third = third;
        }

        @Override
        public int compareTo(Tuple other) {
            if (this.first != other.first) return Integer.compare(this.first, other.first);
            if (this.second != other.second) return Integer.compare(this.second, other.second);
            return Integer.compare(this.third, other.third);
        }
    }

    static boolean inrange(int x, int y) {
        return 1 <= x && x <= N && 1 <= y && y <= N;
    }

    // 가장 가까운 산타 찾기
    static int findClosestSanta() {
        int closestIdx = 0, closestX = 10000, closestY = 10000;
        for (int i = 1; i <= P; i++) {
            if (!is_live[i]) continue;

            Tuple currentBest = new Tuple((closestX - rudolf.first) * (closestX - rudolf.first) + (closestY - rudolf.second) * (closestY - rudolf.second), -closestX, -closestY);
            Tuple currentValue = new Tuple((pos.get(i).first - rudolf.first) * (pos.get(i).first - rudolf.first) + (pos.get(i).second - rudolf.second) * (pos.get(i).second - rudolf.second), -pos.get(i).first, -pos.get(i).second);

            if (currentValue.compareTo(currentBest) < 0) {
                closestX = pos.get(i).first;
                closestY = pos.get(i).second;
                closestIdx = i;
            }
        }
        return closestIdx;
    }

    // 루돌프 이동
    static void moveRudolf(int closestIdx, int t) {
        if (closestIdx == 0) return;

        int closestX = pos.get(closestIdx).first;
        int closestY = pos.get(closestIdx).second;
        Pair prevRudolf = new Pair(rudolf.first, rudolf.second);

        int moveX = Integer.compare(closestX, rudolf.first);
        int moveY = Integer.compare(closestY, rudolf.second);

        rudolf.first += moveX;
        rudolf.second += moveY;

        if (rudolf.first == closestX && rudolf.second == closestY) handleCollision(closestIdx, moveX, moveY, t);
        
        board[prevRudolf.first][prevRudolf.second] = 0;
        board[rudolf.first][rudolf.second] = -1;
    }

    // 충돌 처리
    static void handleCollision(int closestIdx, int moveX, int moveY, int t) {
        int firstX = rudolf.first + moveX * C;
        int firstY = rudolf.second + moveY * C;
        int lastX = firstX, lastY = firstY;

        stun[closestIdx] = t + 1;
        shiftSantaPositions(moveX, moveY, firstX, firstY, lastX, lastY);

        points[closestIdx] += C;
        pos.put(closestIdx, new Pair(firstX, firstY));
        if (inrange(firstX, firstY)) board[firstX][firstY] = closestIdx;
        else is_live[closestIdx] = false;
    }

    // 산타들 위치 이동
    static void shiftSantaPositions(int moveX, int moveY, int firstX, int firstY, int lastX, int lastY) {
        while (inrange(lastX, lastY) && board[lastX][lastY] > 0) {
            lastX += moveX;
            lastY += moveY;
        }
        while (!(lastX == firstX && lastY == firstY)) {
            int beforeX = lastX - moveX, beforeY = lastY - moveY;
            if (!inrange(beforeX, beforeY)) break;

            int idx = board[beforeX][beforeY];
            if (!inrange(lastX, lastY)) is_live[idx] = false;
            else {
                board[lastX][lastY] = board[beforeX][beforeY];
                pos.put(idx, new Pair(lastX, lastY));
            }
            lastX = beforeX;
            lastY = beforeY;
        }
    }

    // 각 산타들 이동
    static void moveSantas(int t) {
        for (int i = 1; i <= P; i++) {
            if (!is_live[i] || stun[i] >= t) continue;
            int moveDir = findBestMoveDirection(i);
            if (moveDir != -1) handleSantaMove(i, moveDir, t);
        }
    }

    // 산타의 최적 이동 방향 계산
    static int findBestMoveDirection(int i) {
    // 산타 i의 현재 위치와 루돌프 위치 간의 거리 계산
    int minDist = getDistance(pos.get(i).first, pos.get(i).second, rudolf.first, rudolf.second);
    int moveDir = -1;

    for (int dir = 0; dir < 4; dir++) {
        int nx = pos.get(i).first + dx[dir];
        int ny = pos.get(i).second + dy[dir];
        if (!inrange(nx, ny) || board[nx][ny] > 0) continue;

        int dist = getDistance(nx, ny, rudolf.first, rudolf.second);
        if (dist < minDist) {
            minDist = dist;
            moveDir = dir;
        }
    }
    return moveDir;
}

    // 산타 이동 처리
    static void handleSantaMove(int i, int moveDir, int t) {
        int nx = pos.get(i).first + dx[moveDir], ny = pos.get(i).second + dy[moveDir];

        if (nx == rudolf.first && ny == rudolf.second) {
            stun[i] = t + 1;
            handleCollision(i, -dx[moveDir], -dy[moveDir], t);
        } else {
            board[pos.get(i).first][pos.get(i).second] = 0;
            pos.put(i, new Pair(nx, ny));
            board[nx][ny] = i;
        }
    }

    static int getDistance(int x1, int y1, int x2, int y2) {
        return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        rudolf.first = Integer.parseInt(st.nextToken());
        rudolf.second = Integer.parseInt(st.nextToken());
        board[rudolf.first][rudolf.second] = -1;

        for (int i = 1; i <= P; i++) {
            st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken()), x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
            pos.put(id, new Pair(x, y));
            board[x][y] = id;
            is_live[id] = true;
        }

        // M 턴 동안 게임 진행
        for (int t = 1; t <= M; t++) {
            int closestIdx = findClosestSanta();
            moveRudolf(closestIdx, t);
            moveSantas(t);

            // 산타들 점수 업데이트
            for (int i = 1; i <= P; i++) if (is_live[i]) points[i]++;
        }

        // 결과 출력
        for (int i = 1; i <= P; i++) System.out.print(points[i] + " ");
    }
}