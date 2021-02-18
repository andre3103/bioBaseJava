import { Moment } from 'moment';
import { IGerenciaId } from 'app/shared/model/gerencia-id.model';

export interface IBarcode {
  id?: number;
  marcador?: string;
  sequencia?: string;
  data?: Moment;
  idSample?: IGerenciaId;
}

export class Barcode implements IBarcode {
  constructor(
    public id?: number,
    public marcador?: string,
    public sequencia?: string,
    public data?: Moment,
    public idSample?: IGerenciaId
  ) {}
}
