package method;

import cacl.MakeTriangularMatrix;
import cacl.ResultRoot;

public class MethodSpline {

    double[] x;
    double[] y;
    int n;

    public MethodSpline(double[] x, double y[], int n) {
        this.x = x;
        this.y = y;
        this.n = n;
    }

    public Spline getSpline() {

        double[] h = makeH(this.x);

        Double[][] argC = makeC(h, this.y);


        MakeTriangularMatrix makeTriangularMatrix = new MakeTriangularMatrix();

        argC = makeTriangularMatrix.getTriangularMatrix(argC, this.n);

        ResultRoot resultRoot = new ResultRoot();
        double[] c = resultRoot.getResult(argC, this.n);
        double[] b = makeB(h, c);
        double[] d = makeD(h, c);


        return new Spline(this.y, b, c, d, this.x);
    }

    private double[] makeH(double[] argX) {

        double[] resH = new double[argX.length - 1];

        for (int i = 0; i < resH.length; i++) {
            resH[i] = argX[i + 1] - argX[i];
        }

        return resH;
    }

    private Double[][] makeC(double[] argH, double[] argY) {
        Double[][] argC = new Double[argY.length][argY.length + 1];

        for (int i = 0; i < argY.length; i++) {
            for (int j = 0; j < argY.length + 1; j++) {
                argC[i][j] = 0.0;
            }
        }
        argC[0][0] = 1.0;
        argC[0][argH.length + 1] = 0.0;
        argC[argH.length][argH.length] = 1.0;
        argC[argH.length][argH.length + 1] = 0.0;
        for (int i = 1; i < argY.length - 1; i++) {
            argC[i][i - 1] = argH[i - 1];
            argC[i][i] = ((argH[i] + argH[i - 1]) * 2);
            argC[i][i + 1] = argH[i];
            argC[i][argH.length + 1] = 6 * (((argY[i + 1] - argY[i]) / argH[i]) - ((argY[i] - argY[i - 1]) / argH[i - 1]));
        }
        return argC;
    }

    private double[] makeB(double[] h, double[] c) {
        double[] b = new double[this.y.length];
        b[0] = 0;
        for (int i = 1; i < b.length; i++) {
            b[i] = (this.y[i] - this.y[i - 1]) / h[i - 1] + h[i - 1] * (2 * c[i] / 3 + 2 * c[i - 1] / 6);
        }
        return b;
    }

    private double[] makeD(double[] h, double[] c) {
        double[] d = new double[this.y.length];
        d[0] = 0;
        for (int i = 1; i < d.length; i++) {
            d[i] = (c[i] - c[i - 1]) / (h[i - 1] * 3);
        }
        return d;
    }


}
