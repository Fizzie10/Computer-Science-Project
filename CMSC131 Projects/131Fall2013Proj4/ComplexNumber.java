
public class ComplexNumber {

	private final MyDouble real;   // To be initialized in constructors
	private final MyDouble imag;   // To be initialized in constructors

	//Constructors
	public ComplexNumber(MyDouble realIn, MyDouble imagIn){
		real = realIn;
		imag = imagIn;
	}
	
	public ComplexNumber(MyDouble realIn){
		MyDouble zero = new MyDouble(0);
		real = realIn;
		imag = zero;
	}
	public ComplexNumber(ComplexNumber x){
		real = x.real;
		imag = x.imag;

	}

	//Instance methods
	
	//Gets Real value for madelbrot tools
	public MyDouble getReal(){
		return real;
	}
	//Gets imag value for Mandelbrot tools
	public MyDouble getImag(){
		return imag;
	}
	//Adds real and Reals. Imaginaries and Imaginaries
	public ComplexNumber add(ComplexNumber x){
		MyDouble r = real.add(x.real);
		MyDouble i = imag.add(x.imag);
		ComplexNumber cn = new ComplexNumber(r, i);
		return cn;
	}
	//Subtracts Reals and Subtracts Imaginaries
	public ComplexNumber subtract(ComplexNumber x){
		MyDouble r = real.subtract(x.real);
		MyDouble i = imag.subtract(x.imag);
		ComplexNumber cn = new ComplexNumber (r,i);
		return cn;
	}
	//Uses Foil method to multiply
	public ComplexNumber multiply(ComplexNumber x){
		MyDouble negative1 = new MyDouble(-1);
		MyDouble mR = real.multiply(x.real);
		MyDouble ImR = real.multiply(x.imag);
		MyDouble ImR2 = imag.multiply(x.real);
		MyDouble mI = imag.multiply(x.imag);
		mI=mI.multiply(negative1);
		MyDouble r = mR.add(mI);
		MyDouble i = ImR.add(ImR2);
		ComplexNumber cn = new ComplexNumber (r,i);
		return cn;
	}
	// Follows division formula in order to divide
	public ComplexNumber divide(ComplexNumber x){
		MyDouble ac = real.multiply(x.real);
		MyDouble bd = imag.multiply(x.imag);
		MyDouble acbd = ac.add(bd);
		MyDouble csquare = x.real.multiply(x.real);
		MyDouble dsquare = x.imag.multiply(x.imag);
		MyDouble c2d2 = csquare.add(dsquare);
		MyDouble r = acbd.divide(c2d2);
		MyDouble bc = imag.multiply(x.real);
		MyDouble ad = real.multiply(x.imag);
		MyDouble bcad = bc.subtract(ad);
		MyDouble i = bcad.divide(c2d2);
		ComplexNumber cn = new ComplexNumber (r,i);
		return cn;
	}
	//Checks to see if Complex Numbers are equal
	public boolean equals(ComplexNumber x){
		if(real.equals(x.real)&&imag.equals(x.imag)){
			return true;
		}
		else{
			return false;
		}
	}
	// Compares two complexNumbers (Very helpful when checking to see if something is negative or positive)
	public int compareTo(ComplexNumber x){
		if(norm(this).compareTo(norm(x))==0){
			return 0;
		}
		else if(norm(this).compareTo(norm(x))<0){
			return -1;	
		}
		else{
			return 1;
		}
	}
	//Sets the correct format for the ComplexNumber
	public String toString(){
		MyDouble zero = new MyDouble(0);
		if(real.compareTo(zero)!=0){
			if(imag.compareTo(zero)<0){
				return real.toString() + "-"+ imag.abs().toString()+"i";
			}else if(imag.compareTo(zero)>0){
				return real.toString() + "+" + imag.toString()+"i";
			}else{
				return real.toString() + "+ 0i";
			}
		}
		else{
			if(imag.compareTo(zero)<0){
				return "0" + "-" + imag.abs().toString()+"i";
			}else if(imag.compareTo(zero)>0){
				return "0" + "+" + imag.toString()+"i";
			}else{
				return "0";
			}
		}

	}

	//Public Static Methods
	
	//Puts ComplexNumbers in a "norm" format
	public static MyDouble norm(ComplexNumber x){
		MyDouble a = x.real;
		MyDouble b = x.imag;
		MyDouble a2 = a.multiply(a);
		MyDouble b2 = b.multiply(b);
		MyDouble a2b2 = a2.add(b2);
		MyDouble norm = a2b2.sqrt();
		return norm;

	}
	//Takes the string and changes it into an actual ComplexNumber by making them doubles, then mydoubles, then putting the MyDoubles into the ComplexNumber
	public static ComplexNumber parseComplexNumber(String x){
		MyDouble zero = new MyDouble(0);
		MyDouble zero1 = new MyDouble(0);
		ComplexNumber parsed = new ComplexNumber(zero,zero1);
		String s = x;
		s.replaceAll(" ", "");
		String x1 = s;
		if(x1.indexOf("+")>0){
			int stophere = x1.lastIndexOf("+");
			String realstring = x1.substring(0,stophere);
			double realdouble = Double.parseDouble(realstring);
			MyDouble realmydouble = new MyDouble(realdouble);
			String imagstring = x1.substring(stophere+1,x1.length()-1);
			double imagdouble = Double.parseDouble(imagstring);
			MyDouble imagmydouble = new MyDouble(imagdouble);

			parsed = new ComplexNumber(realmydouble,imagmydouble);
		}else{
			int stophere2 = x1.lastIndexOf("-");
			String realstring2 = x1.substring(0,stophere2);
			double realdouble2 = Double.parseDouble(realstring2);
			MyDouble realmydouble2 = new MyDouble(realdouble2);
			String imagstring2 = x1.substring(stophere2+1,x1.length()-1);
			double imagdouble2 = Double.parseDouble(imagstring2);
			MyDouble imagmydouble2 = new MyDouble(imagdouble2);

			parsed = new ComplexNumber(realmydouble2,imagmydouble2);
		}
		return parsed;
	}


	/* STUDENTS: Put your methods here, as described in the project description.
	 * IMPORTANT:  You may NOT call the toString method for the MyDouble class except
	 * while you are writing the toString method for the Complex class.  You may NOT
	 * call the toString method of the Complex class ANYWHERE.  If you don't adhere
	 * to this rule, you will fail some (or possibly all) release tests. */
}
