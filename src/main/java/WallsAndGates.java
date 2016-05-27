import com.leetcode.Utils.Utility;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created on 5/25/16.
 */
public class WallsAndGates {

    public int[][] shortestDistance(int[][] graph) {
        int M = graph.length;
        int N = graph[0].length;
        boolean[][] visited = new boolean[M][N];
        int[][] result = new int[M][N];
        Queue<int[]> queue = new LinkedList<int[]>();

        for(int[] eachRow : result) {
            Arrays.fill(eachRow, Integer.MAX_VALUE);
        }

        for(int i = 0; i < M; i++) {
            for(int j = 0; j < N; j++) {
                if(graph[i][j] == 0 && !visited[i][j]) {
                    result[i][j] = 0;
                    queue.add(new int[]{i,j});
                    visited[i][j] = true;
                }
            }
        }
        computeShortestDistance(graph, result, visited, queue);
        Utility.printArray(result);
        return graph;
    }

    private void computeShortestDistance(int[][] graph, int[][] result, boolean[][] visited, Queue<int[]> q) {
        int distance = 0;
        while(!q.isEmpty()) {

            distance++;
            int size = q.size();

            for(int itr = 0; itr < size; itr++) {
                int[] temp = q.poll();
                int x = temp[0];
                int y = temp[1];
                if(x - 1 >= 0 && !visited[x - 1][y] && graph[x-1][y] != -1) {
                    visited[x - 1][y] = true;
                    q.add(new int[] {x-1, y});
                    result[x - 1][y] = distance;
                }
                if(x + 1 < graph.length && !visited[x + 1][y] && graph[x + 1][y] != -1) {
                    visited[x + 1][y] = true;
                    q.add(new int[] {x + 1, y});
                    result[x + 1][y] = distance;
                }
                if(y - 1 >=0 && !visited[x][y - 1] && graph[x][y - 1] != -1) {
                    visited[x][y - 1] = true;
                    q.add(new int[] {x, y - 1});
                    result[x][y - 1] = distance;
                }
                if(y + 1 < graph[0].length && ! visited[x][y + 1] && graph[x][y + 1] != -1) {
                    visited[x][y + 1] = true;
                    q.add(new int[] {x, y+1});
                    result[x][y + 1] = distance;
                }
            }
            Utility.printArray(result);
        }
    }

    public static void main(String[] args) {
        WallsAndGates w = new WallsAndGates();
        int[][] g = new int[][] {
                {0,1,2,-1,0},
                {1,3,4,-1,3},
                {1,3,1,5,3},
                {1,3,4,5,3},
                {0,3,4,5,3}
        };
        w.shortestDistance(g);
    }
}
