import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './deal-option.reducer';
import { IDealOption } from 'app/shared/model/deal-option.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDealOptionProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const DealOption = (props: IDealOptionProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { dealOptionList, match, loading } = props;
  return (
    <div>
      <h2 id="deal-option-heading">
        <Translate contentKey="pangeaApplicationApp.dealOption.home.title">Deal Options</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="pangeaApplicationApp.dealOption.home.createLabel">Create new Deal Option</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {dealOptionList && dealOptionList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.dealOption.name">Name</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.dealOption.status">Status</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.dealOption.created">Created</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.dealOption.modified">Modified</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.dealOption.pack">Pack</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {dealOptionList.map((dealOption, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${dealOption.id}`} color="link" size="sm">
                      {dealOption.id}
                    </Button>
                  </td>
                  <td>{dealOption.name}</td>
                  <td>
                    <Translate contentKey={`pangeaApplicationApp.DealOptionStatus.${dealOption.status}`} />
                  </td>
                  <td>{dealOption.created ? <TextFormat type="date" value={dealOption.created} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{dealOption.modified ? <TextFormat type="date" value={dealOption.modified} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{dealOption.packId ? <Link to={`deal/${dealOption.packId}`}>{dealOption.packId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${dealOption.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${dealOption.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${dealOption.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="pangeaApplicationApp.dealOption.home.notFound">No Deal Options found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ dealOption }: IRootState) => ({
  dealOptionList: dealOption.entities,
  loading: dealOption.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DealOption);
