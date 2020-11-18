import { Moment } from 'moment';
import { ReportStatus } from 'app/shared/model/enumerations/report-status.model';

export interface IReport {
  id?: number;
  description?: string;
  name?: string;
  status?: ReportStatus;
  created?: string;
  modified?: string;
  youId?: number;
  meId?: number;
}

export const defaultValue: Readonly<IReport> = {};
