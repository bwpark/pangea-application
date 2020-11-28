import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Interact from './interact';
import InteractDetail from './interact-detail';
import InteractUpdate from './interact-update';
import InteractDeleteDialog from './interact-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={InteractUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={InteractUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={InteractDetail} />
      <ErrorBoundaryRoute path={match.url} component={Interact} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={InteractDeleteDialog} />
  </>
);

export default Routes;
