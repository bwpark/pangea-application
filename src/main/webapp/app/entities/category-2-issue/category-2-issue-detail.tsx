import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './category-2-issue.reducer';
import { ICategory2Issue } from 'app/shared/model/category-2-issue.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICategory2IssueDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const Category2IssueDetail = (props: ICategory2IssueDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { category2IssueEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="pangeaApplicationApp.category2Issue.detail.title">Category2Issue</Translate> [
          <b>{category2IssueEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="icon">
              <Translate contentKey="pangeaApplicationApp.category2Issue.icon">Icon</Translate>
            </span>
          </dt>
          <dd>{category2IssueEntity.icon}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="pangeaApplicationApp.category2Issue.name">Name</Translate>
            </span>
          </dt>
          <dd>{category2IssueEntity.name}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="pangeaApplicationApp.category2Issue.description">Description</Translate>
            </span>
          </dt>
          <dd>{category2IssueEntity.description}</dd>
          <dt>
            <span id="status">
              <Translate contentKey="pangeaApplicationApp.category2Issue.status">Status</Translate>
            </span>
          </dt>
          <dd>{category2IssueEntity.status}</dd>
          <dt>
            <span id="created">
              <Translate contentKey="pangeaApplicationApp.category2Issue.created">Created</Translate>
            </span>
          </dt>
          <dd>
            {category2IssueEntity.created ? <TextFormat value={category2IssueEntity.created} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="modified">
              <Translate contentKey="pangeaApplicationApp.category2Issue.modified">Modified</Translate>
            </span>
          </dt>
          <dd>
            {category2IssueEntity.modified ? (
              <TextFormat value={category2IssueEntity.modified} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <Translate contentKey="pangeaApplicationApp.category2Issue.parent">Parent</Translate>
          </dt>
          <dd>{category2IssueEntity.parentId ? category2IssueEntity.parentId : ''}</dd>
        </dl>
        <Button tag={Link} to="/category-2-issue" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/category-2-issue/${category2IssueEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ category2Issue }: IRootState) => ({
  category2IssueEntity: category2Issue.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Category2IssueDetail);
