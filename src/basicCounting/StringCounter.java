package basicCounting;

import java.io.PrintWriter;


public class StringCounter {
	private int base = 2;
	private int length = 0;
	private byte[] string = null;
	private PrintWriter writer = null;

	public StringCounter(int base, int length) {
		this.base = base;
		this.length = length;
		if (length > 0) string = new byte[length]; 
	}
	
	public int getBase() {
		return base;
	}
	
	public void setWriter(PrintWriter writer) {
		this.writer = writer;
	}
	
	public int counting(StringFilter filter) {
		if (length == 0) {
			if (filter.accept(null)) {
				if (writer != null) writer.println("Accept null string!");
				return 1;
			} else {
				if (writer != null) writer.println("DO NOT accept null string!");
				return 0;
			}
		}
		
		setToFirstString();
		int result = 0;
		while (true) {
			if (filter.accept(string)) {
				result++;
				if (writer != null) writer.println(getCurrentString() + ", accept " + result);
			} else {
				if (writer != null) writer.println(getCurrentString() + ", NOT accept!");
			}
			if (isLastString()) break;
			setToNextString();
		}

		if (writer != null) writer.println("Total " + result + " strings for filter condition " + filter);
		return result;
	}
	
	public String getCurrentString() {
		StringBuffer contents = new StringBuffer();
		for (int i = 0; i < string.length; i++) {
			if (string[i] < 10) contents.append(string[i] + "");
			else {
				int bit = string[i] - 10 + 'A';
				contents.append(bit + "");
			}
		}
		return contents.toString();
	}
	
	private void setToFirstString() {
		for (int i = 0; i < string.length; i++) string[i] = 0;
	}
	
	private void setToNextString() {
		int carry = 1;
		for (int i = string.length-1; i >= 0; i--) {
			int sum = string[i] + carry;
			if (sum < base) {
				string[i] = (byte)sum;
				return;
			} else {
				string[i] = 0;
				carry = 1;
			}
		}
	}
	
	private boolean isLastString() {
		for (int i = 0; i < string.length; i++) {
			if (string[i] < base-1) return false;
		}
		return true;
	}
}
