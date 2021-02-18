import { IGerenciaId } from 'app/shared/model/gerencia-id.model';
import { ISequenciamento } from 'app/shared/model/sequenciamento.model';

export interface ITraceFile {
  id?: number;
  dirPhd?: string;
  dirAb?: string;
  idSample?: IGerenciaId;
  idSequenciamento?: ISequenciamento;
}

export class TraceFile implements ITraceFile {
  constructor(
    public id?: number,
    public dirPhd?: string,
    public dirAb?: string,
    public idSample?: IGerenciaId,
    public idSequenciamento?: ISequenciamento
  ) {}
}
