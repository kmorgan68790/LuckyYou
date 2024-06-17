package learn.luckyyou.models;

import java.time.LocalDate;
import java.util.Objects;

public class Zodiac {
    private int zodiacId;
    private String zodiacName;
    private String zodiacDescription;
    private LocalDate zodiacStart;
    private LocalDate zodiacEnd;


    public Zodiac(int zodiacId, String zodiacName, String zodiacDescription, LocalDate zodiacStart, LocalDate zodiacEnd) {
        this.zodiacId = zodiacId;
        this.zodiacName = zodiacName;
        this.zodiacDescription = zodiacDescription;
        this.zodiacStart = zodiacStart;
        this.zodiacEnd = zodiacEnd;
    }

    public int getZodiacId() {
        return zodiacId;
    }

    public void setZodiacId(int zodiacId) {
        this.zodiacId = zodiacId;
    }

    public String getZodiacName() {
        return zodiacName;
    }

    public void setZodiacName(String zodiacName) {
        this.zodiacName = zodiacName;
    }

    public String getZodiacDescription() {
        return zodiacDescription;
    }

    public void setZodiacDescription(String zodiacDescription) {
        this.zodiacDescription = zodiacDescription;
    }

    public LocalDate getZodiacStart() {
        return zodiacStart;
    }

    public void setZodiacStart(LocalDate zodiacStart) {
        this.zodiacStart = zodiacStart;
    }

    public LocalDate getZodiacEnd() {
        return zodiacEnd;
    }

    public void setZodiacEnd(LocalDate zodiacEnd) {
        this.zodiacEnd = zodiacEnd;
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Zodiac zodiac)) return false;
        return zodiacId == zodiac.zodiacId && Objects.equals(zodiacName, zodiac.zodiacName) && Objects.equals(zodiacDescription, zodiac.zodiacDescription) && Objects.equals(zodiacStart, zodiac.zodiacStart) && Objects.equals(zodiacEnd, zodiac.zodiacEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zodiacId, zodiacName, zodiacDescription, zodiacStart, zodiacEnd);
    }
}
