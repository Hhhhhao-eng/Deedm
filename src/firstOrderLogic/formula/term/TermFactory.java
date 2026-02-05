package firstOrderLogic.formula.term;

import java.util.ArrayList;
import java.util.List;

/**
 * 项工厂类，用于创建各种类型的项
 */
public class TermFactory {

    /**
     * 创建变量项
     */
    public static VariableTerm createVariable(char variable) {
        return new VariableTerm(variable);
    }

    /**
     * 创建带下标的变量项
     */
    public static VariableTerm createVariable(char variable, int subscript) {
        return new VariableTerm(variable, subscript);
    }

    /**
     * 创建常量项
     */
    public static ConstantTerm createConstant(String constant) {
        return new ConstantTerm(constant);
    }

    /**
     * 创建数字常量项
     */
    public static ConstantTerm createNumericConstant(int value) {
        return new ConstantTerm(String.valueOf(value));
    }

    /**
     * 创建零元函数项
     */
    public static FunctionTerm createFunction(char functionSymbol) {
        return new FunctionTerm(functionSymbol, new ArrayList<>());
    }

    /**
     * 创建一元函数项
     */
    public static FunctionTerm createFunction(char functionSymbol, Term arg1) {
        List<Term> args = new ArrayList<>();
        args.add(arg1);
        return new FunctionTerm(functionSymbol, args);
    }

    /**
     * 创建二元函数项
     */
    public static FunctionTerm createFunction(char functionSymbol, Term arg1, Term arg2) {
        List<Term> args = new ArrayList<>();
        args.add(arg1);
        args.add(arg2);
        return new FunctionTerm(functionSymbol, args);
    }

    /**
     * 创建多元函数项
     */
    public static FunctionTerm createFunction(char functionSymbol, List<Term> arguments) {
        return new FunctionTerm(functionSymbol, arguments);
    }

    /**
     * 解析字符串创建项
     */
    public static Term parseTerm(String termString) {
        // 简化的解析逻辑，实际实现需要完整的解析器
        termString = termString.trim();

        if (termString.isEmpty()) {
            throw new IllegalArgumentException("Term string cannot be empty");
        }

        // 判断是否为变量（单个小写字母，可能带下标）
        if (termString.length() == 1) {
            char ch = termString.charAt(0);
            if (Character.isLowerCase(ch)) {
                return createVariable(ch);
            } else if (Character.isDigit(ch)) {
                return createConstant(termString);
            }
        }

        // 判断是否为带下标的变量
        if (termString.contains("_")) {
            String[] parts = termString.split("_");
            if (parts.length == 2 && parts[0].length() == 1) {
                char var = parts[0].charAt(0);
                try {
                    int subscript = Integer.parseInt(parts[1]);
                    return createVariable(var, subscript);
                } catch (NumberFormatException e) {
                    // 不是数字下标，可能是其他格式
                }
            }
        }

        // 判断是否为函数项（包含括号）
        if (termString.contains("(") && termString.endsWith(")")) {
            int parenIndex = termString.indexOf('(');
            char funcSymbol = termString.charAt(0);

            if (Character.isLowerCase(funcSymbol)) {
                String argsStr = termString.substring(parenIndex + 1, termString.length() - 1);
                List<Term> args = parseTermList(argsStr);
                return createFunction(funcSymbol, args);
            }
        }

        // 默认为常量项
        return createConstant(termString);
    }

    /**
     * 解析项列表
     */
    private static List<Term> parseTermList(String argsStr) {
        List<Term> terms = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        int parenDepth = 0;

        for (char ch : argsStr.toCharArray()) {
            if (ch == '(') {
                parenDepth++;
                current.append(ch);
            } else if (ch == ')') {
                parenDepth--;
                current.append(ch);
            } else if (ch == ',' && parenDepth == 0) {
                if (current.length() > 0) {
                    terms.add(parseTerm(current.toString().trim()));
                    current = new StringBuilder();
                }
            } else {
                current.append(ch);
            }
        }

        if (current.length() > 0) {
            terms.add(parseTerm(current.toString().trim()));
        }

        return terms;
    }

    /**
     * 判断字符串是否表示有效的项
     */
    public static boolean isValidTerm(String termString) {
        try {
            parseTerm(termString);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}