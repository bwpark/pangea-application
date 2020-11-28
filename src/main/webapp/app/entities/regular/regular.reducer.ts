import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IRegular, defaultValue } from 'app/shared/model/regular.model';

export const ACTION_TYPES = {
  FETCH_REGULAR_LIST: 'regular/FETCH_REGULAR_LIST',
  FETCH_REGULAR: 'regular/FETCH_REGULAR',
  CREATE_REGULAR: 'regular/CREATE_REGULAR',
  UPDATE_REGULAR: 'regular/UPDATE_REGULAR',
  DELETE_REGULAR: 'regular/DELETE_REGULAR',
  RESET: 'regular/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IRegular>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type RegularState = Readonly<typeof initialState>;

// Reducer

export default (state: RegularState = initialState, action): RegularState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_REGULAR_LIST):
    case REQUEST(ACTION_TYPES.FETCH_REGULAR):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_REGULAR):
    case REQUEST(ACTION_TYPES.UPDATE_REGULAR):
    case REQUEST(ACTION_TYPES.DELETE_REGULAR):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_REGULAR_LIST):
    case FAILURE(ACTION_TYPES.FETCH_REGULAR):
    case FAILURE(ACTION_TYPES.CREATE_REGULAR):
    case FAILURE(ACTION_TYPES.UPDATE_REGULAR):
    case FAILURE(ACTION_TYPES.DELETE_REGULAR):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_REGULAR_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_REGULAR):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_REGULAR):
    case SUCCESS(ACTION_TYPES.UPDATE_REGULAR):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_REGULAR):
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

const apiUrl = 'api/regulars';

// Actions

export const getEntities: ICrudGetAllAction<IRegular> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_REGULAR_LIST,
  payload: axios.get<IRegular>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IRegular> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_REGULAR,
    payload: axios.get<IRegular>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IRegular> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_REGULAR,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IRegular> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_REGULAR,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IRegular> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_REGULAR,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
