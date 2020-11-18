import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Regular from './regular';
import RegularDetail from './regular-detail';
import RegularUpdate from './regular-update';
import RegularDeleteDialog from './regular-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={RegularUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={RegularUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={RegularDetail} />
      <ErrorBoundaryRoute path={match.url} component={Regular} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={RegularDeleteDialog} />
  </>
);

export default Routes;
