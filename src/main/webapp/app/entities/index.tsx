import React from 'react';
import { Switch } from 'react-router-dom';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import TOS from './tos';
import Category from './category';
import Avatar from './avatar';
import Issue from './issue';
import IssueOption from './issue-option';
import IssueResource from './issue-resource';
import Interact from './interact';
import Emotion from './emotion';
import Repute from './repute';
import Regular from './regular';
import Report from './report';
import Chemistry from './chemistry';
import Pack from './pack';
import Deal from './deal';
import DealOption from './deal-option';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}tos`} component={TOS} />
      <ErrorBoundaryRoute path={`${match.url}category`} component={Category} />
      <ErrorBoundaryRoute path={`${match.url}avatar`} component={Avatar} />
      <ErrorBoundaryRoute path={`${match.url}issue`} component={Issue} />
      <ErrorBoundaryRoute path={`${match.url}issue-option`} component={IssueOption} />
      <ErrorBoundaryRoute path={`${match.url}issue-resource`} component={IssueResource} />
      <ErrorBoundaryRoute path={`${match.url}interact`} component={Interact} />
      <ErrorBoundaryRoute path={`${match.url}emotion`} component={Emotion} />
      <ErrorBoundaryRoute path={`${match.url}repute`} component={Repute} />
      <ErrorBoundaryRoute path={`${match.url}regular`} component={Regular} />
      <ErrorBoundaryRoute path={`${match.url}report`} component={Report} />
      <ErrorBoundaryRoute path={`${match.url}chemistry`} component={Chemistry} />
      <ErrorBoundaryRoute path={`${match.url}pack`} component={Pack} />
      <ErrorBoundaryRoute path={`${match.url}deal`} component={Deal} />
      <ErrorBoundaryRoute path={`${match.url}deal-option`} component={DealOption} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;
