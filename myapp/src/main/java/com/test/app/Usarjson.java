package com.test.app;

import org.json.JSONArray;
import org.json.JSONObject;
//import org.mockito.internal.creation.instance.InstantiationException;




import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Instant;
import java.time.ZoneId;


public class Usarjson {
    void parseTimelineJson(String rawResult) {
		
		if (rawResult==null || rawResult.isEmpty()) {
			System.out.printf("Sem dados%n");
			return;
		}
		
		JSONObject timelineResponse = new JSONObject(rawResult);
		
        ZoneId zoneId = ZoneId.of(timelineResponse.getString("timezone"));

        System.out.printf("Weather data for: %s%n", timelineResponse.getString("resolvedAddress"));
		
        JSONArray values = timelineResponse.getJSONArray("days");

        System.out.printf("Date\tMaxTemp\tMinTemp\tsunrise\tsunset%n");
		for (int i = 0; i < values.length(); i++) {
			JSONObject dayValue = values.getJSONObject(i);
            
            ZonedDateTime datetime=ZonedDateTime.ofInstant(Instant.ofEpochSecond(dayValue.getLong("datetimeEpoch")), zoneId);
            
            double maxtemp=dayValue.getDouble("tempmax");
            double mintemp=dayValue.getDouble("tempmin");
            String nasc=dayValue.getString("sunrise");
            String sunrise=dayValue.getString("sunset");
            System.out.printf("%s\t%.1f\t%.1f\t%s\t%s%n", datetime.format(DateTimeFormatter.ISO_LOCAL_DATE), maxtemp, mintemp, nasc,sunrise );
        }
    }


}
