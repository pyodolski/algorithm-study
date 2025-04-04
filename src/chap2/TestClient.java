package src.chap2;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestClient {
    public static void main(String[] args) {
        ST<String, Integer> st = new ST<>(); // 심볼 테이블 생성
        File file;

        final JFileChooser fc = new JFileChooser(); // 파일 선택기 생성

        // 파일 선택 대화상자
        if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
        } else {
            JOptionPane.showMessageDialog(null, "파일을 선택하세요.", "오류", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Scanner sc = null;

        try {
            // 선택된 파일을 스캐너로 읽기1
            sc = new Scanner(file);

            // 단어들을 키로 하여 ST에 차례대로 저장
            for (int i = 0; sc.hasNext(); i++) {
                String key = sc.next();
                st.put(key, i);
            }

            // 저장된 (키, 값) 쌍 출력
            for (String s : st.keys()) {
                System.out.println(s + " " + st.get(s));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (sc != null) sc.close(); // 스캐너 닫기
        }
    }
}
