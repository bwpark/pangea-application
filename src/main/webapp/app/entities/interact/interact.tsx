import React, { useState, useEffect } from 'react';
import InfiniteScroll from 'react-infinite-scroller';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { byteSize, Translate, ICrudGetAllAction, TextFormat, getSortState, IPaginationBaseState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities, reset } from './interact.reducer';
import { IInteract } from 'app/shared/model/interact.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';

export interface IInteractProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Interact = (props: IInteractProps) => {
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

  const { interactList, match, loading } = props;
  return (
    <div>
      <h2 id="interact-heading">
        <Translate contentKey="pangeaApplicationApp.interact.home.title">Interacts</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="pangeaApplicationApp.interact.home.createLabel">Create new Interact</Translate>
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
          {interactList && interactList.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th className="hand" onClick={sort('id')}>
                    <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('text')}>
                    <Translate contentKey="pangeaApplicationApp.interact.text">Text</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('coin')}>
                    <Translate contentKey="pangeaApplicationApp.interact.coin">Coin</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('point')}>
                    <Translate contentKey="pangeaApplicationApp.interact.point">Point</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('like')}>
                    <Translate contentKey="pangeaApplicationApp.interact.like">Like</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('dislike')}>
                    <Translate contentKey="pangeaApplicationApp.interact.dislike">Dislike</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('author')}>
                    <Translate contentKey="pangeaApplicationApp.interact.author">Author</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('status')}>
                    <Translate contentKey="pangeaApplicationApp.interact.status">Status</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('created')}>
                    <Translate contentKey="pangeaApplicationApp.interact.created">Created</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('modified')}>
                    <Translate contentKey="pangeaApplicationApp.interact.modified">Modified</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="pangeaApplicationApp.interact.you">You</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="pangeaApplicationApp.interact.issue">Issue</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="pangeaApplicationApp.interact.parent">Parent</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="pangeaApplicationApp.interact.me">Me</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {interactList.map((interact, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${interact.id}`} color="link" size="sm">
                        {interact.id}
                      </Button>
                    </td>
                    <td>{interact.text}</td>
                    <td>{interact.coin}</td>
                    <td>{interact.point}</td>
                    <td>{interact.like}</td>
                    <td>{interact.dislike}</td>
                    <td>{interact.author}</td>
                    <td>
                      <Translate contentKey={`pangeaApplicationApp.InteractStatus.${interact.status}`} />
                    </td>
                    <td>{interact.created ? <TextFormat type="date" value={interact.created} format={APP_DATE_FORMAT} /> : null}</td>
                    <td>{interact.modified ? <TextFormat type="date" value={interact.modified} format={APP_DATE_FORMAT} /> : null}</td>
                    <td>{interact.youId ? <Link to={`avatar/${interact.youId}`}>{interact.youId}</Link> : ''}</td>
                    <td>{interact.issueId ? <Link to={`issue/${interact.issueId}`}>{interact.issueId}</Link> : ''}</td>
                    <td>{interact.parentId ? <Link to={`interact/${interact.parentId}`}>{interact.parentId}</Link> : ''}</td>
                    <td>{interact.meId ? <Link to={`avatar/${interact.meId}`}>{interact.meId}</Link> : ''}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${interact.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.view">View</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${interact.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.edit">Edit</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${interact.id}/delete`} color="danger" size="sm">
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
                <Translate contentKey="pangeaApplicationApp.interact.home.notFound">No Interacts found</Translate>
              </div>
            )
          )}
        </InfiniteScroll>
      </div>
    </div>
  );
};

const mapStateToProps = ({ interact }: IRootState) => ({
  interactList: interact.entities,
  loading: interact.loading,
  totalItems: interact.totalItems,
  links: interact.links,
  entity: interact.entity,
  updateSuccess: interact.updateSuccess,
});

const mapDispatchToProps = {
  getEntities,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Interact);
