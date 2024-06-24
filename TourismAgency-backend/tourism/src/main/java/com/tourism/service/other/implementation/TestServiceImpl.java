package com.tourism.service.other.implementation;

import com.tourism.service.other.TestService;
import org.springframework.stereotype.Service;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost; // Cambio a HttpPost
import org.apache.http.entity.StringEntity; // Importar StringEntity para manejar el cuerpo de la solicitud
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

@Service
public class TestServiceImpl implements TestService {

    @Override
    public String getFromDataAPI(String url, String jsonBody) {


        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(url);

        try {
            StringEntity entity = new StringEntity(jsonBody);
            request.setEntity(entity);

            HttpResponse response = httpClient.execute(request);
            HttpEntity responseEntity = response.getEntity();

            if (responseEntity != null) {
                return EntityUtils.toString(responseEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}

