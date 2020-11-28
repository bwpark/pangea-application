import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IDeal, defaultValue } from 'app/shared/model/deal.model';

export const ACTION_TYPES = {
  FETCH_DEAL_LIST: 'deal/FETCH_DEAL_LIST',
  FETCH_DEAL: 'deal/FETCH_DEAL',
  CREATE_DEAL: 'deal/CREATE_DEAL',
  UPDATE_DEAL: 'deal/UPDATE_DEAL',
  DELETE_DEAL: 'deal/DELETE_DEAL',
  RESET: 'deal/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IDeal>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type DealState = Readonly<typeof initialState>;

// Reducer

export default (state: DealState = initialState, action): DealState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_DEAL_LIST):
    case REQUEST(ACTION_TYPES.FETCH_DEAL):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_DEAL):
    case REQUEST(ACTION_TYPES.UPDATE_DEAL):
    case REQUEST(ACTION_TYPES.DELETE_DEAL):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_DEAL_LIST):
    case FAILURE(ACTION_TYPES.FETCH_DEAL):
    case FAILURE(ACTION_TYPES.CREATE_DEAL):
    case FAILURE(ACTION_TYPES.UPDATE_DEAL):
    case FAILURE(ACTION_TYPES.DELETE_DEAL):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_DEAL_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_DEAL):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_DEAL):
    case SUCCESS(ACTION_TYPES.UPDATE_DEAL):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_DEAL):
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

const apiUrl = 'api/deals';

// Actions

export const getEntities: ICrudGetAllAction<IDeal> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_DEAL_LIST,
  payload: axios.get<IDeal>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IDeal> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_DEAL,
    payload: axios.get<IDeal>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IDeal> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_DEAL,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IDeal> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_DEAL,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IDeal> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_DEAL,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
