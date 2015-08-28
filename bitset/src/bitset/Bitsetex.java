package bitset;

import java.util.BitSet;

public class Bitsetex {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BitSet b1 = new BitSet(16);
		BitSet b2 = new BitSet(16);
		
		int i;
		for(i=0;i<16;i++){
			if(i%2==0)
				b1.set(i);
			if(i%5!=0)
				b2.set(i);
		}
		System.out.println("Initial array");
		printlist(b1);
		printlist(b2);
		System.out.println("AND: ");
		b2.and(b1);
		printlist(b2);
		System.out.println("OR: ");
		b2.or(b1);
		printlist(b2);
		System.out.println("XOR: ");
		b2.xor(b1);
		printlist(b2);

	}
	public static void printlist(BitSet bs){	
			System.out.println(bs);		
	}

}
