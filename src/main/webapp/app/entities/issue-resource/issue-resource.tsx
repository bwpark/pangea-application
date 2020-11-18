import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './issue-resource.reducer';
import { IIssueResource } from 'app/shared/model/issue-resource.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IIssueResourceProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const IssueResource = (props: IIssueResourceProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { issueResourceList, match, loading } = props;
  return (
    <div>
      <h2 id="issue-resource-heading">
        <Translate contentKey="pangeaApplicationApp.issueResource.home.title">Issue Resources</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="pangeaApplicationApp.issueResource.home.createLabel">Create new Issue Resource</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {issueResourceList && issueResourceList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.issueResource.type">Type</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.issueResource.link">Link</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.issueResource.created">Created</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.issueResource.issue">Issue</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {issueResourceList.map((issueResource, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${issueResource.id}`} color="link" size="sm">
                      {issueResource.id}
                    </Button>
                  </td>
                  <td>
                    <Translate contentKey={`pangeaApplicationApp.ResourceType.${issueResource.type}`} />
                  </td>
                  <td>{issueResource.link}</td>
                  <td>
                    {issueResource.created ? <TextFormat type="date" value={issueResource.created} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>{issueResource.issueId ? <Link to={`issue/${issueResource.issueId}`}>{issueResource.issueId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${issueResource.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${issueResource.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${issueResource.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="pangeaApplicationApp.issueResource.home.notFound">No Issue Resources found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ issueResource }: IRootState) => ({
  issueResourceList: issueResource.entities,
  loading: issueResource.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(IssueResource);
