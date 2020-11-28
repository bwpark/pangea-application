import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntities as getCategory2Issues } from 'app/entities/category-2-issue/category-2-issue.reducer';
import { getEntity, updateEntity, createEntity, reset } from './category-2-issue.reducer';
import { ICategory2Issue } from 'app/shared/model/category-2-issue.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ICategory2IssueUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const Category2IssueUpdate = (props: ICategory2IssueUpdateProps) => {
  const [childId, setChildId] = useState('0');
  const [parentId, setParentId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { category2IssueEntity, category2Issues, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/category-2-issue');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getCategory2Issues();
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
        ...category2IssueEntity,
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
          <h2 id="pangeaApplicationApp.category2Issue.home.createOrEditLabel">
            <Translate contentKey="pangeaApplicationApp.category2Issue.home.createOrEditLabel">Create or edit a Category2Issue</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : category2IssueEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="category-2-issue-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="category-2-issue-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="iconLabel" for="category-2-issue-icon">
                  <Translate contentKey="pangeaApplicationApp.category2Issue.icon">Icon</Translate>
                </Label>
                <AvField
                  id="category-2-issue-icon"
                  type="text"
                  name="icon"
                  validate={{
                    maxLength: { value: 1024, errorMessage: translate('entity.validation.maxlength', { max: 1024 }) },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="nameLabel" for="category-2-issue-name">
                  <Translate contentKey="pangeaApplicationApp.category2Issue.name">Name</Translate>
                </Label>
                <AvField
                  id="category-2-issue-name"
                  type="text"
                  name="name"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    maxLength: { value: 128, errorMessage: translate('entity.validation.maxlength', { max: 128 }) },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="category-2-issue-description">
                  <Translate contentKey="pangeaApplicationApp.category2Issue.description">Description</Translate>
                </Label>
                <AvField
                  id="category-2-issue-description"
                  type="text"
                  name="description"
                  validate={{
                    maxLength: { value: 1024, errorMessage: translate('entity.validation.maxlength', { max: 1024 }) },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="statusLabel" for="category-2-issue-status">
                  <Translate contentKey="pangeaApplicationApp.category2Issue.status">Status</Translate>
                </Label>
                <AvInput
                  id="category-2-issue-status"
                  type="select"
                  className="form-control"
                  name="status"
                  value={(!isNew && category2IssueEntity.status) || 'ORIGINATE'}
                >
                  <option value="ORIGINATE">{translate('pangeaApplicationApp.Category2IssueStatus.ORIGINATE')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="createdLabel" for="category-2-issue-created">
                  <Translate contentKey="pangeaApplicationApp.category2Issue.created">Created</Translate>
                </Label>
                <AvInput
                  id="category-2-issue-created"
                  type="datetime-local"
                  className="form-control"
                  name="created"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.category2IssueEntity.created)}
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="modifiedLabel" for="category-2-issue-modified">
                  <Translate contentKey="pangeaApplicationApp.category2Issue.modified">Modified</Translate>
                </Label>
                <AvInput
                  id="category-2-issue-modified"
                  type="datetime-local"
                  className="form-control"
                  name="modified"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.category2IssueEntity.modified)}
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label for="category-2-issue-parent">
                  <Translate contentKey="pangeaApplicationApp.category2Issue.parent">Parent</Translate>
                </Label>
                <AvInput id="category-2-issue-parent" type="select" className="form-control" name="parentId">
                  <option value="" key="0" />
                  {category2Issues
                    ? category2Issues.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/category-2-issue" replace color="info">
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
  category2Issues: storeState.category2Issue.entities,
  category2IssueEntity: storeState.category2Issue.entity,
  loading: storeState.category2Issue.loading,
  updating: storeState.category2Issue.updating,
  updateSuccess: storeState.category2Issue.updateSuccess,
});

const mapDispatchToProps = {
  getCategory2Issues,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Category2IssueUpdate);
