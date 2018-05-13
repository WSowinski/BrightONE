package brightonetask2;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Wojciech Sowiński
 */

public class BrightOneTask2 {

    public static void main(String[] args) {

        
        Scanner reader = new Scanner(System.in);
        int numberOfTests = 0;
        int[][] numbers = null;
        
        // Taking data from user part
        try {
            numberOfTests = reader.nextInt();
            if (numberOfTests <= 0) {
                System.out.println("Cant test 0 or less sequences");
                return;
            }

            numbers = new int[numberOfTests][];
            for (int i = 0; i < numberOfTests; i++) {
                int numberOfElements = reader.nextInt();
                numbers[i] = new int[numberOfElements];

                for (int j = 0; j < numberOfElements; j++) {
                    numbers[i][j] = reader.nextInt();
                }

            }
        } catch (InputMismatchException e) {
            System.out.println("Valid input data.");
            return;
        }

        // Checking every sequence
        for (int seq = 0; seq < numberOfTests; seq++) {
            boolean testFlag = false;

            // For every set of numbers count it left and right sum and check
            for (int pos = 0; pos < numbers[seq].length; pos++) {
                int leftSum = countLeftSum(numbers[seq], pos);
                int rightSum = countRightSum(numbers[seq], pos);

                if (leftSum == rightSum) {
                    testFlag = true;
                }
            }

            System.out.println((testFlag ? "Yes" : "No"));
        }

    }

    private static int countLeftSum(int[] array, int pos) {
        if (pos == 0) {
            return 0;
        }

        int leftSum = 0;
        for (int i = 0; i < pos; i++) {
            leftSum += array[i];
        }

        return leftSum;
    }

    private static int countRightSum(int[] array, int pos) {
        if (pos == array.length) {
            return 0;
        }

        int rightSum = 0;
        for (int i = pos + 1; i < array.length; i++) {
            rightSum += array[i];
        }

        return rightSum;
    }
}
