package model;

import java.math.*;
import java.util.*;

/**
 * Paillier class to generate private and public keys of variable bit length
 * @author larvinloy
 *
 */
public class Paillier
{
    private final int CERTAINTY = 64;       // certainty with which primes are generated: 1-2^(-CERTAINTY)
    private int modLength;                  // length in bits of the modulus n
    private BigInteger p;                   // a random prime
    private BigInteger q;                   // a random prime (distinct from p)
    private BigInteger lambda;              // lambda = lcm(p-1, q-1) = (p-1)*(q-1)/gcd(p-1, q-1)
    private BigInteger n;                   // n = p*q
    private BigInteger nsquare;             // nsquare = n*n
    private BigInteger g;                   // a random integer in Z*_{n^2}
    private BigInteger mu;                  // mu = (L(g^lambda mod n^2))^{-1} mod n, where L(u) = (u-1)/n
    
    public Paillier(int modLengthIn) throws Exception
    {
        if (modLengthIn < 8)
            throw new Exception("Paillier(int modLength): modLength must be >= 8");
        
        modLength = modLengthIn;
        
        generateKeys();
    }
    
    public BigInteger getP()
    {
        return p;
    }

    public BigInteger getQ()
    {
        return q;
    }

    public BigInteger getLambda()
    {
        return lambda;
    }

    public int getModLength()
    {
        return modLength;
    }

    public BigInteger getN()
    {
        return n;
    }

    public BigInteger getNsquare()
    {
        return nsquare;
    }

    public BigInteger getG()
    {
        return g;
    }

    public BigInteger getMu()
    {
        return mu;
    }

    public void generateKeys()
    {
        p = new BigInteger(modLength / 2, CERTAINTY, new Random());     // a random prime
        
        do
        {
            q = new BigInteger(modLength / 2, CERTAINTY, new Random()); // a random prime (distinct from p)
        }
        while (q.compareTo(p) == 0);

        // lambda = lcm(p-1, q-1) = (p-1)*(q-1)/gcd(p-1, q-1)
        lambda = (p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE))).divide(
                p.subtract(BigInteger.ONE).gcd(q.subtract(BigInteger.ONE)));
        
        n = p.multiply(q);              // n = p*q
        nsquare = n.multiply(n);        // nsquare = n*n
        
        do
        {
            // generate g, a random integer in Z*_{n^2}
            g = randomZStarNSquare();
        }
        // verify g: gcd(L(g^lambda mod n^2), n) = 1, where L(u) = (u-1)/n
        while (g.modPow(lambda, nsquare).subtract(BigInteger.ONE).divide(n).gcd(n).intValue() != 1);
        
        // mu = (L(g^lambda mod n^2))^{-1} mod n, where L(u) = (u-1)/n
        mu = g.modPow(lambda, nsquare).subtract(BigInteger.ONE).divide(n).modInverse(n);
    }
    
    public BigInteger encrypt(BigInteger m) throws Exception
    {
        // if m is not in Z_n
        if (m.compareTo(BigInteger.ZERO) < 0 || m.compareTo(n) >= 0)
        {
            throw new Exception("Paillier.encrypt(BigInteger m): plaintext m is not in Z_n");
        }
        
        // generate r, a random integer in Z*_n
        BigInteger r = randomZStarN();
        
        // c = g^m * r^n mod n^2
        return (g.modPow(m, nsquare).multiply(r.modPow(n, nsquare))).mod(nsquare);
    }

    public BigInteger encrypt(BigInteger m, BigInteger r) throws Exception
    {
        // if m is not in Z_n
        if (m.compareTo(BigInteger.ZERO) < 0 || m.compareTo(n) >= 0)
        {
            throw new Exception("Paillier.encrypt(BigInteger m, BigInteger r): plaintext m is not in Z_n");
        }
        
        // if r is not in Z*_n
        if (r.compareTo(BigInteger.ZERO) < 0 || r.compareTo(n) >= 0 || r.gcd(n).intValue() != 1)
        {
            throw new Exception("Paillier.encrypt(BigInteger m, BigInteger r): random integer r is not in Z*_n");
        }
        
        // c = g^m * r^n mod n^2
        return (g.modPow(m, nsquare).multiply(r.modPow(n, nsquare))).mod(nsquare);
    }

    public BigInteger decrypt(BigInteger c) throws Exception
    {
        // if c is not in Z*_{n^2}
        if (c.compareTo(BigInteger.ZERO) < 0 || c.compareTo(nsquare) >= 0 || c.gcd(nsquare).intValue() != 1)
        {
            throw new Exception("Paillier.decrypt(BigInteger c): ciphertext c is not in Z*_{n^2}");
        }
        
        // m = L(c^lambda mod n^2) * mu mod n, where L(u) = (u-1)/n
        return c.modPow(lambda, nsquare).subtract(BigInteger.ONE).divide(n).multiply(mu).mod(n);
    }
    
    
    // return a random integer in Z_n
    public BigInteger randomZN()
    {
        BigInteger r;
        
        do
        {
            r = new BigInteger(modLength, new Random());
        }
        while (r.compareTo(BigInteger.ZERO) <= 0 || r.compareTo(n) >= 0);
        
        return r;
    }
    
    // return a random integer in Z*_n
    public BigInteger randomZStarN()
    {
        BigInteger r;
        
        do
        {
            r = new BigInteger(modLength, new Random());
        }
        while (r.compareTo(n) >= 0 || r.gcd(n).intValue() != 1);
        
        return r;
    }
    
    // return a random integer in Z*_{n^2}
    public BigInteger randomZStarNSquare()
    {
        BigInteger r;
        
        do
        {
            r = new BigInteger(modLength * 2, new Random());
        }
        while (r.compareTo(nsquare) >= 0 || r.gcd(nsquare).intValue() != 1);
        
        return r;
    }
}
