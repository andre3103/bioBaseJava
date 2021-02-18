import { IGerenciaId } from 'app/shared/model/gerencia-id.model';
import { IPlaca } from 'app/shared/model/placa.model';

export interface ISequenciamento {
  id?: number;
  posiao?: string;
  status?: number;
  idSample?: IGerenciaId;
  idPlaca?: IPlaca;
}

export class Sequenciamento implements ISequenciamento {
  constructor(public id?: number, public posiao?: string, public status?: number, public idSample?: IGerenciaId, public idPlaca?: IPlaca) {}
}
