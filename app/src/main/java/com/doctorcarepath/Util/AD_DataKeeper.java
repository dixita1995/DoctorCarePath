package com.doctorcarepath.Util;

public class AD_DataKeeper {
    private int appliedCredit = 0;
    private int autoLoans = 0;
    private int creditCards = 0;
    private int creditLimit = 0;
    private int firstOpenCredit = 0;
    private int lastNegativeItem = 0;
    private int mortgages = 0;
    private int otherLoans = 0;
    private int recentBalances = 0;
    private int retailFinances = 0;
    private int score = -1;
    private int studentLoans = 0;

    public static class DataKeeperHolder {
        public static final AD_DataKeeper HOLDER_INSTANCE = new AD_DataKeeper();
    }

    public static AD_DataKeeper getInstance() {
        return DataKeeperHolder.HOLDER_INSTANCE;
    }

    public int getScore() {
        int i = this.score;
        if (i > 0) {
            return i;
        }
        return calculateScore();
    }

    private int calculateScore() {
        int i;
        if (getLastNegativeItem() == 0) {
            i = 80;
        } else {
            i = getLastNegativeItem() * 10;
        }
        int creditCards2 = (((((((((i + 590) + (getCreditCards() * 3)) + getMortgages()) - getRetailFinances()) + (getAutoLoans() * 1)) - (getStudentLoans() * 2)) - getOtherLoans()) - (getRecentBalances() / 70000)) - (getAppliedCredit() * 5)) + (getFirstOpenCredit() * 10);
        if (creditCards2 > 800) {
            return 800;
        }
        if (creditCards2 < 500) {
            return 500;
        }
        return creditCards2;
    }

    public int getLastNegativeItem() {
        return this.lastNegativeItem;
    }

    public void setLastNegativeItem(int i) {
        this.lastNegativeItem = i;
    }

    public int getCreditCards() {
        return this.creditCards;
    }

    public void setCreditCards(int i) {
        this.creditCards = i;
    }

    public int getMortgages() {
        return this.mortgages;
    }

    public void setMortgages(int i) {
        this.mortgages = i;
    }

    public int getRetailFinances() {
        return this.retailFinances;
    }

    public void setRetailFinances(int i) {
        this.retailFinances = i;
    }

    public int getAutoLoans() {
        return this.autoLoans;
    }

    public void setAutoLoans(int i) {
        this.autoLoans = i;
    }

    public int getStudentLoans() {
        return this.studentLoans;
    }

    public void setStudentLoans(int i) {
        this.studentLoans = i;
    }

    public int getOtherLoans() {
        return this.otherLoans;
    }

    public void setOtherLoans(int i) {
        this.otherLoans = i;
    }

    public int getCreditLimit() {
        return this.creditLimit;
    }

    public void setCreditLimit(int i) {
        this.creditLimit = i;
    }

    public int getRecentBalances() {
        return this.recentBalances;
    }

    public void setRecentBalances(int i) {
        this.recentBalances = i;
    }

    public int getAppliedCredit() {
        return this.appliedCredit;
    }

    public void setAppliedCredit(int i) {
        this.appliedCredit = i;
    }

    public int getFirstOpenCredit() {
        return this.firstOpenCredit;
    }

    public void setFirstOpenCredit(int i) {
        this.firstOpenCredit = i;
    }
}
