package multiplicators;

public class JKIMultiplier extends IMultiplicator {
    public JKIMultiplier(double[][] matrix1, double[][] matrix2) {
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
