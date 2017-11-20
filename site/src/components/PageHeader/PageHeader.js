import React from 'react';
import {Link, NavLink} from 'react-router-dom';
import {Icon, Menu} from 'semantic-ui-react';

class MenuNavLink extends React.Component {
  render() {
    const {to, children} = this.props;
    return (
      <NavLink to={to} className="item" activeClassName="active">{children}</NavLink>
    )
  }
}

class PageHeader extends React.Component {
  render() {
    return (
      <Menu className="stuff-header">
        <Link to="/" className="header item">Stuff</Link>
        <MenuNavLink to="/scala-serialization">Scala Serialization</MenuNavLink>
        <MenuNavLink to="/mysql-streaming">MySQL Streaming</MenuNavLink>
        <MenuNavLink to="/scala-string-format">Scala String Interpolation</MenuNavLink>
        <div className="right menu">
          <a href="https://github.com/dkomanov/stuff" className="item">
            <Icon name="github" size="big"/>
            GitHub
          </a>
        </div>
      </Menu>
    );
  }
}

export default PageHeader;
