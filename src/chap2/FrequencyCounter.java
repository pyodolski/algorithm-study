package src.chap2;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class FrequencyCounter {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("사용법: java FrequencyCounter <최소단어길이> <파일경로>");
            return;
        }

        int minlen = Integer.parseInt(args[0]); // 최소 단어 길이
        File file = new File(args[1]);

        ST<String, Integer> st = new ST<>(); // 심볼 테이블 생성

        long start = System.currentTimeMillis(); // 시간 측정 시작

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String word = scanner.next();
                if (word.length() < minlen) continue; // 너무 짧은 단어 무시

                if (!st.contains(word)) st.put(word, 1);
                else st.put(word, st.get(word) + 1);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("파일을 찾을 수 없습니다: " + file.getPath());
            return;
        }

        // 최대 빈도 단어 조사
        String maxKey = "";
        int maxValue = 0;
        for (String word : st.keys()) {
            int freq = st.get(word);
            if (freq > maxValue) {
                maxKey = word;
                maxValue = freq;
            }
        }

        long end = System.currentTimeMillis(); // 시간 측정 종료

        // 결과 출력
        System.out.println("가장 많이 등장한 단어: " + maxKey + " (" + maxValue + "회)");
        System.out.println("소요 시간 = " + (end - start) + "ms");
    }
}
