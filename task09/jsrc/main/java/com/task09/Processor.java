package com.task09;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import com.syndicate.deployment.annotations.LambdaUrlConfig;
import com.syndicate.deployment.annotations.lambda.LambdaHandler;
import com.syndicate.deployment.model.TracingMode;
import com.task09.api.OpenMeteoApiClient;
import com.task09.dto.ForecastData;
import com.task09.dto.ResponseData;
import com.task09.model.WeatherRecord;

import java.util.UUID;

@LambdaHandler(lambdaName = "processor",
		roleName = "processor-role",
		tracingMode = TracingMode.Active
)
@LambdaUrlConfig
public class Processor implements RequestHandler<Object, ResponseData>
{
	private static final String AWS_REGION = "eu-central-1";

	private AmazonDynamoDB amazonDynamoDB;
	private DynamoDBMapper dbMapper;
	private LambdaLogger logger;

	public Processor()
	{
		this.initDynamoDbClient();
		this.initDynamoDbMapper();
	}

	public ResponseData handleRequest(Object request, Context context)
	{
		this.initLambdaLogger(context);
		OpenMeteoApiClient openMeteoApiClient = new OpenMeteoApiClient();;
		Gson gson = new Gson();

		ForecastData forecastData = gson.fromJson(openMeteoApiClient.getWeather(), ForecastData.class);
		WeatherRecord weatherRecord = new WeatherRecord(UUID.randomUUID().toString(), forecastData);

		dbMapper.save(weatherRecord);
		logger.log(String.format("Weather data added successfully! Data: %s", weatherRecord));

		return buildResponse();
	}

	private void initDynamoDbClient()
	{
		this.amazonDynamoDB = AmazonDynamoDBClientBuilder.standard()
				.withRegion(AWS_REGION)
				.build();
	}

	private void initDynamoDbMapper()
	{
		this.dbMapper = new DynamoDBMapper(amazonDynamoDB);
	}

	private void initLambdaLogger(final Context context)
	{
		this.logger = context.getLogger();
	}

	private ResponseData buildResponse()
	{
		return ResponseData.builder()
				.statusCode("200")
				.message("Weather data added successfully!")
				.build();
	}
}
