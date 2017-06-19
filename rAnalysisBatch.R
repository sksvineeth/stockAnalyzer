library(httr)
library(jsonlite)

args = commandArgs(trailingOnly=TRUE)

symbol<- args[1]

k<-read.csv(url(paste("https://finance.yahoo.com/d/quotes.csv?s=GOOG+",symbol,"&f=opghkjabcc1")))
High<-k[3][1]
lower<-k[4][1]
Fifh<-k[5][1]
Fifl<-k[6][1]
open<-k[1][1]
pclose<-k[2][1]
chang<-k[9][1]
pl<-k[10][1]
e<-read.csv(url(paste("https://finance.yahoo.com/d/quotes.csv?s=GOOG+",symbol,"&f=l1yrm3m4a2b4edjk")))
predictif = function(v){

  if(v[1][1]=="N/A")
    v[1][1]=50.0
  if(v[2][1]=="N/A")
    v[2][1]=50.0
  if(v[3][1]=="N/A")
    v[3][1]=50.0
  if(v[4][1]=="N/A")
    v[4][1]=50.0
  if(v[5][1]=="N/A")
    v[5][1]=50.0
  if(v[6][1]=="N/A")
    v[6][1]=50.0
  if(v[7][1]=="N/A")
    v[7][1]=50.0
  if(v[8][1]=="N/A")
    v[8][1]=50.0
  if(v[9][1]=="N/A")
    v[9][1]=50.0

  if(v[10][1]=="N/A")
    v[10][1]=50.0
  if(v[11][1]=="N/A")
    v[11][1]=50.0


  if(v[4][1] > 73.56)
  {
    if(v[2][1]>2.65)
    {
      if(v[5][1]<=81.61)
      {
        return('st')
      }
      else
      {
        if(v[2][1]>3.61){return('LT')}
        else{
          if(v[9][1]<=3.04){return('LT')}
          else {return ('ST')}
        }
      }

    }

    else
    {
      if(v[8][1]<=2.71)
      {
        if(v[10][1]<=99.72)
        {
          return('ST')
        }
        else{return ('LT')}
      }
      else{
        if(v[3][1]>21.09){
          return('ST')
        }
        else{return('LT')}
      }

    }
  }
  else
  {

    if(v[4][1]<9.34){
      return('LT')
    }
    else{
      if(v[11][1]>77.38)
      {return('LT')}
      else
      {
        if(v[1][1]>62.3)
        {return('ST')}
        else{return('LT')}
      }

    }
  }

}
p<-predictif(e)
l <- list();
h <-list();
sticker<- list("AAPL","GOOGL","GOOG","MSFT","AMZN","FB","JNJ","XOM","JPM","WFC","GE","BAC","T","WMT")
f<-read.csv(url(paste("https://finance.yahoo.com/d/quotes.csv?s=GOOG+",symbol,"&f=o")))
i <- 1
while(i<15) {
    j<-read.csv(url(paste("https://finance.yahoo.com/d/quotes.csv?s=GOOG+",sticker[i],"&f=o")))
     l[[i]] <- j[1][1]
     sd<-(f[1][1]-l[i])
     h[[i]] <-sd
     i <- i + 1
}
y <- as.numeric(unlist(h))
sg<-abs(y)
g<-min(unlist(sg))
i<- 1
while(i<15) {
   if(g == y[i]){
    break}
  i<-i+1}
jh<-read.csv(url(paste("https://finance.yahoo.com/d/quotes.csv?s=",sticker[i],"+GOOG&f=n")))
sa<-names(unlist(jh[1][1]))
st<-read.csv(url(paste("https://finance.yahoo.com/d/quotes.csv?s=",symbol,"+GOOG&f=n")))
same<-names(unlist(st[1][1]))
json <- paste('{ "Name" :"',same,'", "Open":',open,',"Close" :',pclose,', "High":',High,',"Low" :',lower,', "Fifwl":',Fifl,',"Fifwh" :',Fifh,', "Change":',pl,',"Type" :"',p,'", "Similar":"',sa,'"}')

#save to firebase
r <- PUT(paste("https://stock-batch-job.firebaseio.com/.json"), body = json)
r
