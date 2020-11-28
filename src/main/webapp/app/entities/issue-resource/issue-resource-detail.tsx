import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './issue-resource.reducer';
import { IIssueResource } from 'app/shared/model/issue-resource.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IIssueResourceDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const IssueResourceDetail = (props: IIssueResourceDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { issueResourceEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="pangeaApplicationApp.issueResource.detail.title">IssueResource</Translate> [<b>{issueResourceEntity.id}</b>
          ]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="type">
              <Translate contentKey="pangeaApplicationApp.issueResource.type">Type</Translate>
            </span>
          </dt>
          <dd>{issueResourceEntity.type}</dd>
          <dt>
            <span id="link">
              <Translate contentKey="pangeaApplicationApp.issueResource.link">Link</Translate>
            </span>
          </dt>
          <dd>{issueResourceEntity.link}</dd>
          <dt>
            <span id="created">
              <Translate contentKey="pangeaApplicationApp.issueResource.created">Created</Translate>
            </span>
          </dt>
          <dd>
            {issueResourceEntity.created ? <TextFormat value={issueResourceEntity.created} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <Translate contentKey="pangeaApplicationApp.issueResource.issue">Issue</Translate>
          </dt>
          <dd>{issueResourceEntity.issueId ? issueResourceEntity.issueId : ''}</dd>
        </dl>
        <Button tag={Link} to="/issue-resource" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/issue-resource/${issueResourceEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ issueResource }: IRootState) => ({
  issueResourceEntity: issueResource.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(IssueResourceDetail);
