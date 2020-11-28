import { Moment } from 'moment';
import { IIssue } from 'app/shared/model/issue.model';
import { IInteract } from 'app/shared/model/interact.model';
import { IEmotion } from 'app/shared/model/emotion.model';
import { IRepute } from 'app/shared/model/repute.model';
import { IRegular } from 'app/shared/model/regular.model';
import { IReport } from 'app/shared/model/report.model';
import { IChemistry } from 'app/shared/model/chemistry.model';
import { IPack } from 'app/shared/model/pack.model';
import { IDeal } from 'app/shared/model/deal.model';
import { AvatarStatus } from 'app/shared/model/enumerations/avatar-status.model';

export interface IAvatar {
  id?: number;
  categoryName?: string;
  name?: string;
  description?: string;
  text?: any;
  logoContentType?: string;
  logo?: any;
  bannerContentType?: string;
  banner?: any;
  coin?: number;
  point?: number;
  like?: number;
  dislike?: number;
  grade?: number;
  credit?: number;
  views?: number;
  comments?: number;
  status?: AvatarStatus;
  created?: string;
  modified?: string;
  issues?: IIssue[];
  interacts?: IInteract[];
  emotions?: IEmotion[];
  reputes?: IRepute[];
  regulars?: IRegular[];
  reports?: IReport[];
  chemistries?: IChemistry[];
  buys?: IPack[];
  sales?: IDeal[];
  categoryId?: number;
  userId?: number;
}

export const defaultValue: Readonly<IAvatar> = {};
