import { Moment } from 'moment';
import { ReputeStatus } from 'app/shared/model/enumerations/repute-status.model';

export interface IRepute {
  id?: number;
  description?: string;
  grade?: number;
  credit?: number;
  status?: ReputeStatus;
  created?: string;
  modified?: string;
  youId?: number;
  meId?: number;
}

export const defaultValue: Readonly<IRepute> = {};
