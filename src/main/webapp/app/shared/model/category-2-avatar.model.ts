import { Moment } from 'moment';
import { IAvatar } from 'app/shared/model/avatar.model';
import { Category2avatarStatus } from 'app/shared/model/enumerations/category-2-avatar-status.model';

export interface ICategory2avatar {
  id?: number;
  icon?: string;
  name?: string;
  description?: string;
  status?: Category2avatarStatus;
  created?: string;
  modified?: string;
  avatars?: IAvatar[];
}

export const defaultValue: Readonly<ICategory2avatar> = {};
