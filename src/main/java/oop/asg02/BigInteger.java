package oop.asg02;

public class BigInteger {
	private String sdigit[];

	public BigInteger() {
		sdigit = null;
	}

	public BigInteger(long val) {
		String s = String.valueOf(val);
		int size = s.length();
		sdigit = new String[size];
		for (int i = 0; i < size; i++) {
			sdigit[i] = s.substring(i, i + 1);
		}
	}

	public BigInteger(String init) {
		int size = init.length();
		sdigit = new String[size];
		for (int i = 0; i < size; i++) {
			sdigit[i] = init.substring(i, i + 1);
		}
	}

	public String toString() {
		String result = "";

		for (int i = 0; i < sdigit.length; i++)
			result = result + sdigit[i];
		if (sdigit.length <= 13) {
			long longResult = Long.parseLong(result);
			String finalResult = "" + longResult + "";
			System.out.println(finalResult);
			return finalResult;
		}
		System.out.println(result);
		return result;
	}

	public boolean equals(Object other) {
		return toString().equals(other.toString());
	}

	public long toLong() {
		String s = "";
		long result = 0;
		if (sdigit.length <= 10) {
			for (int i = 0; i < sdigit.length; i++) {
				s = s + sdigit[i];
			}
			result = Long.parseLong(s);
		} else {
			System.out.println("So vuot qua kieu long.");
		}
		System.out.println(result);
		return result;
	}

	public BigInteger add(BigInteger other) {
		int sizeOfThis = sdigit.length;
		int sizeOfOther = other.sdigit.length;

		// Dao nguoc cac phan tu trong mang.
		String newSdigit[] = new String[sizeOfThis];
		for (int i = 0; i < sizeOfThis; i++) {
			newSdigit[i] = sdigit[sizeOfThis - i - 1];
		}
		String newOtherDigit[] = new String[sizeOfOther];
		for (int i = 0; i < sizeOfOther; i++) {
			newOtherDigit[i] = other.sdigit[sizeOfOther - i - 1];
		}

		BigInteger result = new BigInteger();
		BigInteger finalResult = new BigInteger();

		if (sizeOfThis >= sizeOfOther) {
			long carry = 0;
			result.sdigit = new String[sizeOfThis];
			for (int i = 0; i < sizeOfOther; i++) {
				long digSum = Long.parseLong(newOtherDigit[i])
						+ Long.parseLong(newSdigit[i]) + carry;

				result.sdigit[i] = String.valueOf(digSum % 10);
				carry = digSum / 10;
			}
			for (int i = sizeOfOther; i < sizeOfThis; i++) {
				long digSum = carry + Long.parseLong(newSdigit[i]);
				result.sdigit[i] = String.valueOf(digSum % 10);
				carry = digSum / 10;
			}
			if(carry > 0){
				String []temp = new String[sizeOfThis + 1];
				for(int i = 0; i < sizeOfThis; i++){
					temp[i] = result.sdigit[i];
				}
				temp[sizeOfThis] = String.valueOf(carry);
				finalResult.sdigit = new String[sizeOfThis + 1];
				for (int i = 0; i < sizeOfThis +1; i++) {
					finalResult.sdigit[i] = temp[sizeOfThis - i];
				}
			}
			else{
				finalResult.sdigit = new String[sizeOfThis];
				for (int i = 0; i < sizeOfThis; i++) {
					finalResult.sdigit[i] = result.sdigit[sizeOfThis - i - 1];
				}
			}
	
		}else {
			long carry = 0;
			result.sdigit = new String[sizeOfOther];
			for (int i = 0; i < sizeOfThis; i++) {
				long digSum = Long.parseLong(newOtherDigit[i])
						+ Long.parseLong(newSdigit[i]) + carry;

				result.sdigit[i] = String.valueOf(digSum % 10);
				carry = digSum / 10;
			}
			for (int i = sizeOfThis; i < sizeOfOther; i++) {
				long digSum = carry + Long.parseLong(newOtherDigit[i]);
				result.sdigit[i] = String.valueOf(digSum % 10);
				carry = digSum / 10;
			}
			String []tmp = new String[sizeOfOther + 1];
			for(int i = 0; i < sizeOfOther; i++){
				tmp[i] = result.sdigit[i];
			}
			tmp[sizeOfOther] = String.valueOf(carry);
			finalResult.sdigit = new String[sizeOfOther + 1];
			for (int i = 0; i < sizeOfOther + 1; i++) {
				finalResult.sdigit[i] = tmp[sizeOfOther - i];
			}
		}
		
		return finalResult;
	}

	public BigInteger subtract(BigInteger other) {
		int sizeOfThis = sdigit.length;
		int sizeOfOther = other.sdigit.length;

		// Dao nguoc cac phan tu trong mang.
		String newSdigit[] = new String[sizeOfThis];
		for (int i = 0; i < sizeOfThis; i++) {
			newSdigit[i] = sdigit[sizeOfThis - i - 1];
		}
		String newOtherDigit[] = new String[sizeOfOther];
		for (int i = 0; i < sizeOfOther; i++) {
			newOtherDigit[i] = other.sdigit[sizeOfOther - i - 1];
		}

		BigInteger result = new BigInteger();
		BigInteger finalResult = new BigInteger();

		if (sizeOfThis >= sizeOfOther) {
			result.sdigit = new String[sizeOfThis];
			long carry = 0;
			for (int i = 0; i < sizeOfOther; i++) {
				if (Long.parseLong(newSdigit[i]) < Long
						.parseLong(newOtherDigit[i])) {
					carry = 1;
					long digSub = Long.parseLong(newSdigit[i]) + carry * 10
							- Long.parseLong(newOtherDigit[i]);
					result.sdigit[i] = String.valueOf(digSub % 10);
					newSdigit[i + 1] = String.valueOf((Long
							.parseLong(newSdigit[i + 1]) + 10 - carry) % 10);
				} else {
					carry = 0;
					long digSub = Long.parseLong(newSdigit[i]) + carry * 10
							- Long.parseLong(newOtherDigit[i]);
					result.sdigit[i] = String.valueOf(digSub % 10);
				}
			}
			newSdigit[sizeOfOther] = String.valueOf((Long
					.parseLong(newSdigit[sizeOfOther]) + carry) % 10);
			for (int i = sizeOfOther; i < sizeOfThis; i++) {
				if (Long.parseLong(newSdigit[i]) > 0) {
					long digSub = Long.parseLong(newSdigit[i]) + 10 - carry;
					result.sdigit[i] = String.valueOf(digSub % 10);
					carry = 0;
				} else {
					long digSub = Long.parseLong(newSdigit[i]) + 10 - carry;
					result.sdigit[i] = String.valueOf(digSub % 10);
					carry = 1;
				}
			}
			finalResult.sdigit = new String[sizeOfThis];
			for (int i = 0; i < sizeOfThis; i++) {
				finalResult.sdigit[i] = result.sdigit[sizeOfThis - i - 1];
			}
		}
		
		return finalResult;
	}

	public int compareTo(BigInteger other) {
		if(sdigit.length > other.sdigit.length){
			return 1;
		}
		else if(sdigit.length < other.sdigit.length){
			return -1;
		}
		else {
			for(int i = 0; i < sdigit.length; i++){
				if(Long.parseLong(sdigit[i]) > Long.parseLong(other.sdigit[i]))
					return 1;
				else if(Long.parseLong(sdigit[i]) < Long.parseLong(other.sdigit[i]))
					return -1;
			}
		}
		return 0;
	}
	public BigInteger clone(){
		BigInteger newBigInt = new BigInteger();
		newBigInt.sdigit = new String[sdigit.length];
		for(int i = 0; i < sdigit.length; i++){
			newBigInt.sdigit[i] = sdigit[i];
		}
		return newBigInt;
	}
}