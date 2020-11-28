import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './issue.reducer';
import { IIssue } from 'app/shared/model/issue.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IIssueDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const IssueDetail = (props: IIssueDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { issueEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="pangeaApplicationApp.issue.detail.title">Issue</Translate> [<b>{issueEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="categoryName">
              <Translate contentKey="pangeaApplicationApp.issue.categoryName">Category Name</Translate>
            </span>
          </dt>
          <dd>{issueEntity.categoryName}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="pangeaApplicationApp.issue.name">Name</Translate>
            </span>
          </dt>
          <dd>{issueEntity.name}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="pangeaApplicationApp.issue.description">Description</Translate>
            </span>
          </dt>
          <dd>{issueEntity.description}</dd>
          <dt>
            <span id="text">
              <Translate contentKey="pangeaApplicationApp.issue.text">Text</Translate>
            </span>
          </dt>
          <dd>{issueEntity.text}</dd>
          <dt>
            <span id="coin">
              <Translate contentKey="pangeaApplicationApp.issue.coin">Coin</Translate>
            </span>
          </dt>
          <dd>{issueEntity.coin}</dd>
          <dt>
            <span id="point">
              <Translate contentKey="pangeaApplicationApp.issue.point">Point</Translate>
            </span>
          </dt>
          <dd>{issueEntity.point}</dd>
          <dt>
            <span id="like">
              <Translate contentKey="pangeaApplicationApp.issue.like">Like</Translate>
            </span>
          </dt>
          <dd>{issueEntity.like}</dd>
          <dt>
            <span id="dislike">
              <Translate contentKey="pangeaApplicationApp.issue.dislike">Dislike</Translate>
            </span>
          </dt>
          <dd>{issueEntity.dislike}</dd>
          <dt>
            <span id="author">
              <Translate contentKey="pangeaApplicationApp.issue.author">Author</Translate>
            </span>
          </dt>
          <dd>{issueEntity.author}</dd>
          <dt>
            <span id="views">
              <Translate contentKey="pangeaApplicationApp.issue.views">Views</Translate>
            </span>
          </dt>
          <dd>{issueEntity.views}</dd>
          <dt>
            <span id="comments">
              <Translate contentKey="pangeaApplicationApp.issue.comments">Comments</Translate>
            </span>
          </dt>
          <dd>{issueEntity.comments}</dd>
          <dt>
            <span id="status">
              <Translate contentKey="pangeaApplicationApp.issue.status">Status</Translate>
            </span>
          </dt>
          <dd>{issueEntity.status}</dd>
          <dt>
            <span id="created">
              <Translate contentKey="pangeaApplicationApp.issue.created">Created</Translate>
            </span>
          </dt>
          <dd>{issueEntity.created ? <TextFormat value={issueEntity.created} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="modified">
              <Translate contentKey="pangeaApplicationApp.issue.modified">Modified</Translate>
            </span>
          </dt>
          <dd>{issueEntity.modified ? <TextFormat value={issueEntity.modified} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <Translate contentKey="pangeaApplicationApp.issue.me">Me</Translate>
          </dt>
          <dd>{issueEntity.meId ? issueEntity.meId : ''}</dd>
        </dl>
        <Button tag={Link} to="/issue" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/issue/${issueEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ issue }: IRootState) => ({
  issueEntity: issue.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(IssueDetail);
