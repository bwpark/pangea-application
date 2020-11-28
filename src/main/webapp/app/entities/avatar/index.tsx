import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Avatar from './avatar';
import AvatarDetail from './avatar-detail';
import AvatarUpdate from './avatar-update';
import AvatarDeleteDialog from './avatar-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={AvatarUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={AvatarUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={AvatarDetail} />
      <ErrorBoundaryRoute path={match.url} component={Avatar} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={AvatarDeleteDialog} />
  </>
);

export default Routes;
