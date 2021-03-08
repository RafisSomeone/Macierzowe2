package multiplicators;

public class JKIMultiplier extends IMultiplicator {
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
