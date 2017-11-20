import React from 'react';
import PropTypes from 'prop-types';
import {Button, Label} from 'semantic-ui-react';
import './Choose.css';

class Choose extends React.Component {
  constructor(props) {
    super(props);

    const defaultIndex = props.items.findIndex(i => i.default === true);
    this.state = {
      activeIndex: defaultIndex >= 0 ? defaultIndex : 0
    };
  }

  render() {
    const {label, items} = this.props;
    const {activeIndex} = this.state;

    const buttons = items.map((item, index) =>
      <Button
        active={index === activeIndex}
        key={index}
        onClick={event => this.handleOnClick(event, item, index)}
      >
        {item.label}
      </Button>
    );

    return (
      <div className="stuff-choose">
        {label && (typeof label === 'string' ? <Label size="medium">{label}</Label> : label)}
        <Button.Group size="tiny">{buttons}</Button.Group>
      </div>
    );
  }

  componentDidMount() {
    const {activeIndex} = this.state;
    const {onChange} = this.props;
    if (onChange) {
      onChange(this.props.items[activeIndex].value);
    }
  }

  handleOnClick = (event, item, index) => {
    event.preventDefault();

    const {onChange} = this.props;
    if (onChange) {
      onChange(item.value);
    }

    this.setState({
      activeIndex: index
    });
  };
}

Choose.propTypes = {
  label: PropTypes.any,
  items: PropTypes.arrayOf(PropTypes.shape({
    label: PropTypes.string.isRequired,
    value: PropTypes.any.isRequired,
    default: PropTypes.bool,
  })).isRequired,
  onChange: PropTypes.func
};

export default Choose;
