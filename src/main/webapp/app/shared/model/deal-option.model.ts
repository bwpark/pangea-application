import { Moment } from 'moment';
import { DealOptionStatus } from 'app/shared/model/enumerations/deal-option-status.model';

export interface IDealOption {
  id?: number;
  name?: string;
  status?: DealOptionStatus;
  created?: string;
  modified?: string;
  packId?: number;
}

export const defaultValue: Readonly<IDealOption> = {};
