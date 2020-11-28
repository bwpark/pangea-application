import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './deal-option.reducer';
import { IDealOption } from 'app/shared/model/deal-option.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDealOptionDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DealOptionDetail = (props: IDealOptionDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { dealOptionEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="pangeaApplicationApp.dealOption.detail.title">DealOption</Translate> [<b>{dealOptionEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="name">
              <Translate contentKey="pangeaApplicationApp.dealOption.name">Name</Translate>
            </span>
          </dt>
          <dd>{dealOptionEntity.name}</dd>
          <dt>
            <span id="status">
              <Translate contentKey="pangeaApplicationApp.dealOption.status">Status</Translate>
            </span>
          </dt>
          <dd>{dealOptionEntity.status}</dd>
          <dt>
            <span id="created">
              <Translate contentKey="pangeaApplicationApp.dealOption.created">Created</Translate>
            </span>
          </dt>
          <dd>{dealOptionEntity.created ? <TextFormat value={dealOptionEntity.created} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="modified">
              <Translate contentKey="pangeaApplicationApp.dealOption.modified">Modified</Translate>
            </span>
          </dt>
          <dd>
            {dealOptionEntity.modified ? <TextFormat value={dealOptionEntity.modified} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <Translate contentKey="pangeaApplicationApp.dealOption.pack">Pack</Translate>
          </dt>
          <dd>{dealOptionEntity.packId ? dealOptionEntity.packId : ''}</dd>
        </dl>
        <Button tag={Link} to="/deal-option" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/deal-option/${dealOptionEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ dealOption }: IRootState) => ({
  dealOptionEntity: dealOption.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DealOptionDetail);
