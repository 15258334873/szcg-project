package tx.bxp.dto;

import java.math.BigDecimal;

/**
 * @program: szcg-project
 * @description:
 * @author:pck
 * @create: 2018-09-14 16:34
 **/
public class ProjectAward {

    private BigDecimal Cumulative;
    private BigDecimal Noreceive;
    private Integer integral;

    public BigDecimal getCumulative() {
        return Cumulative;
    }

    public void setCumulative(BigDecimal cumulative) {
        Cumulative = cumulative;
    }

    public BigDecimal getNoreceive() {
        return Noreceive;
    }

    public void setNoreceive(BigDecimal noreceive) {
        Noreceive = noreceive;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }
}
