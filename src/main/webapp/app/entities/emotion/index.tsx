import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Emotion from './emotion';
import EmotionDetail from './emotion-detail';
import EmotionUpdate from './emotion-update';
import EmotionDeleteDialog from './emotion-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={EmotionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={EmotionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={EmotionDetail} />
      <ErrorBoundaryRoute path={match.url} component={Emotion} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={EmotionDeleteDialog} />
  </>
);

export default Routes;
