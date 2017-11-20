import React from 'react';
import {Message} from 'semantic-ui-react';
import {loadJson} from '../../util';
import {ChartAndTable, TimeUnits} from '../../components';
import {JmhChartPage} from '..';

const xDesc = {
  title: 'Converter',
  prop: 'library',
  values: ['Json', 'ScalaPb', 'JavaPb', 'JavaThrift', 'Scrooge', 'BooPickle', 'Chill', 'Pickling', 'JavaSerialization'],
  valuesForMax: ['ScalaPb', 'JavaPb', 'JavaThrift', 'Scrooge', 'BooPickle'],
};

const yDesc = {
  title: 'data size',
  prop: 'dataSize',
  values: ['1k', '2k', '4k', '8k'],
};

function findMax(data) {
  let max = 0;
  data.forEach(v => {
    if (xDesc.valuesForMax.indexOf(v[xDesc.prop]) !== -1 && yDesc.values.indexOf(v[yDesc.prop]) !== -1) {
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
          filter={d => d.dataType === 'site' && d.action === 'both'}
          title="Two-way times for Site (DTO), nanos"
          xDesc={xDesc}
          yDesc={yDesc}
          findMaxFunc={findMax}
        />

        <h3 id="events-both">Events Two-Way</h3>

        <ChartAndTable
          dataTable={jmhList}
          extractor={extractor}
          filter={d => d.dataType === 'events' && d.action === 'both'}
          title="Two-way times for Events, nanos"
          xDesc={xDesc}
          yDesc={yDesc}
          findMaxFunc={findMax}
        />

        <h3 id="site-serialization">Site Serialization</h3>

        <ChartAndTable
          dataTable={jmhList}
          extractor={extractor}
          filter={d => d.dataType === 'site' && d.action === 'serialization'}
          title="Serialization times for Site (DTO), nanos"
          xDesc={xDesc}
          yDesc={yDesc}
          findMaxFunc={findMax}
        />

        <h3 id="site-deserialization">Site Deserialization</h3>

        <ChartAndTable
          dataTable={jmhList}
          extractor={extractor}
          filter={d => d.dataType === 'site' && d.action === 'deserialization'}
          title="Deserialization times for Site (DTO), nanos"
          xDesc={xDesc}
          yDesc={yDesc}
          findMaxFunc={findMax}
        />

        <h3 id="events-serialization">Events Serialization</h3>

        <ChartAndTable
          dataTable={jmhList}
          extractor={extractor}
          filter={d => d.dataType === 'events' && d.action === 'serialization'}
          title="Serialization times for Events, nanos"
          xDesc={xDesc}
          yDesc={yDesc}
          findMaxFunc={findMax}
        />

        <h3 id="events-deserialization">Events Deserialization</h3>

        <Message warning content="In this benchmark Chill failed with &quot;Buffer underflow&quot; exception."/>

        <ChartAndTable
          dataTable={jmhList}
          extractor={extractor}
          filter={d => d.dataType === 'events' && d.action === 'deserialization'}
          title="Deserialization times for Events, nanos"
          xDesc={xDesc}
          yDesc={yDesc}
          findMaxFunc={findMax}
        />

      </div>
    );
  }
}

function exportDimensions(benchmark) {
  //'com.komanov.serialization.jmh.ScalaPbBenchmark.deserialization_events_1k'

  const [namePart, typeParts] = benchmark.split('Benchmark.');
  if (!typeParts) {
    throw new Error('Expected 2 parts in a benchmark: ' + benchmark);
  }

  const [action, dataType, dataSize, ...other] = typeParts.split('_');
  if (!action || !dataType || !dataSize || (other && other.length > 0)) {
    throw new Error('Expected 3 type parts in a benchmark: ' + typeParts);
  }

  return {
    library: namePart.substring(namePart.lastIndexOf('.') + 1),
    action,
    dataType,
    dataSize
  };
}

const ScalaSerialization = JmhChartPage(
  ScalaSerializationImpl,
  {
    fetchFunc: () => loadJson('/data/scala-serialization/jmh-result.json'),
    exportDimensionsFunc: exportDimensions,
    headerText: 'Scala Serialization',
  }
);

export default ScalaSerialization;
