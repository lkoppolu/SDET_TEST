package service;

import java.net.MalformedURLException;
import java.util.List;

public interface FindCapitalService {

    /**
     * Returns the list of capital for thr given country name.
     *
     * @param countryName A non-{@code null}, blank or empty string for country name
     * @return {@link List} of {@link String} capital cities for the given country
     */
    List<String> findCapitalByName(String countryName) throws MalformedURLException;

    /**
     * Returns the list of capitals for the give country code.
     *
     * @param countryCode A non-{@code null}, blank or empty string for country code
     * @return {@link List} of {@link String} capital cities for the given country
     */
    List<String> findCapitalByCode(String countryCode) throws MalformedURLException;

    /**
     * Prints the list of capitals for the give country name.
     *
     * @param filePath A non-{@code null}, blank or empty string for absolute file path
     * @return {@link List} of {@link String} capital cities for the given country
     */
    void findCapitalsByFile(String filePath);
}
