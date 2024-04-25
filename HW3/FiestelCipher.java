
public class FiestelCipher{

    public static void main(String[] args)
    {
        //String msg = "1010";
        //String key = "0011";
        String msg = "1011010011010010";
        String key = "1101010110100110";
        //String msg = "01010110000110010100000000100100";
        //String key = "10001010101101011100111100011100";
        //String msg = "1110101110011010101001010011101111100001010100110110101001010110";
        //String key = "1010010011001001001010001000001000100010100010010000010010110010";
        String oldMsg = "";

        int rounds = 16;
        int startRound = 1;
        int decryptRound = 1;
        double hWeight = 0;

        for(int i = 0; i < rounds; i++)
        {

            //System.out.println("key(round: " + (i + 1) + "): " + key);

            oldMsg = msg;

            msg = fEncrypt(msg, key, i);
            //msg = roundHandler(msg, key, i);

            System.out.println("\nEncrypt Round " + (i + 1) + ": " + msg + "\n");

            key = keyShift(key);
            
            hWeight += hWeightCalc(msg, oldMsg);
            
        }

        if((rounds - 1) % 2 == 0)
        {
            startRound = 0;
        }

        System.out.println("----------------------------------------");

        for(int i = startRound; i < rounds; i++)
        {
            key = rKeyShift(key);

            //System.out.println("key(round: " + (i + 1) + "): " + key);

            msg = fDecrypt(msg, key, i);

            System.out.println("\nDecrypt Round " + (decryptRound) + ": " + msg + "\n");
            decryptRound++;
        }

        System.out.println("Avg hWieght: " + hWeight / rounds);
        
    }

    public static double hWeightCalc(String msg, String msg2)
    {
        double hWeight = 0;

        String hMsg = "";

        //XOR L1 with R to get L2
        for(int m = 0; m < msg.length(); m++)
        {
            if(msg.charAt(m) ==  msg2.charAt(m))
            {
                hMsg += '0';
            }
            else
            {
                hMsg += '1';
            }
                    
        }

        for(int i = 0; i < hMsg.length(); i++)
        {
            if(hMsg.charAt(i) == '1')
            {
                hWeight += 1;
            }
        }

        return hWeight;
    }

    public static String fEncrypt(String msg, String key, int round)
    {
        String subKey = "";
        String L1 = "";
        String L2 = "";

        String L = "";
        String R = "";

        char MBuffer[] = msg.toCharArray();

        char LBuffer[] = new char[msg.length() / 2]; 
        char RBuffer[] = new char[msg.length() / 2];
        char subKeyBuf[] = new char[key.length() / 2];

        //put first half of msg into buffer
        for(int n = 0; n < msg.length() / 2; n++)
        {
            LBuffer[n] = MBuffer[n];
        }
            
        L = new String(LBuffer);            //left side of message

        //System.out.println("L: " + L);

        //put second half of msg into buffer
        for(int n = msg.length() / 2, b = 0; n < msg.length(); n++)
        {
            RBuffer[b] = MBuffer[n];
            b++;
        }

        R = new String(RBuffer);            //right side of message

        //System.out.println("R: " + R);

        //get subkey
        for(int n = 0; n < key.length() / 2; n++)
        {
            subKeyBuf[n] = key.charAt(n);
        }

        subKey = new String(subKeyBuf);     //subkey

        //System.out.println("subKey: " + subKey);

        //put L and subkey into roundhandler to get L1
        L1 = roundHandler(L, subKey, round);

        //System.out.println("L1: " + L1);

        L2 = "";

        //XOR L1 with R to get L2
        for(int m = 0, x = 0; m < L1.length(); m++, x++)
        {
            if(L1.charAt(m) ==  R.charAt(x))
            {
                L2 += '0';
            }
            else
            {
                L2 += '1';
            }
                    
        }

        //System.out.println("L2: " + L2);

        //put together R and L2 to get ciphertext
        for(int n = 0, b = 0; n < msg.length(); n++)
        {
            if(n < msg.length() / 2)
            {
                MBuffer[n] = R.charAt(n);
            }
            else
            {
                MBuffer[n] = L2.charAt(b);
                b++;
            }
        }

        return new String(MBuffer);
    }

    public static String fDecrypt(String msg, String key, int round)
    {
        String subKey = "";
        String L1 = "";
        String L2 = "";

        String L = "";
        String R = "";

        char MBuffer[] = msg.toCharArray();

        char RBuffer[] = new char[msg.length() / 2]; 
        char LBuffer[] = new char[msg.length() / 2];
        char subKeyBuf[] = new char[key.length() / 2];

        //put first half of msg into buffer
        for(int n = 0; n < msg.length() / 2; n++)
        {
           RBuffer[n] = MBuffer[n];
        }
             
        R = new String(RBuffer);            //left side of message

        //System.out.println("R: " + R);
 
        //put second half of msg into buffer
        for(int n = msg.length() / 2, b = 0; n < msg.length(); n++)
        {
           LBuffer[b] = MBuffer[n];
           b++;
        }
 
        L2 = new String(LBuffer);            //right side of message

        //System.out.println("L2: " + L2);

        //get subkey
        for(int n = 0; n < key.length() / 2; n++)
        {
            subKeyBuf[n] = key.charAt(n);
        }

        subKey = new String(subKeyBuf);     //subkey

        //System.out.println("subKey: " + subKey);

        L1 = "";

        //XOR L1 with R to get L2
        for(int m = 0, x = 0; m < L2.length(); m++, x++)
        {
            if(L2.charAt(m) ==  R.charAt(x))
            {
                L1 += '0';
            }
            else
            {
                L1 += '1';
            }
                    
        }

        //System.out.println("L1: " + L1);

        L = roundHandler(L1, subKey, round);

        //System.out.println("L: " + L);

        //put together R and L2 to get ciphertext
        for(int n = 0, b = 0; n < msg.length(); n++)
        {
            if(n < msg.length() / 2)
            {
                MBuffer[n] = L.charAt(n);
            }
            else
            {
                MBuffer[n] = R.charAt(b);
                b++;
            }
        }

        return new String(MBuffer);
    }

    public static String roundHandler(String bitPat, String key, int round)
    {
        char[] bitBuffer = bitPat.toCharArray();
        char[] keyBuffer = key.toCharArray();
        
        int bitLeng = bitPat.length();
        int keyLeng = key.length();

        //even round
        if(round % 2 == 0)
        {
            
            for(int i = 0, n = 0; i < bitLeng; i++)
            {
                //even index + key = 0
                if(i % 2 == 0 && keyBuffer[n] == '1')
                {
                    if(bitBuffer[i] == '0')
                    {
                        bitBuffer[i] = '1';
                    }
                    else
                    {
                        bitBuffer[i] = '0';
                    }
                }
                else if(i % 2 == 1 && keyBuffer[n] == '0')
                {
                    if(bitBuffer[i] == '0')
                    {
                        bitBuffer[i] = '1';
                    }
                    else
                    {
                        bitBuffer[i] = '0';
                    }
                }

                if(n < keyLeng)
                {
                    n++;
                }
                else
                {
                    n = 0;
                }
            }
        }
        else
        {
            for(int i = 0, n = 0; i < bitLeng; i++)
            {
                if(i % 2 == 1 && keyBuffer[n] == '1')
                {
                    if(bitBuffer[i] == '0')
                    {
                        bitBuffer[i] = '1';
                    }
                    else
                    {
                        bitBuffer[i] = '0';
                    }
                }
                else if(i % 2 == 0 && keyBuffer[n] == '0')
                {
                    if(bitBuffer[i] == '0')
                    {
                        bitBuffer[i] = '1';
                    }
                    else
                    {
                        bitBuffer[i] = '0';
                    }
                }

                if(n < keyLeng)
                {
                    n++;
                }
                else
                {
                    n = 0;
                }
            }
        }
    
        
        return new String(bitBuffer);
    }


    public static String keyShift(String key)
    {
        char KBuffer[] = key.toCharArray();
        int Klength = key.length();

        char LBuffer[] = new char[(Klength / 2)];
        char RBuffer[] = new char[(Klength / 2)];

        double bitSize = Math.ceil(Math.log(Klength)/ Math.log(2));

        int kSize = (int)Math.pow(2, bitSize - 4);
        
        char K1[] = new char[kSize];
        char K2[] = new char[kSize];
        
        int buffReader = 0;

        for(int i = 0; i < kSize; i++)
        {
            K1[i] = KBuffer[buffReader];
            buffReader++;
        }
        for(int i = 0;i < LBuffer.length - kSize; i++)
        {
            LBuffer[i] = KBuffer[buffReader];
            buffReader++;
        }
        for(int i = 0; i < kSize; i++)
        {
            K2[i] = KBuffer[buffReader];
            buffReader++;
        }
        for(int i = 0;i < RBuffer.length - kSize; i++)
        {
            RBuffer[i] = KBuffer[buffReader];
            buffReader++;
        }

        for(int i = 0, n = 0, b = 0, c = 0; i < Klength; i++)
        {
            if((i < (((int)((LBuffer.length) - (kSize))))))
            {
                KBuffer[i] = LBuffer[i];
            }
            else if(i < ((int)((LBuffer.length) - (kSize))) + ((int)((RBuffer.length) - (kSize))))
            {
                KBuffer[i] = RBuffer[n];
                n++;
            }
            else if( !(i >= (Klength - (kSize))))
            {
                KBuffer[i] = K1[b];
                b++;
            }
            else
            {
                KBuffer[i] = K2[c];
                c++;
            }
        }

        return new String(KBuffer);
    }

    public static String rKeyShift(String key)
    {
        char KBuffer[] = key.toCharArray();
        int Klength = key.length();

        char LBuffer[] = new char[(Klength / 2)];
        char RBuffer[] = new char[(Klength / 2)];

        double bitSize = Math.ceil(Math.log(Klength)/ Math.log(2));

        int kSize = (int)Math.pow(2, bitSize - 4);

        int buffReader = (LBuffer.length - kSize) * 2;
        for(int i = 0; i < kSize; i++)
        {
            LBuffer[i] = KBuffer[buffReader];
            buffReader++;
        }

        buffReader = 0;
        for(int i = kSize;i < LBuffer.length; i++)
        {
            LBuffer[i] = KBuffer[buffReader];
            buffReader++;
        }

        buffReader = ((LBuffer.length - kSize) * 2) + kSize;
        for(int i = 0; i < kSize; i++)
        {
            RBuffer[i] = KBuffer[buffReader];
            buffReader++;
        }

        buffReader = LBuffer.length - kSize;
        for(int i = kSize;i < RBuffer.length ; i++)
        {
            RBuffer[i] = KBuffer[buffReader];
            buffReader++;
        }
        
        for(int i = 0, n = 0; i < Klength; i++)
        {
            if(i < Klength / 2)
            {
                KBuffer[i] = LBuffer[i];
            }
            else
            {
                KBuffer[i] = RBuffer[n];
                n++;
            }
        }

        return new String(KBuffer);
    }

}