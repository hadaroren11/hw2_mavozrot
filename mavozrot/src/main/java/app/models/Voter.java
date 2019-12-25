package app.models;

public class Voter {
    private int SSN;// Social Security number, id of USA - 9 digits
    // CHECK: i think we have the id in the path
    private String name;
    private String stateName;
    private String vote;// we will numerate the candidate, in all the servers in al

    public Voter(int SSN, String name, String stateName) {
        this.SSN = SSN;
        this.stateName = stateName;
        this.vote = "";
        this.name = name;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    public int getSSN() {
        return SSN;
    }

    public String getName() {
        return name;
    }

    public String getStateName() {
        return stateName;
    }

    public String getVote() {
        return vote;
    }
}
