class Solution {
    public String removeKdigits(String num, int k) {
        if(k >= num.length()) return "0";
        
        Stack<Character> st = new Stack<>();
        
        for(char c: num.toCharArray()){
            while(k>0 && !st.isEmpty() && st.peek() > c){
                st.pop();
                k--;
            }
            st.push(c);
        }
        
        while(k > 0){
            st.pop();
            k--;
        }
        
        StringBuilder str = new StringBuilder();
        
        while(!st.isEmpty())
            str.append(st.pop());
        
        str.reverse();
        
        while(str.length() > 1 && str.charAt(0) == '0')
            str.deleteCharAt(0);
        return str.toString();
    }
}