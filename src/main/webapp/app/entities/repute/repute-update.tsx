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
import { getEntity, updateEntity, createEntity, reset } from './repute.reducer';
import { IRepute } from 'app/shared/model/repute.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IReputeUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ReputeUpdate = (props: IReputeUpdateProps) => {
  const [youId, setYouId] = useState('0');
  const [meId, setMeId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { reputeEntity, avatars, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/repute');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getAvatars();
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
        ...reputeEntity,
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
          <h2 id="pangeaApplicationApp.repute.home.createOrEditLabel">
            <Translate contentKey="pangeaApplicationApp.repute.home.createOrEditLabel">Create or edit a Repute</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : reputeEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="repute-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="repute-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="descriptionLabel" for="repute-description">
                  <Translate contentKey="pangeaApplicationApp.repute.description">Description</Translate>
                </Label>
                <AvField
                  id="repute-description"
                  type="text"
                  name="description"
                  validate={{
                    maxLength: { value: 1024, errorMessage: translate('entity.validation.maxlength', { max: 1024 }) },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="gradeLabel" for="repute-grade">
                  <Translate contentKey="pangeaApplicationApp.repute.grade">Grade</Translate>
                </Label>
                <AvField
                  id="repute-grade"
                  type="string"
                  className="form-control"
                  name="grade"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    number: { value: true, errorMessage: translate('entity.validation.number') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="creditLabel" for="repute-credit">
                  <Translate contentKey="pangeaApplicationApp.repute.credit">Credit</Translate>
                </Label>
                <AvField
                  id="repute-credit"
                  type="string"
                  className="form-control"
                  name="credit"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    number: { value: true, errorMessage: translate('entity.validation.number') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="statusLabel" for="repute-status">
                  <Translate contentKey="pangeaApplicationApp.repute.status">Status</Translate>
                </Label>
                <AvInput
                  id="repute-status"
                  type="select"
                  className="form-control"
                  name="status"
                  value={(!isNew && reputeEntity.status) || 'ORIGINATE'}
                >
                  <option value="ORIGINATE">{translate('pangeaApplicationApp.ReputeStatus.ORIGINATE')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="createdLabel" for="repute-created">
                  <Translate contentKey="pangeaApplicationApp.repute.created">Created</Translate>
                </Label>
                <AvInput
                  id="repute-created"
                  type="datetime-local"
                  className="form-control"
                  name="created"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.reputeEntity.created)}
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="modifiedLabel" for="repute-modified">
                  <Translate contentKey="pangeaApplicationApp.repute.modified">Modified</Translate>
                </Label>
                <AvInput
                  id="repute-modified"
                  type="datetime-local"
                  className="form-control"
                  name="modified"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.reputeEntity.modified)}
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label for="repute-you">
                  <Translate contentKey="pangeaApplicationApp.repute.you">You</Translate>
                </Label>
                <AvInput id="repute-you" type="select" className="form-control" name="youId">
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
                <Label for="repute-me">
                  <Translate contentKey="pangeaApplicationApp.repute.me">Me</Translate>
                </Label>
                <AvInput id="repute-me" type="select" className="form-control" name="meId">
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
              <Button tag={Link} id="cancel-save" to="/repute" replace color="info">
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
  reputeEntity: storeState.repute.entity,
  loading: storeState.repute.loading,
  updating: storeState.repute.updating,
  updateSuccess: storeState.repute.updateSuccess,
});

const mapDispatchToProps = {
  getAvatars,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ReputeUpdate);
