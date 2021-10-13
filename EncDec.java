
import java.util.ArrayList;

class I{
    public static int reverse(int i)
    {
        int no = 0;
        while(i > 0)
        {
            no *= 10;
            no += i%10;
            i = i/10;
        }
        return no;
    }
}


public class EncDec{
    public static int capAdd(int no)
    {
        int flag = (int) (Math.random() * 2);
        if(flag == 0)
        {
            no += 32;
        }
        else{
            no += 97;
        }
        return no;
    }

    public static int capSub(int no)
    {
        if(no >= 97)
        {
            no -= 97;
        }
        else{
            no -= 32;
        }
        return no;
    }

    
    private static char addKey(int c, int noA) {
    	for(int j = noA; j > 0; j--)
        {
        	if(c == (char)126)
        		c = 32;
        	else
        		c++;
        }
		return (char) c;
	}

    private static char removeKey(int c, int noD) {
    	for(int j = noD; j > 0; j--)
        {
        	if(c == 32)
        		c = 126;
        	else
        		c--;
        }
		return (char) c;
	}
    public static String zigzag(String s)
    {
        ArrayList<Character> text = new ArrayList<Character>();

        int sLen = s.length();
        
        int d = 1;
        if(sLen%2 != 0)
        {
            d++;
        }
        
        for(int i = 0; i < (sLen-d); i+=2)
        {
            text.add(i, s.charAt(i+1));
            text.add(i+1, s.charAt(i));
        }

        if(d == 2)
        {
            text.add(sLen-1, s.charAt(sLen-1));
        }

        StringBuilder builder = new StringBuilder(text.size());
        for(Character ch: text)
        {
            builder.append(ch);
        }

        return builder.toString();
    }

    public static int checkValidation(String[] argv, int argvLen)
    {
        char c = ' ';
        int valid = -1;


        if(argvLen >= 1)
        {
            if(argv[0].charAt(0) == '-')
            {
                c = argv[0].charAt(1);
                if(c == 'e')
                {
                    valid = 0;
                }
                else if(c == 'd'){
                    valid = 1;
                }else{
                    return -1;
                }

            }else{
                return -1;
            }
        }

        if(argvLen == 3)
        {
            if(argv[1].charAt(0) == '-' && argv[1].charAt(1) == 'k')
            {
                valid += 2;
            }else{
                return -1;
            }
        }

        return valid;

    }

    public static String encrypt(String s)
    {
        int sLen = s.length();

        String text = "";
        int c = 0;
        int noA = 1;
        for(int i = (sLen-1); i >= 0; i--)
        {
            c = s.charAt(i);

            if(noA == 8 )
            {
                noA = 7; 
            }

            c += noA;
            noA++;

            text += (char)c;
        }
        text += (char)capAdd(noA);
        text = zigzag(text);
        return text;
    } 
    
    public static String encryptRand(String s)
    {
        int sLen = s.length();
        
        String text = "";
        int c = 0;
        int noA = 0;

        for(int i = (sLen-1); i >= 0; i--)
        {
            c = s.charAt(i);
            
            noA = (int) ((Math.random() * (126-32))); 
            
//            c = 33+((c+noA) % (126-33));
            
            text += (char) addKey(c,noA);

            noA += 32;

            text += (char) noA;
            
        }
        text = zigzag(text);
        return text;
    } 
   

	public static String encWithKey(String s, String key)
    {
        s = encryptRand(s);
        System.out.println("After EncRand: " + s);
        
        int sLen = s.length();
        int keyLen = key.length();

        ArrayList<Integer> k = new ArrayList<Integer>();
        int kLen = 0;
        int ch = 0;
        for(int l = 0; l < keyLen; l++)
        {
            ch = key.charAt(l);
            while(ch > 0)
            {
                k.add(kLen, (int) (ch%10));
                ch = ch/10;
                kLen++;
            }
        }
        
        String text = "";
        int c = 0;

        int noALen = 0;
        int noA = 0;
        for(int i = (sLen-1); i >= 0; i--)
        {
            c = s.charAt(i);
            if(noALen == kLen )
            {
                noALen = 0; 
            }

            noA = k.get(noALen);

            c = addKey(c,noA);
            
            noALen++;

            text += (char)c;
        }

        text += (char) capAdd(noALen-1);
        System.out.println("After EncWithKey: " + text);
        text = zigzag(text);
        System.out.println("After ZigZag: " + text);
        return text;
    }
    
    public static String decrypt(String s)
    {
        s = zigzag(s);
        String text = "";
        int sLen = s.length();
        int c = 0;
        int noD = capSub((int) s.charAt(sLen-1));
        for(int i = (sLen-2); i >= 0; i--)
        {
            c = s.charAt(i);
            c -= noD;
            noD--;
            if(noD == 0){
                noD = 7;
            }
            text += (char) c;
        }
        return text;
    }

    public static String decryptRand(String s)
    {
        s = zigzag(s);
        String text = "";
        int sLen = s.length();
        int c = 0;
        int noD = 0;
        for(int i = (sLen-2); i >= 0 ; i-=2)
        {
            c = s.charAt(i);
            noD = s.charAt(i+1)-32;
            
            
//            c -= capSub(noD);
            text += (char)removeKey(c,noD);
        }
        return text;
    }


	public static String decWithKey(String s, String key)
    {
        s = zigzag(s);
        System.out.println("After ZigZag: " + s);
        int sLen = s.length();
        int keyLen = key.length();
        ArrayList<Integer> k = new ArrayList<Integer>();
        int kLen = 0;
        int ch = 0;

        for(int l = 0; l < keyLen; l++)
        {

            ch = key.charAt(l);
            while(ch > 0)
            {
                k.add(kLen, (int) (ch%10));
                kLen++;
                ch = ch/10;
            }
        }

        String text = "";
        int c = 0;
        int noD = 0;
        int noDLen = (int) capSub((int)(s.charAt(sLen-1)));

        for(int i = (sLen-2); i >= 0; i--)
        {
            c = s.charAt(i);

            if(noDLen < 0)
            {
                noDLen = (kLen-1); 
            }

            try{
                noD = k.get(noDLen);
            }
            catch(IndexOutOfBoundsException e)
            {
                k.get(0);
                noDLen = (int)(Math.random()*keyLen);
                continue;
            }

            c = removeKey(c,noD);
            noDLen--;

            text += (char)c;
        }
        System.out.println("After DecWithKey: " + text);
        text = decryptRand(text);
        System.out.println("After decryptRand: " + text);

        return text;
    }
}