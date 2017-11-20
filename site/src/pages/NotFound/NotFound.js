import React from 'react';
import PropTypes from 'prop-types';

class NotFound extends React.Component {
  render() {
    return (
      <div>
        <h1>Not Found</h1>
        <p>
          Page not found: {this.props.location.pathname}.
        </p>
      </div>
    );
  }
}

NotFound.propTypes = {
  location: PropTypes.object.isRequired
};

export default NotFound;
