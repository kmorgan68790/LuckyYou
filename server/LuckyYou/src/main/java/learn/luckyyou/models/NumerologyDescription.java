package learn.luckyyou.models;

import java.util.Objects;

public class NumerologyDescription {
    private int numerologyDescriptionId;
    private String numerologyType;
    private String numerologyDescription;
    private int numerologyNumber;

    public NumerologyDescription(int numerologyDescriptionId, String numerologyType, String numerologyDescription,
                                 int numerologyNumber) {
        this.numerologyDescriptionId = numerologyDescriptionId;
        this.numerologyType = numerologyType;
        this.numerologyDescription = numerologyDescription;
        this.numerologyNumber = numerologyNumber;
    }

    public NumerologyDescription() {}

    public int getNumerologyDescriptionId() {
        return numerologyDescriptionId;
    }

    public void setNumerologyDescriptionId(int numerologyDescriptionId) {
        this.numerologyDescriptionId = numerologyDescriptionId;
    }

    public String getNumerologyType() {
        return numerologyType;
    }

    public void setNumerologyType(String numerologyType) {
        this.numerologyType = numerologyType;
    }

    public String getNumerologyDescription() {
        return numerologyDescription;
    }

    public void setNumerologyDescription(String numerologyDescription) {
        this.numerologyDescription = numerologyDescription;
    }

    public int getNumerologyNumber() {
        return numerologyNumber;
    }

    public void setNumerologyNumber(int numerologyNumber) {
        this.numerologyNumber = numerologyNumber;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof NumerologyDescription that)) return false;
        return numerologyDescriptionId == that.numerologyDescriptionId && numerologyNumber == that.numerologyNumber && Objects.equals(numerologyType, that.numerologyType) && Objects.equals(numerologyDescription, that.numerologyDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerologyDescriptionId, numerologyType, numerologyDescription, numerologyNumber);
    }
}
