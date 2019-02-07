package ua.od.game.model;

public class UpgradeSetEntity {
    Integer id;
    Integer setId;
    Integer upgradeId;
    Float amount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSetId() {
        return setId;
    }

    public void setSetId(Integer setId) {
        this.setId = setId;
    }

    public Integer getUpgradeId() {
        return upgradeId;
    }

    public void setUpgradeId(Integer upgradeId) {
        this.upgradeId = upgradeId;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

}
