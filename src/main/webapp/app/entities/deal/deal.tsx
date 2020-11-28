import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './deal.reducer';
import { IDeal } from 'app/shared/model/deal.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDealProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Deal = (props: IDealProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { dealList, match, loading } = props;
  return (
    <div>
      <h2 id="deal-heading">
        <Translate contentKey="pangeaApplicationApp.deal.home.title">Deals</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="pangeaApplicationApp.deal.home.createLabel">Create new Deal</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {dealList && dealList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.deal.name">Name</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.deal.description">Description</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.deal.quantity">Quantity</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.deal.unitPrice">Unit Price</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.deal.coin">Coin</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.deal.point">Point</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.deal.status">Status</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.deal.created">Created</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.deal.modified">Modified</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.deal.with">With</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.deal.pack">Pack</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.deal.to">To</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {dealList.map((deal, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${deal.id}`} color="link" size="sm">
                      {deal.id}
                    </Button>
                  </td>
                  <td>{deal.name}</td>
                  <td>{deal.description}</td>
                  <td>{deal.quantity}</td>
                  <td>{deal.unitPrice}</td>
                  <td>{deal.coin}</td>
                  <td>{deal.point}</td>
                  <td>
                    <Translate contentKey={`pangeaApplicationApp.DealStatus.${deal.status}`} />
                  </td>
                  <td>{deal.created ? <TextFormat type="date" value={deal.created} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{deal.modified ? <TextFormat type="date" value={deal.modified} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{deal.withId ? <Link to={`issue/${deal.withId}`}>{deal.withId}</Link> : ''}</td>
                  <td>{deal.packId ? <Link to={`pack/${deal.packId}`}>{deal.packId}</Link> : ''}</td>
                  <td>{deal.toId ? <Link to={`avatar/${deal.toId}`}>{deal.toId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${deal.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${deal.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${deal.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="pangeaApplicationApp.deal.home.notFound">No Deals found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ deal }: IRootState) => ({
  dealList: deal.entities,
  loading: deal.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Deal);
