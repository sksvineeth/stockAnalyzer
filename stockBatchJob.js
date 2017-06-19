var exec = require('child_process').exec
const sys = require('sys')



function f1(){

  function puts(error,stdout,stderr) { sys.puts(stdout)}
  exec("Rscript --vanilla rAnalysisBatch.R AAPL", function(err, stdout, stderr){
   console.log(stdout);
  })

}

setTimeout(f1, 30000);
