import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IAvatar } from 'app/shared/model/avatar.model';
import { getEntities as getAvatars } from 'app/entities/avatar/avatar.reducer';
import { IIssue } from 'app/shared/model/issue.model';
import { getEntities as getIssues } from 'app/entities/issue/issue.reducer';
import { getEntity, updateEntity, createEntity, reset } from './emotion.reducer';
import { IEmotion } from 'app/shared/model/emotion.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IEmotionUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const EmotionUpdate = (props: IEmotionUpdateProps) => {
  const [youId, setYouId] = useState('0');
  const [meId, setMeId] = useState('0');
  const [issueId, setIssueId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { emotionEntity, avatars, issues, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/emotion');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getAvatars();
    props.getIssues();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    values.created = convertDateTimeToServer(values.created);
    values.modified = convertDateTimeToServer(values.modified);

    if (errors.length === 0) {
      const entity = {
        ...emotionEntity,
        ...values,
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="pangeaApplicationApp.emotion.home.createOrEditLabel">
            <Translate contentKey="pangeaApplicationApp.emotion.home.createOrEditLabel">Create or edit a Emotion</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : emotionEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="emotion-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="emotion-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="statusLabel" for="emotion-status">
                  <Translate contentKey="pangeaApplicationApp.emotion.status">Status</Translate>
                </Label>
                <AvInput
                  id="emotion-status"
                  type="select"
                  className="form-control"
                  name="status"
                  value={(!isNew && emotionEntity.status) || 'RESPECT'}
                >
                  <option value="RESPECT">{translate('pangeaApplicationApp.EmotionStatus.RESPECT')}</option>
                  <option value="DISS">{translate('pangeaApplicationApp.EmotionStatus.DISS')}</option>
                  <option value="NONE">{translate('pangeaApplicationApp.EmotionStatus.NONE')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="createdLabel" for="emotion-created">
                  <Translate contentKey="pangeaApplicationApp.emotion.created">Created</Translate>
                </Label>
                <AvInput
                  id="emotion-created"
                  type="datetime-local"
                  className="form-control"
                  name="created"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.emotionEntity.created)}
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="modifiedLabel" for="emotion-modified">
                  <Translate contentKey="pangeaApplicationApp.emotion.modified">Modified</Translate>
                </Label>
                <AvInput
                  id="emotion-modified"
                  type="datetime-local"
                  className="form-control"
                  name="modified"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.emotionEntity.modified)}
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label for="emotion-you">
                  <Translate contentKey="pangeaApplicationApp.emotion.you">You</Translate>
                </Label>
                <AvInput id="emotion-you" type="select" className="form-control" name="youId">
                  <option value="" key="0" />
                  {avatars
                    ? avatars.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="emotion-issue">
                  <Translate contentKey="pangeaApplicationApp.emotion.issue">Issue</Translate>
                </Label>
                <AvInput id="emotion-issue" type="select" className="form-control" name="issueId">
                  <option value="" key="0" />
                  {issues
                    ? issues.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="emotion-me">
                  <Translate contentKey="pangeaApplicationApp.emotion.me">Me</Translate>
                </Label>
                <AvInput id="emotion-me" type="select" className="form-control" name="meId">
                  <option value="" key="0" />
                  {avatars
                    ? avatars.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/emotion" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  avatars: storeState.avatar.entities,
  issues: storeState.issue.entities,
  emotionEntity: storeState.emotion.entity,
  loading: storeState.emotion.loading,
  updating: storeState.emotion.updating,
  updateSuccess: storeState.emotion.updateSuccess,
});

const mapDispatchToProps = {
  getAvatars,
  getIssues,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(EmotionUpdate);
