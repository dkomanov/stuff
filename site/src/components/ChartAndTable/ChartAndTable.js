import React from 'react';
import PropTypes from 'prop-types';
import {buildData} from '../../util';
import {GoogleChart} from '..';
import {Button, Loader, Table} from 'semantic-ui-react';

class RawDataTable extends React.Component {
  render() {
    const {data: rawData} = this.props;
    const data = rawData[0].map((col, i) => rawData.map(row => row[i]));
    const [headerRow, ...dataRows] = data;

    const headers = headerRow.map((name, index) =>
      <Table.HeaderCell key={index} textAlign="right">{name}</Table.HeaderCell>);
    const lines = dataRows.map((line, lineIndex) => {
      const cells = line.map((value, index) =>
        <Table.Cell key={index} textAlign="right">{Number.isNaN(value) ? '' : value}</Table.Cell>);
      return <Table.Row key={lineIndex}>{cells}</Table.Row>
    });

    return (
      <Table definition compact>
        <Table.Header>
          <Table.Row>
            {headers}
          </Table.Row>
        </Table.Header>
        <Table.Body>
          {lines}
        </Table.Body>
      </Table>
    )
  }
}

class ChartAndTable extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      hideTable: true
    };
  }

  render() {
    const {dataTable, filter, extractor, title, xDesc, yDesc, findMaxFunc, chartType, options} = this.props;

    if (!dataTable || !extractor) {
      return null;
    }

    const filteredData = filter ? dataTable.filter(filter) : dataTable;

    const data = buildData(filteredData, extractor, xDesc, yDesc);

    function setDefault(path, value) {
      let current = options;
      const pathParts = path.split('.');
      pathParts.forEach((name, index) => {
        if (index === pathParts.length - 1) {
          if (current[name] === undefined) {
            current[name] = typeof value === 'function' ? value() : value;
          }
        } else {
          if (current[name] === undefined) {
            current[name] = {};
          }
          current = current[name];
        }
      });
    }

    setDefault('chart.title', title);
    setDefault('vAxis.format', 'short');
    if (findMaxFunc) {
      setDefault('vAxis.viewWindow.max', () => findMaxFunc(filteredData));
    }

    return (
      <div>
        <GoogleChart
          chartType={chartType}
          data={data}
          width="100%"
          height="600px"
          options={options}
          loader={<Loader active={true} content="Rendering chart..."/>}
        />
        <div>
          <Button basic onClick={this.handleOnClick}>Toggle Raw Data</Button>
          {this.state.hideTable || <RawDataTable data={data}/>}
        </div>
      </div>
    )
  }

  handleOnClick = event => {
    event.preventDefault();
    this.setState({
      hideTable: !this.state.hideTable
    });
  };
}

ChartAndTable.propTypes = {
  chartType: PropTypes.string,
  dataTable: PropTypes.array,
  filter: PropTypes.func,
  extractor: PropTypes.func,
  title: PropTypes.string,
  xDesc: PropTypes.shape({
    title: PropTypes.string.isRequired,
    prop: PropTypes.string.isRequired,
    values: PropTypes.array.isRequired
  }).isRequired,
  yDesc: PropTypes.shape({
    title: PropTypes.string.isRequired,
    prop: PropTypes.string.isRequired,
    values: PropTypes.array.isRequired
  }).isRequired,
  findMaxFunc: PropTypes.func,
  options: PropTypes.object,
};

ChartAndTable.defaultProps = {
  chartType: 'Bar',
  options: {},
};

export default ChartAndTable;
