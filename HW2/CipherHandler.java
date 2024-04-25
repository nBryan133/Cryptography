import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CipherHandler 
{
    CipherHandler()
    {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));   //reads user inputs as a string
        Scanner input2 = new Scanner( System.in);    //scanner to read the user input

        String cont = "";
        String msg = "";
        String key = "";
        String encryptedMsg = "";
        char encryptedCharMsg[];

        int depth;
        int repetitions = 0;

        String select= "";

        try
        {
            do
            {

                System.out.println("Please select Cipher to use (1: Vigenere)(2: Vernam)(3: Transposition): ");
                select = input.readLine();

                if(select.charAt(0) != '1' && select.charAt(0) != '2' && select.charAt(0) != '3')
                {
                    System.out.println("Please enter a valid input!");
                }

            }while(select.charAt(0) != '1' && select.charAt(0) != '2' && select.charAt(0) != '3');

            if(select.charAt(0) == '1')
            {

                do
                {

                    System.out.println("Would you like to Encrypt or Decrypt (1: Encrypt)(2: Decrypt): ");
                    select = input.readLine();

                    if(select.charAt(0) != '1' && select.charAt(0) != '2' && select.charAt(0) != '3')
                    {
                        System.out.println("Please enter a valid input!");
                    }

                }while(select.charAt(0) != '1' && select.charAt(0) != '2' && select.charAt(0) != '3');

                if(select.charAt(0) == '1')
                {

                    System.out.println("Please put in a string to encrypt: ");
                    msg = input.readLine();

                    System.out.println("Please input a key to encrypt with: ");
                    key = input.readLine();

                    encryptedMsg = vigenere(msg, key);

                    System.out.println(encryptedMsg);
                }
                else if(select.charAt(0) == '2')
                {
                    
                    System.out.println("Please put in a string to decrypt: ");
                    msg = input.readLine();

                    do
                    {
                        System.out.println("Please input a key to decrypt with (1 to exit): ");
                        key = input.readLine();

                        if(key.charAt(0) != '1')
                        {
                            encryptedMsg = unVigenere(msg, key);

                            System.out.println(encryptedMsg);
                        }

                    }while(key.charAt(0) != '1');
                }
            }
            else if(select.charAt(0) == '2')
            {
                System.out.println("Please put in a string to encrypt: ");
                msg = input.readLine();

                System.out.println("Please input a string to encrypt with: ");
                key = input.readLine();

                encryptedCharMsg = msg.toCharArray();

                encryptedCharMsg = vernam(encryptedCharMsg, key);

                readVern(encryptedCharMsg);

                System.out.println("And now to decrypt back!!!");

                encryptedCharMsg = vernam(encryptedCharMsg, key);

                readVern(encryptedCharMsg);
            }
            else if(select.charAt(0) == '3')
            {
                System.out.println("Please put in a string to encrypt: ");
                encryptedMsg = input.readLine();

                System.out.println("Please input a depth to encrypt with: ");
                depth = input2.nextInt();

                

                do
                {
                    System.out.println("Generation: " + repetitions);
                    encryptedMsg = fence(encryptedMsg, depth);

                    System.out.println(encryptedMsg);

                    System.out.println("would you like to go another round?(Y: Continue)(anything else to end): ");
                    cont = input.readLine();

                    repetitions++;
                }while(cont.toLowerCase().equals("y"));
            }

            input2.close();
        }
        catch(IOException IOException)
        {
            System.out.println("IOException caught");
        }
    }


    static String vigenere(String msg, String key)
    {
        char msgBuffer[] = msg.toCharArray();      //converts msg into a character array
        char keyBuffer[] = key.toLowerCase().toCharArray();

        int msgLength = msg.length();              //gets the length of the message
        int keyLength = key.length();
        int mathBuffer;

        String alph = "abcdefghijklmnopqrstuvwxyz";

        for(int i = 0, n = 0; i < msgLength; i++, n++)
        {
            if((msgBuffer[i] >= 'a' && msgBuffer[i] <= 'z') || msgBuffer[i] == ' ')
            {
                if(keyBuffer[n] != ' ' && msgBuffer[i] != ' ')
                {
                    mathBuffer = (msgBuffer[i] - 97) + (keyBuffer[n] - 97);
                }
                else if(keyBuffer[n] == ' ' && msgBuffer[i] != ' ')
                {
                    mathBuffer = (msgBuffer[i] - 97) + 26;
                }
                else if(keyBuffer[n] != ' ' && msgBuffer[i] == ' ')
                {
                    mathBuffer = (keyBuffer[n] - 97) + 26;
                }
                else
                {
                    mathBuffer = 52;
                }

                mathBuffer = (mathBuffer % 27);

                if(mathBuffer != 26)
                {
                    msgBuffer[i] = alph.charAt(mathBuffer);
                }
                else{
                    msgBuffer[i] = ' ';
                }
            }
            else if((msgBuffer[i] >= 'A' && msgBuffer[i] <= 'Z') || msgBuffer[i] == ' ')
            {
                if(keyBuffer[n] != ' ' && msgBuffer[i] != ' ')
                {
                    mathBuffer = (msgBuffer[i] - 65) + (keyBuffer[n] - 97);
                }
                else if(keyBuffer[n] == ' ' && msgBuffer[i] != ' ')
                {
                    mathBuffer = (msgBuffer[i] - 65) + 26;
                }
                else if(keyBuffer[n] != ' ' && msgBuffer[i] == ' ')
                {
                    mathBuffer = (keyBuffer[n] - 65) + 26;
                }
                else
                {
                    mathBuffer = 52;
                }

                mathBuffer = (mathBuffer % 27);

                if(mathBuffer != 26)
                {
                    msgBuffer[i] = alph.charAt(mathBuffer);
                }
                else{
                    msgBuffer[i] = ' ';
                }
            }

            if(n == keyLength - 1)
            {
                n = -1;
            }
        }

        return new String(msgBuffer);
    }


    static String unVigenere(String msg, String key)
    {
        char msgBuffer[] = msg.toCharArray();      //converts msg into a character array
        char keyBuffer[] = key.toLowerCase().toCharArray();

        int msgLength = msg.length();              //gets the length of the message
        int keyLength = key.length();
        int mathBuffer;

        String alph = "abcdefghijklmnopqrstuvwxyz";

        for(int i = 0, n = 0; i < msgLength; i++, n++)
        {
            if((msgBuffer[i] >= 'a' && msgBuffer[i] <= 'z') || msgBuffer[i] == ' ')
            {
                if(keyBuffer[n] != ' ' && msgBuffer[i] != ' ')
                {
                    mathBuffer = (msgBuffer[i] - 97) - (keyBuffer[n] - 97);

                    if(mathBuffer < 0)
                    {
                        mathBuffer = 27 + mathBuffer;
                    }
                }
                else if(keyBuffer[n] == ' ' && msgBuffer[i] != ' ')
                {
                    mathBuffer =  (msgBuffer[i] - 97) - 26;

                    if(mathBuffer < 0)
                    {
                        mathBuffer = 27 + mathBuffer;
                    }
                }
                else
                {
                    mathBuffer = 0;
                }

                mathBuffer = (mathBuffer % 27);

                if(mathBuffer != 26)
                {
                    msgBuffer[i] = alph.charAt(mathBuffer);
                }
                else{
                    msgBuffer[i] = ' ';
                }
            }
            else if((msgBuffer[i] >= 'A' && msgBuffer[i] <= 'Z') || msgBuffer[i] == ' ')
            {
                if(keyBuffer[n] != ' ' && msgBuffer[i] != ' ')
                {
                    mathBuffer = (msgBuffer[i] - 65) - (keyBuffer[n] - 97);

                    if(mathBuffer < 0)
                    {
                        mathBuffer = 27 + mathBuffer;
                    }
                }
                else if(keyBuffer[n] == ' ' && msgBuffer[i] != ' ')
                {
                    mathBuffer =  (msgBuffer[i] - 65) - 26;

                    if(mathBuffer < 0)
                    {
                        mathBuffer = 27 + mathBuffer;
                    }
                }
                else
                {
                    mathBuffer = 0;
                }

                mathBuffer = (mathBuffer % 27);

                if(mathBuffer != 26)
                {
                    msgBuffer[i] = alph.charAt(mathBuffer);
                }
                else{
                    msgBuffer[i] = ' ';
                }
            }

            if(n == keyLength - 1)
            {
                n = -1;
            }
        }

        return new String(msgBuffer);
    }


    static char[] vernam(char[] msg, String key)
    {
        char msgBuffer[] = msg;      //converts msg into a character array
        char keyBuffer[] = key.toLowerCase().toCharArray();

        int msgLength = msg.length;              //gets the length of the message
        int keyLength = key.length();

        int mathBuffer;

        String msgBin;
        String keyBin;
        String encryptBin = "";

        for(int i = 0, n = 0; i < msgLength; i++, n++)
        {
            encryptBin = "";

            if((msgBuffer[i] >= 'a' && msgBuffer[i] <= 'z') || msgBuffer[i] == ' ' || msgBuffer[i] >= 0 && msgBuffer[i] <= 31)
            {
                if(((msgBuffer[i] >= 'a' && msgBuffer[i] <= 'z')))
                {
                    mathBuffer = msgBuffer[i] - 97;
                    msgBin = Integer.toBinaryString(mathBuffer);

                    mathBuffer = keyBuffer[n] - 97;
                    keyBin = Integer.toBinaryString(mathBuffer);
                }
                else if(msgBuffer[i] >= 0 && msgBuffer[i] <= 31)
                {
                    mathBuffer = (int)msgBuffer[i];
                    msgBin = Integer.toBinaryString(mathBuffer);

                    mathBuffer = keyBuffer[n] - 97;
                    keyBin = Integer.toBinaryString(mathBuffer);
                }
                else
                {
                    mathBuffer = 26;
                    msgBin = Integer.toBinaryString(mathBuffer);

                    mathBuffer = keyBuffer[n] - 97;
                    keyBin = Integer.toBinaryString(mathBuffer);
                }

                while(msgBin.length() < 5)
                {
                    msgBin = '0' + msgBin;
                }

                while(keyBin.length() < 5)
                {
                    keyBin = '0' + keyBin;
                }

                for(int m = 0, x = 0; m < msgBin.length(); m++, x++)
                {
                    if(msgBin.charAt(m) ==  keyBin.charAt(x))
                    {
                        encryptBin += '0';
                    }
                    else
                    {
                        encryptBin += '1';
                    }

                    if(x == keyBin.length() - 1)
                    {
                        x = 0;
                    }
                    
                }

                mathBuffer = Integer.parseInt(encryptBin, 2);

                msgBuffer[i] = (char)mathBuffer;
            }
            else if((msgBuffer[i] >= 'A' && msgBuffer[i] <= 'Z') || msgBuffer[i] == ' ')
            {
                if((msgBuffer[i] >= 'A' && msgBuffer[i] <= 'A'))
                {
                    mathBuffer = msgBuffer[i] - 65;
                    msgBin = Integer.toBinaryString(mathBuffer);

                    mathBuffer = keyBuffer[n] - 97;
                    keyBin = Integer.toBinaryString(mathBuffer);
                }
                else
                {
                    mathBuffer = 26;
                    msgBin = Integer.toBinaryString(mathBuffer);

                    mathBuffer = keyBuffer[n] - 97;
                    keyBin = Integer.toBinaryString(mathBuffer);
                }

                while(msgBin.length() < 5)
                {
                    msgBin = '0' + msgBin;
                }

                while(keyBin.length() < 5)
                {
                    keyBin = '0' + keyBin;
                }

                for(int m = 0, x = 0; m < msgBin.length(); m++, x++)
                {
                    if(msgBin.charAt(m) ==  keyBin.charAt(m))
                    {
                        encryptBin += '0';
                    }
                    else
                    {
                        encryptBin += '1';
                    }

                    if(x == keyBin.length() - 1)
                    {
                        x = 0;
                    }
                }

                mathBuffer = Integer.parseInt(encryptBin, 2);

                msgBuffer[i] = (char)mathBuffer;
            }

            if(n == keyLength - 1)
            {
                n = 0;
            }
        }

        
        return msgBuffer;
    }

    static void readVern(char msg[])
    {

        String alph = "abcdefghijklmnopqrstuvwxyz abcdefghijklmnopqrstuvwxyz ";

        for(int i = 0; i < msg.length; i++)
        {
            System.out.print(alph.charAt(msg[i])); 
        }
        System.out.println();
    }


    static String fence(String msg, int depth)
    {
        char msgBuffer[] = msg.toCharArray();      //converts msg into a character array
        int msgLength = msg.length();

        int col = depth;
        int row = (int) Math.ceil((double) msg.length() / depth);

        char midBuffer[][] = new char[row][col];

        int m = 0;

        for(int r = 0; r < row; r++)
        {
            for(int c = 0; c < col; c++)
            {
                do
                {
                    if(!(m < msgLength))
                    {
                        break;
                    }

                    if(m < msgLength && (((msgBuffer[m] >= 'a') && (msgBuffer[m] <= 'z')) || ((msgBuffer[m] >= 'A') && (msgBuffer[m] <= 'Z'))))
                    {
                        midBuffer[r][c] = msgBuffer[m];
                    }
                    
                    if(!(((msgBuffer[m] >= 'a') && (msgBuffer[m] <= 'z')) || !((msgBuffer[m] >= 'A') && (msgBuffer[m] <= 'Z'))))
                    {
                        m++;
                    }
                    
                    
                    
                }while(!(((msgBuffer[m] >= 'a') && (msgBuffer[m] <= 'z')) || !((msgBuffer[m] >= 'A') && (msgBuffer[m] <= 'Z'))));

                m++;

                System.out.print(midBuffer[r][c]);
            }
            System.out.println();
        }
        
        m = 0;
        for(int c = 0; c < col; c++)
        {
            for(int r = 0; r < row; r++)
            {
            
                if(m < msgLength)
                {
                    msgBuffer[m] = midBuffer[r][c];   
                }
                else
                {
                    break;
                }
                
                m++;
            }
        }

        return new String(msgBuffer);
    }
}

