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
import { getEntity, updateEntity, createEntity, reset } from './report.reducer';
import { IReport } from 'app/shared/model/report.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IReportUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ReportUpdate = (props: IReportUpdateProps) => {
  const [youId, setYouId] = useState('0');
  const [meId, setMeId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { reportEntity, avatars, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/report');
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
        ...reportEntity,
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
          <h2 id="pangeaApplicationApp.report.home.createOrEditLabel">
            <Translate contentKey="pangeaApplicationApp.report.home.createOrEditLabel">Create or edit a Report</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : reportEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="report-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="report-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="descriptionLabel" for="report-description">
                  <Translate contentKey="pangeaApplicationApp.report.description">Description</Translate>
                </Label>
                <AvField
                  id="report-description"
                  type="text"
                  name="description"
                  validate={{
                    maxLength: { value: 1024, errorMessage: translate('entity.validation.maxlength', { max: 1024 }) },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="nameLabel" for="report-name">
                  <Translate contentKey="pangeaApplicationApp.report.name">Name</Translate>
                </Label>
                <AvField
                  id="report-name"
                  type="text"
                  name="name"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    maxLength: { value: 128, errorMessage: translate('entity.validation.maxlength', { max: 128 }) },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="statusLabel" for="report-status">
                  <Translate contentKey="pangeaApplicationApp.report.status">Status</Translate>
                </Label>
                <AvInput
                  id="report-status"
                  type="select"
                  className="form-control"
                  name="status"
                  value={(!isNew && reportEntity.status) || 'ORIGINATE'}
                >
                  <option value="ORIGINATE">{translate('pangeaApplicationApp.ReportStatus.ORIGINATE')}</option>
                  <option value="TAKEOVER">{translate('pangeaApplicationApp.ReportStatus.TAKEOVER')}</option>
                  <option value="COMPLETED">{translate('pangeaApplicationApp.ReportStatus.COMPLETED')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="createdLabel" for="report-created">
                  <Translate contentKey="pangeaApplicationApp.report.created">Created</Translate>
                </Label>
                <AvInput
                  id="report-created"
                  type="datetime-local"
                  className="form-control"
                  name="created"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.reportEntity.created)}
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="modifiedLabel" for="report-modified">
                  <Translate contentKey="pangeaApplicationApp.report.modified">Modified</Translate>
                </Label>
                <AvInput
                  id="report-modified"
                  type="datetime-local"
                  className="form-control"
                  name="modified"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.reportEntity.modified)}
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label for="report-you">
                  <Translate contentKey="pangeaApplicationApp.report.you">You</Translate>
                </Label>
                <AvInput id="report-you" type="select" className="form-control" name="youId">
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
                <Label for="report-me">
                  <Translate contentKey="pangeaApplicationApp.report.me">Me</Translate>
                </Label>
                <AvInput id="report-me" type="select" className="form-control" name="meId">
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
              <Button tag={Link} id="cancel-save" to="/report" replace color="info">
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
  reportEntity: storeState.report.entity,
  loading: storeState.report.loading,
  updating: storeState.report.updating,
  updateSuccess: storeState.report.updateSuccess,
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

export default connect(mapStateToProps, mapDispatchToProps)(ReportUpdate);
