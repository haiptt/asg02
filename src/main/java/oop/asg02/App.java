package oop.asg02;

public class App {

	public static void main(String args[]){
		BigInteger bigInt1 = new BigInteger("111111111111111111111");
        BigInteger bigInt2 = new BigInteger("1");
        BigInteger difference = bigInt1.subtract(bigInt2);
	    difference.toString();
	}
}