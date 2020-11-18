import axios from 'axios';
import {
  parseHeaderForLinks,
  loadMoreDataWhenScrolled,
  ICrudGetAction,
  ICrudGetAllAction,
  ICrudPutAction,
  ICrudDeleteAction,
} from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IInteract, defaultValue } from 'app/shared/model/interact.model';

export const ACTION_TYPES = {
  FETCH_INTERACT_LIST: 'interact/FETCH_INTERACT_LIST',
  FETCH_INTERACT: 'interact/FETCH_INTERACT',
  CREATE_INTERACT: 'interact/CREATE_INTERACT',
  UPDATE_INTERACT: 'interact/UPDATE_INTERACT',
  DELETE_INTERACT: 'interact/DELETE_INTERACT',
  SET_BLOB: 'interact/SET_BLOB',
  RESET: 'interact/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IInteract>,
  entity: defaultValue,
  links: { next: 0 },
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type InteractState = Readonly<typeof initialState>;

// Reducer

export default (state: InteractState = initialState, action): InteractState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_INTERACT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_INTERACT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_INTERACT):
    case REQUEST(ACTION_TYPES.UPDATE_INTERACT):
    case REQUEST(ACTION_TYPES.DELETE_INTERACT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_INTERACT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_INTERACT):
    case FAILURE(ACTION_TYPES.CREATE_INTERACT):
    case FAILURE(ACTION_TYPES.UPDATE_INTERACT):
    case FAILURE(ACTION_TYPES.DELETE_INTERACT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_INTERACT_LIST): {
      const links = parseHeaderForLinks(action.payload.headers.link);

      return {
        ...state,
        loading: false,
        links,
        entities: loadMoreDataWhenScrolled(state.entities, action.payload.data, links),
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    }
    case SUCCESS(ACTION_TYPES.FETCH_INTERACT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_INTERACT):
    case SUCCESS(ACTION_TYPES.UPDATE_INTERACT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_INTERACT):
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

const apiUrl = 'api/interacts';

// Actions

export const getEntities: ICrudGetAllAction<IInteract> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_INTERACT_LIST,
    payload: axios.get<IInteract>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<IInteract> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_INTERACT,
    payload: axios.get<IInteract>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IInteract> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_INTERACT,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const updateEntity: ICrudPutAction<IInteract> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_INTERACT,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IInteract> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_INTERACT,
    payload: axios.delete(requestUrl),
  });
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
