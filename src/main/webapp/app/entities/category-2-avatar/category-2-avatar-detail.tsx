import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './category-2-avatar.reducer';
import { ICategory2avatar } from 'app/shared/model/category-2-avatar.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICategory2avatarDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const Category2avatarDetail = (props: ICategory2avatarDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { category2avatarEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="pangeaApplicationApp.category2avatar.detail.title">Category2avatar</Translate> [
          <b>{category2avatarEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="icon">
              <Translate contentKey="pangeaApplicationApp.category2avatar.icon">Icon</Translate>
            </span>
          </dt>
          <dd>{category2avatarEntity.icon}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="pangeaApplicationApp.category2avatar.name">Name</Translate>
            </span>
          </dt>
          <dd>{category2avatarEntity.name}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="pangeaApplicationApp.category2avatar.description">Description</Translate>
            </span>
          </dt>
          <dd>{category2avatarEntity.description}</dd>
          <dt>
            <span id="status">
              <Translate contentKey="pangeaApplicationApp.category2avatar.status">Status</Translate>
            </span>
          </dt>
          <dd>{category2avatarEntity.status}</dd>
          <dt>
            <span id="created">
              <Translate contentKey="pangeaApplicationApp.category2avatar.created">Created</Translate>
            </span>
          </dt>
          <dd>
            {category2avatarEntity.created ? (
              <TextFormat value={category2avatarEntity.created} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="modified">
              <Translate contentKey="pangeaApplicationApp.category2avatar.modified">Modified</Translate>
            </span>
          </dt>
          <dd>
            {category2avatarEntity.modified ? (
              <TextFormat value={category2avatarEntity.modified} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
        </dl>
        <Button tag={Link} to="/category-2-avatar" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/category-2-avatar/${category2avatarEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ category2avatar }: IRootState) => ({
  category2avatarEntity: category2avatar.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Category2avatarDetail);
