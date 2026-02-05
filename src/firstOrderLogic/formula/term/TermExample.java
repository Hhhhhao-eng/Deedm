package firstOrderLogic.formula.term;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TermExample {
    public static void main(String[] args) {
        System.out.println("=== 一阶逻辑项类测试 ===");
        System.out.println();

        // ========== 1. 基础项创建测试 ==========
        testBasicTermCreation();

        // ========== 2. 函数项和嵌套测试 ==========
        testFunctionAndNesting();

        // ========== 3. 变量替换测试 ==========
        testVariableSubstitution();

        // ========== 4. LaTeX字符串测试 ==========
        testLaTeXRepresentation();

        // ========== 5. 复杂嵌套函数测试 ==========
        testComplexNestedFunctions();

        // ========== 6. 符号收集和分析测试 ==========
        testSymbolCollection();

        // ========== 7. 工厂类测试 ==========
        testTermFactory();

        // ========== 8. 边缘情况测试 ==========
        testEdgeCases();
    }

    private static void testBasicTermCreation() {
        System.out.println("1. 基础项创建测试");
        System.out.println("=".repeat(40));

        // 测试变量项
        VariableTerm x = new VariableTerm('x');
        VariableTerm y1 = new VariableTerm('y', 1); // y_1
        VariableTerm z42 = new VariableTerm('z', 42); // z_42

        System.out.println("变量项:");
        System.out.println("  x: " + x.toString() + " (LaTeX: " + x.toLaTeXString() + ")");
        System.out.println("  y_1: " + y1.toString() + " (LaTeX: " + y1.toLaTeXString() + ")");
        System.out.println("  z_42: " + z42.toString() + " (LaTeX: " + z42.toLaTeXString() + ")");

        // 测试常量项
        ConstantTerm a = new ConstantTerm("a");
        ConstantTerm b = new ConstantTerm("b");
        ConstantTerm zero = new ConstantTerm("0");
        ConstantTerm num123 = new ConstantTerm("123");

        System.out.println("\n常量项:");
        System.out.println("  a: " + a.toString() + " (LaTeX: " + a.toLaTeXString() + ")");
        System.out.println("  0: " + zero.toString() + " 是否为数字: " + zero.isNumeric());
        System.out.println("  123: " + num123.toString() + " 数值: " + num123.getNumericValue());

        // 测试相等性
        System.out.println("\n相等性测试:");
        VariableTerm x2 = new VariableTerm('x');
        System.out.println("  x.equals(x2): " + x.equals(x2)); // 应为 true
        System.out.println("  x.equals(y1): " + x.equals(y1)); // 应为 false
        System.out.println("  a.equals(new ConstantTerm(\"a\")): " + a.equals(new ConstantTerm("a"))); // true

        System.out.println();
    }

    private static void testFunctionAndNesting() {
        System.out.println("2. 函数项和嵌套测试");
        System.out.println("=".repeat(40));

        // 创建基本项
        VariableTerm x = new VariableTerm('x');
        VariableTerm y = new VariableTerm('y');
        ConstantTerm a = new ConstantTerm("a");

        // 1. 一元函数
        List<Term> unaryArgs = new ArrayList<>();
        unaryArgs.add(x);
        FunctionTerm f_x = new FunctionTerm('f', unaryArgs);

        // 2. 二元函数
        List<Term> binaryArgs = new ArrayList<>();
        binaryArgs.add(x);
        binaryArgs.add(y);
        FunctionTerm g_xy = new FunctionTerm('g', binaryArgs);

        // 3. 三元函数
        List<Term> ternaryArgs = new ArrayList<>();
        ternaryArgs.add(x);
        ternaryArgs.add(a);
        ternaryArgs.add(y);
        FunctionTerm h_xay = new FunctionTerm('h', ternaryArgs);

        System.out.println("基础函数项:");
        System.out.println("  f(x): " + f_x.toString());
        System.out.println("  g(x, y): " + g_xy.toString());
        System.out.println("  h(x, a, y): " + h_xay.toString());

        // 4. 嵌套函数：g(f(x), h(x, a, y))
        List<Term> nestedArgs1 = new ArrayList<>();
        nestedArgs1.add(f_x);
        List<Term> nestedArgs2 = new ArrayList<>();
        nestedArgs2.add(x);
        nestedArgs2.add(a);
        nestedArgs2.add(y);
        FunctionTerm h_xay2 = new FunctionTerm('h', nestedArgs2);

        List<Term> complexArgs = new ArrayList<>();
        complexArgs.add(f_x);
        complexArgs.add(h_xay2);
        FunctionTerm g_fx_hxay = new FunctionTerm('g', complexArgs);

        System.out.println("\n嵌套函数项:");
        System.out.println("  g(f(x), h(x, a, y)): " + g_fx_hxay.toString());
        System.out.println("  深度: " + g_fx_hxay.getDepth());
        System.out.println("  复杂度: " + g_fx_hxay.getComplexity());

        // 5. 更复杂的嵌套：f(g(h(x, y), a))
        List<Term> innerArgs = new ArrayList<>();
        innerArgs.add(x);
        innerArgs.add(y);
        FunctionTerm h_xy = new FunctionTerm('h', innerArgs);

        List<Term> middleArgs = new ArrayList<>();
        middleArgs.add(h_xy);
        middleArgs.add(a);
        FunctionTerm g_hxy_a = new FunctionTerm('g', middleArgs);

        List<Term> outerArgs = new ArrayList<>();
        outerArgs.add(g_hxy_a);
        FunctionTerm f_g_hxy_a = new FunctionTerm('f', outerArgs);

        System.out.println("\n更复杂的嵌套:");
        System.out.println("  f(g(h(x, y), a)): " + f_g_hxy_a.toString());
        System.out.println("  深度: " + f_g_hxy_a.getDepth());

        System.out.println();
    }

    private static void testVariableSubstitution() {
        System.out.println("3. 变量替换测试");
        System.out.println("=".repeat(40));

        // 创建基础项
        VariableTerm x = new VariableTerm('x');
        VariableTerm y = new VariableTerm('y');
        VariableTerm z = new VariableTerm('z');
        ConstantTerm a = new ConstantTerm("a");
        ConstantTerm b = new ConstantTerm("b");

        // 创建复杂函数项：f(g(x, y), h(z))
        List<Term> gArgs = new ArrayList<>();
        gArgs.add(x);
        gArgs.add(y);
        FunctionTerm g_xy = new FunctionTerm('g', gArgs);

        List<Term> hArgs = new ArrayList<>();
        hArgs.add(z);
        FunctionTerm h_z = new FunctionTerm('h', hArgs);

        List<Term> fArgs = new ArrayList<>();
        fArgs.add(g_xy);
        fArgs.add(h_z);
        FunctionTerm f_gxy_hz = new FunctionTerm('f', fArgs);

        System.out.println("原始项: " + f_gxy_hz.toString());
        System.out.println("包含的变量: " + f_gxy_hz.getVariables());

        // 测试1：替换单个变量
        Term result1 = f_gxy_hz.substitute(a, 'x');
        System.out.println("\n1. 将 x 替换为 a:");
        System.out.println("  结果: " + result1.toString());
        System.out.println("  包含的变量: " + result1.getVariables());

        // 测试2：替换嵌套中的变量
        Term result2 = f_gxy_hz.substitute(b, 'y');
        System.out.println("\n2. 将 y 替换为 b:");
        System.out.println("  结果: " + result2.toString());

        // 测试3：替换不存在的变量
        Term result3 = f_gxy_hz.substitute(a, 'w');
        System.out.println("\n3. 将不存在的 w 替换为 a:");
        System.out.println("  结果: " + result3.toString());
        System.out.println("  是否相等: " + f_gxy_hz.equals(result3));

        // 测试4：用函数项替换变量
        List<Term> kArgs = new ArrayList<>();
        kArgs.add(a);
        kArgs.add(b);
        FunctionTerm k_ab = new FunctionTerm('k', kArgs);

        Term result4 = f_gxy_hz.substitute(k_ab, 'z');
        System.out.println("\n4. 将 z 替换为函数项 k(a, b):");
        System.out.println("  结果: " + result4.toString());
        System.out.println("  深度: " + result4.getDepth());

        // 测试5：链式替换
        System.out.println("\n5. 链式替换测试:");
        Term temp = f_gxy_hz.substitute(a, 'x');
        Term finalResult = temp.substitute(b, 'y');
        System.out.println("  先替换x为a，再替换y为b:");
        System.out.println("  结果: " + finalResult.toString());

        System.out.println();
    }

    private static void testLaTeXRepresentation() {
        System.out.println("4. LaTeX字符串测试");
        System.out.println("=".repeat(40));

        // 创建带下标的变量
        VariableTerm x1 = new VariableTerm('x', 1);
        VariableTerm y2 = new VariableTerm('y', 2);
        VariableTerm z = new VariableTerm('z');

        // 创建常量
        ConstantTerm a = new ConstantTerm("a");
        ConstantTerm zero = new ConstantTerm("0");

        // 创建嵌套函数项：f(g(x?, y?), h(z, a))
        List<Term> gArgs = new ArrayList<>();
        gArgs.add(x1);
        gArgs.add(y2);
        FunctionTerm g_x1y2 = new FunctionTerm('g', gArgs);

        List<Term> hArgs = new ArrayList<>();
        hArgs.add(z);
        hArgs.add(a);
        FunctionTerm h_za = new FunctionTerm('h', hArgs);

        List<Term> fArgs = new ArrayList<>();
        fArgs.add(g_x1y2);
        fArgs.add(h_za);
        FunctionTerm complexFunc = new FunctionTerm('f', fArgs);

        System.out.println("复杂嵌套函数项:");
        System.out.println("  普通表示: " + complexFunc.toString());
        System.out.println("  LaTeX表示: " + complexFunc.toLaTeXString());
        System.out.println("  预期: f(g(x_{1}, y_{2}), h(z, a))");

        // 测试更复杂的LaTeX格式
        List<Term> complexArgs = new ArrayList<>();
        complexArgs.add(new FunctionTerm('f', List.of(x1)));
        complexArgs.add(new FunctionTerm('g', List.of(y2, zero)));
        FunctionTerm veryComplex = new FunctionTerm('h', complexArgs);

        System.out.println("\n更复杂函数项:");
        System.out.println("  普通表示: " + veryComplex.toString());
        System.out.println("  LaTeX表示: " + veryComplex.toLaTeXString());
        System.out.println("  预期: h(f(x_{1}), g(y_{2}, 0))");

        // 测试零元函数
        FunctionTerm constantFunc = new FunctionTerm('c', new ArrayList<>());
        System.out.println("\n零元函数:");
        System.out.println("  普通表示: " + constantFunc.toString());
        System.out.println("  LaTeX表示: " + constantFunc.toLaTeXString());

        System.out.println();
    }

    private static void testComplexNestedFunctions() {
        System.out.println("5. 复杂嵌套函数测试");
        System.out.println("=".repeat(40));

        VariableTerm x = new VariableTerm('x');
        VariableTerm y = new VariableTerm('y');
        ConstantTerm a = new ConstantTerm("a");

        // 构建: f(g(h(x, f(y, a)), x), g(x, y))
        // 1. 最内层: f(y, a)
        List<Term> innerFArgs = new ArrayList<>();
        innerFArgs.add(y);
        innerFArgs.add(a);
        FunctionTerm f_ya = new FunctionTerm('f', innerFArgs);

        // 2. h(x, f(y, a))
        List<Term> hArgs = new ArrayList<>();
        hArgs.add(x);
        hArgs.add(f_ya);
        FunctionTerm h_x_fya = new FunctionTerm('h', hArgs);

        // 3. g(h(x, f(y, a)), x)
        List<Term> g1Args = new ArrayList<>();
        g1Args.add(h_x_fya);
        g1Args.add(x);
        FunctionTerm g_hx_x = new FunctionTerm('g', g1Args);

        // 4. g(x, y)
        List<Term> g2Args = new ArrayList<>();
        g2Args.add(x);
        g2Args.add(y);
        FunctionTerm g_xy = new FunctionTerm('g', g2Args);

        // 5. 最外层: f(g(h(x, f(y, a)), x), g(x, y))
        List<Term> outerFArgs = new ArrayList<>();
        outerFArgs.add(g_hx_x);
        outerFArgs.add(g_xy);
        FunctionTerm complexTerm = new FunctionTerm('f', outerFArgs);

        System.out.println("复杂嵌套函数项:");
        System.out.println("  表达式: " + complexTerm.toString());
        System.out.println("  深度: " + complexTerm.getDepth());
        System.out.println("  复杂度: " + complexTerm.getComplexity());

        // 获取所有子项
        List<Term> allSubterms = complexTerm.getAllSubterms();
        System.out.println("\n所有子项:");
        for (int i = 0; i < allSubterms.size(); i++) {
            System.out.println("  " + (i+1) + ". " + allSubterms.get(i).toString());
        }

        // 测试是否为常函数
        System.out.println("\n是否为常函数: " + complexTerm.isConstantFunction());

        // 创建一个真正的常函数
        List<Term> constArgs = new ArrayList<>();
        constArgs.add(a);
        constArgs.add(new ConstantTerm("b"));
        FunctionTerm constFunc = new FunctionTerm('f', constArgs);
        System.out.println("f(a, b) 是否为常函数: " + constFunc.isConstantFunction());

        System.out.println();
    }

    private static void testSymbolCollection() {
        System.out.println("6. 符号收集和分析测试");
        System.out.println("=".repeat(40));

        // 创建复杂项: f(g(x, h(y, a)), k(z, b, c))
        VariableTerm x = new VariableTerm('x');
        VariableTerm y = new VariableTerm('y');
        VariableTerm z = new VariableTerm('z');
        ConstantTerm a = new ConstantTerm("a");
        ConstantTerm b = new ConstantTerm("b");
        ConstantTerm c = new ConstantTerm("c");

        // h(y, a)
        List<Term> hArgs = new ArrayList<>();
        hArgs.add(y);
        hArgs.add(a);
        FunctionTerm h_ya = new FunctionTerm('h', hArgs);

        // g(x, h(y, a))
        List<Term> gArgs = new ArrayList<>();
        gArgs.add(x);
        gArgs.add(h_ya);
        FunctionTerm g_x_hya = new FunctionTerm('g', gArgs);

        // k(z, b, c)
        List<Term> kArgs = new ArrayList<>();
        kArgs.add(z);
        kArgs.add(b);
        kArgs.add(c);
        FunctionTerm k_zbc = new FunctionTerm('k', kArgs);

        // f(g(x, h(y, a)), k(z, b, c))
        List<Term> fArgs = new ArrayList<>();
        fArgs.add(g_x_hya);
        fArgs.add(k_zbc);
        FunctionTerm complexTerm = new FunctionTerm('f', fArgs);

        System.out.println("测试项: " + complexTerm.toString());
        System.out.println();

        // 收集所有变量
        Set<Character> variables = complexTerm.getVariables();
        System.out.println("包含的变量: " + variables);

        // 收集所有函数符号
        Set<Character> functionSymbols = complexTerm.getFunctionSymbols();
        System.out.println("函数符号: " + functionSymbols);

        // 收集所有常量符号
        Set<String> constantSymbols = complexTerm.getConstantSymbols();
        System.out.println("常量符号: " + constantSymbols);

        // 收集所有符号
        Set<Object> allSymbols = complexTerm.getAllSymbols();
        System.out.println("所有符号: " + allSymbols);

        // 测试变量包含性
        System.out.println("\n变量包含性测试:");
        System.out.println("  包含变量 x: " + complexTerm.containsVariable('x'));
        System.out.println("  包含变量 y: " + complexTerm.containsVariable('y'));
        System.out.println("  包含变量 z: " + complexTerm.containsVariable('z'));
        System.out.println("  包含变量 w: " + complexTerm.containsVariable('w'));

        // 测试函数符号包含性
        System.out.println("\n函数符号包含性测试:");
        System.out.println("  包含函数 f: " + complexTerm.containsFunctionSymbol('f'));
        System.out.println("  包含函数 g: " + complexTerm.containsFunctionSymbol('g'));
        System.out.println("  包含函数 h: " + complexTerm.containsFunctionSymbol('h'));
        System.out.println("  包含函数 k: " + complexTerm.containsFunctionSymbol('k'));
        System.out.println("  包含函数 m: " + complexTerm.containsFunctionSymbol('m'));

        System.out.println();
    }

    private static void testTermFactory() {
        System.out.println("7. 工厂类测试");
        System.out.println("=".repeat(40));

        // 使用工厂创建各种项
        VariableTerm x = TermFactory.createVariable('x');
        VariableTerm y1 = TermFactory.createVariable('y', 1);
        ConstantTerm a = TermFactory.createConstant("a");
        ConstantTerm num5 = TermFactory.createNumericConstant(5);

        System.out.println("工厂创建的项:");
        System.out.println("  x: " + x.toString());
        System.out.println("  y_1: " + y1.toString());
        System.out.println("  a: " + a.toString());
        System.out.println("  5: " + num5.toString());

        // 使用工厂创建函数项
        FunctionTerm f_x = TermFactory.createFunction('f', x);
        FunctionTerm g_xy = TermFactory.createFunction('g', x, y1);

        System.out.println("\n工厂创建的函数项:");
        System.out.println("  f(x): " + f_x.toString());
        System.out.println("  g(x, y_1): " + g_xy.toString());

        // 测试字符串解析
        System.out.println("\n字符串解析测试:");

        String[] testStrings = {
                "x",          // 变量
                "y_1",        // 带下标变量
                "a",          // 常量
                "123",        // 数字常量
                "f(x)",       // 一元函数
                "g(x, y)",    // 二元函数
                "h(a, b, c)", // 三元函数
                "f(g(x, y))", // 嵌套函数
                "f(x_1, g(y_2, z))" // 复杂嵌套
        };

        for (String str : testStrings) {
            try {
                Term term = TermFactory.parseTerm(str);
                System.out.println("  解析 \"" + str + "\" => " + term.toString() +
                        " (类型: " + term.getClass().getSimpleName() + ")");
            } catch (Exception e) {
                System.out.println("  解析 \"" + str + "\" 失败: " + e.getMessage());
            }
        }

        // 测试有效性验证
        System.out.println("\n有效性验证测试:");
        System.out.println("  \"x\" 是否有效: " + TermFactory.isValidTerm("x"));
        System.out.println("  \"f(x)\" 是否有效: " + TermFactory.isValidTerm("f(x)"));
        System.out.println("  \"F(x)\" 是否有效: " + TermFactory.isValidTerm("F(x)")); // 大写函数符号
        System.out.println("  \"f(x,\" 是否有效: " + TermFactory.isValidTerm("f(x,")); // 语法错误

        System.out.println();
    }

    private static void testEdgeCases() {
        System.out.println("8. 边缘情况测试");
        System.out.println("=".repeat(40));

        System.out.println("测试1: 空参数列表的函数项");
        try {
            FunctionTerm zeroArityFunc = new FunctionTerm('c', new ArrayList<>());
            System.out.println("  成功创建: " + zeroArityFunc.toString());
            System.out.println("  元数: " + zeroArityFunc.getArity());
            System.out.println("  深度: " + zeroArityFunc.getDepth());
        } catch (Exception e) {
            System.out.println("  创建失败: " + e.getMessage());
        }

        System.out.println("\n测试2: 超大嵌套深度");
        try {
            // 创建深度为100的嵌套函数 f(f(f(...)))
            Term current = new VariableTerm('x');
            for (int i = 0; i < 100; i++) {
                List<Term> args = new ArrayList<>();
                args.add(current);
                current = new FunctionTerm('f', args);
            }
            System.out.println("  成功创建深度100的嵌套函数");
            System.out.println("  最终深度: " + current.getDepth());
        } catch (Exception e) {
            System.out.println("  创建失败: " + e.getMessage());
        }

        System.out.println("\n测试3: 非法输入");
        try {
            new VariableTerm('X'); // 大写变量
            System.out.println("  错误: 应该抛出异常但未抛出");
        } catch (IllegalArgumentException e) {
            System.out.println("  正确捕获异常: " + e.getMessage());
        }

        try {
            new ConstantTerm(""); // 空常量
            System.out.println("  错误: 应该抛出异常但未抛出");
        } catch (IllegalArgumentException e) {
            System.out.println("  正确捕获异常: " + e.getMessage());
        }

        try {
            new FunctionTerm('F', new ArrayList<>()); // 大写函数符号
            System.out.println("  错误: 应该抛出异常但未抛出");
        } catch (IllegalArgumentException e) {
            System.out.println("  正确捕获异常: " + e.getMessage());
        }

        System.out.println("\n测试4: 克隆和相等性");
        VariableTerm x1 = new VariableTerm('x', 1);
        VariableTerm x1Clone = (VariableTerm) x1.clone();
        System.out.println("  x_1.equals(克隆): " + x1.equals(x1Clone));
        System.out.println("  x_1 == 克隆: " + (x1 == x1Clone));

        FunctionTerm f_x = new FunctionTerm('f', List.of(new VariableTerm('x')));
        FunctionTerm f_xClone = (FunctionTerm) f_x.clone();
        System.out.println("  f(x).equals(克隆): " + f_x.equals(f_xClone));

        System.out.println("\n测试5: 哈希值一致性");
        VariableTerm y = new VariableTerm('y');
        VariableTerm y2 = new VariableTerm('y');
        System.out.println("  相同变量的哈希值: " + y.hashCode() + " vs " + y2.hashCode());
        System.out.println("  哈希值是否相等: " + (y.hashCode() == y2.hashCode()));

        System.out.println();
    }
}