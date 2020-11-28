import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import IssueOption from './issue-option';
import IssueOptionDetail from './issue-option-detail';
import IssueOptionUpdate from './issue-option-update';
import IssueOptionDeleteDialog from './issue-option-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={IssueOptionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={IssueOptionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={IssueOptionDetail} />
      <ErrorBoundaryRoute path={match.url} component={IssueOption} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={IssueOptionDeleteDialog} />
  </>
);

export default Routes;
