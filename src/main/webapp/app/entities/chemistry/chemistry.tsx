import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './chemistry.reducer';
import { IChemistry } from 'app/shared/model/chemistry.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IChemistryProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Chemistry = (props: IChemistryProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { chemistryList, match, loading } = props;
  return (
    <div>
      <h2 id="chemistry-heading">
        <Translate contentKey="pangeaApplicationApp.chemistry.home.title">Chemistries</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="pangeaApplicationApp.chemistry.home.createLabel">Create new Chemistry</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {chemistryList && chemistryList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.chemistry.yourName">Your Name</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.chemistry.toYou">To You</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.chemistry.toMe">To Me</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.chemistry.created">Created</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.chemistry.you">You</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.chemistry.me">Me</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {chemistryList.map((chemistry, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${chemistry.id}`} color="link" size="sm">
                      {chemistry.id}
                    </Button>
                  </td>
                  <td>{chemistry.yourName}</td>
                  <td>{chemistry.toYou}</td>
                  <td>{chemistry.toMe}</td>
                  <td>{chemistry.created ? <TextFormat type="date" value={chemistry.created} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{chemistry.youId ? <Link to={`avatar/${chemistry.youId}`}>{chemistry.youId}</Link> : ''}</td>
                  <td>{chemistry.meId ? <Link to={`avatar/${chemistry.meId}`}>{chemistry.meId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${chemistry.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${chemistry.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${chemistry.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="pangeaApplicationApp.chemistry.home.notFound">No Chemistries found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ chemistry }: IRootState) => ({
  chemistryList: chemistry.entities,
  loading: chemistry.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Chemistry);
