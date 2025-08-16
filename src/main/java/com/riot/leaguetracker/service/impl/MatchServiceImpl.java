package com.riot.leaguetracker.service.impl;

import com.riot.leaguetracker.model.Match;
import com.riot.leaguetracker.model.Participants;
import com.riot.leaguetracker.model.Summoner;
import com.riot.leaguetracker.repository.MatchRepository;
import com.riot.leaguetracker.repository.ParticipantRepository;
import com.riot.leaguetracker.repository.SummonerRepository;
import com.riot.leaguetracker.service.MatchService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatchServiceImpl implements MatchService {

    private final RiotApiService riotApiService;
    private final MatchRepository matchRepository;
    private final ParticipantRepository participantRepository;
    private final SummonerRepository summonerRepository;
    public MatchServiceImpl(RiotApiService riotApiService, MatchRepository matchRepository, ParticipantRepository participantRepository, SummonerRepository summonerRepository) {
        this.riotApiService = riotApiService;
        this.matchRepository = matchRepository;
        this.participantRepository = participantRepository;
        this.summonerRepository = summonerRepository;
    }
    @Override
    public void fetchAndSaveMatchesData(String puuid, Integer count){
        List<String> matchIds = riotApiService.getMatchHistory(puuid, count);
        for(String id : matchIds){
            assert matchRepository != null;
            if(matchRepository.existsByMatchId(id)){
                continue;
            }
            Match match = riotApiService.getMatchDetails(id);

            saveParticipantsWithSummoner(match);
        }
    }

    private void saveParticipantsWithSummoner(Match match){
        Match savedMatch = matchRepository.save(match);
        
        for(Participants participant : savedMatch.getParticipants()){
            // Skip participants without a summoner
            if(participant.getSummoner() == null) {
                continue; // Skip to the next participant
            }
            
            String puuid = participant.getSummoner().getPuuid();
            Optional<Summoner> summoner = Optional.ofNullable(summonerRepository.findByPuuid(puuid));
            if(summoner.isPresent()){
                participant.setSummoner(summoner.get());
            }else{
                // If we can't find the summoner in the database, set it to null
                participant.setSummoner(null);
            }
        }
        
        participantRepository.saveAll(savedMatch.getParticipants());
    }
}
