import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Repute from './repute';
import ReputeDetail from './repute-detail';
import ReputeUpdate from './repute-update';
import ReputeDeleteDialog from './repute-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ReputeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ReputeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ReputeDetail} />
      <ErrorBoundaryRoute path={match.url} component={Repute} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ReputeDeleteDialog} />
  </>
);

export default Routes;
