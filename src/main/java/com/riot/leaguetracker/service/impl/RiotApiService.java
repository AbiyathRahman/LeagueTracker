package com.riot.leaguetracker.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class RiotApiService {
    private final WebClient webClient;
    @Value("${riot.api.key}")
    private String apiKey;
    public RiotApiService(WebClient webClient) {
        this.webClient = webClient;
    }
    public String getPuuidByRIotId(String gameName, String tagLine){
        String url = "https://americas.api.riotgames.com/riot/account/v1/accounts/by-riot-id/" + gameName + "/" + tagLine +"?api_key=" + apiKey;

        String response = webClient.get().uri(url).retrieve().bodyToMono(String.class).block();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readTree(response).get("puuid").asText();
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse response:", e);
        }
    };
    //String getSummonerByPuuid(String puuid);
    //String getPlayerRankAndTier(String puuid)

}
