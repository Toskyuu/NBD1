package managers;

import mainClasses.Movie;
import repositories.MovieRepository;
import java.util.List;

public class MovieManager {
    private MovieRepository movieRepository;
    public MovieManager(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public void registerMovie(Movie movie){
        movieRepository.Add(movie);
    }

    public void unregisterItem(Movie movie) {
        movieRepository.Delete(movie);
    }

    public Movie getMovie(Long id) {
        return movieRepository.Find(id);
    }

    public void edit(Movie movie) {
        movieRepository.Update(movie);
    }

    public List<Movie> getAll(){
        return movieRepository.getAll();
    }

}
