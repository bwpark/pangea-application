import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './category-2-avatar.reducer';
import { ICategory2avatar } from 'app/shared/model/category-2-avatar.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICategory2avatarProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Category2avatar = (props: ICategory2avatarProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { category2avatarList, match, loading } = props;
  return (
    <div>
      <h2 id="category-2-avatar-heading">
        <Translate contentKey="pangeaApplicationApp.category2avatar.home.title">Category 2 Avatars</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="pangeaApplicationApp.category2avatar.home.createLabel">Create new Category 2 Avatar</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {category2avatarList && category2avatarList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.category2avatar.icon">Icon</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.category2avatar.name">Name</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.category2avatar.description">Description</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.category2avatar.status">Status</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.category2avatar.created">Created</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.category2avatar.modified">Modified</Translate>
                </th>
                <th>
                  <Translate contentKey="pangeaApplicationApp.category2avatar.parent">Parent</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {category2avatarList.map((category2avatar, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${category2avatar.id}`} color="link" size="sm">
                      {category2avatar.id}
                    </Button>
                  </td>
                  <td>{category2avatar.icon}</td>
                  <td>{category2avatar.name}</td>
                  <td>{category2avatar.description}</td>
                  <td>
                    <Translate contentKey={`pangeaApplicationApp.Category2avatarStatus.${category2avatar.status}`} />
                  </td>
                  <td>
                    {category2avatar.created ? <TextFormat type="date" value={category2avatar.created} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>
                    {category2avatar.modified ? <TextFormat type="date" value={category2avatar.modified} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>
                    {category2avatar.parentId ? (
                      <Link to={`category-2-avatar/${category2avatar.parentId}`}>{category2avatar.parentId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${category2avatar.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${category2avatar.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${category2avatar.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="pangeaApplicationApp.category2avatar.home.notFound">No Category 2 Avatars found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ category2avatar }: IRootState) => ({
  category2avatarList: category2avatar.entities,
  loading: category2avatar.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Category2avatar);
