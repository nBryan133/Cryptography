import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.File;
import java.io.FileNotFoundException;

public class Parts1_2 {

    //function to find the gcd through the euclidean algorithm
    public static int eucAlg(int a, int b)
    {
        if(a == 0)      //if we have found the gcd end loop
        {
            return b;
        }   
        
        return eucAlg(b % a, a); //else we find the next number to check as gcd
    }

    public static String caesar(int key, String msg)
    {
        char buffer[] = msg.toCharArray();
        int length = msg.length();
        int keyLoop;

        for (int i = 0; i < length; i++)
        {
            if (buffer[i] != 92) 
            {
                if(buffer[i] >= 'a' && buffer[i] <= 'z') 
                {
                    if((buffer[i] + key) > 'z' )
                    {   
                        keyLoop = key - (123 - buffer[i]); 
                        buffer[i] = (char) ('a' + keyLoop);
                    }
                    else
                    {
                        buffer[i] += key;
                    }

                }
                else if(buffer[i] >= 'A' && buffer[i] <= 'Z')
                {
                    if(buffer[i] + key > 'Z')
                    {   
                        keyLoop = key - (91 - buffer[i]); 
                        buffer[i] = (char)('A' + keyLoop);
                    }
                    else
                    {
                        buffer[i] += key;
                    }
                }
            }
            else
            {
                buffer[i] = '\n';
                i++;
                buffer[i] = ' ';
            }
        }
        
        return new String(buffer);

    }

    public static String unCaesar(int key, String msg)
    {
        char buffer[] = msg.toCharArray();
        int length = msg.length();
        int keyLoop;

        for (int i = 0; i < length; i++)
        {
            if(buffer[i] != 92)
            {
                if(buffer[i] >= 'a' && buffer[i] <= 'z') 
                {
                    if((buffer[i] - key) < 'a' )
                    {   
                        keyLoop = key - (buffer[i] - 96); 
                        buffer[i] = (char) ('z' - keyLoop);
                    }
                    else
                    {
                        buffer[i] -= key;
                    }

                }
                else if(buffer[i] >= 'A' && buffer[i] <= 'Z')
                {
                    if(buffer[i] - key < 'A')
                    {   
                        keyLoop = key - (buffer[i] - 64); 
                        buffer[i] = (char)('Z' - keyLoop);
                    }
                    else
                    {
                        buffer[i] -= key;
                    }
                }
            }
            else
            {
                buffer[i] = '\n';
                i++;
                buffer[i] = ' ';
            }
        }
        
        return new String(buffer);

    }

    public static String permCipher(String key, String msg)
    {
        char buffer[] = msg.toCharArray();
        int length = msg.length();

        for (int i = 0; i < length; i++)
        {
            if (buffer[i] != 92) 
            {
                switch(buffer[i])
                {
                    case 'a':
                        buffer[i] = key.charAt(0);
                        break;
                    case 'b':
                        buffer[i] = key.charAt(1);
                        break;
                    case 'c':
                        buffer[i] = key.charAt(2);
                        break;
                    case 'd':
                        buffer[i] = key.charAt(3);
                        break;
                    case 'e':
                        buffer[i] = key.charAt(4);
                        break;
                    case 'f':
                        buffer[i] = key.charAt(5);
                        break;
                    case 'g':
                        buffer[i] = key.charAt(6);
                        break;
                    case 'h':
                        buffer[i] = key.charAt(7);
                        break;
                    case 'i':
                        buffer[i] = key.charAt(8);
                        break;
                    case 'j':
                        buffer[i] = key.charAt(9);
                        break;
                    case 'k':
                        buffer[i] = key.charAt(10);
                        break;
                    case 'l':
                        buffer[i] = key.charAt(11);
                        break;
                    case 'm':
                        buffer[i] = key.charAt(12);
                        break;
                    case 'n':
                        buffer[i] = key.charAt(13);
                        break;
                    case 'o':
                        buffer[i] = key.charAt(14);
                        break;
                    case 'p':
                        buffer[i] = key.charAt(15);
                        break;
                    case 'q':
                        buffer[i] = key.charAt(16);
                        break;
                    case 'r':
                        buffer[i] = key.charAt(17);
                        break;
                    case 's':
                        buffer[i] = key.charAt(18);
                        break;
                    case 't':
                        buffer[i] = key.charAt(19);
                        break;
                    case 'u':
                        buffer[i] = key.charAt(20);
                        break;
                    case 'v':
                        buffer[i] = key.charAt(21);
                        break;
                    case 'w':
                        buffer[i] = key.charAt(22);
                        break;
                    case 'x':
                        buffer[i] = key.charAt(23);
                        break;
                    case 'y':
                        buffer[i] = key.charAt(24);
                        break;
                    case 'z':
                        buffer[i] = key.charAt(25);
                        break;
                }
            }
            else
            {
                buffer[i] = '\n';
                i++;
                buffer[i] = ' ';
            }
        }
        
        return new String(buffer);

    }


    public static void main(String[] args)
    {
        int select, a, b, gcd;           //variables to hold the user inputs
        int key;
        String msg = "";
        String fileName;
        String permKey = "qwertyuiopasdfghjklzxcvbnm";

        Scanner input = new Scanner( System.in);    //scanner to read the user input
        BufferedReader input2 = new BufferedReader(new InputStreamReader(System.in));


        do
        {
            System.out.println("What would you like to do?\n(1 = GCD calculator)\n(2 = Caesar Cipher)\n(3 = Decryption)\n(4 PermCipher): ");
            select = input.nextInt();

            if(!(select >= 1) && !(select <= 4))
            {
                System.out.println("Please make a valid selection\n");
            }

        }while(!(select >= 1) && !(select <= 4));

        if(select == 1)
        {
            System.out.println("Find the GCD of what two numbers?");

            System.out.println("Variable a: ");
            a = input.nextInt();
            
            //do while loop to make sure that b is not greater than a
            do
            {
                System.out.println("Variable b (must be smaller than or equal to a): ");
                b = input.nextInt();

                if( b > a)
                {
                    System.out.println("b cannot be larger than a please put a valid input\n");

                }
            }while( b > a);

            input.close();

            gcd = eucAlg(a, b);

            System.out.println("\nGCD( " + a +", " + b + ") = " + gcd );      //prints result to screen

        }
        else if(select == 2)
        {

            try
            {
                System.out.println("Please type the message to Encrypt: ");
                msg = input2.readLine();

                System.out.println("\nPlease enter the key to shift the message by: ");
                key = input.nextInt();

                System.out.println("Encoding message...\n");
                msg = caesar(key, msg);

                System.out.println("Encoded Message:\n" + msg + "\n");

                input.close();
                input2.close();
            }
            catch(IOException ioEx)
            {
                System.out.println("IOException triggered!");
            }
        }
        else if(select == 3)
        {
            String cont;

            do
            {
                System.out.println("Would you like to use a file or type the message?\n(1 = msg)\n(2 = file): ");
                select = input.nextInt();

                if(!(select >= 1) && !(select <= 2))
                {
                    System.out.println("Please use a valid input!");
                }
            }while(!(select >= 1) && !(select <= 2));

            if(select == 1)
            {
                try
                {
                    System.out.println("Please type the message to Decrypt: ");
                    msg = input2.readLine();

                    System.out.println("\nPlease enter the key to start shifting by: ");
                    key = input.nextInt();

                    do
                    {
                        System.out.println("Decoding message...\n");
                        msg = unCaesar(key, msg);

                        System.out.println("Decoded Message:\n" + "Current key: " + key + "\n" + msg + "\n\nDoes this look Correct?\n(type yes to confirm anything else to try the next key)");
                        cont = input2.readLine();

                        if(!(cont.trim().equalsIgnoreCase("yes")))
                        {
                            if(key < 25)
                            {
                                key++;
                            }
                            else
                            {
                                key = 1;
                            }
                        }

                    }while(!(cont.equalsIgnoreCase("yes")));

                    input.close();
                    input2.close();
                }
                catch(IOException ioEx)
                {
                    System.out.println("IOException triggered!");
                }
            }
            else if(select == 2)
            {
                try
                {
                    System.out.println("Please enter the name of the file to decrypt:");
                    fileName = input2.readLine();

                    System.out.println("Please enter the key you wish to start with:");
                    key = input.nextInt();

                    try
                    {
                        File newFile = new File(fileName);
                        

                        do
                        {
                            Scanner fileReader = new Scanner(newFile);

                            System.out.println("Decoding...\n");
                            while(fileReader.hasNextLine())
                            {
                                msg = fileReader.nextLine();
                                msg = unCaesar(key, msg);

                                System.out.print(msg);
                            }
                            

                            System.out.println("\n\nCurrent key: " + key + "\n" + "Does this look Correct?\n(type yes to confirm anything else to try the next key)");
                            cont = input2.readLine();

                            System.out.println(cont);

                            if(!(cont.equalsIgnoreCase("yes")))
                            {
                                if(key < 25)
                                {
                                    key++;
                                }
                                else
                                {
                                    key = 1;
                                }
                            }

                            fileReader.close();

                        }while(!(cont.equalsIgnoreCase("yes")));

                        
                    }
                    catch(FileNotFoundException fnfEx)
                    {
                        System.out.println("File not found.");
                    }
                }
                catch(IOException ieEx)
                {
                    System.out.println("IOException triggered!");
                }


            }

        }
        else if(select == 4)
        {
            try
            {
                System.out.println("Please type the message to Encrypt: ");
                msg = input2.readLine();

                System.out.println("Encoding message...\n");
                msg = permCipher(permKey, msg);

                System.out.println("Encoded Message:\n" + msg + "\n");

                input.close();
                input2.close();
            }
            catch(IOException ioEx)
            {
                System.out.println("IOException triggered!");
            }
        }
    }
}
