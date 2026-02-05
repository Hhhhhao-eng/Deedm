package firstOrderLogic.formula.term;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 变量项：表示个体变量
 * 格式：x, y, z 或带下标的 x_1, x_2
 */
public class VariableTerm extends Term {
    private final char variable;      // 变量名（单个小写字母）
    private int subscript;      // 下标（0表示无下标）

    /**
     * 构造不带下标的变量项
     */
    public VariableTerm(char variable) {
        if (!Character.isLowerCase(variable)) {
            throw new IllegalArgumentException("Variable must be lowercase letter: " + variable);
        }
        this.variable = variable;
        this.subscript = 0;
    }

    /**
     * 构造带下标的变量项
     */
    public VariableTerm(char variable, int subscript) {
        this(variable);
        if (subscript < 0) {
            throw new IllegalArgumentException("Subscript must be non-negative: " + subscript);
        }
        this.subscript = subscript;
    }

    public char getVariable() {
        return variable;
    }

    public int getSubscript() {
        return subscript;
    }

    public boolean hasSubscript() {
        return subscript > 0;
    }

    @Override
    public Set<Character> getVariables() {
        Set<Character> vars = new HashSet<>();
        vars.add(variable);
        return vars;
    }

    @Override
    public boolean containsVariable(char variable) {
        return this.variable == variable;
    }

    @Override
    public Term substitute(Term term, char variable) {
        // 如果当前变量就是被替换的变量，则返回替换项
        if (this.variable == variable) {
            return term.clone();
        }
        // 否则返回当前变量项的副本
        return this.clone();
    }

    @Override
    public String toString() {
        if (hasSubscript()) {
            return variable + "_" + subscript;
        }
        return String.valueOf(variable);
    }

    @Override
    public String toLaTeXString() {
        if (hasSubscript()) {
            return variable + "_{" + subscript + "}";
        }
        return String.valueOf(variable);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        VariableTerm that = (VariableTerm) obj;
        return variable == that.variable && subscript == that.subscript;
    }

    @Override
    public int hashCode() {
        int result = Character.hashCode(variable);
        result = 31 * result + subscript;
        return result;
    }

    @Override
    public int getDepth() {
        return 1; // 变量的深度为1
    }

    @Override
    public Set<Character> getFunctionSymbols() {
        return Collections.emptySet(); // 变量中没有函数符号
    }

    @Override
    public Set<String> getConstantSymbols() {
        return Collections.emptySet(); // 变量中没有常量符号
    }

    @Override
    public Term clone() {
        if (hasSubscript()) {
            return new VariableTerm(variable, subscript);
        }
        return new VariableTerm(variable);
    }

    /**
     * 获取变量的完整标识符
     */
    public String getFullIdentifier() {
        if (hasSubscript()) {
            return variable + "_" + subscript;
        }
        return String.valueOf(variable);
    }

    /**
     * 判断是否为同一个变量（不考虑下标）
     */
    public boolean isSameVariable(VariableTerm other) {
        return this.variable == other.variable;
    }
}