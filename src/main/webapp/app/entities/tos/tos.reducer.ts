import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ITOS, defaultValue } from 'app/shared/model/tos.model';

export const ACTION_TYPES = {
  FETCH_TOS_LIST: 'tOS/FETCH_TOS_LIST',
  FETCH_TOS: 'tOS/FETCH_TOS',
  CREATE_TOS: 'tOS/CREATE_TOS',
  UPDATE_TOS: 'tOS/UPDATE_TOS',
  DELETE_TOS: 'tOS/DELETE_TOS',
  SET_BLOB: 'tOS/SET_BLOB',
  RESET: 'tOS/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ITOS>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type TOSState = Readonly<typeof initialState>;

// Reducer

export default (state: TOSState = initialState, action): TOSState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_TOS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_TOS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_TOS):
    case REQUEST(ACTION_TYPES.UPDATE_TOS):
    case REQUEST(ACTION_TYPES.DELETE_TOS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_TOS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_TOS):
    case FAILURE(ACTION_TYPES.CREATE_TOS):
    case FAILURE(ACTION_TYPES.UPDATE_TOS):
    case FAILURE(ACTION_TYPES.DELETE_TOS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_TOS_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_TOS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_TOS):
    case SUCCESS(ACTION_TYPES.UPDATE_TOS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_TOS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {},
      };
    case ACTION_TYPES.SET_BLOB: {
      const { name, data, contentType } = action.payload;
      return {
        ...state,
        entity: {
          ...state.entity,
          [name]: data,
          [name + 'ContentType']: contentType,
        },
      };
    }
    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

const apiUrl = 'api/tos';

// Actions

export const getEntities: ICrudGetAllAction<ITOS> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_TOS_LIST,
  payload: axios.get<ITOS>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<ITOS> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_TOS,
    payload: axios.get<ITOS>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<ITOS> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_TOS,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ITOS> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_TOS,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ITOS> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_TOS,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const setBlob = (name, data, contentType?) => ({
  type: ACTION_TYPES.SET_BLOB,
  payload: {
    name,
    data,
    contentType,
  },
});

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
