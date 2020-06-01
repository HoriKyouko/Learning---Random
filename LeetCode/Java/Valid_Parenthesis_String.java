public class Valid_Parenthesis_String {
    public boolean checkValidString(String s) {
        int low = 0;
        int high = 0;
        
        for(char c: s.toCharArray()){
            if(c == '('){
                low++;
            }
            else{
                low--;
            }
            if(c != ')'){
                high++;
            }
            else{
                high--;
            }
            
            if(high < 0){
                break;
            }
            
            low = Math.max(low, 0);
        }
        
        return low == 0;
    }
}