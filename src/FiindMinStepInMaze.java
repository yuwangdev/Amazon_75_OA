import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Yu Wang on 2017-06-23.
 */
public class FiindMinStepInMaze {

    public static void main(String[] args) {
        int mat[][] =
                {
                        {1, 0, 1, 1, 1, 1, 0, 1, 1, 1},
                        {1, 0, 1, 0, 1, 1, 1, 0, 1, 1},
                        {1, 1, 1, 0, 1, 1, 0, 1, 0, 1},
                        {0, 0, 0, 0, 1, 0, 0, 0, 0, 1},
                        {1, 1, 1, 0, 1, 1, 1, 0, 1, 0},
                        {1, 0, 1, 1, 1, 1, 0, 1, 0, 0},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {1, 0, 1, 1, 1, 1, 0, 1, 1, 1},
                        {1, 1, 0, 0, 0, 0, 1, 0, 0, 1}
                };

        int[] s = {0, 0};
        int[] b = {3, 4};
        int dist = solve(mat, s, b);

        System.out.println(dist);
    }

    public static int solve(int[][] matrix, int[] source, int[] destination) {
        if (matrix == null) return -1;
        if (matrix.length < 1) return -1;
        int rowDim = matrix.length;
        int colDim = matrix[0].length;
        if (!isValid(source[0], source[1], matrix) || !isValid(destination[0], destination[1], matrix)) return -1;

        int ROW_DIRECTION[] = {-1, 0, 0, 1};
        int COL_DIRECTION[] = {0, -1, 1, 0};

        boolean[][] isVisited = new boolean[rowDim][colDim];
        for (int i = 0; i < rowDim; i++) {
            for (int j = 0; j < colDim; j++)
                isVisited[i][j] = false;
        }

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(source[0], source[1], 0));
        isVisited[source[0]][source[1]] = true;

        while (!queue.isEmpty()) {
            Point current = queue.poll();
            if (current.x == destination[0] && current.y == destination[1]) return current.dist;

            for (int i = 0; i < 4; i++) {
                int rowNext = current.x + ROW_DIRECTION[i];
                int colNext = current.y + COL_DIRECTION[i];
                if (isValid(rowNext, colNext, matrix) && matrix[rowNext][colNext] == 1 &&
                        !isVisited[rowNext][colNext]) {
                    isVisited[rowNext][colNext] = true;
                    queue.add(new Point(rowNext, colNext, current.dist + 1));
                }
            }
        }

        return -1;
    }

    private static boolean isValid(int x, int y, int[][] matrix) {
        if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length) return false;
        return true;
    }


    public static class Point {
        int x;
        int y;
        int dist;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}
