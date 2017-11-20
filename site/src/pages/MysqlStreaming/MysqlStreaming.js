import React from 'react';
import {loadJson} from '../../util';
import {ChartAndTable, Choose, TimeUnits} from '../../components';
import {JmhChartPage} from '..';

const xDesc = {
  title: 'Method',
  prop: 'method',
  values: [
    'ConnectorJ.atOnce',
    'ConnectorJ.stream',
    'MariaDb.atOnce',
    'MariaDb.stream',
  ],
};

const yDesc = {
  title: 'limit',
  prop: 'limit',
  values: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 200, 300, 400, 500, 600, 700, 800, 900, 1000]
};

class Environments extends React.Component {
  constructor(props) {
    super(props);

    this.items = [
      {
        label: 'local',
        value: 'local',
      },
      {
        label: 'bad network (Wi-Fi)',
        value: 'wifi',
      },
      {
        label: 'good network (Wire)',
        value: 'wire',
        default: true,
      },
      {
        label: 'strange',
        value: 'strange',
      },
    ];
  }

  render() {
    const {onChange} = this.props;
    return (
      <Choose label="Environment: " onChange={onChange} items={this.items}/>
    );
  }
}

function getTicks(inc, max) {
  const result = [];
  for (let i = 0; i <= max; i += inc) {
    result.push(i);
  }
  return result;
}

class MysqlStreamingImpl extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      extractor: null,
      environment: 'wire'
    };

    this.limitsBelow100Options = {
      legend: {
        // doesn't work with materialized UI
        position: 'bottom'
      },
      // doesn't work with materialized UI
      focusTarget: 'category',
      hAxis: {
        title: 'row count',
        ticks: getTicks(10, 100),
      },
      vAxis: {
        title: 'time, microseconds',
        format: 'short',
        viewWindow: {
          min: 0,
        },
      },
    };
    this.limitsBelow1kOptions = JSON.parse(JSON.stringify(this.limitsBelow100Options));
    this.limitsBelow1kOptions.hAxis.ticks = getTicks(100, 1000);
  }

  render() {
    const {extractor, environment} = this.state;
    const {jmhList} = this.props;

    return (
      <div>
        <h3>Introduction</h3>

        <p>
          The performance tests are performed via <a href="http://openjdk.java.net/projects/code-tools/jmh/">JMH</a>.
          The configuration of a hardware is Intel® Core™ i7–5600U CPU @ 2.60GHz × 4 (2 core + 2 HT) with 16 GB RAM.
        </p>

        <h3>Charts</h3>

        <Environments onChange={environment => this.setState({environment})}/>
        <TimeUnits onChange={extractor => this.setState({extractor})}/>

        <h3 id="below-100">LIMIT below 100</h3>

        <ChartAndTable
          chartType="LineChart"
          dataTable={jmhList}
          extractor={extractor}
          filter={d => d.environment === environment && d.limit <= 100}
          title="LIMIT below 100, microseconds"
          xDesc={xDesc}
          yDesc={yDesc}
          options={this.limitsBelow100Options}
        />

        <h3 id="all-drivers">All LIMITs</h3>

        <ChartAndTable
          chartType="LineChart"
          dataTable={jmhList}
          extractor={extractor}
          filter={d => d.environment === environment && d.limit <= 1000}
          title="All LIMITs, microseconds"
          xDesc={xDesc}
          yDesc={yDesc}
          options={this.limitsBelow1kOptions}
        />
      </div>
    );
  }
}

function exportDimensions(benchmark, params) {
  //'com.komanov.mysql.streaming.jmh.ConnectorJBenchmark.atOnce'

  const [driverPart, method, ...other] = benchmark.split('Benchmark.');
  if (!driverPart || !method || other.length > 0) {
    throw new Error('Expected 2 parts in a benchmark: ' + benchmark);
  }

  const {limit, environment} = params;

  return {
    method: driverPart.substring(driverPart.lastIndexOf('.') + 1) + '.' + method,
    limit: parseInt(limit),
    environment,
  };
}

function fetchAndCombineResults() {
  return Promise.all([
    loadJson('/data/mysql-streaming/jmh-results-local.json'),
    loadJson('/data/mysql-streaming/jmh-results-wifi.json'),
    loadJson('/data/mysql-streaming/jmh-results-wire.json'),
    loadJson('/data/mysql-streaming/jmh-results-strange.json'),
  ]).then(values => {
    function flattenAndSetEnvironment(index, environment) {
      const list = flatten(values[index].data);
      list.forEach(v => v.params.environment = environment);
      return list;
    }

    return {
      data: flatten([
        flattenAndSetEnvironment(0, 'local'),
        flattenAndSetEnvironment(1, 'wifi'),
        flattenAndSetEnvironment(2, 'wire'),
        flattenAndSetEnvironment(3, 'strange')
      ])
    };
  });
}

function flatten(listOfLists) {
  const result = [];
  listOfLists.forEach(l => l.forEach(v => result.push(v)));
  return result;
}

const MysqlStreaming = JmhChartPage(
  MysqlStreamingImpl,
  {
    fetchFunc: fetchAndCombineResults,
    exportDimensionsFunc: exportDimensions,
    headerText: 'The Cost of a Streaming Data from MySQL',
  }
);

export default MysqlStreaming;
