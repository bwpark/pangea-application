import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './issue-option.reducer';
import { IIssueOption } from 'app/shared/model/issue-option.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IIssueOptionDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const IssueOptionDetail = (props: IIssueOptionDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { issueOptionEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="pangeaApplicationApp.issueOption.detail.title">IssueOption</Translate> [<b>{issueOptionEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="name">
              <Translate contentKey="pangeaApplicationApp.issueOption.name">Name</Translate>
            </span>
          </dt>
          <dd>{issueOptionEntity.name}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="pangeaApplicationApp.issueOption.description">Description</Translate>
            </span>
          </dt>
          <dd>{issueOptionEntity.description}</dd>
          <dt>
            <span id="coin">
              <Translate contentKey="pangeaApplicationApp.issueOption.coin">Coin</Translate>
            </span>
          </dt>
          <dd>{issueOptionEntity.coin}</dd>
          <dt>
            <span id="point">
              <Translate contentKey="pangeaApplicationApp.issueOption.point">Point</Translate>
            </span>
          </dt>
          <dd>{issueOptionEntity.point}</dd>
          <dt>
            <span id="status">
              <Translate contentKey="pangeaApplicationApp.issueOption.status">Status</Translate>
            </span>
          </dt>
          <dd>{issueOptionEntity.status}</dd>
          <dt>
            <span id="created">
              <Translate contentKey="pangeaApplicationApp.issueOption.created">Created</Translate>
            </span>
          </dt>
          <dd>
            {issueOptionEntity.created ? <TextFormat value={issueOptionEntity.created} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="modified">
              <Translate contentKey="pangeaApplicationApp.issueOption.modified">Modified</Translate>
            </span>
          </dt>
          <dd>
            {issueOptionEntity.modified ? <TextFormat value={issueOptionEntity.modified} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <Translate contentKey="pangeaApplicationApp.issueOption.issue">Issue</Translate>
          </dt>
          <dd>{issueOptionEntity.issueId ? issueOptionEntity.issueId : ''}</dd>
        </dl>
        <Button tag={Link} to="/issue-option" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/issue-option/${issueOptionEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ issueOption }: IRootState) => ({
  issueOptionEntity: issueOption.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(IssueOptionDetail);
