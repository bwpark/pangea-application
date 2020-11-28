import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IDealOption, defaultValue } from 'app/shared/model/deal-option.model';

export const ACTION_TYPES = {
  FETCH_DEALOPTION_LIST: 'dealOption/FETCH_DEALOPTION_LIST',
  FETCH_DEALOPTION: 'dealOption/FETCH_DEALOPTION',
  CREATE_DEALOPTION: 'dealOption/CREATE_DEALOPTION',
  UPDATE_DEALOPTION: 'dealOption/UPDATE_DEALOPTION',
  DELETE_DEALOPTION: 'dealOption/DELETE_DEALOPTION',
  RESET: 'dealOption/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IDealOption>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type DealOptionState = Readonly<typeof initialState>;

// Reducer

export default (state: DealOptionState = initialState, action): DealOptionState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_DEALOPTION_LIST):
    case REQUEST(ACTION_TYPES.FETCH_DEALOPTION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_DEALOPTION):
    case REQUEST(ACTION_TYPES.UPDATE_DEALOPTION):
    case REQUEST(ACTION_TYPES.DELETE_DEALOPTION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_DEALOPTION_LIST):
    case FAILURE(ACTION_TYPES.FETCH_DEALOPTION):
    case FAILURE(ACTION_TYPES.CREATE_DEALOPTION):
    case FAILURE(ACTION_TYPES.UPDATE_DEALOPTION):
    case FAILURE(ACTION_TYPES.DELETE_DEALOPTION):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_DEALOPTION_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_DEALOPTION):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_DEALOPTION):
    case SUCCESS(ACTION_TYPES.UPDATE_DEALOPTION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_DEALOPTION):
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

const apiUrl = 'api/deal-options';

// Actions

export const getEntities: ICrudGetAllAction<IDealOption> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_DEALOPTION_LIST,
  payload: axios.get<IDealOption>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IDealOption> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_DEALOPTION,
    payload: axios.get<IDealOption>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IDealOption> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_DEALOPTION,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IDealOption> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_DEALOPTION,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IDealOption> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_DEALOPTION,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
