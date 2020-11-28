import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Category2avatar from './category-2-avatar';
import Category2avatarDetail from './category-2-avatar-detail';
import Category2avatarUpdate from './category-2-avatar-update';
import Category2avatarDeleteDialog from './category-2-avatar-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={Category2avatarUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={Category2avatarUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={Category2avatarDetail} />
      <ErrorBoundaryRoute path={match.url} component={Category2avatar} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={Category2avatarDeleteDialog} />
  </>
);

export default Routes;
