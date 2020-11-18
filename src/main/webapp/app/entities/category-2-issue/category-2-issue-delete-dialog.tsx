import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import { Translate, ICrudGetAction, ICrudDeleteAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { ICategory2Issue } from 'app/shared/model/category-2-issue.model';
import { IRootState } from 'app/shared/reducers';
import { getEntity, deleteEntity } from './category-2-issue.reducer';

export interface ICategory2IssueDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const Category2IssueDeleteDialog = (props: ICategory2IssueDeleteDialogProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const handleClose = () => {
    props.history.push('/category-2-issue');
  };

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const confirmDelete = () => {
    props.deleteEntity(props.category2IssueEntity.id);
  };

  const { category2IssueEntity } = props;
  return (
    <Modal isOpen toggle={handleClose}>
      <ModalHeader toggle={handleClose}>
        <Translate contentKey="entity.delete.title">Confirm delete operation</Translate>
      </ModalHeader>
      <ModalBody id="pangeaApplicationApp.category2Issue.delete.question">
        <Translate contentKey="pangeaApplicationApp.category2Issue.delete.question" interpolate={{ id: category2IssueEntity.id }}>
          Are you sure you want to delete this Category2Issue?
        </Translate>
      </ModalBody>
      <ModalFooter>
        <Button color="secondary" onClick={handleClose}>
          <FontAwesomeIcon icon="ban" />
          &nbsp;
          <Translate contentKey="entity.action.cancel">Cancel</Translate>
        </Button>
        <Button id="jhi-confirm-delete-category2Issue" color="danger" onClick={confirmDelete}>
          <FontAwesomeIcon icon="trash" />
          &nbsp;
          <Translate contentKey="entity.action.delete">Delete</Translate>
        </Button>
      </ModalFooter>
    </Modal>
  );
};

const mapStateToProps = ({ category2Issue }: IRootState) => ({
  category2IssueEntity: category2Issue.entity,
  updateSuccess: category2Issue.updateSuccess,
});

const mapDispatchToProps = { getEntity, deleteEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Category2IssueDeleteDialog);
