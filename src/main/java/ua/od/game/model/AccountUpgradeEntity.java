package ua.od.game.model;

public class AccountUpgradeEntity {
    private Integer upgradeId;
    private Integer amount;

    public Integer getUpgradeId() {
        return upgradeId;
    }

    public void setUpgradeId(Integer upgradeId) {
        this.upgradeId = upgradeId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "AccountUpgradeEntity{" +
                "upgradeId=" + upgradeId +
                ", amount=" + amount +
                '}';
    }
}
