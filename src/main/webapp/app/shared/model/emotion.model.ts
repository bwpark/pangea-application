import { Moment } from 'moment';
import { EmotionStatus } from 'app/shared/model/enumerations/emotion-status.model';

export interface IEmotion {
  id?: number;
  status?: EmotionStatus;
  created?: string;
  modified?: string;
  youId?: number;
  issueId?: number;
  meId?: number;
}

export const defaultValue: Readonly<IEmotion> = {};
