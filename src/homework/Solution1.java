// 학번 : 22213489, 이름 : 표주원
class Solution1 {
    public int solution(int[][] triangle) {
        int h = triangle.length;
        int[][] route = new int[h][h];

        route[0][0] = triangle[0][0];

        for (int i = 1; i < h; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    route[i][j] = route[i - 1][j] + triangle[i][j];
                } else if (j == i) {
                    route[i][j] = route[i - 1][j - 1] + triangle[i][j];
                } else {
                    route[i][j] = Math.max(route[i - 1][j - 1], route[i - 1][j]) + triangle[i][j];
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < h; i++) {
            answer = Math.max(answer, route[h - 1][i]);
        }

        return answer;
    }
}

