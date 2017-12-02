import React from 'react';
import {loadJson} from '../../util';
import {Changelog, ChartAndTable, TimeUnits} from '../../components';
import {JmhChartPage} from '..';

const runs = [
  {
    date: '2017-12-01',
    comment:
      <ul>
        <li>
          Introduced benchmark for <a href="https://github.com/circe/circe">Circe</a> library.
        </li>
        <li>
          Downgrade versions (because using bazel rules): scrooge&nbsp;(4.6.0).
        </li>
        <li>
          Upgrade versions of libraries: scala-library&nbsp;(2.11.8), jackson&nbsp;(2.9.1), protobuf&nbsp;(3.4.0),{' '}
          scalapb&nbsp;(0.6.5), boopickle&nbsp;(1.2.5), chill&nbsp;(0.9.2).
        </li>
        <li>Fixed issue with Chill by cloning input array (therefore it's time of Chill + array clone).</li>
      </ul>,
  },
  {
    date: '2016-06-26',
    comment:
      <ul>
        <li>Initial version of benchmark.</li>
        <li>
          Libraries: scala-library&nbsp;(2.11.7), jackson&nbsp;(2.7.3), protobuf&nbsp;(3.0.0-beta-2), scalapb&nbsp;(0.5.31),{' '}
          scala-pickling&nbsp;(0.11.0-M2), boopickle&nbsp;(1.2.4), chill&nbsp;(0.8.0), libthrift&nbsp;(0.9.1), scrooge&nbsp;(4.7.0).
        </li>
        <li>
          Chill failed with &quot;Buffer underflow&quot; exception on only-deserialization benchmarks,{' '}
          because of <a href="https://github.com/twitter/chill/issues/181">bug</a>.
        </li>
      </ul>,
  },
];
let currentRunDate = runs[0].date;

const xDesc = {
  title: 'Converter',
  prop: 'library',
  values: [
    {
      name: 'Json',
      value: 'JSON',
    },
    {
      name: 'ScalaPb',
      value: 'SCALA_PB',
    },
    {
      name: 'JavaPb',
      value: 'JAVA_PB',
    },
    {
      name: 'JavaThrift',
      value: 'JAVA_THRIFT',
    },
    {
      name: 'Scrooge',
      value: 'SCROOGE',
    },
    {
      name: 'BooPickle',
      value: 'BOOPICKLE',
    },
    {
      name: 'Chill',
      value: 'CHILL',
    },
    {
      name: 'Scrooge',
      value: 'SCROOGE',
    },
    {
      name: 'Circe',
      value: 'CIRCE',
    },
    {
      name: 'Pickling',
      value: 'PICKLING',
    },
    {
      name: 'JavaSerialization',
      value: 'SERIALIZABLE',
    },
  ],
  valuesForMax: ['SCALA_PB', 'JAVA_PB', 'JAVA_THRIFT', 'SCROOGE', 'BOOPICKLE'],
};

const yDesc = {
  title: 'data size',
  prop: 'dataSize',
  values: [
    {
      name: '1k',
      value: '_1_K',
    },
    {
      name: '2k',
      value: '_2_K',
    },
    {
      name: '4k',
      value: '_4_K',
    },
    {
      name: '8k',
      value: '_8_K',
    },
  ],
};

function yDescContainValue(v) {
  return !!yDesc.values.find(({value}) => value === v);
}

function findMax(data) {
  let max = 0;

  data.forEach(v => {
    if (xDesc.valuesForMax.indexOf(v[xDesc.prop]) !== -1 && yDescContainValue(v[yDesc.prop])) {
      const value = v.pm.scorePercentiles['100.0'];
      max = Math.max(value, max);
    }
  });
  const onePercentOfMax = max / 100;
  return max + onePercentOfMax;
}

class ScalaSerializationImpl extends React.Component {
  render() {
    const {extractor} = this.state || {};
    const {jmhList} = this.props;

    return (
      <div>
        <h3>Introduction</h3>

        <p>
          Here are present actual charts for performance comparison of serialization libraries for Scala for the corresponding
          article <a href="https://medium.com/@dkomanov/scala-serialization-419d175c888a">&laquo;Scala
          Serialization&raquo;</a>.
        </p>

        <p>
          The legend for tests. &laquo;Site&raquo; is a data transfer object (DTO) with different types of data
          (lists, enums, regular fields). &laquo;Events&raquo; are the primitive representation of this DTO for
          Event Sourcing (many small objects). Data sizes (1k, 2k etc.) are the corresponding size of the DTO in JSON format.
        </p>

        <p>
          Please notice, that unlike the article, here the performance tests are performed
          via <a href="http://openjdk.java.net/projects/code-tools/jmh/">JMH</a> and using 2 threads.
          The configuration of a hardware is Intel® Core™ i7–5600U CPU @ 2.60GHz × 4 (2 core + 2 HT) with 16 GB RAM.
        </p>

        <Changelog runs={runs} onChange={this.handleRunChange}/>

        <h3>Charts</h3>

        <ul>
          <li><a href="#site-both">Site Two-Way</a></li>
          <li><a href="#events-both">Events Two-Way</a></li>
          <li><a href="#site-serialization">Site Serialization</a></li>
          <li><a href="#site-deserialization">Site Deserialization</a></li>
          <li><a href="#events-serialization">Events Serialization</a></li>
          <li><a href="#events-deserialization">Events Deserialization</a></li>
        </ul>

        <TimeUnits onChange={extractor => this.setState({extractor})}/>

        <h3 id="site-both">Site Two-Way</h3>

        <ChartAndTable
          dataTable={jmhList}
          extractor={extractor}
          filter={d => d.dataType === 'Site' && d.action === 'both'}
          title="Two-way times for Site (DTO), nanos"
          xDesc={xDesc}
          yDesc={yDesc}
          findMaxFunc={findMax}
        />

        <h3 id="events-both">Events Two-Way</h3>

        <ChartAndTable
          dataTable={jmhList}
          extractor={extractor}
          filter={d => d.dataType === 'Event' && d.action === 'both'}
          title="Two-way times for Events, nanos"
          xDesc={xDesc}
          yDesc={yDesc}
          findMaxFunc={findMax}
        />

        <h3 id="site-serialization">Site Serialization</h3>

        <ChartAndTable
          dataTable={jmhList}
          extractor={extractor}
          filter={d => d.dataType === 'Site' && d.action === 'serialization'}
          title="Serialization times for Site (DTO), nanos"
          xDesc={xDesc}
          yDesc={yDesc}
          findMaxFunc={findMax}
        />

        <h3 id="site-deserialization">Site Deserialization</h3>

        <ChartAndTable
          dataTable={jmhList}
          extractor={extractor}
          filter={d => d.dataType === 'Site' && d.action === 'deserialization'}
          title="Deserialization times for Site (DTO), nanos"
          xDesc={xDesc}
          yDesc={yDesc}
          findMaxFunc={findMax}
        />

        <h3 id="events-serialization">Events Serialization</h3>

        <ChartAndTable
          dataTable={jmhList}
          extractor={extractor}
          filter={d => d.dataType === 'Event' && d.action === 'serialization'}
          title="Serialization times for Events, nanos"
          xDesc={xDesc}
          yDesc={yDesc}
          findMaxFunc={findMax}
        />

        <h3 id="events-deserialization">Events Deserialization</h3>

        <ChartAndTable
          dataTable={jmhList}
          extractor={extractor}
          filter={d => d.dataType === 'Event' && d.action === 'deserialization'}
          title="Deserialization times for Events, nanos"
          xDesc={xDesc}
          yDesc={yDesc}
          findMaxFunc={findMax}
        />

        <p>
          Full JMH log is <a target="_blank" href={`/stuff/data/scala-serialization/jmh_${currentRunDate}.log.txt`}>here</a>.
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
  //'com.komanov.serialization.jmh.EventBenchmark.both'

  const [dataTypePart, action, ...otherBenchmark] = benchmark.split('Benchmark.');
  if (!action || otherBenchmark.length > 0) {
    throw new Error('Expected 2 parts in a benchmark: ' + benchmark);
  }

  const dataType = dataTypePart.substring(dataTypePart.lastIndexOf('.') + 1);

  return {
    library: params.converterType,
    action,
    dataType,
    dataSize: params.inputType,
  };
}

const ScalaSerialization = JmhChartPage(
  ScalaSerializationImpl,
  {
    fetchFunc: () => loadJson(`/data/scala-serialization/jmh_${currentRunDate}.json`),
    exportDimensionsFunc: exportDimensions,
    headerText: 'Scala Serialization',
  }
);

export default ScalaSerialization;
