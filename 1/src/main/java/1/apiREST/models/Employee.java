package app.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Voter {
    private int SSN;//Social Security number, id of USA - 9 digits
    //CHECK: i think we have the id in the path
    private int vote;//we will numerate the candidate, in all the servers in al
            @JsonProperty(value = "SSN", required = true) int SSN,
                    @JsonProperty(value = "vote", required = true) int vote) {
        this.SSN = SSN;
    }
    public Voter setVote(int vote) {
        this.vote = vote;
    }
    public int getSSN() { return SSN; }
    public int getVote() { return vote; }
}

