import React from 'react';
import {Container, Loader, Message} from 'semantic-ui-react';
import {AjaxHelper, exportDataTable} from '../../util';

/**
 * @param WrappedComponent Object extends React.Component
 * @param config {{fetchFunc: function(), exportDimensionsFunc: function(benchmark: string, params: Object), [headerText]: string, [loadingText]: string}}
 * @returns Object extends React.Component
 */
export default function JmhChartPage(WrappedComponent, config) {
  if (!config.fetchFunc) {
    throw new Error('fetchFunc is not defined');
  }
  if (!config.exportDimensionsFunc) {
    throw new Error('exportDimensionsFunc is not defined');
  }

  return class extends React.Component {
    constructor(props) {
      super(props);

      this.ajaxHelper = new AjaxHelper(
        this,
        config.fetchFunc.bind(this),
        response => {
          this.setState({
            jmhList: exportDataTable(response.data, config.exportDimensionsFunc.bind(this)),
            warning: null
          });
        },
        reason => {
          console.log(reason);
          this.setState({
            warning: reason
          });
        }
      );

      this.state = {
        warning: null,
        jmhList: null
      };
    }

    render() {
      const {headerText, loadingText} = config;
      const {warning, jmhList} = this.state;

      return (
        <Container fluid>
          {headerText && <h2 className="ui header">{headerText}</h2>}
          {warning && <Message warning content={<pre>{JSON.stringify(warning, null, 2)}</pre>}/>}
          {loadingText &&
          <Loader indeterminate active={this.ajaxHelper.isLoading()} size="massive" content={loadingText}/>}
          <WrappedComponent
            jmhList={jmhList}
            refetch={this.fetchData}
            {...this.props}
          />
        </Container>
      );
    }

    componentDidMount() {
      this.fetchData();
    }

    componentWillUnmount() {
      this.ajaxHelper.cancel();
    }

    fetchData = () => {
      this.ajaxHelper.doAction();
    }
  };
}
