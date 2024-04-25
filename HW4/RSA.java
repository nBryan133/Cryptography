public class RSA
{
    public static void main(String[] args)
    {
        long result[] = new long[2];

        result = RSA_encrypt(1070777, 948047, 1223, 1987);

        System.out.println("newMsg: " + result[0]);
        System.out.println("invKey: " + result[1]);

        result = RSA_decrypt(result[0], result[1], 1223, 1987);

        System.out.println("\nogMsg: " + result[0]);
        System.out.println("ogKey: " + result[1]);
    }

    static boolean isPrime(long num)
    {
        boolean isPrime = true;

        for(int n = 2; n <= num / 2; n++)
        {
            if(num % n == 0)
            {
                isPrime = false;
                break;
            }
        }

        return isPrime;
    }

    static long fPower(long base, long power, long mod)
    {
        long a = base;
        long b = 1;

        while(power > 0)
        {
            if(power % 2 == 1 )
            {
                b = (b * a) % mod;
            }

            a = (a * a) % mod;

            power = power / 2;
        }

        return b;
    }

    static long[] eea(long a, long b)
    {
        
        long[] result = new long[3];

        if(b == 0)
        {
            result[0] = a;
            result[1] = 1;
            result[2] = 0;
        }
        else
        {
            long[] temp = eea(b, a % b);
            result[0] = temp[0];
            result[1] = temp[2];
            result[2] = temp[1] - (a / b) * temp[2];
        }

        return result;
    }

    static long inverseKeyGen(long key, long phin)
    {
        long invKey = eea(phin, key)[2];

        return invKey;
    }

    //takes variables message, key, and two primes that are labeled p and q and then returns a result in an array
    static long[] RSA_encrypt(long message, long key, long p, long q)
    {
        long result[] = new long[2];          //holds result of RSA (positon 0: new message)(position 1: inverse key)

        long n = p * q;

        long phin = (p - 1) * (q - 1);

        long invKey = inverseKeyGen(key, phin);

        long newMsg = fPower(message, key, n);

        result[0] = newMsg;
        result[1] = invKey;
        
        return result;
    }

    static long[] RSA_decrypt(long message, long key, long p, long q)
    {
        long[] result = new long[2];

        long n = p * q;

        long phin = (p - 1) * (q - 1);

        long newMsg = fPower(message, key, n);

        long invKey = inverseKeyGen(key, phin);

        result[0] = newMsg;
        result[1] = invKey;

        return result;
    }
}