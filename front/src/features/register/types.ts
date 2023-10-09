import { MANAGEMENT_ROLES } from '../api/types';

export interface RegisterSliceState {
  registerAccountInfo?: RegisterAccountInfo;
  currentStep: number;
}

export interface RegisterAccountInfo {
  login?: string;
  password?: string;
  email?: string;
  name?: string;
  lastname?: string;
  locale?: string;
  role?: MANAGEMENT_ROLES;
}
