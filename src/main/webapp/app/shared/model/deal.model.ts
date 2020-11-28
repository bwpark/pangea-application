import { Moment } from 'moment';
import { IDealOption } from 'app/shared/model/deal-option.model';
import { DealStatus } from 'app/shared/model/enumerations/deal-status.model';

export interface IDeal {
  id?: number;
  name?: string;
  description?: string;
  quantity?: number;
  unitPrice?: number;
  coin?: number;
  point?: number;
  status?: DealStatus;
  created?: string;
  modified?: string;
  deals?: IDealOption[];
  withId?: number;
  packId?: number;
  toId?: number;
}

export const defaultValue: Readonly<IDeal> = {};
