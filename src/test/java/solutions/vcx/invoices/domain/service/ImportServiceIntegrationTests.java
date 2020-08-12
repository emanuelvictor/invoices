//package solutions.vcx.invoices.domain.service;
//
//import solutions.vcx.invoices.application.builder.BufferedReaderBuilder;
//import solutions.vcx.invoices.domain.AbstractIntegrationTests;
//import solutions.vcx.invoices.domain.entities.Company;
//import org.junit.Assert;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//
//public class ImportServiceIntegrationTests extends AbstractIntegrationTests {
//
//    @Autowired
//    private ImportService importService;
//
//    private final Company movie = new Company();
//
//    private final BufferedReaderBuilder bufferedReaderBuilder;
//
//    public ImportServiceIntegrationTests() {
//        bufferedReaderBuilder = new BufferedReaderBuilder().path(ImportService.PATH_CSV_FILE).build();
//    }
//
//    @Test
//    public void buildBufferedReaderAndReadLinesMustPass() {
//        Assert.assertNotNull(bufferedReaderBuilder);
//        Assert.assertNotNull(bufferedReaderBuilder.lines());
//        Assert.assertFalse(bufferedReaderBuilder.lines().isEmpty());
//    }
//
//    @Test
//    public void buildBufferedReaderWithoutFirstLineMustPass() {
//        buildBufferedReaderAndReadLinesMustPass();
//        Assert.assertNotEquals(bufferedReaderBuilder.lines().stream().findFirst().orElseThrow().split(";")[0], "title");
//    }
//
//    @Test(expected = java.util.NoSuchElementException.class)
//    public void buildBufferedReaderMustFail() {
//        new BufferedReaderBuilder().path("movielist.csv");
//    }
//
//    @Test
//    public void extractTitleOfMovieFromColumnsMustPass() {
//        importService.extractTitleMovieFromColumns(movie, bufferedReaderBuilder.lines().stream().findFirst().orElseThrow().split(ImportService.COLUMN_DIVISOR));
//        Assert.assertEquals(movie.getTitle(), "Can't Stop the Music");
//    }
//
//    @Test
//    public void extractStudiosFromColumnsMustPass() {
//        importService.extractStudiosFromColumns(movie, bufferedReaderBuilder.lines().stream().findFirst().orElseThrow().split(ImportService.COLUMN_DIVISOR));
//        Assert.assertEquals(movie.getStudios().stream().findFirst().orElseThrow().getStudio().getName(), "Associated Film Distribution");
//    }
//
//    @Test
//    public void extractProducersFromColumnsMustPass() {
//        importService.extractProducersFromColumns(movie, bufferedReaderBuilder.lines().stream().findFirst().orElseThrow().split(ImportService.COLUMN_DIVISOR));
//        Assert.assertEquals(movie.getProducers().stream().findFirst().orElseThrow().getProducer().getName(), "Allan Carr");
//    }
//
//    @Test
//    public void extractPremiumAndIndicationsFromColumnsMustPass() {
//        importService.extractPremiumAndIndicationsFromColumns(movie, bufferedReaderBuilder.lines().stream().findFirst().orElseThrow().split(ImportService.COLUMN_DIVISOR));
//        Assert.assertEquals(movie.getIndications().stream().findFirst().orElseThrow().getPremium().getName(), ImportService.DEFAULT_NAME_OF_THE_PREMIUM);
//        Assert.assertEquals(movie.getIndications().stream().findFirst().orElseThrow().getPremium().getYear(), Integer.valueOf(1980));
//        Assert.assertTrue(movie.getIndications().stream().findFirst().orElseThrow().getWinner());
//    }
//
//    @Test
//    public void saveMovieImportedMustPass() {
//        Assert.assertNull(movie.getId());
//
//        extractTitleOfMovieFromColumnsMustPass();
//        extractStudiosFromColumnsMustPass();
//        extractProducersFromColumnsMustPass();
//        extractPremiumAndIndicationsFromColumnsMustPass();
//
//        importService.save(movie);
//
//        Assert.assertEquals(movie.getIndications().stream().findFirst().orElseThrow().getPremium().getName(), ImportService.DEFAULT_NAME_OF_THE_PREMIUM);
//        Assert.assertEquals(movie.getIndications().stream().findFirst().orElseThrow().getPremium().getYear(), Integer.valueOf(1980));
//        Assert.assertTrue(movie.getIndications().stream().findFirst().orElseThrow().getWinner());
//        Assert.assertNotNull(movie.getId());
//    }
//
//    @Test
//    public void saveAllMoviesImportedsMustPass() {
//
//        importService.getMovieRepository().deleteAll();
//
//        importService.importt();
//
//        final int moviesCount = importService.getMovieRepository().findAll().size();
//        final int studiosCount = importService.getStudioRepository().findAll().size();
//        final int producersCount = importService.getProducerRepository().findAll().size();
//        final int premiunsCount = importService.getPremiumRepository().findAll().size();
//        final int indicationsCount = importService.getIndicationRepository().findAll().size();
//        final int winnersCount = importService.getIndicationRepository().findAll().stream().filter(indication -> Optional.ofNullable(indication.getWinner()).orElse(false)).collect(Collectors.toSet()).size();
//
//        Assert.assertEquals(196, moviesCount);
//        Assert.assertEquals(64, studiosCount);
//        Assert.assertEquals(332, producersCount);
//        Assert.assertEquals(38, premiunsCount);
//        Assert.assertEquals(196, indicationsCount);
//        Assert.assertEquals(40, winnersCount);
//
//        importService.getMovieRepository().deleteAll();
//
//    }
//}
