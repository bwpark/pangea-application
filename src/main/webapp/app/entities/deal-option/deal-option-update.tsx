import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IDeal } from 'app/shared/model/deal.model';
import { getEntities as getDeals } from 'app/entities/deal/deal.reducer';
import { getEntity, updateEntity, createEntity, reset } from './deal-option.reducer';
import { IDealOption } from 'app/shared/model/deal-option.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IDealOptionUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DealOptionUpdate = (props: IDealOptionUpdateProps) => {
  const [packId, setPackId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { dealOptionEntity, deals, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/deal-option');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getDeals();
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
        ...dealOptionEntity,
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
          <h2 id="pangeaApplicationApp.dealOption.home.createOrEditLabel">
            <Translate contentKey="pangeaApplicationApp.dealOption.home.createOrEditLabel">Create or edit a DealOption</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : dealOptionEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="deal-option-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="deal-option-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nameLabel" for="deal-option-name">
                  <Translate contentKey="pangeaApplicationApp.dealOption.name">Name</Translate>
                </Label>
                <AvField
                  id="deal-option-name"
                  type="text"
                  name="name"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    maxLength: { value: 128, errorMessage: translate('entity.validation.maxlength', { max: 128 }) },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="statusLabel" for="deal-option-status">
                  <Translate contentKey="pangeaApplicationApp.dealOption.status">Status</Translate>
                </Label>
                <AvInput
                  id="deal-option-status"
                  type="select"
                  className="form-control"
                  name="status"
                  value={(!isNew && dealOptionEntity.status) || 'ORIGINATE'}
                >
                  <option value="ORIGINATE">{translate('pangeaApplicationApp.DealOptionStatus.ORIGINATE')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="createdLabel" for="deal-option-created">
                  <Translate contentKey="pangeaApplicationApp.dealOption.created">Created</Translate>
                </Label>
                <AvInput
                  id="deal-option-created"
                  type="datetime-local"
                  className="form-control"
                  name="created"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.dealOptionEntity.created)}
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="modifiedLabel" for="deal-option-modified">
                  <Translate contentKey="pangeaApplicationApp.dealOption.modified">Modified</Translate>
                </Label>
                <AvInput
                  id="deal-option-modified"
                  type="datetime-local"
                  className="form-control"
                  name="modified"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.dealOptionEntity.modified)}
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label for="deal-option-pack">
                  <Translate contentKey="pangeaApplicationApp.dealOption.pack">Pack</Translate>
                </Label>
                <AvInput id="deal-option-pack" type="select" className="form-control" name="packId">
                  <option value="" key="0" />
                  {deals
                    ? deals.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/deal-option" replace color="info">
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
  deals: storeState.deal.entities,
  dealOptionEntity: storeState.dealOption.entity,
  loading: storeState.dealOption.loading,
  updating: storeState.dealOption.updating,
  updateSuccess: storeState.dealOption.updateSuccess,
});

const mapDispatchToProps = {
  getDeals,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DealOptionUpdate);
