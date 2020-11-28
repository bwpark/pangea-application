import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './regular.reducer';
import { IRegular } from 'app/shared/model/regular.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IRegularProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Regular = (props: IRegularProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { regularList, match, loading } = props;
  return (
    <div>
      <h2 id="regular-heading">
        <Translate contentKey="pangeaApplicationApp.regular.home.title">Regulars</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="pangeaApplicationApp.regular.home.createLabel">Create new Regular</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {regularList && regularList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.regular.name">Name</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.regular.status">Status</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.regular.created">Created</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.regular.modified">Modified</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.regular.you">You</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.regular.me">Me</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {regularList.map((regular, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${regular.id}`} color="link" size="sm">
                      {regular.id}
                    </Button>
                  </td>
                  <td>{regular.name}</td>
                  <td>
                    <Translate contentKey={`pangeaApplicationApp.RegularStatus.${regular.status}`} />
                  </td>
                  <td>{regular.created ? <TextFormat type="date" value={regular.created} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{regular.modified ? <TextFormat type="date" value={regular.modified} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{regular.youId ? <Link to={`avatar/${regular.youId}`}>{regular.youId}</Link> : ''}</td>
                  <td>{regular.meId ? <Link to={`avatar/${regular.meId}`}>{regular.meId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${regular.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${regular.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${regular.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="pangeaApplicationApp.regular.home.notFound">No Regulars found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ regular }: IRootState) => ({
  regularList: regular.entities,
  loading: regular.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Regular);
