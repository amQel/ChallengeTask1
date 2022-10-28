package com.challenge.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.challenge.models.app.Node;
import com.challenge.models.dto.BorderCrossing;
import com.challenge.models.raw.githubusercontent.com.Country;
import com.challenge.services.interfaces.ICountryService;
import com.challenge.helpers.GraphCountryMapper;
import com.challenge.helpers.GraphHelper;

@Service
public class CountryService implements ICountryService {

    private static final String COUNTRIES_ENDPOINT = "https://raw.githubusercontent.com/mledoze/countries/master/countries.json";

    @Autowired
    RestTemplate restTemplate;

    @Cacheable(cacheNames = { "crossingsCache" }, key = "{ #origin, #destination }")
    @Override
    public BorderCrossing getBorderCrossing(String origin, String destination) {
        GraphCountryMapper mapper = new GraphCountryMapper(getCountries());
        GraphHelper g = new GraphHelper(mapper.getMapSize());

        List<Node> l = g.dijkstra(mapper.getGraph(),
                mapper.getIndex(origin),
                mapper.getIndex(destination));

        return mapper.getBorderCrossing(l);

    }

    @Cacheable(cacheNames = { "countriesCache" })
    @Override
    public Country[] getCountries() {
        return restTemplate.exchange(
                COUNTRIES_ENDPOINT, HttpMethod.GET, null, Country[].class).getBody();
    }

}
