package tk.aizydorczyk.model.dto;

import java.math.BigDecimal;

public class SummaryDto {
    private Long numberOfCars;
    private BigDecimal valueOfCars;
    private Long lentCars;
    private Long repairedCars;

    public Long getNumberOfCars() {
        return numberOfCars;
    }

    public void setNumberOfCars(Long numberOfCars) {
        this.numberOfCars = numberOfCars;
    }

    public BigDecimal getValueOfCars() {
        return valueOfCars;
    }

    public void setValueOfCars(BigDecimal valueOfCars) {
        this.valueOfCars = valueOfCars;
    }

    public Long getLentCars() {
        return lentCars;
    }

    public void setLentCars(Long lentCars) {
        this.lentCars = lentCars;
    }

    public Long getRepairedCars() {
        return repairedCars;
    }

    public void setRepairedCars(Long repairedCars) {
        this.repairedCars = repairedCars;
    }
}
