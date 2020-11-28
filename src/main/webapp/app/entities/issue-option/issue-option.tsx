import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './issue-option.reducer';
import { IIssueOption } from 'app/shared/model/issue-option.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IIssueOptionProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const IssueOption = (props: IIssueOptionProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { issueOptionList, match, loading } = props;
  return (
    <div>
      <h2 id="issue-option-heading">
        <Translate contentKey="pangeaApplicationApp.issueOption.home.title">Issue Options</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="pangeaApplicationApp.issueOption.home.createLabel">Create new Issue Option</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {issueOptionList && issueOptionList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.issueOption.name">Name</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.issueOption.description">Description</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.issueOption.coin">Coin</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.issueOption.point">Point</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.issueOption.status">Status</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.issueOption.created">Created</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.issueOption.modified">Modified</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.issueOption.issue">Issue</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {issueOptionList.map((issueOption, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${issueOption.id}`} color="link" size="sm">
                      {issueOption.id}
                    </Button>
                  </td>
                  <td>{issueOption.name}</td>
                  <td>{issueOption.description}</td>
                  <td>{issueOption.coin}</td>
                  <td>{issueOption.point}</td>
                  <td>
                    <Translate contentKey={`pangeaApplicationApp.IssueOptionStatus.${issueOption.status}`} />
                  </td>
                  <td>{issueOption.created ? <TextFormat type="date" value={issueOption.created} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{issueOption.modified ? <TextFormat type="date" value={issueOption.modified} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{issueOption.issueId ? <Link to={`issue/${issueOption.issueId}`}>{issueOption.issueId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${issueOption.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${issueOption.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${issueOption.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="pangeaApplicationApp.issueOption.home.notFound">No Issue Options found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ issueOption }: IRootState) => ({
  issueOptionList: issueOption.entities,
  loading: issueOption.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(IssueOption);
