import { Moment } from 'moment';
import { ResourceType } from 'app/shared/model/enumerations/resource-type.model';

export interface IIssueResource {
  id?: number;
  type?: ResourceType;
  link?: string;
  created?: string;
  issueId?: number;
}

export const defaultValue: Readonly<IIssueResource> = {};
