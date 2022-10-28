package com.challenge.services.interfaces;

import com.challenge.models.dto.BorderCrossing;
import com.challenge.models.raw.githubusercontent.com.Country;

public interface ICountryService {
    BorderCrossing getBorderCrossing(String origin, String destination);

    Country[] getCountries();
}
