import { Moment } from 'moment';
import { IVoucherInfo } from 'app/shared/model/voucher-info.model';
import { IEntrega } from 'app/shared/model/entrega.model';
import { IRecebimento } from 'app/shared/model/recebimento.model';

export interface IGerenciaId {
  id?: number;
  loginName?: string;
  dataGeracao?: Moment;
  status?: string;
  voucherInfo?: IVoucherInfo;
  entrega?: IEntrega;
  recebimento?: IRecebimento;
}

export class GerenciaId implements IGerenciaId {
  constructor(
    public id?: number,
    public loginName?: string,
    public dataGeracao?: Moment,
    public status?: string,
    public voucherInfo?: IVoucherInfo,
    public entrega?: IEntrega,
    public recebimento?: IRecebimento
  ) {}
}
