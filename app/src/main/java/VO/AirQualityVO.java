package VO;

import java.io.Serializable;

public class AirQualityVO implements Serializable {
    String MSRDT_DE = null;        //측정일시
    String MSRSTE_NM = null;       //측정소명
    Double NO2 = null;             //이산화질소
    Double O3 = null;	            //오존농도
    Double CO = null;            	//일산화탄소농도(ppm)
    Double SO2 = null;	            //아황산가스(ppm)
    Double PM10 = null;	            //미세먼지(㎍/㎥)
    Double PM25 = null;            //초미세먼지(㎍/㎥)

    public void setMSRDT_DE(String MSRDT_DE) {
        this.MSRDT_DE = MSRDT_DE;
    }

    public void setCO(Double CO) {
        this.CO = CO;
    }

    public void setMSRSTE_NM(String MSRSTE_NM) {
        this.MSRSTE_NM = MSRSTE_NM;
    }

    public void setNO2(Double NO2) {
        this.NO2 = NO2;
    }

    public void setO3(Double O3) {
        this.O3 = O3;
    }

    public void setPM10(Double PM10) {
        this.PM10 = PM10;
    }

    public void setPM25(Double PM25) {
        this.PM25 = PM25;
    }

    public void setSO2(Double SO2) {
        this.SO2 = SO2;
    }

    public String getMSRDT_DE() {
        return MSRDT_DE;
    }

    public Double getCO() {
        return CO;
    }

    public String getMSRSTE_NM() {
        return MSRSTE_NM;
    }

    public Double getNO2() {
        return NO2;
    }

    public Double getO3() {
        return O3;
    }

    public Double getPM10() {
        return PM10;
    }

    public Double getSO2() {
        return SO2;
    }

    public Double getPM25() {
        return PM25;
    }
}
