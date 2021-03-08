package multiplicators;

public class IKJMultiplier extends IMultiplicator {
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
