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
public class HourlyUnitsData
{
    private String temperature_2m;
    private String time;
}
