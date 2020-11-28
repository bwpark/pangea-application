import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './repute.reducer';
import { IRepute } from 'app/shared/model/repute.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IReputeDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ReputeDetail = (props: IReputeDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { reputeEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="pangeaApplicationApp.repute.detail.title">Repute</Translate> [<b>{reputeEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="description">
              <Translate contentKey="pangeaApplicationApp.repute.description">Description</Translate>
            </span>
          </dt>
          <dd>{reputeEntity.description}</dd>
          <dt>
            <span id="grade">
              <Translate contentKey="pangeaApplicationApp.repute.grade">Grade</Translate>
            </span>
          </dt>
          <dd>{reputeEntity.grade}</dd>
          <dt>
            <span id="credit">
              <Translate contentKey="pangeaApplicationApp.repute.credit">Credit</Translate>
            </span>
          </dt>
          <dd>{reputeEntity.credit}</dd>
          <dt>
            <span id="status">
              <Translate contentKey="pangeaApplicationApp.repute.status">Status</Translate>
            </span>
          </dt>
          <dd>{reputeEntity.status}</dd>
          <dt>
            <span id="created">
              <Translate contentKey="pangeaApplicationApp.repute.created">Created</Translate>
            </span>
          </dt>
          <dd>{reputeEntity.created ? <TextFormat value={reputeEntity.created} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="modified">
              <Translate contentKey="pangeaApplicationApp.repute.modified">Modified</Translate>
            </span>
          </dt>
          <dd>{reputeEntity.modified ? <TextFormat value={reputeEntity.modified} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <Translate contentKey="pangeaApplicationApp.repute.you">You</Translate>
          </dt>
          <dd>{reputeEntity.youId ? reputeEntity.youId : ''}</dd>
          <dt>
            <Translate contentKey="pangeaApplicationApp.repute.me">Me</Translate>
          </dt>
          <dd>{reputeEntity.meId ? reputeEntity.meId : ''}</dd>
        </dl>
        <Button tag={Link} to="/repute" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/repute/${reputeEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ repute }: IRootState) => ({
  reputeEntity: repute.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ReputeDetail);
