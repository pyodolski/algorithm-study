public class HW3 {
    public static void main(String[] args) {
        // 사용자로부터 n과 k를 입력받기 위한 Scanner 객체 생성
        java.util.Scanner scannerX = new java.util.Scanner(System.in);
        System.out.print("정수 n과 k를 입력? ");
        int alpha = scannerX.nextInt(); // n에 해당하는 값(집합의 최대 원소)
        int beta = scannerX.nextInt();  // k에 해당하는 값(부분집합의 크기)
        scannerX.close(); // 입력 종료

        // 결과를 저장할 리스트(모든 부분집합(조합)을 담음)
        java.util.List<java.util.List<Integer>> magicBox = new java.util.ArrayList<>();
        // 현재 조합을 임시로 저장할 리스트
        java.util.List<Integer> tempList = new java.util.ArrayList<>();

        // 조합을 생성하는 재귀 함수 호출 (시작값 1)
        mysterious(alpha, beta, 1, tempList, magicBox);

        // 생성된 모든 조합을 출력 (문제 예시처럼 한 줄에 출력)
        for (java.util.List<Integer> element : magicBox) {
            System.out.print(element + " ");
        }
    }

    /**
     * mysterious: alpha(최대값)까지의 자연수 중에서 beta(뽑을 개수)개를 고르는 모든 조합을 basket에 담아,
     *             완성된 조합은 storage에 저장하는 재귀 함수
     * @param lim     n에 해당하는 값(집합의 최대 원소)
     * @param pick    k에 해당하는 값(부분집합의 크기)
     * @param cursor  현재 선택을 시작할 숫자(중복 방지 및 오름차순 조합)
     * @param basket  현재까지 선택한 숫자들을 담는 임시 리스트
     * @param storage 모든 조합을 저장할 리스트
     */
    private static void mysterious(int lim, int pick, int cursor, java.util.List<Integer> basket, java.util.List<java.util.List<Integer>> storage) {
        // base case: basket의 크기가 pick과 같으면 조합 완성
        if (basket.size() == pick) {
            // 완성된 조합을 storage에 추가(깊은 복사)
            storage.add(new java.util.ArrayList<>(basket));
            return;
        }
        // cursor부터 lim까지 반복하며 조합 생성
        for (int z = cursor; z <= lim; z++) {
            basket.add(z); // 현재 숫자 z를 basket에 추가
            // 다음 숫자를 선택하기 위해 재귀 호출 (z+1부터 시작)
            mysterious(lim, pick, z + 1, basket, storage);
            basket.remove(basket.size() - 1); // 마지막에 추가한 숫자 제거(백트래킹)
        }
    }
}
