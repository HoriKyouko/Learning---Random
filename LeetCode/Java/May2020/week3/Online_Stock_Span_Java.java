class StockSpanner {
    class Pair {
        int val;
        int count;
        public Pair(int v){
            val = v;
            count = 1;
        }
    }
    
    Stack<Pair> stack;
    public StockSpanner() {
        stack = new Stack<>();
    }
    
    public int next(int price) {
        Pair p = new Pair(price);
        while(!stack.empty() && stack.peek().val <= p.val)
            p.count += stack.pop().count;
            
        stack.push(p);
        return stack.peek().count;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */