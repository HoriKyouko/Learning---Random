class Solution {
    public String reverseWords(String s) {
        if(s == null)
            return null;
        
        s = s.trim();
        
        String [] strs = s.split(" ");
        StringBuilder str = new StringBuilder();

        for(int i = strs.length-1; i >= 0; i--){
            if(!strs[i].equals("")){
                str.append(strs[i]);
                if(i != 0)
                    str.append(' ');
            }
        }
        return str.toString();
    }
}