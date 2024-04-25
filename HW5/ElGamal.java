import java.util.Random;
import java.util.Vector;

public class ElGamal {
    
    public static void main(String args[])
    {
        Random random = new Random();
        long xa;
        long ya;
        long q;
        long decrytedMsg;

        long[] c = new long[2];

        long msg;
        long pub;
        long primRoot;

        do
        {
            q = 1000000 + Math.abs(random.nextLong() % 9000000);
        
        }while(!isPrime(q));

        xa = 1000000 + Math.abs(random.nextLong() % 9000000);
        
        primRoot = getPrimRoot(q);

        ya = fPower(primRoot, xa, q);

        msg = 1000000 + Math.abs(random.nextLong() % 9000000);

        System.out.println("Og msg: " + msg);

        c = elEncrypt(q, msg, ya, primRoot);

        decrytedMsg = elDecrypt(q, xa, c[0], c[1]);

        System.out.println("Decryted from ElGamal: " + decrytedMsg);


    }

    public static long[] elEncrypt(long q, long msg, long y, long primRoot)
    { 
        Random random = new Random();

        long[] result = new long[2];

        long k = random.nextLong(q - 1) + 1;
        long K = fPower(y, k, q);

        result[0] = fPower(primRoot, k, q);
        result[1] = (msg * K) % q;

        return result;
    }

    public static long elDecrypt(long q, long x, long c1, long c2)
    {
        long K = fPower(c1, x, q);

        long msg = c2 * modInverse(K, q) % q;

        return msg;
    }

    public static long modInverse(long base, long mod)
    {
        base = base % mod;

        for(long i = 1; i < mod; i++)
        {
            if((base * i) % mod == 1)
            {
                return i;
            }
        }
        return -1;
    }

    public static long getPrimRoot(long num)
    {
        Random random = new Random();

        Vector<Long> factors = new Vector<Long>();
        Vector<Long> primRoots = new Vector<Long>();

        long buf = num - 1;

        for(long i = 2; i * i <= buf; i++)
        {
            while(buf % i == 0)
            {
                factors.addElement(i);
                buf /= i;
            }
        }
        if(buf > 1)
        {
            factors.addElement(buf);
        }

        for(long i = 2; i < num; i++)
        {
            boolean isPrimRoot = true;
            for(long factor : factors)
            {
                if(fPower(i, (num  - 1) / factor, num) == 1)
                {
                    isPrimRoot = false;
                    break;
                }
            }

            if(isPrimRoot)
            {
                primRoots.addElement(i);
            }
        }

        if(primRoots.size() > 0)
        {
            long randPrimRoot = primRoots.elementAt(random.nextInt(primRoots.size()));

            return randPrimRoot;
        }
        else
        {
            return -1;
        }

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
}
