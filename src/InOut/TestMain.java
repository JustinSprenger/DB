package InOut;

public class TestMain {

	public static void main(String[] args) {
		Read sc = new Read();
		int z;
		char c;
		
		System.out.println("zahl eingeben");
		z = sc.readInt();
		System.out.println(z);
		
		System.out.println("buchstaben eingeben");
		c = sc.readChar();
		System.out.println(c);

	}

}
