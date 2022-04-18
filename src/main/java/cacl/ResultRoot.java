package cacl;

public class ResultRoot {
    public double[] getResult(Double[][] matrix, int size) {
        double[] result = new double[size];
        for (int i = 0; i < size; i++) {
            result[size - i - 1] = helpResult(matrix, size, result, size - i - 1);
        }
        return result;
    }

    public double helpResult(Double[][] matrix, int size, double[] result, int i) {
        double res = matrix[i][size];
        for (int k = 0; k < size; k++) {
            if (k != i) {
                res = res - matrix[i][k] * result[k];
            }
        }
        res = res / matrix[i][i];
        return res;
    }
}
