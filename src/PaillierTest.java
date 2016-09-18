import java.math.BigDecimal;
import java.math.BigInteger;

public class PaillierTest
{

	public static void main(String[] args) throws Exception
	{
		Paillier paillier = new Paillier(512);
		
		BigInteger m1 = new BigInteger("998");
		BigInteger m2 = new BigInteger("3");
		/* encryption*/
		BigInteger em1 = paillier.encrypt(m1);
		BigInteger em2 = paillier.encrypt(m2);
		/* printout encrypted text*/
		System.out.println(em1);
		System.out.println(em2);
		/* printout decrypted text */
		System.out.println(paillier.decrypt(em1).toString());
		System.out.println(paillier.decrypt(em2).toString());
		
		/* test homomorphic properties -> D(E(m1)*E(m2) mod n^2) = (m1 + m2) mod n */
		BigInteger product_em1em2 = em1.multiply(em2).mod(paillier.getNsquare());
		BigInteger sum_m1m2 = m1.add(m2).mod(paillier.getN());
		System.out.println("original sum: " + sum_m1m2.toString());
		System.out.println("decrypted sum: " + paillier.decrypt(product_em1em2).toString());
		
		/* test homomorphic properties -> D(E(m1)^m2 mod n^2) = (m1*m2) mod n */
		BigInteger expo_em1m2 = em1.modPow(m2, paillier.getNsquare());
		BigInteger prod_m1m2 = m1.multiply(m2).mod(paillier.getN());
		
		
		System.out.println("original product: " + prod_m1m2.toString());
		System.out.println("decrypted product: " + paillier.decrypt(expo_em1m2).toString());
		
		
		/*Division*/
		double fdivisor = (double) (1.0/333.0);
		int decimalPoint = 0;
		while(fdivisor < 100)
		{
			fdivisor*=10;
			decimalPoint++;
		}
		System.out.println(fdivisor + " " + decimalPoint);
		BigInteger divisor = new BigInteger(String.valueOf((int)fdivisor));
		
		
		
		BigInteger div = em1.modPow(divisor, paillier.getNsquare());
		System.out.println("Division encrypted: " + div.toString());
		System.out.println("Division decrypted: " + paillier.decrypt(div).toString());

	}

}
