const http = require("http");
const Request = require('request');
const sys = require('sys')
const logger = require("./utils/Logger.js")
var exec = require('child_process').exec

logger.info("Server has started up successfully")

var server = http.createServer(function(request, response) {
  var data = "";
  request.on("error", function(err){
      console.log(err);
  })
  request.on("data", function(chunk){
      data += chunk.toString();
      response.writeHead(200);
      response.end("Subscription successfull");
  })

  request.on("end", function() {
         //data += chunk.toString();
         var jsonData = JSON.parse(data);
         logger.debug("Request made for Ticker:",jsonData.Ticker)

         //call the R script
         logger.info("Executing R Analysis Script")
         function puts(error,stdout,stderr) { sys.puts(stdout)}
         exec("Rscript --vanilla rAnalysis.R "+jsonData.Ticker, function(err, stdout, stderr){
         	console.log(stdout);
         })

         logger.info("successfully Executed R Analysis Script")
         response.writeHead(200);
         response.end("Subscription successfull");
      });
});
server.listen(8080,"0.0.0.0");
