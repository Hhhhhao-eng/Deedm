/**
 * 
 */
package basicCounting;

/**
 * @author xiaocong
 *
 */
public class RecurrenceCaculator {

	/**
	 * Section 8.1 Exercise 7
	 */
	public static int calculateWithTwoConsecutive0sBitStrings(int n) {
		int length = n+1;
		if (length < 2) length = 2;
		int[] counter = new int[length];
		counter[0] = 0;
		counter[1] = 0;
		
		int current = 2;
		while (current <= n) {
			counter[current] = counter[current-1] + counter[current-2] + power(2, current-2);  
			current++;
		}
		return counter[n];
	}

	/**
	 * Section 8.1 Example 3
	 */
	public static int calculateWithoutTwoConsecutive0sBitStrings(int n) {
		int length = n+1;
		if (length < 2) length = 2;
		int[] counter = new int[length];
		counter[0] = 1;
		counter[1] = 2;
		
		int current = 2;
		while (current <= n) {
			counter[current] = counter[current-1] + counter[current-2];  
			current++;
		}
		return counter[n];
	}
	

	/**
	 * Section 8.1 Exercise 8
	 */
	public static int calculateWithThreeConsecutive0sBitStrings(int n) {
		int length = n+1;
		if (length < 3) length = 3;
		int[] counter = new int[length];
		counter[0] = 0;
		counter[1] = 0;
		counter[2] = 0; 
		
		int current = 3;
		while (current <= n) {
			counter[current] = counter[current-1] + counter[current-2] + counter[current-3] + power(2, current-3);  
			current++;
		}
		return counter[n];
	}

	/**
	 * Section 8.1 Exercise 9
	 */
	public static int calculateWithoutThreeConsecutive0sBitStrings(int n) {
		int length = n+1;
		if (length < 3) length = 3;
		int[] counter = new int[length];
		counter[0] = 1;
		counter[1] = 2;
		counter[2] = 4;
		
		int current = 3;
		while (current <= n) {
			counter[current] = counter[current-1] + counter[current-2] + counter[current-3];  
			current++;
		}
		return counter[n];
	}
	
	/**
	 * Section 8.1 Exercise 10
	 */
	public static int calculateContain01BitString(int n) {
		int length = n+1;
		if (length < 2) length = 2;
		int[] counter = new int[length];
		counter[0] = 0;
		counter[1] = 0;
		
		int current = 2;
		while (current <= n) {
			counter[current] = counter[current-1] + power(2, current-1) - 1;  
			current++;
		}
		return counter[n];
	}
	
	/**
	 * Section 8.1 Exercise 13
	 */
	public static int calculateWithoutTwoConsecutive0sTernaryString(int n) {
		int length = n+1;
		if (length < 2) length = 2;
		int[] counter = new int[length];
		counter[0] = 1;
		counter[1] = 3;
		
		int current = 2;
		while (current <= n) {
			counter[current] = 2 * (counter[current-1] + counter[current-2]);  
			current++;
		}
		return counter[n];
	}

	/**
	 * Section 8.1 Exercise 14
	 */
	public static int calculateWithTwoConsecutive0sTernaryString(int n) {
		int length = n+1;
		if (length < 2) length = 2;
		int[] counter = new int[length];
		counter[0] = 0;
		counter[1] = 0;
		
		int current = 2;
		while (current <= n) {
			counter[current] = 2 * (counter[current-1] + counter[current-2]) + power(3, current-2);  
			current++;
		}
		return counter[n];
	}

	/**
	 * Section 8.1 Exercise 15
	 */
	public static int calculateWithoutTwoConsecutive0sOr1sTernaryString(int n) {
		int length = n+1;
		if (length < 2) length = 2;
		int[] counter = new int[length];
		counter[0] = 1;
		counter[1] = 3;
		
		int current = 2;
		while (current <= n) {
			counter[current] = 2 * counter[current-1] + counter[current-2];  
			current++;
		}
		return counter[n];
	}

	/**
	 * Section 8.1 Exercise 16
	 */
	public static int calculateWithTwoConsecutive0sOr1sTernaryString(int n) {
		int length = n+1;
		if (length < 2) length = 2;
		int[] counter = new int[length];
		counter[0] = 0;
		counter[1] = 0;
		
		int current = 2;
		while (current <= n) {
			counter[current] = 2 * counter[current-1] + counter[current-2] + 2 * power(3, current-2);  
			current++;
		}
		return counter[n];
	}
	
	/**
	 * Section 8.1 Exercise 17
	 */
	public static int calculateWithoutConsecutiveSymbolsTernaryString(int n) {
		int length = n+1;
		if (length < 2) length = 2;
		int[] counter = new int[length];
		counter[0] = 1;
		counter[1] = 3;
		
		int current = 2;
		while (current <= n) {
			counter[current] = 2 * counter[current-1];  
			current++;
		}
		return counter[n];
	}

	/**
	 * Section 8.1 Exercise 18
	 */
	public static int calculateWithConsecutiveSymbolsTernaryString(int n) {
		int length = n+1;
		if (length < 2) length = 2;
		int[] counter = new int[length];
		counter[0] = 0;
		counter[1] = 0;
		
		int current = 2;
		while (current <= n) {
			counter[current] = 2 * counter[current-1] + power(3, current-1);  
			current++;
		}
		return counter[n];
	}
	
	/**
	 * Calculate base^n 
	 */
	private static int power(int base, int n) {
		int result = 1;
		int count = 0;
		while (count < n) {
			result = result * base;
			count = count + 1;
		}
		return result;
	}
}
