import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import { Translate, ICrudGetAction, ICrudDeleteAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { ICategory2avatar } from 'app/shared/model/category-2-avatar.model';
import { IRootState } from 'app/shared/reducers';
import { getEntity, deleteEntity } from './category-2-avatar.reducer';

export interface ICategory2avatarDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const Category2avatarDeleteDialog = (props: ICategory2avatarDeleteDialogProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const handleClose = () => {
    props.history.push('/category-2-avatar');
  };

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const confirmDelete = () => {
    props.deleteEntity(props.category2avatarEntity.id);
  };

  const { category2avatarEntity } = props;
  return (
    <Modal isOpen toggle={handleClose}>
      <ModalHeader toggle={handleClose}>
        <Translate contentKey="entity.delete.title">Confirm delete operation</Translate>
      </ModalHeader>
      <ModalBody id="pangeaApplicationApp.category2avatar.delete.question">
        <Translate contentKey="pangeaApplicationApp.category2avatar.delete.question" interpolate={{ id: category2avatarEntity.id }}>
          Are you sure you want to delete this Category2avatar?
        </Translate>
      </ModalBody>
      <ModalFooter>
        <Button color="secondary" onClick={handleClose}>
          <FontAwesomeIcon icon="ban" />
          &nbsp;
          <Translate contentKey="entity.action.cancel">Cancel</Translate>
        </Button>
        <Button id="jhi-confirm-delete-category2avatar" color="danger" onClick={confirmDelete}>
          <FontAwesomeIcon icon="trash" />
          &nbsp;
          <Translate contentKey="entity.action.delete">Delete</Translate>
        </Button>
      </ModalFooter>
    </Modal>
  );
};

const mapStateToProps = ({ category2avatar }: IRootState) => ({
  category2avatarEntity: category2avatar.entity,
  updateSuccess: category2avatar.updateSuccess,
});

const mapDispatchToProps = { getEntity, deleteEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Category2avatarDeleteDialog);
