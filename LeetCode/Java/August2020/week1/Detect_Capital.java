public class Detect_Capital{
    public boolean detectCapitalUse(String word) {
        if(word.length() == 1)
            return true;
        if(Character.isUpperCase(word.charAt(0)) && Character.isUpperCase(word.charAt(1))){
            for(int i = 2; i < word.length(); i++)
                if(Character.isLowerCase(word.charAt(i)))
                    return false;
        }
        else{
            for(int i = 1; i < word.length(); i++)
                if(Character.isUpperCase(word.charAt(i)))
                    return false;
        }
        return true;
    }
}