import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, setFileData, byteSize, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntities as getInteracts } from 'app/entities/interact/interact.reducer';
import { IAvatar } from 'app/shared/model/avatar.model';
import { getEntities as getAvatars } from 'app/entities/avatar/avatar.reducer';
import { IIssue } from 'app/shared/model/issue.model';
import { getEntities as getIssues } from 'app/entities/issue/issue.reducer';
import { getEntity, updateEntity, createEntity, setBlob, reset } from './interact.reducer';
import { IInteract } from 'app/shared/model/interact.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IInteractUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const InteractUpdate = (props: IInteractUpdateProps) => {
  const [childId, setChildId] = useState('0');
  const [parentId, setParentId] = useState('0');
  const [youId, setYouId] = useState('0');
  const [meId, setMeId] = useState('0');
  const [issueId, setIssueId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { interactEntity, interacts, avatars, issues, loading, updating } = props;

  const { text } = interactEntity;

  const handleClose = () => {
    props.history.push('/interact');
  };

  useEffect(() => {
    if (!isNew) {
      props.getEntity(props.match.params.id);
    }

    props.getInteracts();
    props.getAvatars();
    props.getIssues();
  }, []);

  const onBlobChange = (isAnImage, name) => event => {
    setFileData(event, (contentType, data) => props.setBlob(name, data, contentType), isAnImage);
  };

  const clearBlob = name => () => {
    props.setBlob(name, undefined, undefined);
  };

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
        ...interactEntity,
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
          <h2 id="pangeaApplicationApp.interact.home.createOrEditLabel">
            <Translate contentKey="pangeaApplicationApp.interact.home.createOrEditLabel">Create or edit a Interact</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : interactEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="interact-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="interact-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="textLabel" for="interact-text">
                  <Translate contentKey="pangeaApplicationApp.interact.text">Text</Translate>
                </Label>
                <AvInput id="interact-text" type="textarea" name="text" />
              </AvGroup>
              <AvGroup>
                <Label id="coinLabel" for="interact-coin">
                  <Translate contentKey="pangeaApplicationApp.interact.coin">Coin</Translate>
                </Label>
                <AvField
                  id="interact-coin"
                  type="string"
                  className="form-control"
                  name="coin"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    number: { value: true, errorMessage: translate('entity.validation.number') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="pointLabel" for="interact-point">
                  <Translate contentKey="pangeaApplicationApp.interact.point">Point</Translate>
                </Label>
                <AvField
                  id="interact-point"
                  type="string"
                  className="form-control"
                  name="point"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    number: { value: true, errorMessage: translate('entity.validation.number') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="likeLabel" for="interact-like">
                  <Translate contentKey="pangeaApplicationApp.interact.like">Like</Translate>
                </Label>
                <AvField
                  id="interact-like"
                  type="string"
                  className="form-control"
                  name="like"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    number: { value: true, errorMessage: translate('entity.validation.number') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="dislikeLabel" for="interact-dislike">
                  <Translate contentKey="pangeaApplicationApp.interact.dislike">Dislike</Translate>
                </Label>
                <AvField
                  id="interact-dislike"
                  type="string"
                  className="form-control"
                  name="dislike"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    number: { value: true, errorMessage: translate('entity.validation.number') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="authorLabel" for="interact-author">
                  <Translate contentKey="pangeaApplicationApp.interact.author">Author</Translate>
                </Label>
                <AvField
                  id="interact-author"
                  type="text"
                  name="author"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    maxLength: { value: 128, errorMessage: translate('entity.validation.maxlength', { max: 128 }) },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="statusLabel" for="interact-status">
                  <Translate contentKey="pangeaApplicationApp.interact.status">Status</Translate>
                </Label>
                <AvInput
                  id="interact-status"
                  type="select"
                  className="form-control"
                  name="status"
                  value={(!isNew && interactEntity.status) || 'ORIGINATE'}
                >
                  <option value="ORIGINATE">{translate('pangeaApplicationApp.InteractStatus.ORIGINATE')}</option>
                  <option value="READED">{translate('pangeaApplicationApp.InteractStatus.READED')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="createdLabel" for="interact-created">
                  <Translate contentKey="pangeaApplicationApp.interact.created">Created</Translate>
                </Label>
                <AvInput
                  id="interact-created"
                  type="datetime-local"
                  className="form-control"
                  name="created"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.interactEntity.created)}
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="modifiedLabel" for="interact-modified">
                  <Translate contentKey="pangeaApplicationApp.interact.modified">Modified</Translate>
                </Label>
                <AvInput
                  id="interact-modified"
                  type="datetime-local"
                  className="form-control"
                  name="modified"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.interactEntity.modified)}
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label for="interact-you">
                  <Translate contentKey="pangeaApplicationApp.interact.you">You</Translate>
                </Label>
                <AvInput id="interact-you" type="select" className="form-control" name="youId">
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
                <Label for="interact-issue">
                  <Translate contentKey="pangeaApplicationApp.interact.issue">Issue</Translate>
                </Label>
                <AvInput id="interact-issue" type="select" className="form-control" name="issueId">
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
                <Label for="interact-parent">
                  <Translate contentKey="pangeaApplicationApp.interact.parent">Parent</Translate>
                </Label>
                <AvInput id="interact-parent" type="select" className="form-control" name="parentId">
                  <option value="" key="0" />
                  {interacts
                    ? interacts.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="interact-me">
                  <Translate contentKey="pangeaApplicationApp.interact.me">Me</Translate>
                </Label>
                <AvInput id="interact-me" type="select" className="form-control" name="meId">
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
              <Button tag={Link} id="cancel-save" to="/interact" replace color="info">
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
  interacts: storeState.interact.entities,
  avatars: storeState.avatar.entities,
  issues: storeState.issue.entities,
  interactEntity: storeState.interact.entity,
  loading: storeState.interact.loading,
  updating: storeState.interact.updating,
  updateSuccess: storeState.interact.updateSuccess,
});

const mapDispatchToProps = {
  getInteracts,
  getAvatars,
  getIssues,
  getEntity,
  updateEntity,
  setBlob,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(InteractUpdate);
