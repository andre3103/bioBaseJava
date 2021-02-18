import { Moment } from 'moment';

export interface IPlaca {
  id?: number;
  dataMontagem?: Moment;
  marcador?: string;
  sequencia?: string;
  data?: Moment;
  status?: number;
}

export class Placa implements IPlaca {
  constructor(
    public id?: number,
    public dataMontagem?: Moment,
    public marcador?: string,
    public sequencia?: string,
    public data?: Moment,
    public status?: number
  ) {}
}
