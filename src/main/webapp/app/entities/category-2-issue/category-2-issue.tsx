import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './category-2-issue.reducer';
import { ICategory2Issue } from 'app/shared/model/category-2-issue.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICategory2IssueProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Category2Issue = (props: ICategory2IssueProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { category2IssueList, match, loading } = props;
  return (
    <div>
      <h2 id="category-2-issue-heading">
        <Translate contentKey="pangeaApplicationApp.category2Issue.home.title">Category 2 Issues</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="pangeaApplicationApp.category2Issue.home.createLabel">Create new Category 2 Issue</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {category2IssueList && category2IssueList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.category2Issue.icon">Icon</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.category2Issue.name">Name</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.category2Issue.description">Description</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.category2Issue.status">Status</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.category2Issue.created">Created</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.category2Issue.modified">Modified</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {category2IssueList.map((category2Issue, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${category2Issue.id}`} color="link" size="sm">
                      {category2Issue.id}
                    </Button>
                  </td>
                  <td>{category2Issue.icon}</td>
                  <td>{category2Issue.name}</td>
                  <td>{category2Issue.description}</td>
                  <td>
                    <Translate contentKey={`pangeaApplicationApp.Category2IssueStatus.${category2Issue.status}`} />
                  </td>
                  <td>
                    {category2Issue.created ? <TextFormat type="date" value={category2Issue.created} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>
                    {category2Issue.modified ? <TextFormat type="date" value={category2Issue.modified} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${category2Issue.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${category2Issue.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${category2Issue.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="pangeaApplicationApp.category2Issue.home.notFound">No Category 2 Issues found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ category2Issue }: IRootState) => ({
  category2IssueList: category2Issue.entities,
  loading: category2Issue.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Category2Issue);
