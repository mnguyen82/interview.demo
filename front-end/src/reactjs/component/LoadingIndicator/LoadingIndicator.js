import React from "react";
import "./LoadingIndicator.css";

/**
 * Component used for displaying a loading animator while waiting for fetching data
 */
class LoadingIndicator extends React.Component {
  constructor(props){
    super(props);
  }
  render() {
    return(
      <div id="loading-indicator-container" className={this.props.loadingCss}>
        <div className = "loader"></div>
      </div>
    )
  }
}
export default LoadingIndicator;