import { Moment } from 'moment';
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
  parentId?: number;
}

export const defaultValue: Readonly<ICategory> = {};
