package generators;

import java.util.Random;

public class MatrixGenerator {

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
