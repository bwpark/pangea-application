import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './tos.reducer';
import { ITOS } from 'app/shared/model/tos.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITOSDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const TOSDetail = (props: ITOSDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { tOSEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="pangeaApplicationApp.tOS.detail.title">TOS</Translate> [<b>{tOSEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="policy">
              <Translate contentKey="pangeaApplicationApp.tOS.policy">Policy</Translate>
            </span>
          </dt>
          <dd>{tOSEntity.policy}</dd>
          <dt>
            <span id="text">
              <Translate contentKey="pangeaApplicationApp.tOS.text">Text</Translate>
            </span>
          </dt>
          <dd>{tOSEntity.text}</dd>
        </dl>
        <Button tag={Link} to="/tos" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/tos/${tOSEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ tOS }: IRootState) => ({
  tOSEntity: tOS.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TOSDetail);
