public class Course_Schedule {
    int[][] matrix = new int[numCourses][numCourses];
        int[] degree = new int[numCourses];
        
        // Setting up our Graph
        for(int i = 0; i < prerequisites.length; i++){
            int ready = prerequisites[i][0];
            int prereq = prerequisites[i][1];
            if(matrix[prereq][ready] == 0)
                degree[ready]++;
            matrix[prereq][ready] = 1;
        }
        
        int count = 0;
        Queue<Integer> visited = new LinkedList<>();
        for(int i = 0; i < degree.length; i++)
            if(degree[i] == 0)
                visited.offer(i);   
        
        while(!visited.isEmpty()){
            int temp = visited.poll();
            count++;
            for(int i = 0; i < numCourses; i++)
                if(matrix[temp][i] != 0)
                    if(--degree[i] == 0)
                        visited.offer(i);
                    
            
        }
        return count == numCourses;
    }
}