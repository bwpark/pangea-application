import { Moment } from 'moment';
import { Category2IssueStatus } from 'app/shared/model/enumerations/category-2-issue-status.model';

export interface ICategory2Issue {
  id?: number;
  icon?: string;
  name?: string;
  description?: string;
  status?: Category2IssueStatus;
  created?: string;
  modified?: string;
}

export const defaultValue: Readonly<ICategory2Issue> = {};
