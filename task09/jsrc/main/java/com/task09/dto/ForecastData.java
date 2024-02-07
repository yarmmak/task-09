package com.task09.dto;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@DynamoDBDocument
@AllArgsConstructor
@Getter @Setter
@ToString
public class ForecastData
{
    private int elevation;
    private double generationtime_ms;
    private HourlyData hourly;
    private HourlyUnitsData hourly_units;
    private double latitude;
    private double longitude;
    private String timezone;
    private String timezone_abbreviation;
    private int utc_offset_seconds;
}

