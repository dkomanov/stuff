const readline = require('readline');

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
  terminal: false
});

let currentPrimaryMetric = '';

rl.on('line', (line) => {
  if (line.startsWith('Benchmark')) {
    console.log(line + '  ·gc.alloc.rate.norm     Error   Units');
  } else if (line.startsWith('SimulationBenchmark')) {
    if (line.includes(':·')) {
      if (line.includes('·gc.alloc.rate.norm')) {
        const index = line.indexOf('±');
        console.log(`${currentPrimaryMetric}            ${line.substring(index - 10)}`);
      } // otherwise - ignore
    } else {
      currentPrimaryMetric = line;
    }
  }
});
