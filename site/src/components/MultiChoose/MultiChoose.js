import React from 'react';
import PropTypes from 'prop-types';
import {Button, Label} from 'semantic-ui-react';
import './MultiChoose.css';

export default class MultiChoose extends React.Component {
  static propTypes = {
    label: PropTypes.any,
    items: PropTypes.arrayOf(PropTypes.shape({
      label: PropTypes.string.isRequired,
      value: PropTypes.any.isRequired,
      default: PropTypes.bool,
    })).isRequired,
    onChange: PropTypes.func
  };

  constructor(props) {
    super(props);

    const activeMap = {};
    props.items.forEach(i => activeMap[i.value] = i.default);
    this.state = {
      activeMap,
    };
  }

  render() {
    const {label, items} = this.props;
    const {activeMap} = this.state;

    const buttons = items.map((item, index) =>
      <Button
        active={activeMap[item.value] === true}
        key={index}
        onClick={event => this.handleOnClick(event, item, index)}
      >
        {item.label}
      </Button>
    );

    return (
      <div className="stuff-multi-choose">
        {label && (typeof label === 'string' ? <Label size="medium">{label}</Label> : label)}
        <Button.Group size="tiny">{buttons}</Button.Group>
      </div>
    );
  }

  componentDidMount() {
    const {onChange} = this.props;
    if (onChange) {
      onChange(this.state.activeMap);
    }
  }

  handleOnClick = (event, item) => {
    event.preventDefault();

    const {activeMap} = this.state;
    const {onChange} = this.props;

    const newActiveMap = Object.assign({}, activeMap);
    newActiveMap[item.value] = !activeMap[item.value];

    if (onChange) {
      onChange(newActiveMap);
    }

    this.setState({
      activeMap: newActiveMap,
    });
  };
}
