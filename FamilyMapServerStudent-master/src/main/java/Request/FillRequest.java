package Request;

public class FillRequest {

    private String userName;
    private int numGenerations;

    public FillRequest(String userName, int numGenerations){
        this.userName = userName;
        this.numGenerations = numGenerations;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String userName) {
        this.userName = userName;
    }

    public int getNumGenerations() {
        return numGenerations;
    }

    public void setNumGenerations(int numGenerations) {
        this.numGenerations = numGenerations;
    }

}
