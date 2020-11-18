import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './pack.reducer';
import { IPack } from 'app/shared/model/pack.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPackDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const PackDetail = (props: IPackDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { packEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="pangeaApplicationApp.pack.detail.title">Pack</Translate> [<b>{packEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="description">
              <Translate contentKey="pangeaApplicationApp.pack.description">Description</Translate>
            </span>
          </dt>
          <dd>{packEntity.description}</dd>
          <dt>
            <span id="coin">
              <Translate contentKey="pangeaApplicationApp.pack.coin">Coin</Translate>
            </span>
          </dt>
          <dd>{packEntity.coin}</dd>
          <dt>
            <span id="point">
              <Translate contentKey="pangeaApplicationApp.pack.point">Point</Translate>
            </span>
          </dt>
          <dd>{packEntity.point}</dd>
          <dt>
            <span id="shipTo">
              <Translate contentKey="pangeaApplicationApp.pack.shipTo">Ship To</Translate>
            </span>
          </dt>
          <dd>{packEntity.shipTo}</dd>
          <dt>
            <span id="status">
              <Translate contentKey="pangeaApplicationApp.pack.status">Status</Translate>
            </span>
          </dt>
          <dd>{packEntity.status}</dd>
          <dt>
            <span id="created">
              <Translate contentKey="pangeaApplicationApp.pack.created">Created</Translate>
            </span>
          </dt>
          <dd>{packEntity.created ? <TextFormat value={packEntity.created} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="modified">
              <Translate contentKey="pangeaApplicationApp.pack.modified">Modified</Translate>
            </span>
          </dt>
          <dd>{packEntity.modified ? <TextFormat value={packEntity.modified} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <Translate contentKey="pangeaApplicationApp.pack.me">Me</Translate>
          </dt>
          <dd>{packEntity.meId ? packEntity.meId : ''}</dd>
        </dl>
        <Button tag={Link} to="/pack" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/pack/${packEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ pack }: IRootState) => ({
  packEntity: pack.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(PackDetail);
