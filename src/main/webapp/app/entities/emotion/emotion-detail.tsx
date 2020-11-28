import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './emotion.reducer';
import { IEmotion } from 'app/shared/model/emotion.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEmotionDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const EmotionDetail = (props: IEmotionDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { emotionEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="pangeaApplicationApp.emotion.detail.title">Emotion</Translate> [<b>{emotionEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="status">
              <Translate contentKey="pangeaApplicationApp.emotion.status">Status</Translate>
            </span>
          </dt>
          <dd>{emotionEntity.status}</dd>
          <dt>
            <span id="created">
              <Translate contentKey="pangeaApplicationApp.emotion.created">Created</Translate>
            </span>
          </dt>
          <dd>{emotionEntity.created ? <TextFormat value={emotionEntity.created} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="modified">
              <Translate contentKey="pangeaApplicationApp.emotion.modified">Modified</Translate>
            </span>
          </dt>
          <dd>{emotionEntity.modified ? <TextFormat value={emotionEntity.modified} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <Translate contentKey="pangeaApplicationApp.emotion.you">You</Translate>
          </dt>
          <dd>{emotionEntity.youId ? emotionEntity.youId : ''}</dd>
          <dt>
            <Translate contentKey="pangeaApplicationApp.emotion.issue">Issue</Translate>
          </dt>
          <dd>{emotionEntity.issueId ? emotionEntity.issueId : ''}</dd>
          <dt>
            <Translate contentKey="pangeaApplicationApp.emotion.me">Me</Translate>
          </dt>
          <dd>{emotionEntity.meId ? emotionEntity.meId : ''}</dd>
        </dl>
        <Button tag={Link} to="/emotion" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/emotion/${emotionEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ emotion }: IRootState) => ({
  emotionEntity: emotion.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(EmotionDetail);
