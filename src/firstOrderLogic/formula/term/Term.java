package firstOrderLogic.formula.term;

import java.util.HashSet;
import java.util.Set;

/**
 * 一阶逻辑中项（Term）的抽象基类
 * 项包括：变量、常量、函数项
 */
public abstract class Term {

    /**
     * 获取项中包含的所有变量
     */
    public abstract Set<Character> getVariables();

    /**
     * 判断项是否包含指定变量
     */
    public abstract boolean containsVariable(char variable);

    /**
     * 替换项中的变量
     * @param term 要替换成的项
     * @param variable 被替换的变量
     * @return 替换后的新项
     */
    public abstract Term substitute(Term term, char variable);

    /**
     * 获取项的字符串表示（用于调试）
     */
    public abstract String toString();

    /**
     * 获取项的LaTeX表示
     */
    public abstract String toLaTeXString();

    /**
     * 判断两个项在语法上是否相等
     */
    public abstract boolean equals(Object obj);

    /**
     * 计算项的哈希值
     */
    public abstract int hashCode();

    /**
     * 判断项是否为变量项
     */
    public boolean isVariableTerm() {
        return this instanceof VariableTerm;
    }

    /**
     * 判断项是否为常量项
     */
    public boolean isConstantTerm() {
        return this instanceof ConstantTerm;
    }

    /**
     * 判断项是否为函数项
     */
    public boolean isFunctionTerm() {
        return this instanceof FunctionTerm;
    }

    /**
     * 获取项的深度（递归深度）
     */
    public abstract int getDepth();

    /**
     * 获取项中所有函数符号
     */
    public abstract Set<Character> getFunctionSymbols();

    /**
     * 获取项中所有常量符号
     */
    public abstract Set<String> getConstantSymbols();

    /**
     * 判断项是否包含函数
     */
    public boolean containsFunction() {
        return isFunctionTerm();
    }

    /**
     * 判断项是否包含指定函数符号
     */
    public boolean containsFunctionSymbol(char functionSymbol) {
        Set<Character> symbols = getFunctionSymbols();
        return symbols.contains(functionSymbol);
    }

    /**
     * 获取项中出现的所有符号（变量、常量、函数）
     */
    public Set<Object> getAllSymbols() {
        Set<Object> symbols = new HashSet<>();
        symbols.addAll(getVariables());
        symbols.addAll(getConstantSymbols());
        symbols.addAll(getFunctionSymbols());
        return symbols;
    }

    /**
     * 克隆项（深拷贝）
     */
    public abstract Term clone();
}
