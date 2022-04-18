package cacl;

public class MakeTriangularMatrix {
    double determinant = 1;
    int swapping = 1;

    public Double[][] getTriangularMatrix(Double[][] matrix, int size) {
        Double[][] triangularMatrix = matrix;
        for (int indexCurrentString = 0; indexCurrentString < size; indexCurrentString++) {
            triangularMatrix = columnSwapping(triangularMatrix, indexCurrentString, size);
            triangularMatrix = zeroingColumn(triangularMatrix, indexCurrentString, size);
        }

        determinant = findDeterminant(triangularMatrix, size) * this.swapping;

        return triangularMatrix;
    }

    private Double[][] columnSwapping(Double[][] matrix, int numberString, int matrixSize) {
        Double[][] returnMatrix = matrix;

        int tmpNumberString = numberString;
        Double[] tmpString;
        Double firstElement = matrix[numberString][numberString];
        if (firstElement == 0) {
            while (firstElement == 0 && tmpNumberString != matrixSize - 1) {
                tmpString = matrix[numberString];
                matrix[numberString] = matrix[tmpNumberString + 1];
                matrix[tmpNumberString + 1] = tmpString;
                firstElement = returnMatrix[numberString][numberString];
                ++tmpNumberString;
                this.swapping = this.swapping * (-1);
            }
        }
        return returnMatrix;
    }

    private Double[][] zeroingColumn(Double[][] matrix, int numberColumn, int matrixSize) {
        Double[][] resultMatrix = matrix;
        for (int i = numberColumn + 1; i < matrixSize; i++) {
            if (matrix[numberColumn][numberColumn] == 0) {
                break;
            }
            double coefficient = (-1) * matrix[i][numberColumn] / matrix[numberColumn][numberColumn];
            for (int j = 0; j < matrixSize + 1; j++) {
                resultMatrix[i][j] = resultMatrix[i][j] + matrix[numberColumn][j] * coefficient;
            }
        }
        return resultMatrix;
    }

    private Double findDeterminant(Double[][] matrix, double size) {
        double determinant = 1;
        for (int i = 0; i < size; i++) {
            determinant = determinant * matrix[i][i];
        }
        return determinant;
    }

    public double getDeterminant() {
        return this.determinant;
    }
}
