import { combineReducers } from 'redux';
import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import locale, { LocaleState } from './locale';
import authentication, { AuthenticationState } from './authentication';
import applicationProfile, { ApplicationProfileState } from './application-profile';

import administration, { AdministrationState } from 'app/modules/administration/administration.reducer';
import userManagement, { UserManagementState } from 'app/modules/administration/user-management/user-management.reducer';
import register, { RegisterState } from 'app/modules/account/register/register.reducer';
import activate, { ActivateState } from 'app/modules/account/activate/activate.reducer';
import password, { PasswordState } from 'app/modules/account/password/password.reducer';
import settings, { SettingsState } from 'app/modules/account/settings/settings.reducer';
import passwordReset, { PasswordResetState } from 'app/modules/account/password-reset/password-reset.reducer';
// prettier-ignore
import tOS, {
  TOSState
} from 'app/entities/tos/tos.reducer';
// prettier-ignore
import category, {
  CategoryState
} from 'app/entities/category/category.reducer';
// prettier-ignore
import avatar, {
  AvatarState
} from 'app/entities/avatar/avatar.reducer';
// prettier-ignore
import issue, {
  IssueState
} from 'app/entities/issue/issue.reducer';
// prettier-ignore
import issueOption, {
  IssueOptionState
} from 'app/entities/issue-option/issue-option.reducer';
// prettier-ignore
import issueResource, {
  IssueResourceState
} from 'app/entities/issue-resource/issue-resource.reducer';
// prettier-ignore
import interact, {
  InteractState
} from 'app/entities/interact/interact.reducer';
// prettier-ignore
import emotion, {
  EmotionState
} from 'app/entities/emotion/emotion.reducer';
// prettier-ignore
import repute, {
  ReputeState
} from 'app/entities/repute/repute.reducer';
// prettier-ignore
import regular, {
  RegularState
} from 'app/entities/regular/regular.reducer';
// prettier-ignore
import report, {
  ReportState
} from 'app/entities/report/report.reducer';
// prettier-ignore
import chemistry, {
  ChemistryState
} from 'app/entities/chemistry/chemistry.reducer';
// prettier-ignore
import pack, {
  PackState
} from 'app/entities/pack/pack.reducer';
// prettier-ignore
import deal, {
  DealState
} from 'app/entities/deal/deal.reducer';
// prettier-ignore
import dealOption, {
  DealOptionState
} from 'app/entities/deal-option/deal-option.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

export interface IRootState {
  readonly authentication: AuthenticationState;
  readonly locale: LocaleState;
  readonly applicationProfile: ApplicationProfileState;
  readonly administration: AdministrationState;
  readonly userManagement: UserManagementState;
  readonly register: RegisterState;
  readonly activate: ActivateState;
  readonly passwordReset: PasswordResetState;
  readonly password: PasswordState;
  readonly settings: SettingsState;
  readonly tOS: TOSState;
  readonly category: CategoryState;
  readonly avatar: AvatarState;
  readonly issue: IssueState;
  readonly issueOption: IssueOptionState;
  readonly issueResource: IssueResourceState;
  readonly interact: InteractState;
  readonly emotion: EmotionState;
  readonly repute: ReputeState;
  readonly regular: RegularState;
  readonly report: ReportState;
  readonly chemistry: ChemistryState;
  readonly pack: PackState;
  readonly deal: DealState;
  readonly dealOption: DealOptionState;
  /* jhipster-needle-add-reducer-type - JHipster will add reducer type here */
  readonly loadingBar: any;
}

const rootReducer = combineReducers<IRootState>({
  authentication,
  locale,
  applicationProfile,
  administration,
  userManagement,
  register,
  activate,
  passwordReset,
  password,
  settings,
  tOS,
  category,
  avatar,
  issue,
  issueOption,
  issueResource,
  interact,
  emotion,
  repute,
  regular,
  report,
  chemistry,
  pack,
  deal,
  dealOption,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar,
});

export default rootReducer;
