import { IVoucherInfo } from 'app/shared/model/voucher-info.model';

export interface ITaxonomy {
  id?: number;
  phylum?: string;
  tclass?: string;
  torder?: string;
  family?: string;
  subfamily?: string;
  genus?: string;
  specie?: string;
  identif?: string;
  identifierEmail?: string;
  identifierInstitution?: string;
  identificationMethod?: string;
  taxonomyNotes?: string;
  voucherInfo?: IVoucherInfo;
}

export class Taxonomy implements ITaxonomy {
  constructor(
    public id?: number,
    public phylum?: string,
    public tclass?: string,
    public torder?: string,
    public family?: string,
    public subfamily?: string,
    public genus?: string,
    public specie?: string,
    public identif?: string,
    public identifierEmail?: string,
    public identifierInstitution?: string,
    public identificationMethod?: string,
    public taxonomyNotes?: string,
    public voucherInfo?: IVoucherInfo
  ) {}
}
