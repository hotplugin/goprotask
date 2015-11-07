package com.gopro.utils;

import com.gopro.pojos.LocationPojo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestClientException;

public class ApiCaller {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    
    public List<LocationPojo> generate(String cityName) {
        try {
            
            StringBuilder sb = new StringBuilder("http://api.goeuro.com/api/v2/position/suggest/en/");
            sb.append(cityName);
//            LOGGER.debug("Loading api by city: {}", cityName);
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

            ResponseEntity<String> result = restTemplate.exchange(sb.toString(), HttpMethod.GET, entity, String.class);
             List<LocationPojo> locationPojos = new ArrayList<>();
            if (result.getStatusCode() == HttpStatus.OK) {
                JSONArray userJson = new JSONArray(result.getBody());
               
                for (int i = 0; i < userJson.length(); i++) {
                    JSONObject responseJson = (JSONObject) userJson.getJSONObject(i);
                    if (responseJson != null) {
                        LocationPojo locationPojo = new LocationPojo();
                        int id = responseJson.getInt("_id");
                        String name = responseJson.getString("name");
                        String type = responseJson.getString("type");

                        JSONObject geo = responseJson.getJSONObject("geo_position");
                        double latitude = geo.getDouble("latitude");
                        double longitude = geo.getDouble("longitude");

                        locationPojo.setId(id);
                        locationPojo.setName(name);
                        locationPojo.setType(type);
                        locationPojo.setLatitude(latitude);
                        locationPojo.setLongitude(longitude);
                        locationPojos.add(locationPojo);
                    }

                }
            } else {
               LOGGER.error("Could not get Response from API!");
            }
            return locationPojos;
        } catch (RestClientException | JSONException e) {
           LOGGER.debug("Exception: "+e.getMessage());
        }
        return null;
    }

}
