import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Chemistry from './chemistry';
import ChemistryDetail from './chemistry-detail';
import ChemistryUpdate from './chemistry-update';
import ChemistryDeleteDialog from './chemistry-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ChemistryUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ChemistryUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ChemistryDetail} />
      <ErrorBoundaryRoute path={match.url} component={Chemistry} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ChemistryDeleteDialog} />
  </>
);

export default Routes;
