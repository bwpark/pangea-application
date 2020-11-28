import { Moment } from 'moment';
import { RegularStatus } from 'app/shared/model/enumerations/regular-status.model';

export interface IRegular {
  id?: number;
  name?: string;
  status?: RegularStatus;
  created?: string;
  modified?: string;
  youId?: number;
  meId?: number;
}

export const defaultValue: Readonly<IRegular> = {};
