import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IChemistry, defaultValue } from 'app/shared/model/chemistry.model';

export const ACTION_TYPES = {
  FETCH_CHEMISTRY_LIST: 'chemistry/FETCH_CHEMISTRY_LIST',
  FETCH_CHEMISTRY: 'chemistry/FETCH_CHEMISTRY',
  CREATE_CHEMISTRY: 'chemistry/CREATE_CHEMISTRY',
  UPDATE_CHEMISTRY: 'chemistry/UPDATE_CHEMISTRY',
  DELETE_CHEMISTRY: 'chemistry/DELETE_CHEMISTRY',
  RESET: 'chemistry/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IChemistry>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type ChemistryState = Readonly<typeof initialState>;

// Reducer

export default (state: ChemistryState = initialState, action): ChemistryState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_CHEMISTRY_LIST):
    case REQUEST(ACTION_TYPES.FETCH_CHEMISTRY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_CHEMISTRY):
    case REQUEST(ACTION_TYPES.UPDATE_CHEMISTRY):
    case REQUEST(ACTION_TYPES.DELETE_CHEMISTRY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_CHEMISTRY_LIST):
    case FAILURE(ACTION_TYPES.FETCH_CHEMISTRY):
    case FAILURE(ACTION_TYPES.CREATE_CHEMISTRY):
    case FAILURE(ACTION_TYPES.UPDATE_CHEMISTRY):
    case FAILURE(ACTION_TYPES.DELETE_CHEMISTRY):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_CHEMISTRY_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_CHEMISTRY):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_CHEMISTRY):
    case SUCCESS(ACTION_TYPES.UPDATE_CHEMISTRY):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_CHEMISTRY):
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

const apiUrl = 'api/chemistries';

// Actions

export const getEntities: ICrudGetAllAction<IChemistry> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_CHEMISTRY_LIST,
  payload: axios.get<IChemistry>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IChemistry> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_CHEMISTRY,
    payload: axios.get<IChemistry>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IChemistry> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_CHEMISTRY,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IChemistry> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_CHEMISTRY,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IChemistry> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_CHEMISTRY,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
