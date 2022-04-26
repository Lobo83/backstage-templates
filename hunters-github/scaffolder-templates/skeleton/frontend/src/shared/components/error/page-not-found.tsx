import React from 'react';
import { Alert } from 'reactstrap';

class PageNotFound extends React.Component {
  render() {
    return (
      <div>
        <Alert color="danger">
          <h1>Error 404: The page does not exist.</h1>
        </Alert>
      </div>
    );
  }
}

export default PageNotFound;
