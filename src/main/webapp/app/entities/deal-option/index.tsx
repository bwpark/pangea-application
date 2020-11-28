import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import DealOption from './deal-option';
import DealOptionDetail from './deal-option-detail';
import DealOptionUpdate from './deal-option-update';
import DealOptionDeleteDialog from './deal-option-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DealOptionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DealOptionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DealOptionDetail} />
      <ErrorBoundaryRoute path={match.url} component={DealOption} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={DealOptionDeleteDialog} />
  </>
);

export default Routes;
