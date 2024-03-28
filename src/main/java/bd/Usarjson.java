package bd;

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
		}
	}
