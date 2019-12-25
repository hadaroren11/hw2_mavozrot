package app.controllers;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.*;

import app.models.State;
import app.models.Voter;

@RestController
public class VoterController {
    private HashMap<String, Integer> candidates = new HashMap<>();
    private HashMap<String, State> states = new HashMap<>();
    private HashMap<Integer, Voter> voters = new HashMap<>();
    String stateName;

    VoterController(String stateName) {
        this.stateName = stateName;
        // init candidates map
        // file : candidateName
        File candidatesFile = new File("../files/candidatesFile.txt");
        Scanner myReaderCandidates = new Scanner(candidatesFile);
        while (myReaderCandidates.hasNextLine()) {
            String data = myReaderCandidates.nextLine();
            candidates.put(data, 0);
        }
        myReaderCandidates.close();
        // init states map
        // file : stateName electors
        File statesFile = new File("../files/statesFile.txt");
        Scanner myReaderStates = new Scanner(statesFile);
        while (myReaderCandidates.hasNextLine()) {
            String data = myReaderCandidates.nextLine();
            String[] parts = data.split(" ");
            states.put(parts[0], new State(parts[0], Integer.parseInt(parts[1])));
        }
        myReaderStates.close();
        // add servers to statea map
        // file : stateName serverPort
        File serversFile = new File("../files/serversFile.txt");
        Scanner myReaderServers = new Scanner(serversFile);
        while (myReaderServers.hasNextLine()) {
            String data = myReaderServers.nextLine();
            String[] parts = data.split(" ");
            states.get(parts[0]).addServer(Integer.parseInt(parts[1]));
        }
        myReaderServers.close();
        // init voters map
        // file : SSN nameVoter stateVoter
        File votersFile = new File("../files/votersFile.txt");
        Scanner myReaderVoters = new Scanner(votersFile);
        while (myReaderVoters.hasNextLine()) {
            String data = myReaderVoters.nextLine();
            String[] parts = data.split(" ");
            voters.put(Integer.parseInt(parts[0]), new Voter(Integer.parseInt(parts[0]), parts[1], parts[2]));
        }
        myReaderVoters.close();
        // i think we need here barrier, wait for all the servers to finish
        // initializaion
    }

    // need to let everybode know about this change?
    // FIXME:no need of state and states!
    // FIXME: didnt understand how the client get HTTP response
    @PutMapping("/voters/{SSN}")
    String vote(@RequestBody String candidate, @PathVariable int SSN) {
        String CandidatePrevious = voters.get(SSN).getVote();
        String voterState = voters.get(SSN).getStateName();
        // this is not my state as a server
        if (voterState != this.stateName) {
            // send to one of the servers which belong this country
            int portServer = states.get(this.stateName).pickServer();
            // TODO: send this request to the picked server (127.0.0.1:portServer)
        }
        // this is my state as a server
        else {
            if (CandidatePrevious != candidate) {
                // change vote in voter
                voters.get(SSN).setVote(candidate);
                // change sum votes in candidates
                int beforeVoteCurrent = candidates.get(candidate);
                candidates.put(candidate, beforeVoteCurrent + 1);
                if (CandidatePrevious != "") {
                    int beforeVotePrevious = candidates.get(CandidatePrevious);
                    candidates.put(CandidatePrevious, beforeVotePrevious - 1);
                }
                // TODO: send everybody about this change???
            }
        }
        String returnValue = "Thanks " + voters.get(SSN).getName() + " for your vote to candidate " + candidate;
        return returnValue;
    }

    @GetMapping("/employees/{SSN}")
    String getVoterVote(@PathVariable int SSN) {
        return voters.get(SSN).getVote();
    }
}
