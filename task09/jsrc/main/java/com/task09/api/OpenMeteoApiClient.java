package com.task09.api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Objects;

public class OpenMeteoApiClient {
    private static final String WEATHER_URI =
            "https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&hourly=temperature_2m";

    private final OkHttpClient client;

    public OpenMeteoApiClient() {
        this.client = new OkHttpClient();
    }

    public String getWeather() {
        Request request = new Request.Builder()
                .url(WEATHER_URI)
                .build();

        try (Response response = client.newCall(request).execute())
        {
            if (!response.isSuccessful())
            {
                throw new IOException("Unexpected response code: " + response);
            }

            return Objects.requireNonNull(response.body()).string();
        } catch (IOException e) {
            throw new RuntimeException("Failed to fetch weather data", e);
        }
    }
}