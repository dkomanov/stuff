import React from 'react';
import {Chart} from 'react-google-charts';

function getConvertOptionsFunc(chartType) {
  return window.google && window.google.charts && window.google.charts[chartType]
    ? window.google.charts[chartType].convertOptions
    : null;
}

class GoogleChart extends React.Component {
  constructor(props) {
    super(props);

    const self = this;

    this.state = {
      convertFunc: getConvertOptionsFunc(self.props.chartType)
    };
    this.chartEvents = [
      {
        eventName: 'ready',
        callback(Chart) {
          const convertFunc = getConvertOptionsFunc(self.props.chartType) || (t => t);
          self.setState({convertFunc});
        },
      },
    ]
  }

  render() {
    const {chartType, options, ...other} = this.props;
    const {convertFunc} = this.state;
    const finalOptions = convertFunc ? convertFunc(options) : options;

    return (
      <Chart chartType={chartType} options={finalOptions} chartEvents={convertFunc ? [] : this.chartEvents} {...other}/>
    );
  }
}

export default GoogleChart;
