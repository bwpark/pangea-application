import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './report.reducer';
import { IReport } from 'app/shared/model/report.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IReportDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ReportDetail = (props: IReportDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { reportEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="pangeaApplicationApp.report.detail.title">Report</Translate> [<b>{reportEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="description">
              <Translate contentKey="pangeaApplicationApp.report.description">Description</Translate>
            </span>
          </dt>
          <dd>{reportEntity.description}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="pangeaApplicationApp.report.name">Name</Translate>
            </span>
          </dt>
          <dd>{reportEntity.name}</dd>
          <dt>
            <span id="status">
              <Translate contentKey="pangeaApplicationApp.report.status">Status</Translate>
            </span>
          </dt>
          <dd>{reportEntity.status}</dd>
          <dt>
            <span id="created">
              <Translate contentKey="pangeaApplicationApp.report.created">Created</Translate>
            </span>
          </dt>
          <dd>{reportEntity.created ? <TextFormat value={reportEntity.created} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="modified">
              <Translate contentKey="pangeaApplicationApp.report.modified">Modified</Translate>
            </span>
          </dt>
          <dd>{reportEntity.modified ? <TextFormat value={reportEntity.modified} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <Translate contentKey="pangeaApplicationApp.report.you">You</Translate>
          </dt>
          <dd>{reportEntity.youId ? reportEntity.youId : ''}</dd>
          <dt>
            <Translate contentKey="pangeaApplicationApp.report.me">Me</Translate>
          </dt>
          <dd>{reportEntity.meId ? reportEntity.meId : ''}</dd>
        </dl>
        <Button tag={Link} to="/report" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/report/${reportEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ report }: IRootState) => ({
  reportEntity: report.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ReportDetail);
