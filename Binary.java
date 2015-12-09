/* Gabriel Marks
APCS1 pd10
HW43 -- This or That
2015-12-07 */

public class Binary implements Comparable{
    private int value;
    public Binary() {
	value = 0;
    }
    public Binary(int val) {
	value = val;
    }
    public Binary(String binRep) {
	value = binToDec(binRep);
    }
    public String toString() { 
	return decToBin(value);
    }
	
    public boolean equals(Object o) {
	if(o instanceof Binary) {
	    Binary b = (Binary)o;
	    return value == b.value;
	}
	else return false;
    }
    public int compareTo(Object o) {
	if(o instanceof Binary) {
	    Binary b = (Binary)o;
	    return value - b.value;
	}
	else {
	    throw new ClassCastException(); //specified in java api
	}
    }
    public static int binToDec(String binary) {
	int ret = 0;
	for (int i = 0; i < binary.length(); i++) {
	    int exp = binary.length() - i - 1; //power of two
	    if(binary.charAt(i) == '1') ret += Math.pow(2,exp); //adds corresponding power of two
	}
	return ret;
    }
    public static int binToDecR(String binary) {
	if(binary.length() == 1) {
	    return Integer.parseInt(binary);
	}
	return 2 * binToDecR(binary.substring(0,binary.length()-1)) +
	    Integer.parseInt(binary.substring(binary.length()-1));
    }
    public static String decToBin(int val) {
	if(val == 0) return "0";
	String ret = "";
	while(val > 0) {
	    ret = val % 2 + ret;
	    val /= 2;
	}
	return ret;
    }
    public static String decToBinR(int val) {
	String ret = decToBinLoop(val);
	if(ret.length() != 1 && ret.substring(0,1).equals("0")) return ret.substring(1);
	return ret.substring(0);
    }
    public static String decToBinLoop( int val) {
	if(val == 0)
	    return "0";
	return decToBinR(val/2) + val%2;
    }
    public static void main(String[] args) {
	System.out.println(decToBin(10)); //1010
	System.out.println(binToDec("1010")); //10
	for(int i = 0; i <= 100; i++) {
	    System.out.println(binToDec(decToBin(i*2))); //should print 0 to 200, with step of 2  
	}
	System.out.println(decToBinR(10)); //1010
	System.out.println(binToDecR("1010")); //10
	for(int i = 0; i <= 100; i++) {
	    System.out.println(binToDecR(decToBinR(i*2))); //should print 0 to 200, with step of 2  
	}
	Binary bin1 = new Binary();
	System.out.println(bin1); //0
	Binary bin2 = new Binary("1001");
	System.out.println(bin2); //1001
	Binary bin3 = new Binary(14);
	System.out.println(bin3); //1110
	String x = "1001";
	Binary bun2 = new Binary("1001");
	System.out.println(bin2.equals(bun2)); //true
	System.out.println(bin2.equals(x)); //false
	System.out.println(bin2.compareTo(bun2)); //0
	System.out.println(bin2.compareTo(bin1)); //positive
	System.out.println(bin2.compareTo(bin3)); //negative
	//System.out.println(bin2.compareTo(x)); //error b/c string is not comparable
    }
}
