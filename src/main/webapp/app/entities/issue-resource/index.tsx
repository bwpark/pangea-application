import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import IssueResource from './issue-resource';
import IssueResourceDetail from './issue-resource-detail';
import IssueResourceUpdate from './issue-resource-update';
import IssueResourceDeleteDialog from './issue-resource-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={IssueResourceUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={IssueResourceUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={IssueResourceDetail} />
      <ErrorBoundaryRoute path={match.url} component={IssueResource} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={IssueResourceDeleteDialog} />
  </>
);

export default Routes;
