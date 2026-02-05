package firstOrderLogic.formula.term;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 常量项：表示个体常量
 * 格式：a, b, c, ... 或 0, 1, 2, ...
 */
public class ConstantTerm extends Term {
    private final String constant;    // 常量名

    /**
     * 构造常量项
     */
    public ConstantTerm(String constant) {
        if (constant == null || constant.isEmpty()) {
            throw new IllegalArgumentException("Constant cannot be null or empty");
        }

        // 验证常量格式：单个小写字母或数字序列
        if (constant.length() == 1) {
            char ch = constant.charAt(0);
            if (!Character.isLowerCase(ch) && !Character.isDigit(ch)) {
                throw new IllegalArgumentException("Constant must be lowercase letter or digit: " + constant);
            }
        } else {
            // 多个字符的情况，只允许数字序列
            for (char ch : constant.toCharArray()) {
                if (!Character.isDigit(ch)) {
                    throw new IllegalArgumentException("Multi-character constant must be digits only: " + constant);
                }
            }
        }

        this.constant = constant;
    }

    public String getConstant() {
        return constant;
    }

    @Override
    public Set<Character> getVariables() {
        return Collections.emptySet(); // 常量中没有变量
    }

    @Override
    public boolean containsVariable(char variable) {
        return false; // 常量不包含任何变量
    }

    @Override
    public Term substitute(Term term, char variable) {
        // 常量中的变量不会被替换
        return this.clone();
    }

    @Override
    public String toString() {
        return constant;
    }

    @Override
    public String toLaTeXString() {
        return constant;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        ConstantTerm that = (ConstantTerm) obj;
        return constant.equals(that.constant);
    }

    @Override
    public int hashCode() {
        return constant.hashCode();
    }

    @Override
    public int getDepth() {
        return 1; // 常量的深度为1
    }

    @Override
    public Set<Character> getFunctionSymbols() {
        return Collections.emptySet(); // 常量中没有函数符号
    }

    @Override
    public Set<String> getConstantSymbols() {
        Set<String> constants = new HashSet<>();
        constants.add(constant);
        return constants;
    }

    @Override
    public Term clone() {
        return new ConstantTerm(constant);
    }

    /**
     * 判断常量是否为数字
     */
    public boolean isNumeric() {
        if (constant.length() == 1) {
            return Character.isDigit(constant.charAt(0));
        }
        // 多个字符的情况
        for (char ch : constant.toCharArray()) {
            if (!Character.isDigit(ch)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取常量的整数值（如果是数字）
     */
    public int getNumericValue() {
        if (isNumeric()) {
            return Integer.parseInt(constant);
        }
        throw new IllegalStateException("Constant is not numeric: " + constant);
    }
}