package solutions.vcx.invoices.infrastructure.aid;

import org.springframework.data.domain.PageImpl;

import java.text.Normalizer;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
public final class Utils {

    /**
     * Transforma uma array em uma lista, sem estourar exceção se o array for nullo ou vazio
     *
     * @param array T[]
     * @return <T> List<T>
     */
    public static <T> List<T> getList(final T... array) {
        if (array == null || array.length == 0)
            return null;
        return Arrays.asList(array);
    }

    /**
     * @param object
     * @param <T>
     * @return
     */
    public static <T> List<T> getList(final T object) {
        if (object == null)
            return null;
        return Collections.singletonList(object);
    }

    /**
     * @param array
     * @param <T>
     * @return
     */
    public static <T> Set<T> getSet(final T[] array) {
        if (array == null || array.length == 0)
            return null;
        return Set.of(array);
    }

    /**
     * @param object
     * @param <T>
     * @return
     */
    public static <T> Set<T> getSet(final T object) {
        if (object == null)
            return null;
        return Set.of(object);
    }

    /**
     * Retorna uma página vazia
     *
     * @param <T>
     * @return
     */
    public static <T> PageImpl<T> getPage() {
        return new PageImpl<>(List.of());
    }

    /**
     * Cria uma página a partir de valores
     *
     * @param values
     * @param <T>
     * @return
     */
    @SafeVarargs
    public static <T> PageImpl<T> getPage(final T... values) {
        if (values == null)
            return getPage();
        return new PageImpl<>(List.of(values));
    }

    /**
     * A palavra nocache pode ser utilizada para requisições de imagem, assim o navegador não armazena o cache.
     *
     * @param schema {String}
     * @return {String}
     */
    public static String removeNoCache(final String schema) {
        if (schema.contains("?nocache"))
            return schema.replace(schema.substring(schema.indexOf("?nocache")), "");
        return schema;
    }

    /**
     * Remove as acentuações
     *
     * @param str {String}
     * @return {String}
     */
    public static String normalizeSymbolsAndAccents(final String str) {
        final String nfdNormalizedString = Normalizer.normalize(org.apache.commons.lang3.StringUtils.defaultString(str), Normalizer.Form.NFD);
        final Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }

    /**
     * @param upperBound
     * @return
     */
    public static int randomNumber(final int upperBound) {
        final Random rand = new Random(); //instance of random class
        int intRandom = rand.nextInt(upperBound);
        return intRandom;
    }
}
