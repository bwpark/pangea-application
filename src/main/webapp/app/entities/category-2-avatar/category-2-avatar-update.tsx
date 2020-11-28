import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntities as getCategory2Avatars } from 'app/entities/category-2-avatar/category-2-avatar.reducer';
import { getEntity, updateEntity, createEntity, reset } from './category-2-avatar.reducer';
import { ICategory2avatar } from 'app/shared/model/category-2-avatar.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ICategory2avatarUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const Category2avatarUpdate = (props: ICategory2avatarUpdateProps) => {
  const [childId, setChildId] = useState('0');
  const [parentId, setParentId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { category2avatarEntity, category2avatars, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/category-2-avatar');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getCategory2Avatars();
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
        ...category2avatarEntity,
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
          <h2 id="pangeaApplicationApp.category2avatar.home.createOrEditLabel">
            <Translate contentKey="pangeaApplicationApp.category2avatar.home.createOrEditLabel">Create or edit a Category2avatar</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : category2avatarEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="category-2-avatar-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="category-2-avatar-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="iconLabel" for="category-2-avatar-icon">
                  <Translate contentKey="pangeaApplicationApp.category2avatar.icon">Icon</Translate>
                </Label>
                <AvField
                  id="category-2-avatar-icon"
                  type="text"
                  name="icon"
                  validate={{
                    maxLength: { value: 1024, errorMessage: translate('entity.validation.maxlength', { max: 1024 }) },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="nameLabel" for="category-2-avatar-name">
                  <Translate contentKey="pangeaApplicationApp.category2avatar.name">Name</Translate>
                </Label>
                <AvField
                  id="category-2-avatar-name"
                  type="text"
                  name="name"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    maxLength: { value: 128, errorMessage: translate('entity.validation.maxlength', { max: 128 }) },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="category-2-avatar-description">
                  <Translate contentKey="pangeaApplicationApp.category2avatar.description">Description</Translate>
                </Label>
                <AvField
                  id="category-2-avatar-description"
                  type="text"
                  name="description"
                  validate={{
                    maxLength: { value: 1024, errorMessage: translate('entity.validation.maxlength', { max: 1024 }) },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="statusLabel" for="category-2-avatar-status">
                  <Translate contentKey="pangeaApplicationApp.category2avatar.status">Status</Translate>
                </Label>
                <AvInput
                  id="category-2-avatar-status"
                  type="select"
                  className="form-control"
                  name="status"
                  value={(!isNew && category2avatarEntity.status) || 'ORIGINATE'}
                >
                  <option value="ORIGINATE">{translate('pangeaApplicationApp.Category2avatarStatus.ORIGINATE')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="createdLabel" for="category-2-avatar-created">
                  <Translate contentKey="pangeaApplicationApp.category2avatar.created">Created</Translate>
                </Label>
                <AvInput
                  id="category-2-avatar-created"
                  type="datetime-local"
                  className="form-control"
                  name="created"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.category2avatarEntity.created)}
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="modifiedLabel" for="category-2-avatar-modified">
                  <Translate contentKey="pangeaApplicationApp.category2avatar.modified">Modified</Translate>
                </Label>
                <AvInput
                  id="category-2-avatar-modified"
                  type="datetime-local"
                  className="form-control"
                  name="modified"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.category2avatarEntity.modified)}
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label for="category-2-avatar-parent">
                  <Translate contentKey="pangeaApplicationApp.category2avatar.parent">Parent</Translate>
                </Label>
                <AvInput id="category-2-avatar-parent" type="select" className="form-control" name="parentId">
                  <option value="" key="0" />
                  {category2avatars
                    ? category2avatars.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/category-2-avatar" replace color="info">
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
  category2avatars: storeState.category2avatar.entities,
  category2avatarEntity: storeState.category2avatar.entity,
  loading: storeState.category2avatar.loading,
  updating: storeState.category2avatar.updating,
  updateSuccess: storeState.category2avatar.updateSuccess,
});

const mapDispatchToProps = {
  getCategory2Avatars,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Category2avatarUpdate);
