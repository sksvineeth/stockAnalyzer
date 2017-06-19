package com.example.stevenabraham.stocksassistant;

/**
 * Created by stevenabraham on 18/06/17.
 */

public class AnalysisResult {

    private Double Change;
    private Double Close;
    private Double Fifwh;
    private Double Fifwl;
    private Double High;
    private Double Low;
    private String Name;
    private Double Open;
    private String Similar;
    private String Type;

    AnalysisResult(){

    }


    AnalysisResult(Double Change,Double Close,Double Fifwh,Double Fifwl,Double High,Double Low,Double Open, String Name, String Similar,String Type){
        this.Change = Change;
        this.Close = Close;
        this.Fifwh = Fifwh;
        this.Fifwl = Fifwl;
        this.High = High;
        this.Low = Low;
        this.Name = Name;
        this.Open =Open;
        this.Similar = Similar;
        this.Type = Type;
    }

    public Double getChange(){ return this.Change;}
    public String getName(){ return this.Name;}
    public String getSimilar(){ return this.Similar;}
    public String getType(){ return this.Type;}

    public Double getClose(){ return this.Close;}
    public Double getFifwh(){ return this.Fifwh;}
    public Double getFifwl(){ return this.Fifwl;}
    public Double getHigh(){ return this.High;}
    public Double getLow(){ return this.Low;}
    public Double getOpen(){ return this.Open;}





    public void setChange(Double Change){ this.Change = Change;}
    public void setName(String Name){ this.Name = Name;}
    public void setSimilar(String Similar){ this.Similar = Similar;}
    public void setType(String Type){ this.Type = Type;}

    public void setClose(Double Close){ this.Close = Close;}
    public void setFifwh(Double Fifwh){ this.Fifwh = Fifwh;}
    public void setFifwl(Double Fifwl){ this.Fifwl = Fifwl;}
    public void setHigh(Double High){ this.High = High;}
    public void setLow(Double Low){ this.Low = Low;}
    public void setOpen(Double Open){ this.Open = Open;}
//
//






}
