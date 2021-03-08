import generators.MatrixGenerator;
import multiplicators.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class main {

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.run();
    }

    public static void print(double[][] matrix){
        System.out.print("[");
        for (int i = 0; i < matrix.length; i++){
            System.out.print("[");
            for (int j = 0; j < matrix[0].length; j++){
                System.out.printf(String.format(Locale.US, "%.10f", matrix[i][j]));
                if (j != matrix[0].length - 1){
                    System.out.print(", ");
                }
            }
            System.out.print("]");
            if (i != matrix.length - 1){
                System.out.print(", ");
            }
        }
        System.out.print("]");
        System.out.println();
    }
}


class Calculator {

    public void run() {
        MatrixGenerator generator = new MatrixGenerator();
        List<Integer> integerList = List.of(10,100,1000);
        List<IMultiplicator> multiplicatorList = new ArrayList<>();
        for(int size : integerList){
            double[][] matrix1 = generator.generate(size);
            double[][] matrix2 = generator.generate(size);
            multiplicatorList.add(new IJKMultiplier(matrix1,matrix2));
            multiplicatorList.add(new IKJMultiplier(matrix1,matrix2));
            multiplicatorList.add(new JIKMultiplier(matrix1,matrix2));
            multiplicatorList.add(new JKIMultiplier(matrix1,matrix2));
            multiplicatorList.add(new KIJMultiplier(matrix1,matrix2));
            multiplicatorList.add(new KJIMultiplier(matrix1,matrix2));
        }

        for (IMultiplicator multiplicator : multiplicatorList) {
            List<Long> results = new ArrayList<>();
            int size = 0;
            for (int i = 0; i < 10; i++) {
                long startTime = System.currentTimeMillis();
                size = multiplicator.getResult().length;
                long stopTime = System.currentTimeMillis();
                Long time = stopTime - startTime;
                results.add(time);
            }
            Long averageResult = average(results);
            String description = multiplicator.getDescription();
            System.out.println("size "+ size +" "+description + ": " + averageResult);
        }
    }

    public Long average(List<Long> results) {
        Long sum = 0L;
        if (!results.isEmpty()) {
            for (Long result : results) {
                sum += result;
            }
            return sum / results.size();
        }
        return sum;
    }
}
