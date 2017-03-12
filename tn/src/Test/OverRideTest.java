package Test;

public class OverRideTest {

	public static void main(String[] args) {
		A a = new A();
		System.out.println(a.GetA());
		B b = new B();
		System.out.println(b.GetB());
		System.out.println(b.GetA());
	}

}
