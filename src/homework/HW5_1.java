// 학번 22213489 이름 표주원
class HW5_1 {
    public int solution(int[][] routes) {
        int routes_len = routes.length;

        int i = 1;
        while (i < routes_len) {
            int j = i;
            while (j > 0 && routes[j - 1][0] > routes[j][0]) {
                int tmp_start = routes[j][0];
                int tmp_end = routes[j][1];
                routes[j][0] = routes[j - 1][0];
                routes[j][1] = routes[j - 1][1];
                routes[j - 1][0] = tmp_start;
                routes[j - 1][1] = tmp_end;
                j--;
            }
            i++;
        }

        int answer = 1;
        int cam_loc = routes[0][1];
        i = 1;
        while (i < routes_len) {
            if (cam_loc < routes[i][0]) {
                cam_loc = routes[i][1];
                answer++;
            } else {
                if (cam_loc > routes[i][1]) {
                    cam_loc = routes[i][1];
                }
            }
            i++;
        }

        return answer;
    }
}
