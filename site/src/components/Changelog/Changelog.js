import React from 'react';
import {List} from 'semantic-ui-react';
import PropTypes from 'prop-types';

class Changelog extends React.Component {
  render() {
    const children = this.props.runs.map(({date, comment}) =>
      <List.Item key={date}>
        <List.Content>
          <List.Header content={<a onClick={event => this.handleOnClick(event, date)}>{date}</a>}/>
          <List.Description content={comment}/>
        </List.Content>
      </List.Item>);
    return (
      <div>
        <h3>Changelog</h3>
        <List divided relaxed>
          {children}
        </List>
      </div>
    );
  }

  handleOnClick = (event, date) => {
    event.preventDefault();

    const {onChange} = this.props;
    if (onChange) {
      onChange(date);
    }
  };
}

Changelog.propTypes = {
  runs: PropTypes.arrayOf(PropTypes.shape({
    date: PropTypes.string.isRequired,
    comment: PropTypes.oneOfType([PropTypes.string, PropTypes.element]).isRequired,
  })).isRequired,
  onChange: PropTypes.func,
};

export default Changelog;
