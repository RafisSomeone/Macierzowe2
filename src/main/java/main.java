import generators.MatrixGenerator;
import multiplicators.*;

import java.rmi.MarshalException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class main {

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.run();
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
                long startTime = System.nanoTime();
                size = multiplicator.getResult().length;
                long stopTime = System.nanoTime();
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
