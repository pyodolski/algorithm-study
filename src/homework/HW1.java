// 학번 : 22213489 이름 : 표주원
package src.homework;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

class GasStationEntity {
    double x;
    double y;
    double distance;

    public GasStationEntity(double x, double y, double distance) {
        this.x = x;
        this.y = y;
        this.distance = distance;
    }
}

public class HW1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("입력 파일 이름? ");
        String fname = sc.nextLine();
        sc.close();

        try {
            sc = new Scanner(new File(fname));

            double currentX = sc.nextDouble();
            double currentY = sc.nextDouble();
            int k = sc.nextInt();
            int n = sc.nextInt();

            if (k != -1 && k > n) {
                System.out.println("오류: k는 n보다 클 수 없습니다. (k=" + k + ", n=" + n + ")");
                return;
            }

            GasStationEntity[] gasStations = new GasStationEntity[n];
            for (int i = 0; i < n; i++) {
                double x = sc.nextDouble();
                double y = sc.nextDouble();
                double dist = distance(currentX, currentY, x, y);
                gasStations[i] = new GasStationEntity(x, y, dist);
            }

            int count = (k == -1 || k > n) ? n : k;

            GasStationEntity[] basicWay = gasStations.clone();
            long start1 = System.currentTimeMillis();
            insertSort(basicWay);
            long end1 = System.currentTimeMillis();
            System.out.println("[기본적인 방법의 실행 시간이 길어질 경우 완료되어야 결과가 나타나기 때문에 개선된 방법의 출력도 늦어 질 수 있습니다]");
            System.out.println("[기본적인 방법]");
            System.out.println("k = " + k + "일 때의 실행시간 = " + (end1 - start1) + "ms");
            for (int i = 0; i < count; i++) {
                System.out.printf("%d: (%.6f, %.6f) 거리 = %.6f\n",
                        i, basicWay[i].x, basicWay[i].y, basicWay[i].distance);
            }

            GasStationEntity[] improvedWay;
            long start2 = System.currentTimeMillis();
            if (k == -1 || k >= n) {
                improvedWay = gasStations.clone();
                insertSort(improvedWay);
            } else {
                improvedWay = byHeap(gasStations, k);
            }
            long end2 = System.currentTimeMillis();

            System.out.println("[개선된 방법]");
            System.out.println("k = " + k + "일 때의 실행시간 = " + (end2 - start2) + "ms");
            for (int i = 0; i < improvedWay.length; i++) {
                System.out.printf("%d: (%.6f, %.6f) 거리 = %.6f\n",
                        i, improvedWay[i].x, improvedWay[i].y, improvedWay[i].distance);
            }

        } catch (IOException e) {
            System.out.println("파일 오류: " + e.getMessage());
        }

        if (sc != null) sc.close();
    }

    public static void insertSort(GasStationEntity[] arr) {
        for (int i = 1; i < arr.length; i++) {
            GasStationEntity key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j].distance > key.distance) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static GasStationEntity[] byHeap(GasStationEntity[] arr, int k) {
        GasStationEntity[] heap = new GasStationEntity[k];
        System.arraycopy(arr, 0, heap, 0, k);
        buildMaxHeap(heap, k);

        for (int i = k; i < arr.length; i++) {
            if (arr[i].distance < heap[0].distance) {
                heap[0] = arr[i];
                maxHeap(heap, 0, k);
            }
        }

        insertSort(heap);
        return heap;
    }

    public static void buildMaxHeap(GasStationEntity[] heap, int size) {
        for (int i = size / 2 - 1; i >= 0; i--) {
            maxHeap(heap, i, size);
        }
    }

    public static void maxHeap(GasStationEntity[] heap, int i, int size) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < size && heap[left].distance > heap[largest].distance) {
            largest = left;
        }
        if (right < size && heap[right].distance > heap[largest].distance) {
            largest = right;
        }

        if (largest != i) {
            GasStationEntity temp = heap[i];
            heap[i] = heap[largest];
            heap[largest] = temp;
            maxHeap(heap, largest, size);
        }
    }

    public static double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }
}
