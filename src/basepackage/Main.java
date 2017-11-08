package basepackage;

import java.util.Arrays;

public class Main {

    private static class Left {

        private int sum = 0;

        int GetSum() {

            return sum;
        }

        private boolean[] booleanArray = null;

        boolean[] getBooleanArray() {

            return booleanArray;
        }

        Left(boolean[] booleanArray, int[] arr, int total) {

            this.booleanArray = booleanArray;
            this.sum = CountSum(arr);

            if (this.sum > total/2) {

                this.sum = 0;
            }
        }

        private int CountSum(int[] arr) {

            int sum = 0;

            for (int i = 0; i < arr.length; i++) {

                if (booleanArray[i]) {

                    sum += arr[i];
                }
            }

            return sum;
        }
    }

    private static class Right {

        private int sum = 0;

        int GetSum() {

            return sum;
        }

        private boolean[] binArr = null;

        Right(boolean[] binArr, int[] arr) {

            this.binArr = binArr;
            this.sum = CountSum(arr);
        }

        private int CountSum(int[] arr) {

            int sum = 0;

            for (int i = 0; i < arr.length; i++) {

                if (!binArr[i]) {

                    sum += arr[i];
                }
            }

            return sum;
        }
    }

    private static boolean[][] binaryInArray(int arrayLen) {

        int totalArrays = twoToPower(arrayLen) - 1;
        boolean[][] binaryArrays = new boolean[totalArrays][arrayLen];

        for (int i = 1; i < totalArrays; i++) {

            boolean add = true;
            for (int j = 0; j < arrayLen; j++) {

                if (add) {

                    if (!binaryArrays[i - 1][j]) {

                        binaryArrays[i][j] = true;
                        add = false;
                    }
                }
                else {

                    binaryArrays[i][j] = binaryArrays[i-1][j];
                }
            }
        }

        return binaryArrays;
    }

    private static int twoToPower(int power) {

        int result = 1;
        for (int i = 0; i < power; i++) {

            result *= 2;
        }

        return result;
    }

    private static boolean[][] binaryInArray(boolean[] left) {

        int arrayLen = left.length;
        int falseCount = 0;

        for (boolean boolEl : left
             ) {

            if (!boolEl)
                falseCount++;
        }


        int totalArrays = twoToPower(falseCount) - 1;
        boolean[][] binaryArrays = new boolean[totalArrays][arrayLen];

        // Write Left array to the first right one
        for (int j = 0; j < arrayLen; j++) {

            if (left[j]) {

                binaryArrays[0][j] = true;
            }
        }

        for (int i = 1; i < totalArrays; i++) {

            boolean add = true;
            for (int j = 0; j < arrayLen; j++) {

                if (left[j]) {

                    binaryArrays[i][j] = true;
                }
                else {

                    if (add) {

                        if (!binaryArrays[i-1][j]) {

                            add = false;
                            binaryArrays[i][j] = true;
                        }
                    }
                    else {

                        binaryArrays[i][j] = binaryArrays[i-1][j];
                    }
                }
            }
        }

        return binaryArrays;
    }


    // Driver Function to test above function
    public static void main(String args[])
    {

        int[] elements = {1, 2, 3, 1, 1, 8, 15, 7, 11};
        //int[] elements = {1, 2, 1};
        int numberOfEl = elements.length;

        int elementsSum = 0;
        for (int element : elements) {

            elementsSum += element;
        }

        boolean[][] leftBooleanArrays = binaryInArray(numberOfEl);

        Left[] lefts = new Left[leftBooleanArrays.length];

        for (int i = 0; i < lefts.length; i++) {

            lefts[i] = new Left(leftBooleanArrays[i], elements, elementsSum);
        }

        System.out.println(Arrays.toString(elements));

        // Bubble Sorting
        int top = lefts.length;
        for (int i = 0; i < lefts.length; i++) {
            for (int j = 1; j < top; j++) {

                if (lefts[j-1].GetSum() < lefts[j].GetSum()) {

                    Left helperLeft = lefts[j];
                    lefts[j] = lefts[j-1];
                    lefts[j-1] = helperLeft;
                }
            }

            top--;
        }

        for (Left leftEl : lefts
             ) {

            int leftSum = leftEl.GetSum();

            if (leftSum == 0) {

                System.out.println("SUM = 0");
                break;
            }

            // Calculate right
            boolean[][] rightBooleanArrays = binaryInArray(leftEl.getBooleanArray());

            for (boolean[] rightBooleanArray : rightBooleanArrays
                 ) {

                Right right = new Right(rightBooleanArray, elements);

                if (right.GetSum() == leftSum) {

                    System.out.println("SUM = " + leftSum);
                    System.exit(0);
                }
            }
        }
    }
}

