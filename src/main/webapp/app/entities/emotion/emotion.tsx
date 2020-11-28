import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './emotion.reducer';
import { IEmotion } from 'app/shared/model/emotion.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEmotionProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Emotion = (props: IEmotionProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { emotionList, match, loading } = props;
  return (
    <div>
      <h2 id="emotion-heading">
        <Translate contentKey="pangeaApplicationApp.emotion.home.title">Emotions</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="pangeaApplicationApp.emotion.home.createLabel">Create new Emotion</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {emotionList && emotionList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.emotion.status">Status</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.emotion.created">Created</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.emotion.modified">Modified</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.emotion.you">You</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.emotion.issue">Issue</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.emotion.me">Me</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {emotionList.map((emotion, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${emotion.id}`} color="link" size="sm">
                      {emotion.id}
                    </Button>
                  </td>
                  <td>
                    <Translate contentKey={`pangeaApplicationApp.EmotionStatus.${emotion.status}`} />
                  </td>
                  <td>{emotion.created ? <TextFormat type="date" value={emotion.created} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{emotion.modified ? <TextFormat type="date" value={emotion.modified} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{emotion.youId ? <Link to={`avatar/${emotion.youId}`}>{emotion.youId}</Link> : ''}</td>
                  <td>{emotion.issueId ? <Link to={`issue/${emotion.issueId}`}>{emotion.issueId}</Link> : ''}</td>
                  <td>{emotion.meId ? <Link to={`avatar/${emotion.meId}`}>{emotion.meId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${emotion.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${emotion.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${emotion.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="pangeaApplicationApp.emotion.home.notFound">No Emotions found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ emotion }: IRootState) => ({
  emotionList: emotion.entities,
  loading: emotion.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Emotion);
