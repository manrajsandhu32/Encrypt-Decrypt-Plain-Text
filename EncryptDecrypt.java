import java.util.Scanner;
public class EncryptDecrypt{
	
    public static void main(String[] argv)
    {
    	
    	
        int argvLen = 0;
        int valid = 0;
        String[] s = new String[3];
        s[0] = "Invalid Passed Arguments: Please Enter Valid Arguments !!!";
        s[1] = "\nUSAGE : java EncryptDecrypt -<e/d> ";
        s[2] = "        You can Also add -k \"....key....\"\n";

        try{
            while(true)
            {
                argv[argvLen] = argv[argvLen];
                argvLen++;
            }
            
        }catch(ArrayIndexOutOfBoundsException e)
        {
            
        }

        valid = EncDec.checkValidation(argv, argvLen);

        if(valid == -1)
        {
            System.out.println(s[0]+'\n'+s[1]+'\n'+s[2]);
            return;
        }

        System.out.print("Enter String : ");
        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();
        scan.close();

        String text = "";
        if(valid == 0)
        {
            text = EncDec.encryptRand(str);
        }else if(valid == 1)
        {
            text = EncDec.decryptRand(str);
        }else if(valid == 2)
        {
            text = EncDec.encWithKey(str, argv[2]);
        }else if(valid == 3)
        {
            text = EncDec.decWithKey(str, argv[2]);
        }
        

        System.out.print("\nEncrypted/Decrypted Text: \"");
        System.err.print(text);
        System.out.println("\"\n");
        return;        
    }
}