import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './deal.reducer';
import { IDeal } from 'app/shared/model/deal.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDealDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DealDetail = (props: IDealDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { dealEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="pangeaApplicationApp.deal.detail.title">Deal</Translate> [<b>{dealEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="name">
              <Translate contentKey="pangeaApplicationApp.deal.name">Name</Translate>
            </span>
          </dt>
          <dd>{dealEntity.name}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="pangeaApplicationApp.deal.description">Description</Translate>
            </span>
          </dt>
          <dd>{dealEntity.description}</dd>
          <dt>
            <span id="quantity">
              <Translate contentKey="pangeaApplicationApp.deal.quantity">Quantity</Translate>
            </span>
          </dt>
          <dd>{dealEntity.quantity}</dd>
          <dt>
            <span id="unitPrice">
              <Translate contentKey="pangeaApplicationApp.deal.unitPrice">Unit Price</Translate>
            </span>
          </dt>
          <dd>{dealEntity.unitPrice}</dd>
          <dt>
            <span id="coin">
              <Translate contentKey="pangeaApplicationApp.deal.coin">Coin</Translate>
            </span>
          </dt>
          <dd>{dealEntity.coin}</dd>
          <dt>
            <span id="point">
              <Translate contentKey="pangeaApplicationApp.deal.point">Point</Translate>
            </span>
          </dt>
          <dd>{dealEntity.point}</dd>
          <dt>
            <span id="status">
              <Translate contentKey="pangeaApplicationApp.deal.status">Status</Translate>
            </span>
          </dt>
          <dd>{dealEntity.status}</dd>
          <dt>
            <span id="created">
              <Translate contentKey="pangeaApplicationApp.deal.created">Created</Translate>
            </span>
          </dt>
          <dd>{dealEntity.created ? <TextFormat value={dealEntity.created} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="modified">
              <Translate contentKey="pangeaApplicationApp.deal.modified">Modified</Translate>
            </span>
          </dt>
          <dd>{dealEntity.modified ? <TextFormat value={dealEntity.modified} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <Translate contentKey="pangeaApplicationApp.deal.with">With</Translate>
          </dt>
          <dd>{dealEntity.withId ? dealEntity.withId : ''}</dd>
          <dt>
            <Translate contentKey="pangeaApplicationApp.deal.pack">Pack</Translate>
          </dt>
          <dd>{dealEntity.packId ? dealEntity.packId : ''}</dd>
          <dt>
            <Translate contentKey="pangeaApplicationApp.deal.to">To</Translate>
          </dt>
          <dd>{dealEntity.toId ? dealEntity.toId : ''}</dd>
        </dl>
        <Button tag={Link} to="/deal" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/deal/${dealEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ deal }: IRootState) => ({
  dealEntity: deal.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DealDetail);
