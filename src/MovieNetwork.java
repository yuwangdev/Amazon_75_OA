import java.util.*;

public class MovieNetwork {

    public static void main(String[] args) {
        List<Movie> neibors = new ArrayList<>();

        List<Movie> nextNei = new ArrayList<>();
        nextNei.add(new Movie(8, 101, null));
        nextNei.add(new Movie(9, 100, null));

        neibors.add(new Movie(1, 90, null));
        neibors.add(new Movie(2, 80, null));
        neibors.add(new Movie(3, 70, null));
        neibors.add(new Movie(4, 60, null));
        neibors.add(new Movie(5, 50, null));
        neibors.add(new Movie(6, 99, null));

        neibors.add(new Movie(7, 10, nextNei));
        Movie root = new Movie(0, 100, neibors);

        System.out.println(getMovieRecommendations(root, 1));
        System.out.println(getMovieRecommendations(root, 2));
        System.out.println(getMovieRecommendations(root, 3));

    }

    public static Set<Movie> getMovieRecommendations(Movie movie, int N) {
        if (movie == null) return new HashSet<>();
        if (N <= 0) return new HashSet<>();

        Queue<Movie> queue = new LinkedList<>();
        PriorityQueue<Movie> minPriorityQueue = new PriorityQueue<>(new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                return Float.compare(o1.getRating(), o2.getRating());
            }
        });
        Set<Movie> result = new HashSet<>();
        Set<Integer> isVisited = new HashSet<>();
        queue.offer(movie);

        while (!queue.isEmpty()) {
            Movie currentMovie = queue.poll();
            if (currentMovie != null && currentMovie.getSimilarMovies() != null) {
                for (Movie neighbor : currentMovie.getSimilarMovies()) {
                    if (isVisited.add(neighbor.getId())) {
                        if (neighbor.getId() != movie.getId()) {
                            minPriorityQueue.offer(neighbor);
                        }
                        if (minPriorityQueue.size() > N) {
                            minPriorityQueue.poll();
                        }
                        queue.offer(neighbor);
                    }
                }


            }

        }

        while (!minPriorityQueue.isEmpty()) {
            result.add(minPriorityQueue.poll());
        }

        return result;
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


        public float getRating() {
            return rating;
        }

        public int getId() {
            return id;
        }

        public List<Movie> getSimilarMovies() {
            return similarMovies;
        }

        @Override
        public String toString() {
            return "Movie{" +
                    "id=" + id +
                    ", rating=" + rating +
                    '}';
        }
    }


}
