import React, { useState, useEffect } from 'react';
import InfiniteScroll from 'react-infinite-scroller';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { openFile, byteSize, Translate, ICrudGetAllAction, TextFormat, getSortState, IPaginationBaseState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities, reset } from './avatar.reducer';
import { IAvatar } from 'app/shared/model/avatar.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';

export interface IAvatarProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Avatar = (props: IAvatarProps) => {
  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(props.location, ITEMS_PER_PAGE), props.location.search)
  );
  const [sorting, setSorting] = useState(false);

  const getAllEntities = () => {
    props.getEntities(paginationState.activePage - 1, paginationState.itemsPerPage, `${paginationState.sort},${paginationState.order}`);
  };

  const resetAll = () => {
    props.reset();
    setPaginationState({
      ...paginationState,
      activePage: 1,
    });
    props.getEntities();
  };

  useEffect(() => {
    resetAll();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      resetAll();
    }
  }, [props.updateSuccess]);

  useEffect(() => {
    getAllEntities();
  }, [paginationState.activePage]);

  const handleLoadMore = () => {
    if ((window as any).pageYOffset > 0) {
      setPaginationState({
        ...paginationState,
        activePage: paginationState.activePage + 1,
      });
    }
  };

  useEffect(() => {
    if (sorting) {
      getAllEntities();
      setSorting(false);
    }
  }, [sorting]);

  const sort = p => () => {
    props.reset();
    setPaginationState({
      ...paginationState,
      activePage: 1,
      order: paginationState.order === 'asc' ? 'desc' : 'asc',
      sort: p,
    });
    setSorting(true);
  };

  const { avatarList, match, loading } = props;
  return (
    <div>
      <h2 id="avatar-heading">
        <Translate contentKey="pangeaApplicationApp.avatar.home.title">Avatars</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="pangeaApplicationApp.avatar.home.createLabel">Create new Avatar</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        <InfiniteScroll
          pageStart={paginationState.activePage}
          loadMore={handleLoadMore}
          hasMore={paginationState.activePage - 1 < props.links.next}
          loader={<div className="loader">Loading ...</div>}
          threshold={0}
          initialLoad={false}
        >
          {avatarList && avatarList.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th className="hand" onClick={sort('id')}>
                    <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('categoryName')}>
                    <Translate contentKey="pangeaApplicationApp.avatar.categoryName">Category Name</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('name')}>
                    <Translate contentKey="pangeaApplicationApp.avatar.name">Name</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('description')}>
                    <Translate contentKey="pangeaApplicationApp.avatar.description">Description</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('text')}>
                    <Translate contentKey="pangeaApplicationApp.avatar.text">Text</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('logo')}>
                    <Translate contentKey="pangeaApplicationApp.avatar.logo">Logo</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('banner')}>
                    <Translate contentKey="pangeaApplicationApp.avatar.banner">Banner</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('coin')}>
                    <Translate contentKey="pangeaApplicationApp.avatar.coin">Coin</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('point')}>
                    <Translate contentKey="pangeaApplicationApp.avatar.point">Point</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('like')}>
                    <Translate contentKey="pangeaApplicationApp.avatar.like">Like</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('dislike')}>
                    <Translate contentKey="pangeaApplicationApp.avatar.dislike">Dislike</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('grade')}>
                    <Translate contentKey="pangeaApplicationApp.avatar.grade">Grade</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('credit')}>
                    <Translate contentKey="pangeaApplicationApp.avatar.credit">Credit</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('views')}>
                    <Translate contentKey="pangeaApplicationApp.avatar.views">Views</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('comments')}>
                    <Translate contentKey="pangeaApplicationApp.avatar.comments">Comments</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('status')}>
                    <Translate contentKey="pangeaApplicationApp.avatar.status">Status</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('created')}>
                    <Translate contentKey="pangeaApplicationApp.avatar.created">Created</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('modified')}>
                    <Translate contentKey="pangeaApplicationApp.avatar.modified">Modified</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="pangeaApplicationApp.avatar.user">User</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="pangeaApplicationApp.avatar.category">Category</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {avatarList.map((avatar, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${avatar.id}`} color="link" size="sm">
                        {avatar.id}
                      </Button>
                    </td>
                    <td>{avatar.categoryName}</td>
                    <td>{avatar.name}</td>
                    <td>{avatar.description}</td>
                    <td>{avatar.text}</td>
                    <td>
                      {avatar.logo ? (
                        <div>
                          {avatar.logoContentType ? (
                            <a onClick={openFile(avatar.logoContentType, avatar.logo)}>
                              <img src={`data:${avatar.logoContentType};base64,${avatar.logo}`} style={{ maxHeight: '30px' }} />
                              &nbsp;
                            </a>
                          ) : null}
                          <span>
                            {avatar.logoContentType}, {byteSize(avatar.logo)}
                          </span>
                        </div>
                      ) : null}
                    </td>
                    <td>
                      {avatar.banner ? (
                        <div>
                          {avatar.bannerContentType ? (
                            <a onClick={openFile(avatar.bannerContentType, avatar.banner)}>
                              <img src={`data:${avatar.bannerContentType};base64,${avatar.banner}`} style={{ maxHeight: '30px' }} />
                              &nbsp;
                            </a>
                          ) : null}
                          <span>
                            {avatar.bannerContentType}, {byteSize(avatar.banner)}
                          </span>
                        </div>
                      ) : null}
                    </td>
                    <td>{avatar.coin}</td>
                    <td>{avatar.point}</td>
                    <td>{avatar.like}</td>
                    <td>{avatar.dislike}</td>
                    <td>{avatar.grade}</td>
                    <td>{avatar.credit}</td>
                    <td>{avatar.views}</td>
                    <td>{avatar.comments}</td>
                    <td>
                      <Translate contentKey={`pangeaApplicationApp.AvatarStatus.${avatar.status}`} />
                    </td>
                    <td>{avatar.created ? <TextFormat type="date" value={avatar.created} format={APP_DATE_FORMAT} /> : null}</td>
                    <td>{avatar.modified ? <TextFormat type="date" value={avatar.modified} format={APP_DATE_FORMAT} /> : null}</td>
                    <td>{avatar.userId ? avatar.userId : ''}</td>
                    <td>{avatar.categoryId ? <Link to={`category-2-avatar/${avatar.categoryId}`}>{avatar.categoryId}</Link> : ''}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${avatar.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.view">View</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${avatar.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.edit">Edit</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${avatar.id}/delete`} color="danger" size="sm">
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
                <Translate contentKey="pangeaApplicationApp.avatar.home.notFound">No Avatars found</Translate>
              </div>
            )
          )}
        </InfiniteScroll>
      </div>
    </div>
  );
};

const mapStateToProps = ({ avatar }: IRootState) => ({
  avatarList: avatar.entities,
  loading: avatar.loading,
  totalItems: avatar.totalItems,
  links: avatar.links,
  entity: avatar.entity,
  updateSuccess: avatar.updateSuccess,
});

const mapDispatchToProps = {
  getEntities,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Avatar);
