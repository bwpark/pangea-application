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
import { getEntity, updateEntity, createEntity, reset } from './chemistry.reducer';
import { IChemistry } from 'app/shared/model/chemistry.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IChemistryUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ChemistryUpdate = (props: IChemistryUpdateProps) => {
  const [youId, setYouId] = useState('0');
  const [meId, setMeId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { chemistryEntity, avatars, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/chemistry');
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

    if (errors.length === 0) {
      const entity = {
        ...chemistryEntity,
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
          <h2 id="pangeaApplicationApp.chemistry.home.createOrEditLabel">
            <Translate contentKey="pangeaApplicationApp.chemistry.home.createOrEditLabel">Create or edit a Chemistry</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : chemistryEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="chemistry-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="chemistry-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="yourNameLabel" for="chemistry-yourName">
                  <Translate contentKey="pangeaApplicationApp.chemistry.yourName">Your Name</Translate>
                </Label>
                <AvField
                  id="chemistry-yourName"
                  type="text"
                  name="yourName"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    maxLength: { value: 128, errorMessage: translate('entity.validation.maxlength', { max: 128 }) },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="toYouLabel" for="chemistry-toYou">
                  <Translate contentKey="pangeaApplicationApp.chemistry.toYou">To You</Translate>
                </Label>
                <AvField
                  id="chemistry-toYou"
                  type="string"
                  className="form-control"
                  name="toYou"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    number: { value: true, errorMessage: translate('entity.validation.number') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="toMeLabel" for="chemistry-toMe">
                  <Translate contentKey="pangeaApplicationApp.chemistry.toMe">To Me</Translate>
                </Label>
                <AvField
                  id="chemistry-toMe"
                  type="string"
                  className="form-control"
                  name="toMe"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    number: { value: true, errorMessage: translate('entity.validation.number') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="createdLabel" for="chemistry-created">
                  <Translate contentKey="pangeaApplicationApp.chemistry.created">Created</Translate>
                </Label>
                <AvInput
                  id="chemistry-created"
                  type="datetime-local"
                  className="form-control"
                  name="created"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.chemistryEntity.created)}
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label for="chemistry-you">
                  <Translate contentKey="pangeaApplicationApp.chemistry.you">You</Translate>
                </Label>
                <AvInput id="chemistry-you" type="select" className="form-control" name="youId">
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
                <Label for="chemistry-me">
                  <Translate contentKey="pangeaApplicationApp.chemistry.me">Me</Translate>
                </Label>
                <AvInput id="chemistry-me" type="select" className="form-control" name="meId">
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
              <Button tag={Link} id="cancel-save" to="/chemistry" replace color="info">
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
  chemistryEntity: storeState.chemistry.entity,
  loading: storeState.chemistry.loading,
  updating: storeState.chemistry.updating,
  updateSuccess: storeState.chemistry.updateSuccess,
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

export default connect(mapStateToProps, mapDispatchToProps)(ChemistryUpdate);
