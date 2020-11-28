import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './regular.reducer';
import { IRegular } from 'app/shared/model/regular.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IRegularDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const RegularDetail = (props: IRegularDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { regularEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="pangeaApplicationApp.regular.detail.title">Regular</Translate> [<b>{regularEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="name">
              <Translate contentKey="pangeaApplicationApp.regular.name">Name</Translate>
            </span>
          </dt>
          <dd>{regularEntity.name}</dd>
          <dt>
            <span id="status">
              <Translate contentKey="pangeaApplicationApp.regular.status">Status</Translate>
            </span>
          </dt>
          <dd>{regularEntity.status}</dd>
          <dt>
            <span id="created">
              <Translate contentKey="pangeaApplicationApp.regular.created">Created</Translate>
            </span>
          </dt>
          <dd>{regularEntity.created ? <TextFormat value={regularEntity.created} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="modified">
              <Translate contentKey="pangeaApplicationApp.regular.modified">Modified</Translate>
            </span>
          </dt>
          <dd>{regularEntity.modified ? <TextFormat value={regularEntity.modified} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <Translate contentKey="pangeaApplicationApp.regular.you">You</Translate>
          </dt>
          <dd>{regularEntity.youId ? regularEntity.youId : ''}</dd>
          <dt>
            <Translate contentKey="pangeaApplicationApp.regular.me">Me</Translate>
          </dt>
          <dd>{regularEntity.meId ? regularEntity.meId : ''}</dd>
        </dl>
        <Button tag={Link} to="/regular" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/regular/${regularEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ regular }: IRootState) => ({
  regularEntity: regular.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(RegularDetail);
