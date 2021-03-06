/* Team Something: Gabriel Marks, Sebastian Dittgen
APCS1 pd10
HW41 -- In America, the Driver Sits on The Left
2015-12-03 */

public class Rational implements Comparable{

	//instance variables
	private int numerator;
	private int denominator;
	public Rational(){
		numerator = 0;
		denominator = 1;
	}
	//Constructor
	public Rational(int a, int b){
		numerator = a;
		if (b != 0){
			denominator = b;
		}
		else{
			numerator = 0;
			denominator = 1;
		}
	}
	//Mutators
	public int getNum(){
		return numerator;
	}

	public int getDen(){
		return denominator;
	}
	public String toString(){
		return ""+numerator+"/"+denominator;
	}
	public double value(){
		return numerator*(1.0)/denominator;
		// division by a floating point # converts the fraction into a float
	}
	public void multiply(Rational Q){
		this.numerator = this.numerator * Q.numerator;
		this.denominator = this.denominator * Q.denominator;
	}
	public void divide(Rational Q){
		this.numerator = this.numerator * Q.denominator;
		if (Q.numerator != 0){  //makes sure there is no division by 0
			this.denominator = this.denominator * Q.numerator;
		}
		else{
			this.numerator = 0;
			this.denominator = 1;
		}
	}
	public void add(Rational Q){
		this.numerator = this.numerator*Q.denominator
		+ Q.numerator*this.denominator;
		this.denominator = this.denominator*Q.denominator;
	}
	public void subtract(Rational Q){
		this.numerator = this.numerator*Q.denominator -
		Q.numerator*this.denominator;
		this.denominator = this.denominator*Q.denominator;
	}
	//Non static version, Euclid's algorithm, using While loop
	public int GCD(){
		int a = numerator;  //"Backup" so that denominator and numerator are not messed up by Euclid's algo
		int b = denominator;
		while (a != b){   //Loop complete when 1 subtracted by other = 0 aka. When both numbers are equal
			if (a > b){
				a -= b;
			}
			else{
				b -= a;
			}
		}
		return a;
	}


	//Static version, takes 2 int inputs
	public static int GCD(int a, int b){
		while (a != b){   //Loop complete when 1 subtracted by other = 0 aka. When both numbers are equal
			if (a > b){
				a -= b;
			}
			else{
				b -= a;
			}
		}
		return a;
	}

	public void reduce(){
		int gcd = GCD(numerator, denominator);
		numerator /= gcd;
		denominator /= gcd;
	}
    public int compareTo( Object other ) {
	if (other == null) {
	    throw new NullPointerException("Cannot compareTo with null object");
	}
	if (!(other instanceof Comparable)) {
	    throw new ClassCastException("Incorrect type of argument for compareTo");
	}
	
	Comparable o = (Comparable)other;
	if (value() > o.value()) {
	    return 1;
	} else if (value() < o.value()) {
	    return -1;
	} else {
	    return 0;
	}
    }

	public static void main(String[] args){
		Rational george = new Rational();
		Rational marley = new Rational(17,38);
		Rational tigger = new Rational(21,69);
		Rational bigger = new Rational(1,2);
		Rational smaller = new Rational (1,3);
		String weirder = new String("HALLO");
		
		System.out.println(tigger.GCD());
		System.out.println(tigger.GCD(tigger.numerator,tigger.denominator));
		System.out.println(george);
		System.out.println(marley);
		System.out.println(tigger);
		tigger.reduce();
		System.out.println(tigger);
		System.out.println(george.value());
		System.out.println(marley.value());
		System.out.println(tigger.value());
		george.multiply(marley);
		tigger.multiply(tigger);
		System.out.println(george);
		System.out.println(marley);
		System.out.println(tigger);
		tigger.divide(marley);
		System.out.println(tigger);
		tigger.divide(george);
		System.out.println(tigger);
		System.out.println(bigger.compareTo(smaller));  //1
		System.out.println(smaller.compareTo(bigger)); //-1
		System.out.println(bigger.compareTo(bigger)); //0
		try {
		    System.out.println(smaller.compareTo(weirder));
		} catch (Exception e) {
		    System.out.println(e);
		}
		try{
		    System.out.println(smaller.compareTo(null));
		}
		catch(Exception e) {
		    System.out.println(e);
		}
		System.out.println(bigger.compareTo(new Binary("1001")));
		System.out.println(bigger.compareTo(new Hexadecimal("0")));
	}
}
