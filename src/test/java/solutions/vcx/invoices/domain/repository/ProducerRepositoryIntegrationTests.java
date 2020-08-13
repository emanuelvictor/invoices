//package solutions.vcx.invoices.domain.repository;
//
//import solutions.vcx.invoices.domain.AbstractIntegrationTests;
//import solutions.vcx.invoices.domain.entities.Company;
//import org.junit.Assert;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.data.domain.Page;
//import org.springframework.test.context.jdbc.Sql;
//
//import java.util.List;
//
//public class ProducerRepositoryIntegrationTests extends AbstractIntegrationTests {
//
//    /**
//     *
//     */
//    @Autowired
//    private IMovieRepository movieRepository;
//
//    /**
//     *
//     */
//    @Test(expected = javax.validation.ConstraintViolationException.class)
//    public void saveMovieMustFail() {
//        this.movieRepository.save(new Company());
//    }
//
//    /**
//     *
//     */
//    @Test
//    public void saveMovieMustPass() {
//        final String title = "Os Vingadores: Ultimato";
//        final Company movie = new Company(title);
//        this.movieRepository.save(movie);
//
//        Assert.assertNotNull(movie);
//        Assert.assertNotNull(movie.getId());
//        Assert.assertEquals(movie.getTitle(), title);
//
//        this.movieRepository.deleteById(movie.getId());
//    }
//
//    /**
//     *
//     */
//    @Test(expected = DataIntegrityViolationException.class)
//    @Sql({"/dataset/companies.sql"})
//    public void saveRepeatedMovieMustPass() {
//        final String title = "Warcraft";
//        final Company movie = new Company(title);
//        this.movieRepository.save(movie);
//    }
//
//    /**
//     *
//     */
//    @Test
//    @Sql({"/dataset/companies.sql"})
//    public void findMovieByIdMustPass() {
//        final Company movie = this.movieRepository.findById(1003L).orElseThrow();
//
//        Assert.assertNotNull(movie);
//        Assert.assertNotNull(movie.getId());
//        Assert.assertEquals("Warcraft", movie.getTitle());
//    }
//
//    /**
//     *
//     */
//    @Test
//    @Sql({"/dataset/companies.sql"})
//    public void listMoviesByTitleMustReturn2() {
//        final Page<Company> pageOfMovies = this.movieRepository.findAllByTitleContaining("Guerra", null);
//
//        Assert.assertNotNull(pageOfMovies);
//        Assert.assertEquals(2, pageOfMovies.getTotalElements());
//    }
//
//    /**
//     *
//     */
//    @Test
//    @Sql({"/dataset/companies.sql"})
//    public void listMoviesByTitleMustReturn1() {
//        final Page<Company> movies = this.movieRepository.findAllByTitleContaining("mediterrâneo", null);
//
//        Assert.assertNotNull(movies);
//        Assert.assertEquals(1, movies.getTotalElements());
//    }
//
//    /**
//     *
//     */
//    @Test
//    @Sql({"/dataset/companies.sql"})
//    public void listMoviesMustReturnAll() {
//        final List<Company> movies = this.movieRepository.findAll();
//
//        Assert.assertNotNull(movies);
//        Assert.assertEquals(4, movies.size());
//    }
//
//}
