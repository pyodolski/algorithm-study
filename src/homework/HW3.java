// 학번 : 22213489 이름 : 표주원
import java.util.*;

class HW4 {
    public static void main(String[] args) {
        Scanner scannerX = new Scanner(System.in);
        System.out.print("정수 n과 k를 입력? ");
        int alpha = scannerX.nextInt();
        int beta = scannerX.nextInt();
        scannerX.close();

        List<List<Integer>> magicBox = new ArrayList<>();
        List<Integer> tempList = new ArrayList<>();

        mysterious(alpha, beta, 1, tempList, magicBox);

        for (List<Integer> element : magicBox) {
            System.out.print(element + " ");
        }
    }

    private static void mysterious(int lim, int pick, int cursor, List<Integer> basket, List<List<Integer>> storage) {
        if (basket.size() == pick) {
            storage.add(new ArrayList<>(basket));
            return;
        }
        for (int z = cursor; z <= lim; z++) {
            basket.add(z);
            mysterious(lim, pick, z + 1, basket, storage);
            basket.remove(basket.size() - 1);
        }
    }
}
