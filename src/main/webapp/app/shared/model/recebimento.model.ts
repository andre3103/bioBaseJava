import { Moment } from 'moment';

export interface IRecebimento {
  id?: number;
  loginName?: string;
  data?: Moment;
}

export class Recebimento implements IRecebimento {
  constructor(public id?: number, public loginName?: string, public data?: Moment) {}
}
