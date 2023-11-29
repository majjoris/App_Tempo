package telas;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.json.JSONArray;
import org.json.JSONObject;

public class Usarjson {
	void parseTimelineJson(String rawResult) {

		if (rawResult==null || rawResult.isEmpty()) {
			System.out.printf("Sem dados%n");
			return;
		}

		try (PrintStream ps = new PrintStream(new FileOutputStream("SaidaJson.txt", true))) {
			ps.println(rawResult);
		} catch (IOException e) {
			System.out.println("Erro ao adicionar texto no arquivo: " + e.getMessage());
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
