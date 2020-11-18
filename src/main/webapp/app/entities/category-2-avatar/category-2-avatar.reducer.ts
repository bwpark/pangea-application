import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ICategory2avatar, defaultValue } from 'app/shared/model/category-2-avatar.model';

export const ACTION_TYPES = {
  FETCH_CATEGORY2AVATAR_LIST: 'category2avatar/FETCH_CATEGORY2AVATAR_LIST',
  FETCH_CATEGORY2AVATAR: 'category2avatar/FETCH_CATEGORY2AVATAR',
  CREATE_CATEGORY2AVATAR: 'category2avatar/CREATE_CATEGORY2AVATAR',
  UPDATE_CATEGORY2AVATAR: 'category2avatar/UPDATE_CATEGORY2AVATAR',
  DELETE_CATEGORY2AVATAR: 'category2avatar/DELETE_CATEGORY2AVATAR',
  RESET: 'category2avatar/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ICategory2avatar>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type Category2avatarState = Readonly<typeof initialState>;

// Reducer

export default (state: Category2avatarState = initialState, action): Category2avatarState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_CATEGORY2AVATAR_LIST):
    case REQUEST(ACTION_TYPES.FETCH_CATEGORY2AVATAR):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_CATEGORY2AVATAR):
    case REQUEST(ACTION_TYPES.UPDATE_CATEGORY2AVATAR):
    case REQUEST(ACTION_TYPES.DELETE_CATEGORY2AVATAR):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_CATEGORY2AVATAR_LIST):
    case FAILURE(ACTION_TYPES.FETCH_CATEGORY2AVATAR):
    case FAILURE(ACTION_TYPES.CREATE_CATEGORY2AVATAR):
    case FAILURE(ACTION_TYPES.UPDATE_CATEGORY2AVATAR):
    case FAILURE(ACTION_TYPES.DELETE_CATEGORY2AVATAR):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_CATEGORY2AVATAR_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_CATEGORY2AVATAR):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_CATEGORY2AVATAR):
    case SUCCESS(ACTION_TYPES.UPDATE_CATEGORY2AVATAR):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_CATEGORY2AVATAR):
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

const apiUrl = 'api/category-2-avatars';

// Actions

export const getEntities: ICrudGetAllAction<ICategory2avatar> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_CATEGORY2AVATAR_LIST,
  payload: axios.get<ICategory2avatar>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<ICategory2avatar> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_CATEGORY2AVATAR,
    payload: axios.get<ICategory2avatar>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<ICategory2avatar> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_CATEGORY2AVATAR,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ICategory2avatar> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_CATEGORY2AVATAR,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ICategory2avatar> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_CATEGORY2AVATAR,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
