import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IRepute, defaultValue } from 'app/shared/model/repute.model';

export const ACTION_TYPES = {
  FETCH_REPUTE_LIST: 'repute/FETCH_REPUTE_LIST',
  FETCH_REPUTE: 'repute/FETCH_REPUTE',
  CREATE_REPUTE: 'repute/CREATE_REPUTE',
  UPDATE_REPUTE: 'repute/UPDATE_REPUTE',
  DELETE_REPUTE: 'repute/DELETE_REPUTE',
  RESET: 'repute/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IRepute>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type ReputeState = Readonly<typeof initialState>;

// Reducer

export default (state: ReputeState = initialState, action): ReputeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_REPUTE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_REPUTE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_REPUTE):
    case REQUEST(ACTION_TYPES.UPDATE_REPUTE):
    case REQUEST(ACTION_TYPES.DELETE_REPUTE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_REPUTE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_REPUTE):
    case FAILURE(ACTION_TYPES.CREATE_REPUTE):
    case FAILURE(ACTION_TYPES.UPDATE_REPUTE):
    case FAILURE(ACTION_TYPES.DELETE_REPUTE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_REPUTE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_REPUTE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_REPUTE):
    case SUCCESS(ACTION_TYPES.UPDATE_REPUTE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_REPUTE):
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

const apiUrl = 'api/reputes';

// Actions

export const getEntities: ICrudGetAllAction<IRepute> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_REPUTE_LIST,
  payload: axios.get<IRepute>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IRepute> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_REPUTE,
    payload: axios.get<IRepute>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IRepute> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_REPUTE,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IRepute> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_REPUTE,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IRepute> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_REPUTE,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
