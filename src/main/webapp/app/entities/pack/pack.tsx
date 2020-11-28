import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './pack.reducer';
import { IPack } from 'app/shared/model/pack.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPackProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Pack = (props: IPackProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { packList, match, loading } = props;
  return (
    <div>
      <h2 id="pack-heading">
        <Translate contentKey="pangeaApplicationApp.pack.home.title">Packs</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="pangeaApplicationApp.pack.home.createLabel">Create new Pack</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {packList && packList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.pack.description">Description</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.pack.coin">Coin</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.pack.point">Point</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.pack.shipTo">Ship To</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.pack.status">Status</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.pack.created">Created</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.pack.modified">Modified</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.pack.me">Me</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {packList.map((pack, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${pack.id}`} color="link" size="sm">
                      {pack.id}
                    </Button>
                  </td>
                  <td>{pack.description}</td>
                  <td>{pack.coin}</td>
                  <td>{pack.point}</td>
                  <td>{pack.shipTo}</td>
                  <td>
                    <Translate contentKey={`pangeaApplicationApp.PackStatus.${pack.status}`} />
                  </td>
                  <td>{pack.created ? <TextFormat type="date" value={pack.created} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{pack.modified ? <TextFormat type="date" value={pack.modified} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{pack.meId ? <Link to={`avatar/${pack.meId}`}>{pack.meId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${pack.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${pack.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${pack.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="pangeaApplicationApp.pack.home.notFound">No Packs found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ pack }: IRootState) => ({
  packList: pack.entities,
  loading: pack.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Pack);
