package method;

public class Spline {
    double[] a;
    double[] b;
    double[] c;
    double[] d;
    double[] xi;

    public Spline(double[] a, double[] b, double[] c, double[] d, double[] xi) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.xi = xi;
    }

    public double[] getA() {
        return a;
    }

    public double[] getXi() {
        return xi;
    }

    public double getF(double x, int i) {

        return this.a[i] + this.b[i] * (x - this.xi[i]) + this.c[i] * (x - this.xi[i]) * (x - this.xi[i]) + this.d[i] * (x - this.xi[i]) * (x - this.xi[i]) * (x - this.xi[i]);

    }
}
