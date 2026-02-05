package firstOrderLogic.formula;

/**
 * 一阶逻辑符号常量管理类
 */
public class Symbol {

    // ========== 基本符号常量 ==========
    public final static char END_FLAG = '#';
    public final static char LEFT_BRACKET = '(';
    public final static char RIGHT_BRACKET = ')';
    public final static char COMMA = ',';
    public final static char EQUAL = '≈';

    // ========== 命题逻辑运算符 ==========
    public final static char OPERATOR_NOT = '~';
    public final static char OPERATOR_AND = '&';
    public final static char OPERATOR_OR = '|';
    public final static char OPERATOR_IMP = '>';
    public final static char OPERATOR_EQ = '=';

    // ========== 一阶逻辑量词 ==========
    public final static char QUANTIFIER_ALL = '∀';
    public final static char QUANTIFIER_EXISTS = '∃';

    // ASCII 替代表示（用于内部处理）
    public final static char QUANTIFIER_ALL_ASCII = '@';
    public final static char QUANTIFIER_EXISTS_ASCII = '?';

    // ========== LaTeX 命令 ==========
    public final static String LATEX_AND = "\\wedge";
    public final static String LATEX_NOT = "\\neg";
    public final static String LATEX_OR = "\\vee";
    public final static String LATEX_IMP = "\\rightarrow";
    public final static String LATEX_EQ = "\\leftrightarrow";
    public final static String LATEX_FORALL = "\\forall";
    public final static String LATEX_EXISTS = "\\exists";

    public final static int MAX_LATEX_LENGTH = 20;

    // ========== 特殊公式类型标识 ==========
    // 用于 Formula 类中的 operator 字段
    public final static char ATOMIC_PREDICATE = 'P';
    public final static char ATOMIC_EQUALITY = 'E';
    public final static char BINARY_FORMULA = 'B';
    public final static char NEGATION_FORMULA = 'N';
    public final static char UNIVERSAL_FORMULA = 'U';
    public final static char EXISTENTIAL_FORMULA = 'X';

    // ========== 判断方法 ==========

    /**
     * 判断是否为逻辑运算符
     */
    public static boolean isOperator(char ch) {
        return ch == OPERATOR_AND || ch == OPERATOR_NOT || ch == OPERATOR_OR ||
                ch == OPERATOR_IMP || ch == OPERATOR_EQ;
    }

    /**
     * 判断是否为量词
     */
    public static boolean isQuantifier(char ch) {
        return ch == QUANTIFIER_ALL || ch == QUANTIFIER_EXISTS ||
                ch == QUANTIFIER_ALL_ASCII || ch == QUANTIFIER_EXISTS_ASCII;
    }

    /**
     * 判断是否为左括号
     */
    public static boolean isLeftBracket(char ch) {
        return ch == LEFT_BRACKET;
    }

    /**
     * 判断是否为右括号
     */
    public static boolean isRightBracket(char ch) {
        return ch == RIGHT_BRACKET;
    }

    /**
     * 判断是否为等号
     */
    public static boolean isEqualSign(char ch) {
        return ch == EQUAL;
    }

    /**
     * 判断是否为逗号
     */
    public static boolean isComma(char ch) {
        return ch == COMMA;
    }

    /**
     * 判断是否为小写字母（变量或函数符号）
     */
    public static boolean isLowerCaseLetter(char ch) {
        return ch >= 'a' && ch <= 'z';
    }

    /**
     * 判断是否为大写字母（谓词符号）
     */
    public static boolean isUpperCaseLetter(char ch) {
        return ch >= 'A' && ch <= 'Z';
    }

    /**
     * 判断是否为字母
     */
    public static boolean isLetter(char ch) {
        return isLowerCaseLetter(ch) || isUpperCaseLetter(ch);
    }

    /**
     * 判断是否为数字
     */
    public static boolean isDigit(char ch) {
        return ch >= '0' && ch <= '9';
    }

    // ========== 运算符优先级 ==========

    /**
     * 比较运算符优先级
     * @param stackOperator 栈内运算符
     * @param inputOperator 输入运算符
     * @return true 如果栈内运算符优先级高于或等于输入运算符
     */
    public static boolean isPriorTo(char stackOperator, char inputOperator) {
        // 右括号特殊处理
        if (inputOperator == RIGHT_BRACKET) return true;
        if (stackOperator == LEFT_BRACKET) return false;
        if (inputOperator == LEFT_BRACKET) return false;

        // 量词优先级最高
        if (isQuantifier(stackOperator)) {
            return true;
        }
        if (isQuantifier(inputOperator)) {
            return false;
        }

        // 否定运算符
        if (stackOperator == OPERATOR_NOT) {
            if (inputOperator == OPERATOR_NOT) return false;
            else return true;
        }
        if (inputOperator == OPERATOR_NOT) return false;

        // 相同运算符的结合性
        if (stackOperator == inputOperator) {
            if (stackOperator != OPERATOR_IMP) return true; // 左结合
            else return false; // 蕴涵右结合
        }

        // 不同运算符的优先级
        int stackPriority = getOperatorPriority(stackOperator);
        int inputPriority = getOperatorPriority(inputOperator);

        return stackPriority >= inputPriority;
    }

    /**
     * 获取运算符优先级数值（数值越大优先级越高）
     */
    private static int getOperatorPriority(char operator) {
        switch (operator) {
            case OPERATOR_NOT: return 5;
            case OPERATOR_AND: return 4;
            case OPERATOR_OR:  return 3;
            case OPERATOR_IMP: return 2;
            case OPERATOR_EQ:  return 1;
            case QUANTIFIER_ALL:
            case QUANTIFIER_EXISTS:
            case QUANTIFIER_ALL_ASCII:
            case QUANTIFIER_EXISTS_ASCII: return 6;
            default: return 0;
        }
    }

    // ========== 符号转换方法 ==========

    /**
     * 获取运算符的LaTeX表示
     */
    public static String getLaTeXOperator(char operator) {
        switch (operator) {
            case OPERATOR_AND: return LATEX_AND;
            case OPERATOR_OR:  return LATEX_OR;
            case OPERATOR_NOT: return LATEX_NOT;
            case OPERATOR_IMP: return LATEX_IMP;
            case OPERATOR_EQ:  return LATEX_EQ;
            case QUANTIFIER_ALL:
            case QUANTIFIER_ALL_ASCII: return LATEX_FORALL;
            case QUANTIFIER_EXISTS:
            case QUANTIFIER_EXISTS_ASCII: return LATEX_EXISTS;
            case EQUAL: return "=";
            default: return String.valueOf(operator);
        }
    }

    /**
     * 获取运算符对应的图片文件名
     */
    public static String getOperatorImageFileName(char operator) {
        String fileName;
        switch (operator) {
            case OPERATOR_AND: fileName = "wedge.png"; break;
            case OPERATOR_OR:  fileName = "vee.png"; break;
            case OPERATOR_NOT: fileName = "neg.png"; break;
            case OPERATOR_IMP: fileName = "rightarrow.png"; break;
            case OPERATOR_EQ:  fileName = "leftrightarrow.png"; break;
            case QUANTIFIER_ALL:
            case QUANTIFIER_ALL_ASCII: fileName = "forall.png"; break;
            case QUANTIFIER_EXISTS:
            case QUANTIFIER_EXISTS_ASCII: fileName = "exists.png"; break;
            default: fileName = "unknown.png";
        }
        return util.Configuration.imageFilePath + fileName;
    }

    /**
     * 将LaTeX命令转换为内部运算符
     */
    public static char parseLaTeXOperator(String latex) {
        if (latex == null) return END_FLAG;

        switch (latex.trim()) {
            case "\\neg":
            case "\\lnot": return OPERATOR_NOT;
            case "\\wedge":
            case "\\land": return OPERATOR_AND;
            case "\\vee":
            case "\\lor": return OPERATOR_OR;
            case "\\rightarrow":
            case "\\to":
            case "\\implies": return OPERATOR_IMP;
            case "\\leftrightarrow":
            case "\\equiv": return OPERATOR_EQ;
            case "\\forall": return QUANTIFIER_ALL_ASCII;
            case "\\exists": return QUANTIFIER_EXISTS_ASCII;
            default: return latex.length() == 1 ? latex.charAt(0) : END_FLAG;
        }
    }

    /**
     * 将内部运算符转换为LaTeX命令
     */
    public static String toLaTeXOperator(char operator) {
        switch (operator) {
            case OPERATOR_NOT: return LATEX_NOT;
            case OPERATOR_AND: return LATEX_AND;
            case OPERATOR_OR: return LATEX_OR;
            case OPERATOR_IMP: return LATEX_IMP;
            case OPERATOR_EQ: return LATEX_EQ;
            case QUANTIFIER_ALL:
            case QUANTIFIER_ALL_ASCII: return LATEX_FORALL;
            case QUANTIFIER_EXISTS:
            case QUANTIFIER_EXISTS_ASCII: return LATEX_EXISTS;
            default: return String.valueOf(operator);
        }
    }
}