import React from 'react';
import {loadJson} from '../../util';
import {ChartAndTable, TimeUnits} from '../../components';
import {JmhChartPage} from '..';

const xDesc = {
  title: 'Method',
  prop: 'name',
  values: ['javaConcat', 'stringFormat', 'messageFormat', 'scalaConcat', 'concatOptimized1', 'concatOptimized2', 'concatOptimizedMacros', 'slf4j', 'sInterpolator', 'fInterpolator', 'rawInterpolator', 'sfiInterpolator'],
};

const yDesc = {
  title: 'string length',
  prop: 'dataSize',
  values: [
    {
      name: "tiny (7)",
      value: "Tiny"
    },
    {
      name: "very short (17)",
      value: "VeryShort"
    },
    {
      name: "short (29)",
      value: "Short"
    },
    {
      name: "medium (75)",
      value: "Medium"
    },
    {
      name: "long (212)",
      value: "Long"
    },
    {
      name: "very long (1004)",
      value: "VeryLong"
    },
    {
      name: "very long size miss (1006)",
      value: "VeryLongSizeMiss"
    }
  ],
};

class ScalaStringFormatImpl extends React.Component {
  render() {
    const {extractor} = this.state || {};
    const {jmhList} = this.props;

    return (
      <div>
        <h3>Introduction</h3>

        <p>
          Here are present actual charts for performance comparison of string formatting in Java/Scala for the corresponding
          post <a href="https://medium.com/@dkomanov/scala-string-interpolation-performance-21dc85e83afd">&laquo;Scala: String Interpolation Performance&raquo;</a>.
        </p>

        <p>
          The legend for tests. &laquo;String length&raquo; is a length of a result string (after formatting).
        </p>

        <p>
          Tests performed via <a href="http://openjdk.java.net/projects/code-tools/jmh/">JMH</a>, 2 forks, 3 warmup
          runs and 7 iteration (3 seconds each). Ubuntu 16.04, linux-kernel 4.4.0-51-generic, JDK 1.8.0_91, scala library 2.12.
          The configuration of a hardware is Intel® Core™ i7–5600U CPU @ 2.60GHz × 4 (2 core + 2 HT) with 16 GB RAM.
        </p>

        <h3>Chart</h3>

        <TimeUnits onChange={extractor => this.setState({extractor})}/>

        <ChartAndTable
          dataTable={jmhList}
          extractor={extractor}
          filter={d => !!d.dataSize}
          title="String formatting times, nanos"
          xDesc={xDesc}
          yDesc={yDesc}
        />

        <p>
          Full JMH log is <a href="/stuff/data/scala-string-format/jmh.log">here</a>.
        </p>
      </div>
    );
  }
}

function exportDimensions(benchmark, params) {
  //'com.komanov.stringformat.jmh.ManyParamsBenchmark.concat'

  const index = benchmark.lastIndexOf('.');
  if (index === -1) {
    throw new Error('Expected a dot in a benchmark: ' + benchmark);
  }

  return {
    name: benchmark.substring(index + 1),
    dataSize: params && params.arg
  };
}

const ScalaStringFormat = JmhChartPage(
  ScalaStringFormatImpl,
  {
    fetchFunc: () => loadJson('/data/scala-string-format/jmh-result.json'),
    exportDimensionsFunc: exportDimensions,
    headerText: 'Scala: String Interpolation Performance',
  }
);

export default ScalaStringFormat;
