import React from 'react';
import {loadJson} from '../../util';
import {Changelog, ChartAndTable, Choose, TimeUnits} from '../../components';
import {JmhChartPage} from '..';

const runs = [
  {
    date: '2017-12-25',
    comment:
      <ul>
        <li>Initial version of benchmark.</li>
      </ul>,
  },
];
let currentRunDate = runs[0].date;

const xDesc = {
  title: 'Method',
  prop: 'method',
  values: [
    {
      name: 'Charset.decode',
      value: 'charset',
    },
    {
      name: 'localMethods',
      value: 'localMethodsScala',
    },
    {
      name: 'localMethodsIndices',
      value: 'localMethodsIndices',
    },
    {
      name: 'sequentialLoop (Scala)',
      value: 'sequentialLoopScala',
    },
    {
      name: 'sequentialLoop (Java)',
      value: 'sequentialLoopJava',
    },
    {
      name: 'changeIndexInsideLoop (Scala)',
      value: 'changeIndexInsideLoopScala',
    },
    {
      name: 'changeIndexInsideLoop (Java)',
      value: 'changeIndexInsideLoopJava',
    },
    {
      name: 'changeIndexInsideLoopByteMagic (Scala)',
      value: 'changeIndexInsideLoopByteMagicScala',
    },
    {
      name: 'changeIndexInsideLoopByteMagic (Java)',
      value: 'changeIndexInsideLoopByteMagicJava',
    },
  ],
};

const yDesc = {
  title: 'Line Length',
  prop: 'lineLength',
  values: [0, 1, 2, 5, 10, 100, 10000].map(v => v.toString(10)),
};

class CharacterTypes extends React.Component {
  constructor(props) {
    super(props);

    this.items = [
      {
        label: 'ASCII only',
        value: 'ASCII',
      },
      {
        label: 'Single char only',
        value: 'SINGLE_CHAR_ONLY',
      },
      {
        label: 'All chars',
        value: 'MIXED',
        default: true
      },
    ];
  }

  render() {
    const {onChange} = this.props;
    return (
      <Choose label="Characters: " onChange={onChange} items={this.items}/>
    );
  }
}

class ReadLinesImpl extends React.Component {
  constructor(props) {
    super(props);

    this.state = {};
  }

  render() {
    const {extractor, inputType, show10k} = this.state;
    const {jmhList} = this.props;

    return (
      <div>
        <h3>Introduction</h3>

        <p>
          The legend for tests. &laquo;Line Length&raquo; is amount of Unicode-symbols (do not confuse with char),{' '}
          &laquo;Characters&raquo; is typo of characters in string: ASCII (single byte for UTF-8), single chars only{' '}
          (all symbols represented as a single char) and all chars.
        </p>

        <p>
          Please notice, that unlike the article, here the performance tests are performed
          via <a href="http://openjdk.java.net/projects/code-tools/jmh/">JMH</a>.
          The configuration of a hardware is Intel® Core™ i7–5600U CPU @ 2.60GHz × 4 (2 core + 2 HT) with 16 GB RAM.
        </p>

        <Changelog runs={runs} onChange={this.handleRunChange}/>
        <TimeUnits onChange={extractor => this.setState({extractor})}/>
        <CharacterTypes onChange={inputType => this.setState({inputType})}/>
        <Choose label="Show 10k" items={[
          {
            label: 'Yes',
            value: true
          },
          {
            label: 'No',
            value: false,
            default: true
          },
        ]} onChange={show10k => this.setState({show10k})}/>

        <h3>Chart</h3>

        <ChartAndTable
          chartType="Bar"
          dataTable={jmhList}
          extractor={extractor}
          filter={d => d.inputType === inputType && (d.lineLength !== '10000' || show10k)}
          title="time, nanos"
          xDesc={xDesc}
          yDesc={yDesc}
        />

        <p>
          Full JMH log is <a target="_blank" href={`/stuff/data/read-utf8/jmh_${currentRunDate}.log.txt`}>here</a>.
        </p>

      </div>
    );
  }

  handleRunChange = date => {
    currentRunDate = date;
    this.props.refetch();
  };
}

function exportDimensions(benchmark, params) {
  //'com.komanov.readlines.jmh.ReadUtf8Benchmark.changeIndexInsideLoopByteMagicJava'

  const [, method, ...otherBenchmark] = benchmark.split('ReadUtf8Benchmark.');
  if (!method || otherBenchmark.length > 0) {
    throw new Error('Expected 2 parts in a benchmark: ' + benchmark);
  }

  return {
    method,
    inputType: params.inputType,
    lineLength: params.lineLength,
  };
}

const ReadUtf8 = JmhChartPage(
  ReadLinesImpl,
  {
    fetchFunc: () => loadJson(`/data/read-utf8/jmh_${currentRunDate}.json`),
    exportDimensionsFunc: exportDimensions,
    headerText: 'Exploring UTF-8 decoding performance',
  }
);

export default ReadUtf8;
