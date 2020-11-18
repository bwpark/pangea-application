import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Category2Issue from './category-2-issue';
import Category2IssueDetail from './category-2-issue-detail';
import Category2IssueUpdate from './category-2-issue-update';
import Category2IssueDeleteDialog from './category-2-issue-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={Category2IssueUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={Category2IssueUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={Category2IssueDetail} />
      <ErrorBoundaryRoute path={match.url} component={Category2Issue} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={Category2IssueDeleteDialog} />
  </>
);

export default Routes;
