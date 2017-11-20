import React from 'react';
import {Link} from 'react-router-dom';
import {List} from 'semantic-ui-react';

class Home extends React.Component {
  render() {
    return (
      <div>
        <h1>Stuff</h1>

        <h2>Introduction</h2>

        <p>
          Hi! My name is Dmitry, and I am a software developer :)
        </p>

        <p>
          Here you may find some of my researches on different aspects of computer engineering. At this moment I mostly
          program on Scala, previously I programmed a lot on C++, Java and C# (and started with PHP, yes).
        </p>

        <h2>Researches</h2>

        <p>
          My researches in reverse order of appearance:
        </p>

        <List bulleted>
          <List.Item>
            <Link to="/scala-string-format">Scala: String Interpolation Performance</Link>.
          </List.Item>
          <List.Item>
            <Link to="/mysql-streaming">The Cost of Streaming Data from MySQL</Link>.
          </List.Item>
          <List.Item>
            <Link to="/scala-serialization">Scala Serialization</Link> (ScalaPB, Scrooge, Jackson and many more).
          </List.Item>
        </List>
      </div>
    );
  }
}

export default Home;
