package app.models;

public class Voter {
    private int SSN;//Social Security number, id of USA - 9 digits
    //CHECK: i think we have the id in the path
    private int vote;//we will numerate the candidate, in all the servers in al
    public Voter(int SSN,int vote) {
        this.SSN = SSN;
        this.vote = vote;
    }
    public void setVote(int vote) {
        this.vote = vote;
    }
    public int getSSN() { return SSN; }
    public int getVote() { return vote; }
}

