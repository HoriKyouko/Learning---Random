public class Course_Schedule_II {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> matrix = new ArrayList<>();
        int[] degree = new int[numCourses];
        
        // Setting up our Graph
        for(int i = 0; i < numCourses; i++)
            matrix.add(new ArrayList<>());
        
        for(int [] edge : prerequisites){
            int ready = edge[0];
            int prereq = edge[1];
            degree[ready]++;
            matrix.get(prereq).add(ready);
        }
        
        Queue<Integer> visited = new LinkedList<>();
        for(int i = 0; i < degree.length; i++)
            if(degree[i] == 0)
                visited.offer(i);   
        
        int count = 0;
        int [] output = new int[numCourses];
        while(!visited.isEmpty()){
            int temp = visited.poll();
            output[count++] = temp;
            for(int i: matrix.get(temp))
                if(--degree[i] == 0)
                    visited.offer(i);
        }
        return count == output.length ? output : new int[0];
    }
}