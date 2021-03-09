package joined;
import java.util.*;

import java.util.Locale;

public class MainJoined {

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

class IJKMultiplier extends IMultiplicator {
    public IJKMultiplier(double[][] matrix1, double[][] matrix2) {
        super(matrix1, matrix2);
    }

    @Override
    public double[][] getResult() {
        double[][] result = new double[matrix1.length][matrix2[0].length];
        for (int i = 0; i < matrix1.length; i++){
            for (int j = 0; j < matrix2[0].length; j++){
                for (int k = 0; k < matrix2.length; k++){
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return result;
    }

    @Override
    public String getDescription() {
        return "IJK";
    }
}

class IKJMultiplier extends IMultiplicator {
    public IKJMultiplier(double[][] matrix1, double[][] matrix2) {
        super(matrix1, matrix2);
    }

    @Override
    public double[][] getResult() {
        double[][] result = new double[matrix1.length][matrix2[0].length];
        for (int i = 0; i < matrix1.length; i++){
            for (int k = 0; k < matrix2.length; k++){
                for (int j = 0; j < matrix2[0].length; j++){
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return result;
    }

    @Override
    public String getDescription() {
        return "IKJ";
    }
}

abstract class IMultiplicator {

    protected double[][] matrix1;
    protected double[][] matrix2;

    public abstract double[][] getResult();
    public abstract String getDescription();

    public IMultiplicator(double[][] matrix1, double[][] matrix2){
        int size = matrix1.length;
        this.matrix1 = new double[size][size];
        this.matrix2 = new double[size][size];

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                this.matrix1[i][j] = matrix1[i][j];
                this.matrix2[i][j] = matrix2[i][j];
            }
        }
    }
}

class JIKMultiplier extends IMultiplicator {
    public JIKMultiplier(double[][] matrix1, double[][] matrix2) {
        super(matrix1, matrix2);
    }

    @Override
    public double[][] getResult() {
        double[][] result = new double[matrix1.length][matrix2[0].length];
        for (int j = 0; j < matrix2[0].length; j++) {
            for (int i = 0; i < matrix1.length; i++) {
                for (int k = 0; k < matrix2.length; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return result;
    }

    @Override
    public String getDescription() {
        return "JIK";
    }
}

class JKIMultiplier extends IMultiplicator {
    public JKIMultiplier(double[][] matrix1, double[][] matrix2) {
        super(matrix1, matrix2);
    }

    @Override
    public double[][] getResult() {
        double[][] result = new double[matrix1.length][matrix2[0].length];
        for (int j = 0; j < matrix2[0].length; j++){
            for (int k = 0; k < matrix2.length; k++) {
                for (int i = 0; i < matrix1.length; i++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return result;
    }

    @Override
    public String getDescription() {
        return "JKI";
    }
}

class KIJMultiplier extends IMultiplicator {
    public KIJMultiplier(double[][] matrix1, double[][] matrix2) {
        super(matrix1, matrix2);
    }

    @Override
    public double[][] getResult() {
        double[][] result = new double[matrix1.length][matrix2[0].length];
        for (int k = 0; k < matrix2.length; k++){
            for (int i = 0; i < matrix1.length; i++){
                for (int j = 0; j < matrix2[0].length; j++){
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return result;
    }

    @Override
    public String getDescription() {
        return "KJI";
    }
}

class KJIMultiplier extends IMultiplicator {
    public KJIMultiplier(double[][] matrix1, double[][] matrix2) {
        super(matrix1, matrix2);
    }

    @Override
    public double[][] getResult() {
        double[][] result = new double[matrix1.length][matrix2[0].length];
        for (int k = 0; k < matrix2.length; k++){
            for (int j = 0; j < matrix2[0].length; j++){
                for (int i = 0; i < matrix1.length; i++){
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return result;
    }

    @Override
    public String getDescription() {
        return "KIJ";
    }
}

class MatrixGenerator {

    private Random random = new Random();

    public double[][] generate(int size){
        double[][] matrix = new double[size][size];
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                matrix[i][j] = random.nextDouble();
            }
        }
        return matrix;
    }
}

