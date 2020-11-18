import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './chemistry.reducer';
import { IChemistry } from 'app/shared/model/chemistry.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IChemistryDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ChemistryDetail = (props: IChemistryDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { chemistryEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="pangeaApplicationApp.chemistry.detail.title">Chemistry</Translate> [<b>{chemistryEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="yourName">
              <Translate contentKey="pangeaApplicationApp.chemistry.yourName">Your Name</Translate>
            </span>
          </dt>
          <dd>{chemistryEntity.yourName}</dd>
          <dt>
            <span id="toYou">
              <Translate contentKey="pangeaApplicationApp.chemistry.toYou">To You</Translate>
            </span>
          </dt>
          <dd>{chemistryEntity.toYou}</dd>
          <dt>
            <span id="toMe">
              <Translate contentKey="pangeaApplicationApp.chemistry.toMe">To Me</Translate>
            </span>
          </dt>
          <dd>{chemistryEntity.toMe}</dd>
          <dt>
            <span id="created">
              <Translate contentKey="pangeaApplicationApp.chemistry.created">Created</Translate>
            </span>
          </dt>
          <dd>{chemistryEntity.created ? <TextFormat value={chemistryEntity.created} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <Translate contentKey="pangeaApplicationApp.chemistry.you">You</Translate>
          </dt>
          <dd>{chemistryEntity.youId ? chemistryEntity.youId : ''}</dd>
          <dt>
            <Translate contentKey="pangeaApplicationApp.chemistry.me">Me</Translate>
          </dt>
          <dd>{chemistryEntity.meId ? chemistryEntity.meId : ''}</dd>
        </dl>
        <Button tag={Link} to="/chemistry" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/chemistry/${chemistryEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ chemistry }: IRootState) => ({
  chemistryEntity: chemistry.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ChemistryDetail);
