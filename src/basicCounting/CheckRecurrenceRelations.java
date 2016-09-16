/**
 * 
 */
package basicCounting;

import java.io.File;
import java.io.PrintWriter;

/**
 * @author xiaocong
 *
 */
public class CheckRecurrenceRelations {


	public static void main(String[] args) {
		String rootPath = "F:\\ZxcWork\\DiscreteTools\\data\\";
		String resultFile = rootPath + "temp.txt";
		
		PrintWriter result = null; 
		try {
			result = new PrintWriter(new File(resultFile));

			checkWithoutTwoConsecutive0sRecurrenceRelation(result);				// Example 3
			result.println();

			checkWithTwoConsecutive0sRecurrenceRelation(result);				// Exercise 7
			result.println();

			checkWithThreeConsecutive0sRecurrenceRelation(result);				// Exercise 8
			result.println();

			checkWithoutThreeConsecutive0sRecurrenceRelation(result);			// Exercise 9
			result.println();

			checkContain01RecurrenceRelation(result);							// Exercise 10
			result.println();

			checkWithoutTwoConsecutive0sTernaryRecurrenceRelation(result);		// Exercise 13
			result.println();

			checkWithTwoConsecutive0sTernaryRecurrenceRelation(result);			// Exercise 14
			result.println();

			checkWithoutTwoConsecutive0sOr1sTernaryRecurrenceRelation(result);	// Exercise 15
			result.println();

			checkWithTwoConsecutive0sOr1sTernaryRecurrenceRelation(result);		// Exercise 16
			result.println();

			checkWithoutConsecutiveSymbolsTernaryRecurrenceRelation(result);	// Exercise 17
			result.println();

			checkWithConsecutiveSymbolsTernaryRecurrenceRelation(result);		// Exercise 18
			result.close();
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	/**
	 * Section 8.1 Example 3
	 */
	public static void checkWithoutTwoConsecutive0sRecurrenceRelation(PrintWriter writer) {
		int n = 7;
		int recurrenceResult = RecurrenceCaculator.calculateWithoutTwoConsecutive0sBitStrings(n);

		StringCounter counter = new StringCounter(2, n);
		StringFilter filter = new WithoutTwoConsecutive0sFilter();
//		counter.setWriter(writer);
		
		int counterResult = counter.counting(filter);
		
		writer.println("Section 8.1, Example 3: Without two consecutive 0s, recurrence result: " + recurrenceResult + ", and counter result: " + counterResult);
		System.out.println("Section 8.1, Example 3: Without two consecutive 0s, recurrence result: " + recurrenceResult + ", and counter result: " + counterResult);
	}
	
	/**
	 * Section 8.1 Exercise 7
	 */
	public static void checkWithTwoConsecutive0sRecurrenceRelation(PrintWriter writer) {
		int n = 7;
		int recurrenceResult = RecurrenceCaculator.calculateWithTwoConsecutive0sBitStrings(n);

		StringCounter counter = new StringCounter(2, n);
		StringFilter filter = new WithTwoConsecutive0sFilter();
//		counter.setWriter(writer);
		
		int counterResult = counter.counting(filter);
		
		writer.println("Section 8.1, Exercise 7: With two consecutive 0s, recurrence result: " + recurrenceResult + ", and counter result: " + counterResult);
		System.out.println("Section 8.1, Exercise 7: With two consecutive 0s, recurrence result: " + recurrenceResult + ", and counter result: " + counterResult);
	}
	
	/**
	 * Section 8.1 Exercise 8
	 */
	public static void checkWithThreeConsecutive0sRecurrenceRelation(PrintWriter writer) {
		int n = 7;
		int recurrenceResult = RecurrenceCaculator.calculateWithThreeConsecutive0sBitStrings(n);

		StringCounter counter = new StringCounter(2, n);
		StringFilter filter = new WithThreeConsecutive0sFilter();
//		counter.setWriter(writer);
		
		int counterResult = counter.counting(filter);
		
		writer.println("Section 8.1, Exercise 8: With three consecutive 0s, recurrence result: " + recurrenceResult + ", and counter result: " + counterResult);
		System.out.println("Section 8.1, Exercise 8: With three consecutive 0s, recurrence result: " + recurrenceResult + ", and counter result: " + counterResult);
	}
	
	/**
	 * Section 8.1 Exercise 9
	 */
	public static void checkWithoutThreeConsecutive0sRecurrenceRelation(PrintWriter writer) {
		int n = 7;
		int recurrenceResult = RecurrenceCaculator.calculateWithoutThreeConsecutive0sBitStrings(n);

		StringCounter counter = new StringCounter(2, n);
		StringFilter filter = new WithoutThreeConsecutive0sFilter();
//		counter.setWriter(writer);
		
		int counterResult = counter.counting(filter);
		
		writer.println("Section 8.1, Exercise 9: Without three consecutive 0s, recurrence result: " + recurrenceResult + ", and counter result: " + counterResult);
		System.out.println("Section 8.1, Exercise 9: Without three consecutive 0s, recurrence result: " + recurrenceResult + ", and counter result: " + counterResult);
	}

	/**
	 * Section 8.1 Exercise 10
	 */
	public static void checkContain01RecurrenceRelation(PrintWriter writer) {
		int n = 7;
		int recurrenceResult = RecurrenceCaculator.calculateContain01BitString(n);

		StringCounter counter = new StringCounter(2, n);
		StringFilter filter = new Contain01Filter();
//		counter.setWriter(writer);
		
		int counterResult = counter.counting(filter);
		
		writer.println("Section 8.1, Exercise 10: Contain 01, recurrence result: " + recurrenceResult + ", and counter result: " + counterResult);
		System.out.println("Section 8.1, Exercise 10: Contain 01, recurrence result: " + recurrenceResult + ", and counter result: " + counterResult);
	}

	/**
	 * Section 8.1 Exercise 13
	 */
	public static void checkWithoutTwoConsecutive0sTernaryRecurrenceRelation(PrintWriter writer) {
		int n = 6;
		int recurrenceResult = RecurrenceCaculator.calculateWithoutTwoConsecutive0sTernaryString(n);

		StringCounter counter = new StringCounter(3, n);
		StringFilter filter = new WithoutTwoConsecutive0sFilter();
//		counter.setWriter(writer);
		
		int counterResult = counter.counting(filter);
		
		writer.println("Section 8.1, Exercise 13: Without two consecutive 0s ternary string, recurrence result: " + recurrenceResult + ", and counter result: " + counterResult);
		System.out.println("Section 8.1, Exercise 13: Without two consecutive 0s ternary string, recurrence result: " + recurrenceResult + ", and counter result: " + counterResult);
	}

	/**
	 * Section 8.1 Exercise 14
	 */
	public static void checkWithTwoConsecutive0sTernaryRecurrenceRelation(PrintWriter writer) {
		int n = 6;
		int recurrenceResult = RecurrenceCaculator.calculateWithTwoConsecutive0sTernaryString(n);

		StringCounter counter = new StringCounter(3, n);
		StringFilter filter = new WithTwoConsecutive0sFilter();
//		counter.setWriter(writer);
		
		int counterResult = counter.counting(filter);
		
		writer.println("Section 8.1, Exercise 14: With two consecutive 0s ternary string, recurrence result: " + recurrenceResult + ", and counter result: " + counterResult);
		System.out.println("Section 8.1, Exercise 14: With two consecutive 0s ternary string, recurrence result: " + recurrenceResult + ", and counter result: " + counterResult);
	}

	/**
	 * Section 8.1 Exercise 15
	 */
	public static void checkWithoutTwoConsecutive0sOr1sTernaryRecurrenceRelation(PrintWriter writer) {
		int n = 6;
		int recurrenceResult = RecurrenceCaculator.calculateWithoutTwoConsecutive0sOr1sTernaryString(n);

		StringCounter counter = new StringCounter(3, n);
		StringFilter filter = new WithoutTwoConsecutive0sOr1sFilter();
//		counter.setWriter(writer);
		
		int counterResult = counter.counting(filter);
		
		writer.println("Section 8.1, Exercise 15: Without two consecutive 0s or 1s ternary string, recurrence result: " + recurrenceResult + ", and counter result: " + counterResult);
		System.out.println("Section 8.1, Exercise 15: Without two consecutive 0s or 1s ternary string, recurrence result: " + recurrenceResult + ", and counter result: " + counterResult);
	}

	/**
	 * Section 8.1 Exercise 16
	 */
	public static void checkWithTwoConsecutive0sOr1sTernaryRecurrenceRelation(PrintWriter writer) {
		int n = 6;
		int recurrenceResult = RecurrenceCaculator.calculateWithTwoConsecutive0sOr1sTernaryString(n);

		StringCounter counter = new StringCounter(3, n);
		StringFilter filter = new WithTwoConsecutive0sOr1sFilter();
//		counter.setWriter(writer);
		
		int counterResult = counter.counting(filter);
		
		writer.println("Section 8.1, Exercise 16: With two consecutive 0s or 1s ternary string, recurrence result: " + recurrenceResult + ", and counter result: " + counterResult);
		System.out.println("Section 8.1, Exercise 16: With two consecutive 0s or 1s ternary string, recurrence result: " + recurrenceResult + ", and counter result: " + counterResult);
	}

	/**
	 * Section 8.1 Exercise 17
	 */
	public static void checkWithoutConsecutiveSymbolsTernaryRecurrenceRelation(PrintWriter writer) {
		int n = 6;
		int recurrenceResult = RecurrenceCaculator.calculateWithoutConsecutiveSymbolsTernaryString(n);

		StringCounter counter = new StringCounter(3, n);
		StringFilter filter = new WithoutConsecutiveSymbolsFilter();
//		counter.setWriter(writer);
		
		int counterResult = counter.counting(filter);
		
		writer.println("Section 8.1, Exercise 17: Without consecutive symbols ternary string, recurrence result: " + recurrenceResult + ", and counter result: " + counterResult);
		System.out.println("Section 8.1, Exercise 17: Without consecutive symbols ternary string, recurrence result: " + recurrenceResult + ", and counter result: " + counterResult);
	}
	
	/**
	 * Section 8.1 Exercise 18
	 */
	public static void checkWithConsecutiveSymbolsTernaryRecurrenceRelation(PrintWriter writer) {
		int n = 6;
		int recurrenceResult = RecurrenceCaculator.calculateWithConsecutiveSymbolsTernaryString(n);

		StringCounter counter = new StringCounter(3, n);
		StringFilter filter = new WithConsecutiveSymbolsFilter();
//		counter.setWriter(writer);
		
		int counterResult = counter.counting(filter);
		
		writer.println("Section 8.1, Exercise 18: With consecutive symbols ternary string, recurrence result: " + recurrenceResult + ", and counter result: " + counterResult);
		System.out.println("Section 8.1, Exercise 18: With consecutive symbols ternary string, recurrence result: " + recurrenceResult + ", and counter result: " + counterResult);
	}
}
