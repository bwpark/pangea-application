import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IEmotion, defaultValue } from 'app/shared/model/emotion.model';

export const ACTION_TYPES = {
  FETCH_EMOTION_LIST: 'emotion/FETCH_EMOTION_LIST',
  FETCH_EMOTION: 'emotion/FETCH_EMOTION',
  CREATE_EMOTION: 'emotion/CREATE_EMOTION',
  UPDATE_EMOTION: 'emotion/UPDATE_EMOTION',
  DELETE_EMOTION: 'emotion/DELETE_EMOTION',
  RESET: 'emotion/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IEmotion>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type EmotionState = Readonly<typeof initialState>;

// Reducer

export default (state: EmotionState = initialState, action): EmotionState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_EMOTION_LIST):
    case REQUEST(ACTION_TYPES.FETCH_EMOTION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_EMOTION):
    case REQUEST(ACTION_TYPES.UPDATE_EMOTION):
    case REQUEST(ACTION_TYPES.DELETE_EMOTION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_EMOTION_LIST):
    case FAILURE(ACTION_TYPES.FETCH_EMOTION):
    case FAILURE(ACTION_TYPES.CREATE_EMOTION):
    case FAILURE(ACTION_TYPES.UPDATE_EMOTION):
    case FAILURE(ACTION_TYPES.DELETE_EMOTION):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_EMOTION_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_EMOTION):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_EMOTION):
    case SUCCESS(ACTION_TYPES.UPDATE_EMOTION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_EMOTION):
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

const apiUrl = 'api/emotions';

// Actions

export const getEntities: ICrudGetAllAction<IEmotion> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_EMOTION_LIST,
  payload: axios.get<IEmotion>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IEmotion> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_EMOTION,
    payload: axios.get<IEmotion>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IEmotion> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_EMOTION,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IEmotion> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_EMOTION,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IEmotion> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_EMOTION,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
