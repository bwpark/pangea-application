import { Moment } from 'moment';

export interface IChemistry {
  id?: number;
  yourName?: string;
  toYou?: number;
  toMe?: number;
  created?: string;
  youId?: number;
  meId?: number;
}

export const defaultValue: Readonly<IChemistry> = {};
