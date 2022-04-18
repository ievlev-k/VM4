package method;

import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.Function;

public class CalculateFunctionInPoint {
    public double calculatingValue(String strFunction, Double arg){
        String strArg = arg.toString();
        Function function = new Function("f(x) = " + strFunction);
        Expression expression = new Expression("f(" + strArg + ")", function);
        return expression.calculate();
    }
}
