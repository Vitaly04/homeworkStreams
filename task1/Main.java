package homework.streams.task1;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(1, 2, 5, 16, -1, -2, 0, 32, 3, 5, 8, 23, 4);
        int count = 0;

        for (Integer value : intList) {
            if (value > 0 && value % 2 == 0) {
                count++;
            }
        }

        Integer[] resultArray = new Integer[count];
        count = 0;

            for (Integer integer: intList) {
                if (integer > 0 && integer % 2 == 0) resultArray[count++] = integer;
            }

        Arrays.sort(resultArray);
        System.out.println(Arrays.toString(resultArray));
    }
}
