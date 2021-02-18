import { Moment } from 'moment';

export interface IVoucherInfo {
  id?: number;
  loginName?: string;
  data?: Moment;
}

export class VoucherInfo implements IVoucherInfo {
  constructor(public id?: number, public loginName?: string, public data?: Moment) {}
}
