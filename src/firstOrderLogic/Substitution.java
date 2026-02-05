package firstOrderLogic;

import firstOrderLogic.formula.FirstOrderFormula;

public class Substitution {
    private char variable = 'p';
    private FirstOrderFormula formula = null;

    public Substitution(char variable, FirstOrderFormula formula) {
        this.variable = variable;
        this.formula = formula;
    }

    public char getVariable() {
        return variable;
    }

    public FirstOrderFormula getFormula() {
        return formula;
    }
}
