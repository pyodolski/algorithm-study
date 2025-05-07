// 학번 : 22213489 이름 : 표주원
class Solution {
    int first = 0;
    int secend = 0;

    public int[] soulution(int [][] arr) {
        int n = arr.length;
        cmpr(arr, 0, 0, n);
        return new int[]{first, secend};
    }
    private void cmpr(int[][] arr, int x, int y, int n) {
        if (samecheck(arr, x, y, n)) {
            if (arr[x][y] == 0) first++;
            else secend++;
            return;
        }
        cmpr(arr, x, y, n/2);
        cmpr(arr, x + n/2, y, n/2);
        cmpr(arr, x, y + n/2, n/2);
        cmpr(arr, x + n/2, y + n/2, n/2);
    }

    private boolean samecheck(int[][] arr, int x, int y, int n) {
        int num = arr[x][y];
        for (int i = x; i < x + n; i++) {
            for (int j = y; j < y + n; j++) {
                if (arr[i][j] != num) return false;
            }
        }
        return true;
    }
}
