import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ICategory2Issue, defaultValue } from 'app/shared/model/category-2-issue.model';

export const ACTION_TYPES = {
  FETCH_CATEGORY2ISSUE_LIST: 'category2Issue/FETCH_CATEGORY2ISSUE_LIST',
  FETCH_CATEGORY2ISSUE: 'category2Issue/FETCH_CATEGORY2ISSUE',
  CREATE_CATEGORY2ISSUE: 'category2Issue/CREATE_CATEGORY2ISSUE',
  UPDATE_CATEGORY2ISSUE: 'category2Issue/UPDATE_CATEGORY2ISSUE',
  DELETE_CATEGORY2ISSUE: 'category2Issue/DELETE_CATEGORY2ISSUE',
  RESET: 'category2Issue/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ICategory2Issue>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type Category2IssueState = Readonly<typeof initialState>;

// Reducer

export default (state: Category2IssueState = initialState, action): Category2IssueState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_CATEGORY2ISSUE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_CATEGORY2ISSUE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_CATEGORY2ISSUE):
    case REQUEST(ACTION_TYPES.UPDATE_CATEGORY2ISSUE):
    case REQUEST(ACTION_TYPES.DELETE_CATEGORY2ISSUE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_CATEGORY2ISSUE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_CATEGORY2ISSUE):
    case FAILURE(ACTION_TYPES.CREATE_CATEGORY2ISSUE):
    case FAILURE(ACTION_TYPES.UPDATE_CATEGORY2ISSUE):
    case FAILURE(ACTION_TYPES.DELETE_CATEGORY2ISSUE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_CATEGORY2ISSUE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_CATEGORY2ISSUE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_CATEGORY2ISSUE):
    case SUCCESS(ACTION_TYPES.UPDATE_CATEGORY2ISSUE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_CATEGORY2ISSUE):
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

const apiUrl = 'api/category-2-issues';

// Actions

export const getEntities: ICrudGetAllAction<ICategory2Issue> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_CATEGORY2ISSUE_LIST,
  payload: axios.get<ICategory2Issue>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<ICategory2Issue> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_CATEGORY2ISSUE,
    payload: axios.get<ICategory2Issue>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<ICategory2Issue> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_CATEGORY2ISSUE,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ICategory2Issue> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_CATEGORY2ISSUE,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ICategory2Issue> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_CATEGORY2ISSUE,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
