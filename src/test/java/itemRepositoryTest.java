import mainClasses.Movie;
import mainClasses.MusicAlbum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import repositories.ItemRepository;

public class itemRepositoryTest {
        private MusicAlbum music = new MusicAlbum(1999, false, "Buqo", "Pop", "Madonna", 7.5, 11);
        private Movie movie = new Movie(2015, false, "Avengers", "Action", "Taika Waititi", 10, 120);
        ItemRepository ir = new ItemRepository();
        @Test
        void addTest(){
            ir.Add(movie);
            Assertions.assertEquals(ir.Find(movie.getId()), movie);
            ir.Add(music);
            Assertions.assertEquals(ir.Find(music.getId()), music);
        }

        @Test
        void updateTest(){
            ir.Add(music);
            Assertions.assertEquals(ir.Find(music.getId()), music);
            music.setAuthor("Bazzi");
            Assertions.assertEquals(ir.Find(music.getId()).getAuthor(), music.getAuthor());
        }

        @Test
        void deleteTest(){
            ir.Add(music);
            Assertions.assertEquals(ir.Find(music.getId()).getAuthor(), music.getAuthor());
            ir.Delete(music);
            Assertions.assertNull(ir.Find(music.getId()));

        }
    }
