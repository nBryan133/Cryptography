import java.util.Random;
import java.util.Vector;

public class Hellman
{
    public static void main(String args[])
    {
        Random random = new Random();
        long xa = 1000000 + Math.abs(random.nextLong() % 9000000);
        long xb = 1000000 + Math.abs(random.nextLong() % 9000000);
        long q;
        long K;
        long primRoot;

        do
        {

            q = 1000000 + Math.abs(random.nextLong() % 9000000);
        
        }while(!isPrime(q));
        
        primRoot = getPrimRoot(q);

        System.out.println("Alice:");
        K = hellman(q, primRoot, xa);

        System.out.println("Bob:");
        K = hellman(q, primRoot, xb);

        System.out.println("\nShared K: " + K);
    }

    public static long hellman(long q, long primRoot, long x)
    { 
        long y = fPower(primRoot, x, q);
        System.out.println("Y: " + y);

        long k = fPower(y, x, q);

        return k;
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