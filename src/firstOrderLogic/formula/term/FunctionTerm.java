package firstOrderLogic.formula.term;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 函数项：表示函数应用
 * 格式：f(t1, t2, ..., tn)，其中f是函数符号，ti是项
 */
public class FunctionTerm extends Term {
    private final char functionSymbol;    // 函数符号（小写字母）
    private final List<Term> arguments;   // 参数列表
    private final int arity;              // 元数

    /**
     * 构造函数项
     */
    public FunctionTerm(char functionSymbol, List<Term> arguments) {
        if (!Character.isLowerCase(functionSymbol)) {
            throw new IllegalArgumentException("Function symbol must be lowercase letter: " + functionSymbol);
        }
        if (arguments == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }

        this.functionSymbol = functionSymbol;
        this.arguments = new ArrayList<>(arguments);
        this.arity = arguments.size();
    }

    public char getFunctionSymbol() {
        return functionSymbol;
    }

    public List<Term> getArguments() {
        return new ArrayList<>(arguments);
    }

    public int getArity() {
        return arity;
    }

    public Term getArgument(int index) {
        if (index < 0 || index >= arity) {
            throw new IndexOutOfBoundsException("Argument index out of bounds: " + index);
        }
        return arguments.get(index);
    }

    @Override
    public Set<Character> getVariables() {
        Set<Character> vars = new HashSet<>();
        for (Term arg : arguments) {
            vars.addAll(arg.getVariables());
        }
        return vars;
    }

    @Override
    public boolean containsVariable(char variable) {
        for (Term arg : arguments) {
            if (arg.containsVariable(variable)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Term substitute(Term term, char variable) {
        // 递归替换所有参数中的变量
        List<Term> newArguments = new ArrayList<>();
        for (Term arg : arguments) {
            newArguments.add(arg.substitute(term, variable));
        }
        return new FunctionTerm(functionSymbol, newArguments);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(functionSymbol);

        if (arity > 0) {
            sb.append('(');
            for (int i = 0; i < arity; i++) {
                if (i > 0) sb.append(", ");
                sb.append(arguments.get(i).toString());
            }
            sb.append(')');
        }

        return sb.toString();
    }

    @Override
    public String toLaTeXString() {
        StringBuilder sb = new StringBuilder();
        sb.append(functionSymbol);

        if (arity > 0) {
            sb.append('(');
            for (int i = 0; i < arity; i++) {
                if (i > 0) sb.append(", ");
                sb.append(arguments.get(i).toLaTeXString());
            }
            sb.append(')');
        }

        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        FunctionTerm that = (FunctionTerm) obj;

        if (functionSymbol != that.functionSymbol) return false;
        if (arity != that.arity) return false;

        for (int i = 0; i < arity; i++) {
            if (!arguments.get(i).equals(that.arguments.get(i))) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = Character.hashCode(functionSymbol);
        result = 31 * result + arity;
        for (Term arg : arguments) {
            result = 31 * result + arg.hashCode();
        }
        return result;
    }

    @Override
    public int getDepth() {
        int maxDepth = 0;
        for (Term arg : arguments) {
            maxDepth = Math.max(maxDepth, arg.getDepth());
        }
        return maxDepth + 1; // 函数项的深度 = 最大参数深度 + 1
    }

    @Override
    public Set<Character> getFunctionSymbols() {
        Set<Character> symbols = new HashSet<>();
        symbols.add(functionSymbol);

        for (Term arg : arguments) {
            symbols.addAll(arg.getFunctionSymbols());
        }

        return symbols;
    }

    @Override
    public Set<String> getConstantSymbols() {
        Set<String> constants = new HashSet<>();
        for (Term arg : arguments) {
            constants.addAll(arg.getConstantSymbols());
        }
        return constants;
    }

    @Override
    public Term clone() {
        List<Term> clonedArguments = new ArrayList<>();
        for (Term arg : arguments) {
            clonedArguments.add(arg.clone());
        }
        return new FunctionTerm(functionSymbol, clonedArguments);
    }

    /**
     * 获取函数项的复杂度（参数个数 + 嵌套深度）
     */
    public int getComplexity() {
        int complexity = arity;
        for (Term arg : arguments) {
            complexity += arg.getDepth();
        }
        return complexity;
    }

    /**
     * 判断函数项是否为常函数（不含变量）
     */
    public boolean isConstantFunction() {
        return getVariables().isEmpty();
    }

    /**
     * 获取函数项中所有子项（递归）
     */
    public List<Term> getAllSubterms() {
        List<Term> subterms = new ArrayList<>();

        // 添加当前函数项本身
        subterms.add(this);

        // 递归添加所有参数的子项
        for (Term arg : arguments) {
            if (arg instanceof FunctionTerm) {
                subterms.addAll(((FunctionTerm) arg).getAllSubterms());
            } else {
                subterms.add(arg);
            }
        }

        return subterms;
    }
}