package ua.od.game.model;

public class ResourceSetEntity {
    Integer id;
    Integer setId;
    Integer resourceId;
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

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "ResourceSetEntity{" +
                "id=" + id +
                ", setId=" + setId +
                ", resourceId=" + resourceId +
                ", amount=" + amount +
                '}';
    }
}
