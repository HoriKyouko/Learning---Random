public class Goat_Latin {
    public String toGoatLatin(String S) {
        HashSet<Character> vowels = new HashSet<>();
        addVowels(vowels);
        
        StringBuilder goat = new StringBuilder("maa");
        StringBuilder str = new StringBuilder();
        char first = S.charAt(0);
        
        for(int i = 0; i < S.length(); i++){
            if(S.charAt(i) == ' '){
                if(!vowels.contains(first))
                    str.append(first);
                
                first = S.charAt(i+1);
                str.append(goat.toString());
                str.append(" ");
                goat.append("a");
                if(!vowels.contains(first))
                    i++;
                
                continue;
            }
            if(i != 0 || vowels.contains(S.charAt(i)))
                str.append(S.charAt(i)); 
        }
        
        if(!vowels.contains(first))
            str.append(first);
        str.append(goat.toString());
        return str.toString();
    }
    
    private void addVowels(HashSet<Character> vowels){
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');
        vowels.add('A');
        vowels.add('E');
        vowels.add('I');
        vowels.add('O');
        vowels.add('U');
    }
}