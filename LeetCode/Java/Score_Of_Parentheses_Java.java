class Solution {
    public int scoreOfParentheses(String S) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        
        for(char c: S.toCharArray()){
            if(c == '('){
                stack.push(0);
            }
            else{
                int i = stack.pop();
                int j = stack.pop();
                stack.push(j + Math.max(2*i, 1));
            }
        }
        return stack.pop();
    }
}