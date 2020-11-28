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

import { IAvatar, defaultValue } from 'app/shared/model/avatar.model';

export const ACTION_TYPES = {
  FETCH_AVATAR_LIST: 'avatar/FETCH_AVATAR_LIST',
  FETCH_AVATAR: 'avatar/FETCH_AVATAR',
  CREATE_AVATAR: 'avatar/CREATE_AVATAR',
  UPDATE_AVATAR: 'avatar/UPDATE_AVATAR',
  DELETE_AVATAR: 'avatar/DELETE_AVATAR',
  SET_BLOB: 'avatar/SET_BLOB',
  RESET: 'avatar/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IAvatar>,
  entity: defaultValue,
  links: { next: 0 },
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type AvatarState = Readonly<typeof initialState>;

// Reducer

export default (state: AvatarState = initialState, action): AvatarState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_AVATAR_LIST):
    case REQUEST(ACTION_TYPES.FETCH_AVATAR):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_AVATAR):
    case REQUEST(ACTION_TYPES.UPDATE_AVATAR):
    case REQUEST(ACTION_TYPES.DELETE_AVATAR):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_AVATAR_LIST):
    case FAILURE(ACTION_TYPES.FETCH_AVATAR):
    case FAILURE(ACTION_TYPES.CREATE_AVATAR):
    case FAILURE(ACTION_TYPES.UPDATE_AVATAR):
    case FAILURE(ACTION_TYPES.DELETE_AVATAR):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_AVATAR_LIST): {
      const links = parseHeaderForLinks(action.payload.headers.link);

      return {
        ...state,
        loading: false,
        links,
        entities: loadMoreDataWhenScrolled(state.entities, action.payload.data, links),
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    }
    case SUCCESS(ACTION_TYPES.FETCH_AVATAR):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_AVATAR):
    case SUCCESS(ACTION_TYPES.UPDATE_AVATAR):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_AVATAR):
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

const apiUrl = 'api/avatars';

// Actions

export const getEntities: ICrudGetAllAction<IAvatar> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_AVATAR_LIST,
    payload: axios.get<IAvatar>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<IAvatar> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_AVATAR,
    payload: axios.get<IAvatar>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IAvatar> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_AVATAR,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const updateEntity: ICrudPutAction<IAvatar> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_AVATAR,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IAvatar> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_AVATAR,
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
