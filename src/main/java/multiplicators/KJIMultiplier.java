package multiplicators;

public class KJIMultiplier extends IMultiplicator {
    public KJIMultiplier(double[][] matrix1, double[][] matrix2) {
        super(matrix1, matrix2);
    }

    @Override
    public double[][] getResult() {
        return new double[0][];
    }

    @Override
    public String getDescription() {
        return null;
    }
}
