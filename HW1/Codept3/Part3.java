package Codept3;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.File;

public class Part3{

    //function to find out how much a specific character shows up in the text
    public static void frequency(String msg)
    {
        char buffer[] = msg.toCharArray();      //converts msg into a character array
        int length = msg.length();              //gets the length of the message
        String alph = "abcdefghijklmnopqrstuvwxyz";

        //loop to do the counting
        for(int n = 0; n < 26; n++)
        {
            int count = 0;                          //keeps track of how much each character shows up

            for(int i = 0; i < length; i++)
            {
                if(buffer[i] == alph.charAt(n))
                {
                    count++;                    //increments counter
                }
            }

            
            System.out.println( alph.charAt(n) + " Shows up " + count + " times");
            
            
        }

        System.out.println();
    
    }

    public static void frequency2(String msg)
    {
        char buffer[] = msg.toCharArray();      //converts msg into a character array
        int length = msg.length();              //gets the length of the message
        int count = 0;                          //keeps track of how much each character shows up
        String alph = "abcdefghijklmnopqrstuvwxyz";
        String alph2 = "abcdefghijklmnopqrstuvwxyz";

        //loop to do the counting
        for(int i = 0; i < 25; i++)
        {
            

            for(int n = 1; n < 26; n++)
            {
                count = 0;

                for(int x = 0; x < length - 1; x++)
                {
                    if((buffer[x] == alph.charAt(i)) && (buffer[x + 1] == alph2.charAt(n)))
                    {
                        count++;                    //increments counter
                        x++;
                    }
                }

                if(count > 50)
                {
                    System.out.println( alph.charAt(i) + "," + alph2.charAt(n) + " Shows up " + count + " times");
                }
            }
            
        }

        System.out.println();
    
    }

    public static void frequency3(String msg)
    {
        char buffer[] = msg.toCharArray();      //converts msg into a character array
        int length = msg.length();              //gets the length of the message
        int count = 0;                          //keeps track of how much each character shows up
        String alph = "abcdefghijklmnopqrstuvwxyz";
        String alph2 = "abcdefghijklmnopqrstuvwxyz";
        String alph3 = "abcdefghijklmnopqrstuvwxyz";

        //loop to do the counting
        for(int i = 0; i < 24; i++)
        {
            

            for(int n = 1; n < 25; n++)
            {
                for(int y = 2; y < 26; y++)
                {
                    count = 0;

                    for(int x = 0; x < length - 2; x++)
                    {
                        if((buffer[x] == alph.charAt(i)) && (buffer[x + 1] == alph2.charAt(n)) && (buffer[x + 2] == alph3.charAt(y)))
                        {
                            count++;                    //increments counter
                            x++;
                        }
                    }

                    if(count > 25)
                    {
                        System.out.println( alph.charAt(i) + "," + alph2.charAt(n) + "," + alph3.charAt(y) + " Shows up " + count + " times");
                    }
                }
            }
            
        }

        System.out.println();
    
    }

    public static void permCipher(String msg, char key[])
    {
        char buffer[] = msg.toCharArray();
        int length = msg.length();
        char alph[] = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'}; 
        String RESET = "\u001B[0m";
        String RED = "\u001B[31m";
        boolean isFound = false;


        for (int i = 0; i < length; i++)
        {
            isFound = false;

            for(int n = 0; n < 26; n++)
            {
                if(key[n] != ' ' && (buffer[i] == alph[n]))
                {
                    isFound = true;
                    break;
                }
            
            }

            if (isFound == true && buffer[i] != ' ') 
            {
                switch(buffer[i])
                {
                    case 'a':
                        System.out.print(RED + key[0] + RESET);
                        break;
                    case 'b':
                        System.out.print(RED + key[1] + RESET);
                        break;
                    case 'c':
                        System.out.print(RED + key[2] + RESET);
                        break;
                    case 'd':
                        System.out.print(RED + key[3] + RESET);
                        break;
                    case 'e':
                        System.out.print(RED + key[4] + RESET);
                        break;
                    case 'f':
                        System.out.print(RED + key[5] + RESET);
                        break;
                    case 'g':
                        System.out.print(RED + key[6] + RESET);
                        break;
                    case 'h':
                        System.out.print(RED + key[7] + RESET);
                        break;
                    case 'i':
                        System.out.print(RED + key[8] + RESET);
                        break;
                    case 'j':
                        System.out.print(RED + key[9] + RESET);
                        break;
                    case 'k':
                        System.out.print(RED + key[10] + RESET);
                        break;
                    case 'l':
                        System.out.print(RED + key[11] + RESET);
                        break;
                    case 'm':
                        System.out.print(RED + key[12] + RESET);
                        break;
                    case 'n':
                        System.out.print(RED + key[13] + RESET);
                        break;
                    case 'o':
                        System.out.print(RED + key[14] + RESET);
                        break;
                    case 'p':
                        System.out.print(RED + key[15] + RESET);
                        break;
                    case 'q':
                        System.out.print(RED + key[16] + RESET);
                        break;
                    case 'r':
                        System.out.print(RED + key[17] + RESET);
                        break;
                    case 's':
                        System.out.print(RED + key[18] + RESET);
                        break;
                    case 't':
                        System.out.print(RED + key[19] + RESET);
                        break;
                    case 'u':
                        System.out.print(RED + key[20] + RESET);
                        break;
                    case 'v':
                        System.out.print(RED + key[21] + RESET);
                        break;
                    case 'w':
                        System.out.print(RED + key[22] + RESET);
                        break;
                    case 'x':
                        System.out.print(RED + key[23] + RESET);
                        break;
                    case 'y':
                        System.out.print(RED + key[24] + RESET);
                        break;
                    case 'z':
                        System.out.print(RED + key[25] + RESET);
                        break;
                    }
                }
                else
                {
                    System.out.print(buffer[i]);
                }
            
            
        }

    }

    public static void main(String[] args) 
    {
        BufferedReader input2 = new BufferedReader(new InputStreamReader(System.in));   //reads user inputs as a string
        String msg = "";                                                                     //holds the message that will be looked through
        int count;                                                                      //holds the amount of times a specific character shows up

        File newFile = new File("PermCiphertext.txt");                     //opens the Perm Ciphertext.txt file
        char key[] = {'b','o','i','h','g','k','n','q','v','t','w','y','u','r','x','z','a','j','e','m','s','l','d','f','p','c'};
            
        try
        {
                
            Scanner fileReader = new Scanner(newFile);                          //reads from the file
            count = 0;                                                          //keeps track of count
                                
            while(fileReader.hasNextLine())
            {
                msg += fileReader.nextLine();
                msg += "\n";
                        
            }

            frequency(msg);
            frequency2(msg);
            frequency3(msg);

            permCipher(msg, key);
                                    
            fileReader.close();
 
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }

    }

}