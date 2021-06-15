package data;

public class TrialBalance {
    private String accountid;
    private Float currentbalance;

    public TrialBalance(String accountid, Float currentbalance) {
        this.accountid = accountid;
        this.currentbalance = currentbalance;
    }

    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid;
    }

    public Float getCurrentbalance() {
        return currentbalance;
    }

    public void setCurrentbalance(Float currentbalance) {
        this.currentbalance = currentbalance;
    }
}
