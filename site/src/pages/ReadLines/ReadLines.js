import React from 'react';
import {loadJson} from '../../util';
import {Changelog, ChartAndTable, Choose, MultiChoose, TimeUnits} from '../../components';
import {JmhChartPage} from '..';

const runs = [
  {
    date: '2017-12-17',
    comment:
      <ul>
        <li>Initial version of benchmark.</li>
      </ul>,
  },
];
let currentRunDate = runs[0].date;
let currentHardDriveType = 'ssd';

const xDesc = {
  title: 'Method',
  prop: 'method',
  values: [
    {
      name: 'CPU only',
      value: 'baseline',
    },
    {
      name: 'Files.readAllLines',
      value: 'filesReadLines',
    },
    {
      name: 'Files.readAllBytes',
      value: 'filesReadBytes',
    },
    {
      name: 'Files.readAllBytes2',
      value: 'filesReadBytes2',
    },
    {
      name: 'BufferedReader inline',
      value: 'forEachInline',
    },
    {
      name: 'BufferedReader func',
      value: 'forEachIndirect',
    },
    {
      name: 'Files.lines',
      value: 'forEachJava',
    },
  ],
};

const yDesc = {
  title: 'CPU cycles',
  prop: 'cpuTokens',
  values: [0, 10, 25, 50, 100, 250, 500, 1000].map(v => v.toString(10)),
};

class LineCounts extends React.Component {
  constructor(props) {
    super(props);

    // "1", "10", "100", "1000", "10000", "100000", "1000000"
    this.items = [1, 10, 100, 1000, 10000, 100000, 1000000].map(v => {
      return {
        label: v,
        value: v,
        default: v === 1000
      }
    });
  }

  render() {
    const {onChange} = this.props;
    return (
      <Choose label="Line count: " onChange={onChange} items={this.items}/>
    );
  }
}

class HardDriveTypes extends React.Component {
  constructor(props) {
    super(props);

    this.items = [
      {
        label: 'SSD',
        value: 'ssd',
        default: true,
      },
      {
        label: 'HDD',
        value: 'hdd',
      },
    ];
  }

  render() {
    const {onChange} = this.props;
    return (
      <Choose label="Hard Drive Type: " onChange={onChange} items={this.items}/>
    );
  }
}

class CpuTokens extends React.Component {
  constructor(props) {
    super(props);

    this.items = yDesc.values.map(v => {
      return {
        label: v,
        value: v,
        default: parseInt(v, 10) <= 100,
      }
    });
  }

  render() {
    const {onChange} = this.props;
    return (
      <MultiChoose label="CPU cycles: " onChange={onChange} items={this.items}/>
    );
  }
}

class ReadLinesImpl extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
    };
  }

  render() {
    const {lineCount, cpuTokensMap} = this.state;
    const {jmhList} = this.props;

    return (
      <div>
        <h3>Introduction</h3>

        <p>
          The legend for tests. &laquo;Line count&raquo; is how many lines of different size are in a file,{' '}
          &laquo;CPU cycles&raquo; is how many abstract cycles spent for each line (modeling file processing).
        </p>

        <p>
          Please notice, that unlike the article, here the performance tests are performed
          via <a href="http://openjdk.java.net/projects/code-tools/jmh/">JMH</a>.
          The configuration of a hardware is Intel® Core™ i7–5600U CPU @ 2.60GHz × 4 (2 core + 2 HT) with 16 GB RAM.
        </p>

        <Changelog runs={runs} onChange={this.handleRunChange}/>
        <HardDriveTypes onChange={this.handleHardDriveTypeChange}/>
        <LineCounts onChange={lineCount => this.setState({lineCount})}/>
        <CpuTokens onChange={cpuTokensMap => this.setState({cpuTokensMap})}/>

        <h3>Chart</h3>

        <ChartAndTable
          chartType="Bar"
          dataTable={jmhList}
          extractor={pm => Math.floor(pm.score / 1000)}
          filter={d => d.lineCount === lineCount && cpuTokensMap[d.cpuTokens] === true}
          title="time, usec"
          xDesc={xDesc}
          yDesc={yDesc}
          options={{
            hAxis: {
              logScale: false,
            }
          }}
        />

        <p>
          Full JMH log is <a target="_blank" href={`/stuff/data/read-lines/jmh_${currentRunDate}.log.txt`}>here</a>.
        </p>

      </div>
    );
  }

  handleRunChange = date => {
    currentRunDate = date;
    this.props.refetch();
  };

  handleHardDriveTypeChange = hdt => {
    currentHardDriveType = hdt;
    this.props.refetch();
  };
}

function exportDimensions(benchmark, params) {
  //'com.komanov.readlines.jmh.ReadLinesBenchmark.forEachIndirect'

  const [, method, ...otherBenchmark] = benchmark.split('ReadLinesBenchmark.');
  if (!method || otherBenchmark.length > 0) {
    throw new Error('Expected 2 parts in a benchmark: ' + benchmark);
  }

  return {
    method,
    cpuTokens: params.cpuTokens,
    lineCount: parseInt(params.lineCount, 10),
  };
}

const ReadLines = JmhChartPage(
  ReadLinesImpl,
  {
    fetchFunc: () => loadJson(`/data/read-lines/jmh_${currentRunDate}_${currentHardDriveType}.json`),
    exportDimensionsFunc: exportDimensions,
    headerText: 'Exploring readLine performance',
  }
);

export default ReadLines;
