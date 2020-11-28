import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import TOS from './tos';
import TOSDetail from './tos-detail';
import TOSUpdate from './tos-update';
import TOSDeleteDialog from './tos-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TOSUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TOSUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TOSDetail} />
      <ErrorBoundaryRoute path={match.url} component={TOS} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={TOSDeleteDialog} />
  </>
);

export default Routes;
