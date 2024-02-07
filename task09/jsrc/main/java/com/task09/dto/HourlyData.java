package com.task09.dto;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@DynamoDBDocument
@AllArgsConstructor
@Getter @Setter
@ToString
public class HourlyData
{
    private List<Double> temperature_2m;
    private List<String> time;
}
