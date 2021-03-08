package multiplicators;

public abstract class IMultiplicator {

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
