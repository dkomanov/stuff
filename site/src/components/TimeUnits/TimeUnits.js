import React from 'react';
import PropTypes from 'prop-types';
import {Choose} from '..';

function getByPercentile(spKey) {
  return pm => pm.scorePercentiles[spKey];
}

class TimeUnits extends React.Component {
  render() {
    return (
      <div>
        <Choose label="Time Units: " onChange={this.handleOnClick} items={[
          {
            label: 'avg',
            value: pm => pm.score,
            default: true,
          },
          {
            label: 'p0',
            value: getByPercentile('0.0'),
          },
          {
            label: 'p50',
            value: getByPercentile('50.0'),
          },
          {
            label: 'p95',
            value: getByPercentile('95.0'),
          },
          {
            label: 'p99',
            value: getByPercentile('99.0'),
          },
          {
            label: 'p100',
            value: getByPercentile('100.0'),
          },
        ]}/>
        <p>
          <small>
            avg - average, p0 - percentile 0 (min), p50 - percentile 50 (median), p95 - percentile 95, p99 - percentile 99, p100 - percentile 100 (max)
          </small>
        </p>
      </div>
    );
  }

  handleOnClick = extractor => {
    const {onChange} = this.props;
    if (onChange) {
      onChange(extractor);
    }
  };
}

TimeUnits.propTypes = {
  onChange: PropTypes.func
};

export default TimeUnits;
