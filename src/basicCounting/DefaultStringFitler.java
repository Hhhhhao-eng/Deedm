/**
 * 
 */
package basicCounting;

/**
 * @author xiaocong
 *
 */
public class DefaultStringFitler implements StringFilter {

	@Override
	public boolean accept(byte[] string) {
		return true;
	}

	@Override
	public String toString() {
		return "[All Strings]";
	}
}

/**
 * Section 8.1 Exercise 7, Exercise 14
 */
class WithTwoConsecutive0sFilter implements StringFilter {
	@Override
	public boolean accept(byte[] string) {
		if (string == null) return false;
		
		for (int i = 0; i < string.length-1; i++) {
			if (string[i] == 0 && string[i+1] == 0) return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "[Two Consecutive 0s]";
	}
}

/**
 * Section 8.1 Example 3, Exercise 13
 */
class WithoutTwoConsecutive0sFilter implements StringFilter {
	@Override
	public boolean accept(byte[] string) {
		if (string == null) return true;

		for (int i = 0; i < string.length-1; i++) {
			if (string[i] == 0 && string[i+1] == 0) return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "[Without Two Consecutive 0s]";
	}
}

/**
 * Section 8.1 Exercise 8
 */
class WithThreeConsecutive0sFilter implements StringFilter {
	@Override
	public boolean accept(byte[] string) {
		if (string == null) return false;

		for (int i = 0; i < string.length-2; i++) {
			if (string[i] == 0 && string[i+1] == 0 && string[i+2] == 0) return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "[With Three Consecutive 0s]";
	}
}


/**
 * Section 8.1 Exercise 9
 */
class WithoutThreeConsecutive0sFilter implements StringFilter {
	@Override
	public boolean accept(byte[] string) {
		if (string == null) return true;

		for (int i = 0; i < string.length-2; i++) {
			if (string[i] == 0 && string[i+1] == 0 && string[i+2] == 0) return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "[Without Three Consecutive 0s]";
	}
}


/**
 * Section 8.1 Exercise 10
 */
class Contain01Filter implements StringFilter {
	@Override
	public boolean accept(byte[] string) {
		if (string == null) return false;

		for (int i = 0; i < string.length-1; i++) {
			if (string[i] == 0 && string[i+1] == 1) return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "[Contain 01]";
	}
}


/**
 * Section 8.1 Exercise 15
 */
class WithoutTwoConsecutive0sOr1sFilter implements StringFilter {
	@Override
	public boolean accept(byte[] string) {
		if (string == null) return true;

		for (int i = 0; i < string.length-1; i++) {
			if (string[i] == 0 && string[i+1] == 0) return false;
			if (string[i] == 1 && string[i+1] == 1) return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "[Without Consecutive 0s or 1s]";
	}
}

/**
 * Section 8.1 Exercise 15
 */
class WithTwoConsecutive0sOr1sFilter implements StringFilter {
	@Override
	public boolean accept(byte[] string) {
		if (string == null) return false;

		for (int i = 0; i < string.length-1; i++) {
			if (string[i] == 0 && string[i+1] == 0) return true;
			if (string[i] == 1 && string[i+1] == 1) return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "[With Consecutive 0s or 1s]";
	}
}

/**
 * Section 8.1 Exercise 17
 */
class WithoutConsecutiveSymbolsFilter implements StringFilter {
	@Override
	public boolean accept(byte[] string) {
		if (string == null) return true;

		for (int i = 0; i < string.length-1; i++) {
			if (string[i] == string[i+1]) return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "[Without Consecutive Symbols]";
	}
}

/**
 * Section 8.1 Exercise 18
 */
class WithConsecutiveSymbolsFilter implements StringFilter {
	@Override
	public boolean accept(byte[] string) {
		if (string == null) return false;

		for (int i = 0; i < string.length-1; i++) {
			if (string[i] == string[i+1]) return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "[With Consecutive Symbols]";
	}
}
