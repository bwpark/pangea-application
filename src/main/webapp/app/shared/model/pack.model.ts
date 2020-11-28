import { Moment } from 'moment';
import { IDeal } from 'app/shared/model/deal.model';
import { PackStatus } from 'app/shared/model/enumerations/pack-status.model';

export interface IPack {
  id?: number;
  description?: string;
  coin?: number;
  point?: number;
  shipTo?: string;
  status?: PackStatus;
  created?: string;
  modified?: string;
  deals?: IDeal[];
  meId?: number;
}

export const defaultValue: Readonly<IPack> = {};
