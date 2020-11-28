import { Moment } from 'moment';
import { IssueOptionStatus } from 'app/shared/model/enumerations/issue-option-status.model';

export interface IIssueOption {
  id?: number;
  name?: string;
  description?: string;
  coin?: number;
  point?: number;
  status?: IssueOptionStatus;
  created?: string;
  modified?: string;
  issueId?: number;
}

export const defaultValue: Readonly<IIssueOption> = {};
