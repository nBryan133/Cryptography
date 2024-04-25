import java.math.BigInteger;
import java.util.Random;

public class MillerRabin
{
    public static void main(String args[])
    {
        Random random = new Random();
        BigInteger num = new BigInteger(34, random);
        //BigInteger num = new BigInteger("5540083049");

        int prime = 0;
        int nPrime = 0;
        
        if(!num.testBit(0))
        {
            num = num.add(BigInteger.ONE);
        }

        for (int i = 0; i < 25; i++)
        {
            if(millerRabin(num))
            {
                prime++;
            }
            else
            {
                nPrime++;
            }
        }
        
        System.out.println("Prime: " + prime);
        System.out.println("Not Prime: " + nPrime);
        if(prime > nPrime)
        {
            System.out.println(num + " is Prime");
        }
        else
        {
            System.out.println(num + " is not Prime");
        }
    }

    public static boolean millerRabin(BigInteger num)
    {
        Random random = new Random();
        BigInteger a;

        //checks if num is even or odd
        if(num.testBit(0))
        {
            //generates one random prime number of num
            do
            {
                a = new BigInteger(num.bitLength(), random);

            }while (((a.compareTo(BigInteger.TWO) <= 0) || (a.compareTo(num.subtract(BigInteger.ONE)) >= 0)) || (!a.gcd(num).equals(BigInteger.ONE)));

            return isProbPrime(a, num);
        }
        else
        {
            System.out.println("Number is even!!!");

            return false;
        }
    }

    public static boolean isProbPrime(BigInteger a, BigInteger num)
    {

        BigInteger bk[] = new BigInteger[2];
        bk = largestPower(num.subtract(BigInteger.ONE));    //gets k and q

        a = fPower(a, bk[1], num);

        for(BigInteger i = BigInteger.ONE; i.compareTo(bk[0]) <= 0; i = i.add(BigInteger.ONE))
        {

            //System.out.println(a);

            if(a.equals(BigInteger.ONE) || a.equals(num.subtract(BigInteger.ONE)))
            {
                return true;
            }

            
            a = fPower(a, BigInteger.TWO, num);
        }


        return false;
    }

    static BigInteger fPower(BigInteger base, BigInteger power, BigInteger mod)
    {
        BigInteger a = base;
        BigInteger b = BigInteger.ONE;

        while(power.compareTo(BigInteger.ZERO) > 0)
        {
            //System.out.println("power: " + power);
            if(power.testBit(0))
            {
                b = b.multiply(a).mod(mod);
                //System.out.println("new B: " + b);
            }

            a = a.multiply(a).mod(mod);

            //System.out.println("new A: " + a);

            power = power.shiftRight(1);
        }

        return b;
    }

    static BigInteger[] largestPower(BigInteger num)
    {
        BigInteger power = BigInteger.ZERO;
        BigInteger result[] = new BigInteger[2];

        while(num.mod(BigInteger.TWO).equals(BigInteger.ZERO))
        {
            power = power.add(BigInteger.ONE);
            num = num.divide(BigInteger.TWO);
        }

        result[0] = power;
        result[1] = num;

        return result;
    }
}
