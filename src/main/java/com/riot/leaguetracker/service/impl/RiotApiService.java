package com.riot.leaguetracker.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.riot.leaguetracker.model.Match;
import com.riot.leaguetracker.model.Participants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Service
public class RiotApiService {
    private final WebClient webClient;
    //private SummonerRepository summonerRepository;
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
    }
    public String getSummonerTierByPuuid(String puuid){
        String url = "https://na1.api.riotgames.com/lol/league/v4/entries/by-puuid/" + puuid +"?api_key=" + apiKey;
        String response = webClient.get().uri(url).retrieve().bodyToMono(String.class).block();
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            JsonNode jsonNode = objectMapper.readTree(response);
            return jsonNode.get(0).get("tier").asText();

        }catch (Exception e){
            throw new RuntimeException("Failed to parse response:", e);
        }


    }
    public String getSummonerRankByPuuid(String puuid){
        String url = "https://na1.api.riotgames.com/lol/league/v4/entries/by-puuid/" + puuid +"?api_key=" + apiKey;
        String response = webClient.get().uri(url).retrieve().bodyToMono(String.class).block();
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            JsonNode jsonNode = objectMapper.readTree(response);
            return jsonNode.get(0).get("rank").asText();
        }catch(Exception e){
            throw new RuntimeException("Failed to parse response:", e);
        }
    }
    public Integer getSummonerWinsByPuuid(String puuid){
        String url = "https://na1.api.riotgames.com/lol/league/v4/entries/by-puuid/" + puuid +"?api_key=" + apiKey;
        String response = webClient.get().uri(url).retrieve().bodyToMono(String.class).block();
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            JsonNode jsonNode = objectMapper.readTree(response);
            return jsonNode.get(0).get("wins").asInt();
        }catch(Exception e){
            throw new RuntimeException("Failed to parse response:", e);
        }
    }
    public Integer getSummonerLossesByPuuid(String puuid){
        String url = "https://na1.api.riotgames.com/lol/league/v4/entries/by-puuid/" + puuid +"?api_key=" + apiKey;
        String response = webClient.get().uri(url).retrieve().bodyToMono(String.class).block();
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            JsonNode jsonNode = objectMapper.readTree(response);
            return jsonNode.get(0).get("losses").asInt();
        }catch(Exception e){
            throw new RuntimeException("Failed to parse response:", e);
        }
    }
    public Long getSummonerLeveledUpByPuuid(String puuid){
        String url = "https://na1.api.riotgames.com/lol/summoner/v4/summoners/by-puuid/" + puuid +"?api_key=" + apiKey;
        String response = webClient.get().uri(url).retrieve().bodyToMono(String.class).block();
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            JsonNode jsonNode = objectMapper.readTree(response);
            return jsonNode.get("summonerLevel").asLong();
        }catch(Exception e){
            throw new RuntimeException("Failed to parse response:", e);
        }
    }
    public List<String> getMatchHistory(String puuid, Integer count){
        String url = "https://americas.api.riotgames.com/lol/match/v5/matches/by-puuid/" + puuid + "/ids?start=0&count=" + count + "&api_key=" + apiKey;
        String response = webClient.get().uri(url).retrieve().bodyToMono(String.class).block();
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            return objectMapper.readValue(response,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, String.class));
        }catch(Exception e){
            throw new RuntimeException("Failed to parse response:" + e.getMessage(), e);
        }

    }
    public Match getMatchDetails(String matchId){
        String url = "https://americas.api.riotgames.com/lol/match/v5/matches/" + matchId + "?api_key=" + apiKey;
        String response = webClient.get().uri(url).retrieve().bodyToMono(String.class).block();
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            JsonNode root = objectMapper.readTree(response);
            JsonNode info = root.get("info");
            // Create Match
            Match match = new Match();
            match.setMatchId(root.get("metadata").get("matchId").asText());
            match.setGameMode(info.get("gameMode").asText());
            match.setQueueId(info.get("queueId").asInt());
            match.setGameDuration(info.get("gameDuration").asLong());
            match.setGameVersion(info.get("gameVersion").asText());
            match.setGameCreation(LocalDateTime.ofInstant(
                    Instant.ofEpochMilli(info.get("gameCreation").asLong()),
                    ZoneOffset.UTC
            ));
            match.setCreatedAt(LocalDateTime.now());
            JsonNode participantsJson = info.get("participants");
            List<Participants> participants = new ArrayList<>();
            for(JsonNode participantJson : participantsJson){
                Participants participant = new Participants();
                participant.setMatch(match);
                participant.setChampionName(participantJson.get("championName").asText());
                participant.setDeaths(participantJson.get("deaths").asInt());
                participant.setKills(participantJson.get("kills").asInt());
                participant.setAssists(participantJson.get("assists").asInt());
                participant.setTotalDamageDealt(participantJson.get("totalDamageDealt").asInt());
                participant.setTotalDamageDealtToChampions(participantJson.get("totalDamageDealtToChampions").asInt());
                participant.setTotalMinionsKilled(participantJson.get("totalMinionsKilled").asInt());
                participant.setGoldEarned(participantJson.get("goldEarned").asInt());
                participant.setCreatedAt(LocalDateTime.now());
                participants.add(participant);
            }
            match.setParticipants(participants);
            return match;
        }catch(Exception e){
            throw new RuntimeException("Failed to parse response:" + e.getMessage(), e);
        }

    }

    //String getSummonerByPuuid(String puuid);
    //String getPlayerRankAndTier(String puuid)

}
