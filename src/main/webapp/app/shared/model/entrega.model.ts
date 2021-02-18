import { Moment } from 'moment';

export interface IEntrega {
  id?: number;
  loginName?: string;
  data?: Moment;
}

export class Entrega implements IEntrega {
  constructor(public id?: number, public loginName?: string, public data?: Moment) {}
}
