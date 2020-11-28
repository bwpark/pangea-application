import { Moment } from 'moment';
import { IIssueOption } from 'app/shared/model/issue-option.model';
import { IInteract } from 'app/shared/model/interact.model';
import { IIssueResource } from 'app/shared/model/issue-resource.model';
import { IEmotion } from 'app/shared/model/emotion.model';
import { IssueStatus } from 'app/shared/model/enumerations/issue-status.model';

export interface IIssue {
  id?: number;
  categoryName?: string;
  name?: string;
  description?: string;
  text?: any;
  coin?: number;
  point?: number;
  like?: number;
  dislike?: number;
  author?: string;
  views?: number;
  comments?: number;
  status?: IssueStatus;
  created?: string;
  modified?: string;
  options?: IIssueOption[];
  interacts?: IInteract[];
  resources?: IIssueResource[];
  emotions?: IEmotion[];
  categoryId?: number;
  meId?: number;
}

export const defaultValue: Readonly<IIssue> = {};
