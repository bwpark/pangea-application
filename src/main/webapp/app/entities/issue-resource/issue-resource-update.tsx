import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IIssue } from 'app/shared/model/issue.model';
import { getEntities as getIssues } from 'app/entities/issue/issue.reducer';
import { getEntity, updateEntity, createEntity, reset } from './issue-resource.reducer';
import { IIssueResource } from 'app/shared/model/issue-resource.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IIssueResourceUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const IssueResourceUpdate = (props: IIssueResourceUpdateProps) => {
  const [issueId, setIssueId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { issueResourceEntity, issues, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/issue-resource');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getIssues();
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
        ...issueResourceEntity,
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
          <h2 id="pangeaApplicationApp.issueResource.home.createOrEditLabel">
            <Translate contentKey="pangeaApplicationApp.issueResource.home.createOrEditLabel">Create or edit a IssueResource</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : issueResourceEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="issue-resource-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="issue-resource-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="typeLabel" for="issue-resource-type">
                  <Translate contentKey="pangeaApplicationApp.issueResource.type">Type</Translate>
                </Label>
                <AvInput
                  id="issue-resource-type"
                  type="select"
                  className="form-control"
                  name="type"
                  value={(!isNew && issueResourceEntity.type) || 'LINK'}
                >
                  <option value="LINK">{translate('pangeaApplicationApp.ResourceType.LINK')}</option>
                  <option value="IMAGE">{translate('pangeaApplicationApp.ResourceType.IMAGE')}</option>
                  <option value="VIDEO">{translate('pangeaApplicationApp.ResourceType.VIDEO')}</option>
                  <option value="FILE">{translate('pangeaApplicationApp.ResourceType.FILE')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="linkLabel" for="issue-resource-link">
                  <Translate contentKey="pangeaApplicationApp.issueResource.link">Link</Translate>
                </Label>
                <AvField
                  id="issue-resource-link"
                  type="text"
                  name="link"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="createdLabel" for="issue-resource-created">
                  <Translate contentKey="pangeaApplicationApp.issueResource.created">Created</Translate>
                </Label>
                <AvInput
                  id="issue-resource-created"
                  type="datetime-local"
                  className="form-control"
                  name="created"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.issueResourceEntity.created)}
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label for="issue-resource-issue">
                  <Translate contentKey="pangeaApplicationApp.issueResource.issue">Issue</Translate>
                </Label>
                <AvInput id="issue-resource-issue" type="select" className="form-control" name="issueId">
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
              <Button tag={Link} id="cancel-save" to="/issue-resource" replace color="info">
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
  issues: storeState.issue.entities,
  issueResourceEntity: storeState.issueResource.entity,
  loading: storeState.issueResource.loading,
  updating: storeState.issueResource.updating,
  updateSuccess: storeState.issueResource.updateSuccess,
});

const mapDispatchToProps = {
  getIssues,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(IssueResourceUpdate);
