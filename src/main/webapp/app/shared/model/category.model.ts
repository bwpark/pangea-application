import { Moment } from 'moment';
import { IAvatar } from 'app/shared/model/avatar.model';
import { IIssue } from 'app/shared/model/issue.model';
import { CategoryStatus } from 'app/shared/model/enumerations/category-status.model';

export interface ICategory {
  id?: number;
  icon?: string;
  path?: string;
  name?: string;
  description?: string;
  hitAndSort?: number;
  status?: CategoryStatus;
  created?: string;
  modified?: string;
  children?: ICategory[];
  avatars?: IAvatar[];
  issues?: IIssue[];
  parentId?: number;
}

export const defaultValue: Readonly<ICategory> = {};
