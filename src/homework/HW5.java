// 학번 : 22213489, 이름 : 표주원
class HW5 {
    public String solution(String number, int k) {
        String answer = "";

        int number_len = number.length();
        char[] retrun_string = new char[number_len];
        int i = 0;
        int j = 0;

        while (j < number_len) {
            char c = number.charAt(j);

            while (i > 0 && k > 0 && retrun_string[i - 1] < c) {
                i--;
                k--;
            }

            retrun_string[i] = c;
            i++;
            j++;
        }

        int return_len = i - k;
        int idx = 0;
        while (idx < return_len) {
            answer += retrun_string[idx];
            idx++;
        }

        return answer;
    }
}
