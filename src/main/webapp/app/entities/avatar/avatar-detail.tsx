import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, openFile, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './avatar.reducer';
import { IAvatar } from 'app/shared/model/avatar.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAvatarDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const AvatarDetail = (props: IAvatarDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { avatarEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="pangeaApplicationApp.avatar.detail.title">Avatar</Translate> [<b>{avatarEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="categoryName">
              <Translate contentKey="pangeaApplicationApp.avatar.categoryName">Category Name</Translate>
            </span>
          </dt>
          <dd>{avatarEntity.categoryName}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="pangeaApplicationApp.avatar.name">Name</Translate>
            </span>
          </dt>
          <dd>{avatarEntity.name}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="pangeaApplicationApp.avatar.description">Description</Translate>
            </span>
          </dt>
          <dd>{avatarEntity.description}</dd>
          <dt>
            <span id="text">
              <Translate contentKey="pangeaApplicationApp.avatar.text">Text</Translate>
            </span>
          </dt>
          <dd>{avatarEntity.text}</dd>
          <dt>
            <span id="logo">
              <Translate contentKey="pangeaApplicationApp.avatar.logo">Logo</Translate>
            </span>
          </dt>
          <dd>
            {avatarEntity.logo ? (
              <div>
                {avatarEntity.logoContentType ? (
                  <a onClick={openFile(avatarEntity.logoContentType, avatarEntity.logo)}>
                    <img src={`data:${avatarEntity.logoContentType};base64,${avatarEntity.logo}`} style={{ maxHeight: '30px' }} />
                  </a>
                ) : null}
                <span>
                  {avatarEntity.logoContentType}, {byteSize(avatarEntity.logo)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="banner">
              <Translate contentKey="pangeaApplicationApp.avatar.banner">Banner</Translate>
            </span>
          </dt>
          <dd>
            {avatarEntity.banner ? (
              <div>
                {avatarEntity.bannerContentType ? (
                  <a onClick={openFile(avatarEntity.bannerContentType, avatarEntity.banner)}>
                    <img src={`data:${avatarEntity.bannerContentType};base64,${avatarEntity.banner}`} style={{ maxHeight: '30px' }} />
                  </a>
                ) : null}
                <span>
                  {avatarEntity.bannerContentType}, {byteSize(avatarEntity.banner)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="coin">
              <Translate contentKey="pangeaApplicationApp.avatar.coin">Coin</Translate>
            </span>
          </dt>
          <dd>{avatarEntity.coin}</dd>
          <dt>
            <span id="point">
              <Translate contentKey="pangeaApplicationApp.avatar.point">Point</Translate>
            </span>
          </dt>
          <dd>{avatarEntity.point}</dd>
          <dt>
            <span id="like">
              <Translate contentKey="pangeaApplicationApp.avatar.like">Like</Translate>
            </span>
          </dt>
          <dd>{avatarEntity.like}</dd>
          <dt>
            <span id="dislike">
              <Translate contentKey="pangeaApplicationApp.avatar.dislike">Dislike</Translate>
            </span>
          </dt>
          <dd>{avatarEntity.dislike}</dd>
          <dt>
            <span id="grade">
              <Translate contentKey="pangeaApplicationApp.avatar.grade">Grade</Translate>
            </span>
          </dt>
          <dd>{avatarEntity.grade}</dd>
          <dt>
            <span id="credit">
              <Translate contentKey="pangeaApplicationApp.avatar.credit">Credit</Translate>
            </span>
          </dt>
          <dd>{avatarEntity.credit}</dd>
          <dt>
            <span id="views">
              <Translate contentKey="pangeaApplicationApp.avatar.views">Views</Translate>
            </span>
          </dt>
          <dd>{avatarEntity.views}</dd>
          <dt>
            <span id="comments">
              <Translate contentKey="pangeaApplicationApp.avatar.comments">Comments</Translate>
            </span>
          </dt>
          <dd>{avatarEntity.comments}</dd>
          <dt>
            <span id="status">
              <Translate contentKey="pangeaApplicationApp.avatar.status">Status</Translate>
            </span>
          </dt>
          <dd>{avatarEntity.status}</dd>
          <dt>
            <span id="created">
              <Translate contentKey="pangeaApplicationApp.avatar.created">Created</Translate>
            </span>
          </dt>
          <dd>{avatarEntity.created ? <TextFormat value={avatarEntity.created} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="modified">
              <Translate contentKey="pangeaApplicationApp.avatar.modified">Modified</Translate>
            </span>
          </dt>
          <dd>{avatarEntity.modified ? <TextFormat value={avatarEntity.modified} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <Translate contentKey="pangeaApplicationApp.avatar.user">User</Translate>
          </dt>
          <dd>{avatarEntity.userId ? avatarEntity.userId : ''}</dd>
          <dt>
            <Translate contentKey="pangeaApplicationApp.avatar.category">Category</Translate>
          </dt>
          <dd>{avatarEntity.categoryId ? avatarEntity.categoryId : ''}</dd>
        </dl>
        <Button tag={Link} to="/avatar" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/avatar/${avatarEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ avatar }: IRootState) => ({
  avatarEntity: avatar.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AvatarDetail);
