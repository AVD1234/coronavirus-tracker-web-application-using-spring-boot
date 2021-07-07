package com.example.CoronaVirusTracker.services;

import com.example.CoronaVirusTracker.models.Locationstats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronaVirusDataServices {

    private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

    public List<Locationstats> getAllstats() {
        return allstats;
    }

    private List<Locationstats> allstats = new ArrayList<>();

    @PostConstruct
    @Scheduled(cron = "1 * * * * *")
    public void fetchData() throws IOException, InterruptedException {
        List<Locationstats> newstats = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(VIRUS_DATA_URL))
                .build();
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        StringReader csvBodyReader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(csvBodyReader);
        for (CSVRecord record : records) {
            Locationstats locationstat = new Locationstats();
            locationstat.setState(record.get("Province/State"));
            locationstat.setCountry(record.get("Country/Region"));
            int latestcases= Integer.parseInt(record.get(record.size() - 1));
            int prevdaycases=Integer.parseInt(record.get(record.size() - 2));
            locationstat.setLatestTotalcases(latestcases);
            locationstat.setSetDiffdiffFromPrevDay(prevdaycases);
            newstats.add(locationstat);
        }
        this.allstats = newstats;

    }
}
