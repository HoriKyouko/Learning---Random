public class Valid_Palindrome{
    public boolean isPalindrome(String s) {
        if(s.equals("")) return true;
        
        StringBuilder str = new StringBuilder();
        
        for(char c: s.toCharArray())
            if(Character.isLetterOrDigit(c))
                str.append(Character.toLowerCase(c));
        
        int j = str.length()-1;
        for(int i = 0; i < str.length(); i++)
            if(str.charAt(i) != str.charAt(j--))
                return false;
        
        return true;
    }
}