class MinStack {

    /**
     * Works on the principle of the minimum value at the top of
     * the stack will always either be itself or some where
     * further down the stack which can be referenced by the top
     * value of the stacks minimum value at that point.
     * 
     * Example:
     * push(-2)
     * push(0)
     * push(1)
     * push(-3)
     * push(4)
     * push(6)
     * 
     * Stack would look something like this at the end:
     * 
     *     Stack Value  |  Minimum Value
     *          6       |        -3
     *          4       |        -3
     *         -3       |        -3
     *          1       |        -2
     *          0       |        -2
     *         -2       |        -2
     * 
     * At our first push we obviously have an empty stack and the
     * minimum is just itself. At the second push we need to check
     * if the value were about to push on is smaller than our current
     * minimum value we have seen at that point in the stack. This is
     * the reason that -2 is the minimum when we push 0 and 1, and when
     * we push -3 onto the stack we see that -3 is smaller than -2 thus
     * at this point in the stack it is now the minimum value. When we
     * go to push 4 onto the stack the minimum value is still -3 and so
     * on for when 6 is pushed onto the stack.\
     * 
     * If we were to push -3 and then immediately pop it then the most minimum
     * value in the stack would be -2 and that wouldn't be a problem since
     * our stacks new top would be at 1 and it's minimum value at that point
     * is -2.
     */

    Stack<int[]> stack;
    public MinStack() {
        stack = new Stack<int[]>();
    }
    
    public void push(int x) {
        if(stack.empty()){
            stack.push(new int[] {x,x});
        }   
        else{
            int minimum = stack.peek()[1];
            stack.push(new int[] {x, Math.min(x, minimum)});
        }
    }
    
    public void pop() {
        stack.pop();
    }
    
    public int top() {
        return stack.peek()[0];
    }
    
    public int getMin() {
        return stack.peek()[1];
    }
}
