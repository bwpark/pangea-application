import { Moment } from 'moment';
import { InteractStatus } from 'app/shared/model/enumerations/interact-status.model';

export interface IInteract {
  id?: number;
  text?: any;
  coin?: number;
  point?: number;
  like?: number;
  dislike?: number;
  author?: string;
  status?: InteractStatus;
  created?: string;
  modified?: string;
  children?: IInteract[];
  youId?: number;
  issueId?: number;
  parentId?: number;
  meId?: number;
}

export const defaultValue: Readonly<IInteract> = {};
