import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IIssueOption, defaultValue } from 'app/shared/model/issue-option.model';

export const ACTION_TYPES = {
  FETCH_ISSUEOPTION_LIST: 'issueOption/FETCH_ISSUEOPTION_LIST',
  FETCH_ISSUEOPTION: 'issueOption/FETCH_ISSUEOPTION',
  CREATE_ISSUEOPTION: 'issueOption/CREATE_ISSUEOPTION',
  UPDATE_ISSUEOPTION: 'issueOption/UPDATE_ISSUEOPTION',
  DELETE_ISSUEOPTION: 'issueOption/DELETE_ISSUEOPTION',
  RESET: 'issueOption/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IIssueOption>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type IssueOptionState = Readonly<typeof initialState>;

// Reducer

export default (state: IssueOptionState = initialState, action): IssueOptionState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ISSUEOPTION_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ISSUEOPTION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_ISSUEOPTION):
    case REQUEST(ACTION_TYPES.UPDATE_ISSUEOPTION):
    case REQUEST(ACTION_TYPES.DELETE_ISSUEOPTION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_ISSUEOPTION_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ISSUEOPTION):
    case FAILURE(ACTION_TYPES.CREATE_ISSUEOPTION):
    case FAILURE(ACTION_TYPES.UPDATE_ISSUEOPTION):
    case FAILURE(ACTION_TYPES.DELETE_ISSUEOPTION):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_ISSUEOPTION_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_ISSUEOPTION):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_ISSUEOPTION):
    case SUCCESS(ACTION_TYPES.UPDATE_ISSUEOPTION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_ISSUEOPTION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {},
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

const apiUrl = 'api/issue-options';

// Actions

export const getEntities: ICrudGetAllAction<IIssueOption> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_ISSUEOPTION_LIST,
  payload: axios.get<IIssueOption>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IIssueOption> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ISSUEOPTION,
    payload: axios.get<IIssueOption>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IIssueOption> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ISSUEOPTION,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IIssueOption> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ISSUEOPTION,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IIssueOption> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ISSUEOPTION,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
