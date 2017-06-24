import java.util.*;

/**
 * Created by Yu Wang on 2017-06-23.
 */
public class MovieNetwork {

    public static void main(String[] args) {
        System.out.println(getMovieRecommendations(null, 3));

        PriorityQueue<Movie> priorityQueue = new PriorityQueue<>(new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                return Float.compare(o1.getRating(), o2.getRating());
            }
        });

        priorityQueue.offer(new Movie(1,6));
        priorityQueue.offer(new Movie(2,4));
        priorityQueue.offer(new Movie(3,7));
        priorityQueue.offer(new Movie(4,8));

        while (!priorityQueue.isEmpty()){
            System.out.println(priorityQueue.poll().getId());
        }



    }

    public static Set<Movie> getMovieRecommendations(Movie movie, int N) {
        if (movie == null) return null;
        Queue<Movie> q = new LinkedList<Movie>();
        PriorityQueue<Movie> minHeap = new PriorityQueue<>(new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                return Float.compare(o1.getRating(), o2.getRating());
            }
        });
        Set<Movie> res = new HashSet<>();
        Set<Integer> visited = new HashSet<Integer>();
        q.offer(movie);

        while (!q.isEmpty()) {
            Movie cur = q.poll();
            for (Movie neighbor : cur.getSimilarMovies()) {
                if (visited.add(neighbor.getId())) {
                    if (neighbor.getId() != movie.getId()) {
                        minHeap.offer(neighbor);
                    }
                    if (minHeap.size() > N) {
                        minHeap.poll();
                    }
                    q.offer(neighbor);
                }
            }
        }

        while (!minHeap.isEmpty()) {
            res.add(minHeap.poll());
        }

        return res;
    }

    public static class Movie {
        private int id;
        private float rating;
        private List<Movie> similarMovies;

        public Movie(int id, float rate, List<Movie> neighbors) {
            this.id = id;
            this.rating = rate;
            this.similarMovies = neighbors;
        }

        public Movie(int id, float rating) {
            this.id = id;
            this.rating = rating;
        }

        public float getRating() {
            return rating;
        }

        public int getId() {
            return id;
        }

        public List<Movie> getSimilarMovies() {
            return similarMovies;
        }
    }


}
